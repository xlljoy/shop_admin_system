package com.xlljoy.o2o.service;

import java.util.List;

import com.xlljoy.o2o.dto.ProductExecution;
import com.xlljoy.o2o.entity.Product;
import com.xlljoy.o2o.exceptions.ProductException;
import com.xlljoy.o2o.util.ImageUnit;

public interface ProductService {
	ProductExecution addProduct(Product product, ImageUnit mainImg, List<ImageUnit> sideImgList) throws ProductException;
	ProductExecution modifyProduct(Product product, ImageUnit mainImg, List<ImageUnit> sideImgList) throws ProductException;
	Product getProductById(Long productId);
	List<Product> getProductListByShopId(long shopId);
	ProductExecution getProductListBySearch(Product productCondition, int pageIndex, int pageSize);
}
