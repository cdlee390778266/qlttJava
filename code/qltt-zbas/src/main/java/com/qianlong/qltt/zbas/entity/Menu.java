package com.qianlong.qltt.zbas.entity;

import java.util.List;

public class Menu {
	//菜单id
	private String menuid;

	//菜单名称
	private String menuname;

	//菜单URL
	private String menuurl;

	// 子菜单
	private List<Menu> childmenus;

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public String getMenuurl() {
		return menuurl;
	}

	public void setMenuurl(String menuurl) {
		this.menuurl = menuurl;
	}

	public List<Menu> getChildmenus() {
		return childmenus;
	}

	public void setChildmenus(List<Menu> childmenus) {
		this.childmenus = childmenus;
	}
}
