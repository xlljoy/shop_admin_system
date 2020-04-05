package com.xlljoy.o2o.dao;

import java.util.List;

import com.xlljoy.o2o.entity.ProductImg;

public interface ProductImgDao {
	
	// batch add product imgs
	int batchInsertProductImg(List<ProductImg> productImgList);
	int deleteProductImg(String imgAddress);
	int deleteProductImgByProductId(long productId);
	List<ProductImg> queryProductImgByProductId(long productId);
}
