package com.xlljoy.o2o.enums;

public enum ShopStateEnum {
	CHECK(0, "checking"), OFFLINE(-1, "illegal shop"), SUCCESS(1, "well done"), PASS(2, "pass verification"), INNER_ERROR(-1001,
			"internal system error "), NULL_SHOPID(-1002, "ShopId is empty"),NULL_SHOP(-1003, "shop info is empty"), NULL_IMG(-1004, "shop image does not exist");
	private int state;
	private String stateInfo;
	
	private ShopStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	
	public static ShopStateEnum stateOf(int state) {
		for (ShopStateEnum stateEnum : values()) {
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
