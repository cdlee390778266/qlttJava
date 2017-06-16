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
<script type="text/javascript" src="<c:url value='/extension/js/zepto.touch.js' />"></script>
<script type="text/javascript" src="<c:url value='/extension/js/footer.js' />"></script>
<script type="text/javascript" src="<c:url value='/extension/js/login.js' />"></script>
<script type="text/javascript" src="<c:url value='/extension/js/messager.js' />"></script>
<title>手机验证</title>
</head>
<body>
	<div class="login-bg">
		<img src="<c:url value='/extension/images/login_bg.png' />" class="img-responsive" />
	</div>
	<form action="<c:url value='/webapp/register/register.do' />" class="container login" method="POST">
		<div class="login-input-box">
			<div class="login-input borb">
				<input type="tel" class="icon icon-phone" maxlength="11" placeholder="请输入您的手机号码" name="phone" id="phone" />
			</div>
		</div>
		<div class="login-input-box">
			<div class="login-input borb">
				<input type="number" class="icon icon-code mt15" placeholder="手机验证码" maxlength="6" name="code" id="code" />
			</div>
			<div class="btn-code">
				<span>获取验证码</span>
			</div>
		</div>
		<div class="login-tip">
			<p>
				<span>选择【手机验证】</span>代表你已阅读并同意<a class="blue" href="<c:url value='agreement.do' />">《钱龙推推用户协议》</a>，验证成功你将获得钱龙推推应用全部功能。
			</p>
		</div>
		<input class="login-submit" type="submit" value="确定">
	</form>
	<div id="error" class="animated">
		<span></span>
	</div>
</body>
</html>