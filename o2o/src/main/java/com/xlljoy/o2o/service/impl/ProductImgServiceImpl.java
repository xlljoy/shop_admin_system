package com.xlljoy.o2o.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xlljoy.o2o.dao.ProductDao;
import com.xlljoy.o2o.dao.ProductImgDao;
import com.xlljoy.o2o.entity.Product;
import com.xlljoy.o2o.entity.ProductImg;
import com.xlljoy.o2o.exceptions.ProductImgException;
import com.xlljoy.o2o.service.ProductImgService;
import com.xlljoy.o2o.util.ImageUnit;
import com.xlljoy.o2o.util.ImageUtil;
import com.xlljoy.o2o.util.PathUtil;

@Service
public class ProductImgServiceImpl implements ProductImgService {

	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductImgDao productImgDao;
	@Override
	@Transactional
	public int addProductImgs(List<ImageUnit> imgList, Long productId) throws ProductImgException {
		if (productId > 0) {
			Product product = productDao.queryProductById(productId);
			if (product == null) {
				throw new ProductImgException("failed to get product by id");
			}
			Long shopId = product.getShop().getId();
			if (shopId < 1) {
				throw new ProductImgException("failed to get shop from product with productId = " + productId);
			}
			try {
				List<ProductImg> productImgList = new ArrayList<ProductImg>();
				for (ImageUnit img : imgList) {
					ProductImg productImg = new ProductImg();
					productImg.setCreateTime(new Date());
					productImg.setImgAddr(addImg(img.getImage(), img.getName(), shopId, productId));
					productImg.setProductId(productId);
					productImgList.add(productImg);
				}
				if (productImgList.size() > 0) {
					int effect = productImgDao.batchInsertProductImg(productImgList);
					if (effect < 0) {
						throw new ProductImgException("failed to add product images for product with productId = " + productId);
					}
				}
			} catch (Exception e) {
				throw new ProductImgException("failed to add product images for product with productId = " + productId);
			}
		}
		return 1;
	}
	
	public String addImg(InputStream imgInputStream, String fileName, long shopId, long productId) {
		String targetAddrString = PathUtil.getProductImgPath(productId, shopId);
		String imgPath = ImageUtil.generateThumbnail(imgInputStream, fileName, targetAddrString);
		return imgPath;
	}

	@Override
	public List<ProductImg> getProductImgList(Long productId) {
		if (productId > 0) {
			return productImgDao.queryProductImgByProductId(productId);
		}
		return null;
	}

	@Override
	public int deleteProductImg(String imgAddress) {
		return productImgDao.deleteProductImg(imgAddress);
	}

}
