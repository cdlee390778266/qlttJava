import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;


public class ZMQServer {
	
	
	static final int MAXTHREADNUM = 10;

	public static void main(String[] args) {
		
		
		/*
		ZMQ.Context context = ZMQ.context(1);  //1表示IO线程数
        
		
        ZMQ.Socket socket = context.socket(ZMQ.REP);  
        socket.bind ("tcp://*:5555");    //绑定端口  
  
        while (!Thread.currentThread().isInterrupted()) {  
             
            byte[] request = socket.recv();  
            System.out.println("server receive : " + new String(request));  
 
            socket.send(request);  
        }  
        socket.close();    
        context.term();  
        */
        

		
		ZMQ.Context context = ZMQ.context(1);  //1表示IO线程数
        ZMQ.Socket socket = context.socket(ZMQ.REP);  
        socket.bind ("tcp://*:5555");    //绑定端口  

		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(MAXTHREADNUM);
		for (int i = 0; i < MAXTHREADNUM-1; i++) {
			fixedThreadPool.execute(new ZMQWorker(socket));
		}
		//socket.close();
		//context.term();  
		
		

    }  
}


class ZMQWorker implements Runnable {

	private Socket socket;

	public ZMQWorker(Socket socket) {
		this.socket = socket;
	}
	

	@Override
	public void run() {

		System.err.println("Starting Thread:"+Thread.currentThread().getName());  


		while (Thread.currentThread().isInterrupted()) {
			byte[] request = socket.recv();

			// TODO
			 System.err.println("Thread:"+Thread.currentThread().getName()+"server receive : " + new String(request));  

			byte[] response = request;
			socket.send(response);
		}
		

	}

}

