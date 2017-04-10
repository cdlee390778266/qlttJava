/* 
* @Author: lee
* @Date:   2017-04-07 17:02:56
* @Last Modified time: 2017-04-10 14:18:07
*/


$(document).ready(function(){

    var Searchswiper ;

    var loadingShow = function($showEle){
        $showEle.css({
            'opacity' : 1,
            'display' : 'block'
        });
    }

    var loadingHide = function($showEle){
        $showEle.css({
            'opacity' : 0,
            'display' : 'none'
        });
    }

    var createHtml = function($parent,childId){
        var html = '';

        $.ajax({
            url: '../data/search.json',
            type: 'post',
            success: function(resData){
                    html += '<div class="search-box swiper-slide animated fadeIn" id="' + childId + '" >';
                    for(var i in resData){
                        html += '<div class="search-item " >'
                             +      '<div class="search-head">' + resData[i].searchHead + '</div>'
                             +      '<div class="search-body">' + resData[i].searchBody + '</div>'
                             +      '<div class="search-foot">'
                             +          '<span >关注</span>'
                             +      '</div>'         
                    }
                    html += '<div class="loadMore">下拉加载更多</div>';
                    html += '</div>';

                    $parent.append(html);
                    loadingHide($('.qltt-toast'));

            },
            error: function(xhr, type){
                alert('Ajax error!')
            }
        })
        
        html += '</div>';
    }

    var init = function(){
        loadingShow($('.qltt-toast'));
        createHtml($('.swiper-wrapper'),'search-bod-0');
        Searchswiper = new Swiper('.swiper-container',{
            spaceBetween: 30,
            observer:true
        });
    }


    $('#header').delegate('li', 'tap', function(event) {

        var searchBoxId = 'search-bod-'+$(this).index();

        $('#header li').removeClass('active');
        $(this).addClass('active');
        if($('#'+searchBoxId).length>0){
            // $('.search-box').hide();
            // $('#'+searchBoxId).show();
        }else{
            loadingShow($('.qltt-toast'));
            // $('.search-box').hide();
            // $('#'+searchBoxId).show();
            createHtml($('.swiper-wrapper'),searchBoxId);
        }

        Searchswiper.params.initialSlide = $(this).index();
        Searchswiper.update();

    });

    $('body').delegate('span', 'tap', function(event) {
        if($(this).hasClass('active')){
            $(this).removeClass('active');
        }else{
            $(this).addClass('active');
        }
    
        
    });


    init();
});