package weixin.guanjia.message.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.jeewx.api.core.exception.WexinReqException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.message.entity.MessageTemplate;
import weixin.guanjia.message.model.SendMessageTep;
import weixin.guanjia.message.service.MessageTemplateService;
import weixin.guanjia.message.task.InitQueue;
import weixin.util.MessageTemplateApiUtil;
import weixin.util.StringTypeUtil;

/**
 * 自动回复关键字
 * 
 */
@Controller
@RequestMapping("/messagetemplate")
public class MessageTemplateController {

	@Autowired
	private MessageTemplateService messageTemplateService;
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private WeixinAccountServiceI weixinAccountService;
    
	/*
	 * 转向列表
	 */
	@RequestMapping(params = "list")
	public ModelAndView list() {
		return new ModelAndView("weixin/guanjia/message/messageTelist");
	}
 
	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(MessageTemplate messageTemplate,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(MessageTemplate.class,
				dataGrid);
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				messageTemplate, request.getParameterMap());
		cq.eq("status", 1);
		cq.add();
		this.messageTemplateService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除角色
	 * 
	 * @param ids
	 * @return
	 * @throws WexinReqException 
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson delRole(String id,HttpServletRequest request) throws WexinReqException {
		String token = weixinAccountService.getAccessToken();
		AjaxJson j = new AjaxJson();
		MessageTemplate tm = this.messageTemplateService.get(MessageTemplate.class, id);
		//向微信发送消息
		String msg = MessageTemplateApiUtil.delTemplate(token, id);
		//{"errmsg":"ok","errcode":0}
		JSONObject mb = JSONObject.fromObject(msg);
		if("0".equals(mb.getString("errcode"))){
			tm.setStatus(0);
			this.messageTemplateService.saveOrUpdate(tm);
			systemService.addLog("模板:"+tm.getTitle()+"删除成功", Globals.Log_Type_DEL,
					Globals.Log_Leavel_INFO);
			j.setMsg("模板:"+tm.getTitle()+"删除成功!");
		}else{
			j.setMsg("模板:"+tm.getTitle()+"删除失败!");
		}
		return j;
	}
	
	/**
	 * @throws IOException 
	 * @throws Exception 
	 * 查看详细信息
	* @Title: goUpdate 
	* @Description: TODO
	* @param @param weixinAccount
	* @param @param req
	* @param @return    
	* @return ModelAndView   
	* @throws
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(MessageTemplate messageTemplate,
			HttpServletRequest req) throws Exception {
		if (StringUtil.isNotEmpty(messageTemplate.getTemplateId())) {
			messageTemplate = this.messageTemplateService.getEntity(
					MessageTemplate.class, messageTemplate.getTemplateId());
			String detailContent = StringTypeUtil.clobToString(messageTemplate.getDetailContent());
			String exContent = StringTypeUtil.clobToString(messageTemplate.getExContent());
			req.setAttribute("message", messageTemplate);
			req.setAttribute("exContent", exContent);
			req.setAttribute("detailContent", detailContent);
		}
		return new ModelAndView("weixin/guanjia/message/messageTemInfo");
	}
	
	/**
	 * 同步消息模板
	 * 
	 * @param ids
	 * @return
	 * @throws WexinReqException 
	 */
	@RequestMapping(params = "async")
	@ResponseBody
	public String asyncDate() throws WexinReqException {
		String token = weixinAccountService.getAccessToken();
		String temp = MessageTemplateApiUtil.getTemplateList(token);
		JSONArray tempA = JSONArray.fromObject(temp);
		List<MessageTemplate> messnewList = new ArrayList<>();
		for (int i = 0; i < tempA.size(); i++) {
			MessageTemplate messnew = new MessageTemplate();
			JSONObject tcon = JSONObject.fromObject(tempA.get(i));
			messnew.setTemplateId(tcon.getString("template_id"));
			messnew.setTitle(tcon.getString("title"));
			messnew.setPrimaryIndustryName(tcon.getString("primary_industry"));
			messnew.setSecondaryIndustryName(tcon.getString("deputy_industry"));
			messnew.setDetailContent(StringTypeUtil.stringToClob(tcon.getString("content")));
			messnew.setCreatetime(new Timestamp(new Date().getTime()));
			messnew.setExContent(StringTypeUtil.stringToClob(tcon.getString("example")));
			messnew.setStatus(1);
			messnewList.add(messnew);
			
		}
		List<MessageTemplate> messageoldList = this.messageTemplateService.findByProperty(MessageTemplate.class,
				"status", 1);
		//添加新的消息模板
		List<String> mnewIdList = new ArrayList<>();
		List<String> moldIdList = new ArrayList<>();
		for (MessageTemplate m2 : messageoldList) {
			moldIdList.add(m2.getTemplateId());
		}
		for (MessageTemplate m1 : messnewList) {
			mnewIdList.add(m1.getTemplateId());
			if(!moldIdList.contains(m1.getTemplateId())){
				this.messageTemplateService.saveOrUpdate(m1);
			}
		}
		//删除已经不存在的消息模板
		for (MessageTemplate m2 : messageoldList) {
			if(!mnewIdList.contains(m2.getTemplateId())){
				m2.setStatus(0);
				this.messageTemplateService.saveOrUpdate(m2);
			}
		}
		return "0";
	}
	
	/**
	 * 测试发送消息
	* @Title: goUpdate 
	* @Description: TODO
	* @param @param weixinAccount
	* @param @param req
	* @param @return    
	* @return ModelAndView   
	* @throws
	 */
	@RequestMapping(params = "sendMessage")
	public ModelAndView sendMessage(MessageTemplate messageTemplate,
			HttpServletRequest req) throws Exception {
		if (StringUtil.isNotEmpty(messageTemplate.getTemplateId())) {
			req.setAttribute("message", messageTemplate);
		}
		return new ModelAndView("weixin/guanjia/message/sendmessage");
	}
	
	@RequestMapping(params = "send")
	@ResponseBody
	public AjaxJson send(String id,SendMessageTep tep,String content1,
			HttpServletRequest req) throws Exception {
		AjaxJson j = new AjaxJson();
		try{
			tep.setContent(StringTypeUtil.stringToClob(content1));
			System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(tep));
			InitQueue.q.add(tep);
			j.setSuccess(true);
			j.setMsg("发送消息成功!");
		}catch(Exception e){
			j.setSuccess(false);
			j.setMsg("发送消息失败!");
		}
		return j;
	}
}