package com.xlljoy.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import com.xlljoy.o2o.exceptions.ShopException;

public class ShopServiceTest extends BaseTest{
	@Autowired
	private ShopService shopService;
	
	@Test
	public void testGetShopList() {
		Shop shopCondition = new Shop();
		User owner = new User();
		owner.setId(1L);
		shopCondition.setOwner(owner);
		int pageIndex = 3;
		int pageSize = 5;
		ShopExecution se = shopService.getShopList(shopCondition, pageIndex, pageSize);
		System.out.println(se.getCount());
		System.out.println(se.getShopList().size());
	}
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
	
	@Test
	public void testModifyShop() throws ShopException, FileNotFoundException{
		Shop shop = shopService.getByShopId(6L);
		shop.setName("modified shop");
		File img = new File("/home/jli/Pictures/image3.jpg");
		InputStream is;
		ShopExecution shopExecution;
		try {
			is = new FileInputStream(img);
			shopExecution = shopService.modifyShop(shop, is, img.getName());
		} catch (Exception e) {
			throw new RuntimeException("errMsg: " + e.getMessage());
		}
		System.out.println(shopExecution.getShop().getImg());
	}
}
