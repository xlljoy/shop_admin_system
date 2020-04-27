package com.xlljoy.o2o.enums;

public enum UserStateEnum {
	CHECK(0, "checking"), OFFLINE(-1, "illegal Local Account"), SUCCESS(1, "well done"), PASS(2, "pass verification"), 
	INNER_ERROR(-1001, "internal system error "), NULL_USERID(-1002, "userId is empty"),NULL_USER(-1003, "user is empty"),
	DUPLICATE_ACCOUNT(-1004, "local account already exist");
	private int state;
	private String stateInfo;
	
	private UserStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	
	public static UserStateEnum stateOf(int state) {
		for (UserStateEnum stateEnum : values()) {
			if (stateEnum.state == state) {
				return stateEnum;
			}
		}
		return null;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
}
