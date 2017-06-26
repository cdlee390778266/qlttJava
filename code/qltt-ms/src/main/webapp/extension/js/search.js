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
	
	var loadGroups = function(){
		var url = contextPath+'/webapp/search/findgroups.do';
		
		$.ajax({
			url : url,
			type : 'post',
			dataType: 'json',
			success : function(data) {
				if (data != null) {					
					if(data.ptgcnt > 0){						
						data.ptglist.forEach(function(idxGroup){
							if(idxGroup.grplevel == 1)
								$('#scroller ul').append('<li data-group="'+idxGroup.tacgroup+'">'+idxGroup.grpname+'</li>');
						});
					}
				}
				$('#scroller').css('width',$('#scroller li').length*90+20);
		        headIScroll = new IScroll('#header', { 
		        eventPassthrough: true, 
		        scrollX: true, 
		        scrollY: false, 
		        preventDefault: false 
		        });
		        init();
			},
			error : function(){
				
			}
		});
	};

	var createHtml = function($parent,init) {
		var html = '';
		var group = $parent.data("group");
		$.ajax({
			url : contextPath+'/webapp/search/child.do',
			data : {"tacgroup" : group},
			type : 'post',
			success : function(data) {
				data = $.parseJSON(data);
				if (data != null) {
					if (group == "combination") {
						var members = data.content.tacMenu;
						if(!members.length){
							if(init=='init'){
								searchSwiper.slideTo(1);
								return;
							}
						}
						for ( var i in members) {
							var href = encodeURI(contextPath+"/webapp/stock/home.do?tactic=" + members[i].tacTic + "&tacname=" + members[i].tacName+"&isCombRequest=true");
							html += '<div class="search-item anisearch-item-time" swiper-animate-effect="fadeIn" swiper-animate-duration="1s" swiper-animate-delay="0s" >'
								+ '<div class="search-head search-head-del" data-tacTic="'+members[i].tacTic+'">'
								+ members[i].tacName
								+ '<i></i>'
								+ '</div>'
								+ '<div class="search-body"><a href="' + href + '">'
								+ members[i].tacDetail
								+ '</a></div>' 
								+ '<div class="search-foot" data-tacTic="'+members[i].tacTic+'">'
                                + '<span class="">未关注</span>'
                                + '</div>'
								+ '</div>';
						}
					} else {
						if(data.children.length==0){
							var members = data.member.ptgmlist;
							for ( var i in members) {
								var href = encodeURI(contextPath+"/webapp/stock/home.do?tactic=" + members[i].tactic + "&tacname=" + members[i].tacname);
								html += '<div class="search-item ani search-item-time" swiper-animate-effect="fadeIn" swiper-animate-duration="1s" swiper-animate-delay="0s" >'
									+ '<div class="search-head" data-ticTic="'+members[i].tactic+'" >'
									+ members[i].tacname
									+ '</div>'
									+ '<div class="search-body"><a href="' + href + '">'
									+ members[i].tacdetail
									+ '</a></div>' 
									+ '<div class="search-foot" data-tacTic="'+members[i].tactic+'">'
	                                + '<span class="">未关注</span>'
	                                + '</div>'
									+ '</div>';
							}
						}else{
							var resData = data.children;
							 for(var i in resData){
		                        html += '<div class="search-slide">'
		                             +      '<div class="search-slide-head">' + resData[i]['info']['grpname'] + '<span></span></div>'
		                             +      '<div class="search-slide-body animated" >'

		                        for(var j in resData[i]['member'].ptgmlist){
		                        	var ptgm = resData[i]['member'].ptgmlist[j];
		                        	var href = encodeURI(contextPath+"/webapp/stock/home.do?tactic=" +ptgm.tactic + "&tacname=" +ptgm.tacname);
		                            html += '<div class="search-item ani" swiper-animate-effect="fadeIn" swiper-animate-duration="1s" swiper-animate-delay="0s" >'
		                                 +      '<div class="search-head" data-ticTic="'+ptgm.tactic+'">' + ptgm.tacname + '</div>'
		                                 +      '<div class="search-body"><a href="'+href+'">' + ptgm.tacdetail + '</a></div>'
		 								 + '<div class="search-foot" data-tacTic="'+ptgm.tactic+'">'
		                                 + '<span class="">未关注</span>'
		                                 + '</div>'
		                                 +   '</div>'
		                        }

		                        html +=     '</div>';
		                        html += '</div>';
		                    }
		                   
						}
					}
				}
				$parent.html(html);
				$parent.find('.search-slide').eq(0).addClass('active');
				loadingHide($('.qltt-toast'));
				
				updateFollow();
			},
			error : function(jqXHR, textStatus, errorThrown) {
				$().alert('获取数据失败！');
			}
		});
	};

	loadGroups();
	
	var updateFollow = function(){
		var url = contextPath+'/webapp/myattention/followlist.do';
		
		$.ajax({
			url : url,
			type : 'post',
			dataType: 'json',
			success : function(data) {				
				if(data != null && data.length > 0){					
					var follow = [];					
					for(var i in data){
						follow.push(data[i].tacTic);
					}
					
					$('.swiper-slide-active .search-foot').each(function(){
						var tactic = String($(this).data('tactic'));
						if($.inArray(tactic, follow) != -1){
							if(!$(this).find('span').hasClass('active')){								
								$(this).find('span').addClass('active');
								$(this).find('span').text('已关注')
							}
						}else{
							if($(this).find('span').hasClass('active')){								
								$(this).find('span').removeClass('active');
								$(this).find('span').text('未关注')
							}
						}
					});
				}
			},
			error : function(){
				
			}
		});
		
	};
	
	var init = function() {
		
		loadingShow($('.qltt-toast'));
		createSearchBox($('.swiper-wrapper'));
		createHtml($('.search-box ').first(),'init');
		searchSwiper = new Swiper(
			'.swiper-container',
			{
				spaceBetween : 30,
				observer : true,
				autoHeight: true, //高度随内容变化
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
					searchSwiper.update();
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
			url : contextPath+'/webapp/combined/delcombined.do',
			data : {"tactic":tactic},
			type : 'post',
			success : function(data) {
				data = $.parseJSON(data);
				if(data.status==1){
					$searchitem.remove();
					$().alert("该组合指标已成功删除");
				}else{
					$().alert(data.message);
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				$().alert('系统错误，请稍后重试或联系管理员');
			}
		});
	}
	
	$('body').delegate('.search-item', 'tap', function(event) {
		var $searchitem = $(this);
        if(event.target.tagName=='I'){
        	$().confirm("您确定要从收藏中删除该组合指标吗？",function(){
        		var tactic = $searchitem.find(".search-head").attr("data-tacTic");
            	var data = {"tacTic" : tactic,"tacPrm":0};
            	
            	$.ajax({
        			url : contextPath+'/webapp/myattention/isfollowed.do',
        			data : data,
        			type : 'post',
        			success : function(data) {
        				data = $.parseJSON(data);
        				if(data.content){//如果已经被关注
        					$().alert("该指标已经被关注，请先取消关注再删除");
        				}else{
        					deleteTacMenu($searchitem,tactic);
        				}
        			},
        			error : function(jqXHR, textStatus, errorThrown) {
        				$().alert('系统错误，请稍后重试或联系管理员');
        			}
        		});
        	})
        }else{
            location.href = $(this).find('a').attr('href');
        }
        
    });
});