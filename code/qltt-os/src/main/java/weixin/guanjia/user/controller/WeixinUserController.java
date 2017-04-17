package weixin.guanjia.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import weixin.guanjia.user.entity.WeixinUser;
import weixin.guanjia.user.service.IUserService;
/**
 * 微信用户管理：	
 */
@Controller
@RequestMapping("/weixinUserController")
public class WeixinUserController extends BaseController {

	private static final Logger logger = Logger.getLogger(WeixinUserController.class);

	@Autowired
	private SystemService systemService;
	
	@Autowired
	private IUserService userService;
	
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
	public ModelAndView weixinUser(HttpServletRequest request){
		logger.debug("进入用户页面----------------------------------");
		return new ModelAndView("weixin/guanjia/user/userlist");
	}
	
	/**
	 * 页面跳转-->更改用户备注
	 */
	@RequestMapping(params = "goModifyRemark")
	public ModelAndView goModifyRemark(WeixinUser user,HttpServletRequest request) {
		user.setAccountid( ResourceUtil.getWeiXinAccountId());
		user = userService.getEntity(WeixinUser.class,user);
		request.setAttribute("user", user);
		return new ModelAndView("weixin/guanjia/user/modifyRemark");
	}

	/**
	 * easyui AJAX请求数据
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */
	@RequestMapping(params = "datagrid")
	public void datagrid(WeixinUser weixinUser,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid){
		CriteriaQuery cq = new CriteriaQuery(WeixinUser.class,
				dataGrid);
		// 查询条件组装器
		weixinUser.setAccountid(ResourceUtil.getWeiXinAccountId());
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				weixinUser, request.getParameterMap());
		cq.eq("accountid", ResourceUtil.getWeiXinAccountId());
		cq.add();
		userService.getDataGridReturn(cq, true);
		List users = dataGrid.getResults();
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * Ajax:用户同步
	 */
	@RequestMapping(params = "doModifyRemark")
	@ResponseBody
	public AjaxJson doSame(WeixinUser user) {
		AjaxJson j = new AjaxJson();
		String message = userService.doModifyRemark(user);
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);
		j.setMsg(message);
		return j;
	}
	

	/**
	 * Ajax:用户同步
	 */
	@RequestMapping(params = "doSameUser")
	@ResponseBody
	public AjaxJson doSameUser() {
		AjaxJson j = new AjaxJson();
		String accountid = ResourceUtil.getWeiXinAccountId();//设置微信账号
		String message = userService.doSameWeixinUser(accountid);
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);
		j.setMsg(message);
		return j;
	}
	
	/**
	 * Ajax:用户同步
	 */
	@RequestMapping(params = "doSameBlack")
	@ResponseBody
	public AjaxJson doSameBlack(){
		AjaxJson j = new AjaxJson();
		String accountid = ResourceUtil.getWeiXinAccountId();//设置微信账号
		String message = userService.doSameBlack(accountid);
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);
		j.setMsg(message);
		return j;
	}

	/**
	 * Ajax:移除黑名单
	 */
	@RequestMapping(params = "blackList")
	@ResponseBody
	public AjaxJson batchUnBlackList(@RequestParam(value="openids[]") String[] openids,Integer black){
		AjaxJson j = new AjaxJson();
		String accountid = ResourceUtil.getWeiXinAccountId();//设置微信账号
		String message = userService.blackList(accountid,openids,black);
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);
		j.setMsg(message);
		return j;
	}	
}
