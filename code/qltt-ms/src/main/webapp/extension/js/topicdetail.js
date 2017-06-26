/* 
* @Author: lee
* @Date:   2017-04-07 15:15:54
* @Last Modified time: 2017-06-16 14:47:19
*/

$(document).ready(function(){
	
	var findPagePara = function(p){
		var reg = new RegExp("(^|&)" + p + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) return unescape(r[2]); return null; //返回参数值
	}

	var topicid = findPagePara('id');


	/*
    var height = 37;

    //下拉功能
    $('.hd-tags-slide').tap(function(){
        if($(this).hasClass('active')) {
            $(this).removeClass('active');
            $('.hd-tags-items').height(height);
        }else {
            $(this).addClass('active');
            $('.hd-tags-items').height('auto');
        }
    });
    */
    
    var start = 1;
    var size = 8;
    var lastlabels = [];

    var loadBlkStock = function() {
        var labels = [];
        $('.hd-tags-items span').each(function(){
        	if($(this).hasClass('active')){
        		labels.push($(this).text());
        	}
        });
        
        if(labels.length == 0) {
        	$('.qltt-toast').hide();
        	return;
        }
        
        if(lastlabels.sort().toString() != labels.sort().toString()){
        	start = 1;
        	size = 8;
        	$('.screen-main').children().remove();
        	$('.screen-main').html('');
        }else{
        	$('.screen-main').find('.blkloadmore').remove();
        }
        lastlabels = labels;

        var blkurl = contextPath+'/webapp/hottopic/stockbyblock.do';	

        $.ajax({
    		url : blkurl,
    		type : 'post',
    		dataType: 'json',
    		data : 
    		    {'labels' : labels,
    			 'start' : start,
    			 'size' :size
    		    },
            success : function(data) {  	
            	if(data.rspnum > 0){	
            		data.tables.forEach(function(value,index,array){           			
            			var item =$('<div/>').addClass('screen-item').append($('<div/>').addClass('item-col-1').append($('<span/>').text(value.fsMarketID=='0'?'上海':'深圳')))
            				.append($('<div/>').addClass('item-col-2').html(value.fsCode))
            				.append($('<div/>').addClass('item-col-3').html(value.fsName));
            				
            			$('.screen-main').append(item);
            			
            		}); 
            		start += data.rspnum;
            		if(start < data.totalnum){
            			$('.screen-main').append($('<div/>').addClass('screen-item blkloadmore').text('点击加载更多商品'));
            		}
            	}else{
            		if(data.message != undefined)
            			$('.screen-main').html(data.message);
            		else
            		    $('.screen-main').html('没有对映板块股票信息');
            	}
                $('.qltt-toast').hide();              
            },
            error: function() {
                alert('获取数据失败！');
                $('.qltt-toast').hide();
            }
        });   
    }

    var init = function() {

    	$('.hot-detail').hide();
		
		var url = contextPath+'/webapp/hottopic/topicinfo.do';		
		$.ajax({
			url : url,
			type : 'post',
			dataType: 'json',
			data : {'id' : topicid},
			success : function(data) {
				var topic = data.data;
				
				$('.hd-title').text(topic.title);
				$('.hd-author').text("作者:"+ topic.author);
				$('.hd-date').text("发布时间:"+ topic.publishtimestr);
				$('.hd-click').text("阅读量:"+topic.clicktimes);
				$('.hot-detail-body').html(topic.content);
				
				if(topic.blockItems != null && topic.blockItems.length > 0){
				    topic.blockItems.forEach(function(value,index,array){
					    $('.hd-tags-items').append($('<span/>').text(value.blockname));
				    });
				    $('.hd-tags-items span:first-child').addClass('active');
				}
				loadBlkStock();
				$('.hot-detail').show();
				$('.qltt-toast').hide();
	
			},
			error : function(jqXHR, textStatus, errorThrown) {
				$().alert('获取数据失败！');
			}
		});	
    }
    
    
    $('.screen-main').delegate('.blkloadmore', 'tap', function(event) {
        $('.qltt-toast').css({
            'display' : 'block',
            'opacity' : 1
        });

        loadBlkStock();
    });
    

    //筛选股票
    $('.hd-tags-items').delegate('span', 'tap', function(event) {
        if($(this).hasClass('active')) {
            $(this).removeClass('active');          
        }else {
            $(this).addClass('active');
        }
        
        $('.qltt-toast').css({
            'display' : 'block',
            'opacity' : 1
        });

        loadBlkStock();
    });
    

    //加载更多评论
    var cmtstart = 0;
    var cmtsize = 10;
    
    
    var commentFlag = true;
    $(window).scroll(function(event) {
    	
        if(commentFlag && $(document).height() <= $(window).scrollTop() + $(window).height() + 10) {
            commentFlag = false;
            $('.load-more i').addClass('active');
            $('.load-more span').text('正在加载中...');
            loadCommentMore();        
        }
    })

    //点赞
    $('body').delegate('.hot-cmt-fab', 'tap', function(event) {
    	
    	var that = $(this);
    	
    	var cmtid =  $(this).find('span').data('cmtid');
     
    	var url = contextPath+'/webapp/hottopic/thumbsup.do';		
		$.ajax({
			url : url,
			type : 'post',
			dataType: 'json',
			data : {'cmtid' : cmtid},
			success : function(data) {
				if(data.status == 0){
					 $(that).find('i').addClass('active');
			         $(that).find('span').text(parseInt($(that).find('span').text())+1);
			         $(that).find('i').toggleClass('bounceIn');
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				
			}
		});	
    });
    
    
    var loadCommentMore = function(){
    	var cmturl = contextPath+'/webapp/hottopic/topiccommit.do';	
    	 $.ajax({
             url: cmturl,
             type: 'post',
             data: {'id' : topicid,
     			 'start' : cmtstart,
      			 'size' : cmtsize
      		 },
      		dataType: 'json',
             success : function(data) {                    	
             	if(data.status == 0)
             	{              		
             		if(data.data == null || data.data.length == 0){
                 		$('.load-more i').removeClass('active');
                 		if(cmtstart == 0)
                 			$('.load-more span').text('刚刚发布还没有人评论,不防吐槽抢个沙发');
                 		else
                 			$('.load-more span').text('已加载全部评论');
                 	}else{
                 		cmtstart+=data.data.length;
                 		var html = '';
                 		var comments = data.data;
                 		 for( var i in comments) {
                              html += '<div class="hot-cmt-item">'
                                   +      '<div class="hot-cmt-face"><img src="' + comments[i].portrait + '"  alt="" /></div>'
                                   +      '<div class="hot-cmt-mes">'
                                   +          '<span>' + comments[i].nickname + '</span>'
                                   +          '<strong>' + comments[i].content + '</strong>'
                                   +          '<i>' + comments[i].commenttimeStr + '</i>'
                                   +      '</div>'
                                   +      '<div class="hot-cmt-fab"><i class="animated"></i><span data-cmtid="'+comments[i].id+'">' + comments[i].likenum + '</span></div>'
                                   +  '</div>'
                          }
                 		 $('.hot-cmt-body').append(html);

                          $('.load-more i').removeClass('active');
                          $('.load-more span').text('下拉加载更多');
                          commentFlag = true;
                 	}               		
             	}else{
             		$().alert(data.message);
             	}
             },
             error: function() {
                 $().alert('获取数据失败！');
                 $('.load-more i').removeClass('active');
                 $('.load-more span').text('下拉加载更多');
                 commentFlag = true;
             }
         });
    	
    }

    // 评论框聚焦
    $('#comment-txt').focus(function(event) {
        $('#comment').addClass('active');
        event.stopPropagation();
    });

    $('#comment').tap(function(event) {
        event.stopPropagation();
        event.preventDefault();
    })

    $('#comment-submit').tap(function(event) {
        event.stopPropagation();
    })

    $(document).tap(function(event) {
        $('#comment').removeClass('active');
        $('#comment-txt').blur();
        event.stopPropagation();
        event.preventDefault();
    });
    
    // 写评论
    $('#comment').submit(function() {
        $('#comment').removeClass('active');

        if($('#comment-txt').val().length == 0){
    		$().alert('请填入评论内容');
    		return;
    	}
    	
    	var url = contextPath+'/webapp/hottopic/publishcomment.do';		
		$.ajax({
			url : url,
			type : 'post',
			dataType: 'json',
			data : {'id' : topicid,
					'content': $('#comment-txt').val()
			},
			success : function(data) {
				 $().alert('评论成功');
				 cmtstart = 0;
				 $('#comment-txt').val('');
				 $('.hot-cmt-body').children().remove();
				 loadCommentMore();
			},
			error : function(jqXHR, textStatus, errorThrown) {
				
			}
		});	
		return false;
    });

    init();

});
