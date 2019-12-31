package com.example.demo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Menu {

	private String name;
	private int linkId;
	private int parentId;
	
	public Menu(String name, int linkId, int parentId) {
		super();
		this.name = name;
		this.linkId = linkId;
		this.parentId = parentId;
	}
}
