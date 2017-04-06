<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>标签信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
 	<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinUserController.do?doModifyRemark" tiptype="1">
 		<input id="accountid" name="accountid" type="hidden" value="${user.accountid}">
 		<input id="openid" name="openid" type="hidden" value="${user.openid}">
		<table style="width: 400px;margin:15px;" cellpadding="0" cellspacing="1" class="formtable">
			<tr>
				<td align="right">
					<label class="Validform_label">昵称:</label>
				</td>
				<td class="value">
					${user.nickname}
				</td>
			</tr>
			<tr>
				<td align="right">
					<label class="Validform_label">备注:</label>
				</td>
				<td class="value">
					<input id="remark" name="remark" type="text" style="width: 150px" class="inputxt" datatype="byterange" max="160" min="1" value="${user.remark}">
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">备注</label>
				</td>
			</tr>
		</table>
	</t:formvalid>
 </body>
 </html>