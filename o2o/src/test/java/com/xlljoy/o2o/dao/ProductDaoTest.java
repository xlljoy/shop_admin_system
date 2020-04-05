package com.xlljoy.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xlljoy.o2o.BaseTest;
import com.xlljoy.o2o.entity.Product;
import com.xlljoy.o2o.entity.ProductCategory;
import com.xlljoy.o2o.entity.Shop;

public class ProductDaoTest extends BaseTest {

	@Autowired
	private ProductDao productDao;
	
	@Test
	public void testInsertProduct() {
		Product product = new Product();
		Shop shop = new Shop();
		shop.setId(32L);
		ProductCategory productCategory = new ProductCategory();
		productCategory.setId(7L);
		//product.setImgAddr("/home/jli/Pictures/image7.jpg");
		product.setEnableStatus(1);
		product.setName("beef");
		product.setPriority(200);
		product.setPoint(100);
		product.setCreateTime(new Date());
		product.setProductDesc("tasty tasty ");
		product.setUpdateTime(new Date());
		product.setNormalPrice("15");
		product.setProductCategory(productCategory);
		product.setShop(shop);
		product.setPromotionPrice("13");
		int effect = productDao.insertProduct(product);
		assertEquals(1, effect);
	}
	@Test
	public void testModifyProduct() {
		Product product = new Product();
		Shop shop = new Shop();
		shop.setId(32L);
		ProductCategory productCategory = new ProductCategory();
		productCategory.setId(2L);
		//product.setImgAddr("/home/jli/Pictures/image7.jpg");
		product.setEnableStatus(1);
		product.setName("modifiedProduct");
		product.setPriority(199);
		product.setPoint(98);
		product.setProductDesc("not really tasty tasty ");
		product.setUpdateTime(new Date());
		product.setNormalPrice("155");
		product.setProductCategory(productCategory);
		product.setShop(shop);
		product.setPromotionPrice("122");
		product.setId(3L);
		int effect = productDao.updateProduct(product);
		assertEquals(1, effect);
	}
	@Test
	public void TestintInsertProduct() {
		Product product = new Product();
		product.setId(8L);
		product.setImgAddr("/home/jli/Pictures/image7.jpg");
		int effect = productDao.updateProduct(product);
		assertEquals(1, effect);
	}
	@Test
	public void testQueryProductList() {
		List<Product> productList = productDao.queryProductList();
		System.out.println(productList.size());
	}
	
	@Test
	public void TestQueryProductById() {
		Product product = productDao.queryProductById(4L);
		System.out.println(product.getName());
	}
	@Test
	public void testQueryProductListByShopId() {
		List<Product> productList = productDao.queryProductListByShopId(32L);
		System.out.println(productList.size());
	}
	@Test
	public void testQueryProductListBySearch() {
		Product productCondition = new Product();
		productCondition.setEnableStatus(1);
		Shop shop = new Shop();
		shop.setId(32L);
		productCondition.setShop(shop);
		ProductCategory pc = new ProductCategory();
		pc.setId(2L);
		//productCondition.setProductCategory(pc);
		productCondition.setName("test");
		List<Product> productList = productDao.queryProductListBySearch(productCondition, 0, 3);
		int count = productDao.queryProductCount(productCondition);
		System.out.println(productList.size());
		System.out.println(count);
	}
	@Test
	public void testUpdateProductCategoryToNull() {
		int effect = productDao.updateProductCategoryToNull(4L);
		System.out.println(effect);
	}
}
