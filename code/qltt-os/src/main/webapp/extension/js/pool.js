/* 
* @Author: lee
* @Date:   2017-04-07 14:31:52
* @Last Modified time: 2017-04-21 14:05:30
*/
$(document).ready(function(){
	var pollSwiper ;
	var scrollTopArr = {};
	var editFlag = true;
	var menuNums = 0;

	var refreshScrollTop = function(prevKey,currentKey){
		scrollTopArr[prevKey] = getScrollTop(); 
		scrollTop(scrollTopArr[currentKey]);
	}

	var createSearchBox = function($parent){
		var html = '';
		$('#scrollerPool li').each(function(index, val) {
			var searchBoxId = $(this).attr('data-href');
			var pool = $(this).data('pool');
			html += '<div class="pool-main animated" id="' + searchBoxId + '" data-pool="' + pool + '"></div>';
			scrollTopArr[searchBoxId] = 0;
		});
		$parent.append(html);
		menuNums = $('#scrollerPool li').length ;
	}

	var createHtml = function(parentId){
		var html = '';
		var $parent = $('#' + parentId);
		var pool = $parent.data('pool');
		var wrapperId = parentId + '-wrapper';
		var paginationId = parentId + '-page';
        
		$.ajax({
			url: '../userpool/pool.do',
			type: 'post',
			data: {
				'poolIndex': pool,
				'reqNum': 10,
				'reqStart': 0
			},
			dataType: 'json',
			success: function(data){
				if (data && data.content && data.content.attnStock && data.content.attnStock.length > 0) {
					var attnStock = data.content.attnStock;
					for(var i in attnStock){
						html += '<div class="pool-item">'
							+ '<div class="poolItem-td item-col-name">' + attnStock[i].stockName + '</div>'
							+ '<div class="poolItem-td red item-col-code">' + attnStock[i].stockCode + '</div>'
							+ '<div class="poolItem-td">'
							+ '<span class="poolItem-icon-cmd"></span>'
							+ '<span class="poolItem-icon-cancle"></span>'
							+ '</div>'
							+ '</div>';
					}
					$parent.html(html);
					loadingHide($('.qltt-toast'));
				} else {
					html += '<div class="pool-empty">' + '<img src="../../extension/images/pool.png" /><br />该选股池还没有数据' + '</div>';
	                $parent.html(html);
	                loadingHide($('.qltt-toast'));
				}
            },
            error: function(jqXHR, textStatus, errorThrown){
            	html += '<div class="pool-empty">' + '<img src="../../extension/images/pool.png" /><br />该选股池还没有数据' + '</div>';
                $parent.html(html);
                loadingHide($('.qltt-toast'));
            }
		});
        $parent.append(html);
        loadingHide($('.qltt-toast'));
    }

	var createMenuHtml = function($parent){
		var html = '';
		$('#scrollerPool li').each(function(index, val) {
			html += '<div data-href="' + $(this).attr('data-href') + '" ><span><strong>' + $(this).find('span').text() + '</strong> <i></i></span></div>';
		});
		$parent.append(html);
	}

	var loadMore = function(url,$parent){
		$.ajax({
			url: url,
			type: 'post',
			success: function(resData){
				var html = '';
				$parent.append(html);
					$('.load-more i').removeClass('active');
					loadFlag = true; 
			},
			error: function(xhr, type){
				alert('获取数据失败!');
			}
		});
	}

	var showDialog = function($dialogEle, callBack){
		$dialogEle.removeClass('fadeOut').css('display','block').addClass('fadeIn');
		if(typeof callBack == 'function')
			callBack();
	}

	var hideDialog = function($dialogEle, callBack){
		$dialogEle.removeClass('fadeIn').addClass('fadeOut').css('display','none');
		if(typeof callBack == 'function')
			callBack();
	}

	var showMessage = function(text, time){
		$('#error span').text(text);
		$('#error').removeClass('fadeOut').css('display','block').addClass('fadeIn');
		setTimeout(function(){
			$('#error').removeClass('fadeIn').addClass('fadeOut').css('display','none');
		}, time);
	}

	var refreshIScroll = function(){
		$('#scrollerPool').css('width',$('#scrollerPool li').length*90+50);
	}

	var refreshMenu = function(type,dataHref,text){
		if(type == 'remove'){
			if($('#scrollerPool li[data-href="' + dataHref +'"]').hasClass('active')){
				var $ele = $('#scrollerPool li[data-href="' + dataHref + '"]').next().length ? $('#scrollerPool li[data-href="' + dataHref + '"]').next() : $('#scrollerPool li[data-href="' + dataHref + '"]').prev();
				var searchBoxId = $ele.attr('data-href');
				var prevKey = $('#headerPool li.active').attr('data-href');
				$('#headerPool li').removeClass('active');
				$ele.addClass('active');
				if(!$('#' + $ele.attr('data-href')).text() ){
					loadingShow($('.qltt-toast'));
					createHtml($ele.attr('data-href'));
				}
				refreshScrollTop(prevKey,$ele.attr('data-href'));
				$('.pool-main').removeClass('fadeIn').addClass('fadeOut').css('display','none');
				$('.pool-main').eq($ele.index()).css('display','block').removeClass('fadeOut').addClass('fadeIn');
            }
			$('#scrollerPool li[data-href="'+ dataHref +'"]').remove();
			$('#'+dataHref).remove();
			delete scrollTopArr[dataHref];
			refreshIScroll();
        }else if(type=='update'){
        	$('#scrollerPool li[data-href="'+ dataHref +'"]').find('span').text(text);
        }else if(type=='add'){
        	$('#scrollerPool ul').append('<li class="" data-href="' + dataHref + '" ><span>' + text + '</span></li>');
        	$('#pool-wrapper').append('<div class="pool-main animated" id="' + dataHref + '" ></div>');
        	scrollTopArr[dataHref] = 0;
        	refreshIScroll();
        }
	}

	var init = function(){
		$('#scrollerPool').css('width',$('#scrollerPool li').length * 90 + 50);
		headIScroll = new IScroll('#headerPool', { 
			eventPassthrough: true, 
			scrollX: true, 
			scrollY: false, 
			preventDefault: false
		});
		loadingShow($('.qltt-toast'));
		createSearchBox($('#pool-wrapper'));
		createHtml($('#scrollerPool li').eq(0).attr('data-href'));
		createMenuHtml($('.menu-body'));
	}

	init();

	$('#headerPool').delegate('li', 'tap', function(event){
		if($(this).hasClass('active')) return;
		var searchBoxId = $(this).attr('data-href');
		var prevKey = $('#headerPool li.active').attr('data-href');
		$('#headerPool li').removeClass('active');
		$(this).addClass('active');
		if($('#'+$(this).attr('data-href')).find('.pool-item').length <= 0 ){
			loadingShow($('.qltt-toast'));
			createHtml($(this).attr('data-href'));
		}
		refreshScrollTop(prevKey,$(this).attr('data-href'));
		$('.pool-main').removeClass('fadeIn').addClass('fadeOut').css('display','none');
		$('.pool-main').eq($(this).index()).css('display','block').removeClass('fadeOut').addClass('fadeIn');
	});

	var loadFlag = true;
	//加载更多
	$('body').delegate('.load-more', 'tap', function(event){
		if(loadFlag){
			loadFlag = false;
			$(this).find('i').addClass('active');
			loadMore('../data/result.json',$(this).prev());
		}
	});

	//荐股池弹窗
	$('body').delegate('.poolItem-icon-cmd', 'tap', function() {
		showDialog($('#recommend'));
	});

	$('#recommend .dialog-btn-close,#recommend .dialog-mask').tap(function() {
		hideDialog($('#recommend'));
	})

	$('#recommend .dialog-btn-confirm').tap(function() {
		hideDialog($('#recommend'),function(){
			console.log('提交');
		});
	})

	//选股池弹窗
	$('body').delegate('.poolItem-icon-cancle', 'tap', function() {
		var stockcode = $(this).parent().parent().find('.item-col-code').text();
		$("#code").val(stockcode);
		$("#name").val($(this).parent().parent().find('.item-col-name').text());
		//ajax加载该股票已选股票池
		$.ajax({
			url : '../stock/poolindexs.do',
			data : {
				"stockcode" : stockcode
			},
			dataType : "json",
			error : function(jqXHR, textStatus, errorThrown) {
				alert("加载数据失败！");
			},
			success : function(data, textStatus, jqXHR){
				//多选下拉框置于所有未选中的状态
				$.each($("#choosePool option"),function(){
					$(this).attr("selected",false);
				});
				if (data && data.length >= 0){
					for ( var i in data){
						$("#choosePool option[value="+data[i].poolIndex+"]").attr("selected",true);
					}
				}
				showDialog($('#choose'));	
			}
		});
	});
         
	$('#choose .dialog-btn-close,#choose .dialog-mask').tap(function() {
		console.log('关闭窗口');
		hideDialog($('#choose'));
	});
	
	//直接删除
	$('#choose .dialog-btn-delete').tap(function() {
		console.log('删除');
		var stockPool = [{"poolIndex": $('#headerPool li.active').data('pool')}];
		var data = {
			"stockCode": $("#code").val(),
			"stockName": $("#name").val(),
			"stockPool": stockPool
		};
		$.ajax({
			url: '../userpool/unfollow.do',
			data: JSON.stringify(data),
			dataType: 'json',
			type: 'post',
			contentType: 'application/json;charset=UTF-8',
			success: function(data) {
				if (data.status == 1)
					alert("成功移出选股池！");
				else
					alert(data.message);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("移出选股池失败！");
			},
			complete: function() {
				hideDialog($('#choose'), function(){
					createHtml($('#scrollerPool li.active').attr('data-href'));
				});
			}
		});
	});

	$('#choose .dialog-btn-confirm').tap(function() {
		hideDialog($('#choose'), function() {
			console.log('提交');
			var stockPool = [];
			$.each($("#choosePool").val(), function(idx, e){
				stockPool.push({"poolIndex": e});
			});
			var data = {
				"stockCode": $("#code").val(),
				"stockName": $("#name").val(),
				"stockPool": stockPool
			};
			$.ajax({
				url: '../userpool/follow.do',
				data: JSON.stringify(data),
				dataType: 'json',
				type: 'post',
				contentType: 'application/json;charset=UTF-8',
				success: function(data) {
					if (data.status == 1)
						alert("成功加入选股池！");
					else
						alert(data.message);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("加入选股池失败！");
				}
			});
		});
	})

	$('body').delegate('#choosePool', 'change', function(event) {
		$('.select-mask').text($(this).val());
	});

	//增删菜单弹窗
	$('body').delegate('.pam-icon', 'tap', function(event) {
		if($(this).parent().hasClass('active')){
			$(this).parent().removeClass('active');
			$('.pool-add-menu').removeClass('fadeIn').addClass('fadeOut').removeClass('fadeOut');
			$('.pool').removeClass('fadeOut').css('display','block').addClass('fadeIn');
			headIScroll.refresh();
		}else{
			$(this).parent().addClass('active');
			$('.pool-add-menu').removeClass('fadeOut').addClass('fadeIn');
			$('.pool').removeClass('fadeIn').addClass('fadeOut').css('display','none');
		}
	});

	//编辑
	$('body').delegate('.menu-head-right', 'tap', function(event) {
		if(editFlag) {
			editFlag = false;
			$('.menu-body  i').show();
			$('.menu-head-right span').text('完成');
		} else {
			editFlag = true;
			$('.menu-body  i').hide();
			$('.menu-head-right span').text('编辑');
		}
	});

	//删除菜单
	$('.menu-body').delegate('i', 'tap', function(event) {
		refreshMenu('remove',$(this).parent().parent().attr('data-href'));
		$(this).parent().parent().remove();
	});

	//选中
	$('.menu-body').delegate('span', 'tap', function(event) {
		if(!editFlag){
			$('.menu-body span').removeClass('active');
			$(this).addClass('active');
			$('#poolName').val($(this).find('strong').text())
		}
	});

	//修改名称
	$('body').delegate('.menu-btns-edit', 'tap', function(event) {
		if(!editFlag){
			if($('.menu-body div span.active').length){
				if(!$('#poolName').val()){
					showMessage('输入名称为空！',2000);
				}else{
					$('.menu-body div span.active').find('strong').text($('#poolName').val());
					refreshMenu('update',$('.menu-body div span.active').parent().attr('data-href'),$('#poolName').val());
					showMessage('修改成功',2000);
				}
			}else{
				showMessage('没有选中要修改的菜单！',2000);
			}
		}
	});

	//新增菜单
	$('body').delegate('.menu-btns-add', 'tap', function(event) {
		if(!editFlag){
			if(!$('#poolName').val()){
				showMessage('输入名称为空！', 2000);
			}else{
				var dataHrefArr = $('.menu-body>div:last-child').attr('data-href').split('-');
				var addDataHref = dataHrefArr[0] + '-' + dataHrefArr[1] + '-' + menuNums;
				var html = '<div data-href="' + addDataHref + '"><span><strong>' + $('#poolName').val() + '</strong> <i style="display:block;"></i></span></div>'
				$('.menu-body').append(html);
				menuNums ++;
				refreshMenu('add',addDataHref,$('#poolName').val());
			}
		}
	});
});