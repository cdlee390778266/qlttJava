<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@ include file="../common/jstlres.jsp"%>
<html>
<head>
<title>指标管理</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<%@ include file="../common/uires.jsp"%>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div
		data-options="region:'west',split:true,title:'原生指标',collapsible:false"
		style="width:45%; padding: 10px;">
		<div class="easyui-panel" style="width:95%;padding: 5px;margin:5px;" data-options="border:false">
			<input id="sch-fsTactic" name="sch-fsTactic" class="easyui-textbox" style="width: 160px" data-options="label:'指标：',labelWidth:60,labelAlign:'right'">
			<input id="sch-fiType" name="sch-fsTactic" class="easyui-textbox" style="width: 200px" data-options="label:'指标类型：',labelWidth:100,labelAlign:'right'">
			<input id="sch-fsName" name="sch-fsName" class="easyui-textbox" style="width: 200px" data-options="label:'指标名称：',labelWidth:100,labelAlign:'right'">
			<a id="zb-query" onclick="javascript:handleZbQuery()" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
		</div>
		<div id="zb-toolbar" style="padding: 4px 5px;">
			<a  id="zb-add"    onclick="javascript:handleZbAdd()"  style="margin-left:20px;margin-right:10px;" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
			<a  id="zb-edit"   onclick="javascript:editZb()"       style="margin-right:10px;" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',disabled:true">修改</a>
			<a  id="zb-delete" onclick="javascript:deleteZb()"     style="margin-right:10px;" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',disabled:true">删除</a>
			<a  id="zb-schcs" onclick="javascript:searchZBCS()"       href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',disabled:true">查看指标参数</a>
		</div>
		<table class="easyui-datagrid" id="tb-zb" style="min-height:300px;"
			data-options="rownumbers:true,singleSelect:true,autoRowHeight:true,fitColumns:true">
			<thead>
				<tr>
					<th data-options="field:'fsTactic',width:55">指标</th>
					<th data-options="field:'fsName',width:80">指标名称</th>
					<th data-options="field:'fiType',width:30">指标类型</th>
					<th data-options="field:'fsDetail',width:80">指标描述</th>
				</tr>
			</thead>
		</table>
	</div>
	
	<div data-options="region:'center',split:true,title:'原生指标参数',collapsible:false" style="width:54%; padding:10px;">
		<div class="easyui-panel" style="width:95%;padding: 5px;margin:5px;" data-options="border:false">
			<form id="sch-cs-form">
				<input id="sch-cs-fsTactic" name="sch-cs-fsTactic" type="hidden"/>
				<input id="sch-cs-fiTacticprm" name="sch-cs-fiTacticprm"   class="easyui-textbox" 
					style="width: 200px" data-options="label:'指标参数：',labelWidth:100,labelAlign:'right'">
				<input id="sch-cs-fsTacprmname" name="sch-cs-fsTacprmname" class="easyui-textbox" 
					style="width: 200px" data-options="label:'指标参数名称：',labelWidth:100,labelAlign:'right'">
				<a id="zbcs-query" onclick="javascript:loadZBCSData()" href="#" 
					class="easyui-linkbutton" data-options="iconCls:'icon-search',disabled:true">查询</a>
			</form>
		</div>
		  
		<div id="zbcs-toolbar" style="padding: 4px 5px;">
			<a id="zbcs-add"    onclick="javascript:handleZBCSAdd()"  style="margin-left:20px;margin-right:10px;" href="#" 
				class="easyui-linkbutton" data-options="iconCls:'icon-add',disabled:true">新增</a>
			<a id="zbcs-edit"   onclick="javascript:editZBCS()"       style="margin-right:10px;" href="#" 
				class="easyui-linkbutton" data-options="iconCls:'icon-edit',disabled:true">修改</a>
			<a id="zbcs-delete" onclick="javascript:deleteZBCS()"     style="margin-right:10px;" href="#" 
				class="easyui-linkbutton" data-options="iconCls:'icon-remove',disabled:true">删除</a>
		</div>
		
		<div class="easyui-panel" style="padding: 5px;"
			data-options="border:false">
			<table class="easyui-datagrid" id="tb-zbcs" style="min-height:300px;"
				data-options="rownumbers:true,singleSelect:true,autoRowHeight:true,fitColumns:true">
				<thead>
					<tr>
						<th data-options="field:'fsTactic',width:80">指标</th>
						<th data-options="field:'fiTacticprm',width:80">指标参数</th>
						<th data-options="field:'fsTacprmname',width:120">指标参数名称</th>
						<th data-options="field:'fiMaxdelay',width:120">最大延迟指数</th>
						<th data-options="field:'fsDetail',width:150">指标描述</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	
	<!-- 指标编辑弹出框 -->
	<div id="zbEditWindow" class="easyui-window" title="原生指标维护" data-options="modal:true,closed:true,iconCls:'icon-save'" 
		style="width:500px; height:300px; padding: 10px;">
		<form id="zbEditForm" method="post" style="margin:30px;width:300px;">
			<input type="hidden" id="zboper" name="zboper" value="A">
			<div style="margin-bottom: 10px">
				<input class="easyui-textbox" iconWidth="20"  id="fsTactic"
					style="width: 100%" name="fsTactic"
					data-options="label:'指标:',required:true,missingMessage:'不能为空',validType:'maxZHLength[16]'">
			</div>
			
			<div style="margin-bottom: 10px">
				<input class="easyui-textbox" iconWidth="20"  id="fiType"
					style="width: 100%" name="fiType"
					data-options="label:'指标类型:',required:true,missingMessage:'不能为空'" validType='integer'/>
			</div>
			<div style="margin-bottom: 10px">
				<input class="easyui-textbox" iconWidth="20" id="fsName"
					style="width: 100%" name="fsName"
					data-options="label:'指标名称:',required:true,missingMessage:'不能为空',validType:'maxZHLength[64]'">
			</div>
			<div style="margin-bottom: 10px">
				<input class="easyui-textbox" iconWidth="20" id="fsDetail"
					style="width: 100%" name="fsDetail"
					data-options="label:'指标描述:',missingMessage:'不能为空',validType:'maxZHLength[2000]'">
			</div>
		</form>
		<div style="text-align: center; padding: 5px 0;">
			<a id="zb-submit" href="javascript:void(0);" class="easyui-linkbutton" onclick="submitZbEditForm();" data-options="iconCls:'icon-ok'" style="width: 80px">提交</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="cleanZbEditForm();"  data-options="iconCls:'icon-reload'"  style="width: 80px">重置</a>
		</div>
	</div>
	
	
	<!-- 指标参数编辑弹出框 -->
	<div id="zbcsEditWindow" class="easyui-window" title="原生指标参数维护" data-options="modal:true,closed:true,iconCls:'icon-save'" 
		style="width:500px; height:350px; padding: 10px;">
		<form id="zbcsEditForm" method="post" style="margin:30px;width:300px;">
			<input type="hidden" id="zboper" name="zboper" value="A">
			<div style="margin-bottom: 10px">
				<input class="easyui-textbox" iconWidth="20"  id="fsTactic"
					style="width: 100%" name="fsTactic"
					data-options="label:'指标:',required:true,missingMessage:'不能为空',validType:'maxZHLength[16]',disabled:true">
			</div>
			
			<div style="margin-bottom: 10px">
				<input class="easyui-textbox" iconWidth="20"  id="fiTacticprm"
					style="width: 100%" name="fiTacticprm"
					data-options="label:'指标参数:',required:true,missingMessage:'不能为空'" validType='integer'>
			</div>
			
			<div style="margin-bottom: 10px">
				<input class="easyui-textbox" iconWidth="20" id="fsTacprmname"
					style="width: 100%" name="fsTacprmname"
					data-options="label:'参数名称:',validType:'maxZHLength[64]'">
			</div>
			
			<div style="margin-bottom: 10px">
				<input class="easyui-textbox" iconWidth="20"  id="fiMaxdelay"
					style="width: 100%" name="fiMaxdelay"
					data-options="label:'最大延迟指数:',required:true,missingMessage:'不能为空'" validType='integer'>
			</div>
			<div style="margin-bottom: 10px">
				<input class="easyui-textbox" iconWidth="20" id="fsDetail"
					style="width: 100%" name="fsDetail"
					data-options="label:'指标参数描述:',validType:'maxZHLength[2000]'">
			</div>
		</form>
		<div style="text-align: center; padding: 5px 0;">
			<a id="zbcs-submit" href="javascript:void(0);" class="easyui-linkbutton" onclick="submitZBCSEditForm();" data-options="iconCls:'icon-ok'" style="width: 80px">提交</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="cleanZbEditForm();"  data-options="iconCls:'icon-reload'"  style="width: 80px">重置</a>
		</div>
	</div>

	<script type="text/javascript">
		$.parser.parse();

		$(function(){
			var param =  queryParams();
			$("#tb-zb").datagrid({
				url : 'list.html',
				pagination : true,
				fitColumns : true,
				method : 'POST',
				idField : 'fsTactic',
				singleSelect : true,
				toolbar : '#zb-toolbar',
				queryParams: param,
				onClickRow:function(node){
					manageZBBtn("enable");//所有按钮置为能操作
					searchZBCS();//查询指标参数
				},
				onLoadSuccess:function(){
					$("#tb-zb").datagrid("unselectAll");//加载成功后取消被选中
				}
			});
			
			$("#tb-zbcs").datagrid({
				pagination : false,
				fitColumns : true,
				method : 'POST',
				idField : 'fsTactic',
				singleSelect : true,
				toolbar : '#zbcs-toolbar',
				data:[],
				onClickRow:function(node){
					manageZBCSBtn("enable");
				},
				onLoadSuccess:function(){
					$("#tb-zbcs").datagrid("unselectAll");//加载成功后取消被选中
				}
			}); 
		})
		//指标按钮组
		function manageZBBtn(oper){
			$("#zb-edit").linkbutton(oper);
			$("#zb-delete").linkbutton(oper);
			$("#zb-schcs").linkbutton(oper);
		}
		
		//拼装指标查询的参数
		function queryParams(){
			var param = {};
			param.fsTactic = $("#sch-fsTactic").textbox("getValue");
			param.fiType = $("#sch-fiType").textbox("getValue");
			param.fsName = $("#sch-fsName").textbox("getValue");
			return param;
		}
		
		/*
		 * 处理原生指标新增
		 */
		function handleZbAdd(){
			cleanZbEditForm();//清空原生指标编辑Form
			$("#zbEditForm #zboper").val("ADD");
			$('#zbEditWindow').window('open');
		}
		
		/*
		 * 处理原生指标查询
		 */
		function handleZbQuery(){
			manageZBBtn("disable")
			var param = queryParams(); 
			$("#tb-zb").datagrid("load",param);
			cleanCenterPanel();//清空指标参数面板
		}
		
		function reloadZbQuery(){
			manageZBBtn("disable")
			var param = queryParams(); 
			$("#tb-zb").datagrid("reload");
			cleanCenterPanel();//清空指标参数面板
		}
		
	   /*
		* 处理原生指标修改
		*/
		function editZb(){
		   var row = $("#tb-zb").datagrid("getSelected");
		   if(row){
			    cleanZbEditForm();//清空原生指标编辑Form
				$("#zbEditForm #zboper").val("EDIT");
				$("#zbEditForm #fsTactic").textbox("disable");
			    $("#zbEditForm #fsTactic").textbox("setValue",row.fsTactic);
			    $("#zbEditForm #fiType").textbox("setValue",row.fiType);
			    $("#zbEditForm #fsName").textbox("setValue",row.fsName);
			    $("#zbEditForm #fsDetail").textbox("setValue",row.fsDetail);
				$('#zbEditWindow').window('open');
		   }else{
			   $.messager.alert("提示","请选择一个原生指标");
		   }
	   }
	   
		/*
		* 处理原生指标删除
		*/
		function deleteZb(){
		   var row = $("#tb-zb").datagrid("getSelected");
		   if(row){
			   $.messager.confirm({
					title: '原生指标删除确认',
					msg: '你确认要删除该指标吗?',
					fn: function(r){
						if(r){
							var url = "editProtactic.html";
							var param = {}
							param.fsTactic = row.fsTactic;
							param.zboper = "DELETE";
							$.ajax({
			    				async : true,
			    				data : param,
			    				type : "post",
			    				dataType : 'json',
			    				url : url,
			    				cache : false,
			    				success : function(object) {
			    					if(object.result == 'ok') {
			    						$.messager.alert("操作成功","删除原生指标成功");
			    						reloadZbQuery();
			    					}else{
			    						$.messager.alert("操作失败","删除原生指标失败："+object.message);
			    					}
			    				}
							});
						}
					}
				});
		   }else{
			   $.messager.alert("提示","请选择一个原生指标");
		   }
	   }
		
		/*
		 * 查询原生指标参数
		 */
		function searchZBCS(){
			 var row = $("#tb-zb").datagrid("getSelected");
			 if(row){
			   cleanCenterPanel();//清空右边原生指标参数
			   //指标input
			   $("#sch-cs-fsTactic").val(row.fsTactic);
			  //按钮
			  $("#zbcs-query").linkbutton("enable");
			  $("#zbcs-add").linkbutton("enable");
			  //加载表单
			  loadZBCSData();
		   }else{
			   $.messager.alert("提示","请选择一个原生指标");
		   }
		}
		
		//清空指标的编辑表单
		function cleanZbEditForm(){
			var oper = $("#zbEditForm #zboper").val();
			$("#zbEditForm").form("clear");
			$("#zbEditForm #zboper").val(oper);
		    $("#zbEditForm #fsTactic").textbox("enable");
		} 
		
		//提交指标
		function submitZbEditForm(){
			if($("#zbEditForm").form('validate')){
				var url = "editProtactic.html";
				var param = {};
				param.zboper = $("#zbEditForm #zboper").val();
				param.fsTactic = $("#zbEditForm #fsTactic").textbox("getValue");
				param.fiType = $("#zbEditForm #fiType").textbox("getValue");
				param.fsName = $("#zbEditForm #fsName").textbox("getValue");
				param.fsDetail = $("#zbEditForm #fsDetail").textbox("getValue");
				$("#zb-submit").linkbutton('disable');//禁用提交按钮
				$.ajax({
    				async : true,
    				data : param,
    				type : "post",
    				dataType : 'json',
    				url : url,
    				cache : false,
    				success : function(object) {
    					$("#zb-submit").linkbutton('enable');//启用提交按钮
    					if (object.result == 'ok') {
    						$('#zbEditWindow').window('close');
    						$.messager.alert("提示","操作成功");
    						reloadZbQuery();
    					}else{
    						$.messager.alert("提示","操作失败："+object.message);
    					}
    				},
    				error : function(XMLHttpRequest, textStatus, errorThrown) {
    					$("#zb-submit").linkbutton('enable');//启用提交按钮
    					var text = XMLHttpRequest.responseText;
    					if(!isEmpty(text)) {
    						$.messager.alert("提示",text);
    					} else {
    						$.messager.alert("提示","系统调用出现错误，请联系系统管理员进行处理");
    					}
    				}
    			});
			}
			return;
		}
		//---------------------------------原生指标---------------------------------------------
		//指标参数按钮组
		function manageZBCSBtn(oper){
			$("#zbcs-query").linkbutton(oper);
			$("#zbcs-add").linkbutton(oper);
			$("#zbcs-edit").linkbutton(oper);
			$("#zbcs-delete").linkbutton(oper);
		}
		
		//清空原生参数panel
		function cleanCenterPanel(){
			//查询参数置空
			$("#sch-cs-form").form("clear");
			//按钮置为不能操作
			manageZBCSBtn("disable");
			//table清空
			$("#tb-zbcs").datagrid({
				url:null,
				data:{
						result:"ok",
						data:[]
					}
			}); 
		}
		
		/*
		 *加载指标参数Data
		 */
		function  loadZBCSData(){
			var param = {};
			param.fsTactic = $("#sch-cs-fsTactic").val();
			param.fiTacticprm = $("#sch-cs-fiTacticprm").textbox("getValue");
			param.fsTacprmname = $("#sch-cs-fsTacprmname").textbox("getValue");
			$("#tb-zbcs").datagrid({
				url:"cslist.html",
				queryParams:param,
				onClickRow:function(node){
					manageZBCSBtn("enable");
				}
			});
		} 
		
		//清空指标参数的编辑表单
		function cleanZbCSEditForm(){
			$("#zbcsEditForm").form("clear");
			$("#zbcsEditForm #fiTacticprm").textbox("enable");
		} 
		
		/*
		 * 处理原生指标参数新增
		 */
		function handleZBCSAdd(){
			cleanZbCSEditForm();//清空原生指标编辑Form
			$("#zbcsEditForm #fsTactic").textbox("setValue",$("#sch-cs-fsTactic").val());
			$("#zbcsEditForm #zboper").val("ADD");
			$('#zbcsEditWindow').window('open');
		}
		
		function submitZBCSEditForm(){
			if($("#zbcsEditForm").form('validate')){
				var url = "editProtacprm.html";
				var param = {};
				param.oper = $("#zbcsEditForm #zboper").val();
				param.fsTactic = $("#zbcsEditForm #fsTactic").textbox("getValue");
				param.fiTacticprm = $("#zbcsEditForm #fiTacticprm").textbox("getValue");
				param.fsTacprmname = $("#zbcsEditForm #fsTacprmname").textbox("getValue");
				param.fiMaxdelay = $("#zbcsEditForm #fiMaxdelay").textbox("getValue");
				param.fsDetail = $("#zbcsEditForm #fsDetail").textbox("getValue");
				$("#zbcs-submit").linkbutton('disable');//禁用提交按钮
				$.ajax({
    				async : true,
    				data : param,
    				type : "post",
    				dataType : 'json',
    				url : url,
    				cache : false,
    				success : function(object) {
    					$("#zbcs-submit").linkbutton('enable');//启用提交按钮
    					if (object.result == 'ok') {
    						$('#zbcsEditWindow').window('close');
    						$.messager.alert("提示","操作成功");
    						loadZBCSData();
    					}else{
    						$.messager.alert("提示","操作失败："+object.message);
    					}
    				},
    				error : function(XMLHttpRequest, textStatus, errorThrown) {
    					$("#zbcs-submit").linkbutton('enable');//启用提交按钮
    					var text = XMLHttpRequest.responseText;
    					if(!isEmpty(text)) {
    						$.messager.alert("提示",text);
    					} else {
    						$.messager.alert("提示","系统调用出现错误，请联系系统管理员进行处理");
    					}
    				}
    			});
			}
			return;
		}
		
		//修改原生指标
		function editZBCS(){
			 var row = $("#tb-zbcs").datagrid("getSelected");
			   if(row){
				   cleanZbCSEditForm();//清空原生指标编辑Form
				   $("#zbcsEditForm #zboper").val("EDIT");
				   $("#zbcsEditForm #fsTactic").textbox("setValue",row.fsTactic);
				   $("#zbcsEditForm #fsTactic").textbox("disable");
				   $("#zbcsEditForm #fiTacticprm").textbox("setValue",row.fiTacticprm);
				   $("#zbcsEditForm #fiTacticprm").textbox("disable");
				   $("#zbcsEditForm #fsTacprmname").textbox("setValue",row.fsTacprmname);
				   $("#zbcsEditForm #fiMaxdelay").textbox("setValue",row.fiMaxdelay);
				   $("#zbcsEditForm #fsDetail").textbox("setValue",row.fsDetail);
				   $('#zbcsEditWindow').window('open');
			   }else{
				   $.messager.alert("提示","请选择一个原生指标参数");
			   }
		}
		
		function deleteZBCS(){
			var row = $("#tb-zbcs").datagrid("getSelected");
			   if(row){
				   $.messager.confirm({
						title: '原生指标参数删除确认',
						msg: '你确认要删除该指标参数吗?',
						fn: function(r){
							if(r){
								var url = "editProtacprm.html";
								var param = {}
								param.fsTactic = row.fsTactic;
								param.fiTacticprm = row.fiTacticprm;
								param.oper = "DELETE";
								$.ajax({
				    				async : true,
				    				data : param,
				    				type : "post",
				    				dataType : 'json',
				    				url : url,
				    				cache : false,
				    				success : function(object) {
				    					if (object.result == 'ok') {
				    						$.messager.alert("提示","删除原生指标参数成功");
				    						loadZBCSData();
				    					}else{
				    						$.messager.alert("提示","删除原生指标成功："+object.message);
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
					});
			   }else{
				   $.messager.alert("提示","请选择一个原生指标参数");
			   }
		}
	</script>
</body>
</html>