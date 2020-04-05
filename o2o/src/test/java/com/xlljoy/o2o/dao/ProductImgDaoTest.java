package com.xlljoy.o2o.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.xlljoy.o2o.BaseTest;
import com.xlljoy.o2o.entity.Product;
import com.xlljoy.o2o.entity.ProductImg;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductImgDaoTest extends BaseTest {
	@Autowired
	private ProductImgDao productImgDao;
	
	@Test
	public void testAInsertProductImg() {
		ProductImg productImg = new ProductImg();
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		Product product = new Product();
		product.setId(1L);
		productImg.setCreateTime(new Date());
		productImg.setImgAddr("/Home/Pictures/newnew.jpg");
		productImg.setImgDesc("milktea");
		productImg.setPriority(6);
		productImg.setProductId(product.getId());
		productImgList.add(productImg);
		
		ProductImg productImg1 = new ProductImg();
		productImg1.setCreateTime(new Date());
		productImg1.setImgAddr("/Home/Pictures/pic1.jpg");
		productImg1.setImgDesc("smile face");
		productImg1.setPriority(7);
		productImg1.setProductId(product.getId());
		productImgList.add(productImg1);
		
		int effect = productImgDao.batchInsertProductImg(productImgList);
		System.out.println(effect);
	}
	
	@Test
	public void testBDeleteProductImg() {
		int effect = productImgDao.deleteProductImg("upload/item/shop/32/product/20/2020032317561910003.jpg");
		System.out.println(effect);
	}
	@Test
	public void testBDeleteProductImgByProductId() {
		int effect = productImgDao.deleteProductImgByProductId(17L);
		System.out.println(effect);
	}
	@Test
	public void testQueryProductImgByProductId() {
		List<ProductImg> productImgList = productImgDao.queryProductImgByProductId(4L);
		System.out.println(productImgList.size());
	}
}
