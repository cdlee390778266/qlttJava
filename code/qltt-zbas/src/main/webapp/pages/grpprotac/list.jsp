<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@ include file="../common/jstlres.jsp"%>
<html>
<head>
<title>指标分组管理</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<%@ include file="../common/uires.jsp"%>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'west',split:true,title:'原生指标组',collapsible:false" style="width:55%;padding:10px;">
		<div id="grp-toolbar" style="padding:8px;" >
			<a id="grp-add" style="margin-left:10px;" onclick="javascript:addTaggrp()" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" >新增</a>
			<a id="grp-edit"   onclick="javascript:editTaggrp()"       href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',disabled:true">编辑</a>
			<a id="grp-cancel"  onclick="javascript:deleteTaggrp()" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',disabled:true">删除</a>
			<a id="grp-un-select"  onclick="javascript:unselectTaggrp()" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">取消选中</a>
		</div>
		 <table class="easyui-treegrid" style="width:100%;padding:5px;" id="grp-tb"
            data-options="fitColumns:true,method: 'get',idField:'fsTacgroup',treeField:'fsName',rownumbers:true">
	        <thead>
	            <tr>
	                <th data-options="field:'fsTacgroup'" width="100">指标组编号</th>
	                <th data-options="field:'fsName'" width="200">指标组名称</th>
	                <th data-options="field:'fiLevel'" width="120">指标组层级</th>
	                <th data-options="field:'fsDetail'" width="400">指标组描述</th>
	                <th data-options="field:'fsPtacgroup',hidden:true" width="150">父级指标组</th>
	            </tr>
	        </thead>
    	</table>
	</div>
	<div data-options="region:'center',split:true,title:'原生指标组详细信息'" style="width:43%;padding:10px;">
		<div class="easyui-panel" style="padding:5px;" data-options="border:false">
			<form id="grp-form">
				<table cellspacing="5px" style="width:100%">
					<tr>
						<td>
							<label for="fsTacgroup">指标组编号:</label>
						</td>
						<td>
							<input class="easyui-textbox" id="fsTacgroup" 
								data-options="disabled:true"/>
						</td>
						<td>
							<label for="fsName">指标组名称:</label>
						</td>
						<td>
							<input class="easyui-textbox" id="fsName" 
								data-options="disabled:true" />
						</td>
					</tr>
					<tr>
						<td>
							<label for="fsPtacgroup">父级指标组:</label>
						</td>
						<td>
							<input class="easyui-textbox" id="fsPtacgroup" 
								data-options="disabled:true"/>
						</td>
						<td>
							<label for="fiLevel">指标组层级:</label>
						</td>
						<td>
							<input class="easyui-textbox"   id="fiLevel" 
								data-options="disabled:true"/>
						</td>
					</tr>
					<tr>
						<td><label for="fsDetail">指标组描述:</label></td>
						<td colspan="3">
							<input class="easyui-textbox"  id="fsDetail" width="300px"
								data-options="disabled:true" />
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<table class="easyui-datagrid" id="mem-tb" title="原生指标组指标成员" style="width:90%; height:300px;"  
								data-options="singleSelect:true,autoRowHeight:true,fitColumns:true">  
								 <thead>
							        <tr>
							            <th data-options="field:'fsTacgroup',width:90">指标组编号</th>
							            <th data-options="field:'fiIndex',width:80">顺序号</th>
							            <th data-options="field:'fsTactic',width:90">指标</th>
							            <th data-options="field:'fsName',width:150">指标名称</th>
							        </tr>
							    </thead>
							</table>
						</div>
					</tr>
				</table>
			</form>
		</div>	
	</div>
	
	<div id="Window" class="easyui-window" title="原生指标参数维护" data-options="modal:true,closed:true,iconCls:'icon-edit'" 
		style="width:800px; height:550px; padding: 10px;">
		<div class="easyui-panel" style="padding:5px;" data-options="border:false">
			<form id="grp-edit-form">
				<input type="hidden" id="oper">
				<table cellspacing="5px" style="width:100%">
					<tr>
						<td>
							<label for="fsTacgroup">指标组编号:</label>
						</td>
						<td>
							<input class="easyui-textbox" id="fsTacgroup" 
								data-options="required:true,missingMessage:'不能为空',validType:'maxZHLength[16]'"/>
						</td>
						<td>
							<label for="fsName">指标组名称:</label>
						</td>
						<td>
							<input class="easyui-textbox" id="fsName" 
								data-options="required:true,missingMessage:'不能为空',validType:'maxZHLength[64]'" />
						</td>
					</tr>
					<tr>
						<td>
							<label for="fsPtacgroup">父级指标组:</label>
						</td>
						<td>
							<input class="easyui-textbox" id="fsPtacgroupName" 
								data-options="disabled:true"/>
							<input type="hidden" id="fsPtacgroup">
						</td>
						<td>
							<label for="fiLevel">指标组层级:</label>
						</td>
						<td>
							<input class="easyui-textbox"   id="fiLevel" 
								data-options="disabled:true"/>
						</td>
					</tr>
					<tr>
						<td><label for="fsDetail">指标组描述:</label></td>
						<td colspan="3">
							<input class="easyui-textbox"  id="fsDetail" width="300px"
								data-options="validType:'maxZHLength[2000]'" />
						</td>
					</tr>
				</table>
			</form>
		</div>	
		<div class="easyui-layout" style="dispaly:block;width:745px;height:310px;">
			<div data-options="region:'west',split:false,border:false" style="width:44%">
				<table class="easyui-datagrid" id="edit-mem-unselect" title="未选择原生指标" style="width:98%;height:300px;"  
					data-options="singleSelect:false,autoRowHeight:true,fitColumns:true,rownumbers:true,idField:'fsTactic'">  
					 <thead>
				        <tr>
				        	<th field="" checkbox="true"></th>
				            <th data-options="field:'fsTactic',width:90">指标</th>
				            <th data-options="field:'fsName',width:150">指标名称</th>
				        </tr>
				    </thead>
				</table>
			</div>
			<div data-options="region:'center',split:false,border:false" style="width:12%">
				<div style="margin-left: 7px;margin-top: 120px;">
					<a class="easyui-linkbutton" data-options="iconCls:'icon-redo'" href="javascript:void(0)" onclick="javascript:transferRow('edit-mem-unselect','edit-mem-select')" style="width:55px">选择</a>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-undo'" href="javascript:void(0)" onclick="javascript:transferRow('edit-mem-select','edit-mem-unselect')" style="width:55px;margin-top:5px;">删除</a>
				</div>
			</div>
			<div data-options="region:'east',split:false,border:false" style="width:44%">
				<table class="easyui-datagrid" id="edit-mem-select" title="已选中原生指标" style="width:90%; height:300px;"  
					data-options="singleSelect:false,checkOnSelect:true,autoRowHeight:true,fitColumns:true,rownumbers:true,idField:'fsTactic'">  
					<thead>
				        <tr>
				            <th  field="" checkbox="true"></th>
				            <th data-options="field:'fsTactic',width:90">指标</th>
				            <th data-options="field:'fsName',width:150">指标名称</th>
				        </tr>
				    </thead>
				</table>	
			</div>
		</div>
		<div style="text-align: center; padding: 5px 0;margin-top:30px;">
			<a id="zbcs-submit" href="javascript:void(0);" class="easyui-linkbutton" onclick="submitEditForm();" data-options="iconCls:'icon-ok'" style="width: 80px">提交</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="closeEditForm();"  data-options="iconCls:'icon-reload'"  style="width: 80px">关闭</a>
		</div>
	</div>
 
	<script type="text/javascript">
	$.parser.parse();
	var checknode;
	/*
	 * Tool Function:
	 * 		管理按钮状态:设置按钮的状态，是否可用;oper ="enable"，按钮可用;oper ="disable"，按钮禁用   
	 */
	function manageGrpBtn(oper){
		$("#grp-edit").linkbutton(oper);
		$("#grp-cancel").linkbutton(oper)
	}
	
	 /*
	  * Tool Function:
	  * 	加载主页面右部面板数据:
	  *	  		1、原生指标组相关信息 
	  *	  		2、原生指标组组成员相关信息
	  */
	function loadRightPanel(data){
		//填充指标组相关信息
		$("#grp-form #fsTacgroup").textbox("setValue",data.fsTacgroup);
		$("#grp-form #fsName").textbox("setValue",data.fsName);
		$("#grp-form #fsDetail").textbox("setValue",data.fsDetail);
		$("#grp-form #fiLevel").textbox("setValue",data.fiLevel);
		$("#grp-form #fsPtacgroup").textbox("setValue",data.fsPtacgroupName);
		
		//加载指标组成员
		var mems = data.member;
		if((typeof mems) == "undefined" || mems == null || mems ==""){
			mems = [];
		}
		$("#mem-tb").datagrid({data:mems,fitColumns:true}); 
		$("#mem-tb").datagrid("resize"); 
	}
	

	 /*
	  * Tool Function:
	  * 	弹出编辑窗口:"新增"、"编辑"按钮点击后，在填充完相关数据后调用该函数使窗口显示出来
	  */
	function openWindow(){
		$("#Window").window("open");
	}
	
	 /*
	  * Tool Function:
	  * 	清空编辑窗口Form表单的数据
	  */
	function clearEditWindow(){
		$("#grp-edit-form").form("clear");
		$("#grp-edit-form #fsTacgroup").textbox("enable");
	}
	
	 /*
	  * Button Action Function:
	  * 	"转移"函数:
	  *	  		1、窗口上"选择"和"删除"按钮"点击"事件的响应函数；
	  *			2、用于两个DataGrid之间数据的移动
	  */
	function transferRow(source,target){
		var $sourceTb =  $("#"+source);
		var sourceData = $sourceTb.datagrid("getSelections");//获取所有被选中的数据
		if(!sourceData) return;
		
		var $targetTb = $("#"+target);
		var tmpNodes = [];
		var node ;
		//添加到目的gatagrid
		for(var i=0;i<sourceData.length;i++){
			$targetTb.datagrid('appendRow',sourceData[i]);
			
		}
		
		while(sourceData.length>0){
			var index =  $sourceTb.datagrid('getRowIndex',sourceData[0]);
			$sourceTb.datagrid('deleteRow',index);
		}
	}
	 
	 /*
	  * TOOL Function:
	  * 	加载原生指标库中当前指标组(grpcode)中未选中的所有指标，
	  *	  	如果grpcode参数未传入，加载所有原生指标
	  */
	function loadUnselectZB(grpcode){
		var param = {};
		if(grpcode){
			param.grpcode = grpcode;
		}
		var url = "selectUnselectZB.html";
		$.ajax({
			async : true,
			data : param,
			type : "post",
			dataType : 'json',
			url : url,
			cache : false,
			success : function(object) {
				if (object.result == 'ok'){
					var data = object.data;
					if(data){
					}else{
						data =[];
					}
					$("#edit-mem-unselect").datagrid({data:data});
				}else{
					$.messager.alert("提示","加载未选中原生指标失败："+object.message);
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
	/*
	 * Tool Function:
	 * 		管理按钮状态:设置按钮的状态，是否可用;oper ="enable"，按钮可用;oper ="disable"，按钮禁用   
	 */
	function reloadTreeGrid(){
		$("#grp-form").form("clear")
		$("#mem-tb").datagrid({data:[]}); 
		manageGrpBtn("disable");
		$('#grp-tb').treegrid("reload");
	}
	 
	 
   /*
    * Action Function:页面初始化后执行函数
    */
	$(function(){
		//加载原生指标组列表
		$('#grp-tb').treegrid({
			url: '${ctx}/grpprotac/treelist.html',
			method: 'get',
			animate: true,
			toolbar : '#grp-toolbar',
			onClickRow: function(node) {
				checkNode = node ; 
				loadRightPanel(node);
				manageGrpBtn("enable");
			},
			onLoadSuccess:function(){
				//置为没有选中
				$('#grp-tb').treegrid("unselectAll");
				//加载右边的面板
				if(checknode){
					checknode = $('#grp-tb').treegrid("find",checknode.fsTacgroup);
					if(checknode){
						loadRightPanel(checknode);
						manageGrpBtn("enable");
					}
				}
			}
		});
	})
	
	/*  
	 * Button Action Function:
	 *    "取消选中"按钮"点击"事件的响应函数：
	 *		    1、被选中的节点设置为未选中
	 			2、主页面右部数据清空
	 */
	function unselectTaggrp(){
		$('#grp-tb').treegrid("unselectAll");
		$("#grp-form").form("clear")
		$("#mem-tb").datagrid({data:[]});
		manageGrpBtn("disable");
	}
	
	/* 
	 *  Button Action Function:
	 * 		"新增"按钮"点击"事件的响应function
	 */
	function addTaggrp(){
		//清空弹出窗口
		clearEditWindow();
		//数据填充
		$("#oper").val("ADD");//操作标识
		var node = $('#grp-tb').treegrid("getSelected");
		console.log(node);
		if(node){//父级
			$("#grp-edit-form #fiLevel").textbox("setValue",node.fiLevel+1);//层级
			$("#grp-edit-form #fsPtacgroupName").textbox("setValue",node.fsName);//指标组名称
			$("#grp-edit-form #fsPtacgroup").val(node.fsTacgroup);//指标组
		}else{
			$("#grp-edit-form #fiLevel").textbox("setValue",1);
		}
		loadUnselectZB();//加载未选择所有指标
		$("#edit-mem-select").datagrid({data:[]});
		openWindow();
	}
	
	/*
	 * Button Action Function:
	 * 		"编辑"按钮"点击"事件的响应function
	 */
	function editTaggrp(){
		//清空弹出窗口
		clearEditWindow();
		//数据填充
		$("#oper").val("EDIT");//操作标识
		//填充指标组信息
		var node = $('#grp-tb').treegrid("getSelected");
		if(node){
			$("#grp-edit-form #fsTacgroup").textbox("setValue",node.fsTacgroup);
			$("#grp-edit-form #fsTacgroup").textbox("disable");
			$("#grp-edit-form #fsName").textbox("setValue",node.fsName);
			$("#grp-edit-form #fsDetail").textbox("setValue",node.fsDetail);
			$("#grp-edit-form #fiLevel").textbox("setValue",node.fiLevel);
			$("#grp-edit-form #fsPtacgroup").val(node.fsPtacgroup);
			$("#grp-edit-form #fsPtacgroupName").textbox("setValue",node.fsPtacgroupName);//指标组名称
			if(node.member){
				$("#edit-mem-select").datagrid({data:node.member});
			}else{
				$("#edit-mem-select").datagrid({data:[]});
			}
			loadUnselectZB(node.fsTacgroup);//加载未选择所有指标
			openWindow();
		}else{//如果节点没有被选中
			$.messager.alert("提示","请先选择一个原生指标组再进行编辑");
		}
	}
	
	/*
	 * Button Action Function:
	 * 		"删除"按钮"点击"事件的响应function
	 */
	function deleteTaggrp(){
		var node = $('#grp-tb').treegrid("getSelected");
		if(node){
			 $.messager.confirm({
					title: '原生指标组删除确认',
					msg: '<span style="color:red;padding:2px;text-indent:2em;line-heigth:24px;">请谨慎操作:点击"确认"后,'+
							'将会删除该原生指标组、该原生指标组的组成员以及该原生指标组的子元素。<br/>您确认要删除该原生指标组吗?</span>',
					fn: function(r){
						if(r){
							var url = "saveTacgroup.html";
							var param = {}
							param.fsTacgroup = node.fsTacgroup;
							param.oper = "DELETE";
							console.log(param);
							$.ajax({
			    				async : true,
			    				data : param,
			    				type : "post",
			    				dataType : 'json',
			    				url : url,
			    				cache : false,
			    				success : function(object) {
			    					if(object.result == 'ok') {
			    						$.messager.alert("提示","删除原生指标组成功");
			    						reloadTreeGrid();
			    					}else{
			    						$.messager.alert("提示","删除原生指标组成功："+object.message);
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
		}else{//如果节点没有被选中
			$.messager.alert("提示","请先选择一个原生指标组再进行删除");
		}
	}
	
	/**
	 * Button Action Function:
	 *		编辑窗口页面上"关闭"按钮"点击"事件的响应Function
	 */
	function closeEditForm(){
		$("#Window").window("close");
	}
	
	/**
	 * Button Action Function:
	 * 		编辑窗口页面上"提交"按钮"点击"事件的响应Function
	 */
	 function submitEditForm(){
		var param = {};
		//操作参数
		param.oper = $("#oper").val();
		//指标组参数
		param.fsTacgroup =  $("#grp-edit-form #fsTacgroup").textbox("getValue");
		param.fsName = $("#grp-edit-form #fsName").textbox("getValue");
		param.fsDetail = $("#grp-edit-form #fsDetail").textbox("getValue");
		param.fiLevel = $("#grp-edit-form #fiLevel").textbox("getValue");
		param.fsPtacgroup = $("#grp-edit-form #fsPtacgroup").val();
		//指标成员参数
		var selectDatas = $("#edit-mem-select").datagrid("getData").rows;
		if(selectDatas.length > 0){
			for(var i=0;i<selectDatas.length;i++){
				param["member["+i+"].fsTacgroup"] = param.fsTacgroup;
				param["member["+i+"].fsTactic"] = selectDatas[i].fsTactic;
				param["member["+i+"].fiIndex"] = i;
				
			}
		}
		console.log(param);
		var url = "saveTacgroup.html";
		$.ajax({
			async : true,
			data : param,
			type : "post",
			dataType : 'json',
			url : url,
			cache : false,
			success : function(object) {
				if (object.result == 'ok'){
					$.messager.alert("提示","操作成功");
					closeEditForm();
					reloadTreeGrid();
				}else{
					$.messager.alert("提示","保存数据失败："+object.message);
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
	</script>
</body>
</html>