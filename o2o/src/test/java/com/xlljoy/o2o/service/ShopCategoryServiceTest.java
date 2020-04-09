package com.xlljoy.o2o.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xlljoy.o2o.BaseTest;
import com.xlljoy.o2o.entity.ShopCategory;
import com.xlljoy.o2o.service.impl.RedisKeysEnum;

public class ShopCategoryServiceTest extends BaseTest {
	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private CacheService cacheService;
	@Test
	public void testGetShopCategory() {
		cacheService.removeFromCache(RedisKeysEnum.SHOPCATEGORYLIST.getValue());
		List<ShopCategory> shopCategoryList = shopCategoryService.getShopCategory(null);
		System.out.println(shopCategoryList.size());
	}
}
