/* 
* @Author: lee
* @Date:   2017-04-07 15:15:54
* @Last Modified time: 2017-06-28 15:34:52
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
    

    function load () {  
        var myScroll,  
            pullDown = $("#pullDown"),  
            pullDownLabel = $(".pullDownLabel"),  
            container = $('#hot'),  
            loadingStep = 0;//加载状态0默认，1显示加载状态，2执行加载数据，只有当为0时才能再次加载，这是防止过快拉动刷新  
  
            pullDown.hide();  
  
            myScroll = new IScroll("#wrapper", {  
                scrollbars: true,  
                mouseWheel: false,  
                interactiveScrollbars: true,  
                shrinkScrollbars: 'scale',  
                fadeScrollbars: true,  
                scrollY:true,  
                probeType: 2,  
                bindToWrapper:true  
            });  
            myScroll.on("scroll",function(){  
                if(loadingStep == 0 && !pullDown.attr("class").match('refresh|loading') ){  
                    if(this.y > 40){//下拉刷新操作  
                        $(".pulldown-tips").hide();  
                        pullDown.addClass("refresh").show();  
                        pullDownLabel.text("松手刷新数据");  
                        loadingStep = 1;  
                        myScroll.refresh();  
                    }
                }  
            });  
            myScroll.on("scrollEnd",function(){  
                if(loadingStep == 1){  
                    if( pullDown.attr("class").match("refresh") ){//下拉刷新操作  
                        pullDown.removeClass("refresh").addClass("loading");  
                        pullDownLabel.text("正在刷新");  
                        loadingStep = 2;  
                        pullDownAction();  
                    }  
                }  
            });  
  
        function pullDownAction(){
            setTimeout(function(){  
                $.ajax({
                    url: '../data/hot.json',
                    type: 'post',
                    success : function(data) {
                        var html = '';
                        var tags = '';
                        // 模拟数据
                        if(data.length){
                            for( var i in data) {
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
                                
                                container.prepend(html);  
                                pullDown.attr('class','').hide();  
                                myScroll.refresh();  
                                loadingStep = 0;  
                                $(".pulldown-tips").show();
                            }
                        }else {
                            pullDownLabel.text("已加载全部数据");
                            loadingStep = 0;
                        } 
                    },
                    error: function() {
                        pullDownLabel.text("加载失败，请检查网络！");
                        loadingStep = 0;
                    }
                })
            },1000);

            
        }  
        
        document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);  
        }

        load();
});
