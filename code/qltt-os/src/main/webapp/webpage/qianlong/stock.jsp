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
<link rel="stylesheet" href="<c:url value='/extension/css/swiper.min.css' />" />
<link rel="stylesheet" href="<c:url value='/extension/css/animate.min.css' />" />
<link rel="stylesheet" href="<c:url value='/extension/css/style.css' />" />
<script type="text/javascript" src="<c:url value='/extension/js/zepto.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/extension/js/swiper.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/extension/js/zepto.touch.js' />"></script>
<script type="text/javascript" src="<c:url value='/extension/js/stock.js' />"></script>
<title>我的选股结果</title>
</head>
<body>
	<!-- 详细内容 -->
	<div class="result">
		<input type="hidden" id="tacTic" hidden="tacTic" value="${tacTic}">
		<div class="result-box">
			<img src="<c:url value='/extension/images/result_bg.png' />" alt="" class="img-responsive" />
			<div class="result-main">
				<div class="result-tags">
					<div class="result-tags-box">
						<span>${tacName}</span>
					</div>
				</div>
			</div>
		</div>
		<div class="result-btns">
			<span class="result-btn-care<c:if test="${isFollow}"> active</c:if>">关注</span>
		</div>
	</div>
	
	<div class="result-middle">
		<c:if test="${members!=null && !empty members}">
			<c:forEach items="${members}" var="mem">
				<div class="result-middle-item">
					<div class="search-head">${mem.srctacname}[${mem.srctactic}]</div>
					<!--
					<div class="search-body">
						<a href="#"></a>
					</div>
					  -->
				</div>
			</c:forEach>
		</c:if>	
	</div>	

	<!-- 详细内容 -->
	<div class="srceen">
		<div class="srceen-txt">
			<span>该指标共计筛选出</span><span class="red"></span><span>支股票</span>
		</div>
		<div class="screen-main" data-start="1" data-size="5"></div>
		<div class="load-more">
			<i></i><span>加载更多</span>
		</div>
	</div>
	
	<input type="hidden" id="code" name="code" />
	<input type="hidden" id="name" name="name" />

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

	<div class="dialog animated" id="choose">
		<div class="dialog-mask"></div>
		<div class="dialog-box dialog-box-bg">
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