package com.qlcd.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;

import com.google.protobuf.InvalidProtocolBufferException;
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

	public Object OutBound(Object requestBody) {

		String proxyAddr = String.format("tcp://%s:%d", proxyIp, proxyPort);

		Socket requester = scontext.socket(ZMQ.REQ);
		requester.connect(proxyAddr);
		requester.setReceiveTimeOut(rcvTimeOut * 1000);
		requester.setSendTimeOut(sndTimeOut * 1000);
		byte[] reqHeadByte = null;
		byte[] reqBodyByte = null;
		byte[] rspHeadByte = null;
		byte[] rspBodyByte = null;
		Object responBody = null;

		// 生成请求头
		Hpprot._req.Builder reqHeadBuilder = Hpprot._req.newBuilder();
		// 设置请求参数?
		// reqHeadBuilder.setTrdcode();
		reqHeadBuilder.setBodyclass(requestBody.getClass().getName());
		// ... 其他参数填

		Hpprot._req reqHead = reqHeadBuilder.build();
		reqHeadByte = reqHead.toByteArray();

		Class<?> clazzs;
		try {
			clazzs = Class.forName(requestBody.getClass().getName());
			Method toByteArray = clazzs.getMethod("toByteArray", null);
			reqBodyByte = (byte[]) toByteArray.invoke(clazzs, reqBodyByte);

			requester.send(reqHeadByte, org.zeromq.ZMQ.SNDMORE);
			requester.send(reqBodyByte, 0);

			rspHeadByte = requester.recv(0);
			System.err.println("Received reply--   [" + new String(rspHeadByte)
					+ "]");
			int recvCnt = 0;
			while (requester.hasReceiveMore()) {
				rspBodyByte = requester.recv(0);
				System.err.println("Received reply   ["
						+ new String(rspBodyByte) + "]");
				recvCnt++;
			}
			if (recvCnt > 1) {
				// 接收次数异常时返回通用异常报文
				responBody = null;
			} else {

				Hpprot._rsp rspHead;
				rspHead = Hpprot._rsp.parseFrom(rspHeadByte);
				// 头部验证

				// 如果OK则根据请求头解析请求体
				Class<?> clazzr = Class.forName(rspHead.getBodyclass());
				Method parseFrom = clazzr.getMethod("parseFrom", byte[].class);
				responBody = parseFrom.invoke(clazzr, rspBodyByte);
			}

		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			requester.close();
		}
		return responBody;

	}

	public void InBound(InvocationHandler handler, IBusProcess busProcess) {

		IBusProcess busProcessProxy = (IBusProcess) Proxy.newProxyInstance(
				handler.getClass().getClassLoader(), busProcess.getClass()
						.getInterfaces(), handler);

		String proxyAddr = String.format("tcp://%s:%d", proxyIp, proxyPort);

		Socket responder = rcontext.socket(ZMQ.REP);
		responder.connect(proxyAddr);
//		responder.setReceiveTimeOut(rcvTimeOut * 1000);
//		responder.setSendTimeOut(sndTimeOut * 1000);
		byte[] reqHeadByte = null;
		byte[] reqBodyByte = null;
		byte[] rspHeadByte = null;
		byte[] rspBodyByte = null;

		Object responBody = null;
		Object requestBody = null;

		while (!Thread.currentThread().isInterrupted()) {
			try {
//				reqHeadByte = responder.recv(0);
				reqHeadByte = new byte[]{8, -94, 10, 16, -94, 10, 24, -94, 10, 32, -94, 10, 40, -94, 10, 48, -94, 10, 58, 37, 99, 111, 109, 46, 113, 108, 99, 100, 46, 113, 108, 116, 116, 46, 98, 111, 100, 121, 46, 112, 114, 116, 46, 84, 48, 51, 48, 48, 49, 48, 48, 49, 46, 95, 114, 101, 113};
				System.err.println(Thread.currentThread().getName()+"Receivedreply--   ["
						+ new String(reqHeadByte) + "]");
				int recvCnt = 0;
//				while (responder.hasReceiveMore()) {
//					reqBodyByte = responder.recv(0);
					reqBodyByte =new byte[]{10, 35, 10, 8, 50, 48, 49, 55, 48, 52, 48, 57, 16, 1, 24, 1, 34, 10, 50, 48, 49, 55, 45, 48, 52, 45, 48, 57, 40, 1, 50, 3, 48, 48, 49, 56, 1, 18, 25, 10, 4, 116, 116, 48, 49, 18, 6, -28, -67, -96, -25, -116, -100, 34, 9, 122, 109, 115, 116, 116, 48, 49, 119, 120, 18, 26, 10, 4, 116, 116, 48, 49, 18, 6, -28, -67, -96, -25, -116, -100, 34, 10, 122, 109, 115, 116, 116, 48, 49, 105, 111, 115, 18, 25, 10, 4, 116, 116, 48, 50, 18, 6, -28, -67, -96, -25, -116, -100, 34, 9, 122, 109, 115, 116, 116, 48, 49, 119, 120};
					System.err.println("Received reply   ["
							+ new String(reqBodyByte) + "]");
					recvCnt++;
//				}
				if (recvCnt > 1) {
					// 接收次数异常时返回通用异常报文
					responBody = null;
				} else {

					// 请求头解析
					Hpprot._req reqHead;

					reqHead = Hpprot._req.parseFrom(reqHeadByte);
					//解析体的对象
					String bodyClass = reqHead.getBodyclass().replace("._req", "$_req");

					// 根据请求头解析请求体
					Class<?> clazzr;

					clazzr = Class.forName(bodyClass);

					Method parseFrom = clazzr.getMethod("parseFrom",
							byte[].class);
					requestBody = parseFrom.invoke(clazzr, reqBodyByte);

					// 执行业务处理
					responBody = busProcessProxy.active(reqHead.getTrdcode(),requestBody);

				}
				// 生成响应头
				Hpprot._rsp.Builder rspHeadBuilder = Hpprot._rsp.newBuilder();
				// rspHeadBuilder.setTrdcode(reqHead.getTrdcode());

				Hpprot._rsp rspHead = rspHeadBuilder.build();
				rspHeadByte = rspHead.toByteArray();

				// 生成响应体?? 没有toByteArray
				Class<?> clazzs = Class
						.forName(responBody.getClass().getName());
				Method toByteArray = clazzs.getMethod("toByteArray", null);
				rspBodyByte = (byte[]) toByteArray.invoke(clazzs, reqBodyByte);

				// 发送响应
				responder.send(rspHeadByte, org.zeromq.ZMQ.SNDMORE);
				responder.send(rspBodyByte, 0);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidProtocolBufferException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// We never get here but clean up anyhow
		responder.close();
		rcontext.term();
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
