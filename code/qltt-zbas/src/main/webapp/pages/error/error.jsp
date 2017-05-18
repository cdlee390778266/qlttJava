<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@ include file="../common/jstlres.jsp"%>
<html>
<head>
<title>错误信息</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<%@ include file="../common/uires.jsp"%>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div style="margin:40px;">
		<h3>出现错误</h3><br/>
		<h3>错误信息：</h3>
		<p>{error}</p>
		<h3>返回<a style="color:red" href="#" onClick="javascript:backToPre();">上一页</a></h3>
	</div>
	<script>
		function backToPre(){
			history.go(-1);
		}
	</script>
    <h2>${error}</h2>
</body>
</html>