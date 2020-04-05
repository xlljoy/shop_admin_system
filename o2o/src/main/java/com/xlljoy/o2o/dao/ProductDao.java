package com.xlljoy.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xlljoy.o2o.entity.Product;

public interface ProductDao {
	List<Product> queryProductList();
	
	// search product by name(likely), product status, shopId, productCategory
	List<Product> queryProductListBySearch(@Param("productCondition") Product productCondition, 
			@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);
	
	int queryProductCount(@Param("productCondition") Product productCondition);
	int insertProduct(Product product);
	Product queryProductById(Long productId);
	List<Product> queryProductListByShopId(long shopId);
	int updateProduct(Product product);
	int updateProductCategoryToNull(long productCategoryId);
}
