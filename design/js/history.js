/* 
* @Author: lee
* @Date:   2017-04-07 14:31:52
* @Last Modified time: 2017-04-20 17:51:52
*/

$(document).ready(function(){
    
    var pollSwiper ;
    var scrollTopArr = {};
    var editFlag = true;
    var menuNums = 0;

    var refreshScrollTop = function(prevKey,prevKeyType,prevActive,currentKey,currentKeyType,currentActive){
        scrollTopArr[prevKey][prevKeyType] = getScrollTop();
        scrollTopArr[prevKey]['active'] = prevActive; 
        scrollTop(scrollTopArr[currentKey][currentKeyType]);
    }

    var createSearchBox = function($parent){
        var html = '';
        $('#scroller li').each(function(index, val) {
            var searchBoxId = 'history-main-'+index;
            html += '<div class="history-main animated" id="' + searchBoxId + '" ><div class="animated" ></div><div class="animated"></div></div>';
            scrollTopArr[searchBoxId] = {};
            scrollTopArr[searchBoxId][0] = 0;
            scrollTopArr[searchBoxId][1] = 0;
            scrollTopArr[searchBoxId]['active'] = 0;
        });
        $parent.append(html);
        menuNums = $('#scroller li').length ;
    }

    var createHtml = function(parentId,type,tplType){
        var html = '';
        var $parent = $('#'+parentId+'>div').eq(type);
        if(!tplType){
            $.ajax({
                url: '../data/history.json',
                type: 'post',
                success: function(resData){
                       for(var i in resData){
                        html += '<div class="history-item">'
                             +      '<div class="hisItem-head">'
                             +          '<div class="hisItem-headL"><img src="' + resData[i].faceUrl + '"  alt="" /></div>'
                             +          '<div class="hisItem-headM"><span>' + resData[i].name + '</span>' + resData[i].time + '</div>'
                             +          '<div class="hisItem-headR">成功率：' + resData[i].probability + '</div>'
                             +      '</div>'
                             +      '<div class="hisItem-body">'
                             +          '<div class="hisItem-bodyT"><span>' + resData[i].type + '</span>' + resData[i].typeD +'</div>'
                             +          '<div class="hisItem-bodyD">' + resData[i].detail +'</div>'
                             +      '</div>'
                             +      '<div class="hisItem-foot">'
                             +          '<div class="hisItem-footL"><i class="animated"></i><span>' + resData[i].zNums + '</span></div>'
                             +          '<div class="hisItem-footR"><i class="animated"></i><span>' + resData[i].cNums + '</span></div>'
                             +      '</div>'
                             +  '</div>'        
                        }
                        // html += '<div class="loadMore">下拉加载更多</div>'; 

                    $('.history-type>div').removeClass('active');
                    $('.history-type>div').eq(type).addClass('active');
                    $('.history-main>div').removeClass('fadeIn').addClass('fadeOut').css('display','none');
                    $('#'+parentId+'>div').eq($('.history-type div.active').index()).css('display','block').removeClass('fadeOut').addClass('fadeIn');
                    $parent.append(html);
                    loadingHide($('.qltt-toast'));

                },
                error: function(xhr, type){
                    alert('获取数据失败!');
                }
            })
        }else{
            $.ajax({
                url: '../data/history1.json',
                type: 'post',
                success: function(resData){
                   html += '<div class="history-head"><div>用户名</div><div>荐股总数</div><div>成功率</div></div>'
                   html += '<div class="history-body">'
                       for(var i in resData){
                        html += '<div class="history-tr">'
                             +      '<div class="red">' + resData[i].name + '</div>'
                             +      '<div>' + resData[i].nums + '</div>'
                             +      '<div>' + resData[i].probability + '</div>'
                             +  '</div>'        
                        }
                        // html += '<div class="loadMore">下拉加载更多</div>'; 
                    html += '</div>';

                    $('.history-type>div').removeClass('active');
                    $('.history-type>div').eq(type).addClass('active');
                    $('.history-main>div').removeClass('fadeIn').addClass('fadeOut').css('display','none');
                    $('#'+parentId+'>div').eq($('.history-type div.active').index()).css('display','block').removeClass('fadeOut').addClass('fadeIn');
                    $parent.append(html);
                    loadingHide($('.qltt-toast'));

                },
                error: function(xhr, type){
                    alert('获取数据失败!');
                }
            })
        }
        
           
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

    var showMessage = function(text,time){
        $('#error span').text(text);
        $('#error').removeClass('fadeOut').css('display','block').addClass('fadeIn');
        setTimeout(function(){
            $('#error').removeClass('fadeIn').addClass('fadeOut').css('display','none');
        }, time)
    }


    var init = function(){
        $('#scroller').css('width',$('#scroller li').length*90+50);
        headIScroll = new IScroll('#header', { 
            eventPassthrough: true, 
            scrollX: true, 
            scrollY: false, 
            preventDefault: false
        });

        loadingShow($('.qltt-toast'));
        createSearchBox($('#history-wrapper'));
        createHtml('history-main-0',0);

    }


    init();

    $('#header').delegate('li', 'tap', function(event) {

        if($(this).hasClass('active')) return;
        var prevKey = 'history-main-' + $('#header li.active').index();
        var prevKeyType =  $('.history-type div.active').index();
        var currentKey = 'history-main-'+$(this).index();
        var currentKeyType =  scrollTopArr[currentKey]['active'];

        $('#header li').removeClass('active');
        $(this).addClass('active');
        $('.history-type div').removeClass('active');
        $('.history-type div').eq(currentKeyType).addClass('active');
        
        $('.history-main').removeClass('fadeIn').addClass('fadeOut').css('display','none');
        $('.history-main').eq($(this).index()).css('display','block').removeClass('fadeOut').addClass('fadeIn');

        if(!$('#'+currentKey).find('div').eq(0).text()){
            loadingShow($('.qltt-toast'));
            if($(this).attr('tplType')!='history'){
                $('.history-type div').eq(0).text('今日荐股');
                $('.history-type div').eq(1).text('历史荐股');
                createHtml(currentKey,0);
            }else{
                $('.history-type div').eq(0).text('个人成功率');
                $('.history-type div').eq(1).text('股票成功率');
                createHtml(currentKey,0,1);
            }
            
        }else{
            if($(this).attr('tplType')!='history'){
                $('.history-type div').eq(0).text('今日荐股');
                $('.history-type div').eq(1).text('历史荐股');
            }else{
                $('.history-type div').eq(0).text('个人成功率');
                $('.history-type div').eq(1).text('股票成功率');
            }
            $('.history-main>div').removeClass('fadeIn').addClass('fadeOut').css('display','none');
            $('#'+ currentKey +'>div').eq(currentKeyType).css('display','block').removeClass('fadeOut').addClass('fadeIn');
        }


        refreshScrollTop(prevKey,prevKeyType,prevKeyType,currentKey,currentKeyType,currentKeyType);

        
    });

    $('body').delegate('.history-type>div', 'tap', function(event) {

        if($(this).hasClass('active')) return;
        var prevKey = 'history-main-' + $('#header li.active').index();
        var prevKeyType =  $('.history-type div.active').index();
        var currentKey = prevKey;
        var currentKeyType =  $(this).index();

        $('.history-type>div').removeClass('active');
        $(this).addClass('active');
        
        if(!$('#'+prevKey+'>div').eq(currentKeyType).text()){
            loadingShow($('.qltt-toast'));
            if($('#header li.active').attr('tplType')!='history'){
                createHtml(prevKey,currentKeyType);
            }else{
                createHtml(prevKey,currentKeyType,1);
            }
            
        }else{
            $('.history-main>div').removeClass('fadeIn').addClass('fadeOut').css('display','none');
            $('#'+prevKey+'>div').eq(currentKeyType).css('display','block').removeClass('fadeOut').addClass('fadeIn');
        }

        refreshScrollTop(prevKey,prevKeyType,prevKeyType,prevKey,currentKeyType,currentKeyType);

        


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

   
    //赞
    $('body').delegate('.hisItem-foot>div', 'tap', function(event) {
        
        if($(this).find('i').hasClass('active')){
            $(this).find('i').removeClass('active');
            $(this).find('span').text(parseInt($(this).find('span').text())-1);
        }else{
            $(this).find('i').addClass('active');
            $(this).find('span').text(parseInt($(this).find('span').text())+1);
        }
        $(this).find('i').toggleClass('bounceIn');
        
    });
   


    
});