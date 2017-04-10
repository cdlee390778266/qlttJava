/* 
* @Author: lee
* @Date:   2017-04-07 14:31:52
* @Last Modified time: 2017-04-07 14:33:17
*/

$(document).ready(function(){
    $('#scrollerPool').css('width',$('#scrollerPool li').length*90+20);
    var headIscroll = new IScroll('#headerPool', { 
        eventPassthrough: true, 
        scrollX: true, 
        scrollY: false, 
        preventDefault: false 
    });
});