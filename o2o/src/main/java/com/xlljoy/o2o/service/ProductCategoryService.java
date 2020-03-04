package com.xlljoy.o2o.service;

import java.util.List;

import com.xlljoy.o2o.entity.ProductCategory;

public interface ProductCategoryService {
	int addProductCategory(ProductCategory productCategory);
	List<ProductCategory> getProductCategoryList();
	List<ProductCategory> getProductCategoryListByShopId(long shopId);
}
