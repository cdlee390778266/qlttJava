/* 
 * @Author: lee
 * @Date:   2017-04-11 16:23:54
 * @Last Modified time: 2017-04-12 18:19:49
 */
$(document).ready(function() {
	var url = 'pool.do';
	var refreshFlag = true;
	
	var refreshHeight = function (){
        $('.result-tags-head span').css('top',($('.result-tags-head').height()-$('.result-tags-head span').height())/2);
    }

	$(window).resize(function(event) {
        refreshHeight();
    });

	var refreshData = function(url, $parent) {
		var start = $parent.data('start');
		var size = $parent.data('size');
		var tacTic = $('#tacTic').val();
		$.ajax({
			url : url,
			type : 'post',
			data : {
				'start' : start, 
				'size' : size,
				'tacTic' : tacTic,
				'tacPrm' : '0'
			},
			success : function(data) {
				var html = '';
				data = $.parseJSON(data);
				var stocks = data.dprtlist;
				if (stocks != null) {
					for ( var i in stocks) {
						var isInPool = stocks[i].isSelected?"active":"";
						html += '<div class="screen-item" id="screen-item-'+stocks[i].detail.stockcode+'">'
							+ '<div class="item-col-1">'
							+ '<span class="item-col-name">' + stocks[i].detail.stockname + '</span>'
							+ '<span class="blue item-col-code">' + stocks[i].detail.stockcode + '</span>'
							+ '</div>'
							+ '<div class="item-col-2">' + stocks[i].detail.efftime + '</div>'
							+ '<div class="item-col-3">' + stocks[i].detail.remarks + '</div>'
							+ '<div class="item-col-4">'
							+ '<a href="javascript:void(0);" class="recommend"></a>'
							+ '<a href="javascript:void(0);" class="choose  '+isInPool+'"></a>'
							+ '</div>' + '</div>';
					}
				}
				$parent.append(html);
				var totalnum = data.pgrsp.totalnum;
				$('.srceen-txt .red').text(totalnum);
				if($parent.find(".screen-item").length < totalnum )
					displayLoadMore($(".load-more"),"show");
				else
					displayLoadMore($(".load-more"),"hide");
				
				$('.load-more i').removeClass('active');
				refreshFlag = true;
				$parent.data('start', parseInt(start) + size);
			},
			error : function(xhr, type) {
				alert('获取数据失败！');
			}
		});
	}
	
	//显示或隐藏更多
	var displayLoadMore = function($div,display){
		if(display=="hide")
			$div.hide();
		else
			$div.show();
	}
	var showDialog = function($dialogEle, callBack) {
		$dialogEle.removeClass('fadeOut').css('display', 'block').addClass('fadeIn');
		if (typeof callBack == 'function')
			callBack();
	}

	var hideDialog = function($dialogEle, callBack) {
		$dialogEle.removeClass('fadeIn').addClass('fadeOut').css('display', 'none');
		if (typeof callBack == 'function')
			callBack();
	}

	var init = function() {
		refreshHeight();
		refreshData(url, $('.screen-main'));

	}

	//关注和取消关注
	$('.result-btns span').tap(function() {
		var follow = $(this);
		if ($(this).hasClass('active')) {
			//取消关注
			$.ajax({
				url : '../myattention/unfollow.do',
				data : {
					"tacTic" : $("#tacTic").val(),
					"tacPrm" : 0
				},
				dataType : "json",
				error : function(jqXHR, textStatus, errorThrown) {
					alert(textStatus);
				},
				success : function(data, textStatus, jqXHR) {
					if (data.status == 1)
						follow.removeClass('active');
					else
						alert(data.message);
				}
			});
		} else {
			//关注
			$.ajax({
				url : '../myattention/follow.do',
				data : {
					"tacTic" : $("#tacTic").val(),
					"tacPrm" : 0
				},
				dataType : "json",
				error : function(jqXHR, textStatus, errorThrown) {
					alert("服务器异常！");
				},
				success : function(data, textStatus, jqXHR) {
					if (data.status == 1)
						follow.addClass('active');
					else
						alert(data.message);
				}
			});
		}
	})
					
	$('body').delegate('.load-more', 'tap', function() {
		if (refreshFlag) {
			refreshFlag = false;
			$(this).find('i').addClass('active');
			refreshData(url, $('.screen-main'));
		}
	});

	// 荐股池弹窗
	$('body').delegate('.recommend', 'tap', function() {
		showDialog($('#recommend'));
	});

	$('#recommend .dialog-btn-close,#recommend .dialog-mask').tap(function() {
		hideDialog($('#recommend'));
	});

	//加入选股池提交
	$('#recommend .dialog-btn-confirm').tap(function() {
		hideDialog($('#recommend'), function() {
		
		});
	});

	// 选股池弹窗
	$('body').delegate('.choose', 'tap', function() {
		var stockcode = $(this).parent().parent().find('.item-col-code').text();
		$("#code").val(stockcode);
		$("#name").val($(this).parent().parent().find('.item-col-name').text());
		$("#choosePool").html('<option value="1">选股池A</option><option value="2">选股池B</option><option value="3">选股池C</option>');
		//ajax加载该股票已选股票池
		$.ajax({
			url : 'poolindexs.do',
			data : {
				"stockcode" : stockcode
			},
			dataType : "json",
			error : function(jqXHR, textStatus, errorThrown) {
				alert("加载数据失败！");
			},
			success : function(data, textStatus, jqXHR){
				//多选下拉框置于所有未选中的状态
				if (data && data.length >= 0){
					for ( var i in data){
						$("#choosePool option[value='"+data[i].poolIndex+"']").attr("selected",true);
					}
				}
				showDialog($('#choose'));	
			}
		});
	});

	$('#choose .dialog-btn-close,#choose .dialog-mask').tap(function() {
		$("#code").val("");
		$("#name").val("");
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
					if (data.status == 1){
						var selecttor = "#screen-item-"+$("#code").val()+" a.choose";
						if(!stockPool.length)
							$(selecttor).removeClass("active");
						else
							$(selecttor).addClass("active");
						
						alert("操作成功！");
					}else
						alert(data.message);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("操作失败！");
				}
			});
		});
	});

	$('body').delegate('#choosePool', 'change', function(event) {
		$('.select-mask').text($(this).val());
	});

	init();
});