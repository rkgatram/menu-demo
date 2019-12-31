package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/home")
public class MenuController {
	
	@Autowired
	private MenuHelper helper;

	@GetMapping("/menu")
	public List<MenuBean> getMenus() {
		
		return helper.createMenuStructure();
	}
	

}
