package weixin.guanjia.message.task;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import com.qlcd.qltt.body.pvt.T03001001;
import com.qlcd.qltt.body.pvt.T03001001._evacctpush;
import com.qlcd.qltt.body.pvt.T03001001._eventpush;
import com.qlcd.util.IBusProcess;

import net.sf.json.JSONObject;
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
				_eventpush eventpush = content.getEp();
				List<_evacctpush> e =content.getEaplistList();
				for (_evacctpush epush : e) {
					SendMessageTep tep = new SendMessageTep();
					tep.setCreatetime(new Timestamp(data.parse(content.getEp().getOcctime()).getTime()));//创建时间
					tep.setOpenId(epush.getDevno());//设备id
					tep.setSvcchnl(epush.getSvcchnlValue());//渠道标识
					tep.setDevtype(epush.getDevtypeValue());
					tep.setTtacct(epush.getTtacct());//推推账号
					
					tep.setTitle1(eventpush.getTitle1());//标题
					tep.setKeyword1("尊敬的客户"+epush.getCN());//keyword1
					tep.setKeyword2(eventpush.getTacname());//keyword2
					String remark = eventpush.getEvdetail()+epush.getContent();//remark
					
					tep.setContent(StringTypeUtil.stringToClob(remark));//内容
					tep.setWeight(content.getEp().getMxdlyval());//权重
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
			T03001001._req content = (T03001001._req) requestBody;
			org.jeecgframework.core.util.LogUtil.info("代理发送的异常数据为"+JSONObject.fromObject(content));
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
