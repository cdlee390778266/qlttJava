/* 
* @Author: lee
* @Date:   2017-04-11 16:23:54
* @Last Modified time: 2017-04-12 18:19:49
*/

$(document).ready(function(){

    var url = '../data/result.json';
    var refreshFlag = true;

    var refreshHeight = function (){
        $('.result-main').height($('.img-responsive').height());
        $('.result-tags-box').height($('.result-tags').height());
        $('.result-tags-box').width($('.result-tags').width());
    }

    var refreshData = function(url,$parent){
        $.ajax({
            url: url,
            type: 'post',
            success: function(resData){
                    var html = '';
                    for(var i in resData){
                        html += '<div class="screen-item"  >'
                             +      '<div class="item-col-1">' 
                             +          '<span>' + resData[i].name + '</span>'
                             +          '<span class="red">' + resData[i].nums + '</span>'
                             +      '</div>'
                             +      '<div class="item-col-2">' + resData[i].date + '</div>'
                             +      '<div class="item-col-3">' + resData[i].type + '</div>'
                             +      '<div class="item-col-4">' 
                             +          '<a href="javascript:void(0);" class="recommend" ></a>'
                             +          '<a href="javascript:void(0);" class="choose"></a>'
                             +      '</div>'
                             +   '</div>'         
                    }
                   
                    $parent.append(html);
                    $('.load-more i').removeClass('active');
                    refreshFlag = true;

            },
            error: function(xhr, type){
                alert('获取数据失败!');
            }
        })
        
    }

    var showDialog = function($dialogEle,callBack){
        $dialogEle.removeClass('fadeOut').css('display','block').addClass('fadeIn');
        if(typeof callBack == 'function')
            callBack();
    }

    var hideDialog = function($dialogEle,callBack){
        $dialogEle.removeClass('fadeIn').addClass('fadeOut').css('display','none');
        if(typeof callBack == 'function')
            callBack();
    }

    var init = function(){
        refreshHeight();
        refreshData(url,$('.screen-main'));

    }


    $('.result-btns span').tap(function(){
        if($(this).hasClass('active')){
            $(this).removeClass('active');
        }else{
            $(this).addClass('active');
        }
    })
    // $(window).resize(refreshHeight);

    //加载更多
    $('body').delegate('.load-more', 'tap', function(){
        if(refreshFlag){
            refreshFlag = false;
            $(this).find('i').addClass('active');
            refreshData(url,$('.screen-main'));
        }
    });

    //荐股池弹窗
    $('body').delegate('.recommend', 'tap', function(){
       
        showDialog($('#recommend'));
    });
         
    $('#recommend .dialog-btn-close,#recommend .dialog-mask').tap(function(){
        hideDialog($('#recommend'));
    })

    $('#recommend .dialog-btn-confirm').tap(function(){
        hideDialog($('#recommend'),function(){
            console.log('提交');
        });
    })

    //选股池弹窗
    $('body').delegate('.choose', 'tap', function(){
        showDialog($('#choose'));
    });
         
    $('#choose .dialog-btn-close,#choose .dialog-mask').tap(function(){
        hideDialog($('#choose'));
    })

    $('#choose .dialog-btn-confirm').tap(function(){
        hideDialog($('#choose'),function(){
            console.log('提交');
        });
    })

    $('body').delegate('#choosePool', 'change', function(event) {
        $('.select-mask').text($(this).val());
    });

    init();
});