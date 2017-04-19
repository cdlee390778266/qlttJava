package weixin.guanjia.message.task;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jeewx.api.core.exception.WexinReqException;
import org.jeewx.api.core.req.model.message.TemplateData;
import org.jeewx.api.core.req.model.message.TemplateMessageSendResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import weixin.guanjia.message.model.SendMessageTep;
import weixin.util.MessageTemplateApiUtil;
import weixin.util.StringTypeUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:**.xml")
public class WeixinSendMessageTest{
	    
	 @Test
	 public void sendMessage() throws WexinReqException{
		 SendMessageTep tep = new SendMessageTep();
		 tep.setCreatetime(new Timestamp(new Date().getTime()));//创建时间
		 tep.setOpenId("o99gXwICsXEr-KGR3xhKs_wcaobA");//设备id
		 tep.setSvcchnl(1);//渠道标识
		 tep.setDevtype(1);
		 tep.setTtacct("qltt_0001");//推推账号
			
		 tep.setTitle1("测试标题");//标题
		 tep.setKeyword1("尊敬的客户"+"13730619242");//keyword1
		 tep.setKeyword2("订阅指标-XX提醒");//keyword2
		 tep.setContent(StringTypeUtil.stringToClob("测试一条信息"));//内容
		 
		 if(tep.getSvcchnl().intValue() ==1){
				//如果是微信渠道调用API接口发送
				Map<String, TemplateData> data = new HashMap<>();
				TemplateMessageSendResult  result = new TemplateMessageSendResult();
				result.setTemplate_id("NF9JbCSlpmHmCSBtt0flQQ3CpHydHHftwXArrB939ko");
				result.setTouser(tep.getOpenId());
				result.setData(data);
				
//				TemplateData tdata = new TemplateData();
//				tdata.setValue(df.format(tep.getCreatetime()));
//				tdata.setColor("#173177");
//				
//				TemplateData tdata2 = new TemplateData();
//				tdata2.setValue(StringTypeUtil.clobToString(tep.getContent()));
//				tdata2.setColor("#173177");
				
				//first
				TemplateData first = new TemplateData();
				first.setValue(tep.getTitle1());
				first.setColor("#173177");
				data.put("first", first);
				
				//keyword1
				TemplateData keyword1 = new TemplateData();
				keyword1.setValue(tep.getKeyword1());
				keyword1.setColor("#173177");
				data.put("keyword1", keyword1);
				
				//keyword2
				TemplateData keyword2 = new TemplateData();
				keyword2.setValue(tep.getKeyword2());
				keyword2.setColor("#173177");
				data.put("keyword2", keyword2);
				
				//remark
				TemplateData remark = new TemplateData();
				remark.setValue(StringTypeUtil.clobToString(tep.getContent()));
				remark.setColor("#173177");
				data.put("remark", remark);
				
				String msg = MessageTemplateApiUtil.sendTemplateMsg("yH_YhpwOzwCQFRW8PMKOvrqMOODclfmo2c-u17Kl90f7a76O67FEQAbIKkFOJXz-kQdDuUx6FwFJVuaGuoroNAnIv8WeAtZsLeraTT-_nWe0W8XgyLY6yQKDkmrh_nbzUMIiAIADVR", result);
				System.out.println(msg);
		 }
	 }
}
