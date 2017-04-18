/* 
* @Author: lee
* @Date:   2017-04-07 14:31:52
* @Last Modified time: 2017-04-17 18:26:16
*/

$(document).ready(function(){
    
    var pollSwiper ;
    var scrollTopArr = {};
    var editFlag = true;

    var refreshScrollTop = function(prevKey,currentKey){
        scrollTopArr[prevKey] = getScrollTop(); 
        scrollTop(scrollTopArr[currentKey]);
    }

    var createSearchBox = function($parent){
        var html = '';
        $('#scrollerPool li').each(function(index, val) {
            var searchBoxId = 'pool-main-'+ index;
            html += '<div class="pool-main animated" id="' + searchBoxId + '" ></div>';
            scrollTopArr[searchBoxId] = 0;
        });
        $parent.append(html);
    }

    var createHtml = function(parentId){
        var html = '';
        var $parent = $('#'+parentId);
        var wrapperId = parentId +'-wrapper';
        var paginationId = parentId +'-page';
        
        $.ajax({
            url: '../data/pool.json',
            type: 'post',
            success: function(resData){
                    if(parentId =='pool-main-1'){
                        html += '<div class="pool-empty">'
                             +      '<img src="../images/pool.png" /><br />该选股池还没有数据'
                             +  '</div>'
                    }else{
                       for(var i in resData){
                        html += '<div class="pool-item">'
                             +      '<div class="poolItem-td">' + resData[i].name + '</div>'
                             +      '<div class="poolItem-td">' + resData[i].nums + '</div>'
                             +      '<div class="poolItem-td">'
                             +          '<span class="poolItem-icon-cmd"></span>'
                             +          '<span class="poolItem-icon-cancle"></span>'
                             +      '</div>'
                             +   '</div>'         
                        }
                        // html += '<div class="loadMore">下拉加载更多</div>'; 
                    }
                    
                    
                    $parent.append(html);
                    loadingHide($('.qltt-toast'));

            },
            error: function(xhr, type){
                alert('获取数据失败!');
            }
        })
       
        
        $parent.append(html);
        loadingHide($('.qltt-toast'));
        
    }

    var loadMore = function(url,$parent){
        $.ajax({
            url: url,
            type: 'post',
            success: function(resData){
                    var html = '';
                    
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
        $('#scrollerPool').css('width',$('#scrollerPool li').length*90+20);
        headIScroll = new IScroll('#headerPool', { 
            eventPassthrough: true, 
            scrollX: true, 
            scrollY: false, 
            preventDefault: false
        });

        loadingShow($('.qltt-toast'));
        createSearchBox($('#pool-wrapper'));
        createHtml('pool-main-0');
        
    }


    init();

    $('#headerPool').delegate('li', 'tap', function(event) {

        if($(this).hasClass('active')) return;
        var searchBoxId = 'pool-main-'+$(this).index();
        var prevKey = $('#headerPool li.active').index();
        $('#headerPool li').removeClass('active');
        $(this).addClass('active');
        
        if($('#pool-main-'+ $(this).index()).find('.pool-item').length<=0){
            loadingShow($('.qltt-toast'));
            createHtml('pool-main-'+ $(this).index());
        }

        refreshScrollTop('pool-main-'+prevKey,'pool-main-'+$(this).index());

        $('.pool-main').removeClass('fadeIn').addClass('fadeOut').css('display','none');
        $('.pool-main').eq($(this).index()).css('display','block').removeClass('fadeOut').addClass('fadeIn');
        
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
    $('body').delegate('.poolItem-icon-cmd', 'tap', function(){
       
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
    $('body').delegate('.poolItem-icon-cancle', 'tap', function(){
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


    //增删菜单
    $('body').delegate('.pam-icon', 'tap', function(event) {
        if($(this).parent().hasClass('active')){
            $(this).parent().removeClass('active');
            $('.pool-add-menu').removeClass('fadeIn').addClass('fadeOut').removeClass('fadeOut');
            $('.pool').removeClass('fadeOut').css('display','block').addClass('fadeIn');
        }else{
            $(this).parent().addClass('active');
            $('.pool-add-menu').removeClass('fadeOut').addClass('fadeIn');
            $('.pool').removeClass('fadeIn').addClass('fadeOut').css('display','none');
        }
    });

    $('body').delegate('.menu-head-right', 'tap', function(event) {
        if(editFlag){
            editFlag = false;
            $('.menu-body  i').show();
        }else{
            editFlag = true;
            $('.menu-body  i').hide();
        }
        
    });

    $('.menu-body').delegate('i', 'tap', function(event) {
        $(this).parent().remove();
    });

    $('.menu-body').delegate('span', 'tap', function(event) {
        if($(this).hasClass('active')){
            $(this).removeClass('active');
        }else{
            $(this).addClass('active');
        }
    });

});