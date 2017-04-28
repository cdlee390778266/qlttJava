package com.qianlong.qltt.us.protocol.setprm;

import com.qianlong.qltt.us.util.JSONUtil;

public class SetPrm002Rsp {
	
	private Integer pushscope;//推送范围	
	
	private Integer scopeprm;//范围参数	

	public Integer getPushscope() {
		return pushscope;
	}

	public void setPushscope(Integer pushscope) {
		this.pushscope = pushscope;
	}

	public Integer getScopeprm() {
		return scopeprm;
	}

	public void setScopeprm(Integer scopeprm) {
		this.scopeprm = scopeprm;
	}
	
	public static void main(String[] args) {
		SetPrm002Rsp req = new SetPrm002Rsp();
		req.setPushscope(1);
		req.setScopeprm(1);
		System.out.println(JSONUtil.objToJson(req));
	}
}
