/* 
* @Author: lee
* @Date:   2017-04-07 15:15:54
* @Last Modified time: 2017-04-07 15:32:05
*/

$(document).ready(function(){

    var codeFlag = true;    //获取验证码按钮能否点击旗标 true为可用，false 为不可用
    var coldeText ;         //保存获取验证码按钮文字
    var coldeTime = 60;     //获取验证码倒计时长
    var interVal;           //定时器
    var $codeEle = $('.btn-code');

    $('.login').delegate('.btn-code', 'tap', function(event) {
        if(codeFlag){
            coldeText = $(this).text();
            codeFlag = false;
            $codeEle.text(60)
            interVal = setInterval(function(){

                $codeEle.text('some text')
            }, 1000);
        }
    });
});
