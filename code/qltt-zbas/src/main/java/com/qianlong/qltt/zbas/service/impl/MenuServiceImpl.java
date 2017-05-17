package com.qianlong.qltt.zbas.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.qianlong.qltt.zbas.entity.Admin;
import com.qianlong.qltt.zbas.entity.Menu;
import com.qianlong.qltt.zbas.service.IMenuService;
import com.qianlong.qltt.zbas.util.JSONUtil;
import com.qianlong.qltt.zbas.util.StringUtil;

@Service("menuService")
public class MenuServiceImpl implements IMenuService {
	private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
	
	@Override
	public List<Menu> selectMenu(Admin admin) {
		List<Menu> menus = new ArrayList<Menu>();
		Menu menu  = new  Menu();
		
		menu.setMenuid("0000");
		menu.setMenuname("密码修改");
		menu.setMenuurl("/admin/gopwd.html");
		menus.add(menu);
		
		menu = new Menu();
		menu.setMenuid("0001");
		menu.setMenuname("原生指标管理");
		menu.setMenuurl("/protactic/golist.html");
		menus.add(menu);
		
		menu = new Menu();
		menu.setMenuid("0002");
		menu.setMenuname("自定义指标查询");
		menu.setMenuurl("/combtactic/golist.html");
		menus.add(menu);
		
		menu = new Menu();
		menu.setMenuid("0003");
		menu.setMenuname("原生指标分组管理");
		menu.setMenuurl("/grpprotac/golist.html");
		menus.add(menu);
		
		menu = new Menu();
		menu.setMenuid("0004");
		menu.setMenuname("数据库表复制");
		menu.setMenuurl("/tablemanage/golist.html");
		menus.add(menu);
		
		return menus;
	}

	@Override
	public List<Map<String, Object>> convertMenusToMap(List<Menu> menus,String parentId){
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		if(!CollectionUtils.isEmpty(menus)){
			Map<String, Object> map ;
			for(Menu menu:menus){
				map = convertMenuToMap(menu,parentId);
				mapList.add(map);
			}
		}
		logger.debug((JSONUtil.arrayToJson(mapList)));
		return mapList;
	}
	
	public  Map<String, Object> convertMenuToMap(Menu menu,String parentId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id",menu.getMenuid());
		map.put("text", menu.getMenuname());
		map.put("parentId", parentId);
		map.put("url", StringUtil.nullOrBlank(menu.getMenuurl())?"":menu.getMenuurl());
		if(!CollectionUtils.isEmpty(menu.getChildmenus())){// 如果存在子菜单递归转换
			map.put("children", convertMenusToMap(menu.getChildmenus(),menu.getMenuid()));
		}
		map.put("isLeaveMenu",!StringUtil.nullOrBlank(menu.getMenuurl()));// 是否叶子菜单
		return map;
	}
	
	public static void main(String[] args) {
		MenuServiceImpl menuServiceImpl = new  MenuServiceImpl();
		System.out.println(JSONUtil.arrayToJson(menuServiceImpl.convertMenusToMap(menuServiceImpl.selectMenu(null), null)));
	}
}
