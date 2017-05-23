/* 
 * @Author: lee
 * @Date:   2017-04-07 17:02:56
 * @Last Modified time: 2017-04-13 11:00:30
 */
$(document).ready(function() {
	var searchSwiper;
	var scrollTopArr = {};

	var refreshScrollTop = function(prevKey, currentKey) {
		scrollTopArr[prevKey] = getScrollTop();
		scrollTop(scrollTopArr[currentKey]);
	};
	
	var createSearchBox = function($parent) {
		var html = '';
		$('#scroller li').each(function(index, val) {
			var searchBoxId = 'search-box-' + index;
			html += '<div class="search-box swiper-slide" data-group="' + $(this).data('group') + '" id="' + searchBoxId + '" ></div>';
			scrollTopArr[searchBoxId] = 0;
		});
		$parent.append(html);
	};

	var createHtml = function($parent) {
		var html = '';
		var group = $parent.data("group");
		$.ajax({
			url : 'child.do',
			data : {"tacgroup" : group},
			type : 'post',
			success : function(data) {
				data = $.parseJSON(data);
				if (data != null) {
					if (group == "combination") {
						var members = data.content.tacMenu;
						for ( var i in members) {
							var href = encodeURI("../stock/home.do?tactic=" + members[i].tacTic + "&tacname=" + members[i].tacName+"&isCombRequest=true");
							html += '<div class="search-item ani" swiper-animate-effect="fadeIn" swiper-animate-duration="1s" swiper-animate-delay="0s" >'
								+ '<div class="search-head search-head-del" data-tacTic="'+members[i].tacTic+'">'
								+ members[i].tacName
								+ '<i></i>'
								+ '</div>'
								+ '<div class="search-body"><a href="' + href + '">'
								+ members[i].tacDetail
								+ '</a></div>' + '</div>';
						}
					} else {
						if(data.children.length==0){
							var members = data.member.ptgmlist;
							for ( var i in members) {
								var href = encodeURI("../stock/home.do?tactic=" + members[i].tactic + "&tacname=" + members[i].tacname);
								html += '<div class="search-item ani" swiper-animate-effect="fadeIn" swiper-animate-duration="1s" swiper-animate-delay="0s" >'
									+ '<div class="search-head">'
									+ members[i].tacname
									+ '</div>'
									+ '<div class="search-body"><a href="' + href + '">'
									+ members[i].tacdetail
									+ '</a></div>' + '</div>';
							}
						}else{
							var resData = data.children;
							 for(var i in resData){
			                        html += '<div class="search-slide">'
			                             +      '<div class="search-slide-head">' + resData[i]['info']['grpname'] + '<span></span></div>'
			                             +      '<div class="search-slide-body animated" >'

			                        for(var j in resData[i]['member'].ptgmlist){
			                        	var ptgm = resData[i]['member'].ptgmlist[j];
			                        	var href = encodeURI("../stock/home.do?tactic=" +ptgm.tactic + "&tacname=" +ptgm.tacname);
			                            html += '<div class="search-item ani" swiper-animate-effect="fadeIn" swiper-animate-duration="1s" swiper-animate-delay="0s" >'
			                                 +      '<div class="search-head">' + ptgm.tacname + '</div>'
			                                 +      '<div class="search-body"><a href="'+href+'">' + ptgm.tacdetail + '</a></div>'
			                                 +   '</div>'
			                        }

			                        html +=     '</div>';
			                        html += '</div>';
			                    }
			                    $parent.find('.search-slide').eq(0).addClass('active');
			                    $parent.find('.search-slide').eq(0).find('.search-slide-body');
						}
						
					}
				}
				
				$parent.append(html);
				loadingHide($('.qltt-toast'));
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert('获取数据失败！');
			}
		});

	};

	var init = function() {
		loadingShow($('.qltt-toast'));
		createSearchBox($('.swiper-wrapper'));
		createHtml($('.search-box ').first());
		searchSwiper = new Swiper(
			'.swiper-container',
			{
				spaceBetween : 30,
				observer : true,
				onInit : function(swiper) {
					swiperAnimateCache(swiper); // 隐藏动画元素
					swiperAnimate(swiper); // 初始化完成开始动画
				},
				onSlideChangeStart : function() {
					refreshScrollTop('search-box-' + searchSwiper.previousIndex, 'search-box-' + searchSwiper.activeIndex);
					if ($('#search-box-' + searchSwiper.activeIndex).find('.search-item ').length <= 0) {
						loadingShow($('.qltt-toast'));
						createHtml($('#search-box-' + searchSwiper.activeIndex));
					}
					if (searchSwiper.activeIndex == 0) {
						$('.pFooter-btn').show();
					} else {
						$('.pFooter-btn').hide();
					}

					headIScroll.scrollToElement($('#header li').get(searchSwiper.activeIndex), 30);
				},
				onSlideChangeEnd : function(swiper) {
					$('#header li').removeClass('active');
					$('#header li').eq(searchSwiper.activeIndex).addClass('active');
					swiperAnimate(swiper);   // 每个slide切换结束时也运行当前slide动画
				}
			}
		);

	}

	$('#header').delegate('li', 'tap', function(event) {
		var searchBoxId = 'search-box-' + $(this).index();
		$('#header li').removeClass('active');
		$(this).addClass('active');
		searchSwiper.slideTo($(this).index());
	});
	
	$('body').delegate('.search-slide-head', 'tap', function(event) {
        $(this).parent().toggleClass('active');
    });
	
	var deleteTacMenu = function($searchitem,tactic){
		$.ajax({
			url : '../combined/delcombined.do',
			data : {"tactic":tactic},
			type : 'post',
			success : function(data) {
				data = $.parseJSON(data);
				if(data.status==1){
					$searchitem.remove();
					alert("该组合指标已成功删除");
				}else{
					alert(data.message);
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert('系统错误，请稍后重试或联系管理员');
			}
		});
	}
	
	$('body').delegate('.search-item', 'tap', function(event) {
		var $searchitem = $(this);
        if(event.target.tagName=='I'){
            if(window.confirm('您确定要从收藏中删除该组合指标吗？')){
            	var tactic = $searchitem.find(".search-head").attr("data-tacTic");
            	var data = {"tacTic" : tactic,"tacPrm":0};
            	console.log(data);
            	$.ajax({
        			url : '../myattention/isfollowed.do',
        			data : data,
        			type : 'post',
        			success : function(data) {
        				data = $.parseJSON(data);
        				if(data.content){//如果已经被关注
        					alert("该指标已经被关注，请先取消关注再删除");
        				}else{
        					deleteTacMenu($searchitem,tactic);
        				}
        			},
        			error : function(jqXHR, textStatus, errorThrown) {
        				alert('系统错误，请稍后重试或联系管理员');
        			}
        		});
            }
        }else{
            location.href = $(this).find('a').attr('href');
        }
        
    });
	init();
});