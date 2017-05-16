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
<script type="text/javascript" src="<c:url value='/extension/js/zepto.touch.js' />"></script>
<script type="text/javascript" src="<c:url value='/extension/js/iscroll.js' />"></script>
<script type="text/javascript" src="<c:url value='/extension/js/swiper.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/extension/js/swiper.animate1.0.2.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/extension/js/headerISroll.js' />"></script>
<script type="text/javascript" src="<c:url value='/extension/js/footer.js' />"></script>
<script type="text/javascript" src="<c:url value='/extension/js/common.js' />"></script>
<script type="text/javascript" src="<c:url value='/extension/js/pool.js' />"></script>
<script type="text/javascript">
	var contextPath = '${pageContext.request.contextPath}';
</script>
<script type="text/javascript" src="<c:url value='/extension/js/search.js' />"></script>
<title>指标查询</title>
</head>
<body>
	<!-- 头部菜单 -->
	<div id="header">
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

	<!-- 查询内容 -->
	<div class="search swiper-container">
		<div class="swiper-wrapper"></div>
	</div>

	<!-- 加载中 -->
	<div class="qltt-toast">
		<i class="qltt-loading qltt-icon_toast"></i>
	</div>

	<!-- 页脚菜单 -->
	<div class="pool-footer">
		<div class="pFooter-btn">
			<a href="<c:url value='/webapp/combined/home.do' />"><span><i>+</i>组合指标设置</span></a>
		</div>
		<div class="footer">
			<a href="<c:url value='/webapp/search/home.do' />" class="active"><i class="icon icon-searchd"></i><br><span>指标查询</span></a>
			<a href="<c:url value='/webapp/myattention/home.do' />"><i class="icon icon-care"></i><br><span>我的关注</span></a>
			<a href="<c:url value='/webapp/userpool/home.do' />"><i class="icon icon-pool"></i><br><span>选股池</span></a>
			<a href="javascript:void(0);"><i class="icon icon-rmd"></i><br><span>荐股池</span></a>
		</div>
	</div>
</body>
</html>