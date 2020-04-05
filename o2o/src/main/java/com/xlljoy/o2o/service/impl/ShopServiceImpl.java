package com.xlljoy.o2o.service.impl;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

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
import com.xlljoy.o2o.util.PageCalculator;
import com.xlljoy.o2o.util.PathUtil;

@Service
public class ShopServiceImpl implements ShopService {
	@Autowired
	private ShopDao shopDao;

	@Override
	@Transactional
	public ShopExecution addShop(Shop shop, InputStream imgInputStream, String fileName) {
		if (shop == null)
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		if (imgInputStream == null)
			return new ShopExecution(ShopStateEnum.NULL_IMG);
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
		} catch (ShopException e) {
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
	public ShopExecution modifyShop(Shop shop, InputStream imgInputStream, String fileName) 
			throws ShopException {
		if (shop == null || shop.getId() == null)
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		if (imgInputStream == null)
			return new ShopExecution(ShopStateEnum.NULL_IMG);
		
		// 1 delete imgs
		if (shop.getImg() != null && fileName != null) {
			ImageUtil.deleteFileOrPath(shop.getImg());
		}
		// 2 modify shop info

		try {
			shop.setEnableStatus(0);
			shop.setUpdateTime(new Date());

			// save img to shop
			shop = shopDao.queryByShopId(shop.getId());
			addImg(imgInputStream, fileName, shop);
			int effect = shopDao.updateShop(shop);
			if (effect < 1) {
				return new ShopExecution(ShopStateEnum.INNER_ERROR);
				
			}
		} catch (ShopException e) {
			throw new ShopException("modifiyShop error : " + e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.SUCCESS, shop);
	}

	@Override
	public Shop getByShopId(Long id) {
		Shop shop = shopDao.queryByShopId(id);
		return shop;
	}

	@Override
	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
		ShopExecution se = new ShopExecution();
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
		int count = shopDao.queryShopAmount(shopCondition);
		if (shopList != null) {
			se.setShopList(shopList);
			se.setCount(count);
			se.setState(ShopStateEnum.CHECK.getState());
		} else {
			se.setState(ShopStateEnum.INNER_ERROR.getState());
		}
		return se;
	}
}
