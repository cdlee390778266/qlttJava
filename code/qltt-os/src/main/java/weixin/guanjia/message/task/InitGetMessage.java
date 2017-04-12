package weixin.guanjia.message.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.Resource;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeewx.api.core.req.model.message.TemplateData;
import org.jeewx.api.core.req.model.message.TemplateMessageSendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import freemarker.template.utility.StringUtil;
import net.sf.json.JSONObject;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.message.entity.MessageTemplate;
import weixin.guanjia.message.entity.SendMessage;
import weixin.guanjia.message.model.SendMessageTep;
import weixin.util.MessageTemplateApiUtil;
import weixin.util.StringTypeUtil;

@Service
@Transactional
public class InitGetMessage extends CommonServiceImpl{
	
	@Resource(name = "taskExecutor")
	private TaskExecutor taskExecutor;
	
	@Autowired
	private WeixinAccountServiceI weixinAccountService;
	
	private static final ResourceBundle getData = java.util.ResourceBundle.getBundle("sysConfig");
	
	public InitGetMessage() throws Exception {
		Thread thr = new Thread(new Runnable() {
			@Override
			public void run() {
				
				try {
					Thread.sleep(3000);
					while(true){
						if(!InitQueue.q.isEmpty()){
							taskExecutor.execute(new Runnable() {
								public void run() {
									
									try {
										//System.out.println("队列中数据个数:"+InitQueue.q.size()+" "+Thread.currentThread().getName()+"获取队列的数值"+InitQueue.q.take()+"移除后队列剩余的个数:"+InitQueue.q.size());
										SendMessageTep tep= (SendMessageTep)InitQueue.q.take();
										List<MessageTemplate> mList = getList(MessageTemplate.class);
										
										
										String msg ="";
										if(tep.getSvcchnl().intValue() ==1){
											//如果是微信渠道调用API接口发送
											TemplateMessageSendResult  result = new TemplateMessageSendResult();
											result.setTemplate_id(mList.get(0).getTemplateId());
											result.setTouser(tep.getOpenId());
											Map<String, TemplateData> data = new HashMap<>();
											TemplateData tdata = new TemplateData();
											tdata.setValue(tep.getOpenId());
											tdata.setColor("#173177");
											TemplateData tdata2 = new TemplateData();
											tdata2.setValue(StringTypeUtil.clobToString(tep.getContent()));
											tdata2.setColor("#173177");
											data.put("result", tdata);
											data.put("money", tdata2);
											result.setData(data);
											String token = weixinAccountService.getAccessToken(getData.getString("accountId"));
											msg =MessageTemplateApiUtil.sendTemplateMsg(token, result);
										}else{
											//手机APP渠道
										}
										
										//发送消息保存
										if(getData.getString("is_save_message").equals("0")){
											JSONObject mb = JSONObject.fromObject(msg);
											SendMessage message = new SendMessage();
											message.setContent(tep.getContent());
											message.setCreatetime(tep.getCreatetime());
											message.setDevtype(tep.getDevtype());
											message.setId(tep.getId());
											message.setOpenId(tep.getOpenId());
											message.setTemplateId(mList.get(0).getTemplateId());
											if("0".equals(mb.getString("errcode"))){
												message.setSendStatus(1);
												save(message);
											}else{
												message.setSendStatus(0);
												save(message);
											}
										}
										
									} catch (Exception e) {
										
										e.printStackTrace();
									}
								}
							});
						}else{
							
						}
						Thread.sleep(1000);
					}
				} catch (Exception e) {
					
				}
			}

		});
		thr.start();
	}
		
}

