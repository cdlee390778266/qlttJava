/* 
* @Author: lee
* @Date:   2017-04-07 13:49:55
* @Last Modified time: 2017-05-12 17:44:32
*/

$(function(){

    var myScroll1,myScroll2;
    var itemW = 160;
    
    function updatePosition1 () {
        myScroll2.scrollTo(this.x, 0)
    }

    function updatePosition2 () {
        myScroll1.scrollTo(this.x, 0)
    }

    function loaded () {
        var w = $('#scroller1 span').length*itemW +20;
        var lW = $('.data-body-l').width();
        var wW = $(window).width();
        if(w+lW<wW){
            w = wW;   
        }
        $('.swiper-scroller').width(w);

        myScroll1 = new IScroll('#wrapper1', {
            scrollX: true,
            scrollY: false,
            probeType: 3, 
            mouseWheel: true,
            eventPassthrough: true 
        });

        myScroll2 = new IScroll('#wrapper2', {
            scrollX: true,
            scrollY: false,
            probeType: 3, 
            mouseWheel: true,
            eventPassthrough: true,
        });
        myScroll1.on('scroll', updatePosition1);
        myScroll1.on('scrollEnd', updatePosition1);
        myScroll2.on('scroll', updatePosition2);
        myScroll2.on('scrollEnd', updatePosition2);
    }

    var refresh = function(){
        var w = $('#scroller1 span').length*itemW +20;
        var lW = $('.data-body-l').width();
        var wW = $(window).width();
        if(w+lW<wW){
            w = wW;   
        }
        $('.swiper-scroller').width(w);
        myScroll1.refresh();
        myScroll2.refresh();
    }

    var init = function(){
        loaded();
    }

    $(window).resize(refresh);

    init();    

})
    
    
