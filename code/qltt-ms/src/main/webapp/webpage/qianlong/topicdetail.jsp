<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="referrer" content="always">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<meta http-equiv="x-dns-prefetch-control" content="on">
<link rel="stylesheet" href="<c:url value='/extension/css/animate.min.css' />" />
<link rel="stylesheet" href="<c:url value='/extension/css/style.css' />" />
<script type="text/javascript" src="<c:url value='/extension/js/zepto.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/extension/js/zepto.touch.js' />"></script>
<script type="text/javascript" src="<c:url value='/extension/js/messager.js' />"></script>
<script type="text/javascript">
	var contextPath = '${pageContext.request.contextPath}';
</script>
<title>热门主题</title>
</head>
<body>

	<div class="hot-detail">
		<div class="hot-detail-head">
			<div class="hd-title"></div>
			<span class="hd-date"></span> 
			<span class="hd-click"></span>
			<span class="hd-author"></span>
		</div>
		
		<div class="hot-detail-body">
		</div>
		
		<div class="hot-detail-tags">
			<div class="hd-tags-items">				
			</div>

			<div class="hd-tags-result">
				<div class="hd-result-bar">
					相关股票结果
				</div>
				<div class="srceen">
					<div class="screen-main"></div>
				</div>
			</div>
		</div>
		
		<div class="hot-cmt">
			<div class="hot-cmt-title">主题评论</div>
			<div class="hot-cmt-body">
							
			</div>
			<div class="load-more">
				<i class=""></i> <span>下拉加载更多...</span>
			</div>
		</div>
	</div>
	
	<!-- 评论 -->
    <form action="" class="hot-comment" id="comment">
        <textarea name="" id="comment-txt" placeholder="写评论" ></textarea>
        <input type="submit" value="发表" id="comment-submit" />
    </form>

	<!-- 返回 -->
	<div class="goBack">
		<a href="javascript:history.back();"></a>
	</div>
	<!-- 加载中 -->
	<div class="qltt-toast">
		<i class="qltt-loading qltt-icon_toast"></i>
	</div>
	 
	<script type="text/javascript"
		src="<c:url value='/extension/js/topicdetail.js' />">
	</script>
	 
</body>
</html>