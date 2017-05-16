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
<link rel="stylesheet" href="<c:url value='/extension/css/style.css' />" />
<script type="text/javascript" src="<c:url value='/extension/js/zepto.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/extension/js/zepto.touch.js' />"></script>
<script type="text/javascript" src="<c:url value='/extension/js/iscroll-probe.js' />"></script>
<script type="text/javascript" src="<c:url value='/extension/js/detail.js' />"></script>
<title>数据详情</title>
</head>
<body>
	<div class="data">
		<div class="data-head">
			<div class="data-head-l">${titleFirst.name}/${titleFirst.code}</div>
			<div class="data-head-r dswiper" id="wrapper1">
				<div id="scroller1" class="swiper-scroller">
					<c:forEach items="${titleLast}" var="titleLast" varStatus="status">
						<span>${titleLast.name}</span>
					</c:forEach>
					
				</div>
			</div>
		</div>
		<div class="data-body">
			<div class="data-body-l">
				
				<c:forEach items="${contentFirst}" var="contentFirst" varStatus="status">
						<div class="data-tr-l">
							<span>${contentFirst.name}<br />
							<strong>${contentFirst.code}</strong></span>
						</div>
				</c:forEach>

			</div>

			<div class="data-body-r dswiper" id="wrapper2">
				<div id="scroller2" class="swiper-scroller">
					
					<c:forEach items="${contentLast}" var="content" varStatus="status">
						<div class="data-tr-r">
							<c:forEach items="${content}" var="contentLast1" varStatus="status">
								<span>${contentLast1}</span>
							</c:forEach>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</body>
</html>