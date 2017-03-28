package weixin.guanjia.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import weixin.guanjia.user.entity.WeixinTag;
import weixin.guanjia.user.service.IUserTagService;
/**
 * 微信用户标签管理：		
 */
@Controller
@RequestMapping("/weixinUserTagController")
public class WeixinUserTagController extends BaseController {

	private static final Logger logger = Logger.getLogger(WeixinUserTagController.class);

	@Autowired
	private SystemService systemService;
	
	@Autowired
	private IUserTagService userTagService;
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 页面跳转-->用户标签列表
	 */
	@RequestMapping(params = "list")
	public ModelAndView weixinAccount(HttpServletRequest request) {
		return new ModelAndView("weixin/guanjia/user/userTaglist");
	}

	/**
	 * easyui AJAX请求数据
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */
	@RequestMapping(params = "datagrid")
	public void datagrid(WeixinTag weixinTag,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid){
		CriteriaQuery cq = new CriteriaQuery(WeixinTag.class,
				dataGrid);
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				weixinTag, request.getParameterMap());
		cq.eq("accountid", ResourceUtil.getWeiXinAccountId());
		try {
			// 自定义追加查询条件
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		userTagService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * Ajax：新增一个标签
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(WeixinTag weixinTag,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String message = userTagService.addWeixinTag(weixinTag);
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);
		j.setMsg(message);
		return j;
	}

	/**
	 * Ajax:删除一个标签
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(WeixinTag weixinTag,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String message = userTagService.delWeixinTag(weixinTag);
		j.setMsg(message);
		return j;
	}
	
	/**
	 * Ajax：编辑一个标签
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(WeixinTag weixinTag,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String message = userTagService.updateWeixinTag(weixinTag);
		j.setMsg(message);
		return j;
	}
}
