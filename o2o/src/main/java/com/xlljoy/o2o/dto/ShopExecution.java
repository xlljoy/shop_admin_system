package com.xlljoy.o2o.dto;

import java.util.List;

import com.xlljoy.o2o.entity.Shop;
import com.xlljoy.o2o.enums.ShopStateEnum;

public class ShopExecution {
	// result status
	private int state;
	// status information
	private String stateInfo;
	// current shop which we are working on
	private Shop shop;
	//shop amount
	private int count;
	//for searching shop from list
	private List<Shop> shopList;

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

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Shop> getShopList() {
		return shopList;
	}

	public void setShopList(List<Shop> shopList) {
		this.shopList = shopList;
	}

	public ShopExecution() {}
	
	public ShopExecution(ShopStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	
	public ShopExecution(ShopStateEnum stateEnum, Shop shop) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.shop = shop;
	}
	
	public ShopExecution(ShopStateEnum stateEnum, Shop shop, List<Shop> shopList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.shop = shop;
		this.shopList = shopList;
	}
}
