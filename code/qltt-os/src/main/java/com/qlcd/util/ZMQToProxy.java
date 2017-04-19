package com.qlcd.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;

import com.google.protobuf.InvalidProtocolBufferException;
import com.qlcd.qltt.body.BppSys;
import com.qlcd.qltt.head.Hpprot;

public class ZMQToProxy {

	private String proxyIp;

	private int proxyPort;

	private int rcvTimeOut = 6;

	private int sndTimeOut = 6;

	private int num = 0;

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

	static ZMQ.Context rcontext = null;

	static {
		rcontext = ZMQ.context(1);
	}

	public void InBound(InvocationHandler handler, IBusProcess busProcess) {

		IBusProcess busProcessProxy = (IBusProcess) Proxy.newProxyInstance(handler.getClass().getClassLoader(),
				busProcess.getClass().getInterfaces(), handler);

		String proxyAddr = String.format("tcp://%s:%d", proxyIp, proxyPort);
		byte[] reqHeadByte = null;
		byte[] reqBodyByte = null;
		Object responBody = null;
		Object requestBody = null;

		Socket responder = null;
		boolean connFlag = false;

		while (!Thread.currentThread().isInterrupted()) {
			if (connFlag == false) {
				if (responder != null)
					responder.close();
				responder = rcontext.socket(ZMQ.REP);
				responder.connect(proxyAddr);
				connFlag = true;
			}

			int recvTimes = 0;
			try {
				reqHeadByte = responder.recv(0);
				if (reqHeadByte != null && reqHeadByte.length > 0)
					org.jeecgframework.core.util.LogUtil.info("线程名称：" + Thread.currentThread().getName()
							+ "Request Head:[" + new String(reqHeadByte) + "]");

				while (responder.hasReceiveMore()) {
					reqBodyByte = responder.recv(0);
					if (reqBodyByte != null && reqBodyByte.length > 0)
						org.jeecgframework.core.util.LogUtil.info("线程名称：" + Thread.currentThread().getName()
								+ "Request Body:[" + new String(reqBodyByte) + "]");
					recvTimes++;
				}
			} catch (Exception e) {
				doRecvExceptionReply(responder);
				connFlag = false;
			}

			if (recvTimes > 1) {
				org.jeecgframework.core.util.LogUtil.warning("接收请求报文体发生了次数为：" + recvTimes);
			}
			Hpprot._req requestHead = null;
			if (reqHeadByte != null && reqHeadByte.length > 0) {
				try {
					requestHead = Hpprot._req.parseFrom(reqHeadByte);
				} catch (InvalidProtocolBufferException e) {
					doHeadExceptionReply(responder);
				}
			}

			if (reqBodyByte != null && reqBodyByte.length > 0) {
				try {
					Class<?> clazz = Class.forName(requestHead.getBodyclass().replace("._req", "$_req"));
					Method parseFrom = clazz.getMethod("parseFrom", byte[].class);
					requestBody = parseFrom.invoke(clazz, reqBodyByte);
				} catch (Exception e) {
					doBodyExceptionReply(responder, requestHead);
				}
			}

			try {
				responBody = busProcessProxy.active(requestHead.getTrdcode(), requestBody);
			} catch (Exception e) {
				doBusExceptionReply(responder, requestHead, e.getMessage());
			}
			doNormalReply(responder, requestHead, responBody);
			if (num == 50) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				num = 0;
			}
			num ++;

		}
		responder.close();
		rcontext.term();

	}

	private void doReply(Socket socket, Hpprot._rsp.Builder rspHeadBuilder, Object rspBody) {
		rspHeadBuilder.setBodyclass(rspBody.getClass().getName().replace("$_rsp", "._rsp"));
		rspHeadBuilder.setRspsys(ProtocolUtil.getSystemNo());
		rspHeadBuilder.setRspnode(ProtocolUtil.getNodeNo());

		byte[] rspHeadByte = null;
		byte[] rspBodyByte = null;
		Class<?> clazz;
		try {
			clazz = Class.forName(rspBody.getClass().getName());
			Method toByteArray = clazz.getMethod("toByteArray");
			rspBodyByte = (byte[]) toByteArray.invoke(rspBody);

			rspHeadBuilder.setMac(ProtocolUtil.getCRC32(rspBodyByte));
			rspHeadByte = rspHeadBuilder.build().toByteArray();
		} catch (Exception e) {
			org.jeecgframework.core.util.LogUtil.info("响应反序列化错误:" + e.getMessage());
			throw new CommZMQException("响应反序列化错误:" + e.getMessage());
		} finally {
			socket.send(rspHeadByte, org.zeromq.ZMQ.SNDMORE);
			socket.send(rspBodyByte, 0);
		}
	}

	private void doRecvExceptionReply(Socket socket) {
		Hpprot._rsp.Builder rspHeadBuilder = Hpprot._rsp.newBuilder();
		rspHeadBuilder.setTrdcode(0);
		rspHeadBuilder.setReqno(0);
		rspHeadBuilder.setRspstatus(com.qlcd.qltt.head.Hpem.PEH_RSPSTATUS.EV_EXCEPTION);

		BppSys._rsp_genexcphead.Builder excpHeadBuilder = BppSys._rsp_genexcphead.newBuilder();
		excpHeadBuilder.setExcpcode("9999");
		excpHeadBuilder.setExcpmsg("接收数据异常");

		doReply(socket, rspHeadBuilder, excpHeadBuilder.build());
	}

	private void doHeadExceptionReply(Socket socket) {
		Hpprot._rsp.Builder rspHeadBuilder = Hpprot._rsp.newBuilder();
		rspHeadBuilder.setTrdcode(0);
		rspHeadBuilder.setReqno(0);
		rspHeadBuilder.setRspstatus(com.qlcd.qltt.head.Hpem.PEH_RSPSTATUS.EV_EXCEPTION);

		BppSys._rsp_genexcphead.Builder excpHeadBuilder = BppSys._rsp_genexcphead.newBuilder();
		excpHeadBuilder.setExcpcode("9998");
		excpHeadBuilder.setExcpmsg("接收数据头解析出现异常");

		doReply(socket, rspHeadBuilder, excpHeadBuilder.build());
	}

	private void doBodyExceptionReply(Socket socket, Hpprot._req requestHead) {
		Hpprot._rsp.Builder rspHeadBuilder = Hpprot._rsp.newBuilder();
		rspHeadBuilder.setTrdcode(requestHead.getTrdcode());
		rspHeadBuilder.setReqno(requestHead.getReqno());
		rspHeadBuilder.setRspstatus(com.qlcd.qltt.head.Hpem.PEH_RSPSTATUS.EV_EXCEPTION);

		BppSys._rsp_genexcphead.Builder excpHeadBuilder = BppSys._rsp_genexcphead.newBuilder();
		excpHeadBuilder.setExcpcode("9997");
		excpHeadBuilder.setExcpmsg("接收数据体解析出现异常");

		doReply(socket, rspHeadBuilder, excpHeadBuilder.build());
	}

	private void doBusExceptionReply(Socket socket, Hpprot._req requestHead, String message) {
		Hpprot._rsp.Builder rspHeadBuilder = Hpprot._rsp.newBuilder();
		rspHeadBuilder.setTrdcode(requestHead.getTrdcode());
		rspHeadBuilder.setReqno(requestHead.getReqno());
		rspHeadBuilder.setRspstatus(com.qlcd.qltt.head.Hpem.PEH_RSPSTATUS.EV_FAILURE);

		BppSys._rsp_genfailhead.Builder failHeadBuilder = BppSys._rsp_genfailhead.newBuilder();
		failHeadBuilder.setRspcode(1);
		failHeadBuilder.setRspmsg(message);

		doReply(socket, rspHeadBuilder, failHeadBuilder.build());

	}

	private void doNormalReply(Socket socket, Hpprot._req requestHead, Object responseBody) {

		Hpprot._rsp.Builder rspHeadBuilder = Hpprot._rsp.newBuilder();
		rspHeadBuilder.setTrdcode(requestHead.getToken());
		rspHeadBuilder.setReqno(requestHead.getReqno());
		rspHeadBuilder.setRspstatus(com.qlcd.qltt.head.Hpem.PEH_RSPSTATUS.EV_SUCCESS);

		doReply(socket, rspHeadBuilder, responseBody);

	}
}
