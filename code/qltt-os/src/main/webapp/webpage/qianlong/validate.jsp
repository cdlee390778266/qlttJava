<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta name="keywords" content="乾隆推推">
<meta name="description" content="乾隆推推">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="webkit">
<script type="text/javascript" src="<c:url value='/plug-in/jquery/jquery-1.8.3.js' />"></script>
<title>乾隆推推</title>
</head>
<body>
	<form action="<c:url value='/webapp/register/validate.do' />" method="POST">
		<label for="phone">请输入手机号码：</label><input type="text" id="phone" name="phone" width="20">
		<a id="obtain" href="javascript:void(0);">点击获取验证码</a>
		<label for="authCode">请输入验证码：</label><input type="text" id="authCode" name="authCode" width="20">
		<input type="submit" id="submit" value="提交">
	</form>
	<script type="text/javascript">
	$("#obtain").on("click", function(){
		var url = "<c:url value='/webapp/register/obtainAuthCode.do' />";
		var phone = $("#phone").val();
		if (phone == null || phone == "") {
			alert("手机号不能为空！");
			return false;
		}
		$.ajax({
			url : url,
			data : {"phone" : phone},
			dataType : "text",
			error : function(jqXHR, textStatus, errorThrown) {
				//
			},
			success : function(data, textStatus, jqXHR) {
				alert(data);
			}
		});
	});
	</script>
</body>
</html>