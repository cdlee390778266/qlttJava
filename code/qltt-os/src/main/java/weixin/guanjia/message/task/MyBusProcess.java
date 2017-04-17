package weixin.guanjia.message.task;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import com.qlcd.qltt.body.prt.T03001001;
import com.qlcd.qltt.body.prt.T03001001._evacctpush;
import com.qlcd.util.IBusProcess;

import weixin.guanjia.message.model.SendMessageTep;
import weixin.util.StringTypeUtil;

public class MyBusProcess implements IBusProcess {

	@SuppressWarnings("unchecked")
	@Override
	public Object active(int trdcode,Object requestBody) {
		int i = 0;
		try{
			//模板消息推送
			if(trdcode == 3001001){
				SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				T03001001._req content = (T03001001._req) requestBody;
				List<_evacctpush> e =content.getEaplistList();
				for (_evacctpush epush : e) {
					SendMessageTep tep = new SendMessageTep();
					tep.setCreatetime(new Timestamp(data.parse(content.getEp().getOcctime()).getTime()));
					tep.setOpenId(epush.getDevno());
					tep.setSvcchnl(epush.getSvcchnlValue());
					
					tep.setDevtype(epush.getDevtypeValue());
					tep.setTtacct(epush.getTtacct());
					tep.setContent(StringTypeUtil.stringToClob(epush.getContent()));
					tep.setWeight(content.getEp().getMxdlyval());
					InitQueue.q.add(tep);
				
				}
				com.qlcd.qltt.body.BppSys._rsp_succhead.Builder succheadbuilder = com.qlcd.qltt.body.BppSys._rsp_succhead.newBuilder();
				succheadbuilder.setRspcode(1);
				succheadbuilder.setRspmsg("OK");
				
				T03001001._rsp.Builder rsph =T03001001._rsp.newBuilder();
				rsph.setRsh(succheadbuilder.build());
				T03001001._rsp rsp = rsph.build();
				return rsp;
			}else{
				
				com.qlcd.qltt.body.BppSys._rsp_succhead.Builder succheadbuilder = com.qlcd.qltt.body.BppSys._rsp_succhead.newBuilder();
				succheadbuilder.setRspcode(0);
				succheadbuilder.setRspmsg("fail");
				
				T03001001._rsp.Builder rsph =T03001001._rsp.newBuilder();
				rsph.setRsh(succheadbuilder.build());
				T03001001._rsp rsp = rsph.build();
				return rsp;
			}
		}catch(Exception e){
			com.qlcd.qltt.body.BppSys._rsp_succhead.Builder succheadbuilder = com.qlcd.qltt.body.BppSys._rsp_succhead.newBuilder();
			succheadbuilder.setRspcode(0);
			succheadbuilder.setRspmsg("fail");
			
			T03001001._rsp.Builder rsph =T03001001._rsp.newBuilder();
			rsph.setRsh(succheadbuilder.build());
			T03001001._rsp rsp = rsph.build();
			return rsp;
		}	
	}
	


}
