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

import com.xlljoy.o2o.dto.ShopExecution;
import com.xlljoy.o2o.entity.Shop;
import com.xlljoy.o2o.entity.ShopCategory;
import com.xlljoy.o2o.entity.Zone;
import com.xlljoy.o2o.service.ShopCategoryService;
import com.xlljoy.o2o.service.ShopService;
import com.xlljoy.o2o.service.ZoneService;
import com.xlljoy.o2o.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/frontend")
public class ShopListController {
	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private ZoneService zoneService;
	@Autowired
	private ShopService shopService;
	// return second class or first class shopCategoryList and zones
	@RequestMapping(value="/listshopspageinfo", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listShopsPageInfo(HttpServletRequest request){
		long parentId = HttpServletRequestUtil.getLong(request, "parentId");
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<ShopCategory> shopCategoryList = null;
		if (parentId != -1) {
			try {
				ShopCategory parent = new ShopCategory();
				parent.setId(parentId);
				ShopCategory shopCategoryCondition = new ShopCategory();
				shopCategoryCondition.setParent(parent);
				shopCategoryList = shopCategoryService.getShopCategory(shopCategoryCondition);

			} catch (Exception e) {
				modelMap.put("errMsg", e.getMessage());
				modelMap.put("success", false);
			}
		} else {
			try {
				shopCategoryList = shopCategoryService.getShopCategory(null);
			} catch (Exception e) {
				modelMap.put("errMsg", e.getMessage());
				modelMap.put("success", false);
			}
		}
		if (shopCategoryList != null) {
			modelMap.put("shopCategoryList", shopCategoryList);
		}
		
		List<Zone> zoneList = null;
		try {
			zoneList = zoneService.getZoneList();
			if (zoneList != null) {
				modelMap.put("zoneList", zoneList);
			}
		} catch (Exception e) {
			modelMap.put("errMsg", e.getMessage());
			modelMap.put("success", false);
		}
		modelMap.put("success", true);
		return modelMap;
	}
	
	@RequestMapping(value="/listshops", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listShops(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		if ((pageIndex > -1) && (pageSize > -1)) {
			long parentId = HttpServletRequestUtil.getLong(request, "parentId");
			long shopCategoryId = HttpServletRequestUtil.getLong(request, "shopCategoryId");
			String name = HttpServletRequestUtil.getString(request, "name");
			int zoneId = HttpServletRequestUtil.getInt(request, "zoneId");
			Shop shopCondition = generateShopCondition4Search(parentId, shopCategoryId, name, zoneId);
			ShopExecution shopExecution = shopService.getShopList(shopCondition, pageIndex, pageSize);
			modelMap.put("shopList", shopExecution.getShopList());
			modelMap.put("count", shopExecution.getCount());
			modelMap.put("success", true);
		} else {
			modelMap.put("errMsg", "input pageIndex and pageSize");
			modelMap.put("success", false);
		}
		return modelMap;
	}
	
	private Shop generateShopCondition4Search(long parentId, long shopCategoryId, String name, int zoneId) {
		Shop shopCondition = new Shop();
		
		
		if (parentId > -1) {
			ShopCategory shopCategoryParent = new ShopCategory();
			ShopCategory shopCategoryCondition = new ShopCategory();
			shopCategoryParent.setId(parentId);
			shopCategoryCondition.setParent(shopCategoryParent);
			shopCondition.setShopCategory(shopCategoryCondition);
		}
		if (shopCategoryId > -1) {
			ShopCategory shopCategoryCondition = new ShopCategory();
			shopCategoryCondition.setId(shopCategoryId);
			shopCondition.setShopCategory(shopCategoryCondition);
		}
		if (name != null) {
			shopCondition.setName(name);
		}
		if (zoneId > -1) {
			Zone zone = new Zone();
			zone.setZoneId(zoneId);
			shopCondition.setZone(zone);
		}
		shopCondition.setEnableStatus(1);
		return shopCondition;
	}
}
