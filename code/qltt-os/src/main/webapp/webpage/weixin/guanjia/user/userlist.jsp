<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="weixinUserList"  actionUrl="weixinUserController.do?datagrid" checkbox="true" fitColumns="true"   title="用户列表"  idField="openid" fit="true" queryMode="group">
   <t:dgCol title="微信账号"  field="accountid"   hidden="false"   width="0"></t:dgCol>
   <t:dgCol title="微信号"    field="openid"      hidden="true"   width="120"></t:dgCol>
   <t:dgCol title="昵称"      field="nickname"    hidden="true"   query="true" queryMode="single" width="120"></t:dgCol>
   <t:dgCol title="是否关注"   field="subscribe"   hidden="true"  dictionary="subscribe"   width="120"></t:dgCol>
   <t:dgCol title="性别"      field="sex"         hidden="true"   dictionary="sex"  width="120"></t:dgCol>
   <t:dgCol title="国家"      field="country"     hidden="true"   width="120"></t:dgCol>
   <t:dgCol title="省份"      field="province"    hidden="true"   width="120"></t:dgCol>
   <t:dgCol title="城市"      field="city"        hidden="true"       width="120"></t:dgCol>
   <!-- <t:dgCol title="关注时间"   field="subscribeTime"  hidden="false"  formatter="yyyy-MM-dd hh:mm:ss"  width="120"></t:dgCol> --> 
   <t:dgCol title="是否拉黑"   field="isblack"         dictionary="isblack" hidden="true"    width="120"  query="true" queryMode="single" ></t:dgCol>
   <t:dgCol title="备注"       field="remark"         hidden="true"    width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgFunOpt title="更新备注" funname="goModifyRemark(openid)"></t:dgFunOpt>
   <t:dgToolBar title="用户同步"  icon="icon-reload" url="weixinUserController.do?doSameUser" funname="doSameUser"></t:dgToolBar>
   <t:dgToolBar title="批量拉黑用户"  icon="icon-redo" url="weixinUserController.do?blackList" funname="batchBlackList"></t:dgToolBar>
   <t:dgToolBar title="批量取消拉黑"  icon="icon-undo" url="weixinUserController.do?blackList" funname="batchUnBlackList"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
//将微信和本地数据库的信息同步
 function doSameUser(title,url,gname){
	var $mask = masking(null);
	//console.log("gname:"+gname)
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
//更新一个标签
 function goModifyRemark(id){
	var url = "weixinUserController.do?goModifyRemark&openid="+id;
	createwindow("更改备注", url,450,150);
 }
 
//更改用户备注名
function modifyRemark(title,addurl,gname,width,height){
	//console.log("gname:"+gname)
	var getData = $('#'+gname).datagrid('getSelections');//获取选中的记录
	if(getData.length==1){
		addurl += '&openid=' + getData[0].openid;
		createwindow(title, addurl,width,height);
		return;
	}
	tip("请选择一个用户");
	return;
 }
 
 //用户黑名单同步
 function doSameBlack(title,url,gname){
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
 
 //拉黑用户
 function batchBlackList(title,url,gname){
	gridname = gname;
    var rows = $("#"+gridname).datagrid('getSelections');
    if(rows.length > 0) {
    	$.dialog.setting.zIndex = getzIndex(true);
    	$.dialog.confirm('你确认要拉黑这些用户?', function(r) {
    	  
		   if(r){
			   var openids = [];
			   var index = 0;
				for(var i = 0;i< rows.length; i++) {
					if(rows[i].isblack==0)//如果已经拉黑了，不需要再拉黑
						openids.push(rows[i].openid);
				}
				if(openids.length==0){
					tip("你所选择的用户已经全部拉黑");
					return;
				}
				var data ={};
				data.openids = openids;
				data.black = 1;
				//console.log(data);
				var $mask = masking(null);
				$.ajax({
					url : url,
					type : 'post',
					data : data,
					cache : false,
					success : function(data) {
						var d = $.parseJSON(data);
						if (d.success) {
							unmasking($mask);
							var msg = d.msg;
							tip(msg);
							reloadTable();
							$("#"+gname).datagrid('unselectAll');
						}
					}
				});
			}
		});
	} else {
		tip("请选择你需要拉黑的用户");
	}
 }
 
 
 //取消用户拉黑
 function batchUnBlackList(title,url,gname){
	gridname = gname;
    var rows = $("#"+gridname).datagrid('getSelections');
    if(rows.length > 0) {
    	$.dialog.setting.zIndex = getzIndex(true);
    	$.dialog.confirm('你确认要将这些用户取消拉黑?', function(r) {
		   if(r){
			   var openids = [];
				for(var i = 0;i< rows.length; i++) {
					if(rows[i].isblack==1)//如果已经拉黑了，不需要再拉黑
						openids.push(rows[i].openid);
				}
				if(openids.length==0){
					tip("你所选择的用户已经全部取消拉黑");
					return;
				}
				var data ={};
				data.openids = openids;
				data.black = 0;
				var $mask = masking(null);
				$.ajax({
					url : url,
					type : 'post',
					data : data,
					cache : false,
					success : function(data) {
						unmasking($mask);
						var d = $.parseJSON(data);
						if (d.success) {
							var msg = d.msg;
							tip(msg);
							reloadTable();
							$("#"+gname).datagrid('unselectAll');
						}
					}
				});
			}
		});
	} else {
		tip("请选择你需要拉黑的用户");
	}
 }
 </script>