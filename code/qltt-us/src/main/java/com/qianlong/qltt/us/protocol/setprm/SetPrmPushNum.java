package com.qianlong.qltt.us.protocol.setprm;

import javax.validation.constraints.NotNull;

import com.qianlong.qltt.us.protocol.Ttacct;

public class SetPrmPushNum extends Ttacct{
	
	@NotNull
	private Integer 	pushnum	;//推送数量	

	public Integer getPushnum() {
		return pushnum;
	}

	public void setPushnum(Integer pushnum) {
		this.pushnum = pushnum;
	}
}
