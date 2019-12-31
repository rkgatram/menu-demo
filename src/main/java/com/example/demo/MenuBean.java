package com.example.demo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class MenuBean {

	private String name;
	
	@JsonIgnore
	private int linkId;
	
	private List<MenuBean> subMenus;
}
