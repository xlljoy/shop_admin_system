package com.xlljoy.o2o.entity;

import java.util.Date;

public class ProductCategory {
	private Long id;
	// 该类别是属于哪个店铺的
	private Long shopId;
	// 类别名
	private String name;
	// 权重，越大越排前显示
	private Integer priority;
	// 创建时间
	private Date createTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

	
}
