package com.xlljoy.o2o.dto;

import com.xlljoy.o2o.entity.User;
import com.xlljoy.o2o.enums.LocalAccountStateEnum;
import com.xlljoy.o2o.enums.UserStateEnum;

public class UserExecution {
	private int state;
	// status information
	private String stateInfo;
	// current shop which we are working on
	private User user;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public UserExecution(UserStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	public UserExecution(UserStateEnum stateEnum, User user) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.user = user;
	}
}
