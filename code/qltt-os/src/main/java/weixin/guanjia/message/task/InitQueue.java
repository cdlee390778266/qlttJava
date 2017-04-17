package weixin.guanjia.message.task;

import java.util.concurrent.PriorityBlockingQueue;

import org.springframework.stereotype.Service;

import com.qlcd.util.IBusProcess;
import com.qlcd.util.ZMQToProxy;
@Service
public class InitQueue {
	
	private IBusProcess busProcess;

	private ZMQToProxy zmqToProxy;

	public static PriorityBlockingQueue q = new PriorityBlockingQueue();

	public InitQueue(IBusProcess busProcess,ZMQToProxy zmqToProxy) throws Exception {

		this.busProcess = busProcess;
		this.zmqToProxy = zmqToProxy;
		
		for (int i = 0; i < 5; i++) {
			MyZmq myzmq = new MyZmq(busProcess, zmqToProxy);
			Thread th = new Thread(myzmq);
			th.start();
		}
	}
}
