/* 
* @Author: lee
* @Date:   2017-04-07 13:49:55
* @Last Modified time: 2017-06-15 11:10:40
*/

$(function(){

    var refreshData = function(key) {
        $.ajax({
            url: '../data/share.json',
            type: 'post',
            data: {
                key: key
            },
            success : function(data) {
                var html = '';
                // 模拟数据
                if(data.length){
                    for( var i in data) {
                        html += '<div class="history-item">'
                             +      '<div class="hisItem-head">'
                             +          '<div class="hisItem-headL"><img src="' + data[i].faceUrl + '" alt=""></div>'
                             +          '<div class="hisItem-headM"><span>' + data[i].name + '</span>' + data[i].time + '</div>'
                             +          '<div class="hisItem-headR"><span>' + data[i].tags + '</span></div>'
                             +      '</div>'
                             +      '<div class="hisItem-body">'
                             +          '<div class="index-rmd-item">'
                             +              '<span>分享股票：</span>' + data[i].share
                             +          '</div>'
                             +          '<div class="index-rmd-item">'
                             +              '<span>预判方向：</span>' + data[i].direction
                             +          '</div>'
                             +          '<div class="index-rmd-item">'
                             +              '<span>有效期：</span>' + data[i].date
                             +          '</div>'
                             +          '<div class="index-rmd-item share-rmd-item">'
                             +              '<span>分享理由：</span>' + data[i].reason
                             +          '</div>'
                             +      '</div>'
                             +      '<div class="hisItem-foot">'
                             +          '<div class="hisItem-footL"><i class="animated"></i><span>' + data[i].zNums + '</span></div>'
                             +          '<div class="hisItem-footR"><i class="animated"></i><span>' + data[i].cNums + '</span></div>'
                             +      '</div>'
                             +  '</div>'
                        $('#empty').hide();
                        $('#share').html(html).show();
                    }
                }else {
                    $('#share').html('').hide();
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
                alert('查询失败');
            }
        })
    }

    //头部下拉
    $('.share-header-title').tap(function(e) {
        if($('.share-header-tip').css('display') == 'block') {
            $('.share-header-tip').css('display', 'none');
        }else {
            $('.share-header-tip').css('display','block');
        }
        e.stopPropagation();
        e.preventDefault();
    });


    $('.share-header-tip li').tap(function(e) {
        $self = $(this);
        $('.share-header-tip li').removeClass('active');
        $(this).addClass('active');
        $('.share-header-title span').text($(this).text());
        $('.share-header-tip').css('display', 'none');
        $('.qltt-toast').css({
            'display' : 'block',
            'opacity' : 1
        });
        refreshData($self.attr('sort'));
        e.stopPropagation();
        e.preventDefault();
    })

    $(document).tap(function(e){
        $('.share-header-tip').css('display', 'none');
        e.stopPropagation();
        e.preventDefault();
    })

    //搜索
    $('#share-search').submit(function(e){
        $('.qltt-toast').css({
            'display' : 'block',
            'opacity' : 1
        });
        refreshData($('#share-search').val());
        $('#share-text').get(0).blur();
        return false;
    })

    //赞
    $('body').delegate('.hisItem-foot>div', 'tap', function(event) {
        $(this).parent().find('div').each(function(index, val) {
            if($(this).find('i').hasClass('active')){
                $(this).find('span').text(parseInt($(this).find('span').text())-1).removeClass('active');
                $(this).find('i').removeClass('active');
            }
        });
        if($(this).find('i').hasClass('active')){
            $(this).find('i').removeClass('active');
            $(this).find('span').text(parseInt($(this).find('span').text())-1).removeClass('active');
        }else{
            $(this).find('i').addClass('active');
            $(this).find('span').text(parseInt($(this).find('span').text())+1).addClass('active');
        }
        $(this).find('i').toggleClass('bounceIn');
        
    });

    //展开
    $('body').delegate('.share-rmd-item', 'tap', function(){
        $(this).css({
            'overflow': 'visible',
            'display': 'block'
        });
    })

    var init = function() {
        $('.qltt-toast').css({
            'display' : 'block',
            'opacity' : 1
        });
        refreshData($('.share-header-tip li.active').attr('sort'));
    }

    init();
})
    
    
