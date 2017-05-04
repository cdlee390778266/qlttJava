package com.qianlong.qltt.us.protocol.tacmenu;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.qianlong.qltt.us.protocol.Ttacct;
import com.qianlong.qltt.us.util.JSONUtil;

public class AcctTacMenu003Req extends Ttacct{
	
	private List<AddTacMenu003> addtacmenu;

	@Valid
	public List<AddTacMenu003> getAddtacmenu() {
		return addtacmenu;
	}

	public void setAddtacmenu(List<AddTacMenu003> addtacmenu) {
		this.addtacmenu = addtacmenu;
	}
	
	public static void main(String[] args) {
		AcctTacMenu003Req req = new AcctTacMenu003Req();
		req.setTtacct("tt000001");
		
		List<AddTacMenu003> menu003s = new ArrayList<AddTacMenu003>();
		req.setAddtacmenu(menu003s);
		AddTacMenu003 menu003 = new AddTacMenu003();
		menu003.setOrder(1);
		menu003.setTactic("指标2");
		menu003.setTacname("指标名字2");
		menu003.setTacdetail("指标描述2");
		menu003s.add(menu003);
		
		System.out.println(JSONUtil.objToJson(req));
	}
}
