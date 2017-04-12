package weixin.guanjia.message.task;

import java.lang.reflect.InvocationHandler;
import java.util.concurrent.PriorityBlockingQueue;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import com.qlcd.util.IBusProcess;
import com.qlcd.util.ToZMQProxyHandler;
import com.qlcd.util.ZMQToProxy;

@Service
public class InitQueue {

	public static PriorityBlockingQueue q = new PriorityBlockingQueue();
	
	@Autowired
	IBusProcess busProcess;
	
	@Autowired
	private ZMQToProxy zmqToProxy;
	
	@Resource(name = "ataskExecutor")
	private TaskExecutor ataskExecutor;

	public InitQueue() throws Exception {
		Thread thr = new Thread(new Runnable() {
			@Override
			public void run() {
				
				try {
					Thread.sleep(3000);
					while(true){
						ataskExecutor.execute(new Runnable() {
							public void run() {
								try {
//									InvocationHandler handler = new ToZMQProxyHandler(busProcess);
//									zmqToProxy.InBound(handler, busProcess);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
						Thread.sleep(1000);
					}
				} catch (Exception e) {
					
				}
			}
		});
		thr.start();

	}
}
