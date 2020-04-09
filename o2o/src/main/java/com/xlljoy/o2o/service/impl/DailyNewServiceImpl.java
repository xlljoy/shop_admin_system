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
import com.xlljoy.o2o.dao.DailyNewDao;
import com.xlljoy.o2o.entity.DailyNew;
import com.xlljoy.o2o.service.DailyNewService;
@Service
public class DailyNewServiceImpl implements DailyNewService {
	@Autowired
	private DailyNewDao dailyNewDao;
	@Autowired
	private JedisUtil.Keys jedisKeys;
	@Autowired
	private JedisUtil.Strings jedisStrings;
	
	//private static String DAILYNEWLIST = "dailynewlist";
	@Override
	@Transactional
	public List<DailyNew> getDailyNewList(DailyNew dailyNew) {
		String key = RedisKeysEnum.DAILYNEWLIST.getValue();
		if ((dailyNew != null) && (dailyNew.getEnableStatus() != null)) {
			key = key + "_" + dailyNew.getEnableStatus();
		}
		List<DailyNew> dailynewList = null;
		String jsonString;
		ObjectMapper mapper = new ObjectMapper();
		if (!jedisKeys.exists(key)) {
			dailynewList = dailyNewDao.queryDailyNew(dailyNew);
			try {
				jsonString = mapper.writeValueAsString(dailynewList);
				jedisStrings.getSet(key, jsonString);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		} else {
			jsonString = jedisStrings.get(key);
			JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, DailyNew.class);
			try {
				dailynewList = mapper.readValue(jsonString, javaType);
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
		return dailynewList;
	}

}
