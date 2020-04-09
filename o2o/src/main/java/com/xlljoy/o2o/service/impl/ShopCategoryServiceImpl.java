package com.xlljoy.o2o.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xlljoy.o2o.cache.JedisUtil;
import com.xlljoy.o2o.dao.ShopCategoryDao;
import com.xlljoy.o2o.entity.DailyNew;
import com.xlljoy.o2o.entity.ShopCategory;
import com.xlljoy.o2o.service.ShopCategoryService;

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService{

	@Autowired
	private ShopCategoryDao shopCategoryDao;
	@Autowired
	private JedisUtil.Keys jedisKeys;
	@Autowired
	private JedisUtil.Strings jedisStrings;
	
//	private static String SHOPCATEGORYLIST = "shopcategorylist";
	@Override
	@Transactional
	public List<ShopCategory> getShopCategory(ShopCategory shopCategoryCondition) {
		String key = RedisKeysEnum.SHOPCATEGORYLIST.getValue();
		if (shopCategoryCondition == null) {
			key = key + "_" + "allfirstlevel";
		} else if ((shopCategoryCondition != null) && (shopCategoryCondition.getParent() != null) &&
				(shopCategoryCondition.getParent().getId() != null)) {
			// all child category under this parent
			key = key + "_" + "parent_" + shopCategoryCondition.getParent().getId();
		} else if (shopCategoryCondition.getParent() != null) {
			// all second level shopCategory; like new owner could select one from to open a shop
			key = key + "_" + "allsecondlevel"; 
		}
		List<ShopCategory> shopCategoryList = null;
		String jsonString;
		ObjectMapper mapper = new ObjectMapper();
		if (!jedisKeys.exists(key)) {
			shopCategoryList = shopCategoryDao.queryShopCategory(shopCategoryCondition);
			try {
				jsonString = mapper.writeValueAsString(shopCategoryList);
				jedisStrings.getSet(key, jsonString);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		} else {
			jsonString = jedisStrings.get(key);
			JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, ShopCategory.class);
			try {
				shopCategoryList = mapper.readValue(jsonString, javaType);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return shopCategoryList;
	}
}
