/* 
 * @Author: lee
 * @Date:   2017-04-07 13:49:55
 * @Last Modified time: 2017-04-19 10:50:26
 */
$(function() {
	$('body').delegate('.search-item', 'tap', function(event) {
		var $this = $(this);
		var tactic =  $this.find(".search-head").data("id");
		if(event.target.tagName == 'I'){
			$().confirm('你确定删除此关注吗？',function(){
				//取消关注
				$.ajax({
					url: 'unfollow.do',
					data: {
						"tacTic": tactic,
						"tacPrm": 0
					},
					dataType: "json",
					error: function(jqXHR, textStatus, errorThrown) {
						$().alert("取消关注失败！");
					},
					success: function(data, textStatus, jqXHR) {
						if (data.status == 1){
							$this.remove();
							displayEmpty();
							$().alert("取消关注成功");
						}else
							$().alert(data.message);
					}
				});
			})
		} else {
			var url ="../stock/home.do?tactic=" + tactic + "&tacname=" + $this.find(".search-head").data("name");
			if(new String(tactic).indexOf("11")==0)
				url += "&isCombRequest=true";
			var href = encodeURI(url);
			window.location.href = href;
		}
	});
});