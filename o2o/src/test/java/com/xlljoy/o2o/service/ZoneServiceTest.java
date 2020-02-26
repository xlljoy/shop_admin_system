package com.xlljoy.o2o.service;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xlljoy.o2o.BaseTest;
import com.xlljoy.o2o.entity.Zone;

public class ZoneServiceTest extends BaseTest{
	@Autowired
	public ZoneService zoneService;
	
	@Test
	public void testGetZoneList() {
		List<Zone>  zoneList = zoneService.getZoneList();
		assertEquals(2, zoneList.size());
		assertEquals("west yard", zoneList.get(0).getName());
	}
}
