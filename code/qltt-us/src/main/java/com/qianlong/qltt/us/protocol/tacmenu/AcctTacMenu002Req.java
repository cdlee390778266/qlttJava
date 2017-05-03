package com.qianlong.qltt.us.protocol.tacmenu;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.qianlong.qltt.us.protocol.Ttacct;
import com.qianlong.qltt.us.util.JSONUtil;

public class AcctTacMenu002Req extends Ttacct{
	
	@NotNull
	private Tactic deltacmenu;

	@Valid
	public Tactic getDeltacmenu() {
		return deltacmenu;
	}

	public void setDeltacmenu(Tactic deltacmenu) {
		this.deltacmenu = deltacmenu;
	}
	
	public static void main(String[] args) {
		AcctTacMenu002Req req = new AcctTacMenu002Req();
		req.setTtacct("tt000001");
		Tactic tactic = new Tactic();
		req.setDeltacmenu(tactic);
		tactic.setTactic("指标1");
		System.out.println(JSONUtil.objToJson(req));
	}
}
