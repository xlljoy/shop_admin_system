package com.xlljoy.o2o.service;

import java.io.InputStream;

import com.xlljoy.o2o.dto.ShopExecution;
import com.xlljoy.o2o.entity.Shop;
import com.xlljoy.o2o.exceptions.ShopException;

public interface ShopService {
	Shop getByShopId(Long id);
	//ShopExecution addShop(Shop shop, File img);
	ShopExecution addShop(Shop shop, InputStream imgInputStream, String fileName) throws ShopException;
	ShopExecution modifyShop(Shop shop, InputStream imgInputStream, String fileName) throws ShopException;
	ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);
}
