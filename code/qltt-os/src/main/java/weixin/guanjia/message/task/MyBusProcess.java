package weixin.guanjia.message.task;

import java.util.List;

import com.qlcd.qltt.body.prt.T03001001;
import com.qlcd.qltt.body.prt.T03001001._evacctpush;
import com.qlcd.util.IBusProcess;

import weixin.guanjia.message.model.SendMessageTep;

public class MyBusProcess implements IBusProcess {

	@Override
	public Object active(int trdcode,Object requestBody) {
		SendMessageTep tep = new SendMessageTep();
		T03001001._req content = (T03001001._req) requestBody;
		List<_evacctpush> e =content.getEaplistList();
		content.getEp();
		
		return null;
	}

}
