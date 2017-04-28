package com.qianlong.qltt.us.protocol.setprm;

import javax.validation.constraints.NotNull;

import com.qianlong.qltt.us.protocol.Ttacct;
import com.qianlong.qltt.us.util.JSONUtil;

public class SetPrmPushScope extends Ttacct{
	@NotNull
	private Integer pushscope;//推送范围	
	
	@NotNull
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
		SetPrmPushScope req = new SetPrmPushScope();
		req.setTtacct("tt000001");
		req.setPushscope(1);
		req.setScopeprm(1);
		System.out.println(JSONUtil.objToJson(req));
	}
}
