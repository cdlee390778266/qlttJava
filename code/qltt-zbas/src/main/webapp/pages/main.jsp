<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.qianlong.qltt.zbas.entity.Admin"%>
<%@page import="com.qianlong.qltt.zbas.common.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@ include file="common/jstlres.jsp"%>
<html>
<head>
<title>主页面</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<%@ include file="common/uires.jsp"%>
</head>
<body class="easyui-layout" >
	<div data-options="region:'north',title:'',border:false" style="height:80px;background:#B3DFDA;padding:10px">
		<div style="margin-right:30px;text-align:right;line-height:20px;font-size: 12px;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-no',border:false" href="${ctx}/logout.html">退出</a>
		</div>
		<div style="display: block;margin-right:30px;text-align:right;line-height:20px;font-size: 12px;">欢迎您,
		<%=((Admin)request.getSession().getAttribute(Constants.ADMINISTRATOR)).getUsername()%></div>
		
	</div>
	<div data-options="region:'west',split:true,title:'菜单'" style="width:200px;padding:10px;">
		<ul id="menuTree" class="easyui-tree"></ul>
	</div>
	<div data-options="region:'center',title:''">
		<div id="main" class="easyui-tabs"  data-options="fit:true">
			<div title="About" style="padding:5px;"></div>
		</div>
	</div>
	<script type="text/javascript">
	(function() {
		$('#menuTree').tree({
			url: 'menutree.html',
			method: 'get',
			animate: true,
			onClick: function(node) {
				if(!node.isLeaveMenu){
					return ;
				}
				var isExist = $('#main').tabs('exists',node.text);
				if(isExist){
					$('#main').tabs('select',node.text);
				}else{
					$('#main').tabs('add', {
						id:node.id,
						title: node.text,
						content: '<iframe scrolling="auto" frameborder="0" style="100%;height:100%;" src="${ctx}' + node.url + '"  width="100%"></iframe>',
						closable: true
					});
				}
			}
		});
	})();
	</script>
</body>
</html>
