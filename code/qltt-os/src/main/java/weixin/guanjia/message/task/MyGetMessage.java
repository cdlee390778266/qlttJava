package weixin.guanjia.message.task;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.jeecgframework.core.common.service.CommonService;
import org.jeewx.api.core.req.model.message.TemplateData;
import org.jeewx.api.core.req.model.message.TemplateMessageSendResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.message.entity.MessageTemplate;
import weixin.guanjia.message.entity.SendMessage;
import weixin.guanjia.message.model.SendMessageTep;
import weixin.guanjia.message.service.MessageTemplateService;
import weixin.util.MessageTemplateApiUtil;
import weixin.util.StringTypeUtil;

public class MyGetMessage implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(MyGetMessage.class);

	private CommonService commonService;	

	private WeixinAccountServiceI weixinAccountService;
	
	private MessageTemplateService messageTemplateService;

	private ResourceBundle zmqconfigResBundle = java.util.ResourceBundle.getBundle("zmqconfig");
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public MyGetMessage(CommonService commonService, WeixinAccountServiceI weixinAccountService,MessageTemplateService messageTemplateService) {
		this.commonService = commonService;
		this.weixinAccountService = weixinAccountService;
		this.messageTemplateService = messageTemplateService;
	}

	@Override
	public void run() {
		SendMessageTep tep ;
		while (true) {
			try {					
				tep = (SendMessageTep) InitQueue.q.take();
				
				logger.debug("MyGetMessage.run");
				if (tep.getSvcchnl().intValue() == 1) {
					handleWeiXinSend(tep);
				} else {
					// TODO 手机APP渠道
				}
				
			} catch (InterruptedException e1) {
				e1.printStackTrace();
				logger.error(e1.getMessage());
			}
		}
	}

	//处理微信消息
	@SuppressWarnings("unchecked")
	private void handleWeiXinSend(SendMessageTep tep) {
		try {
			List<MessageTemplate> mList = commonService.findByProperty(MessageTemplate.class, "status", 1);
			String templateId = mList.get(0).getTemplateId();
			// 如果是微信渠道调用API接口发送
			Map<String, TemplateData> data = new HashMap<>();
			TemplateMessageSendResult result = new TemplateMessageSendResult();
			result.setTemplate_id(templateId);
			result.setTouser(tep.getOpenId());
			result.setData(data);
			result.setUrl(tep.getUrl());
			// first
			TemplateData first = new TemplateData();
			first.setValue(tep.getTitle1());
			first.setColor("#173177");
			data.put("first", first);
	
			// keyword1
			TemplateData keyword1 = new TemplateData();
			keyword1.setValue(tep.getKeyword1());
			keyword1.setColor("#173177");
			data.put("keyword1", keyword1);
	
			// keyword2
			TemplateData keyword2 = new TemplateData();
			keyword2.setValue(tep.getKeyword2());
			keyword2.setColor("#173177");
			data.put("keyword2", keyword2);
	
			// remark
			TemplateData remark = new TemplateData();
			remark.setValue(StringTypeUtil.clobToString(tep.getContent()));
			remark.setColor("#173177");
			data.put("remark", remark);
	
			String msg;
		
			String token = weixinAccountService.getAccessToken(zmqconfigResBundle.getString("zmq.push.wechataccountid"));
			msg = MessageTemplateApiUtil.sendTemplateMsg(token, result);
			// 发送消息保存
			if (zmqconfigResBundle.getString("zmq.push.save").equals("0")) {
				if (!msg.equals("")) {
					JSONObject mb = JSONObject.fromObject(msg);
					String msgid = mb.getString("msgid");
					saveSendMessage(tep,msgid, templateId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			//发送失败了是否将该条信息从新塞回队列发送
			//InitQueue.q.add(tep);
		}
	}
	
	private void saveSendMessage(SendMessageTep tep,String msgid,String templateId){
		SendMessage message = new SendMessage();
		message.setContent(tep.getContent());
		message.setSvcchnl(tep.getSvcchnl());
		message.setDevtype(tep.getDevtype());
		message.setTtacct(tep.getTtacct());
		message.setCreatetime(tep.getCreatetime());
		message.setOpenId(tep.getOpenId());
		message.setTemplateId(templateId);
		message.setMsgid(msgid);
		messageTemplateService.handle(message);
	}
}
