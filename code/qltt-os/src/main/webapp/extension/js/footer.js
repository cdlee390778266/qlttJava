/* 
* @Author: lee
* @Date:   2017-04-07 13:49:55
* @Last Modified time: 2017-04-10 10:43:53
*/

$(function(){

    $('.footer').delegate('a', 'click', function(event) {
        if(!$(this).attr('href')) 
            !$(this).attr('href','javascript:void(0);');
    });

})
    
    
