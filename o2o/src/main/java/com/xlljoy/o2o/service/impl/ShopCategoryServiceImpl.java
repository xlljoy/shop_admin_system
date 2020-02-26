package com.xlljoy.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xlljoy.o2o.dao.ShopCategoryDao;
import com.xlljoy.o2o.entity.ShopCategory;
import com.xlljoy.o2o.service.ShopCategoryService;

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService{

	@Autowired
	private ShopCategoryDao shopCategoryDao;
	@Override
	public List<ShopCategory> getShopCategory(ShopCategory shopCategoryCondition) {
		List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(shopCategoryCondition);
		return shopCategoryList;
	}
}
