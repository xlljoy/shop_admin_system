package com.xlljoy.o2o.dto;

import java.util.List;

import com.xlljoy.o2o.entity.ProductCategory;
import com.xlljoy.o2o.enums.ProductCategoryStateEnum;

public class ProductCategoryExecution {
	private int state;
	private String stateInfo;
	
	private List<ProductCategory> productCategoryList;
	
	
	public List<ProductCategory> getProductCategoryList() {
		return productCategoryList;
	}

	public void setProductCategoryList(List<ProductCategory> productCategoryList) {
		this.productCategoryList = productCategoryList;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public ProductCategoryExecution() {}
	
	public ProductCategoryExecution (ProductCategoryStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	
	public ProductCategoryExecution (ProductCategoryStateEnum stateEnum, List<ProductCategory> productCategoryList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.productCategoryList = productCategoryList;
	}
	
	
}
