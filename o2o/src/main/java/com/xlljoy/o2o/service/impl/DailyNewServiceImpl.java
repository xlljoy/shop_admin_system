package com.xlljoy.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xlljoy.o2o.dao.DailyNewDao;
import com.xlljoy.o2o.entity.DailyNew;
import com.xlljoy.o2o.service.DailyNewService;
@Service
public class DailyNewServiceImpl implements DailyNewService {
	@Autowired
	private DailyNewDao dailyNewDao;
	@Override
	public List<DailyNew> getDailyNewList(DailyNew dailyNew) {
		
		return dailyNewDao.queryDailyNew(dailyNew);
	}

}
