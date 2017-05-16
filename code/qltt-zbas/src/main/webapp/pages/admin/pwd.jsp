<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.qianlong.qltt.zbas.entity.Admin"%>
<%@page import="com.qianlong.qltt.zbas.common.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@ include file="../common/jstlres.jsp"%>
<html>
<head>
<title>主页面</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<%@ include file="../common/uires.jsp"%>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div class="easyui-panel" style="padding: 5px;"
		data-options="border:false">
		<div style="margin-top:20px;margin-left:10px;width:400px;padding:10px;">
			<form method="post">
				<input id="username" name="username" type="hidden"
					value="<%=((Admin)request.getSession().getAttribute(Constants.ADMINISTRATOR)).getUsername()%>" />
				<div style="margin-bottom: 20px">
					<input class="easyui-passwordbox" iconWidth="20"  id="password"
						style="width: 100%" name="password"
						data-options="label:'旧密码:',required:true,missingMessage:'不能为空',validType:'length[6,16]'">
				</div>
				<div style="margin-bottom: 20px">
					<input class="easyui-passwordbox" iconWidth="20" id="newpwd"
						style="width: 100%" name="newpwd"
						data-options="label:'新密码:',required:true,missingMessage:'不能为空',validType:'length[6,16]'">
				</div>
				<div style="margin-bottom: 20px">
					<input class="easyui-passwordbox" iconWidth="20" id="confirm"
						style="width: 100%" name="newpwd"
						data-options="label:'确认密码:',required:true,missingMessage:'不能为空',validType:'length[6,16]'">
				</div>
			</form>
			<div style="text-align: center; padding: 5px 0">
				<button  class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="chgpwd()" style="width: 80px">提交</button>
				<button  class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onclick="clearForm()" style="width: 80px">重置</button>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$.parser.parse();

		function chgpwd(){
        	if($("form").form('validate')){
        		var param = {};
        		param.username = $('#username').val();
        	    param.password = $('#password').textbox("getValue");
        	    param.newpwd = $('#newpwd').textbox("getValue");
        	    if(param.password == param.newpwd){
        	    	$.messager.alert("提示","新密码和旧密码相同");
        	    	return;
        	    }
        	    var confirmpwd = $('#confirm').textbox("getValue");
        	    if(param.newpwd != confirmpwd){
        	    	$.messager.alert("提示","新密码和确认密码不一样");
        	    	return;
        	    }
        		var url = "${ctx}/admin/chgpwd.html";
    			$.ajax({
    				async : true,
    				data : param,
    				type : "post",
    				dataType : 'json',
    				url : url,
    				cache : false,
    				success : function(object) {
    					if (object.result == 'ok') {
    						$.messager.alert("提示","操作成功");
    					}else{
    						$.messager.alert("提示","操作失败："+object.message);
    					}
    				},
    				error : function(XMLHttpRequest, textStatus, errorThrown) {
    					var text = XMLHttpRequest.responseText;
    					if(!isEmpty(text)) {
    						$.messager.alert("提示",text);
    					} else {
    						$.messager.alert("提示","系统调用出现错误，请联系系统管理员进行处理");
    					}
    				}
    			});
        	}
        }
        function clearForm(){
            $('form').form('clear');
        }
	</script>
</body>
</html>