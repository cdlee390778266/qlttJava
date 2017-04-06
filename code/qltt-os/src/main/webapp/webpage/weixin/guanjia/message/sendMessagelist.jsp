<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<script type="text/javascript" src="plug-in/tools/Map.js"></script>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="sendmesList" checkbox="false" fitColumns="false" title="已发送消息列表" actionUrl="sendmessage.do?datagrid" idField="id" fit="true" sortOrder="desc">
   <t:dgCol title="模板ID"  field="templateId"    query="true"  width="120"></t:dgCol>
   <t:dgCol title="用户ID"  field="openId"    query="true"  width="120"></t:dgCol>
   <t:dgCol title="创建时间"  field="createtime"   width="120"></t:dgCol>
   <t:dgCol title="发送状态"  field="sendStatus"    query="true"  width="120" replace="发送成功_1,发送失败_0"></t:dgCol>
    <t:dgToolBar title="详细信息"  icon="icon-search" url="messagetemplate.do?goUpdate&templateId={templateId}" funname="getinfo"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>


<script type="text/javascript">

//查看详情
function getinfo(){
	var row = $('#messageList').datagrid('getSelected');
	if(row){
		var url= 'messagetemplate.do?goUpdate&templateId='+row.templateId;
		detail("详细信息",url,"messageList","","");
	}else{
		tip("请选择消息模板");
	}
}
</script>