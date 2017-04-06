<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="weixinAccountList" checkbox="true" fitColumns="true" title="微信标签" actionUrl="weixinUserTagController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="标签编号"  field="id"   queryMode="single"  width="200"></t:dgCol>
   <t:dgCol title="标签名称"  field="name" queryMode="single"  width="500"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgFunOpt title="编辑标签" funname="goUpdate(id)"></t:dgFunOpt>
   <t:dgFunOpt title="用户管理" funname="goTagUserManage(id)"/>
   <t:dgToolBar title="创建标签" icon="icon-add" width="450" height="150" url="weixinUserTagController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="标签批量删除"  icon="icon-remove" url="weixinUserTagController.do?doBatchDel" funname="doBatchDel"></t:dgToolBar>
   <t:dgToolBar title="微信同步"  icon="icon-reload" url="weixinUserTagController.do?doTagSame" funname="doTagSame"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
 //新增一个标签
 function myadd(title,addurl,gname,width,height) {
	createwindow(title, addurl,width,height);
 }
 
 //更新一个标签
 function goUpdate(id){
	 var url = "weixinUserTagController.do?goUpdate";
	 url += '&id='+id;
	createwindow("编辑标签", url,450,150);
 }
 
 //批量删除
 function doBatchDel(title,url,gname) {
		gridname=gname;
	    var ids = [];
	    var rows = $("#"+gname).datagrid('getSelections');
	    if (rows.length > 0) {
	    	$.dialog.setting.zIndex = getzIndex(true);
	    	$.dialog.confirm('你确定永久删除该数据吗?', function(r) {
			   if (r){
				   
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].id);
					}
					var $mask = masking(null);
					$.ajax({
						url : url,
						type : 'post',
						data : {
							ids : ids.join(',')
						},
						cache : false,
						success : function(data) {
							unmasking($mask);
							var d = $.parseJSON(data);
							if (d.success){
								unmasking($mask);
								var msg = d.msg;
								tip(msg);
								reloadTable();
								$("#"+gname).datagrid('unselectAll');
								ids='';
							}
						}
					});
				}
			});
		} else {
			tip("请选择需要删除的数据");
		}
	}
 
 //将微信和本地数据库标签同步
 function doTagSame(title,url,gname){
	 var $mask = masking(null);
	$.ajax({
		url : url,
		type : 'post',
		data : null,
		cache : false,
		success : function(data) {
			unmasking($mask);
			var d = $.parseJSON(data);
			if (d.success) {
				var msg = d.msg;
				tip(msg);
				$("#"+gname).datagrid("reload");
			}
		}
	});
}
 
 
//管理一个标签下的用户
 function goTagUserManage(tagid){
	var url = "weixinUserTagController.do?goTagUserManage";
	url += '&tagid=' + tagid;
	openwindow("用户管理", url,null,900,600); 
 }
 </script>