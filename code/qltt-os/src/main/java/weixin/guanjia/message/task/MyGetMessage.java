package weixin.guanjia.message.task;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeewx.api.core.req.model.message.TemplateData;
import org.jeewx.api.core.req.model.message.TemplateMessageSendResult;

import net.sf.json.JSONObject;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.account.service.impl.WeixinAccountServiceImpl;
import weixin.guanjia.message.entity.MessageTemplate;
import weixin.guanjia.message.entity.SendMessage;
import weixin.guanjia.message.model.SendMessageTep;
import weixin.util.MessageTemplateApiUtil;
import weixin.util.StringTypeUtil;

public class MyGetMessage  implements Runnable{

	private CommonService commonService;

	private WeixinAccountServiceI weixinAccountService;

	private static final ResourceBundle getData = java.util.ResourceBundle.getBundle("sysConfig");
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public   MyGetMessage(CommonService commonService,WeixinAccountServiceI weixinAccountService) {
		this.commonService = commonService;
		this.weixinAccountService = weixinAccountService;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(3000);
			while(true){
				if(!InitQueue.q.isEmpty()){
					//System.out.println("队列中数据个数:"+InitQueue.q.size()+" "+Thread.currentThread().getName()+"获取队列的数值"+InitQueue.q.take()+"移除后队列剩余的个数:"+InitQueue.q.size());
					SendMessageTep tep= (SendMessageTep)InitQueue.q.take();
					List<MessageTemplate> mList = commonService.findByProperty(MessageTemplate.class,"status",1);
					
					String msg ="";
					if(tep.getSvcchnl().intValue() ==1){
						//如果是微信渠道调用API接口发送
						TemplateMessageSendResult  result = new TemplateMessageSendResult();
						result.setTemplate_id(mList.get(0).getTemplateId());
						result.setTouser(tep.getOpenId());
						Map<String, TemplateData> data = new HashMap<>();
						TemplateData tdata = new TemplateData();
						tdata.setValue(df.format(tep.getCreatetime()));
						tdata.setColor("#173177");
						TemplateData tdata2 = new TemplateData();
						tdata2.setValue(StringTypeUtil.clobToString(tep.getContent()));
						tdata2.setColor("#173177");
						data.put("time", tdata);
						data.put("content", tdata2);
						result.setData(data);
						String token = weixinAccountService.getAccessToken(getData.getString("accountId"));
						msg =MessageTemplateApiUtil.sendTemplateMsg(token, result);
					}else{
						//手机APP渠道
					}
					
					//发送消息保存
					if(getData.getString("is_save_message").equals("0")){
						if(!msg.equals("")){
							JSONObject mb = JSONObject.fromObject(msg);
							if(!"".equals(mb.getString("msgid"))){
								SendMessage message = commonService.findUniqueByProperty(SendMessage.class,"msgid",mb.getString("msgid"));
								if(message == null){													
									message = new SendMessage();
									message.setContent(tep.getContent());
									message.setSvcchnl(tep.getSvcchnl());
									message.setDevtype(tep.getDevtype());
									message.setTtacct(tep.getTtacct());
									message.setCreatetime(tep.getCreatetime());
									message.setOpenId(tep.getOpenId());
									message.setTemplateId(mList.get(0).getTemplateId());
									message.setMsgid(mb.getString("msgid"));
									commonService.save(message);
								}else{
									message.setSvcchnl(tep.getSvcchnl());
									message.setTtacct(tep.getTtacct());
									message.setContent(tep.getContent());
									message.setCreatetime(tep.getCreatetime());
									message.setDevtype(tep.getDevtype());
									message.setOpenId(tep.getOpenId());
									message.setTemplateId(mList.get(0).getTemplateId());
									commonService.updateEntitie(message);
								}
								
							}
						}
						
					}
				}
				
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			
		}
	}
		
}
