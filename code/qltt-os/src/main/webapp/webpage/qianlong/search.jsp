<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html lang="zh-CN" ng-app="qlttApp">
<head>
<%@ include file="head.jsp"%>
<script type="text/javascript" src="<c:url value='/extension/js/search.js' />"></script>
<title>指标查询</title>
</head>
<body>
	<!-- 头部菜单 -->
	<div id="header">
		<div id="scroller">
			<ul>
				<li class="active">组合指标</li>
				<li>实时指标</li>
				<li>K线指标</li>
				<li>K线指标</li>
				<li>数据指标</li>
			</ul>
		</div>
	</div>

	<!-- 查询内容 -->
	<div class="search swiper-container">
		<div class="swiper-wrapper"></div>
	</div>

	<!-- 加载中 -->
	<div class="qltt-toast">
		<i class="qltt-loading qltt-icon_toast"></i>
		<p class="qltt-toast_content">数据加载中</p>
	</div>

	<!-- 页脚菜单 -->
	<div class="footer">
		<a href="" class="active"><i class="icon icon-searchd "></i><br> <span>指标查询</span></a>
		<a href=""><i class="icon icon-care"></i><br><span>我的关注</span></a>
		<a href=""><i class="icon icon-pool"></i><br><span>选股池</span></a>
		<a href=""><i class="icon icon-rmd"></i><br><span>荐股池</span></a>
	</div>
</body>
</html>