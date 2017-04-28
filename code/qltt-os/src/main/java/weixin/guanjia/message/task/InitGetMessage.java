package weixin.guanjia.message.task;

import org.jeecgframework.core.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;

import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.message.service.MessageTemplateService;

public class InitGetMessage{

	private CommonService commonService;
	private WeixinAccountServiceI weixinAccountService;
	private MessageTemplateService messageTemplateService;

	public InitGetMessage(CommonService commonService,WeixinAccountServiceI weixinAccountService,MessageTemplateService messageTemplateService) throws Exception {
		this.commonService = commonService;
		this.weixinAccountService = weixinAccountService;
		for (int i = 0; i < 5; i++) {
			MyGetMessage gm = new MyGetMessage(commonService, weixinAccountService,messageTemplateService);
			Thread th = new Thread(gm);
			th.start();
		}
	}
}

