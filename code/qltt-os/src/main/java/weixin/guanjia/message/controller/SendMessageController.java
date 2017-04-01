package weixin.guanjia.message.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.message.entity.SendMessage;
import weixin.guanjia.message.service.MessageTemplateService;

/**
 * 自动回复关键字
 * 
 */
@Controller
@RequestMapping("/sendmessage")
public class SendMessageController {

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
		return new ModelAndView("weixin/guanjia/message/sendMessagelist");
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
	public void datagrid(SendMessage messageTemplate,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(SendMessage.class,
				dataGrid);
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				messageTemplate, request.getParameterMap());
		cq.add();
		this.messageTemplateService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
}