/* 
 * @Author: lee
 * @Date:   2017-04-07 15:15:54
 * @Last Modified time: 2017-04-12 09:34:42
 */
$(document).ready(function() {

	var codeFlag = true; // 获取验证码按钮能否点击旗标 true为可用，false 为不可用
	var codeText; // 保存获取验证码按钮文字
	var codeTime = 60; // 获取验证码倒计时长
	var codeSrcTime = 60;
	var interVal; // 定时器
	var $codeEle = $('.btn-code span'); // 验证码按钮
	var $errorEle = $('#error'); // 验证码按钮

	// 验证手机
	var checkMobile = function(mobile) {
		if (!(/^1[3|4|5|8][0-9]\d{4,8}$/.test(mobile))) {
			return false;
		}
		return true;
	}

	// 倒计时回调函数
	var changeTime = function() {
		codeTime --;
		if (codeTime >= 0) {
			$codeEle.text(codeTime + '秒后重新发送');
		} else {
			$codeEle.text(codeText);
			clearInterval(interVal);
			codeFlag = true;
			codeTime = codeSrcTime;
			$codeEle.removeClass('disabled');
		}

	}

	$('.login').delegate('.btn-code', 'tap', function(event) {
		if (codeFlag) {
			var phone = $("#phone").val();
			if (!checkMobile(phone)) {
				return false;
			}
			
			codeText = $(this).text();
			codeFlag = false;
			$codeEle.text(codeTime + '秒后重新发送');
			$codeEle.addClass('disabled');
			
			$.ajax({
				url : "code.do",
				data : {"phone" : phone},
				error : function(jqXHR, textStatus, errorThrown) {
					$().alert("生成验证码失败");
				},
				success : function(data, textStatus, jqXHR) {
					$().alert(data);
				}
			});
			
			interVal = setInterval(changeTime, 1000);
		}
	});

	$('.login').submit(function() {
		if (!checkMobile($('#phone').val())) {

			$errorEle.find('span').text('输入的手机号码错误！');
			$errorEle.removeClass('fadeOut');
			$errorEle.addClass('fadeIn');
			$errorEle.css('display', 'block');
			return false;
		}

		if ($('#code').val().length != 6) {
			$errorEle.find('span').text('验证码长度不正确！');
			$errorEle.removeClass('fadeOut');
			$errorEle.addClass('fadeIn');
			$errorEle.css('display', 'block');
			return false;
		}
	});

	$('#error').tap(function(e) {
		e.stopPropagation();
		$errorEle.removeClass('fadeIn');
		$errorEle.addClass('fadeOut');
		$errorEle.css('display', 'none');
	})

});
