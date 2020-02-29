package com.xlljoy.o2o.dao;

import com.xlljoy.o2o.entity.Shop;

public interface ShopDao {
	Shop queryByShopId(Long id);
	int insertShop(Shop shop);
	int updateShop(Shop shop);
}
