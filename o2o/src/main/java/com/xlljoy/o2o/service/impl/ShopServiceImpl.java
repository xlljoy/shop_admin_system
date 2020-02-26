package com.xlljoy.o2o.service.impl;

import java.io.InputStream;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xlljoy.o2o.dao.ShopDao;
import com.xlljoy.o2o.dto.ShopExecution;
import com.xlljoy.o2o.entity.Shop;
import com.xlljoy.o2o.enums.ShopStateEnum;
import com.xlljoy.o2o.exceptions.ShopException;
import com.xlljoy.o2o.service.ShopService;
import com.xlljoy.o2o.util.ImageUtil;
import com.xlljoy.o2o.util.PathUtil;

@Service
public class ShopServiceImpl implements ShopService{
	@Autowired
	private ShopDao shopDao;
	
	@Override
	@Transactional
	public ShopExecution addShop(Shop shop, InputStream imgInputStream, String fileName) {
		if (shop == null) return new ShopExecution(ShopStateEnum.NULL_SHOP);
		if (imgInputStream == null) return new ShopExecution(ShopStateEnum.NULL_IMG);
		try {
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setUpdateTime(new Date());
			int effect = shopDao.insertShop(shop);
			if (effect < 1) {
				throw new ShopException("failed to add a new shop");
			} else {
				try {
					// save img to shop
					addImg(imgInputStream, fileName, shop);
				} catch (ShopException e) {
					throw new ShopException("addShop Img Error: " + e.getMessage());
				}
			}
			effect = shopDao.updateShop(shop);
			if (effect < 1) {
				throw new ShopException("failed to update shop img");
			}
		} catch(ShopException e) {
			throw new ShopException("addShop error : " + e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.CHECK, shop);
	}
	
	public void addImg(InputStream imgInputStream, String fileName, Shop shop) {
		Long shopId = shop.getId();
		String targetAddrString = PathUtil.getShopImgPath(shopId);
		String imgPath = ImageUtil.generateThumbnail(imgInputStream, fileName, targetAddrString);
		shop.setImg(imgPath);
	}

	@Override
	public ShopExecution updateShop(Shop shop) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
