package com.xlljoy.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xlljoy.o2o.BaseTest;
import com.xlljoy.o2o.util.ImageUnit;

public class ProductImgServiceTest extends BaseTest{
	@Autowired
	private ProductImgService productImgService;
	
	@Test
	public void testAddProductImgs() {
		File img1 = new File("/home/jli/Pictures/pic1.jpg");
		File img2 = new File("/home/jli/Pictures/image1.jpg");
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
			int effect = productImgService.addProductImgs(imgList, 4L);
			System.out.println(effect);
			assertEquals(1, effect);
		} catch (Exception e) {
			throw new RuntimeException("errMsg: " + e.getMessage());
		}
		
	}
}
