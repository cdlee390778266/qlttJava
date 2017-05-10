/* 
 * @Author: lee
 * @Date:   2017-04-11 16:23:54
 * @Last Modified time: 2017-04-12 18:19:49
 */
$(document).ready(function() {
	var url = 'pool.do';
	var refreshFlag = true;

	var refreshHeight = function() {
		$('.result-main').height($('.img-responsive').height());
		$('.result-tags-box').height($('.result-tags').height());
		$('.result-tags-box').width($('.result-tags').width());
	}

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
						html += '<div class="screen-item"  >'
							+ '<div class="item-col-1">'
							+ '<span>'
							+ stocks[i].stockname
							+ '</span>'
							+ '<span class="blue">'
							+ stocks[i].stockcode
							+ '</span>'
							+ '</div>'
							+ '<div class="item-col-2">'
							+ stocks[i].efftime
							+ '</div>'
							+ '<div class="item-col-3">'
							+ stocks[i].remarks
							+ '</div>'
							+ '<div class="item-col-4">'
							+ '<a href="javascript:void(0);" class="recommend" ></a>'
							+ '<a href="javascript:void(0);" class="choose"></a>'
							+ '</div>' + '</div>';
					}
					$('.srceen-txt .red').text(data.pgrsp.rspnum);
				}

				$parent.append(html);
				$('.load-more i').removeClass('active');
				refreshFlag = true;

				$parent.data('start', parseInt(start) + size);
			},
			error : function(xhr, type) {
				alert('获取数据失败！');
			}
		});
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

	$('#recommend .dialog-btn-confirm').tap(function() {
		hideDialog($('#recommend'), function() {
			console.log('提交');
		});
	});

	// 选股池弹窗
	$('body').delegate('.choose', 'tap', function() {
		showDialog($('#choose'));
	});

	$('#choose .dialog-btn-close,#choose .dialog-mask').tap(
		function() {
			hideDialog($('#choose'));
	});

	$('#choose .dialog-btn-confirm').tap(function() {
		hideDialog($('#choose'), function() {
			console.log('提交');
		});
	});

	$('body').delegate('#choosePool', 'change', function(event) {
		$('.select-mask').text($(this).val());
	});

	init();
});