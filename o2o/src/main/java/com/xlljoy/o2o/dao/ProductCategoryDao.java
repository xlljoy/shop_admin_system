package com.xlljoy.o2o.dao;

import java.util.List;

import com.xlljoy.o2o.entity.ProductCategory;

public interface ProductCategoryDao {
	int insertProductCategory(ProductCategory productCategory);
	List<ProductCategory> queryProductCategory();
	List<ProductCategory> queryProductCategoryByShopId(long shopId);
}
