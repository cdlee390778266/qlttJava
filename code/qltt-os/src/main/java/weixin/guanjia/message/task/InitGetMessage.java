package weixin.guanjia.message.task;

import javax.annotation.Resource;

import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class InitGetMessage {
	
	@Resource(name = "taskExecutor")
	private TaskExecutor taskExecutor;
	
	public InitGetMessage() throws Exception {
		Thread thr = new Thread(new Runnable() {
			@Override
			public void run() {
				
				try {
					Thread.sleep(3000);
					while(true){
						if(!InitQueue.q.isEmpty()){
							taskExecutor.execute(new Runnable() {
								public void run() {
									
									try {
										System.out.println("队列中数据个数:"+InitQueue.q.size()+" "+Thread.currentThread().getName()+"获取队列的数值"+InitQueue.q.take()+"移除后队列剩余的个数:"+InitQueue.q.size());
									} catch (InterruptedException e) {
										
										e.printStackTrace();
									}
								}
							});
						}else{
							
						}
						Thread.sleep(1000);
					}
				} catch (Exception e) {
					
				}
			}

		});
		thr.start();
	}
		
}

