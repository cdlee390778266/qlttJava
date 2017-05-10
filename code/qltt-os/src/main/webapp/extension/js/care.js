/* 
 * @Author: lee
 * @Date:   2017-04-07 13:49:55
 * @Last Modified time: 2017-04-19 10:50:26
 */

$(function() {
	$('body').delegate('.search-foot>span', 'tap', function(event) {
		$(this).toggleClass('active');
	});

	$('body').delegate('.search-head>i', 'tap', function(event) {
		if (window.confirm('你确定删除此关注吗？')) {
			//取消关注
			var parent = $(this).parent();
			var tacTic = parent.data("id");
			console.log("取消关注的指标代码为:" + tacTic);
			$.ajax({
				url : 'unfollow.do',
				data : {
					"tacTic" : tacTic,
					"tacPrm" : 0
				},
				dataType : "json",
				error : function(jqXHR, textStatus, errorThrown) {
					alert("取消关注失败！");
				},
				success : function(data, textStatus, jqXHR) {
					if (data.status == 1)
						parent.parent().remove();
					else
						alert(data.message);
				}
			});
		}
	});
});