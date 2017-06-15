/* 
* @Author: lee
* @Date:   2017-04-07 13:49:55
* @Last Modified time: 2017-06-13 11:06:09
*/

$(function(){

    //头部下拉
    $('.share-header-title').tap(function(e) {
        if($('.share-header-tip').css('display') == 'block') {
            $('.share-header-tip').css('display', 'none');
        }else {
            $('.share-header-tip').css('display','block');
        }
        e.stopPropagation();
        e.preventDefault();
    });

    $('.share-header-tip li').tap(function(e) {
        $('.share-header-tip li').removeClass('active');
        $(this).addClass('active');
        //$('.share-header-tip').css('display', 'none');
        e.stopPropagation();
        e.preventDefault();
    })

    $(document).tap(function(e){
        $('.share-header-tip').css('display', 'none');
        e.stopPropagation();
        e.preventDefault();
    })

    //左右滑动显示删除按钮
    $('.share-item').swipeLeft(function() {
        $(this).find('.share-item-wrapper').addClass('active');
    })
    $('.share-item').swipeRight(function() {
        $(this).find('.share-item-wrapper').removeClass('active');
    })

    //删除
    $('.share-del').tap(function(){
        $self = $(this);
        $().confirm('您确定要删除此条收藏？', function(){
            $self.parents('.share-item').remove();
        });
    })

})
    
    
