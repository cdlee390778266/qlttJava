/* 
* @Author: lee
* @Date:   2017-04-07 15:15:54
* @Last Modified time: 2017-06-09 15:16:40
*/

$(document).ready(function(){

    $('#hot-search').submit(function(event) {

        if($('#hot-text').val()) {
            $('.qltt-toast').css({
                'display' : 'block',
                'opacity' : 1
            });
            $.ajax({
                url: '../data/hot.json',
                type: 'post',
                data: {
                    key: $('#hot-text').val()
                },
                success : function(data) {
                    var html = '';
                    var tags = '';
                    // 模拟数据
                    for( var i in data) {
                        if(data){
                            tags = '';
                            for(var j in data[i]['tags']){
                                tags += '<span>' + data[i]['tags'][j] +'</span>';
                            }
                            html += '<div class="hot-item">'
                                 +      '<div class="hot-item-body">'
                                 +          '<a href="">'
                                 +              '<strong>' + data[i]['title'] + '</strong>'
                                 +              '<span>' + data[i]['detail'] +'</span>'
                                 +          '</a>'
                                 +      '</div>'
                                 +      '<div class="hot-tags">' + tags + '</div>'
                                 +  '</div>'
                            $('#empty').hide();
                            $('#hot').html(html).show();
                        }else {
                            $('#hot').html('').hide();
                            $('#empty').show();
                        } 

                        $('.qltt-toast').css({
                            'display' : 'none',
                            'opacity' : 0
                        });
                    }
                },
                error: function() {
                    alert('查询失败');
                }
            })    
        }

        return false;
    });


});
