package com.xlljoy.o2o.dto;

import com.xlljoy.o2o.entity.LocalAccount;
import com.xlljoy.o2o.enums.LocalAccountStateEnum;

public class LocalAccountExecution {
	// result status
	private int state;
	// status information
	private String stateInfo;
	// current shop which we are working on
	private LocalAccount localAccount;
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
	public LocalAccount getLocalAccount() {
		return localAccount;
	}
	public void setLocalAccount(LocalAccount localAccount) {
		this.localAccount = localAccount;
	}
	
	public LocalAccountExecution(LocalAccountStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	public LocalAccountExecution(LocalAccountStateEnum stateEnum, LocalAccount localAccount) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.localAccount = localAccount;
	}

}
