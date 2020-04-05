package com.xlljoy.o2o.service;

import java.util.List;

import com.xlljoy.o2o.entity.ProductImg;
import com.xlljoy.o2o.exceptions.ProductImgException;
import com.xlljoy.o2o.util.ImageUnit;

public interface ProductImgService {
	public int addProductImgs(List<ImageUnit> imgList, Long productId) throws ProductImgException;
	List<ProductImg> getProductImgList(Long productId);
	int deleteProductImg(String imgAddress);
}
