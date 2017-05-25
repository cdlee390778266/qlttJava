/* 
 * @Author: lee
 * @Date:   2017-04-07 13:49:55
 * @Last Modified time: 2017-04-19 10:50:26
 */
$(function() {
	$('body').delegate('.search-item', 'tap', function(event) {
		if(event.target.tagName == 'I') {
			if (window.confirm('你确定删除此关注吗？')) {
				//取消关注
				var $this =$(this);
				var tacTic =  $this.find(".search-head").data("id");
				console.log("取消关注的指标代码为:" + tacTic);
				$.ajax({
					url: 'unfollow.do',
					data: {
						"tacTic": tacTic,
						"tacPrm": 0
					},
					dataType: "json",
					error: function(jqXHR, textStatus, errorThrown) {
						alert("取消关注失败！");
					},
					success: function(data, textStatus, jqXHR) {
						if (data.status == 1){
							$this.remove();
							displayEmpty();
						}
						else
							alert(data.message);
					}
				});
			}
		} else {
			var tactic = $(this).find(".search-head").data("id");
			var url ="../stock/home.do?tactic=" + tactic + "&tacname=" + $(this).find(".search-head").data("name");
			if(tactic.indexof("11")==0)
				url += "&isCombRequest=true";
			var href = encodeURI(url);
			window.location.href = href;
		}
	});
});