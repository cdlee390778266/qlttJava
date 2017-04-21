/* 
* @Author: lee
* @Date:   2017-04-07 15:15:54
* @Last Modified time: 2017-04-21 14:38:46
*/

$(document).ready(function(){

    var codeFlag = true;    //获取验证码按钮能否点击旗标 true为可用，false 为不可用
    var codeText ;          //保存获取验证码按钮文字
    var codeTime = 60;      //获取验证码倒计时长
    var codeSrcTime = 60;     
    var interVal;           //定时器
    var $codeEle = $('.btn-code span'); //验证码按钮
    var $errorEle = $('#error'); //验证码按钮

     //验证手机
    var checkMobile = function ($mobile){
        var sMobile = $mobile.val();
        if(!(/^1[3|4|5|8][0-9]\d{4,8}$/.test(sMobile))){
            // alert("不是完整的11位手机号或者正确的手机号前七位");
            // $mobile.focus();
            return false;
        }
        return true;
    } 

    //倒计时回调函数
    var changeTime = function(){
        codeTime--;
        if(codeTime>=0){
            $codeEle.text(codeTime+'秒后重新发送');
        }else{
            $codeEle.text(codeText);
            clearInterval(interVal);
            codeFlag = true;
            codeTime = codeSrcTime;
            $codeEle.removeClass('disabled');
        }
        
    }

    $('.login').delegate('.btn-code', 'tap', function(event) {
        if(codeFlag){
            codeText = $(this).text();
            codeFlag = false;
            $codeEle.text(codeTime+'秒后重新发送');
            $codeEle.addClass('disabled');
            interVal = setInterval(changeTime, 1000);
        }
    });

    $('.login').submit(function(){

        location.href = 'tpls/zcsusucucess.html';
        return false;

        if( !checkMobile($('#phone')) ){

            $errorEle.find('span').text('输入的手机号码错误！');
            $errorEle.removeClass('fadeOut');
            $errorEle.addClass('fadeIn');
            $errorEle.css('display','block');
            return false;
        }

        if( $('#code').val().length !=4 ){
            $errorEle.find('span').text('验证码长度不正确！');
            $errorEle.removeClass('fadeOut');
            $errorEle.addClass('fadeIn');
            $errorEle.css('display','block');
            return false;
        }

        
        

    });

    $('#error').tap(function(e){
        e.stopPropagation();
        $errorEle.removeClass('fadeIn');
        $errorEle.addClass('fadeOut');
        $errorEle.css('display','none');
    })    


});
