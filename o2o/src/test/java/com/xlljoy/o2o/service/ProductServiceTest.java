package com.xlljoy.o2o.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xlljoy.o2o.BaseTest;
import com.xlljoy.o2o.dto.ProductExecution;
import com.xlljoy.o2o.entity.Product;
import com.xlljoy.o2o.entity.ProductCategory;
import com.xlljoy.o2o.entity.Shop;
import com.xlljoy.o2o.enums.ProductStateEnum;
import com.xlljoy.o2o.util.ImageUnit;

public class ProductServiceTest extends BaseTest {
	@Autowired
	private ProductService productService;
	
	@Test
	public void testAddProduct() {
		Product product = new Product();
		Shop shop = new Shop();
	
		shop.setId(32L);
		ProductCategory pc = new ProductCategory();
		pc.setId(2L);
		product.setShop(shop);
		product.setProductCategory(pc);
		product.setName("test1");
		product.setProductDesc("testtest1");
		product.setPriority(20);
		product.setPoint(100);
		product.setNormalPrice("15");
		product.setPromotionPrice("13");
		product.setCreateTime(new Date());
		product.setEnableStatus(ProductStateEnum.SUCCESS.getState());
		File img1 = new File("/home/jli/Pictures/pic1.jpg");
		File img2 = new File("/home/jli/Pictures/image1.jpg");
		File main_img = new File("/home/jli/Pictures/image8.png");
		InputStream is;
		ImageUnit iu;
		List<ImageUnit> imgList =  new ArrayList<ImageUnit>();
		try {
			is = new FileInputStream(img1);
			iu = new  ImageUnit(is, img1.getName());
			imgList.add(iu);
			is = new FileInputStream(img2);
			iu = new  ImageUnit(is, img2.getName());
			imgList.add(iu);
			InputStream mains = new FileInputStream(main_img);
			ImageUnit mainu = new  ImageUnit(mains, main_img.getName());
			ProductExecution pcE = productService.addProduct(product, mainu, imgList);
			System.out.println(pcE.getState());
			} catch (Exception e) {
			throw new RuntimeException("errMsg: " + e.getMessage());
		}
	}
	
	@Test
	public void testGetProductByShopId() {
		List<Product> productList = productService.getProductListByShopId(32L);
		System.out.print(productList.size());
	}
}
