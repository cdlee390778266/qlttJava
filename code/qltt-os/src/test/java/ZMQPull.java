import org.zeromq.ZMQ;


public class ZMQPull {
	
	public static void main(String[] args) throws InterruptedException {
		ZMQ.Context context = ZMQ.context(1);  
	    ZMQ.Socket receiver = context.socket(ZMQ.PULL);  
	    receiver.connect("tcp://192.168.16.118:6666");  
	    while (true) {  
	     
	        System.out.println(new String(receiver.recv(0)));  
	        Thread.sleep(10);
	    }  
	    
	    

	}

}
