import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class ZMQProxy {
	
	static final int MAXTHREADNUM = 10;

	public static void main(String[] args) {

		Context context = ZMQ.context(1);

		Socket clients = context.socket(ZMQ.ROUTER);
		clients.bind("tcp://*:5555");

		Socket workers = context.socket(ZMQ.DEALER);
		workers.bind("inproc://workers");

		for (int thread_nbr = 0; thread_nbr < MAXTHREADNUM; thread_nbr++) {
			Thread worker = new ProxyWorker(context);
			worker.start();
		}
		// Connect work threads to client threads via a queue
		ZMQ.proxy(clients, workers, null);

		// We never get here but clean up anyhow
		clients.close();
		workers.close();
		context.term();
	}

}

class ProxyWorker extends Thread {

	private Context context;

	ProxyWorker(Context context) {
		this.context = context;
	}

	@Override
	public void run() {
		ZMQ.Socket socket = context.socket(ZMQ.REP);
		socket.connect("inproc://workers");
		
		System.err.println("Starting Thread:"+Thread.currentThread().getName());  

		while (true) {

			// Wait for next request from client (C string)
			byte[] bytes  = socket.recv(0);
			
			if(bytes != null && bytes.length > 0){
				System.err.println("Server Thread:"+Thread.currentThread().getName()+"server receive : " + new String(bytes));  

				// Do some 'work'
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
				}
	
				// Send reply back to client (C string)
				
				socket.send(bytes, 0);
			}
		}
	}
}
