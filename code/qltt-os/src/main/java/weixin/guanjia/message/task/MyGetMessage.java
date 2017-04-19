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
import weixin.util.MessageTemplateApiUtil;
import weixin.util.StringTypeUtil;

public class MyGetMessage implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(MyGetMessage.class);

	private CommonService commonService;

	private WeixinAccountServiceI weixinAccountService;

	private static final ResourceBundle getData = java.util.ResourceBundle.getBundle("sysConfig");
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public MyGetMessage(CommonService commonService, WeixinAccountServiceI weixinAccountService) {
		this.commonService = commonService;
		this.weixinAccountService = weixinAccountService;
	}

	@Override
	public void run() {
		while (true) {
			if (!InitQueue.q.isEmpty()) {
				// System.out.println("队列中数据个数:"+InitQueue.q.size()+"
				// "+Thread.currentThread().getName()+"获取队列的数值"+InitQueue.q.take()+"移除后队列剩余的个数:"+InitQueue.q.size());
				try {
					SendMessageTep tep = (SendMessageTep) InitQueue.q.take();
					if (tep.getSvcchnl().intValue() == 1) {
						handleWeiXinSend(tep);
					} else {
						// TODO 手机APP渠道
					}
					Thread.sleep(1000);//take()函数，队列中没有数据，则线程wait释放CPU，这个是否有必要？
				} catch (InterruptedException e1) {
					e1.printStackTrace();
					logger.error(e1.getMessage());
				}
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
		
			String token = weixinAccountService.getAccessToken(getData.getString("accountId"));
			msg = MessageTemplateApiUtil.sendTemplateMsg(token, result);
			// 发送消息保存
			if (getData.getString("is_save_message").equals("0")) {
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
		if (!"".equals(msgid)) {
			SendMessage message = commonService.findUniqueByProperty(SendMessage.class, "msgid",msgid);
			if (message == null) {
				message = new SendMessage();
				message.setContent(tep.getContent());
				message.setSvcchnl(tep.getSvcchnl());
				message.setDevtype(tep.getDevtype());
				message.setTtacct(tep.getTtacct());
				message.setCreatetime(tep.getCreatetime());
				message.setOpenId(tep.getOpenId());
				message.setTemplateId(templateId);
				message.setMsgid(msgid);
				commonService.save(message);
			} else {
				message.setSvcchnl(tep.getSvcchnl());
				message.setTtacct(tep.getTtacct());
				message.setContent(tep.getContent());
				message.setCreatetime(tep.getCreatetime());
				message.setDevtype(tep.getDevtype());
				message.setOpenId(tep.getOpenId());
				message.setTemplateId(templateId);
				commonService.updateEntitie(message);
			}
		}
	}
}
