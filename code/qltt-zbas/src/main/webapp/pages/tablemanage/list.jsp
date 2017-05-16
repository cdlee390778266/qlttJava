<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@ include file="../common/jstlres.jsp"%>
<html>
<head>
<title>数据库表复制</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<%@ include file="../common/uires.jsp"%>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<!--源数据库 开始-->
		<div
			data-options="region:'west',split:false,collapsible:false,border:false"
			style="width:44%; padding: 10px;">
			<div class="easyui-layout" data-options="fit:true">
				<div style="height:250px;"  data-options="region:'north',split:false,border:false" ></div>
				<div data-options="region:'center',border:false" >
					<table class="easyui-datagrid" id="tb-source" style="height:500px;"
						data-options="rownumbers:true,singleSelect:false,autoRowHeight:true,fitColumns:true,title:'源数据库'">
						<thead>
							<tr>
								<th data-options="field:''" checkbox="true"></th>
								<th data-options="field:'tableName',width:120">表名</th>
								<th data-options="field:'remarks',width:120">备注</th>
								<th data-options="field:'recordNum',width:120">记录总数</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
		<!--源数据库 结束-->
		
		<div data-options="region:'center',split:false,collapsible:false,border:false" 
			style="width:6%; padding: 10px;">
			<div class="easyui-layout" data-options="fit:true">
				<div style="height:250px;"  data-options="region:'north',split:false,border:false"></div>
				<div style="height: 600px;" data-options="region:'center',border:false">
					<a class="easyui-linkbutton" 
						data-options="iconCls:'icon-redo'" 
						href="javascript:void(0)" 
						onclick="javascript:copy();" style="width:100px;margin-top:200px;margin-left:20px;">复制</a>
				</div>
			</div>
		</div>
	
		<!--目标数据库 开始-->
		<div data-options="region:'east',split:false,collapsible:false,border:false"
			style="width: 44%; padding: 10px;">
			<div class="easyui-layout" data-options="fit:true">
				<div style="height:250px;"  data-options="region:'north',split:false,border:false">
					<div class="easyui-panel" data-options="title:'目标数据库连接参数'">
						<form id="tg-db-form" style="width:60%;margin:auto">
							<div style="margin:5px">
								<input class="easyui-textbox" style="width:70%" id ="host"  
									data-options="label:'主机名:',labelAlign:'right',required:true,missingMessage:'不能为空',prompt:'数据库所在主机的ip或域名'" value="127.0.0.1">
							</div>
							<div style="margin:5px">
								<input class="easyui-textbox" style="width:70%" id ="port"  
									data-options="label:'端口:',labelAlign:'right',required:true,missingMessage:'不能为空'" value="3306">
							</div>
							<div style="margin:5px">
								<input class="easyui-textbox" style="width:70%" id ="database"  
									data-options="label:'数据库名称:',labelAlign:'right',required:true,missingMessage:'不能为空'" value="test">
							</div>
							<div style="margin:5px">
								<input class="easyui-textbox" style="width:70%" id ="username"  
									data-options="label:'用户名:',labelAlign:'right',required:true,missingMessage:'不能为空'" value="root">
							</div>
							<div style="margin:5px">
								<input class="easyui-passwordbox" style="width:70%" id ="password"  
									data-options="label:'密码:',labelAlign:'right', required:true,missingMessage:'不能为空'" value="cdyf2008">
							</div>
						</form>
						<div style="text-align: center; padding: 5px 0">
							<a href="#" class="easyui-linkbutton" onclick="queryTargetDBTables();" data-options="iconCls:'icon-search'"   style="width: 80px">查询</a>
							<a href="#" class="easyui-linkbutton" onclick="clearQueryForm();" data-options="iconCls:'icon-reload'" style="width: 80px">重置</a>
						</div>
					</div>
				</div>
				
				<div data-options="region:'center',border:false">
					<table class="easyui-datagrid" id="tb-target" style="height:500px;"
						data-options="singleSelect:false,autoRowHeight:true,fitColumns:true,rownumbers:true,idField:'tableName',title:'目标数据库'">
						<thead>
							<tr>
								<th data-options="field:'tableName',width:120">表名</th>
								<th data-options="field:'remarks',width:120">备注</th>
								<th data-options="field:'recordNum',width:120">记录总数</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!--目标数据库 结束-->

	<script type="text/javascript">
		$.parser.parse();

		$(function() {
			/**
			 * 加载源数据库表信息
			 */
			$("#tb-source").datagrid({
				url : 'srcdbtables.html',
				pagination : false,
				method : 'GET',
				idField : 'tableName'
			});

			/**
			 * 加载目标数据库表信息
			 */
			$("#tb-target").datagrid({
				idField : 'tableName',
				data:[],
				emptyMsg : '没有数据！'
			});
		})
		
		/**
		 * 封装目标数据库连接参数
		 */
		function queryData(){
			var param = {};
			param.host = $("#host").textbox("getValue");
			param.port = $("#port").textbox("getValue");
			param.database = $("#database").textbox("getValue");
			param.username = $("#username").textbox("getValue");
			param.password = $("#password").textbox("getValue");
			return param;
		}
		
		/*
		 * 加载目标数据库表信息
		 */
		function queryTargetDBTables(){
			if($("#tg-db-form").form('validate')){
				var param = queryData();
				$("#tb-target").datagrid({
					url : 'tgtdbtables.html',
					queryParams:param
				});
        	}
		}
		
		/**
		 * 清空目标数据库查询参数列表
		 */
		function clearQueryForm(){
			$("#tg-db-form").form('clear')
		}
		
		/**
		 * 将表从源数据库拷贝到目标数据库，包括表结构和数据
		 */
		function copy(){
			if($("#tg-db-form").form('validate')){
				var tables = $("#tb-source").datagrid("getSelections");
				if(tables.length != 0){
					var param = queryData();//数据库的连接参数
					var tablenames = new Array();
					for(var i=0;i<tables.length ;i++){
						tablenames.push(tables[i].tableName);
					}
					param.tablenames=tablenames;
					$.messager.confirm({
						title: '表复制确认',
						msg: '您确认要将选中的表复制到目标数据库中吗',
						fn: function(r){
							if(r){
								console.log(param);
								var url = "copytables.html"
								$.ajax({
									async : true,
									data : param,
									type : "post",
									dataType : 'json',
									url : url,
									cache : false,
									success : function(object) {
										if (object.result == 'ok'){
											queryTargetDBTables();//从新加载目标数据库的数据
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
											$.messager.alert("提示","系统错误...");
										}
									} 
								}); 
							}
						}
					});
				}else{
					$.messager.alert("提示","请选择需要复制的表");
				}
        	}else{
        		$.messager.alert("提示","请填写目标数据库的相关参数");
        	}
		}
	</script>
</body>
</html>