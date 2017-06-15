(function ($) {
      
    $.fn.alert = function(message){
        if(!$('body').find('.alert').length){
            var html = '';
            html += '<div class="animated alert" style="position: fixed;top: 0;bottom: 0;left: 0;right: 0;display: block;z-index: 99999;color: #fff;">'
                 +      '<span style="display: block;position: absolute;left: 0;top: 0;padding:10px;font-size: 1.4rem;background-color: rgba(0, 0, 0, 0.5);border-radius: 4px;line-height:1.8rem;min-width:166px;max-width:80%;">'
                 +      '</span>'
                 +   '</div>'
            $('body').append(html);
            $('.alert').tap(function(e){
                $('.alert').removeClass('fadeIn').addClass('fadeOut').css('display','none');
                e.stopPropagation();
            })
        }
        $('.alert span').text(message);
        var textAlign = 'center';
        if($('.alert span').width() > 166){
            textAlign = 'left';
        }
        $('.alert').removeClass('fadeOut').css('display','block').addClass('fadeIn');
    
        $('.alert span').css({
            'left' : ($(window).width()-$('.alert span').width())/2,
            'top' : ($(window).height()-$('.alert span').height())/2,
            'textAlign' : textAlign
        });
    }

    $.fn.confirm = function(options,fn){
        if(!$('body').find('.confirm').length){
            var html = '';
            html += '<div class="animated confirm" style="position: fixed;top: 0;bottom: 0;left: 0;right: 0;display: block;z-index: 99999;color: #666;background-color:rgba(0,0,0,0);font-size:1.4rem;">'
                 +      '<div class="confirm-main" style="position: absolute;top: 0;left: 0;width: 300px;z-index: 99999;background: #fff;border-radius: 4px;box-shadow: 0 5px 10px 0px #aaa;">'
                 +          '<div class="confirm-head" style="height: 40px;line-height: 40px;text-align: center;border-bottom:1px solid #eee;padding:0 10px;">消息提示<img src="../images/confirm.png"  style="width: 18px;margin-top: 10px;float: right;"  alt="" /></div>'
                 +          '<div class="confirm-body" style="min-height:80px;padding:10px;border-bottom:1px solid #eee;line-height:2rem;font-size:1.2rem;"></div>'
                 +          '<div class="confirm-foot" style="text-align:center;color:#56aff8;height: 40px;line-height: 40px;">'
                 +              '<span class="confirm-btn-no" style="padding:0 20px;margin:0 20px;">取消</span>'
                 +              '<span class="confirm-btn-yes" style="padding:0 20px;margin:0 20px;">确定</span>'
                 +          '</div>'
                 +      '</div>'
                 +  '</div>'
            $('body').append(html);

            //取消
            $('.confirm-btn-no, .confirm-head').tap(function(e){
                $('.confirm').removeClass('fadeIn').addClass('fadeOut').css('display','none');
                e.stopPropagation();
            })

            //确定
            $('.confirm-btn-yes').tap(function(e){
                $('.confirm').removeClass('fadeIn').addClass('fadeOut').css('display','none');
                if(typeof fn == 'function'){
                    fn();
                }
                $('body').find('.confirm').remove();
                e.stopPropagation();
            })
        }
        var text = typeof options == 'string' ? options : options.text;
        var width = options.width || 300;
        var height = $('.confirm-main').height();
        $('.confirm-body').text(text);
         $('.confirm').removeClass('fadeOut').css('display','block').addClass('fadeIn');
        $('.confirm-main').css({
            'width' : width,
            'left' : ($(window).width()-width)/2,
            'top' : ($(window).height()-$('.confirm-main').height())/2
        }); 
    }
})(window.Zepto);