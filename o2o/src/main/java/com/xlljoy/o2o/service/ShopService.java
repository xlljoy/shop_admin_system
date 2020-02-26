package com.xlljoy.o2o.service;

import java.io.InputStream;

import com.xlljoy.o2o.dto.ShopExecution;
import com.xlljoy.o2o.entity.Shop;

public interface ShopService {
	//ShopExecution addShop(Shop shop, File img);
	ShopExecution addShop(Shop shop, InputStream imgInputStream, String fileName) throws Exception;
	ShopExecution updateShop(Shop shop);
}
