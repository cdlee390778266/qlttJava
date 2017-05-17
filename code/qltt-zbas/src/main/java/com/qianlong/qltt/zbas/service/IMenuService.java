package com.qianlong.qltt.zbas.service;

import java.util.List;
import java.util.Map;

import com.qianlong.qltt.zbas.entity.Admin;
import com.qianlong.qltt.zbas.entity.Menu;

public interface IMenuService {
	/**获取系统菜单*/
	List<Menu> selectMenu(Admin admin);

	/**将菜单转化成easyUI需要的格式*/
	List<Map<String, Object>>  convertMenusToMap(List<Menu> menus,String parentId);
}
