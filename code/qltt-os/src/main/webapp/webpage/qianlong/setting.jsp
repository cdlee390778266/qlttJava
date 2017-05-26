<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN" ng-app="qlttApp">
<head>
<meta charset="utf-8">
<meta name="referrer" content="always">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<meta http-equiv="x-dns-prefetch-control" content="on">
<link rel="stylesheet" href="<c:url value='/extension/css/animate.min.css' />" />
<link rel="stylesheet" href="<c:url value='/extension/css/style.css' />" />
<script type="text/javascript" src="<c:url value='/extension/js/zepto.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/extension/js/zepto.touch.js' />"></script>
<title>推送设置</title>
</head>
<body style="background: #f0f0f0;">
	<div class="push">
		<div class="push-box">
			<div class="push-item push-set-go">
				<div class="push-item-left">即时推送</div>
				<div class="push-item-right">推送频率</div>
			</div>
			<select name="pushFreq" id="pushFreq">
				<option value="0" <c:if test="${pushFreq == 0}">selected="selected"</c:if>>即时推送</option>
				<option value="1000900" <c:if test="${pushFreq == 1000900}">selected="selected"</c:if>>每一刻钟集合推送一次</option>
				<option value="1001800" <c:if test="${pushFreq == 1001800}">selected="selected"</c:if>>每半小时集合推送一次</option>
				<option value="1003600" <c:if test="${pushFreq == 1003600}">selected="selected"</c:if>>每小时集合推送一次</option>
				<option value="2000600" <c:if test="${pushFreq == 2000600}">selected="selected"</c:if>>集合竞价后推送</option>
				<option value="2100000" <c:if test="${pushFreq == 2100000}">selected="selected"</c:if>>每日盘前推送一次</option>
				<option value="2107200" <c:if test="${pushFreq == 2107200}">selected="selected"</c:if>>早/午盘开盘前各推送一次</option>
				<option value="2207200" <c:if test="${pushFreq == 2207200}">selected="selected"</c:if>>早/午盘收盘前各推送一次</option>
				<option value="2214400" <c:if test="${pushFreq == 2214400}">selected="selected"</c:if>>每日收盘后推送一次</option>
			</select>
		</div>
		<div class="push-box">
			<div class="push-head">推送范围</div>
		</div>
		<div class="push-box">
			<div class="push-item">
				<div class="push-item-left">全部市场</div>
				<div class="push-item-right">
					<label for="all" class="section-switch-cp">
						<input id="all" name="all" class="section-switch-cp__input" <c:if test="${pushScope == 0}">checked="checked"</c:if> type="checkbox">
						<div class="section-switch-cp__box"></div>
					</label>
				</div>
			</div>
		</div>
		<div class="push-box">
			<div class="push-item push-set-go">
				<div class="push-item-left">全部</div>
				<div class="push-item-right">选股池设置</div>
			</div>
			<select name="scopePrm" id="scopePrm" <c:if test="${pushScope == 0}">disabled="disabled"</c:if>>
				<option value="0" <c:if test="${pushScope == 1 && scopePrm == 0}">selected="selected"</c:if>>全部</option>
				<option value="1" <c:if test="${pushScope == 1 && scopePrm == 1}">selected="selected"</c:if>>选股池A</option>
				<option value="2" <c:if test="${pushScope == 1 && scopePrm == 2}">selected="selected"</c:if>>选股池B</option>
				<option value="3" <c:if test="${pushScope == 1 && scopePrm == 3}">selected="selected"</c:if>>选股池C</option>
			</select>
		</div>
		
		<!-- 页脚菜单 -->
		<div class="pool-footer">
			<div class="footer">
				<a href="<c:url value='/webapp/search/home.do' />"><i class="icon icon-searchd"></i><br><span>指标查询</span></a>
				<a href="<c:url value='/webapp/myattention/home.do' />"><i class="icon icon-care"></i><br><span>我的关注</span></a>
				<a href="<c:url value='/webapp/userpool/home.do' />"><i class="icon icon-pool"></i><br><span>选股池</span></a>
				<a href="javascript:void(0);"><i class="icon icon-rmd"></i><br><span>荐股池</span></a>
				<a href="<c:url value='/webapp/setting/home.do' />"  class="active"><i class="icon icon-set"></i><br><span>设置</span></a>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	//推送频率设置
	$('body').delegate('#pushFreq', 'change', function(event) {
		$.ajax({
			url: 'freq.do',
			data: {
				"pushFreq": $(this).val()
			},
			dataType: "json"
		});
	});
	
	//推送范围参数设置
	$('body').delegate('#scopePrm', 'change', function(event) {
		$.ajax({
			url: 'scope.do',
			data: {
				"pushScope": 1,
				"scopePrm": $("#scopePrm").val()
			},
			dataType: "json"
		});
	});
	
	//推送范围设置
	$('body').delegate('input[type="checkbox"]', 'change', function(event) {
		var checked = $(this).is(':checked');
		if (checked) {
			$('#scopePrm').attr('disabled', 'disabled');
			$.ajax({
				url: 'scope.do',
				data: {
					"pushScope": 0,
					"scopePrm": 0
				},
				dataType: "json"
			});
		} else {
			$('#scopePrm').removeAttr('disabled');
			$.ajax({
				url: 'scope.do',
				data: {
					"pushScope": 1,
					"scopePrm": 0
				},
				dataType: "json"
			});
		}
	});
	</script>
</body>
</html>