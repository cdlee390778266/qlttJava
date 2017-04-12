package weixin.guanjia.message.task;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.jeecgframework.core.util.ResourceUtil;

import com.qlcd.qltt.body.BppSys;
import com.qlcd.qltt.body.prt.T03001001;
import com.qlcd.qltt.body.prt.T03001001._evacctpush;
import com.qlcd.util.IBusProcess;

import weixin.guanjia.message.model.SendMessageTep;
import weixin.util.StringTypeUtil;

public class MyBusProcess implements IBusProcess {

	@SuppressWarnings("unchecked")
	@Override
	public Object active(int trdcode,Object requestBody) {
		//模板消息推送
//		if(trdcode == 3001001){
			T03001001._req content = (T03001001._req) requestBody;
			List<_evacctpush> e =content.getEaplistList();
			for (_evacctpush epush : e) {
				SendMessageTep tep = new SendMessageTep();
				tep.setId(StringTypeUtil.getUUid());
				tep.setCreatetime(new Timestamp(new Date().getTime()));
				if(epush.getSvcchnlValue() == 1){
					tep.setOpenId(epush.getDevno());
					tep.setSvcchnl(epush.getSvcchnlValue());
				}
				tep.setDevtype(epush.getDevtypeValue());
				tep.setTtacct(epush.getTtacct());
				tep.setContent(StringTypeUtil.stringToClob(epush.getContent()));
				tep.setWeight(content.getEp().getMxdlyval());
				InitQueue.q.add(tep);
			}
			//TODO:怎么拼
			T03001001._rsp.Builder rsph =T03001001._rsp.newBuilder();
			rsph.setRsh(BppSys._rsp_succhead.getDefaultInstance());
			T03001001._rsp rsp = rsph.build();
			return rsp;
//		}else{
//			
//			return null;
//		}
		
	}
	


}
