<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html lang="zh-CN">
<head>
<jsp:include page="comm/head.jsp" flush="true" />
<script type="text/javascript" src="<c:url value='/extension/js/angular.js' />"></script>
<title>热门主题</title>
</head>
<body ng-app="hottopic" ng-controller="hottopicCtrl">
	<!-- 搜索 -->
	<div class="history-header hot-header">
		<form action="" id="hot-search">
			<input type="text" placeholder="输入关键词搜索" id="hot-text" ng-bind="searchkey()" ng-model="keyword" />
		</form>
	</div>

	<!-- 页面主体 -->
	<div class="hot">
		<div class="hot-item" ng-repeat="topic in topics" ng-hide="topicitem">
			<div class="hot-item-body" ng-click="showTopic(topic.id)">
				<a href=""> <strong>{{topic.title}}</strong> <span>{{topic.contenttext}}</span>
				</a>
			</div>
			<div class="hot-tags">
				<span ng-repeat="blk in topic.blockItems">{{blk.blockname}}</span> 
			</div>
		</div>
				
		<!-- 没有数据 -->
		<div class="pool-empty" ng-hide="emptyinfo" >
			<img src="<c:url value='/extension/images/pool.png' />" /><br />没有查询到任何数据！
		</div>
	</div>
	
	<script>
var app = angular.module('hottopic', []);

app.controller('hottopicCtrl', function($scope, $http) {
	$scope.loadingtoast = false;
	$scope.emptyinfo = true;
	$scope.topicitem = true;
	var maxResult = 12;
	
	var url = contextPath+'/webapp/hottopic/findtopics.do';

	$http({	
	   method:'post',  
       url:url,  
       data:{maxResults:maxResult},  
       headers:{'Content-Type': 'application/x-www-form-urlencoded'},  
       transformRequest: function(obj) {  
           var str = [];  
           for(var p in obj){  
               str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));  
           }  
           return str.join("&");  
       }  
    }).then(function successCallback(response) {
    		$scope.loadingtoast = true;
    		
    		if(response.data.status != 0){
    			$scope.emptyinfo = false;
    		}else{
    			$scope.topics = response.data.data;
    			$scope.topicitem = false;	
    		}			
		}, function errorCallback(response) {			
	});
	
	$scope.showTopic = function(id) {		
		var topicUrl = contextPath+'/webapp/hottopic/topic.do?id='+id;
       location.href = topicUrl;
    };
    
    $scope.searchkey = function(){
    	if($scope.keyword != undefined ){
    		
    	}
    }
});
</script>
	

	<!-- 返回 -->
	<div class="goBack">
		<a href="javascript:history.back();"></a>
	</div>
	<!-- 加载中 -->
	<div class="qltt-toast" ng-hide="loadingtoast">
		<i class="qltt-loading qltt-icon_toast"></i>
	</div>
	<!-- 
	<script type="text/javascript"
		src="<c:url value='/extension/js/hottopic.js' />">
	</script>
	 -->
</body>
</html>