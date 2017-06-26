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
			html+=	'<div class="pool-main animated" id="' + searchBoxId + '" data-pool="' + pool + '" data-start="0" data-size="15">'+
						'<div class="pool-items"></div>'+
						'<div class="pool-items-count srceen" style="display:none">'+
							'<div class="srceen-txt">'+
								'<span>该选股池共计</span>'+
								'<span class="red"></span>'+
								'<span>支股票</span>'+
							'</div>'+
							'<div class="load-more">'+
								'<i></i><span>加载更多</span>'+
							'</div>'+
						'</div>'+
					'</div>';
			scrollTopArr[searchBoxId] = 0;
		});
		$parent.append(html);
		menuNums = $('#scrollerPool li').length ;
		$('.pool-main').removeClass('fadeIn').addClass('fadeOut').css('display','none');
		$('.pool-main').eq(0).removeClass('fadeOut').css('display','block').addClass('fadeIn');
	}
    var handleRecordsEmpty = function ($parent){
    	var html = '<div class="pool-empty">' + '<img src="../../extension/images/pool.png" /><br />该选股池还没有数据' + '</div>';
		$parent.find(".pool-items").html(html);
		$parent.find('.pool-items-count').hide();
		$parent.find(".srceen-txt span.red").text(0);
        loadingHide($('.qltt-toast'));
    }
	var createHtml = function(parentId){
		var html = '';
		var $parent = $('#' + parentId);
		var pool = $parent.data('pool');
		var reqNum =  $parent.data('size');
		var reqreqStart =  $parent.data('start'); 
		var wrapperId = parentId + '-wrapper';
		var paginationId = parentId + '-page';
        
		$.ajax({
			url: '../userpool/pool.do',
			type: 'post',
			data: {
				'poolIndex': pool,
				'reqNum': reqNum,
				'reqStart': parseInt(reqreqStart)
			},
			dataType: 'json',
			success: function(data){
				$('.load-more i').removeClass('active');
				loadFlag = true;
				if(data.status==1){
					var html='';
					var rspnum = data.content.pageRsp.rspNum;
					var totalnum = data.content.pageRsp.totalNum;
					$parent.find(".srceen-txt span.red").text(totalnum);
					if(!totalnum){
						handleRecordsEmpty($parent);
					}else{
						$parent.find(".pool-items .pool-empty").remove();
						if(rspnum){
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
							$parent.find(".pool-items").append(html);
							loadingHide($('.qltt-toast'));
							$parent.data('start', parseInt(reqreqStart)+parseInt(reqNum));
						}
						$parent.find('.pool-items-count').show();
						if($parent.find('.pool-items .pool-item').length >= totalnum){
							 $parent.find('.load-more').hide();
						}else{
							 $parent.find('.load-more').show();
						}	
					}
				}else{
					if(data.message){
						$().alert(data.message);
					}
				}
            },
            error: function(jqXHR, textStatus, errorThrown){
            	$().alert("加载数据失败");
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
        	var html  = '<div class="pool-main animated" id="' + dataHref + '"  data-start="0" data-size="10">'+
							'<div class="srceen-txt">'+
								'<span>该选股池共计</span><span class="red"></span><span>支股票</span>'+
							'</div>'+
							'<div class="load-more">'+
								'<i></i><span>加载更多</span>'+
							'</div>'+
				        +'</div>';
        	$('#pool-wrapper').append(html);
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
			createHtml($(this).parent().parent().attr("id"));
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
			
		});
	})

	//选股池弹窗
	$('body').delegate('.poolItem-icon-cancle', 'tap', function() {
		var $this = $(this);
		var $poolmain = $(this).parents(".pool-main");
		$().confirm('您确定从该选股池中删除该股票吗？',function(){
			var data = {
					"stockCode": $this.parent().parent().find('.item-col-code').text(),
					"stockName": $this.parent().parent().find('.item-col-name').text(),
					"stockPool": [{"poolIndex": $('#headerPool li.active').data('pool')}]
				};
				$.ajax({
					url: '../userpool/unfollow.do',
					data: JSON.stringify(data),
					dataType: 'json',
					type: 'post',
					contentType: 'application/json;charset=UTF-8',
					success: function(data) {
						if (data.status == 1){
							var start = parseInt($poolmain.data("start"))-1;
							$poolmain.data("start",start<0?0:start);
							$this.parent().parent().remove();
							var totalNum = parseInt($poolmain.find('span.red').text())-1;
							if(!totalNum){
								handleRecordsEmpty($poolmain);
							}
							$poolmain.find('span.red').text(totalNum<0?0:totalNum);
							$().alert("成功移出选股池！");
						}else
							$().alert(data.message);
					},
					error: function(jqXHR, textStatus, errorThrown) {
						$().alert("移出选股池失败！");
					}/*,
					complete: function() {
						hideDialog($('#choose'), function(){
							createHtml($('#scrollerPool li.active').attr('data-href'));
						});
					}*/
			});
		})
	});
         
	$('#choose .dialog-btn-close,#choose .dialog-mask').tap(function() {
		hideDialog($('#choose'));
	});
	

	$('#choose .dialog-btn-confirm').tap(function() {
		hideDialog($('#choose'), function() {
			
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
						$().alert("成功加入选股池！");
					else
						$().alert(data.message);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					$().alert("加入选股池失败！");
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
	
	
	//弹窗添加股票
    //弹窗显示
	var stockPool = null;
	
    $('.pFooter-btn span').tap(function(event) {
        $('.dialog-add').removeClass('fadeOut').css('display','block').addClass('fadeIn');
        
        $('#keywordbox').focus();
        $('#keywordbox').val('');
        
        var stockPoolID = $('#headerPool ul .active').data('pool');   
        
        $.ajax({
			url: '../userpool/pool.do',
			type: 'post',
			data: {
				'poolIndex': stockPoolID,
				'reqNum': 9999,
				'reqStart': 1
			},
			dataType: 'json',
			success: function(data){
				stockPool = [];
				if(data.status==1 && data.content.pageRsp.rspNum > 0){    					
					var attnStock = data.content.attnStock;
					for(var i in attnStock){    								
						stockPool.push(attnStock[i].stockCode);
					}
				}   				
			},
			error: function(jqXHR, textStatus, errorThrown){
            	$().alert("加载数据失败");
                loadingHide($('.qltt-toast'));
            }
        });
        
       
    })

    //弹窗关闭
    $('.dialog-add-mask, .dialog-add-foot').tap(function(event) {
        $('.dialog-add').removeClass('fadeIn').addClass('fadeOut');
        setTimeout(function(){
            $('.dialog-add').css('display','none');
        }, 300);
        
        $('#keywordbox').val('');
        $('#add-body').html('');
        lastkeyword = '';
        stockPool = [];
        
        var activeid = $('#scrollerPool li.active').data('href');
        
        createHtml(activeid);
        
        event.stopPropagation();
        event.preventDefault();
    });

    //搜索框检索股票
    var lastkeyword = '';  //输入框上一次值

    $('#keywordbox').keyup(function(event) {
        //只能输入字母与数字
        $(this).val($(this).val().replace(/[^\w\.\/]/ig,''));
       
        if($(this).val().length >2 && $(this).val() != lastkeyword) {
        	lastkeyword = $(this).val();                  
            var keyword = $(this).val();
                  
            $('.qltt-toast').css({
                'display' : 'block',
                'opacity' : 1
            });
       	  
       	 	$.ajax({
                url: '../nametable/search.do',
                type: 'post',
            	dataType: 'json',
                data: {
                    key: keyword
                },
                success : function(data) {
                    var html = '';
                    if(data.length > 0){                   	                    	           
                        for( var i in data) {                      	                       	
                        	var isExist = $.inArray(data[i].fsCode, stockPool);                       	
                        	var activehtml = '';
                        	if(isExist == -1){                       		
                        		activehtml = '<span class="active">+</span>';
                        	}else{
                        		activehtml =  '<span>+</span>';
                        	}                      	
                        	var stock ='<div class="dialog-ab-tr">'
                        	 	+'<div class="dialog-ab-td">' + (data[i].fsMarketID==='0'?'上海':'深圳') +'</div>'
                        	 	+'<div class="dialog-ab-td">' + data[i].fsCode +'</div>'
                        	 	+'<div class="dialog-ab-td">' + data[i].fsName +'</div>'
                        	 	+'<div class="dialog-ab-td">' + activehtml  +'</div>'
                        	 	+'</div>';                        	
                        	html+=stock;                                
                        }
                        $('#empty').hide();
                        $('#add-body').html(html).show();
                                             
                    }else {
                        $('#add-body').html('').hide();
                        $('#empty').show();
                    } 

                    $('.qltt-toast').css({
                        'display' : 'none',
                        'opacity' : 0
                    });                    
                },
                error: function() {
                    $('.qltt-toast').css({
                        'display' : 'none',
                        'opacity' : 0
                    });
                    showMessage('查询失败！',2000);
                }
            });
       	 
        }
    });


    //添加与删除股票
    $('body').delegate('.dialog-ab-tr .dialog-ab-td:last-child', 'tap', function(event) {
    	
    	var stockPool = [];			
		stockPool.push({"poolIndex": $('#headerPool ul .active').data('pool')});
		
		var stockCode = $($(this).parent().children().get(1)).html();
		var stockName = $($(this).parent().children().get(2)).html();
	
		var data = {
			"stockCode": stockCode,
			"stockName": stockName,
			"stockPool": stockPool
		};
		var that = $(this).find('span');
		
        if($(this).find('span').hasClass('active')) {
			$.ajax({
				url: '../userpool/follow.do',
				data: JSON.stringify(data),
				dataType: 'json',
				type: 'post',
				contentType: 'application/json;charset=UTF-8',
				success: function(data) {
					if (data.status == 1){
						 showMessage('成功加入选股池！',1000);
						 $(that).removeClass('active').text('+');
					}else{
						showMessage(data.message,1000);
					}
				},
				error: function(jqXHR, textStatus, errorThrown) {
					showMessage("操作失败！",1000);
				}
			});
        }else {
        	$.ajax({
				url: '../userpool/unfollow.do',
				data: JSON.stringify(data),
				dataType: 'json',
				type: 'post',
				contentType: 'application/json;charset=UTF-8',
				success: function(data) {
					if (data.status == 1){
						 showMessage('取消加入选股池！',1000);
						 $(that).addClass('active').text('-');
					}else{
						showMessage(data.message,1000);
					}
				},
				error: function(jqXHR, textStatus, errorThrown) {
					showMessage("操作失败！",1000);
				}
			});
        }
    });
	
	
});