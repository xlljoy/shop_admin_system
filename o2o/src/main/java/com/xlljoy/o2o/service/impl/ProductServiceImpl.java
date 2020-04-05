package com.xlljoy.o2o.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xlljoy.o2o.dao.ProductDao;
import com.xlljoy.o2o.dto.ProductExecution;
import com.xlljoy.o2o.entity.Product;
import com.xlljoy.o2o.entity.ProductImg;
import com.xlljoy.o2o.enums.ProductStateEnum;
import com.xlljoy.o2o.enums.ShopStateEnum;
import com.xlljoy.o2o.exceptions.ProductException;
import com.xlljoy.o2o.service.ProductImgService;
import com.xlljoy.o2o.service.ProductService;
import com.xlljoy.o2o.util.ImageUnit;
import com.xlljoy.o2o.util.ImageUtil;
import com.xlljoy.o2o.util.PageCalculator;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductImgService productImgService;
	
	@Override
	@Transactional
	// 1. side pictures processing, and produce ralative path for each picture
	// 2. write info into product
	// 3. get productId and process side imgs
	// 4. add all side imgs into tb_product_img
	public ProductExecution addProduct(Product product, ImageUnit mainImg, List<ImageUnit> sideImgList) throws ProductException {
		ProductExecution productExecution = new ProductExecution(ProductStateEnum.SUCCESS);
		// check product is not null && product has shopId
		if (product != null) {
			try {
				long shopId = product.getShop().getId();
				if (shopId < 1) {
					return new ProductExecution(ProductStateEnum.NULL_SHOPID);
				}
		// write info into product
				product.setCreateTime(new Date());
				product.setUpdateTime(new Date());
				product.setEnableStatus(1);
				int effect = productDao.insertProduct(product);
				if (effect < 1) {
					return new ProductExecution(ProductStateEnum.INNER_ERROR);
				}
				Long productId = product.getId();
		// main img processing
				if (mainImg != null) {
					String imgPath = ImageUtil.addImgForProduct(mainImg, shopId, productId);
					product.setImgAddr(imgPath);
				}
				effect = productDao.updateProduct(product);
				if (effect < 1) {
					return new ProductExecution(ProductStateEnum.INNER_ERROR);
				}
				
		// side img list processing
				if (sideImgList != null && sideImgList.size() > 0) {
					effect = productImgService.addProductImgs(sideImgList, productId);
					if (effect != 1) {
						return new ProductExecution(ProductStateEnum.NULL_PRODUCR_IMG);
					}
				}
				productExecution.setProduct(product);
			} catch (Exception e) {
				throw new ProductException("failed to add product " + e.getMessage() );
			}
			
		}
		return productExecution;
	}
	@Override
	public Product getProductById(Long productId) {
		return productDao.queryProductById(productId);
	}
	@Override
	public List<Product> getProductListByShopId(long shopId) {
		if (shopId > 0) {
			return productDao.queryProductListByShopId(shopId);
		}
		return null;
	}
	@Override
	@Transactional
	public ProductExecution modifyProduct(Product product, ImageUnit mainImg, List<ImageUnit> sideImgList)
			throws ProductException {
		ProductExecution productExecution = new ProductExecution(ProductStateEnum.SUCCESS);
		// check product is not null && product has shopId
		if (product != null && product.getShop() != null) {
			try {
				long shopId = product.getShop().getId();
				if (shopId < 1) {
					return new ProductExecution(ProductStateEnum.NULL_SHOPID);
				}
				Long productId = product.getId();
		// main img processing
				if (mainImg != null && mainImg.getName() != null && mainImg.getImage() != null) {
					if (productDao.queryProductById(productId).getImgAddr() != null) {
						ImageUtil.deleteFileOrPath(productDao.queryProductById(productId).getImgAddr());
					}
					
					String imgPath = ImageUtil.addImgForProduct(mainImg, shopId, productId);
					product.setImgAddr(imgPath);
				}

		// write info into product
				product.setUpdateTime(new Date());
				int effect = productDao.updateProduct(product);
				if (effect < 1) {
					return new ProductExecution(ProductStateEnum.INNER_ERROR);
				}
				
		// side img list processing
				if (sideImgList != null && sideImgList.size() > 0) {
					List<ProductImg> productImgList = productImgService.getProductImgList(productId);
					if (productImgList != null && productImgList.size() > 0) {
						for (ProductImg img : productImgList) {
							String imgAddress = img.getImgAddr();
							if (imgAddress != null) {
								ImageUtil.deleteFileOrPath(imgAddress);
								if (productImgService.deleteProductImg(imgAddress) < 1) {
									throw new ProductException("failed to delete product detail image");
								}
							}
						}
					}
					effect = productImgService.addProductImgs(sideImgList, productId);
					if (effect < 1) {
						return new ProductExecution(ProductStateEnum.NULL_PRODUCR_IMG);
					}
				}
				productExecution.setProduct(product);
			} catch (Exception e) {
				throw new ProductException("failed to add product " + e.getMessage() );
			}
			
		}
		return productExecution;
	}
	@Override
	public ProductExecution getProductListBySearch(Product productCondition, int pageIndex, int pageSize) {
		ProductExecution pe = new ProductExecution();
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Product> productList = productDao.queryProductListBySearch(productCondition, rowIndex, pageSize);
		int count = productDao.queryProductCount(productCondition);
		if (productList != null) {
			pe.setCount(count);
			pe.setProductList(productList);
			pe.setState(ProductStateEnum.SUCCESS.getState());
		} else {
			pe.setState(ProductStateEnum.NULL_LIST.getState());
		}
		return pe;
	}
}
