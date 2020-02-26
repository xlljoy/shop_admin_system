package com.xlljoy.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xlljoy.o2o.BaseTest;
import com.xlljoy.o2o.dto.ShopExecution;
import com.xlljoy.o2o.entity.Shop;
import com.xlljoy.o2o.entity.ShopCategory;
import com.xlljoy.o2o.entity.User;
import com.xlljoy.o2o.entity.Zone;
import com.xlljoy.o2o.enums.ShopStateEnum;

public class ShopServiceTest extends BaseTest{
	@Autowired
	private ShopService shopService;
	
	@Test
	public void testAddShop() {
		Shop shop = new Shop();
		shop.setAddr("lalalla");
		shop.setName("rainbow drink");
		
		User owner = new User();
		owner.setId(1L);
		shop.setOwner(owner);
		
		Zone zone = new Zone();
		zone.setZoneId(2);
		shop.setZone(zone);
		
		ShopCategory shopCategory = new ShopCategory();
		shopCategory.setId(1L);
		shop.setShopCategory(shopCategory);
		
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setShopDesc("it is a good one");
		shop.setPhone("123456");
		shop.setImg("a store pic");
		shop.setCreateTime(new Date());
		shop.setUpdateTime(new Date());
		shop.setAdvice("checking");
		File img = new File("/home/jli/Pictures/pic1.jpg");
		InputStream is;
		ShopExecution shopExecution;
		try {
			is = new FileInputStream(img);
			shopExecution = shopService.addShop(shop, is, img.getName());
		} catch (Exception e) {
			throw new RuntimeException("errMsg: " + e.getMessage());
		}
		assertEquals(0, shopExecution.getState());
	}
	
}
