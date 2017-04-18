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
			html += '<div class="search-box swiper-slide" id="' + searchBoxId + '" ></div>';
			scrollTopArr[searchBoxId] = 0;
		});
		$parent.append(html);
	};

	var createHtml = function($parent) {
		var html = '';
		$.ajax({
			url : '../../extension/data/search.json',
			type : 'post',
			success : function(resData) {
				for ( var i in resData) {
					html += '<div class="search-item ani" swiper-animate-effect="fadeIn" swiper-animate-duration="1s" swiper-animate-delay="0s" >'
						+ '<div class="search-head">'
						+ resData[i].searchHead
						+ '</div>'
						+ '<div class="search-body"><a href="result.html">'
						+ resData[i].searchBody
						+ '</a></div>' + '</div>'
				}

				$parent.append(html);
				loadingHide($('.qltt-toast'));
			},
			error : function(xhr, type) {
				alert('获取数据失败!');
			}
		});

	};

	var init = function() {
		loadingShow($('.qltt-toast'));
		createSearchBox($('.swiper-wrapper'));
		createHtml($('#search-box-0'));
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

					headIScroll.scrollToElement($('#header li').get(searchSwiper.activeIndex), 30);
				},
				onSlideChangeEnd : function(swiper) {
					$('#header li').removeClass('active');
					$('#header li').eq(searchSwiper.activeIndex).addClass('active');
					swiperAnimate(swiper); // 每个slide切换结束时也运行当前slide动画
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

	init();
});