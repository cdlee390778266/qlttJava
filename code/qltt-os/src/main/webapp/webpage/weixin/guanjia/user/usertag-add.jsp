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
 	<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinUserTagController.do?doAdd" tiptype="1">
		<table style="width: 400px;margin:15px;" cellpadding="0" cellspacing="1" class="formtable">
			<tr>
				<td align="right">
					<label class="Validform_label">标签名称:</label>
				</td>
				<td class="value">
					<input id="name" name="name" type="text" style="width: 150px" class="inputxt" datatype="byterange" max="12" min="1">
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">标签名称</label>
				</td>
			</tr>
		</table>
	</t:formvalid>
 </body>
 </html>