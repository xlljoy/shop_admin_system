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
import com.xlljoy.o2o.dao.ZoneDao;
import com.xlljoy.o2o.entity.Zone;
import com.xlljoy.o2o.service.ZoneService;

@Service
public class ZoneServiceImpl implements ZoneService{

	@Autowired
	public ZoneDao zoneDao;
	@Autowired
	private JedisUtil.Keys jedisKeys;
	@Autowired
	private JedisUtil.Strings jedisStrings;
	
	//private static String ZONELIST = "zonelist";
	@Override
	@Transactional
	public List<Zone> getZoneList(){
		jedisKeys.flushAll();
		String key = RedisKeysEnum.ZONELIST.getValue();
		List<Zone> zoneList = null;
		ObjectMapper mapper = new ObjectMapper();
		if (!jedisKeys.exists(key)) {
			zoneList = zoneDao.queryZone();
			String jsonString;
			try {
				jsonString = mapper.writeValueAsString(zoneList);
				jedisStrings.set(key, jsonString);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		} else {
			String jsonString = jedisStrings.get(key);
			JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Zone.class);
			try {
				zoneList = mapper.readValue(jsonString, javaType);
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
		return zoneList;
	}
}
