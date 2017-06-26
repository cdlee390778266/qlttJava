package com.qianlong.qlttms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.qianlong.qlttms.domain.AuthResultEntity;
import com.qianlong.qlttms.domain.BlockPage;
import com.qianlong.qlttms.domain.db.NameTable;
import com.qianlong.qlttms.domain.hottopic.ArticleEntity;
import com.qianlong.qlttms.domain.hottopic.CommentEntity;
import com.qianlong.qlttms.domain.hottopic.PublishTargetEntity;
import com.qianlong.qlttms.service.IBlockService;
import com.qianlong.qlttms.service.ICommonDBService;
import com.qianlong.qlttms.utils.Constants;
import com.qianlong.qlttms.utils.DateUtils;
import com.qianlong.qlttms.utils.JsonResult;
import com.qlcd.qltt.body.pvt.T02007002;
import com.qlcd.qltt.body.pvt.T02007004;


@Scope("prototype")
@Controller
@RequestMapping("webapp/hottopic/")
public class HotTopicController {
	
	
	private Logger logger = Logger.getLogger(HotTopicController.class);
	
	@Autowired
	private ICommonDBService commonDBService;
	
	@Autowired
	private IBlockService blockService;
	
	@RequestMapping("home")
	public ModelAndView home() {
		logger.debug("进入热门主题首页");

		return new ModelAndView("qianlong/hottopic");
	}
	
	
	@RequestMapping("findtopics")
	@ResponseBody
	public Object findtopics(HttpServletRequest request, 
			@RequestParam(value = "maxResults")int maxResults) {
		logger.debug("进入热门主题");
		
		AuthResultEntity user = (AuthResultEntity)request.getSession().getAttribute(Constants.LOGIN_USER_ACCOUNT);
		
		String hql = "select article from ArticleEntity article, PublishTargetEntity publishTarget  WHERE article.id=publishTarget.articleid AND  publishTarget.accountid = ?";

		List<ArticleEntity> articles =commonDBService.findMaxResultHql(hql, maxResults, user.getWeixinAccountId());
			
		return JsonResult.jsonData(articles);

	}
	
	
	@RequestMapping("topic")
	public ModelAndView topic() {
		logger.debug("进入热门主题详细页面");

		return new ModelAndView("qianlong/topicdetail");
	}
	
	
	
	@RequestMapping("topicinfo")
	@ResponseBody
	public Object topicinfo(HttpServletRequest request, 
			@RequestParam(value = "id")String id) {
		logger.debug("进入热门主题详细页面"+id);
	
		AuthResultEntity user = (AuthResultEntity)request.getSession().getAttribute(Constants.LOGIN_USER_ACCOUNT);
		
		String hql = "from PublishTargetEntity WHERE  articleid = ? AND accountid = ?";

		List<PublishTargetEntity> targets =commonDBService.findHql(hql, id, user.getWeixinAccountId());
		
		if(targets == null || targets.size() == 0){
			return JsonResult.jsonError("您没有权限查看发布的热门主题,请关注相关公众号");
		}
		ArticleEntity article = commonDBService.findUniqueByProperty(ArticleEntity.class, "id", id);	
		
		article.setClicktimes(article.getClicktimes()+1);
		
		commonDBService.updateEntitie(article);
		
		return JsonResult.jsonData(article);
	}
	
	
	@RequestMapping("topiccommit")
	@ResponseBody
	public Object topiccommit(HttpServletRequest request, 
			@RequestParam(value = "id")String id,
			@RequestParam(value = "start", required = false, defaultValue="1") int start,
			@RequestParam(value = "size", required = false, defaultValue="20") int size) {
		
		logger.debug("查看评论请求"+id);
	
		AuthResultEntity user = (AuthResultEntity)request.getSession().getAttribute(Constants.LOGIN_USER_ACCOUNT);
		HashMap<String, Object> maps = new HashMap<String, Object>();
		
		DetachedCriteria dc = DetachedCriteria.forClass(CommentEntity.class);   
		dc.add(Restrictions.eq("articleid", id));
		dc.add(Restrictions.eq("accountid", user.getWeixinAccountId()));
		
		dc.addOrder(Order.desc("commenttime"));
		
		List<CommentEntity> comments =commonDBService.pageList(dc, start, size);
		
		maps.put("data", comments);

		return JsonResult.jsonMap(maps);

	}
	
	
	@RequestMapping("publishcomment")
	@ResponseBody
	public Object publishcomment(HttpServletRequest request, 
			@RequestParam(value = "id")String id,
			@RequestParam(value = "content", required = true) String content) {
		
		logger.debug("发布主题评论"+id);
	
		AuthResultEntity user = (AuthResultEntity)request.getSession().getAttribute(Constants.LOGIN_USER_ACCOUNT);

		String sql = "SELECT nickName, headimgurl FROM WeiXin_User WHERE Accountid = ? AND openid = ? ";
		
		List<Map<String, Object>> lists = commonDBService.findForJdbc(sql, user.getKeyid(), user.getOpenid());
		
		CommentEntity commentEntity  = new CommentEntity();
		if(lists.size() == 1){
			Map<String, Object> fans = lists.get(0);
			commentEntity.setNicknamebytes((byte[])fans.get("nickName"));
			commentEntity.setPortrait(fans.get("headimgurl").toString());
		}
		
		commentEntity.setOpenid(user.getOpenid());		
		commentEntity.setArticleid(id);
		commentEntity.setContent(content);
		commentEntity.setAccountid(user.getWeixinAccountId());
		commentEntity.setLikenum(0);
		commentEntity.setCommenttime(DateUtils.gettimestamp());
		
		commonDBService.save(commentEntity);

		return JsonResult.jsonOk();
	}
	
	@RequestMapping("thumbsup")
	@ResponseBody
	public Object thumbsup(HttpServletRequest request, 
			@RequestParam(value = "cmtid")String cmtid) {
		
		logger.debug("进入点赞处理"+cmtid);
	
		CommentEntity commentEntity = commonDBService.findUniqueByProperty(CommentEntity.class, "id", cmtid);	
		
		commentEntity.setLikenum(commentEntity.getLikenum()+1);
		
		commonDBService.updateEntitie(commentEntity);
		
		return JsonResult.jsonOk();
	}
	
	
	
	@RequestMapping("stockbyblock")
	@ResponseBody
	public Object stockbyblock(HttpServletRequest request, 
			@RequestParam(value = "labels[]", required = false) String[] labels,
			@RequestParam(value = "start", required = false, defaultValue="1") int start,
			@RequestParam(value = "size", required = false, defaultValue="20") int size) {
		
		
		logger.debug("根据版块查询股票"+labels.length);
	
		AuthResultEntity user = (AuthResultEntity)request.getSession().getAttribute(Constants.LOGIN_USER_ACCOUNT);
		
		BlockPage page = new BlockPage();
		page.setSize(size);
		page.setStart(start);

		
		int rspnum = 0;
		int totalnum = 0;
		
		HashMap<String, Object> res = new HashMap<String, Object>();
		
		StringBuffer hql = new StringBuffer("FROM NameTable WHERE ");
		if(labels.length == 1){
			T02007002._rsp rsp = blockService.queryStockLabel(user.getWeixinAccountId(), labels[0], page);
			rspnum = rsp.getPgrsp().getRspnum();
			totalnum = rsp.getPgrsp().getTotalnum();
			
			List<com.qlcd.qltt.body.pvt.T02007002._labelstock> stock = rsp.getLslistList();
			
			String json;
			try {
				json = JsonFormat.printer().includingDefaultValueFields().print(rsp);
				logger.debug(
						String.format("2007003 交易 - 转换后的JSON字符串: [%s]", json));
			} catch (InvalidProtocolBufferException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(rspnum == 0){				
				return JsonResult.jsonError("无对映版块商品");
			}

			int i = 0;
			for(com.qlcd.qltt.body.pvt.T02007002._labelstock l: stock){
				String[] mandc = l.getStockcode().split("[,]");
				if(i > 0){
					hql.append("OR ");
				}
				i++;
				hql.append("(fsMarketID = '").append(mandc[0]).append("' AND fsCode = '").append(mandc[1]).append("') ");
			}
		}else{
			T02007004._rsp rsp = blockService.queryStockLimitLabel(user.getWeixinAccountId(), labels, page);
			
			String json;
			try {
				json = JsonFormat.printer().includingDefaultValueFields().print(rsp);
				logger.debug(
						String.format("2007004 交易 - 转换后的JSON字符串: [%s]", json));
			} catch (InvalidProtocolBufferException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			rspnum = rsp.getPgrsp().getRspnum();
			totalnum = rsp.getPgrsp().getTotalnum();
			
			if(rspnum == 0){
				return JsonResult.jsonError("无对映版块商品");
			}
			
			List<com.qlcd.qltt.body.pvt.T02007004._labelstock> stock = rsp.getLslistList();
			
			int i = 0;
			for(com.qlcd.qltt.body.pvt.T02007004._labelstock l: stock){
				String[] mandc = l.getStockcode().split("[,]");
				if(i > 0){
					hql.append("OR ");					
				}
				i++;
				hql.append("(fsMarketID = '").append(mandc[0]).append("' AND fsCode = '").append(mandc[1]).append("') ");
			}
		}
		
		logger.debug(hql.toString());
		
		List<NameTable> tables =commonDBService.findHql(hql.toString());
		if(tables == null || tables.size() == 0){
			return JsonResult.jsonError("无法找到对映的商品名称");
		}
		res.put("rspnum", tables.size());
		res.put("totalnum", totalnum);
		res.put("tables", tables);
		
		return JsonResult.jsonMap(res);
	}
	
	
	
	
	
	

}
