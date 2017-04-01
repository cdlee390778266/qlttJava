package weixin.guanjia.message.service.impl;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weixin.guanjia.message.service.MessageTemplateService;

@Service("messageTemplateService")
@Transactional
public class MessageTemplateServiceImpl extends CommonServiceImpl implements MessageTemplateService {

}
