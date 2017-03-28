package weixin.guanjia.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.jeewx.api.wxuser.user.model.Wxuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.user.service.IUserService;
/**
 * 
 * @ClassName:     WeixinUserController.java 
 * @Description:   微信用户管理：	
 * @author         sunchao/Email:sunchao@qianlong2.net
 * @version        V1.0   
 * @Date           2017年3月20日 下午4:10:02
 */
@Controller
@RequestMapping("/weixinUserController")
public class WeixinUserController extends BaseController {

	private static final Logger logger = Logger.getLogger(WeixinUserController.class);

	@Autowired
	private SystemService systemService;
	
	//@Autowired
	private IUserService weixinUserService;
	
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
	@RequestMapping(params = "users")
	public ModelAndView weixinAccount(HttpServletRequest request){
		return new ModelAndView("weixin/guanjia/user/users");
	}

	/**
	 * easyui AJAX请求数据
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */
	@RequestMapping(params = "datagrid")
	public void datagrid(Wxuser wxuser,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid){
		CriteriaQuery cq = new CriteriaQuery(Wxuser.class,
				dataGrid);
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				wxuser, request.getParameterMap());
		cq.eq("userName", ResourceUtil.getSessionUserName().getUserName());
		try {
			// 自定义追加查询条件
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.weixinUserService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * Ajax:用户同步
	 */
	@RequestMapping(params = "doSame")
	@ResponseBody
	public AjaxJson doSame(WeixinAccountEntity weixinAccount,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		//TODO
//		message = "微信公众帐号信息添加成功";
//		try {
//			// 判断当前帐号是否已经添加微信公众账户
//			int f = weixinAccountService.findByUsername(
//					ResourceUtil.getSessionUserName().getUserName()).size();
//			if (f == 0) {
//				weixinAccount.setUserName(ResourceUtil.getSessionUserName()
//						.getUserName());
//				weixinAccountService.save(weixinAccount);
//				systemService.addLog(message, Globals.Log_Type_INSERT,
//						Globals.Log_Leavel_INFO);
//				//重置session中的微信公众帐号ID
//				List<WeixinAccountEntity> acclst = weixinAccountService.findByProperty(WeixinAccountEntity.class, "accountnumber", weixinAccount.getAccountnumber());
//				request.getSession().setAttribute(WeiXinConstants.WEIXIN_ACCOUNT, acclst.get(0));
//			} else {
//				message = "微信公众帐号信息添加失败,每个用户只能添加一个微信公众帐号";
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			message = "微信公众帐号信息添加失败";
//			throw new BusinessException(e.getMessage());
//		}
//
//		j.setMsg(message);
		return j;
	}
	
}
