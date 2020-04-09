package com.xlljoy.o2o.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xlljoy.o2o.BaseTest;
import com.xlljoy.o2o.entity.DailyNew;

public class DailyNewServiceTest extends BaseTest {
	@Autowired
	private DailyNewService dailyNewService;
	@Test
	public void testGetDailyNewList() {
		DailyNew dailyNew  = new DailyNew();
		List<DailyNew> dailynewList = dailyNewService.getDailyNewList(dailyNew);
		System.out.println(dailynewList.size());
	}
}
