package weixin.guanjia.message.task;

import java.util.concurrent.PriorityBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qlcd.util.IBusProcess;
import com.qlcd.util.ZMQToProxy;

import weixin.util.WeiXinConstants;

@Service
public class InitQueue {
	
	private static final Logger logger = LoggerFactory.getLogger(InitQueue.class);
	
	private IBusProcess busProcess;

	private ZMQToProxy zmqToProxy;

	public static PriorityBlockingQueue q = new PriorityBlockingQueue();

	public InitQueue(IBusProcess busProcess,ZMQToProxy zmqToProxy, String zmqSwitch) throws Exception {

		if (WeiXinConstants.ZMQ_SWITCH_ON.equalsIgnoreCase(zmqSwitch)) {
			logger.debug("正在初始化推送消息队列……");
			this.busProcess = busProcess;
			this.zmqToProxy = zmqToProxy;
			
			for (int i = 0; i < 5; i++) {
				MyZmq myzmq = new MyZmq(busProcess, zmqToProxy);
				Thread th = new Thread(myzmq);
				th.start();
			}
		}
	}
}
