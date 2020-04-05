package com.xlljoy.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xlljoy.o2o.BaseTest;
import com.xlljoy.o2o.entity.Shop;
import com.xlljoy.o2o.entity.ShopCategory;
import com.xlljoy.o2o.entity.User;
import com.xlljoy.o2o.entity.Zone;

public class ShopDaoTest extends BaseTest{
	@Autowired
	private ShopDao shopDao;
	
	@Test
	public void testQueryShopList() {
		Shop shop = new Shop();
		User owner = new User();
		owner.setId(1L);
		shop.setOwner(owner);
		//shop.setEnableStatus(0);
		ShopCategory sc = new ShopCategory();
		sc.setId(2L);
		shop.setShopCategory(sc);
		List<Shop> shopList = shopDao.queryShopList(shop, 0, 1);
		int count  = shopDao.queryShopAmount(shop);
		System.out.println(shopList.size());
		System.out.println(count);
	}
	
	@Test
	public void testQueryShopAmount() {
		Shop shop = new Shop();
		shop.setEnableStatus(0);
		int count = shopDao.queryShopAmount(shop);
		System.out.println(count);
	}
	@Test
	public void testQueryShopListAndAmount() {
		Shop shop = new Shop();
		ShopCategory sc_parent = new ShopCategory();
		sc_parent.setId(4L);
		ShopCategory sc_child = new ShopCategory();
		sc_child.setParent(sc_parent);
		shop.setShopCategory(sc_child);
		//shop.setShopCategory(new ShopCategory());
		List<Shop> shopList = shopDao.queryShopList(shop, 6, 3);
		int count = shopDao.queryShopAmount(shop);
		System.out.println(shopList.size());
		System.out.println(count);
	}
	@Test
	public void testQueryByShopId() {
		Shop shop = shopDao.queryByShopId(4L);
		System.out.println(shop.getName());
		System.out.println(shop.getZone().getName());
		System.out.println(shop.getZone().getZoneId());
	}
	@Test
	public void testInsertShop() {
		Shop shop = new Shop();
		shop.setAddr("lalalla");
		shop.setName("milkeTea");
		
		User owner = new User();
		owner.setId(1L);
		shop.setOwner(owner);
		
		Zone zone = new Zone();
		zone.setZoneId(2);
		shop.setZone(zone);
		
		ShopCategory shopCategory = new ShopCategory();
		shopCategory.setId(1L);
		shop.setShopCategory(shopCategory);
		
		shop.setEnableStatus(1);
		shop.setShopDesc("it is a good one");
		shop.setPhone("123456");
		shop.setImg("a store pic");
		shop.setCreateTime(new Date());
		shop.setAdvice("checking");
		int effect = shopDao.insertShop(shop);
		assertEquals(1, effect);
	}
	
	@Test
	public void testUpdateShop() {
		Shop shop = new Shop();
		shop.setId(5L);
		shop.setAddr("montreal");
		shop.setName("the aly");
		
		User owner = new User();
		owner.setId(1L);
		shop.setOwner(owner);
		
		Zone zone = new Zone();
		zone.setZoneId(2);
		shop.setZone(zone);
		
		ShopCategory shopCategory = new ShopCategory();
		shopCategory.setId(1L);
		shop.setShopCategory(shopCategory);
		
		shop.setEnableStatus(1);
		shop.setShopDesc("it is a good one");
		shop.setPhone("1234567");
		shop.setImg("a store pic");
		shop.setCreateTime(new Date());
		shop.setAdvice("checking");
		shop.setUpdateTime(new Date());
		int effect = shopDao.updateShop(shop);
		assertEquals(1, effect);
	}
}
