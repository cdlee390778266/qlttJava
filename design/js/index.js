/* 
* @Author: lee
* @Date:   2017-04-07 14:10:42
* @Last Modified time: 2017-06-12 10:00:36
*/

$(function(){

    //轮播
    var mySwiper = new Swiper('#swiper-banner', {
        autoplay: 5000,//可选选项，自动滑动
        pagination: '.swiper-pagination',
        paginationClickable: true,
        loop: true,
        autoplayDisableOnInteraction : false
    })

    //最新信息
    var mySwiper = new Swiper('#swiper-bar', {
        direction: 'vertical',
        autoplay: 3000,//可选选项，自动滑动
        paginationClickable: true,
        loop: true,
        autoplayDisableOnInteraction : false,
        height: $('#swiper-bar').height()
    })

    //赞
    $('body').delegate('.hisItem-foot>div', 'tap', function(event) {
        $(this).parent().find('div').each(function(index, val) {
            if($(this).find('i').hasClass('active')){
                $(this).find('span').text(parseInt($(this).find('span').text())-1);
                $(this).find('i').removeClass('active');
            }
        });
        if($(this).find('i').hasClass('active')){
            $(this).find('i').removeClass('active');
            $(this).find('span').text(parseInt($(this).find('span').text())-1);
        }else{
            $(this).find('i').addClass('active');
            $(this).find('span').text(parseInt($(this).find('span').text())+1);
        }
        $(this).find('i').toggleClass('bounceIn');
        
    });
    
})