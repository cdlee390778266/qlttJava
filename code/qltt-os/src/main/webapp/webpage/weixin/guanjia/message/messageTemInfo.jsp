<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>消息模板详细信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="messagetemplate.do?goUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${weixinAccountPage.id }">
					<input id="addtoekntime" name="addtoekntime" type="hidden" value="${weixinAccountPage.addtoekntime }"  />
					<input id="accountaccesstoken" name="accountaccesstoken" value="${weixinAccountPage.accountaccesstoken }" type="hidden"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								模板编号:
							</label>
						</td>
						<td class="value">
						     	 <input id="templateId" name="templateId" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${message.templateId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">模板编号</label>
						</td>
					</tr>
					
					<tr>
						<td align="right">
							<label class="Validform_label">
								标题:
							</label>
						</td>
						<td class="value">
						     	 <input id="title" name="title" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${message.title}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">标题</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								一级行业名称:
							</label>
						</td>
						<td class="value">
						    <input id="primaryIndustryName" name="primaryIndustryName" type="text" style="width: 150px" class="inputxt"  
									              
									                 value='${message.primaryIndustryName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">一级行业名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								二级行业名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="secondaryIndustryName" name="secondaryIndustryName" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${message.secondaryIndustryName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">二级行业名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								详细内容:
							</label>
						</td>
						<td class="value">
						     	 <textarea  rows="15" cols="80" name="detailContent" id="detailContent">${detailContent}</textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">详细内容</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								内容示例:
							</label>
						</td>
						<td class="value">
						     	 <textarea  rows="15" cols="80" name="exContent" id="exContent">${exContent}</textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">内容示例</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>