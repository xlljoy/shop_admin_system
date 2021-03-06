package com.xlljoy.o2o.entity;

import java.util.Date;

public class Shop {
	private Long id;
	// 店铺名称
	private String name;
	// 店铺简介
	private String shopDesc;
	// 店铺地址
	private String addr;
	// 店铺号码
	private String phone;
	// 店铺门面图片地址
	private String img;
	// 权重，越大越排前显示
	private Integer priority;
	// 创建时间
	private Date createTime;
	// 最近一次更新的时间
	private Date updateTime;
	// -1.不可用 0.审核中 1.可用
	private Integer enableStatus;
	// 超级管理员给店家的提醒，包括为什么审核不通过等
	private String advice;
	// 店铺所属区域
	private Zone zone;
	// 店铺是属于哪个店家的
	private User owner;
	// 店铺类别
	private ShopCategory shopCategory;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShopDesc() {
		return shopDesc;
	}
	public void setShopDesc(String shopDesc) {
		this.shopDesc = shopDesc;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getEnableStatus() {
		return enableStatus;
	}
	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
	}
	public String getAdvice() {
		return advice;
	}
	public void setAdvice(String advice) {
		this.advice = advice;
	}
	public Zone getZone() {
		return zone;
	}
	public void setZone(Zone zone) {
		this.zone = zone;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public ShopCategory getShopCategory() {
		return shopCategory;
	}
	public void setShopCategory(ShopCategory shopCategory) {
		this.shopCategory = shopCategory;
	}
	
	
}
