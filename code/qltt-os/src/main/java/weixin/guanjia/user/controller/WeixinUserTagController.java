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
import weixin.guanjia.user.json.ErrorJson;
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
	public ModelAndView weixinUserTag(HttpServletRequest request) {
		logger.debug("进入标签列表界面----------------------------");
		return new ModelAndView("weixin/guanjia/user/userTaglist");
	}
	
	/**
	 * 页面跳转-->打开新增页面
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd() {
		return new ModelAndView("weixin/guanjia/user/usertag-add");
	}
	
	/**
	 * 页面跳转-->打开编辑页面
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(WeixinTag weixinTag,
			HttpServletRequest req){
		weixinTag.setAccountid(ResourceUtil.getWeiXinAccountId());
		if(weixinTag.getId() != null) {
			weixinTag = userTagService.getEntity(WeixinTag.class,weixinTag);
			req.setAttribute("usertag", weixinTag);
		}
		return new ModelAndView("weixin/guanjia/user/usertag-update");
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
		cq.eq("status", 1);
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

	private ErrorJson doDel(WeixinTag weixinTag) {
		ErrorJson rsp = userTagService.delWeixinTag(weixinTag);
		systemService.addLog(rsp.getErrmsg(), Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);
		return rsp;
	}
	
	/**
	 * 批量删除标签
	 */
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "删除信息数据成功";
		int succeed = 0;
		int error = 0;
		try {
			WeixinTag weixinTag = new WeixinTag();
			weixinTag.setAccountid(ResourceUtil.getWeiXinAccountId());
			for (String id : ids.split(",")) {
				if(id!=null && id.trim().length()!=0){
					weixinTag.setId(Integer.parseInt(id.trim()));
					ErrorJson rsp = doDel(weixinTag);
					if(rsp.getErrcode()==0)
						succeed += 1;
					else
						error += 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			error += 1;
			message = "删除信息数据失败";
			throw new BusinessException(e.getMessage());
		}
		message="删除信息数据成功"+succeed+"条，失败"+error+"条";
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
		weixinTag.setAccountid(ResourceUtil.getWeiXinAccountId());//设置微信账号
		String message = userTagService.updateWeixinTag(weixinTag);
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);
		j.setMsg(message);
		return j;
	}
	

	/**
	 * Ajax：将微信上的标签和本地数据库同步
	 */
	@RequestMapping(params = "doSame")
	@ResponseBody
	public AjaxJson doSame(WeixinTag weixinTag) {
		AjaxJson j = new AjaxJson();
		weixinTag.setAccountid(ResourceUtil.getWeiXinAccountId());//设置微信账号
		String message = userTagService.doSameWeixinTag(weixinTag);
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);
		j.setMsg(message);
		return j;
	}
}
