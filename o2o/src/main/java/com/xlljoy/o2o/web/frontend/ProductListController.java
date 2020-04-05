package com.xlljoy.o2o.web.frontend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xlljoy.o2o.dto.ProductExecution;
import com.xlljoy.o2o.entity.Product;
import com.xlljoy.o2o.entity.ProductCategory;
import com.xlljoy.o2o.entity.ProductImg;
import com.xlljoy.o2o.entity.Shop;
import com.xlljoy.o2o.service.ProductCategoryService;
import com.xlljoy.o2o.service.ProductImgService;
import com.xlljoy.o2o.service.ProductService;
import com.xlljoy.o2o.service.ShopService;
import com.xlljoy.o2o.util.HttpServletRequestUtil;
import com.xlljoy.o2o.util.ImageUnit;

@Controller
@RequestMapping("/frontend")
public class ProductListController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private ProductCategoryService productCategoryService;
	@Autowired
	private ProductImgService productImgService;
	@RequestMapping(value="/listproducts", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getListProducts(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		Shop currentShop = (Shop)request.getSession().getAttribute("currentShop");
		if ((pageIndex > -1) && (pageSize > -1) && (shopId > -1)) {
			long productCategoryId = HttpServletRequestUtil.getLong(request, "productCategoryId");
			String name = HttpServletRequestUtil.getString(request, "name");
			Product productCondition = new Product();
			if (name != null ) {
				productCondition.setName(name);
			}
			if (productCategoryId > -1) {
				ProductCategory productCategory = new ProductCategory();
				productCategory.setId(productCategoryId);
				productCondition.setProductCategory(productCategory);
			}
			Shop shop = new Shop();
			shop.setId(shopId);
			productCondition.setShop(shop);
			productCondition.setEnableStatus(1);
			ProductExecution productExecution = productService.getProductListBySearch(productCondition, pageIndex, pageSize);
			modelMap.put("productList", productExecution.getProductList());
			modelMap.put("count", productExecution.getCount());
			modelMap.put("success", true);
		} else {
			modelMap.put("errMsg", "input pageIndex and pageSize");
			modelMap.put("success", false);
		}
		return modelMap;
	}
	
	@RequestMapping(value="/getshopdetailinfo", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getShopDetail(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		if (shopId > -1) {
			Shop shop = shopService.getByShopId(shopId);
			String shopDesc = shop.getShopDesc();
			String shopAddr = shop.getAddr();
			String shopImg = shop.getImg();
			String phone = shop.getPhone();
			String name = shop.getName();
			request.getSession().setAttribute("currentShop", shop);
			List<ProductCategory> productCategoryList = productCategoryService.getProductCategoryListByShopId(shopId);
			modelMap.put("shopDesc", shopDesc);
			modelMap.put("shopAddr", shopAddr);
			modelMap.put("productCategoryList", productCategoryList);
			modelMap.put("shopImg", shopImg);
			modelMap.put("phone", phone);
			modelMap.put("name", name);
			modelMap.put("success", true);
		} else {
			modelMap.put("errMsg", "shopId is not valid");
			modelMap.put("success", false);
		}
		return modelMap;
	}
	
	@RequestMapping(value="/getproduct", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getProduct(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		long productId = HttpServletRequestUtil.getLong(request, "productId");
		if (productId > -1) {
			Product product = productService.getProductById(productId);
			if (product != null) {
				modelMap.put("product", product);
			} else {
				modelMap.put("errMsg", "cannot get product");
				modelMap.put("success", false);
				return modelMap;
			}
			List<ProductImg> productImgList = productImgService.getProductImgList(productId);
			if (productImgList != null) {
				modelMap.put("productImgList", productImgList);
			}
			modelMap.put("success", true);
		} else {
			modelMap.put("errMsg", "invalid productId");
			modelMap.put("success", false);
			return modelMap;
		}
		return modelMap;
	}
}
