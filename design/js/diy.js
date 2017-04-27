/* 
* @Author: lee
* @Date:   2017-04-07 14:10:42
* @Last Modified time: 2017-04-27 15:08:50
*/

$(function(){

    var index = 0;
    var tagNums = 5;    //最多可选中的组合指标个数  
    var $errorEle = $('#error');
    
    var createSearchBox = function($parent){
        var html = '';
        $('#scroller li').each(function(index, val) {
            var searchBoxId = 'diy-main-'+ index;
            html += '<div class="diy-main animated" id="' + searchBoxId + '" ></div>';
        });
        $parent.append(html);
    }

    var createHtml = function(parentId){
        var $parent = $('#'+parentId);
        var wrapperId = parentId +'-wrapper';
        var paginationId = parentId +'-page';
        var html = '<div class="diy-box ani" swiper-animate-effect="fadeIn" swiper-animate-duration="1s" swiper-animate-delay="0s">';
        html +=  '<div class="tag swiper-container swiper-container-horizontal" id="'+ wrapperId +'" >'
             +      '<div class="swiper-wrapper">'
             +          '<div class="swiper-slide" id="tag'+ index +'" >'
             +               '<span>红三兵</span>'
             +               '<span>早晨之星</span>'
             +               '<span>红三兵</span>'
             +               '<span>红三兵</span>'
             +               '<span>红三兵</span>'
             +               '<span>红三兵</span>'
             +               '<span>红三兵</span>'
             +               '<span>红三兵</span>'
             +               '<span>红三兵</span>'
             +               '<span>红三兵</span>'
             +               '<span>红三兵</span>'
             +           '</div>'
        index++;

        html +=      '<div class="swiper-slide" id="tag'+ index +'"  >'
             +               '<span>红三兵</span>'
             +               '<span>早晨之星</span>'
             +               '<span>红三兵</span>'
             +               '<span>红三兵</span>'
             +               '<span>红三兵</span>'
             +               '<span>红三兵</span>'
             +               '<span>红三兵</span>'
             +               '<span>红三兵</span>'
             +               '<span>红三兵</span>'
             +               '<span>红三兵</span>'
             +              '<span>红三兵</span>'
             +           '</div>'
             +      '</div>'

        html +=   '<div class="swiper-button-next"></div>'
             +       '<div class="swiper-button-prev swiper-button-disabled"></div>'
             +       '<div class="swiper-mes" id="' + paginationId + '"></div>'
             +    '</div>'
  

        html += '</div>';

        $parent.append(html);
        $('.diy-main').removeClass('fadeIn').addClass('fadeOut').css('display','none');
        $parent.removeClass('fadeOut').css('display','block').addClass('fadeIn');
        loadingHide($('.qltt-toast'));

        
        new Swiper('#'+wrapperId, {
            pagination: '#'+paginationId,
            paginationClickable: '#'+paginationId,
            nextButton: '.swiper-button-next',
            prevButton: '.swiper-button-prev',
            paginationType : 'fraction',
            paginationFractionRender: function (swiper, currentClassName, totalClassName) {
              return '<i class="' + currentClassName + ' red"></i>' +
                     ' / ' +
                     '<i class="' + totalClassName + '"></i>';
            },
            spaceBetween: 30
        });

    }

    var loadMore = function(url,$parent){
        $.ajax({
            url: url,
            type: 'post',
            success: function(resData){
                    var html = '';
                    
                    for(var i in resData){
                        html += '<div class="screen-item"  >'
                             +      '<div class="item-col-1">' 
                             +          '<span>' + resData[i].name + '</span>'
                             +          '<span class="blue">' + resData[i].nums + '</span>'
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
                    loadFlag = true; 

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
        loadingShow($('.qltt-toast'));
        createSearchBox($('#diy-wrapper'));
        createHtml('diy-main-0');

        if($('.check-tags span').length<=0){
            $('.check-tags').html('<div class="tag-empty">您没有选中任何指标！</div>');
            $('.check-btn a strong').addClass('disabled');
        }

        $errorEle.find('span').html('最多只能选中'+tagNums+'个指标！');
    }

    // var refreshTag = function($cParent,$parent){
    //     var html = '';
    //     $cParent.find('span').each(array/object, function(index, val) {
    //          if($(this).hasClass('active')){
    //             html += '<span><i></i><strong>' + $(this).text() + '</strong></span>';
    //          }
    //     });

    //     $parent.html(html);
    // }


    init();

    $('#header').delegate('li', 'tap', function(event) {

        if($(this).hasClass('active')) return;
        var searchBoxId = 'diy-main-'+$(this).index();
        $('#header li').removeClass('active');
        $(this).addClass('active');

        if(!$('#'+searchBoxId).text()){
            createHtml(searchBoxId);
        }else{
            $('.diy-main').removeClass('fadeIn').addClass('fadeOut').css('display','none');
            $('#'+searchBoxId).removeClass('fadeOut').css('display','block').addClass('fadeIn');
        }
         
    });

    var loadFlag = true;
    //加载更多
    $('body').delegate('.load-more', 'tap', function(event) {
        if(loadFlag){
            loadFlag = false;
            $(this).find('i').addClass('active');
            loadMore('../data/result.json',$(this).prev());
        }
        
    });

    //搜藏指标弹窗
    $('body').delegate('.sc-btn', 'tap', function(){

        showDialog($('#zdyzh'));
    });

    $('#zdyzh .dialog-btn-close,#zdyzh .dialog-mask').tap(function(){
        hideDialog($('#zdyzh'));
    })

    $('#zdyzh .dialog-btn-confirm').tap(function(){
        hideDialog($('#zdyzh'),function(){
            alert('搜藏成功，请到我的关注查看');
        });
    })


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

    //选中与取消组合指标
    $('body').delegate('.swiper-slide span', 'tap', function(event) {
        var id = $(this).parent().attr('id') + '-' + $(this).index();

            if($(this).hasClass('active')){
                
                    $(this).removeClass('active');
                    $('#'+id).remove();
                    if($('.check-tags span').length<=0){
                        $('.check-tags').html('<div class="tag-empty">您没有选中任何指标！</div>');
                        $('.check-btn strong').addClass('disabled');
                    }     
            }else{
                if($('.check-tags span').length<tagNums){
                    $(this).addClass('active');
                    $('.tag-empty').remove();
                    if($('#'+id).length<=0){
                        $('.check-tags').append('<span id="' + id + '"><i></i><strong>' + $(this).text() + '</strong></span>');
                    }
                    $('.check-btn strong').removeClass('disabled');
                }else{
                    $errorEle.removeClass('fadeOut');
                    $errorEle.addClass('fadeIn');
                    $errorEle.css('display','block');
                }

            }
      
    });

    $('#error').tap(function(e){
        e.stopPropagation();
        $errorEle.removeClass('fadeIn');
        $errorEle.addClass('fadeOut');
        $errorEle.css('display','none');
    }) 

    $('body').delegate('.check-tags i', 'tap', function(event) {
        var $ele = $(this).parent();
        var dataArr = $ele.attr('id').split('-');
        $('#'+dataArr[0]).find('span').eq(dataArr[1]).removeClass('active');
        $ele.remove();
        if($('.check-tags span').length<=0){
            $('.check-tags').html('<div class="tag-empty">您没有选中任何指标！</div>');
            $('.check-btn strong').addClass('disabled');
        }  
    });
  
})