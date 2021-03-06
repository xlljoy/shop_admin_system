package com.xlljoy.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xlljoy.o2o.BaseTest;
import com.xlljoy.o2o.entity.Zone;


public class ZoneDaoTest extends BaseTest{
	@Autowired
	public ZoneDao zoneDao;
	
	@Test
	public void queryZoneDaoTest() {
		List<Zone> zoneList = zoneDao.queryZone();
		assertEquals(2, zoneList.size());
	}
	
}
