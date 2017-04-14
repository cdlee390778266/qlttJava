/* 
* @Author: lee
* @Date:   2017-04-07 14:10:42
* @Last Modified time: 2017-04-13 16:26:45
*/

$(function(){

    var diySwiper ;
    var scrollTopArr = {};

    var refreshScrollTop = function(prevKey,currentKey){
        scrollTopArr[prevKey] = getScrollTop(); 
        scrollTop(scrollTopArr[currentKey]);
    }
    var createSearchBox = function($parent){
        var html = '';
        $('#scroller li').each(function(index, val) {
            var searchBoxId = 'diy-main-'+ index;
            html += '<div class="diy-main swiper-slide" id="' + searchBoxId + '" ></div>';
            scrollTopArr[searchBoxId] = 0;
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
             +          '<div class="swiper-slide" >'
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
             +          '<div class="swiper-slide" >'
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
  
        html += '<div class="tag-check tag ">'
             +     '<div class="check-txt ">我的组合</div>'
             +     '<div class="check-tags">'
             +         '<span><i></i><strong>红三兵</strong></span>'
             +         '<span><i></i><strong>红三兵</strong></span>'
             +         '<span><i></i><strong>红三兵</strong></span>'
             +         '<span><i></i><strong>红三兵</strong></span>'
             +         '<span><i></i><strong>红三兵</strong></span>'
             +     '</div>'
             +     '<div class="check-btn">'
             +         '<a href="javascript:void(0);"><strong ng-bind="btnCare" class="">关注组合指标</strong></a>'
             +         '<a href="javascript:void(0);"><strong ng-bind="btnClc" class="">收藏组合指标</strong></a>'
             +     '</div>'
             + '</div>'

        html += '<div class="srceen ">'
             +     '<div class="srceen-txt">'
             +        ' <span>根据您的指标组合共计筛选出</span><span class="red ">120</span><span >支股票</span>'
             +     '</div>'
             +     '<div class="screen-main">'
             +         '<div class="screen-item ">'
             +             '<div class="item-col-1">'
             +                 '<span  class="">上海电影</span>'
             +                 '<span class="blue">328370</span>'
             +             '</div>'
             +             '<div class="item-col-2 ">03-01 12:35</div>'
             +             '<div class="item-col-3 ">潜力股</div>'
             +             '<div class="item-col-4">'
             +                 '<a href="javascript:void(0);" class="recommend"></a>'
             +                 '<a href="javascript:void(0);" class="choose"></a>'
             +             '</div>'
             +         '</div>'
             +     '</div>'
             +    '<div class="screen-main">'
            +         '<div class="screen-item ">'
            +             '<div class="item-col-1">'
            +                 '<span  class="">上海电影</span>'
            +                 '<span class="blue">328370</span>'
            +             '</div>'
            +             '<div class="item-col-2 ">03-01 12:35</div>'
            +             '<div class="item-col-3 ">潜力股</div>'
            +             '<div class="item-col-4">'
            +                 '<a href="javascript:void(0);" class="recommend"></a>'
            +                 '<a href="javascript:void(0);" class="choose"></a>'
            +             '</div>'
            +         '</div>'
            +     '</div>'
            +      '<div class="screen-main">'
            +         '<div class="screen-item ">'
            +             '<div class="item-col-1">'
            +                 '<span  class="">上海电影</span>'
            +                 '<span class="blue">328370</span>'
            +             '</div>'
            +             '<div class="item-col-2 ">03-01 12:35</div>'
            +             '<div class="item-col-3 ">潜力股</div>'
            +             '<div class="item-col-4">'
            +                 '<a href="javascript:void(0);" class="recommend"></a>'
            +                 '<a href="javascript:void(0);" class="choose"></a>'
            +             '</div>'
            +         '</div>'
            +     '</div>'
            +      '<div class="screen-main">'
            +         '<div class="screen-item ">'
            +             '<div class="item-col-1">'
            +                 '<span  class="">上海电影</span>'
            +                 '<span class="blue">328370</span>'
            +             '</div>'
            +             '<div class="item-col-2 ">03-01 12:35</div>'
            +             '<div class="item-col-3 ">潜力股</div>'
            +             '<div class="item-col-4">'
            +                 '<a href="javascript:void(0);" class="recommend"></a>'
            +                 '<a href="javascript:void(0);" class="choose"></a>'
            +             '</div>'
            +         '</div>'
            +     '</div>'
            +     '<div class="load-more">'
             +         '<i></i><span>加载更多</span>'
             +     '</div>'
             +   '</div> '

        html += '</div>';

        $parent.append(html);
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
                    diySwiper.update();

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
        diySwiper = new Swiper('#diy-container',{
            spaceBetween: 30,
            observer:true,
            autoHeight: true, //高度随内容变化
            onInit: function(swiper){ 
                swiperAnimateCache(swiper); //隐藏动画元素 
                swiperAnimate(swiper); //初始化完成开始动画
            }, 
            onSlideChangeStart: function(){
                
                refreshScrollTop('diy-main-'+diySwiper.previousIndex,'diy-main-'+diySwiper.activeIndex);
                if($('#diy-main-'+ diySwiper.activeIndex).find('.diy-box').length<=0){
                    loadingShow($('.qltt-toast'));
                    createHtml('diy-main-'+ diySwiper.activeIndex);
                }

                headIScroll.scrollToElement($('#header li').get(diySwiper.activeIndex),30);

            },
            onSlideChangeEnd: function(swiper){
                $('#header li').removeClass('active');
                $('#header li').eq(diySwiper.activeIndex).addClass('active');
                swiperAnimate(swiper); //每个slide切换结束时也运行当前slide动画
                diySwiper.update();
            }
        });

    }


    init();

    $('#header').delegate('li', 'tap', function(event) {

        var searchBoxId = 'diy-main-'+$(this).index();

        $('#header li').removeClass('active');
        $(this).addClass('active');
        
        diySwiper.slideTo($(this).index());
        
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
  
})