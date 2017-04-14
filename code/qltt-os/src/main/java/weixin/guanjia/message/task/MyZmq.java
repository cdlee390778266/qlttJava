package weixin.guanjia.message.task;

import java.lang.reflect.InvocationHandler;

import com.qlcd.util.IBusProcess;
import com.qlcd.util.ToZMQProxyHandler;
import com.qlcd.util.ZMQToProxy;

public class MyZmq implements Runnable {
	
	IBusProcess busProcess;

	private ZMQToProxy zmqToProxy;
	
	public MyZmq(IBusProcess busProcess,ZMQToProxy zmqToProxy){
		this.busProcess = busProcess;
		this.zmqToProxy = zmqToProxy;
	}

	@Override
	public void run() {
		InvocationHandler handler = new ToZMQProxyHandler(busProcess);
		zmqToProxy.InBound(handler, busProcess);
		
//		SendMessageTep tep = new SendMessageTep();
//		tep.setId(StringTypeUtil.getUUid());
//		tep.setCreatetime(new Timestamp(new Date().getTime()));
//
//		tep.setOpenId("onO6V0r6r3fYUrTDGpY5EwKS4AQo");
//		tep.setSvcchnl(1);
//
//		tep.setDevtype(0);
//		tep.setTtacct("tto1");
//		tep.setContent(StringTypeUtil.stringToClob("你才" + 1));
//		tep.setWeight(1);
//		InitQueue.q.add(tep);

	}

	public IBusProcess getBusProcess() {
		return busProcess;
	}

	public void setBusProcess(IBusProcess busProcess) {
		this.busProcess = busProcess;
	}

	public ZMQToProxy getZmqToProxy() {
		return zmqToProxy;
	}

	public void setZmqToProxy(ZMQToProxy zmqToProxy) {
		this.zmqToProxy = zmqToProxy;
	}

}
