/* 
* @Author: lee
* @Date:   2017-04-07 14:10:42
* @Last Modified time: 2017-06-28 12:21:35
*/

$(function(){

    // 表单提交
    $('#login').submit(function(event) {

        if(!$('#name').val()) {
            $().alert('用户名不能为空！');
            $('#name').focus();
            return false;
        }

        if(!$('#password').val()) {
            $().alert('密码不能为空！');
            $('#password').focus();
            return false;
        }

        $('input[type=submit]').val('登录中...');
        $.ajax({
            url: '../data/login.json',
            type: 'post',
            success: function(resData){
                if(resData) {
                    location.href = 'chatlist.html';
                }else {
                    $().alert('用户名或密码错误！');
                    $('#name').focus();
                    $('input[type=submit]').val('登录');
                }     
            },
            error: function(xhr, type){
                $().alert('登录失败！');
                $('#name').focus();
                $('input[type=submit]').val('登录');
            }

        })
        
        return false;
    });
    
})