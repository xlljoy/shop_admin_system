package com.xlljoy.o2o.entity;

import java.util.Date;

public class UserShopMap {
	// 主键Id
	private Long userShopId;
	// 创建时间
	private Date createTime;
	// 顾客在该店铺的积分
	private Integer point;
	// 顾客信息实体类
	private User customer;
	// 店铺信息实体类
	private Shop shop;
	public Long getUserShopId() {
		return userShopId;
	}
	public void setUserShopId(Long userShopId) {
		this.userShopId = userShopId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	public User getCustomer() {
		return customer;
	}
	public void setCustomer(User customer) {
		this.customer = customer;
	}
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
}
