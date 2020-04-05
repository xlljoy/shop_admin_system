package com.xlljoy.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xlljoy.o2o.entity.DailyNew;

public interface DailyNewDao {
	List<DailyNew> queryDailyNew(@Param("DailyNewCondition") DailyNew dailyNewCondition);
	int insertDailyNew(DailyNew dailyNew);
}
