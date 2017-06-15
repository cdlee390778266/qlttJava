/* 
* @Author: lee
* @Date:   2017-04-07 15:15:54
* @Last Modified time: 2017-06-14 15:34:17
*/

$(document).ready(function(){

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
    })

    var getResult = function(key) {
        if(!key) return;

        $.ajax({
            url: '../data/hotDetail.json',
            type: 'post',
            data: {
                key: key
            },
            success : function(data) {
                var html = '';
                // 模拟数据
                for( var i in data) {
                    if(data){
                        html += '<div class="screen-item">'
                             +      '<div class="item-col-1"><span>'+ data[i].name + '</span><span class="blue">' + data[i].nums + '</span></div>'
                             +      '<div class="item-col-2">' + data[i].date + '</div>'
                             +      '<div class="item-col-3">' + data[i].type + '</div>'
                             +      '<div class="item-col-4"><a href="javascript:void(0);" class="choose"></a></div>'
                             +  '</div>'

                        $('.screen-main').html(html);
                    }else {
                        $('.screen-main').html('没有相关股票信息！');
                    } 

                    $('.qltt-toast').css({
                        'display' : 'none',
                        'opacity' : 0
                    });
                }
            },
            error: function() {
                alert('获取数据失败！');
                $('.qltt-toast').css({
                    'display' : 'none',
                    'opacity' : 0
                });
            }
        })   
    }

    var init = function() {
        
        //根据内容多少决定是否显示下拉按钮
        if($('.hd-tags-items').height() > height){
            $('.hd-tags-items i').css('display','block');
            $('.hd-tags-items').height(height); 
        }else {
            $('.hd-tags-items i').hide();
        }

        $('.hd-result-bar span').text($('.hd-tags-items span.active').text());
        $('.qltt-toast').css({
            'display' : 'block',
            'opacity' : 1
        });
        getResult($('.hd-tags-items span.active').text());
    }

    //筛选股票
    $('.hd-tags-items').delegate('span', 'tap', function(event) {
        $('.hd-tags-items span').removeClass('active');
        $(this).addClass('active');
        $('.hd-result-bar span').text($(this).text());
        $('.qltt-toast').css({
            'display' : 'block',
            'opacity' : 1
        });
        getResult($(this).text());
    });

    //加载更多评论
    var commentFlag = true;
    $(window).scroll(function(event) {
        if(commentFlag && $(document).height() <= $(window).scrollTop() + $(window).height() + 10) {
            commentFlag = false;
            $('.load-more i').addClass('active');
            $('.load-more span').text('加载中...');

            $.ajax({
                url: '../data/comment.json',
                type: 'post',
                data: {
                   
                },
                success : function(data) {
                    var html = '';
                    // 模拟数据
                    if(data){
                        for( var i in data) {
                            html += '<div class="hot-cmt-item">'
                                 +      '<div class="hot-cmt-face"><img src="' + data[i].face + '"  alt="" /></div>'
                                 +      '<div class="hot-cmt-mes">'
                                 +          '<span>' + data[i].name + '</span>'
                                 +          '<strong>' + data[i].content + '</strong>'
                                 +          '<i>' + data[i].time + '</i>'
                                 +      '</div>'
                                 +      '<div class="hot-cmt-fab"><i class="animated"></i><span>' + data[i].fabulous + '</span></div>'
                                 +  '</div>'
                        }

                        $('.hot-cmt-body').append(html);

                        $('.load-more i').removeClass('active');
                        $('.load-more span').text('点击加载更多');

                        commentFlag = true;

                    }else {
                        $('.load-more i').removeClass('active');
                        $('.load-more span').text('已加载全部评论');
                    }
                },
                error: function() {
                    $().alert('获取数据失败！');
                    $('.load-more i').removeClass('active');
                    $('.load-more span').text('点击加载更多');

                    commentFlag = true;
                }
            })
        }
    })

    //点赞
    $('body').delegate('.hot-cmt-fab', 'tap', function(event) {
        
        if($(this).find('i').hasClass('active')){
            $(this).find('i').removeClass('active');
            $(this).find('span').text(parseInt($(this).find('span').text())-1);
        }else{
            $(this).find('i').addClass('active');
            $(this).find('span').text(parseInt($(this).find('span').text())+1);
        }
        $(this).find('i').toggleClass('bounceIn');
        
    });

    init();

});
