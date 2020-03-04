package com.xlljoy.o2o.web.shopadmin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xlljoy.o2o.dto.Result;
import com.xlljoy.o2o.entity.ProductCategory;
import com.xlljoy.o2o.entity.Shop;
import com.xlljoy.o2o.enums.ProductCategoryStateEnum;
import com.xlljoy.o2o.service.ProductCategoryService;
import com.xlljoy.o2o.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/shopadmin")
public class ProductCategoryManagementController {
	@Autowired
	private ProductCategoryService productCategoryService; 
	
	@RequestMapping(value="/getproductcategorylist", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<ProductCategory>>getProductCategoryList(HttpServletRequest request) {
		long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		Shop shop = new Shop();
		ProductCategoryStateEnum ps;
		Object shopObject;
		if (shopId < 1) {
			shopObject = request.getSession().getAttribute("currentShop");
			if (shopObject == null) {
				ps =  ProductCategoryStateEnum.INNER_ERROR;
				return new Result<List<ProductCategory>>(false, ps.getState(),ps.getStateInfo());
			} 
			shop = (Shop)shopObject;
			shopId = shop.getId();
		}
		shop.setId(shopId);
		request.getSession().setAttribute("currentShop", shop);
		List<ProductCategory> productCategoryList= productCategoryService.getProductCategoryListByShopId(shopId);
		
		if (productCategoryList.size() > 0) {
			return new Result<List<ProductCategory>>(true, productCategoryList);
		}
		ps =  ProductCategoryStateEnum.NULL_PRODUCT_CATEGORY;
		return new Result<List<ProductCategory>>(false, ps.getState(),ps.getStateInfo());
	}
	
//	Map<String, Object> modelMap = new HashMap<String, Object>();
//	long shopId = HttpServletRequestUtil.getLong(request, "shopId");
//	Shop shop;
//	if (shopId < 1) {
//		Object shopObject = request.getSession().getAttribute("currentShop");
//		if (shopObject == null) {
//			modelMap.put("redirect", true);
//			modelMap.put("url", "/shopadmin/shoplist");
//		} else {
//			shop = (Shop)shopObject;
//			shopId = shop.getId();
//			modelMap.put("redirect", false);
//		}
//	}
//	List<ProductCategory> pcList= productCategoryService.getProductCategoryListByShopId(shopId);
//	if (pcList.size() > 0) {
//		modelMap.put("productCategoryList", pcList);
//		modelMap.put("success", true);
//	} else {
//		modelMap.put("success", false);
//		modelMap.put("errMsg", "cannot get prodoct category list of shopId = " + shopId);
//	}
//	return modelMap;
}
