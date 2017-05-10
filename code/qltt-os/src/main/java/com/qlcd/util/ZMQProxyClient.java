package com.qlcd.util;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;

import com.google.protobuf.InvalidProtocolBufferException;
import com.qlcd.qltt.body.BppSys;
import com.qlcd.qltt.head.Hpprot;

public class ZMQProxyClient {
	
	private Logger logger = Logger.getLogger(ZMQProxyClient.class);

	private String proxyIp;

	private int proxyPort;

	private int rcvTimeOut = 6;

	private int sndTimeOut = 6;
	
	boolean validCRC = true;
	
	private ZMQ.Context context;
	
	public ZMQProxyClient() {
		context = ZMQ.context(1);
	}

	public String getProxyIp() {
		return proxyIp;
	}

	public void setProxyIp(String proxyIp) {
		this.proxyIp = proxyIp;
	}

	public int getProxyPort() {
		return proxyPort;
	}

	public void setProxyPort(int proxyPort) {
		this.proxyPort = proxyPort;
	}

	public int getRcvTimeOut() {
		return rcvTimeOut;
	}

	public void setRcvTimeOut(int rcvTimeOut) {
		this.rcvTimeOut = rcvTimeOut;
	}

	public int getSndTimeOut() {
		return sndTimeOut;
	}

	public void setSndTimeOut(int sndTimeOut) {
		this.sndTimeOut = sndTimeOut;
	}

	public boolean isValidCRC() {
		return validCRC;
	}

	public void setValidCRC(boolean validCRC) {
		this.validCRC = validCRC;
	}

	@SuppressWarnings("unchecked")
	public <T> T outBound(String trdCode, Object requestBody) {
		if (StringUtils.isBlank(trdCode))
			throw new CommZMQException("交易码为空");

		if (requestBody == null)
			throw new CommZMQException("请求报文体为空");

		String proxyAddr = String.format("tcp://%s:%d", proxyIp, proxyPort);
		org.jeecgframework.core.util.LogUtil.info("Proxy Addr:" + proxyAddr);

		byte[] reqHeadByte = null;
		byte[] reqBodyByte = null;
		byte[] rspHeadByte = null;
		byte[] rspBodyByte = null;
		T responBody = null;

		// 生成请求头
		Hpprot._req.Builder reqHeadBuilder = Hpprot._req.newBuilder();
		reqHeadBuilder.setTrdcode(Integer.parseInt(trdCode));
		reqHeadBuilder.setReqno(ProtocolUtil.BuildTxSN());
		reqHeadBuilder.setReqsys(ProtocolUtil.getSystemNo());
		reqHeadBuilder.setReqnode(ProtocolUtil.getNodeNo());
		reqHeadBuilder.setBodyclass(requestBody.getClass().getName().replace("$_req", "._req"));

		try {
			Method toByteArray = requestBody.getClass().getMethod("toByteArray");
			reqBodyByte = (byte[]) toByteArray.invoke(requestBody);
		} catch (Exception e) {
			throw new CommZMQException(e.getMessage());
		}

		reqHeadBuilder.setMac(ProtocolUtil.getCRC32(reqBodyByte));
		Hpprot._req reqHead = reqHeadBuilder.build();
		reqHeadByte = reqHead.toByteArray();

		if (reqHeadByte == null || reqHeadByte.length == 0)
			throw new CommZMQException("请求报文头长度为0");

		Socket requester = null;
		int recvTimes = 0;
		try {
			requester = context.socket(ZMQ.REQ);
			requester.connect(proxyAddr);
			requester.setReceiveTimeOut(rcvTimeOut * 1000);
			requester.setSendTimeOut(sndTimeOut * 1000);

			if (!requester.send(reqHeadByte, org.zeromq.ZMQ.SNDMORE))
				throw new CommZMQException("请求交易头部未发送成功");
			if (!requester.send(reqBodyByte, 0))
				throw new CommZMQException("请求交易体未发送成功");
			rspHeadByte = requester.recv(0);
			
			if (rspHeadByte == null)
				throw new CommZMQException("网络通讯错误: 未接收到任何数据");

			while (requester.hasReceiveMore()) {
				rspBodyByte = requester.recv(0);
				recvTimes ++;
			}
		} catch (Exception e) {
			logger.error("网络通讯错误", e);
			throw new CommZMQException("网络通讯错误:" + e.getMessage());
		} finally {
			if (requester != null)
				requester.close();
		}

		if (recvTimes > 1)
			throw new CommZMQException("接收报文体发生错误,无法确定接收的报文体");

		Hpprot._rsp rspHead;
		try {
			rspHead = Hpprot._rsp.parseFrom(rspHeadByte);
		} catch (InvalidProtocolBufferException e) {
			throw new CommZMQException("解析响应报文头出错:" + e.getMessage());
		}
		
		org.jeecgframework.core.util.LogUtil.info(String.format("响应报文头主要信息: { [%d] [%s] [%d] [%d] }", rspHead.getTrdcode(), rspHead.getBodyclass(), rspHead.getReqno(), rspHead.getToken()));

		// 头部验证
		if (rspHead.getTrdcode() != reqHead.getTrdcode()) {
			throw new CommZMQException("请求交易码与响应交易码不一致");
		}

		if (rspHead.getReqno() != reqHead.getReqno()) {
			throw new CommZMQException("请求流水号与响应流水号不一致");
		}

		if (validCRC && !ProtocolUtil.checkCRC32(rspHead.getMac(), rspBodyByte)) {
			throw new CommZMQException("协议CRC校验错误");
		}

		if (rspHead.getRspstatus().equals(com.qlcd.qltt.head.Hpem.PEH_RSPSTATUS.EV_FAILURE)) {
			BppSys._rsp_genfailhead failHead = null;
			try {
				failHead = BppSys._rsp_genfailhead.parseFrom(rspBodyByte);
			} catch (InvalidProtocolBufferException e) {
				throw new CommZMQException("解析失败响应报文头出错:" + e.getMessage());
			}
			String failMsg = String.format("Fail:%d Message:%s", failHead.getRspcode(), failHead.getRspmsg());
			throw new CommZMQException(failMsg);
		}

		if (rspHead.getRspstatus().equals(com.qlcd.qltt.head.Hpem.PEH_RSPSTATUS.EV_EXCEPTION)) {
			BppSys._rsp_genexcphead excpHead = null;
			try {
				excpHead = BppSys._rsp_genexcphead.parseFrom(rspBodyByte);
			} catch (InvalidProtocolBufferException e) {
				throw new CommZMQException("解析失败响应报文头出错:" + e.getMessage());
			}
			String failMsg = String.format("Fail:%d Message:%s DebugInfo:%s", excpHead.getExcpcode(),
					excpHead.getExcpmsg(), excpHead.getDebuginfo());
			throw new CommZMQException(failMsg);
		}
		// 如果OK则根据请求头解析请求体
		try {
			Class<?> clazz = Class.forName(rspHead.getBodyclass().replace("._rsp", "$_rsp"));
			Method parseFrom = clazz.getMethod("parseFrom", byte[].class);
			responBody = (T)parseFrom.invoke(null, rspBodyByte);
		} catch (Exception e) {
			throw new CommZMQException("响应报文体反序列化出现错误:" + e.getMessage());
		}
		return responBody;
	}
}
