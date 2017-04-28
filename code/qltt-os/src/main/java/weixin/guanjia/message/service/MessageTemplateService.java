package weixin.guanjia.message.service;

import org.jeecgframework.core.common.service.CommonService;

import weixin.guanjia.message.entity.SendMessage;


public interface MessageTemplateService extends CommonService{

	public void handle (SendMessage message);
}
