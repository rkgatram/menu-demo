package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class MenuHelper {

	public List<Menu> getMenuItems() {
		
		List<Menu> dbMenuList = new ArrayList<Menu>();
		
		dbMenuList.add(new Menu("Home", 1, 0));
		dbMenuList.add(new Menu("Tools", 3, 0));
		dbMenuList.add(new Menu("Support", 4, 0));
		
		dbMenuList.add(new Menu("T11", 31, 3));
		dbMenuList.add(new Menu("T21", 32, 31));
		dbMenuList.add(new Menu("T22", 33, 32));
		
		dbMenuList.add(new Menu("S11", 41, 4));
		dbMenuList.add(new Menu("S21", 42, 41));
		dbMenuList.add(new Menu("S22", 43, 41));
		dbMenuList.add(new Menu("S23", 44, 41));
		dbMenuList.add(new Menu("S31", 45, 43));
		dbMenuList.add(new Menu("S32", 46, 43));
		dbMenuList.add(new Menu("S33", 47, 44));
		
		return dbMenuList;
	}
	
	public List<MenuBean> createMenuStructure() {
		
		List<Menu> dbMenuList = getMenuItems();
		List<Menu> submenus = dbMenuList.stream().filter(s -> s.getParentId() != 0).collect(Collectors.toList());
		
		System.out.println(submenus);
		
		List<MenuBean> menuBeans = new ArrayList<>();
		MenuBean mb1 = new MenuBean();
		mb1.setName("Home");
		mb1.setLinkId(1);
		menuBeans.add(mb1);
		
		MenuBean mb2 = new MenuBean();
		mb2.setName("Tools");
		mb2.setLinkId(3);
		menuBeans.add(mb2);
		
		MenuBean mb3 = new MenuBean();
		mb3.setName("Support");
		mb3.setLinkId(4);
		menuBeans.add(mb3);
		
		//[Menu(name=T11, linkId=31, parentId=3), Menu(name=T21, linkId=32, parentId=31), Menu(name=T22, linkId=33, parentId=32), Menu(name=S11, linkId=41, parentId=4), Menu(name=S21, linkId=42, parentId=41), Menu(name=S22, linkId=43, parentId=41), Menu(name=S23, linkId=44, parentId=41), Menu(name=S31, linkId=45, parentId=43), Menu(name=S32, linkId=46, parentId=43), Menu(name=S33, linkId=47, parentId=44)]

		submenus.forEach(menu -> deepSearch(menuBeans, menu));
		
		return menuBeans;
	}
		
	public void deepSearch(List<MenuBean> menuBeans, Menu menu) {
		
		/*
			Optional<MenuBean> matchedBean = menuBeans.stream().filter(mb -> mb.getLinkId() == menu.getParentId()).findFirst();
			
			if(matchedBean.isPresent()) {
				addMenuItem(matchedBean.get(), menu);
			}
		*/
		
		
		menuBeans.forEach(mb -> {
			if(mb.getLinkId() == menu.getParentId()) {
				addMenuItem(mb, menu);
				//break;
			}else {
				if(mb.getSubMenus() != null && !mb.getSubMenus().isEmpty()) {
					// if menuBean has subMenus
					deepSearch(mb.getSubMenus(), menu);
				}
			}
		});
	}
	
	private void addMenuItem(MenuBean mb, Menu menu) {
		MenuBean temp = new MenuBean();
		temp.setName(menu.getName());
		temp.setLinkId(menu.getLinkId());
		if(mb.getSubMenus() == null) {
			mb.setSubMenus(new ArrayList<>());
		}
		mb.getSubMenus().add(temp);
	}
}
