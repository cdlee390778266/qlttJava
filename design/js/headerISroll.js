/* 
* @Author: lee
* @Date:   2017-04-07 13:49:55
* @Last Modified time: 2017-04-11 11:00:14
*/

$(function(){
    $('#scroller').css('width',$('#scroller li').length*90+20);
        headIScroll = new IScroll('#header', { 
        eventPassthrough: true, 
        scrollX: true, 
        scrollY: false, 
        preventDefault: false 
    });
})