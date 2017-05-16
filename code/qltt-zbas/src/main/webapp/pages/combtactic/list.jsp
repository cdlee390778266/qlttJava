<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@ include file="../common/jstlres.jsp"%>
<html>
<head>
<title>自定义（组合）指标管理</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<%@ include file="../common/uires.jsp"%>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div
		data-options="region:'west',split:true,title:'自定义（组合）指标',collapsible:false"
		style="width:45%; padding: 10px;">
		<div class="easyui-panel" style="width:95%;padding: 5px;margin:5px;" data-options="border:false">
			<input id="sch-fsTactic"    class="easyui-textbox" style="width: 160px" data-options="label:'指标：',labelWidth:60,labelAlign:'right'">
			<input id="sch-fiMaxdelay"  class="easyui-textbox" style="width: 200px" data-options="label:'最大延迟指数：',labelWidth:100,labelAlign:'right'">
			<!-- <input id="sch-fsChkcode"   class="easyui-textbox" style="width: 200px" data-options="label:'校验码：',labelWidth:100,labelAlign:'right'"> -->
			<a id="zb-query" onclick="javascript:handleZbQuery()" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
		</div>
		<table class="easyui-datagrid" id="tb-zb"
			data-options="rownumbers:true,singleSelect:true,autoRowHeight:true,fitColumns:true">
			<thead>
				<tr>
					<th data-options="field:'fsTactic',width:100">指标</th>
					<th data-options="field:'fiMaxdelay',width:100">最大延迟指数</th>
					<th data-options="field:'fsChkcode',width:100">校验码</th>
				</tr>
			</thead>
		</table>
	</div>
	
	<div data-options="region:'center',split:true,title:'自定义（组合）指标参数',collapsible:false" style="width:54%; padding:10px;">
		<div class="easyui-panel" style="width:95%;padding: 5px;margin:5px;" data-options="border:false">
			<form id="sch-cs-form">
				<input id="sch-cs-fsTactic" name="sch-cs-fsTactic" type="hidden"/>
				<input id="sch-cs-fsSrctactic" name="sch-cs-fiTacticprm"   class="easyui-textbox" 
					style="width: 200px" data-options="label:'原生指标：',labelWidth:100,labelAlign:'right'">
				<input id="sch-cs-fiSrctacticprm" name="sch-cs-fsTacprmname" class="easyui-textbox" 
					style="width: 200px" data-options="label:'原生指标参数：',labelWidth:100,labelAlign:'right'">
				<a id="zbcs-query" onclick="javascript:loadZBCSData()" href="#" 
					class="easyui-linkbutton" data-options="iconCls:'icon-search',disabled:true">查询</a>
			</form>
		</div>
		<div class="easyui-panel" style="padding: 5px;"
			data-options="border:false">
			<table class="easyui-datagrid" id="tb-zbcs" style="height:400px;"
				data-options="rownumbers:true,singleSelect:true,autoRowHeight:true,fitColumns:true">
				<thead>
					<tr>
						<th data-options="field:'fsTactic',width:80">指标</th>
						<th data-options="field:'fsSrctactic',width:80">原生指标</th>
						<th data-options="field:'fiSrctacticprm',width:120">原生指标参数</th>
					</tr>
				</thead>
			</table>
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
				queryParams: param,
				onClickRow:function(node){
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
		
		//拼装指标查询的参数
		function queryParams(){
			var param = {};
			param.fsTactic   =  $("#sch-fsTactic").textbox("getValue");
			param.fiMaxdelay =  $("#sch-fiMaxdelay").textbox("getValue");
			//param.fsChkcode  =  $("#sch-fsChkcode").textbox("getValue");
			return param;
		}
		
		/*
		 * 处理原生指标查询
		 */
		function handleZbQuery(){
			var param = queryParams(); 
			console.log(param);
			$("#tb-zb").datagrid("load",param);
			cleanCenterPanel();//清空指标参数面板
		}
		
		//---------------------------------------------------------------------------
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
			  //加载表单
			  loadZBCSData();
		   }else{
			   $.messager.alert("提示","请选择一个原生指标");
		   }
		}
		//清空原生参数panel
		function cleanCenterPanel(){
			//查询参数置空
			$("#sch-cs-form").form("clear");
			$("#zbcs-query").linkbutton("disable");
			//table清空
			$("#tb-zbcs").datagrid({
				url:null,
				data:[]
			}); 
		}
		
		/*
		 *加载指标参数Data
		 */
		function  loadZBCSData(){
			var param = {};
			param.fsTactic = $("#sch-cs-fsTactic").val();
			param.fsSrctactic = $("#sch-cs-fsSrctactic").textbox("getValue");
			param.fiSrctacticprm = $("#sch-cs-fiSrctacticprm").textbox("getValue");
			console.log("指标参数查询参数：");
			console.log(param);
			$("#tb-zbcs").datagrid({
				url:"cslist.html",
				queryParams:param
			});
		} 
	</script>
</body>
</html>