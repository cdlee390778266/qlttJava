package weixin.guanjia.message.task;

import org.jeecgframework.core.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;

import weixin.guanjia.account.service.WeixinAccountServiceI;

public class InitGetMessage{

	private CommonService commonService;
	private WeixinAccountServiceI weixinAccountService;

	public InitGetMessage(CommonService commonService,WeixinAccountServiceI weixinAccountService) throws Exception {
		this.commonService = commonService;
		this.weixinAccountService = weixinAccountService;
		for (int i = 0; i < 5; i++) {
			MyGetMessage gm = new MyGetMessage(commonService, weixinAccountService);
			Thread th = new Thread(gm);
			th.start();
		}
	}
}

