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
<link rel="stylesheet" href="<c:url value='/extension/css/style.css' />" />
<script type="text/javascript" src="<c:url value='/extension/js/zepto.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/extension/js/zepto.touch.js' />"></script>
<script type="text/javascript">
	var contextPath = '${pageContext.request.contextPath}';
</script>
<script type="text/javascript" src="<c:url value='/extension/js/care.js' />"></script>
<title>我的关注</title>
</head>
<body>
	<div class="search container care">
		<c:forEach items="${followingList}" var="following" varStatus="status">
			<div class="search-item animated">
				<div class="search-head" data-id="${following.tacTic}" data-name="${following.tacName}"><span>${following.tacName}</span><i></i></div>
				<div class="search-body"><a href="javascript:void(0);">${following.tacDetail}</a></div>
				<div class="search-foot">
					<span class="active">关注</span>
				</div>
			</div>
		</c:forEach>
	
		<!-- 没有数据 -->
		<div class="pool-empty hidden">
			<img src="<c:url value='/extension/images/pool.png' />" /><br />您还没有任何关注！
		</div>
	</div>
	
	<!-- 页脚菜单 -->
	<div class="footer">
		<a href="<c:url value='/webapp/search/home.do' />"><i class="icon icon-searchd"></i><br><span>指标查询</span></a>
		<a href="<c:url value='/webapp/myattention/home.do' />" class="active"><i class="icon icon-care"></i><br><span>我的关注</span></a>
		<a href="<c:url value='/webapp/userpool/home.do' />"><i class="icon icon-pool"></i><br><span>选股池</span></a>
		<a href="javascript:void(0);"><i class="icon icon-rmd"></i><br><span>荐股池</span></a>
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			displayEmpty();
		});
		
		function displayEmpty() {
			if ($(".search-item").length < 1){
				if($(".pool-empty").hasClass("hidden"))
					$(".pool-empty").removeClass("hidden");
			} else {
				$(".pool-empty").addClass("hidden");
			}
		}
	</script>
</body>
</html>