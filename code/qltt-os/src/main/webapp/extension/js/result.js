/* 
* @Author: lee
* @Date:   2017-04-11 16:23:54
* @Last Modified time: 2017-04-11 18:02:12
*/

$(document).ready(function(){

    var refreshHeight = function (){
        $('.result-main').height($('.img-responsive').height());
        $('.result-tags-box').height($('.result-tags').height());
        $('.result-tags-box').width($('.result-tags').width());
    }

    var init = function(){
        refreshHeight();
    }

    $('.result-btns span').tap(function(){
        if($(this).hasClass('active')){
            $(this).removeClass('active');
        }else{
            $(this).addClass('active');
        }
    })
    // $(window).resize(refreshHeight);

    init();
});