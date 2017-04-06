package weixin.guanjia.message.task;

import java.util.Random;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;

public class Worker extends Thread {
	private Context context;
	private int workerNum;
	

	Worker(Context context, int worker) {
		this.context = context;
		this.workerNum = worker;
	}

	@Override
	public void run() {
		ZMQ.Socket socket = context.socket(ZMQ.REP);
		socket.connect("inproc://workers");

		while (true) {

			// Wait for next request from client (C string)
			String request = socket.recvStr(0);
//			System.out.println("当前线程名称:"+Thread.currentThread().getName() + " 接收到的数据为: [" + request + "]");
			System.out.println("消息队列中的消息为："+InitQueue.q);
			try {
				if(InitQueue.q.size()>7){
					System.out.println("取出的数据："+ InitQueue.q.take());
				}
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				
			}
			//将数据添加到数据队列中
			PriorityEntity en = new PriorityEntity(new Random().nextInt(100), request);
			InitQueue.q.add(en);
			
			socket.send("work" + this.workerNum + "回复的数据: " + "world", 0);
		}
	}
}

class PriorityEntity implements Comparable<PriorityEntity>{

	private static int count = 0;
	private int id = count++;
	private int priority;
	private String index;

	public PriorityEntity(int _priority, String _index) {
		this.priority = _priority;
		this.index = _index;
	}

	public String toString() {
		return id + "# [值=" + index + " 权重=" + priority + "]";
	}

	// 数字小，优先级高
	public int compareTo(PriorityEntity o) {
		return this.priority > o.priority ? 1 : this.priority < o.priority ? -1
				: 0;
	}

}
