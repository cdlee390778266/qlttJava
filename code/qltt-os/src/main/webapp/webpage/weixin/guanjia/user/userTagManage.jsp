<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<script type="text/javascript">
 
 </script>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="weixinUserList"  actionUrl="weixinUserTagController.do?getAllUsers" showRefresh="true" 
  	pagination="false" showText="false" checkbox="true" fitColumns="true"   
  	title="用户列表"  idField="openid" fit="true" queryMode="group" onLoadSuccess="onLoadSuccess">
   <t:dgCol title="微信账号"  field="accountid"   hidden="false"  query="true" queryMode="single" width="0"></t:dgCol>
   <t:dgCol title="微信号"    field="openid"      hidden="true"   width="120"></t:dgCol>
   <t:dgCol title="昵称"      field="nickname"    hidden="true"   query="true" queryMode="single" width="120"></t:dgCol>
   <t:dgCol title="是否关注"   field="subscribe"   hidden="true"  dictionary="subscribe"   width="120"></t:dgCol>
   <t:dgCol title="性别"      field="sex"         hidden="true"   dictionary="sex"  width="120"></t:dgCol>
   <t:dgCol title="国家"      field="country"     hidden="true"   width="120"></t:dgCol>
   <t:dgCol title="省份"      field="province"    hidden="true"   width="120"></t:dgCol>
   <t:dgCol title="城市"      field="city"        hidden="true"       width="120"></t:dgCol>
   <t:dgCol title="备注"       field="remark"     hidden="true"    width="120"></t:dgCol>
   <t:dgToolBar title="用户设置"  icon="icon-redo" url="weixinUserTagController.do?batchTagUser" funname="batchTagUser"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 	//加载该标签下的用户
	function onLoadSuccess(rows){
		var url="weixinUserTagController.do?getTagUsers&tagid=${tagid}";
		$.ajax({
			url:url,
			type:'GET',
			data:null,
			cache:false,
			success:function(data){
				var d = $.parseJSON(data);
				if(d.success){
					var data = d.obj;
					if(data!=null && data.length>0){
						$(data).each(function(idx,usertag){
							$('#weixinUserList').datagrid('selectRecord',usertag.openid);
						});
					}
				}else{
					tip(d.msg);
				}
		 	}
		});
	}
 
	$(function(){
		$('#weixinUserList').datagrid({
			onLoadSuccess :onLoadSuccess
		});
	}) 

	function batchTagUser(title,url,gname){
		//alert("执行到了这里");
		 var getData = $('#'+gname).datagrid('getSelections');//获取选中的记录
		 var postData = {};
		 postData.tagid = ${tagid};
		 if(getData.length>0){
			 var  openids = [];
			 for(var i=0;i<getData.length;i++){
				 openids.push(getData[i].openid);
			 }
			 postData.openids = openids;
		 }
		 var $mask = masking(null);
		 $.ajax({
			url : url,
			type : 'post',
			data : postData,
			cache : false,
			success : function(data){
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
 </script>