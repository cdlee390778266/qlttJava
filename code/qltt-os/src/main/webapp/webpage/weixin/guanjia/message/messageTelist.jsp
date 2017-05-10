<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 1px;">
		<t:datagrid name="messageList" checkbox="false" fitColumns="false"
			title="消息模板管理" actionUrl="messagetemplate.do?datagrid"
			idField="templateId" fit="true" sortOrder="desc">
			<t:dgCol title="模板编号" field="templateId"></t:dgCol>
			<t:dgCol title="模板标题" field="title" query="true" width="120"></t:dgCol>
			<t:dgCol title="一级行业名称" field="primaryIndustryName" query="true" width="120"></t:dgCol>
			<t:dgCol title="二级行业名称" field="secondaryIndustryName" query="true" width="120"></t:dgCol>
			<t:dgToolBar title="删除" icon="icon-remove" funname="deleteTem"></t:dgToolBar>
			<t:dgToolBar title="详细信息" icon="icon-search" url="messagetemplate.do?goUpdate&templateId={templateId}" funname="getinfo"></t:dgToolBar>
			<t:dgToolBar title="微信同步" icon="icon-reload" funname="getDate"></t:dgToolBar>
			<t:dgToolBar title="发送微信消息" icon="icon-add" funname="sendMessage"></t:dgToolBar>
		</t:datagrid>
	</div>
</div>


<script type="text/javascript">
	//删除
	function deleteTem() {
		var row = $('#messageList').datagrid('getSelected');
		if (row) {

			var url = 'messagetemplate.do?del&id=' + row.templateId;
			$.dialog.confirm('确定删除该记录吗', function() {
				doSubmit(url);
				$('#messageList').datagrid('reload');
			});
		} else {
			tip("请选择消息模板");
		}
	}
	//查看详情
	function getinfo() {
		var row = $('#messageList').datagrid('getSelected');
		if (row) {
			var url = 'messagetemplate.do?goUpdate&templateId='
					+ row.templateId;
			detail("详细信息", url, "messageList", "", "");
		} else {
			tip("请选择消息模板");
		}
	}

	function getDate() {
		var $mask = masking(null);
		$.ajax({
			async : false,
			cache : false,
			type : 'POST',
			url : 'messagetemplate.do?async',// 请求的action路径
			success : function(data) {
				unmasking($mask);
				var d = $.parseJSON(data);

				if (parseInt(d) == 0) {
					tip("同步数据成功");
				} else {
					tip("同步数据失败");
				}
				tag = 0;
				$('#messageList').datagrid('reload');
			}
		});
	}
	
	//查看详情
	function sendMessage() {
		var row = $('#messageList').datagrid('getSelected');
		if (row) {
			var url = 'messagetemplate.do?sendMessage&templateId='
					+ row.templateId;
			add("发送消息", url, "messageList", "", "");
		} else {
			tip("请选择消息模板");
		}
	}
</script>