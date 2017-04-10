/* 
* @Author: lee
* @Date:   2017-04-07 13:49:55
* @Last Modified time: 2017-04-07 13:52:03
*/

window.onload = function(){
    $('#scroller').css('width',$('#scroller li').length*90+20);
    var headIScroll = new IScroll('#header', { 
        eventPassthrough: true, 
        scrollX: true, 
        scrollY: false, 
        preventDefault: false 
    });
}