<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html lang="zh-CN" ng-app="qlttApp">
<head>
<meta charset="utf-8">
<meta name="referrer" content="always">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<meta http-equiv="x-dns-prefetch-control" content="on">
<link rel="stylesheet" href="<c:url value='/extension/css/animate.min.css' />" />
<link rel="stylesheet" href="<c:url value='/extension/css/style.css' />" />
<script type="text/javascript" src="<c:url value='/extension/js/zepto.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/extension/js/swiper.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/extension/js/zepto.touch.js' />"></script>
<script type="text/javascript" src="<c:url value='/extension/js/iscroll.js' />"></script>
<script type="text/javascript" src="<c:url value='/extension/js/swiper.animate1.0.2.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/extension/js/common.js' />"></script>
<script type="text/javascript" src="<c:url value='/extension/js/pool.js' />"></script>
<title>指标查询</title>
</head>
<body>
	<div class="pool animated">
		<!-- 头部菜单 -->
		<div class="pool-head-wrapper">
			<div id="headerPool">
				<div id="scrollerPool">
					<ul>
						<li class="active" data-href="pool-main-0" data-pool="1"><span>选股池A</span></li>
						<li class="" data-href="pool-main-1" data-pool="2"><span>选股池B</span></li>
						<li class="" data-href="pool-main-2" data-pool="3"><span>选股池C</span></li>
					</ul>
				</div>
			</div>
			<div class="pool-head">
				<div class="pHead-th">股票名称</div>
				<div class="pHead-th">股票代码</div>
				<div class="pHead-th">操作</div>
			</div>
		</div>
		
		<!-- 页面主体 -->
		<div class="page-main pool-container" id="pool-container">
			<div id="pool-wrapper" class="pool-wrapper"></div>
		</div>
		
		<!-- 加载中 -->
		<div class="qltt-toast">
			<i class="qltt-loading qltt-icon_toast"></i>
		</div>
		
		<!-- 页脚菜单 -->
		<div class="pool-footer">
			<!-- 
			<div class="pFooter-btn">
				<span><i>+</i>自选股票</span>
			</div>
			 -->
			<div class="footer">
				<a href="<c:url value='/webapp/search/home.do' />"><i class="icon icon-searchd"></i><br><span>指标查询</span></a>
				<a href="<c:url value='/webapp/myattention/home.do' />"><i class="icon icon-care"></i><br><span>我的关注</span></a>
				<a href="<c:url value='/webapp/userpool/home.do' />" class="active"><i class="icon icon-pool"></i><br><span>选股池</span></a>
				<a href="javascript:void(0);"><i class="icon icon-rmd"></i><br><span>荐股池</span></a>
				<a href="<c:url value='/webapp/setting/home.do' />"><i class="icon icon-set"></i><br><span>设置</span></a>
			</div>
		</div>
	</div>
	
	<div class="dialog animated" id="recommend">
		<div class="dialog-mask"></div>
		<div class="dialog-box">
			<div class="dialog-head dialog-head-bg">推荐到我的荐股</div>
			<div class="dialog-body">
				<input type="hidden" id="code" name="code" />
				<input type="hidden" id="name" name="name" />
				<input type="text" id="reason" name="reason" placeholder="推荐理由" />
			</div>
			<div class="dialog-foot">
				<div class="dialog-foot-right">
					<span class="dialog-btn-close">取消</span>
					<span class="dialog-btn-confirm">确定</span>
				</div>
			</div>
		</div>
	</div>
	
	<div class="dialog animated" id="choose">
		<div class="dialog-mask"></div>
		<div class="dialog-box">
			<div class="dialog-head">加入选股池</div>
			<div class="dialog-body">
				<div class="select-box">
					<div class="select-mask">选股池A</div>
					<select name="" id="choosePool" multiple="multiple">
						<option value="1">选股池A</option>
                        <option value="2">选股池B</option>
                        <option value="3">选股池C</option>
					</select>
				</div>
			</div>
			<div class="dialog-foot pool-dialog-foot">
				<div class="dialog-foot-left">
					<span class="dialog-btn-delete">直接删除</span>
				</div>
				<div class="dialog-foot-right">
					<span class="dialog-btn-close">取消</span>
					<span class="dialog-btn-confirm">确定</span>
				</div>
			</div>
		</div>
	</div>
	
	<div class="pool-add-menu animated">
		<!-- <div class="pam-icon"><i>+</i></div> -->
		<div class="menu-box">
			<div class="menu-head">
				<div class="menu-head-left">我的选股池</div>
				<div class="menu-head-right">
					<span>编辑</span>
				</div>
			</div>
			<div class="menu-body"></div>
			<div class="menu-foot">
				<div class="menu-input"><input type="text" placeholder="输入选股池名称" name="poolName" id="poolName" /></div>
				<div class="menu-btns">
					<div class="menu-btns-edit"><span>修改名称</span></div>
					<div class="menu-btns-add"><span>新增菜单</span></div>
				</div>
			</div>
		</div>
	</div>
	
	<div id="error" class="animated"><span></span></div>
</body>
</html>