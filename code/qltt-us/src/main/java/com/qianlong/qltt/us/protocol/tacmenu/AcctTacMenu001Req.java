package com.qianlong.qltt.us.protocol.tacmenu;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.qianlong.qltt.us.protocol.Ttacct;

public class AcctTacMenu001Req extends Ttacct{
	
	@Valid
	@NotNull
	private AddTacMenu addtacmenu;

	public AddTacMenu getAddtacmenu() {
		return addtacmenu;
	}

	public void setAddtacmenu(AddTacMenu addtacmenu) {
		this.addtacmenu = addtacmenu;
	}
	
	
	@Override
	public String toString() {
		return "AcctTacMenu001Req [ttacct="+ getTtacct()+"," + addtacmenu.toString()+"]";
	}

	public static void main(String[] args) {
		AcctTacMenu001Req req = new AcctTacMenu001Req();
		req.setTtacct("tt000001");
		AddTacMenu addtacmenu = new AddTacMenu();
		req.setAddtacmenu(addtacmenu);
		addtacmenu.setTactic("指标1");
		addtacmenu.setTacname("指标名字1");
		addtacmenu.setTacdetail("指标内容1");
		
		System.out.println(req.toString());
	}
}
