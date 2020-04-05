package com.xlljoy.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xlljoy.o2o.dao.ZoneDao;
import com.xlljoy.o2o.entity.Zone;
import com.xlljoy.o2o.service.ZoneService;

@Service
public class ZoneServiceImpl implements ZoneService{

	@Autowired
	public ZoneDao zoneDao;
	
	@Override
	public List<Zone> getZoneList(){
		return zoneDao.queryZone();
	}
}
