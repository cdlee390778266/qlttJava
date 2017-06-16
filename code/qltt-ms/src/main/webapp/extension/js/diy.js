/* 
 * @Author: lee
 * @Date:   2017-04-07 14:10:42
 * @Last Modified time: 2017-04-27 15:08:50
 */
$(function() {
	var index = 0;
	var tagNums = 5;   // 最多可选中的组合指标个数
	var $errorEle = $('#error');

	var createSearchBox = function($parent) {
		var html = '';
		$('#scroller li').each(function(index, val) {
			var searchBoxId = 'diy-main-' + index;
			html += '<div class="diy-main animated" id="' + searchBoxId + '" data-group="' + $(this).data('group') + '"></div>';
		});
		$parent.append(html);
	}

	var createHtml = function(parentId) {
		var $parent = $('#' + parentId);
		var wrapperId = parentId + '-wrapper';
		var paginationId = parentId + '-page';
		
		//获取数据
		$.ajax({
			url : 'grouptag.do',
			data : {"tacgroup" : $parent.data("group")},
			type : 'post',
			success : function(data) {
				data = $.parseJSON(data);
				
				var members = data;   //数据
				var total = data.length;   //数据总量
				var size = 9;   //分页大小
				var pages = Math.ceil(total / size);   //总页数
				var html = $('<div class="diy-box ani" swiper-animate-effect="fadeIn" swiper-animate-duration="1s" swiper-animate-delay="0s">'
					+ '<div class="tag swiper-container swiper-container-horizontal" id="' + wrapperId + '" >'
					+ '<div class="swiper-wrapper">'
					+ '</div>'
					+ '</div>'
					+ '</div>');
				
				for (var pageNum = 0; pageNum < pages; pageNum ++) {
					var elements = [];
					elements.push('<div class="swiper-slide" id="tag' + pageNum + '" >');
					for (var start = pageNum * size; start < pageNum * size + size; start ++) {
						if (start >= total)
							break;
						elements.push('<span id="c_' + members[start].tactic + '" data-tactic="' + members[start].tactic + '">'+ members[start].tacname + '</span>');
					}
					elements.push('</div>')
					html.find('#' + wrapperId + ' .swiper-wrapper').append(elements.join(''));
				}
				
				
				html.find('#' + wrapperId).append('<div class="swiper-button-next"></div>' 
					+ '<div class="swiper-button-prev swiper-button-disabled"></div>' 
					+ '<div class="swiper-mes" id="' + paginationId + '"></div>');
				
				$parent.append(html);
				$('.diy-main').removeClass('fadeIn').addClass('fadeOut').css('display', 'none');
				$parent.removeClass('fadeOut').css('display', 'block').addClass('fadeIn');
				loadingHide($('.qltt-toast'));
				
				new Swiper('#' + wrapperId, {
					pagination : '#' + paginationId,
					paginationClickable : '#' + paginationId,
					nextButton : '.swiper-button-next',
					prevButton : '.swiper-button-prev',
					paginationType : 'fraction',
					paginationFractionRender : function(swiper, currentClassName, totalClassName) {
						return '<i class="' + currentClassName + ' red"></i>' + ' / ' + '<i class="' + totalClassName + '"></i>';
					},
					spaceBetween : 30
				});
			},
			error : function(jqXHR, textStatus, errorThrown) {
				$().alert("获取数据失败")
			}
		});
	}

	var loadMore = function(url, $parent) {
		var indices = [];
		$('.check-tags span').each(function(idx, e) {
			indices.push({"srcTactic": $(e).data("tactic"), "srcTacticPrm" : 0});
		});
		
		var start = $(".srceen .screen-main").data("start");
		var size = $(".srceen .screen-main").data("size");
		
		$.ajax({
			url : url,
			data : JSON.stringify({
				"start" : start, 
				"size" : size,
				"indices" : indices
			}),
			type : "post",
			contentType : "application/json;charset=UTF-8",
			success : function(data) {
				var html = '';
				data = JSON.parse(data);
				if (data && data.dpcblist) {
					var stocks = data.dpcblist;
					for (var i in stocks) {
						html += '<div class="screen-item"  >'
							+ '<div class="item-col-1">'
							+ '<span class="item-col-name">' + stocks[i].stockname + '</span>'
							+ '<span class="blue item-col-code">' + stocks[i].stockcode + '</span>' + '</div>'
							+ '<div class="item-col-3">潜力股</div>'
							+ '<div class="item-col-4">'
							+ '<a href="javascript:void(0);" class="recommend" ></a>'
							+ '<a href="javascript:void(0);" class="choose"></a>'
							+ '</div>' + '</div>';
					}
					$parent.parent().find(".stock-num").text(data.pgrsp.totalnum);
					$parent.data("start", parseInt(start) + stocks.length);
				}
				$parent.append(html);
				var totalnum = data.pgrsp.totalnum;
				$('.srceen-txt .red').text(totalnum);
				if($parent.find(".screen-item").length < totalnum )
					displayLoadMore($(".load-more"),"show");
				else
					displayLoadMore($(".load-more"),"hide");
				$('.load-more i').removeClass('active');
				loadFlag = true;
			},
			error : function(xhr, type) {
				$().alert('获取数据失败！');
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
		loadingShow($('.qltt-toast'));
		createSearchBox($('#diy-wrapper'));
		createHtml('diy-main-0');

		if ($('.check-tags span').length <= 0) {
			$('.check-tags').html('<div class="tag-empty">您没有选中任何指标！</div>');
			$('.check-btn a strong').addClass('disabled');
		}

		$errorEle.find('span').html('最多只能选中' + tagNums + '个指标！');
	}

	init();

	$('#header').delegate('li', 'tap', function(event) {
		if ($(this).hasClass('active'))
			return;
		var searchBoxId = 'diy-main-' + $(this).index();
		$('#header li').removeClass('active');
		$(this).addClass('active');

		if (!$('#' + searchBoxId).text()) {
			createHtml(searchBoxId);
		} else {
			$('.diy-main').removeClass('fadeIn').addClass('fadeOut').css('display', 'none');
			$('#' + searchBoxId).removeClass('fadeOut').css('display', 'block').addClass('fadeIn');
		}

	});

	var loadFlag = true;
	// 加载更多
	$('body').delegate('.load-more', 'tap', function(event) {
		if (loadFlag) {
			loadFlag = false;
			$(this).find('i').addClass('active');
			loadMore('filtration.do', $(this).prev());
		}

	});

	// 收藏指标弹窗
	$('body').delegate('.sc-btn', 'tap', function() {
		showDialog($('#zdyzh'));
	});

	$('#zdyzh .dialog-btn-close,#zdyzh .dialog-mask').tap(function() {
		hideDialog($('#zdyzh'));
	})

	// 收藏指标
	$('#zdyzh .dialog-btn-confirm').tap(function() {
		var indices = [];
		$('.check-tags span').each(function(idx, e) {
			indices.push({"srcTactic": $(e).data("tactic"), "srcTacticPrm" : 0});
		});
		$.ajax({
			url : "collect.do",
			type : "post",
			contentType : "application/json;charset=UTF-8",
			dataType : "json",
			data : JSON.stringify({
				"indices": indices,
				"addTacMenu" : {
					"tacName" : $("#zdyzh .sc-title").val(),
					"tacDetail" : $("#zdyzh .sc-text").val()
				}
			}),
			success : function(data) {
				if (data != null && data.status == 1) {
					hideDialog($('#zdyzh'), function() {
						history.go(-1);
					});
				} else {
					$().alert(data.message);
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				$().alert('收藏失败');
			}
		});
	})

	// 荐股池弹窗
	$('body').delegate('.recommend', 'tap', function() {
		showDialog($('#recommend'));
	});

	$('#recommend .dialog-btn-close,#recommend .dialog-mask').tap(function() {
		hideDialog($('#recommend'));
	})

	$('#recommend .dialog-btn-confirm').tap(function() {
		hideDialog($('#recommend'), function() {
	
		});
	})

	// 选股池弹窗
	$('body').delegate('.choose', 'tap', function() {
		$("#code").val($(this).parent().parent().find('.item-col-code').text());
		$("#name").val($(this).parent().parent().find('.item-col-name').text());
		showDialog($('#choose'));
	});

	$('#choose .dialog-btn-close,#choose .dialog-mask').tap(function() {
		$("#code").val("");
		$("#name").val("");
		hideDialog($('#choose'));
	})

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
					$().alert("加入选股池失败");
				}
			});
		});
	})

	$('body').delegate('#choosePool', 'change', function(event) {
		$('.select-mask').text($(this).val());
	});

	// 选中与取消组合指标
	$('body').delegate('.swiper-slide span', 'tap', function(event) {
		var id = $(this).data('tactic');

		if ($(this).hasClass('active')) {
			$(this).removeClass('active');
			$('#' + id).remove();
			if ($('.check-tags span').length <= 0) {
				$('.check-tags').html('<div class="tag-empty">您没有选中任何指标！</div>');
				$('.check-btn strong').addClass('disabled');
			}
		} else {
			if ($('.check-tags span').length < tagNums) {
				$(this).addClass('active');
				$('.tag-empty').remove();
				if ($('#' + id).length <= 0) {
					$('.check-tags').append('<span data-tactic="' + id + '" id="' + id + '"><i></i><strong>' + $(this).text() + '</strong></span>');
				}
				$('.check-btn strong').removeClass('disabled');
			} else {
				$errorEle.removeClass('fadeOut');
				$errorEle.addClass('fadeIn');
				$errorEle.css('display', 'block');
			}
		}
		filtration();
	});

	$('#error').tap(function(e) {
		e.stopPropagation();
		$errorEle.removeClass('fadeIn');
		$errorEle.addClass('fadeOut');
		$errorEle.css('display', 'none');
	})

	$('body').delegate('.check-tags i', 'tap', function(event) {
		var $ele = $(this).parent();
		var id = $ele.attr('id');
		$('#c_' + id).removeClass('active');
		$ele.remove();
		filtration();
		if ($('.check-tags span').length <= 0) {
			$('.check-tags').html('<div class="tag-empty">您没有选中任何指标！</div>');
			$('.check-btn strong').addClass('disabled');
		}
	});
	
	function filtration() {
		var indices = [];
		$('.check-tags span').each(function(idx, e) {
			indices.push({"srcTactic": $(e).data("tactic"), "srcTacticPrm" : 0});
		});
		
		$(".srceen .screen-main").empty();
		$(".srceen .stock-num").text("0");
		var start = 1;
		var size = $(".srceen .screen-main").data("size");
		if (indices.length > 0) {
			$.ajax({
				url : "filtration.do",
				data : JSON.stringify({
					"start" : start, 
					"size" : size,
					"indices" : indices
				}),
				type : "post",
				contentType : "application/json;charset=UTF-8",
				success : function(data) {
					data = JSON.parse(data);
					if (data && data.dpcblist) {
						var stocks = data.dpcblist;
						var html = [];
						for ( var i in stocks) {
							html.push("<div class=\"screen-item\">");
							html.push("<div class=\"item-col-1\">");
							html.push("<span class=\"item-col-name\">" + stocks[i].stockname + "</span>");
							html.push("<span class=\"blue item-col-code\">" + stocks[i].stockcode + "</span>")
							html.push("</div>");
							//html.push("<div class=\"item-col-2 \">" + new Date().toLocaleString() + "</div>");
							html.push("<div class=\"item-col-3 \">潜力股</div>");
							html.push("<div class=\"item-col-4\">");
							html.push("<a href=\"javascript:void(0);\" class=\"recommend\"></a>");
							html.push("<a href=\"javascript:void(0);\" class=\"choose\"></a>");
							html.push("</div>");
							html.push("</div>");
						}
						$(".srceen .stock-num").text(data.pgrsp.totalnum);
						$(".srceen .screen-main").data("start", parseInt(start) + stocks.length);
						$(".srceen .screen-main").append(html.join(""));
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					//
				}
			});
		}
	}
})