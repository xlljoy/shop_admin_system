package com.xlljoy.o2o.web.shopadmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xlljoy.o2o.dto.ShopExecution;
import com.xlljoy.o2o.entity.Shop;
import com.xlljoy.o2o.entity.ShopCategory;
import com.xlljoy.o2o.entity.User;
import com.xlljoy.o2o.entity.Zone;
import com.xlljoy.o2o.enums.ShopStateEnum;
import com.xlljoy.o2o.service.ShopCategoryService;
import com.xlljoy.o2o.service.ShopService;
import com.xlljoy.o2o.service.ZoneService;
import com.xlljoy.o2o.util.HttpServletRequestUtil;
import com.xlljoy.o2o.util.VerifyCodeUtil;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
	
	@Autowired
	private ShopService shopService;
	@Autowired
	private ZoneService zoneService;
	@Autowired
	private ShopCategoryService shopCategoryService;
	
	@RequestMapping(value="/getshopmanagementinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopManagementInfo(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		Shop shop;
		if (shopId < 1) {
			Object shopObject = request.getSession().getAttribute("currentShop");
			if (shopObject == null) {
				modelMap.put("redirect", true);
				modelMap.put("url", "/shopadmin/shoplist");
			} else {
				shop = (Shop)shopObject;
				modelMap.put("redirect", false);
				modelMap.put("shopId", shop.getId());
			}
		} else {
			shop = new Shop();
			shop.setId(shopId);
			request.getSession().setAttribute("currentShop", shop);
			modelMap.put("success", false);
		}
	return modelMap;
	}
	
	@RequestMapping(value="/getshoplist", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopList(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		User user = new User();
		user.setId(1L);
		user.setName("xlljoy");
		request.getSession().setAttribute("user", user);
		user = (User)request.getSession().getAttribute("user");
		modelMap.put("user", user);
		try {
			Shop shopCondition = new Shop();
			shopCondition.setOwner(user);
			ShopExecution se = shopService.getShopList(shopCondition, 1, 100);
			if (se.getState() == 0) {
				request.getSession().setAttribute("shopList", se.getShopList());
				modelMap.put("shopList", se.getShopList());
				modelMap.put("success", true);
			}
		}catch(Exception e){
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}		
	return modelMap;
	}
	@RequestMapping(value="/modifyshop", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> modifyShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// check verify code 
		if (!VerifyCodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "please input right verify code");
			return modelMap;
		}
		// 1 get info from front end and transfer to shop.class
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		ObjectMapper objectMapper = new ObjectMapper();
		Shop shop = null;
		try {
			shop = objectMapper.readValue(shopStr, Shop.class);
		} catch (IOException e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		} 
		
		// 1.1 img processing
		CommonsMultipartFile shopImg = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest  multipartHttpServletRequest = (MultipartHttpServletRequest)request;
			shopImg = (CommonsMultipartFile)multipartHttpServletRequest.getFile("shopImg");
		}
		
		// 2 modify shop
		
		if (shop == null && shop.getId() > -1) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "input shop information");
			return modelMap;
		}
		
		ShopExecution sExecution;
		try {
			if (shopImg == null) {
				//if no image uploaded, just modify shop withouth img
				sExecution = shopService.modifyShop(shop, null, null);
			}
			sExecution = shopService.modifyShop(shop, shopImg.getInputStream(), shopImg.getOriginalFilename());
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		if (sExecution.getState() == ShopStateEnum.SUCCESS.getState()) {
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "please input valid shop id");
		}		
		// 3 return
		return modelMap;
	}
	@RequestMapping(value = "/getshopbyid", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopById(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		if (shopId > -1) {
			try {
				Shop shop = shopService.getByShopId(shopId);
				if (shop != null) {
					List<Zone> zoneList = zoneService.getZoneList();
					modelMap.put("shop", shop);
					modelMap.put("zoneList", zoneList);
					modelMap.put("success", true);

				}
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
		}
		else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "shop id is invalid");
		}
		return modelMap;
	}
	@RequestMapping(value = "/getshopinitinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopInitInfo() {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<Zone> zoneList = new ArrayList<Zone>();
		List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
		try {
			zoneList = zoneService.getZoneList();
			shopCategoryList = shopCategoryService.getShopCategory(new ShopCategory());
			modelMap.put("success", true);
			modelMap.put("zoneList", zoneList);
			modelMap.put("shopCategoryList", shopCategoryList);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}
	
	@RequestMapping(value="/registershop", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> addShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// check verify code 
		if (!VerifyCodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "please input right verify code");
			return modelMap;
		}
		// 1 get info from front end and transfer to shop.class
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		ObjectMapper objectMapper = new ObjectMapper();
		Shop shop = null;
		try {
			shop = objectMapper.readValue(shopStr, Shop.class);
		} catch (IOException e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		} 
		
		// 1.1 img processing
		CommonsMultipartFile shopImg = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest  multipartHttpServletRequest = (MultipartHttpServletRequest)request;
			shopImg = (CommonsMultipartFile)multipartHttpServletRequest.getFile("shopImg");
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "shop img cannot be null");
			return modelMap;
		}
		
		// 2 register
		
		if (shop == null || shopImg == null) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "input shop information");
			return modelMap;
		}
		User owner = (User) request.getSession().getAttribute("user");
		shop.setOwner(owner);
		
		ShopExecution sExecution;
		try {
			sExecution = shopService.addShop(shop, shopImg.getInputStream(), shopImg.getOriginalFilename());
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		if (sExecution.getState() == ShopStateEnum.CHECK.getState()) {
			@SuppressWarnings("unchecked")
			List<Shop> shopList = (List<Shop>)request.getSession().getAttribute("shopList");
			if (shopList.size() == 0) {
				shopList = new ArrayList<Shop>();
			}
			shopList.add(sExecution.getShop());
			request.getSession().setAttribute("shopList", shopList);				
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", sExecution.getStateInfo());
		}		
		// 3 return
		return modelMap;
	}
}
