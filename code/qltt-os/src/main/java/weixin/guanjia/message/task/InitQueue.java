package weixin.guanjia.message.task;

import java.util.concurrent.PriorityBlockingQueue;

import org.springframework.stereotype.Service;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

@Service
public class InitQueue {

	public static PriorityBlockingQueue q = new PriorityBlockingQueue();

	public InitQueue() throws Exception {

		Thread thr = new Thread(new Runnable() {

			@Override
			public void run() {

				try {

					Context context = ZMQ.context(1);
					Socket clients = context.socket(ZMQ.ROUTER);
					clients.bind("tcp://*:5559");

					Socket workers = context.socket(ZMQ.DEALER);
					workers.bind("inproc://workers");

					for (int thread_nbr = 0; thread_nbr < 5; thread_nbr++) {
						Thread worker = new Worker(context, thread_nbr);
						worker.start();
					}
					// Connect work threads to client threads via a queue
					ZMQ.proxy(clients, workers, null);

					// We never get here but clean up anyhow
					clients.close();
					workers.close();
					context.term();

				} catch (Exception e) {

				}
			}

		});
		thr.start();

	}
}
