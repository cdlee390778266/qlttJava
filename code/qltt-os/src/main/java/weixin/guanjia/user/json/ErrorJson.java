package weixin.guanjia.user.json;

import net.sf.json.JSONObject;
import weixin.guanjia.user.entity.WeixinTag;

public class ErrorJson {
	
	private Integer errcode;
	
	private String errmsg;

	public Integer getErrcode() {
		return errcode;
	}

	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	
	public static void main(String[] args) {
		//String json ="{\"tag\":{\"id\":134,\"name\":\"广东\"}}";
		String json ="{\"errcode\":0,\"errmsg\":\"ok\"}";
		JSONObject jsonObject = JSONObject.fromObject(json);
		json = jsonObject.getString("tag");//获取tag
		WeixinTag tag = JSONUtil.toBean(json, WeixinTag.class);
		System.out.println("11111111111");
	}
}
