package weixin.guanjia.message.task;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.qlcd.qltt.body.pvt.T03001001;
import com.qlcd.qltt.body.pvt.T03001001._evacctpush;
import com.qlcd.qltt.body.pvt.T03001001._eventpush;
import com.qlcd.qltt.body.pvt.T03001002;
import com.qlcd.util.IBusProcess;

import net.sf.json.JSONObject;
import weixin.guanjia.message.model.SendMessageTep;
import weixin.util.StringTypeUtil;

public class MyBusProcess implements IBusProcess {

	@SuppressWarnings("unchecked")
	@Override
	public Object active(int trdcode,Object requestBody) {
		SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			//模板消息推送
			if(trdcode == 3001001){
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
					tep.setKeyword1("尊敬的客户:"+epush.getCN());//keyword1
					tep.setKeyword2(eventpush.getTacname());//keyword2
					String remark = eventpush.getEvdetail()+epush.getSummary()+"(查看更多请点击详情,内容当日有效)";//remark
					//TODO:详情url
					String url ="";
					tep.setUrl(url);
					tep.setContent(StringTypeUtil.stringToClob(remark));//内容
					tep.setWeight(content.getEp().getMxdlyval());//权重
					InitQueue.q.add(tep);

				
				}
				com.qlcd.qltt.body.BppSys._rsp_succhead.Builder succheadbuilder = com.qlcd.qltt.body.BppSys._rsp_succhead.newBuilder();
				succheadbuilder.setRspcode(0);
				succheadbuilder.setRspmsg("成功");
				
				T03001001._rsp.Builder rsph =T03001001._rsp.newBuilder();
				rsph.setRsh(succheadbuilder.build());
				T03001001._rsp rsp = rsph.build();
				return rsp;
			}if(trdcode == 3001002){
				T03001002._rsp.Builder rsph02 = T03001002._rsp.newBuilder();
				T03001002._req content = (T03001002._req)requestBody;
				List<com.qlcd.qltt.body.pvt.T03001002._eventpush> event = content.getEpList();
				
				for (com.qlcd.qltt.body.pvt.T03001002._eventpush _eventpush : event) {
					T03001002._acptpush.Builder acp = T03001002._acptpush.newBuilder();
					SendMessageTep tep = new SendMessageTep();
					tep.setCreatetime(new Timestamp(data.parse(_eventpush.getOcctime()).getTime()));//创建时间
					tep.setOpenId(_eventpush.getDevno());//设备id
					tep.setSvcchnl(_eventpush.getSvcchnlValue());//渠道标识
					tep.setDevtype(_eventpush.getDevtypeValue());
					tep.setTtacct(_eventpush.getTtacct());//推推账号
					
					tep.setTitle1(_eventpush.getTitle1());//标题
					tep.setKeyword1("尊敬的客户:"+_eventpush.getCN());//keyword1
					tep.setKeyword2(_eventpush.getTacname());//keyword2
					String remark = _eventpush.getEvdetail()+_eventpush.getSummary()+"(查看更多请点击详情,内容当日有效)";//remark
					
					//TODO:详情url
					String url ="";
					tep.setUrl(url);
					
					tep.setContent(StringTypeUtil.stringToClob(remark));//内容
					tep.setWeight(_eventpush.getMxdlyval());//权重
					InitQueue.q.add(tep);
					acp.setPushindex(_eventpush.getPushindex());
					rsph02.addAcplist(acp);
				}
				
				com.qlcd.qltt.body.BppSys._rsp_succhead.Builder succheadbuilder = com.qlcd.qltt.body.BppSys._rsp_succhead.newBuilder();
				succheadbuilder.setRspcode(0);
				succheadbuilder.setRspmsg("成功");
				
				rsph02.setRsh(succheadbuilder);
				T03001002._rsp rsp =rsph02.build();
				return rsp;
			}
		}catch(Exception e){
			com.qlcd.qltt.body.BppSys._rsp_succhead.Builder succheadbuilder = com.qlcd.qltt.body.BppSys._rsp_succhead.newBuilder();
			succheadbuilder.setRspcode(1);
			succheadbuilder.setRspmsg("失败");
			if(trdcode == 3001001){
				T03001001._req content = (T03001001._req) requestBody;		
				org.jeecgframework.core.util.LogUtil.info("代理发送的异常数据为"+JSONObject.fromObject(content));
				T03001001._rsp.Builder rsph =T03001001._rsp.newBuilder();
				rsph.setRsh(succheadbuilder.build());
				T03001001._rsp rsp = rsph.build();
				return rsp;
			}else if(trdcode == 3001002){
				T03001002._rsp.Builder rsph02 = T03001002._rsp.newBuilder();
				rsph02.setRsh(succheadbuilder.build());
				T03001002._rsp rsp = rsph02.build();
				return rsp;
			}
			
		}
		return null;	
	}
}
