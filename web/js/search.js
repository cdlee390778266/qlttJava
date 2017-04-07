/* 
* @Author: lee
* @Date:   2017-04-07 17:02:56
* @Last Modified time: 2017-04-07 18:18:01
*/


$(document).ready(function(){

    var createHtml = function($parent,childId){
        var html = '';

        $.ajax({
            url: '../data/search.json',
            type: 'post',
            success: function(resData){
                    html += '<div class="search-box" id="' + childId + '" >';
                    for(var i in resData){
                        html += '<div class="search-item " >'
                             +      '<div class="search-head">' + resData[i].searchHead + '</div>'
                             +      '<div class="search-body">' + resData[i].searchBody + '</div>'
                             +      '<div class="search-foot">'
                             +          '<span >关注</span>'
                             +      '</div>'         
                    }
                    html += '</div>';

                    $parent.append(html);

            },
            error: function(xhr, type){
                alert('Ajax error!')
            }
        })
        
        html += '</div>';
    }

    var init = function(){
        createHtml($('.search'),'search-bod-0');
    }


    $('#header').delegate('li', 'click', function(event) {

        var searchBoxId = 'search-bod-'+$(this).index();

        $('#header li').removeClass('active');
        $(this).addClass('active');
        if($('#'+searchBoxId).length>0){
            $('.search-box').hide();
            $('#'+searchBoxId).show();
        }else{
            $('.search-box').hide();
            $('#'+searchBoxId).show();
            createHtml($('.search'),searchBoxId);
        }

    });

    $('body').delegate('span', 'click', function(event) {
        if($(this).hasClass('active')){
            $(this).removeClass('active');
        }else{
            $(this).addClass('active');
        }
    
        
    });


    init();
});