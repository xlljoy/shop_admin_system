package com.xlljoy.o2o.service;

import java.util.List;

import com.xlljoy.o2o.dto.ProductCategoryExecution;
import com.xlljoy.o2o.entity.ProductCategory;
import com.xlljoy.o2o.exceptions.ProductCategoryException;

public interface ProductCategoryService {
	int addProductCategory(ProductCategory productCategory);
	List<ProductCategory> getProductCategoryList();
	List<ProductCategory> getProductCategoryListByShopId(long shopId);
	
	// batch add product category
	ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList);
	
	// delete product category from a shop
	ProductCategoryExecution deleteProductCategory(long shopId, long productCategoryId) throws ProductCategoryException;
}
