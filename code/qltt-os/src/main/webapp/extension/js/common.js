/* 
* @Author: lee
* @Date:   2017-04-07 17:02:56
* @Last Modified time: 2017-04-13 11:00:24
*/

var loadingShow = function($showEle){
    $showEle.css({
        'opacity' : 1,
        'display' : 'block'
    });
}

var scrollTop = function(pos){
    window.pageYOffset = pos;
    document.documentElement.scrollTop = pos;
    document.body.scrollTop = pos;
}

var scrollTop = function(pos){
    window.pageYOffset = pos;
    document.documentElement.scrollTop = pos;
    document.body.scrollTop = pos;
}

var getScrollTop = function(){
    var pos = document.documentElement.scrollTop ? document.documentElement.scrollTop : document.body.scrollTop;
    return pos;
}

var loadingHide = function($showEle){
    $showEle.css({
        'opacity' : 0,
        'display' : 'none'
    });
}
