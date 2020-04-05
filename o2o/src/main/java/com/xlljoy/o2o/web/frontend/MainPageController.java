package com.xlljoy.o2o.web.frontend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xlljoy.o2o.entity.DailyNew;
import com.xlljoy.o2o.entity.ShopCategory;
import com.xlljoy.o2o.service.DailyNewService;
import com.xlljoy.o2o.service.ShopCategoryService;

@Controller
@RequestMapping("/frontend")
public class MainPageController {
	@Autowired
	private DailyNewService dailyNewService;
	@Autowired
	private ShopCategoryService shopCategoryService;
	@RequestMapping(value="/getmainpageinfo", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getMainPageInfo(){
		List<DailyNew> dailyNewList = new ArrayList<DailyNew>();
		List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			DailyNew dailyNew = new DailyNew();
			dailyNew.setEnableStatus(1);
			dailyNewList = dailyNewService.getDailyNewList(dailyNew);
			shopCategoryList = shopCategoryService.getShopCategory(null);
			modelMap.put("dailyNewList", dailyNewList);
			modelMap.put("shopCategoryList", shopCategoryList);
		} catch(Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		modelMap.put("success", true);
		return modelMap;
	}
}
