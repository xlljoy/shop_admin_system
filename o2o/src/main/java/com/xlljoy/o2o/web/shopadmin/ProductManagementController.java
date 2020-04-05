package com.xlljoy.o2o.web.shopadmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xlljoy.o2o.dto.ProductExecution;
import com.xlljoy.o2o.dto.ShopExecution;
import com.xlljoy.o2o.entity.Product;
import com.xlljoy.o2o.entity.ProductCategory;
import com.xlljoy.o2o.entity.Shop;
import com.xlljoy.o2o.entity.User;
import com.xlljoy.o2o.entity.Zone;
import com.xlljoy.o2o.enums.ProductStateEnum;
import com.xlljoy.o2o.service.ProductCategoryService;
import com.xlljoy.o2o.service.ProductService;
import com.xlljoy.o2o.util.HttpServletRequestUtil;
import com.xlljoy.o2o.util.ImageUnit;
import com.xlljoy.o2o.util.VerifyCodeUtil;

@Controller
@RequestMapping("/shopadmin")
public class ProductManagementController {

	@Autowired
	private ProductService productService;
	@Autowired
	private ProductCategoryService productCategoryService;
	int SIDEIMAGECOUNTMAX = 6;
	
	@RequestMapping(value = "/getproductbyshopid", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getProductByShopId(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 1 , get shop and list all product category of this shop
		Shop shop;
		Product product;
		try {			
			long shopId = HttpServletRequestUtil.getLong(request, "shopId");
			if (shopId < 1) {
				shop = (Shop)request.getSession().getAttribute("currentShop");
				if (shop == null) {
					modelMap.put("success", false);
					modelMap.put("errMsg", "cannot get currentShop");
					return modelMap;
				}
				shopId = shop.getId();
			}
			// 2.  get product property, if not null, pass into front end
			product = (Product)request.getSession().getAttribute("currentProduct");
			if (product == null) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "cannot get currentProduct");
				return modelMap;
			}
			List<ProductCategory> productCategoryList = productCategoryService.getProductCategoryListByShopId(shopId);
			if (product != null) {
				modelMap.put("product", product);
				modelMap.put("productCategoryList", productCategoryList);
				modelMap.put("success", true);
			}

		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		
		return modelMap;
	}
	
	@RequestMapping(value="/getproductlist", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getProductList(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		User user = new User();
		user.setId(1L);
		user.setName("xlljoy");
		request.getSession().setAttribute("user", user);
		user = (User)request.getSession().getAttribute("user");
		modelMap.put("user", user);
		long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		Shop shop = new Shop();
		Object shopObject;
		if (shopId < 1) {
			shopObject = request.getSession().getAttribute("currentShop");
			if (shopObject == null) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "cannot get currentShop");
				return modelMap;
			}
			shop = (Shop) shopObject;
			shopId = shop.getId();
		}
		try {
			List<Product> productList = productService.getProductListByShopId(shopId);
			request.getSession().setAttribute("productList", productList);
			modelMap.put("productList", productList);
			modelMap.put("success", true);
		}catch(Exception e){
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}		
	return modelMap;
	}
	
	@RequestMapping(value = "/getproductinitinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getProductInitInfo(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
		long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		Shop shop = new Shop();
		Object shopObject;
		if (shopId < 1) {
			shopObject = request.getSession().getAttribute("currentShop");
			if (shopObject == null) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "cannot get currentShop");
				return modelMap;
			}
			shop = (Shop) shopObject;
			shopId = shop.getId();
		}
		try {
			productCategoryList = productCategoryService.getProductCategoryListByShopId(shopId);
			if (productCategoryList != null) {
				modelMap.put("success", true);
				modelMap.put("productCategoryList", productCategoryList);
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/getproductbyid", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getProductById(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
		long productId = HttpServletRequestUtil.getLong(request, "productId");
		
		try {
			if (productId > 0) {
				Product product = productService.getProductById(productId);
				if (product != null) {
					productCategoryList = 
							productCategoryService.getProductCategoryListByShopId(product.getShop().getId());
					if (productCategoryList != null) {
						modelMap.put("success", true);
						modelMap.put("productCategoryList", productCategoryList);
						modelMap.put("product", product);
					} else {
						modelMap.put("success", false);
						modelMap.put("errMsg", "failed to get productCategoryList");
					}
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", "failed to get product");
				}
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "invalid productId");
			}
			
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}
	@RequestMapping (value="/addproduct", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addProduct(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 1. check verify code 
		if (!VerifyCodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "please input right verify code");
			return modelMap;
		}
		// 2. get info from front end and transfer to product.class
		String productStr = HttpServletRequestUtil.getString(request, "productStr");
		ObjectMapper objectMapper = new ObjectMapper();
		Product product = null;
		try {
			product = objectMapper.readValue(productStr, Product.class);
		} catch (IOException e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		} 
		
		// 3 img processing
		MultipartHttpServletRequest  multipartHttpServletRequest;
		CommonsMultipartFile productImg = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (commonsMultipartResolver.isMultipart(request)) {
			multipartHttpServletRequest = (MultipartHttpServletRequest)request;
			productImg = (CommonsMultipartFile)multipartHttpServletRequest.getFile("mainImg");
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "shop img cannot be null");
			return modelMap;
		}
		
		ImageUnit mainImg = new ImageUnit();
		try {
			mainImg.setImage(productImg.getInputStream());
			mainImg.setName(productImg.getOriginalFilename());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<ImageUnit> sideImgList = new ArrayList<ImageUnit>();
		for (int i = 0;  i < SIDEIMAGECOUNTMAX; i++) {
			CommonsMultipartFile sideImg = (CommonsMultipartFile)multipartHttpServletRequest.getFile("sideImg" + i);
			if (sideImg != null) {
				ImageUnit sideimage = new ImageUnit();
				try {
					sideimage.setImage(sideImg.getInputStream());
					sideimage.setName(sideImg.getOriginalFilename());
					sideImgList.add(sideimage);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				break;
			}
		}
		
		// 2 register
		if (product == null || productImg == null || sideImgList.size() < 1) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "input shop information: productImg, detailImg or product");
			return modelMap;
		}
		
		ProductExecution pExecution;
		Shop currentShop = (Shop)request.getSession().getAttribute("currentShop");
		if (currentShop == null) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "shop info not available");
			return modelMap;
		}
		product.setShop(currentShop);
		try {
			pExecution = productService.addProduct(product, mainImg, sideImgList);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		if (pExecution.getState() == ProductStateEnum.SUCCESS.getState()) {				
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", pExecution.getStateInfo());
		}		
		// 3 return
		return modelMap;	
	}
	
	// 1 chekc whethe mainImg is uploaded, if so, clean up old imgs
	// 2 check side imgs are uploaded, if so, delete old imgs
	// 3 modify product
	@RequestMapping (value="/modifyproduct", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> modifyProduct(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		boolean statusChange = HttpServletRequestUtil.getBoolean(request, "statusChange");
		// 1. check verify code 
		if (!VerifyCodeUtil.checkVerifyCode(request) && statusChange != true) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "please input right verify code");
			return modelMap;
		}
		// 2. get info from front end and transfer to product.class
		String productStr = HttpServletRequestUtil.getString(request, "productStr");
		ObjectMapper objectMapper = new ObjectMapper();
		Product product = null;
		try {
			product = objectMapper.readValue(productStr, Product.class);
		} catch (IOException e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		} 
		
		// 3 img processing
		MultipartHttpServletRequest  multipartHttpServletRequest;
		CommonsMultipartFile productImg = null;
		ImageUnit mainImg = new ImageUnit();
		List<ImageUnit> sideImgList = new ArrayList<ImageUnit>();
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (commonsMultipartResolver.isMultipart(request)) {
			multipartHttpServletRequest = (MultipartHttpServletRequest)request;
			
			productImg = (CommonsMultipartFile)multipartHttpServletRequest.getFile("mainImg");
			if (productImg != null) {
				try {
					mainImg.setImage(productImg.getInputStream());
					mainImg.setName(productImg.getOriginalFilename());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			for (int i = 0;  i < SIDEIMAGECOUNTMAX; i++) {
				CommonsMultipartFile sideImg = (CommonsMultipartFile)multipartHttpServletRequest.getFile("sideImg" + i);
				if (sideImg != null) {
					ImageUnit sideimage = new ImageUnit();
					try {
						sideimage.setImage(sideImg.getInputStream());
						sideimage.setName(sideImg.getOriginalFilename());
						sideImgList.add(sideimage);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					break;
				}
			}
		}
		// 2 modify
		
		ProductExecution pExecution;
		Shop currentShop = (Shop)request.getSession().getAttribute("currentShop");
		if (currentShop == null) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "shop info not available");
			return modelMap;
		}
		product.setShop(currentShop);
		try {
			pExecution = productService.modifyProduct(product, mainImg, sideImgList);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		if (pExecution.getState() == ProductStateEnum.SUCCESS.getState()) {				
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", pExecution.getStateInfo());
		}		
		// 3 return
		return modelMap;		
	}
	
}
