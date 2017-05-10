<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>发送模板消息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="messagetemplate.do?send">
		<input id="svcchnl" name="svcchnl" type="hidden" value="1">
		<input id="weight" name="weight" type="hidden" value="2147483">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								模板编号:
							</label>
						</td>
						<td class="value">
						     	 <input id="templateId" name="templateId" type="text" style="width: 150px" readonly="readonly" class="inputxt" value='${message.templateId}'>
							<span class="Validform_checktip"></span>
						</td>
					</tr>
					
					<tr>
     					<td align="right">
      						<label class="Validform_label">
     							接收人OPENID:
      						</label>
     					</td>
					     <td class="value">
							<input id="openId" class="inputxt" name="openId"  value="" datatype="*" nullmsg="接收人OPENID不能为空！">
					      	<span class="Validform_checktip"></span>
					     </td>
    				</tr>
    				<tr>
     					<td align="right">
      						<label class="Validform_label">
     							标题:
      						</label>
     					</td>
					     <td class="value">
							<input id="title1" class="inputxt" name="title1"  value="" datatype="*" nullmsg="标题不能为空！">
					      	<span class="Validform_checktip"></span>
					     </td>
    				</tr>
    				<tr>
     					<td align="right">
      						<label class="Validform_label">
     							接收人名称:
      						</label>
     					</td>
					     <td class="value">
							<input id="keyword1" class="inputxt" name="keyword1"  value="" datatype="*" nullmsg="接收人名称不能为空！">
					      	<span class="Validform_checktip"></span>
					     </td>
    				</tr>
    				<tr>
     					<td align="right">
      						<label class="Validform_label">
     							业务类型:
      						</label>
     					</td>
					     <td class="value">
							<input id="keyword2" class="inputxt" name="keyword2"  value="" datatype="*" nullmsg="业务类型不能为空！">
					      	<span class="Validform_checktip"></span>
					     </td>
    				</tr>
    				<tr>
     					<td align="right">
      						<label class="Validform_label">
     							发送内容:
      						</label>
     					</td>
					     <td class="value">
							<input id="content1" class="inputxt" name="content1"  value="" datatype="*" nullmsg="发送内容不能为空！">
					      	<span class="Validform_checktip"></span>
					     </td>
    				</tr>
			</table>
		</t:formvalid>
 </body>