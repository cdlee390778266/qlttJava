<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN" ng-app="qlttApp">
<head>
<meta charset="utf-8">
<meta name="referrer" content="always">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<meta http-equiv="x-dns-prefetch-control" content="on">
<link rel="stylesheet" href="<c:url value='/extension/css/swiper.min.css' />" />
<link rel="stylesheet" href="<c:url value='/extension/css/animate.min.css' />" />
<link rel="stylesheet" href="<c:url value='/extension/css/style.css' />" />
<script type="text/javascript" src="<c:url value='/extension/js/zepto.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/extension/js/swiper.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/extension/js/zepto.touch.js' />"></script>
<script type="text/javascript" src="<c:url value='/extension/js/iscroll.js' />"></script>
<script type="text/javascript" src="<c:url value='/extension/js/headerISroll.js' />"></script>
<script type="text/javascript" src="<c:url value='/extension/js/swiper.animate1.0.2.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/extension/js/common.js' />"></script>
<script type="text/javascript" src="<c:url value='/extension/js/diy.js' />"></script>
<script type="text/javascript" src="<c:url value='/extension/js/messager.js' />"></script>
<title>自定义组合指标</title>
</head>
<body>
	<!-- 头部菜单 -->
	<div id="header" class="zbset">
		<div id="scroller">
			<ul>
				<c:forEach items="${idxGroups}" var="idxGroup" varStatus="status">
					<c:choose>
						<c:when test="${status.index == 0}">
							<li data-group="${idxGroup.tacgroup}" class="active">${idxGroup.grpname}</li>
						</c:when>
						<c:otherwise>
							<li data-group="${idxGroup.tacgroup}">${idxGroup.grpname}</li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ul>
		</div>
	</div>
	
	<!-- 页面主体 -->
	<div class="page-main " id="diy-container">
		<div id="diy-wrapper"></div>
		<div class="tag-check tag">
			<div class="check-txt ">我的组合</div>
			<div class="check-tags"></div>
			<div class="check-btn">
				<a href="javascript:void(0);"><strong ng-bind="btnClc" class="sc-btn">收藏组合指标</strong></a>
			</div>
		</div>
		
		<!--自定义组合弹框-->
		<div class="dialog animated" id="zdyzh">
			<div class="dialog-mask"></div>
			<div class="dialog-box">
				<div class="dialog-head dialog-head-bg">自定义组合指标</div>
				<div class="dialog-body">
					<div class="sc-content-box">
						<div class="sc-content-title">
							<input type="text" class="sc-title" placeholder="自定义指标名称">
						</div>
						<div class="sc-content-text">
							<textarea type="text" class="sc-text" placeholder="请输入备注"></textarea>
						</div>
					</div>
				</div>
				<div class="dialog-foot">
					<div class="dialog-foot-right">
						<span class="dialog-btn-close">取消</span>
						<span class="dialog-btn-confirm">确定</span>
					</div>
				</div>
			</div>
		</div>
		
		<div class="srceen">
			<div class="srceen-txt">
				<span>根据您的指标组合共计筛选出</span><span class="red stock-num">0</span><span>支股票</span>
			</div>
			<div class="screen-main" data-start="1" data-size="3">
			</div>
			<div class="load-more">
				<i></i><span>点击加载更多</span>
			</div>
		</div>
	</div>
	
	<!-- 加载中 -->
	<div class="qltt-toast">
		<i class="qltt-loading qltt-icon_toast"></i>
	</div>
	
	<!-- 错误提示 -->
	<div id="error" class="animated"><span></span></div>
	
	<input type="hidden" id="code" name="code" />
	<input type="hidden" id="name" name="name" />
	
	<!-- 荐股池弹窗 -->
	<div class="dialog animated" id="recommend">
		<div class="dialog-mask"></div>
		<div class="dialog-box">
			<div class="dialog-head">推荐到我的荐股</div>
			<div class="dialog-body">
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
	
	<!-- 选股池弹窗 -->
	<div class="dialog animated" id="choose">
		<div class="dialog-mask"></div>
		<div class="dialog-box dialog-box-bg">
			<div class="dialog-head">加入选股池</div>
			<div class="dialog-body">
				<div class="select-box">
					<div class="select-mask">选股池A</div>
					<select name="choosePool" id="choosePool" multiple="multiple">
						<option value="1">选股池A</option>
						<option value="2">选股池B</option>
						<option value="3">选股池C</option>
					</select>
				</div>
			</div>
			<div class="dialog-foot">
				<div class="dialog-foot-right">
					<span class="dialog-btn-close">取消</span>
					<span class="dialog-btn-confirm">确定</span>
				</div>
			</div>
		</div>
	</div>
</body>
</html>