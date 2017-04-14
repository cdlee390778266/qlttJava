package com.qlcd.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.commons.lang3.StringUtils;
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

	static ZMQ.Context scontext = null;
	static ZMQ.Context rcontext = null;

	static {
		scontext = ZMQ.context(1);
		rcontext = ZMQ.context(1);
	}

	public Object OutBound(String trdCode, Object requestBody){

		if(StringUtils.isBlank(trdCode))
			throw new CommZMQException("交易码为空");
		
		if(requestBody == null)
			throw new CommZMQException("请求报文体为空");

		String proxyAddr = String.format("tcp://%s:%d", proxyIp, proxyPort);		
		org.jeecgframework.core.util.LogUtil.info("Proxy Addr:"+proxyAddr);

		byte[] reqHeadByte = null;
		byte[] reqBodyByte = null;
		byte[] rspHeadByte = null;
		byte[] rspBodyByte = null;
		Object responBody = null;

		// 生成请求头
		Hpprot._req.Builder reqHeadBuilder = Hpprot._req.newBuilder();
		reqHeadBuilder.setTrdcode(Integer.parseInt(trdCode));
		reqHeadBuilder.setReqno(ProtocolUtil.BuildTxSN());
		reqHeadBuilder.setReqsys(ProtocolUtil.getSystemNo());
		reqHeadBuilder.setReqnode(ProtocolUtil.getNodeNo());
		reqHeadBuilder.setBodyclass(requestBody.getClass().getName());

		try {
			Class<?> clazzs = Class.forName(requestBody.getClass().getName().replace("$_req", "._req"));
			Method toByteArray = clazzs.getMethod("toByteArray");
			reqBodyByte = (byte[]) toByteArray.invoke(requestBody);
		} catch (Exception e1) {
			throw new CommZMQException(e1.getMessage());
		} 
		
		if(reqBodyByte == null && reqBodyByte.length == 0)
			throw new CommZMQException("请求报文体长度为0");
		
		reqHeadBuilder.setMac(ProtocolUtil.getCRC32(reqBodyByte));
		Hpprot._req reqHead = reqHeadBuilder.build();
		reqHeadByte = reqHead.toByteArray();
		
		if(reqHeadByte == null && reqHeadByte.length == 0)
			throw new CommZMQException("请求报文头长度为0");
		
		Socket requester = null;
		int recvTimes = 0;
		try{		
			requester = scontext.socket(ZMQ.REQ);
			requester.connect(proxyAddr);
			requester.setReceiveTimeOut(rcvTimeOut * 1000);
			requester.setSendTimeOut(sndTimeOut * 1000);
			
			requester.send(reqHeadByte, org.zeromq.ZMQ.SNDMORE);
			requester.send(reqBodyByte, 0);
			rspHeadByte = requester.recv(0);

			org.jeecgframework.core.util.LogUtil.info("Response Head:["+new String(rspHeadByte)+"]");
			
			while (requester.hasReceiveMore()) {
				rspBodyByte = requester.recv(0);
				if(rspBodyByte != null && rspBodyByte.length > 0)
					org.jeecgframework.core.util.LogUtil.info("Response Body:["+ new String(rspBodyByte) + "]");
				recvTimes++;
			}
		}catch(Exception e1){
			throw new CommZMQException("网络通讯错误:"+e1.getMessage());			
		}finally{
			if(requester != null)requester.close();
		}
		
		if(recvTimes > 1)
			throw new CommZMQException("接收报文体发生错误,无法确定接收的报文体");

		Hpprot._rsp rspHead;		
		try {
			rspHead = Hpprot._rsp.parseFrom(rspHeadByte);
		} catch (InvalidProtocolBufferException e) {
			throw new CommZMQException("解析响应报文头出错:"+e.getMessage());		
		}

		// 头部验证
		if(rspHead.getTrdcode() != reqHead.getTrdcode()){
			throw new CommZMQException("请求交易码与响应交易码不一致");
		}
				
		if(rspHead.getReqno() != reqHead.getReqno()){
			throw new CommZMQException("请求流水号与响应流水号不一致");
		}
				
		if(!ProtocolUtil.checkCRC32(rspHead.getMac(), rspBodyByte)){
			throw new CommZMQException("协议CRC校验错误");
		}				
				
		if(rspHead.getRspstatus().equals(com.qlcd.qltt.head.Hpem.PEH_RSPSTATUS.EV_FAILURE)){
			BppSys._rsp_genfailhead failHead = null;
			try{
				BppSys._rsp_genfailhead.parseFrom(rspBodyByte);
			} catch (InvalidProtocolBufferException e) {
				throw new CommZMQException("解析失败响应报文头出错:"+e.getMessage());		
			}
			String failMsg = String.format("Fail:%d Message:%s", failHead.getRspcode(), failHead.getRspmsg());
			throw new CommZMQException(failMsg);					
		}
				
		if(rspHead.getRspstatus().equals(com.qlcd.qltt.head.Hpem.PEH_RSPSTATUS.EV_EXCEPTION)){
			BppSys._rsp_genexcphead excpHead = null;		
			try{
				excpHead = BppSys._rsp_genexcphead.parseFrom(rspBodyByte);
			} catch (InvalidProtocolBufferException e) {
				throw new CommZMQException("解析失败响应报文头出错:"+e.getMessage());		
			}
			String failMsg = String.format("Fail:%d Message:%s DebugInfo:%s", excpHead.getExcpcode(), excpHead.getExcpmsg(),  excpHead.getDebuginfo());
			throw new CommZMQException(failMsg);					
		}
		// 如果OK则根据请求头解析请求体
		try {
			Class<?>  clazzr = Class.forName(rspHead.getBodyclass().replace("._rsp", "$_rsp"));
			Method parseFrom = clazzr.getMethod("parseFrom", byte[].class);
			responBody = parseFrom.invoke(clazzr, rspBodyByte);
		} catch (Exception e) {
			throw new CommZMQException("响应报文体反序列号出现错误:"+e.getMessage());
		} 
		return responBody;
	}

	
	public void InBound(InvocationHandler handler, IBusProcess busProcess) {

		IBusProcess busProcessProxy = (IBusProcess) Proxy.newProxyInstance(
				handler.getClass().getClassLoader(), busProcess.getClass()
						.getInterfaces(), handler);

		String proxyAddr = String.format("tcp://%s:%d", proxyIp, proxyPort);
		byte[] reqHeadByte = null;
		byte[] reqBodyByte = null;
		Object responBody = null;
		Object requestBody = null;
		
		Socket responder = null;
		boolean connFlag = false;

		while (!Thread.currentThread().isInterrupted()) {
			if(connFlag == false){
				if(responder != null)
					responder.close();
				responder = rcontext.socket(ZMQ.REP);
				responder.connect(proxyAddr);
				connFlag = true;
			}
			
			int recvTimes = 0;
			try {
				reqHeadByte = responder.recv(0);
				if(reqHeadByte != null && reqHeadByte.length > 0)
				org.jeecgframework.core.util.LogUtil.info("线程名称："+Thread.currentThread().getName()+"Request Head:["
						+ new String(reqHeadByte) + "]");
				
				while (responder.hasReceiveMore()) {
					reqBodyByte = responder.recv(0);
					if(reqBodyByte != null && reqBodyByte.length > 0)
						org.jeecgframework.core.util.LogUtil.info("线程名称："+Thread.currentThread().getName()+"Request Body:["
								+ new String(reqBodyByte) + "]");
					recvTimes++;
				}
			} catch (Exception e) {
				doRecvExceptionReply(responder);
				connFlag = false;
			}
			
			if (recvTimes > 1) {
				org.jeecgframework.core.util.LogUtil.warning("接收请求报文体发生了次数为："+recvTimes);
			}

			Hpprot._req requestHead = null;
			if(reqHeadByte != null && reqHeadByte.length > 0 ){
				try {
					requestHead = Hpprot._req.parseFrom(reqHeadByte);
				} catch (InvalidProtocolBufferException e) {
					doHeadExceptionReply(responder);	
				}
			}
			
			if(reqBodyByte != null && reqBodyByte.length > 0){
				try {
					Class<?> clazzr  = Class.forName(requestHead.getBodyclass().replace("._req", "$_req"));
					Method parseFrom = clazzr.getMethod("parseFrom",byte[].class);
					requestBody = parseFrom.invoke(clazzr, reqBodyByte);	
				} catch (Exception e) {
					doBodyExceptionReply(responder, requestHead);
				}
			}
					
			try{
				responBody = busProcessProxy.active(requestHead.getTrdcode(),requestBody);
			}catch(Exception e){
				doBusExceptionReply(responder, requestHead, e.getMessage());
			}		
			doNormalReply(responder, requestHead, responBody);
		}

		responder.close();
		rcontext.term();
	}
	

	private void doReply(Socket socket, Hpprot._rsp rspHead, Object rspBody){
		byte[] rspHeadByte = rspHead.toByteArray();
		byte[] rspBodyByte = null;
		Class<?> clazz;
		try {
			clazz = Class
					.forName(rspBody.getClass().getName());
			Method toByteArray = clazz.getMethod("toByteArray");
			rspBodyByte = (byte[]) toByteArray.invoke(rspBody);	
		} catch (Exception e) {
			org.jeecgframework.core.util.LogUtil.info("响应反序列化错误:"+e.getMessage());
			throw new CommZMQException("响应反序列化错误:"+e.getMessage());
		}finally{
			socket.send(rspHeadByte, org.zeromq.ZMQ.SNDMORE);
			socket.send(rspBodyByte, 0);
		}
	}


	private void doRecvExceptionReply(Socket socket){
		Hpprot._rsp.Builder rspHeadBuilder = Hpprot._rsp.newBuilder();
		rspHeadBuilder.setTrdcode(0);
		rspHeadBuilder.setReqno(0);
		rspHeadBuilder.setRspsys(ProtocolUtil.getSystemNo());
		rspHeadBuilder.setRspnode(ProtocolUtil.getNodeNo());
		rspHeadBuilder.setRspstatus(com.qlcd.qltt.head.Hpem.PEH_RSPSTATUS.EV_EXCEPTION);
	
		BppSys._rsp_genexcphead.Builder  excpHeadBuilder = BppSys._rsp_genexcphead.newBuilder();
		excpHeadBuilder.setExcpcode("9999");
		excpHeadBuilder.setExcpmsg("接收数据异常");	
		
		BppSys._rsp_genexcphead rspBody = excpHeadBuilder.build();
		rspHeadBuilder.setMac(ProtocolUtil.getCRC32(rspBody.toByteArray()));
		
		doReply(socket, rspHeadBuilder.build(), rspBody);
	}
	

	private void doHeadExceptionReply(Socket socket){
		Hpprot._rsp.Builder rspHeadBuilder = Hpprot._rsp.newBuilder();
		rspHeadBuilder.setTrdcode(0);
		rspHeadBuilder.setReqno(0);
		rspHeadBuilder.setRspsys(ProtocolUtil.getSystemNo());
		rspHeadBuilder.setRspnode(ProtocolUtil.getNodeNo());
		rspHeadBuilder.setRspstatus(com.qlcd.qltt.head.Hpem.PEH_RSPSTATUS.EV_EXCEPTION);
	
		BppSys._rsp_genexcphead.Builder  excpHeadBuilder = BppSys._rsp_genexcphead.newBuilder();
		excpHeadBuilder.setExcpcode("9998");
		excpHeadBuilder.setExcpmsg("接收数据头解析出现异常");	
		
		BppSys._rsp_genexcphead rspBody = excpHeadBuilder.build();
		rspHeadBuilder.setMac(ProtocolUtil.getCRC32(rspBody.toByteArray()));
		
		doReply(socket, rspHeadBuilder.build(), rspBody);
	}
	
	private void doBodyExceptionReply(Socket socket, Hpprot._req requestHead){
		Hpprot._rsp.Builder rspHeadBuilder = Hpprot._rsp.newBuilder();
		rspHeadBuilder.setTrdcode(requestHead.getToken());
		rspHeadBuilder.setReqno(requestHead.getReqno());
		rspHeadBuilder.setRspsys(ProtocolUtil.getSystemNo());
		rspHeadBuilder.setRspnode(ProtocolUtil.getNodeNo());
		rspHeadBuilder.setRspstatus(com.qlcd.qltt.head.Hpem.PEH_RSPSTATUS.EV_EXCEPTION);
	
		BppSys._rsp_genexcphead.Builder  excpHeadBuilder = BppSys._rsp_genexcphead.newBuilder();
		excpHeadBuilder.setExcpcode("9997");
		excpHeadBuilder.setExcpmsg("接收数据体解析出现异常");	
		
		BppSys._rsp_genexcphead rspBody = excpHeadBuilder.build();
		rspHeadBuilder.setMac(ProtocolUtil.getCRC32(rspBody.toByteArray()));
		
		doReply(socket, rspHeadBuilder.build(), rspBody);
	}
	
	private void doBusExceptionReply(Socket socket, Hpprot._req requestHead, String message){
		Hpprot._rsp.Builder rspHeadBuilder = Hpprot._rsp.newBuilder();
		rspHeadBuilder.setTrdcode(requestHead.getToken());
		rspHeadBuilder.setReqno(requestHead.getReqno());
		rspHeadBuilder.setRspsys(ProtocolUtil.getSystemNo());
		rspHeadBuilder.setRspnode(ProtocolUtil.getNodeNo());
		rspHeadBuilder.setRspstatus(com.qlcd.qltt.head.Hpem.PEH_RSPSTATUS.EV_FAILURE);
	
		BppSys._rsp_genfailhead.Builder  failHeadBuilder = BppSys._rsp_genfailhead.newBuilder();
		failHeadBuilder.setRspcode(1);
		failHeadBuilder.setRspmsg(message);	
		
		BppSys._rsp_genfailhead rspBody = failHeadBuilder.build();
		rspHeadBuilder.setMac(ProtocolUtil.getCRC32(rspBody.toByteArray()));
		
		doReply(socket, rspHeadBuilder.build(), rspBody);
		
	}
	
	private void doNormalReply(Socket socket, Hpprot._req requestHead, Object responseBody){
		
		Hpprot._rsp.Builder rspHeadBuilder = Hpprot._rsp.newBuilder();
		rspHeadBuilder.setTrdcode(requestHead.getToken());
		rspHeadBuilder.setReqno(requestHead.getReqno());
		rspHeadBuilder.setRspsys(ProtocolUtil.getSystemNo());
		rspHeadBuilder.setRspnode(ProtocolUtil.getNodeNo());
		rspHeadBuilder.setRspstatus(com.qlcd.qltt.head.Hpem.PEH_RSPSTATUS.EV_SUCCESS);
	
		byte[] rspBodyByte;
		Class<?> clazz;
		try {
			clazz = Class
					.forName(responseBody.getClass().getName());
			Method toByteArray = clazz.getMethod("toByteArray");
			rspBodyByte = (byte[]) toByteArray.invoke(responseBody);	
		} catch (Exception e) {
			org.jeecgframework.core.util.LogUtil.info("响应反序列化错误:"+e.getMessage());
			throw new CommZMQException("响应反序列化错误:"+e.getMessage());
		}
		rspHeadBuilder.setMac(ProtocolUtil.getCRC32(rspBodyByte));
		doReply(socket, rspHeadBuilder.build(), responseBody);
		
	}

	public static void main(String[] args) {

		/*
		ZMQToProxy zmqProxy = new ZMQToProxy();

		IBusProcess busProcess = new TestBusProcess();

		InvocationHandler handler = new ToZMQProxyHandler(busProcess);

		zmqProxy.InBound(handler, busProcess);
		*/

	}

}
