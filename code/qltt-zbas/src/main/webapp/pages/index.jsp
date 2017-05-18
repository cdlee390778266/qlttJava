<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@ include file="common/jstlres.jsp"%>
<html>
<head>
<title>登陆页面</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<%@ include file="common/uires.jsp"%>
</head>
<body>
	<div class="login-box">
		<div class="easyui-panel" title="登陆"
			style="width: 100%; max-width: 400px; padding: 30px 60px;">
			<form id="login-form" method="post" action="${ctx}/login.html">
				<div class="alert-danger">
					<h4>${errorMsg}</h4>
				</div>
				
				<div style="margin-bottom: 20px">
					<input class="easyui-textbox" iconWidth="20" style="width: 100%"
						name="username"  
						data-options="label:'用户名:',required:true,missingMessage:'不能为空',iconCls:'icon-man'"
						value="${admin.username}">
				</div>
				
				<div style="margin-bottom: 20px">
					<input class="easyui-passwordbox" iconWidth="20" style="width: 100%"
						name="password" 
						data-options="label:'密码:',required:true,missingMessage:'不能为空',validType:'length[6,16]'"
						value="${admin.password}"
						>
				</div>
			</form>
			<div style="text-align: center; padding: 5px 0">
				<button  type class="easyui-linkbutton"
					onclick="submitForm()" style="width: 80px">登陆</button>
				<a
					href="javascript:void(0)" class="easyui-linkbutton"
					onclick="clearForm()" style="width: 80px">重置</a>
			</div>
		</div>
	</div>
	<script>
        function submitForm(){
        	if($("#login-form").form('validate')){
        		$('#login-form').submit();
        	}
        }
        function clearForm(){
            $('#login-form').form('clear');
        }
    </script>
</body>
</html>
