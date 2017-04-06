import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.zeromq.ZMQ;

import com.cdqianlong.qltt.GPBAcctCn;
import com.cdqianlong.qltt.Protocol201;
import com.cdqianlong.qltt.Protocol201.Data;
import com.cdqianlong.qltt.Protocol201.Data.Builder;

public class ZMQClient {

	static final int MAXTHREADNUM = 10;

	public static void main(String[] args) {

		/*
		ZMQ.Context context = ZMQ.context(1);
		 
		ZMQ.Socket socket = context.socket(ZMQ.REQ);
		socket.setSendTimeOut(3000);
		socket.setReceiveTimeOut(2000);

		int i = 0;
		while (true)
			try {

				System.err.println("Connecting to server..."
						+ Thread.currentThread().getId());
				socket.connect("tcp://192.168.16.40:5555");

				Builder builder = Protocol201.Data.newBuilder();
				builder.setData("四川省|" + Thread.currentThread().getId() + "|"
						+ i++);
				builder.setId(11);

				Data data = builder.build();
				byte[] buf = data.toByteArray();
				System.err.println(data.toString() + "|" + buf.length);

				socket.send(buf, 0);

				byte[] reply = socket.recv(0);
				System.err.println("Received reply   [" + new String(reply)
						+ "]");

				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			*/

		
		ZMQ.Context context = ZMQ.context(1);
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(MAXTHREADNUM);
		for (int i = 0; i < 10; i++) {
			fixedThreadPool.execute(new ExecuteThread(context));
		}
		
	}
}

class ExecuteThread implements Runnable {
	
	ZMQ.Context _context;
	
	public ExecuteThread(ZMQ.Context context){
		_context = context;
	}
	
	public void run() {
		
		ZMQ.Socket socket = _context.socket(ZMQ.REQ);
		socket.connect("tcp://192.168.16.40:5555");
		socket.setSendTimeOut(3000);
		socket.setReceiveTimeOut(2000);
		System.out.println("Connecting to server..."+ Thread.currentThread().getId());
		int i = 0;
		while (true){
			try {
				
				GPBAcctCn.acctcnreg_req.Builder reqbuilder = GPBAcctCn.acctcnreg_req.newBuilder();
				GPBAcctCn._acctbindinfo.Builder acctBuild = GPBAcctCn._acctbindinfo.newBuilder();
				acctBuild.setBindacct("测试");
				acctBuild.setST(1);
				acctBuild.setSvcchnl(2);
				acctBuild.setSvcno("11111111");
				
				reqbuilder.setAbiS(acctBuild.build());
				reqbuilder.setCN("111!!@@@");

				/*
				Builder builder = Protocol201.Data.newBuilder();
				builder.setData("四川省|" + Thread.currentThread().getId() + "|"
						+ i++);
				builder.setId(11);

				Data data = builder.build();
				byte[] buf = data.toByteArray();
				//System.err.println(data.toString() + "|" + buf.length);
				  
				 */
				socket.send(reqbuilder.build().toByteArray(), 0);
				

				byte[] reply = socket.recv(0);
				System.out.println("Received reply   [" + new String(reply)
						+ "]");

				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
