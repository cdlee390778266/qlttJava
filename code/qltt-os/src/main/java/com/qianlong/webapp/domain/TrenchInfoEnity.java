package com.qianlong.webapp.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class TrenchInfoEnity {

	/**
	 * 服务渠道
	 */
	@JSONField(name = "svcchnl")
	private String svcchnl;
	
	/**
	 * 渠道绑定账号
	 */
	@JSONField(name = "bindacct")
	private String bindacct;

	public String getSvcchnl() {
		return svcchnl;
	}

	public void setSvcchnl(String svcchnl) {
		this.svcchnl = svcchnl;
	}

	public String getBindacct() {
		return bindacct;
	}

	public void setBindacct(String bindacct) {
		this.bindacct = bindacct;
	}

	@Override
	public String toString() {
		return "TdPartAuthLoginEntity [svcchnl=" + svcchnl + ", bindacct=" + bindacct + "]";
	}
}
