<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="weixinUserList" checkbox="true" fitColumns="true" title="用户列表" actionUrl="weixinUserController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="微信账号"  field="accountid"   hidden="true"  queryMode="single"  width="0"></t:dgCol>
   <t:dgCol title="微信号"    field="openid"      hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="昵称"      field="nickname"    hidden="true" queryMode="single" query="true" width="120"></t:dgCol>
   <t:dgCol title="是否关注"   field="subscribe"   hidden="true"  dictionary="subscribe" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="性别"      field="sex"         hidden="true"  dictionary="sex" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="国家"      field="country"     hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="省份"      field="province"    hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="城市"      field="city"        hidden="true"      queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="关注时间"  field="subscribeTime"  hidden="true"  formatter="yyyy-MM-dd hh:mm:ss" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="备注"      field="remark"         hidden="true"   queryMode="single"  width="120"></t:dgCol>
  <!-- <t:dgCol title="操作"  field="accountaccesstoken"  hidden="true"  queryMode="single"  width="120"></t:dgCol>-->
  
   <t:dgToolBar title="创建公众账号" icon="icon-add" url="weixinAccountController.do?goAdd" funname="myadd"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinAccountController.do?goUpdate" funname="update"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 </script>