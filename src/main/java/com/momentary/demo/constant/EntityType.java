package com.momentary.demo.constant;

public enum EntityType {
	
	PRODUCT("product"), USER("user");
	
	private String presentName;
	
	private EntityType( String name) {
		this.presentName = name;
	}
	
	@Override
	public String toString() {
		return this.presentName;
	}

}
