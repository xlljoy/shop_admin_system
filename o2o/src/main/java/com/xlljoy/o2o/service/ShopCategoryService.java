package com.xlljoy.o2o.service;

import java.util.List;

import com.xlljoy.o2o.entity.ShopCategory;

public interface ShopCategoryService {
	List<ShopCategory> getShopCategory(ShopCategory shopCategoryCondition);
}
