package weixin.guanjia.message.service.impl;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weixin.guanjia.message.entity.SendMessage;
import weixin.guanjia.message.service.MessageTemplateService;

@Service("messageTemplateService")
@Transactional
public class MessageTemplateServiceImpl extends CommonServiceImpl implements MessageTemplateService {

	@Override
	public void handle(SendMessage message) {
		SendMessage me = this.findUniqueByProperty(SendMessage.class,"msgid",message.getMsgid());
		if(me == null){
			this.save(message);
		}else{
			if(!"".equals(me.getSendStatus()) || me.getSendStatus() != null){
				message.setSendStatus(me.getSendStatus());
			}
			this.updateEntitie(message);
		}
		
		
	}

}
