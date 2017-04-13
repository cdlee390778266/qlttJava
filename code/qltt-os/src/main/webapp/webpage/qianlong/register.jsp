<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html lang="zh-CN" ng-app="qlttApp">
<head>
<%@ include file="head.jsp"%>
<script type="text/javascript" src="<c:url value='/extension/js/login.js' />"></script>
<title>手机验证</title>
</head>
<body>
	<div class="login-bg">
		<img src="<c:url value='/extension/images/login_bg.png' />" class="img-responsive" />
	</div>

	<form action="" class="container login">
		<div class="login-input-box">
			<div class="login-input borb">
				<input type="text" class="icon icon-phone" maxlength="11" placeholder="请输入您的手机号码" name="phone" id="phone" />
			</div>
		</div>
		<div class="login-input-box">
			<div class="login-input borb">
				<input type="text" class="icon icon-code mt15" placeholder="手机验证码" maxlength="4" name="code" id="code" />
			</div>
			<div class="btn-code">
				<span>获取验证码</span>
			</div>
		</div>
		<div class="login-tip">
			<p>
				<span>选择【手机验证】</span>代表你已阅读并同意<a class="fred" href="tpls/ttxieyi.html">《钱龙推推用户协议》</a>，验证成功你将获得钱龙推推应用全部功能。
			</p>

		</div>
		<input type="submit" value="确定" class="login-submit waves-effect waves-button waves-float" />
	</form>
	<div id="error" class="animated">
		<span></span>
	</div>
</body>
</html>