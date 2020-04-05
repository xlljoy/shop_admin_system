package com.xlljoy.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xlljoy.o2o.entity.ProductCategory;
import com.xlljoy.o2o.exceptions.ProductCategoryException;

public interface ProductCategoryDao {
	int insertProductCategory(ProductCategory productCategory);
	List<ProductCategory> queryProductCategory();
	List<ProductCategory> queryProductCategoryByShopId(long shopId);
	// volume add product category
	int batchInsertProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryException;
	
	//delete certain product category
	int deleteProductCategory(@Param("productCategoryId") long productCategoryId, @Param("shopId") long shopId);
}
