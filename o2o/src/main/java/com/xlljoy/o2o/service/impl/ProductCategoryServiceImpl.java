package com.xlljoy.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xlljoy.o2o.dao.ProductCategoryDao;
import com.xlljoy.o2o.dao.ProductDao;
import com.xlljoy.o2o.dto.ProductCategoryExecution;
import com.xlljoy.o2o.entity.ProductCategory;
import com.xlljoy.o2o.enums.ProductCategoryStateEnum;
import com.xlljoy.o2o.exceptions.ProductCategoryException;
import com.xlljoy.o2o.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	private ProductCategoryDao productCategoryDao;
	@Autowired
	private ProductDao productDao;
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

	@Override
	@Transactional
	public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryException {
		try {
			if (productCategoryList.size() > 0 && productCategoryList != null) {
				int effect = productCategoryDao.batchInsertProductCategory(productCategoryList);
				if (effect == productCategoryList.size()) {
					return  new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS, productCategoryList);
				} else {
					throw new ProductCategoryException("failed to create product categories");
				}
			}
		}
		catch (Exception e) {
			throw new ProductCategoryException("batchAddProductCategory " + e.getMessage());
		}
		return new ProductCategoryExecution(ProductCategoryStateEnum.NULL_LIST);
	}

	// 1. delete all products under this product category
	// 2. delete this product category
	@Override
	@Transactional
	public ProductCategoryExecution deleteProductCategory(long shopId, long productCategoryId) throws ProductCategoryException {
		if (shopId > 0 && productCategoryId > 0) {
			try {
				// decouple connection between product & productCategory
				int effect = productDao.updateProductCategoryToNull(productCategoryId);
				if (effect < 0) {
					throw new ProductCategoryException("failed to update category to NULL");
				}
				// delete productCategory
				if (productCategoryDao.deleteProductCategory(productCategoryId, shopId) >= 1) {
					return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS, productCategoryDao.queryProductCategoryByShopId(shopId));
				} else {
					throw new ProductCategoryException("failed to delete product category");
				}			
			} catch (Exception e) {
				throw new ProductCategoryException("failed to delete product category, the errMsg is " + e.getMessage());
			}
		}
		return new ProductCategoryExecution(ProductCategoryStateEnum.NULL_SHOPID);
	}
}
