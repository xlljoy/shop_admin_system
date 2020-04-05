package com.xlljoy.o2o.enums;

public enum ProductCategoryStateEnum {

	CHECK(0, "checking"), OFFLINE(-1, "illegal product category"), SUCCESS(1, "well done"), PASS(2, "pass verification"), 
	INNER_ERROR(-1001, "internal system error "), NULL_SHOPID(-1002, "ShopId is empty"),NULL_PRODUCT_CATEGORY(-1003, "product category is empty"), 
	NULL_LIST(-1004, "adding product category num less than 1");
	private int state;
	private String stateInfo;
	
	private ProductCategoryStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	
	public static ProductCategoryStateEnum stateOf(int state) {
		for (ProductCategoryStateEnum stateEnum : values()) {
			if (stateEnum.state == state) {
				return stateEnum;
			}
		}
		return null;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}
}
