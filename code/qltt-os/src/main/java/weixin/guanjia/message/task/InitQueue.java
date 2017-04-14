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
									InvocationHandler handler = new ToZMQProxyHandler(busProcess);
									zmqToProxy.InBound(handler, busProcess);
									
//									SendMessageTep tep = new SendMessageTep();
//									tep.setId(StringTypeUtil.getUUid());
//									tep.setCreatetime(new Timestamp(new Date().getTime()));
//									
//									tep.setOpenId("onO6V0r6r3fYUrTDGpY5EwKS4AQo");
//									tep.setSvcchnl(1);
//									
//									tep.setDevtype(0);
//									tep.setTtacct("tto1");
//									tep.setContent(StringTypeUtil.stringToClob("你才"+1));
//									tep.setWeight(1);
//									InitQueue.q.add(tep);
									
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
