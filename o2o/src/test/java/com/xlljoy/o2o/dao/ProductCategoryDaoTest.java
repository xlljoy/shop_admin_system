package com.xlljoy.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xlljoy.o2o.BaseTest;
import com.xlljoy.o2o.entity.ProductCategory;

public class ProductCategoryDaoTest extends BaseTest{
	@Autowired
	public ProductCategoryDao productCategoryDao;
	
	@Test
	public void testInsertProductCategory() {
		ProductCategory pc = new ProductCategory();
		pc.setName("soy milk");
		pc.setCreateTime(new Date());
		pc.setPriority(2);
		pc.setShopId(32L);
		int effect = productCategoryDao.insertProductCategory(pc);
		assertEquals(1, effect);
	}
	@Test
	public void testQueryProductCategoryByShopId() {
		List<ProductCategory> pcList = productCategoryDao.queryProductCategoryByShopId(32L);
		System.out.println(pcList.size());
	}
	
	@Test
	public void testQueryProductCategory() {
		List<ProductCategory> pcList = productCategoryDao.queryProductCategory();
		System.out.println(pcList.size());
	}
}
