package com.xlljoy.o2o.service.impl;

public enum RedisKeysEnum {
	DAILYNEWLIST(1, "dailynewlist"), SHOPCATEGORYLIST( 2,"shopcategorylist"), ZONELIST(3,"zonelist");
	private int order;
	private String value;
	
	private RedisKeysEnum(int order, String value) {
		this.order = order;
		this.value = value;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
