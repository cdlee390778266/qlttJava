/* 
* @Author: lee
* @Date:   2017-04-07 14:10:42
* @Last Modified time: 2017-06-27 17:04:25
*/

$(function(){

    // 获取当前的日期时间 格式“yyyy-MM-dd HH:MM:SS”
    var getNowFormatDate = function() {
        var date = new Date();
        var seperator1 = "-";
        var seperator2 = ":";
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
                + " " + date.getHours() + seperator2 + date.getMinutes()
                + seperator2 + date.getSeconds();
        return currentdate;
    } 

    var timeOut;
    var ajaxForm = function(type,data) {
        var date = getNowFormatDate();
        var imgPath = data;
        var strExtension = imgPath.substr(imgPath.lastIndexOf('.') + 1);
        if(type == 'image'){
            if (strExtension != 'jpg' && strExtension != 'gif' && strExtension != 'png' && strExtension != 'bmp') {
                $().alert("请选择图片文件");
                return;
            }
        }
        
        var formData = new FormData($('#chat')[0]);

        $.ajax({
            url: '../data/login.json',
            type: 'post',
            data: formData,
            processData : false,
            contentType : false,
            success: function(resData){
                var html = '';
                if(resData) {

                    clearTimeout(timeOut);
                    timeOut = setTimeout(function() {
                        $('#chat-btn').removeClass('active').text('+');
                    }, 300);

                    if(type == 'text') {
                        html += '<div class="chat-item">'
                             +      '<div class="chat-time"><span>' + date + '</span></div>'
                             +      '<div class="chat-main">'
                             +          '<div class="chat-content chat-content-right">'
                             +              '<span>' + $('#chat-txt').val() + '</span>'
                             +          '</div>'
                             +          '<div class="chat-face"><img src="../images/face1.png"  alt="" /></div>'
                             +      '</div>'
                             +  '</div>'

                        $('#chat-txt').val('');
                    }

                    if(type == 'image') {
    
                        html += '<div class="chat-item">'
                             +      '<div class="chat-time"><span>' + date + '</span></div>'
                             +      '<div class="chat-main">'
                             +          '<div class="chat-content chat-content-right">'
                             +              '<span class="content-img"><img src="' + '../images/face1.png' + '"  alt="" class="big" /></span>'
                             +          '</div>'
                             +          '<div class="chat-face"><img src="../images/face1.png"  alt="" /></div>'
                             +      '</div>'
                             +  '</div>'

                             $('#chat-img').val('');
                    }

                    $('.chat-body').append(html);
                    $(window).scrollTop($(document).height() - $(window).height());

                }else {
                    $().alert('发送失败！');
                }
            },
            error: function(xhr, type){
                $().alert('发送失败！');
            }

        })
    }

    // 阻止表单提交
    $('#chat').submit(function(event) {
        return false;  
    });

    // 点击发送按钮表单提交
    $('#chat-btn').tap(function(event) {
        if(!$('#chat-txt').val()) {
            $().alert('发送内容为空！');
            return;
        }

        ajaxForm('text',$('#chat-txt').val());
    });

    // 选中图片后表单提交
    $('#chat-img').change(function(event) {
        if(!$('#chat-img').val()) {
            $().alert('发送内容为空！');
            return;
        }

        ajaxForm('image',$('#chat-img').val());
    });

    //text改变时改变按钮
    $('#chat-txt').keyup(function(event) {
        if(!$(this).val()) {
            $('#chat-btn').removeClass('active').text('+');
        }else {
            $('#chat-btn').addClass('active').text('发送');
        }
    });

    // 大图查看图片
    var win = $(window);  
    var currentIndex = 0;  

    $('#chat-body').delegate('.big', 'tap', function(event) {
        currentIndex = $('.big').index($(this));
        showLarge($(this).attr('src'));
    });
    function showLarge(src,callback){
        $('#container-large').show();
        $('.loading').show();
        var imgObj = new Image();
        imgObj.src =   src;
        imgObj.onload = function(){
            var imgW = this.width;
            var imgH = this.height;
            var zW = win.width();
            var zH = win.height();
            var relW = imgW/imgH*zH;
            var relH = imgH/imgW*zW; 
            var top=0,left=0;
            
            $('.loading').hide();

            if(this.width >= zW || this.height >= zH ){
               if((imgW/imgH > 1.2 && relH<=zH) || (imgW/imgH <= 1.2 && relW>zW)){
                    top = parseInt((zH-relH)/2);
                    
                    $('#largeImg').css({
                        'top' : top,
                        'left' : 0,
                        'width' : zW,
                        'height' : relH
                    }).attr({
                        'src' : this.src
                    });
                }else{
                    left = parseInt((zW-relW)/2);
                    $('#largeImg').css({
                        'left' : left,
                        'top' : 0,
                        'height' : zH,
                        'width' : relW
                    }).attr({
                        'src' : this.src
                    });
                } 
            }else {
                top = (zH - this.height) / 2;
                left = (zW - this.width) / 2;
                    
                $('#largeImg').css({
                    'top' : top,
                    'left' : left,
                    'width' : this.width,
                    'height' : this.height
                }).attr({
                    'src' : this.src
                });
            }
            

            callback&&callback();
            $('#container-large img')[0].addEventListener('webkitAnimationEnd', function(){
                $(this).removeClass('animated bounceInRight bounceInLeft');
                //this.removeEventListener('webkitAnimationEnd');
            })
        }
    }

    $('#container-large')
    .tap(function(){
        $('#container-large').hide();
    })
    .swipeLeft(function(){
        if(currentIndex >= $('.big').length - 1) return;
        currentIndex++;
        
        showLarge($('.big').eq(currentIndex).attr('src'),function(){
            $('#container-large img').addClass('animated bounceInRight')
        });
    })
    .swipeRight(function(){
        if(currentIndex <= 0) return;
        currentIndex--;

        showLarge($('.big').eq(currentIndex).attr('src'),function(){
            $('#container-large img').addClass('animated bounceInLeft')
        });
    })

})
