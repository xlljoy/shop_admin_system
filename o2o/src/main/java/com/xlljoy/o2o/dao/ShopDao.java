package com.xlljoy.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xlljoy.o2o.entity.Shop;

public interface ShopDao {
	/*
	 * 
	 * search shops in different pages. the input could be: shop likely name, shop status, shop category, zoneId, owner info
	 * rowIndex: search from which line
	 * pageSize: return size
	 */
	List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition, 
			@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);
	
	// get the number of all shops
	int queryShopAmount(@Param("shopCondition") Shop shopCondition);
	Shop queryByShopId(Long id);
	int insertShop(Shop shop);
	int updateShop(Shop shop);
}
