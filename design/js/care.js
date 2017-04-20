/* 
* @Author: lee
* @Date:   2017-04-07 13:49:55
* @Last Modified time: 2017-04-19 10:50:26
*/

$(function(){

    $('body').delegate('.search-foot>span', 'tap', function(event) {
        $(this).toggleClass('active');
    });

    $('body').delegate('.search-head>i', 'tap', function(event) {
        if(window.confirm('你确定删除此关注吗？')){
            $(this).parent().parent().remove();
        }
    });

})
    
    
