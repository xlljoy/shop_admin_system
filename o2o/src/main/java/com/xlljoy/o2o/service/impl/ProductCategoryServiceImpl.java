package com.xlljoy.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xlljoy.o2o.dao.ProductCategoryDao;
import com.xlljoy.o2o.entity.ProductCategory;
import com.xlljoy.o2o.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	ProductCategoryDao productCategoryDao;
	@Override
	public int addProductCategory(ProductCategory productCategory) {
		if (productCategory != null) {
			return productCategoryDao.insertProductCategory(productCategory);
		}
		return 0;
	}

	@Override
	public List<ProductCategory> getProductCategoryList() {
		return productCategoryDao.queryProductCategory();
	}

	@Override
	public List<ProductCategory> getProductCategoryListByShopId(long shopId) {
		if (shopId > 0) {
			return productCategoryDao.queryProductCategoryByShopId(shopId);
		}
		return null;
	}
}
