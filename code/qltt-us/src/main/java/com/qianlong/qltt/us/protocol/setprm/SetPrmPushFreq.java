package com.qianlong.qltt.us.protocol.setprm;

import javax.validation.constraints.NotNull;

import com.qianlong.qltt.us.protocol.Ttacct;

public class SetPrmPushFreq extends Ttacct{
	
	@NotNull
	private Integer pushfreq;//推送频率	

	public Integer getPushfreq() {
		return pushfreq;
	}

	public void setPushfreq(Integer pushfreq) {
		this.pushfreq = pushfreq;
	}
}
