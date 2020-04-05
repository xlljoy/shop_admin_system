package com.xlljoy.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.xlljoy.o2o.BaseTest;
import com.xlljoy.o2o.entity.ProductCategory;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductCategoryDaoTest extends BaseTest{
	@Autowired
	public ProductCategoryDao productCategoryDao;
	
	@Test
	public void testDInsertProductCategory() {
		ProductCategory pc = new ProductCategory();
		pc.setName("test1");
		pc.setCreateTime(new Date());
		pc.setPriority(2);
		pc.setShopId(4L);
		int effect = productCategoryDao.insertProductCategory(pc);
		assertEquals(1, effect);
	}
	@Test
	public void testBQueryProductCategoryByShopId() {
		List<ProductCategory> pcList = productCategoryDao.queryProductCategoryByShopId(4L);
		System.out.println(pcList.size());
	}
	
	@Test
	public void testCQueryProductCategory() {
		List<ProductCategory> pcList = productCategoryDao.queryProductCategory();
		System.out.println(pcList.size());
	}
	
	@Test
	public void testABatchInsertProductCategory() {
		ProductCategory pc1 = new ProductCategory();
		pc1.setName("test1");
		pc1.setCreateTime(new Date());
		pc1.setPriority(4);
		pc1.setShopId(4L);
		
		ProductCategory pc2 = new ProductCategory();
		pc2.setName("test2");
		pc2.setCreateTime(new Date());
		pc2.setPriority(1);
		pc2.setShopId(4L);
		
		List<ProductCategory> pcList = new ArrayList<ProductCategory>();
		pcList.add(pc1);
		pcList.add(pc2);
		int effect = productCategoryDao.batchInsertProductCategory(pcList);
		assertEquals(2,effect);
	}
	
	@Test
	public void testEDeleteProductCategory() {
		long shopId = 4L;
		List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryByShopId(shopId);
		for (ProductCategory pc : productCategoryList) {
			if ("test1".equals(pc.getName()) || "test2".equals(pc.getName())) {
				int effect = productCategoryDao.deleteProductCategory(pc.getId(), shopId);
				assertEquals(1,effect);
			}
		}
		
	}
}
