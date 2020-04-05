package com.xlljoy.o2o.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xlljoy.o2o.BaseTest;
import com.xlljoy.o2o.entity.DailyNew;
import com.xlljoy.o2o.util.ImageUnit;
import com.xlljoy.o2o.util.ImageUtil;

public class DailyNewDaoTest extends BaseTest{
	@Autowired
	private DailyNewDao dailyNewDao;
	@Test
	public void testInsertDailyNew() {
		DailyNew dailynew = new DailyNew();
		dailynew.setCreateTime(new Date());
		dailynew.setEnableStatus(1);
		dailynew.setLink("link");
		dailynew.setName("good new 1");
		dailynew.setPriority(100);
		dailynew.setUpdateTime(new Date());
		ImageUnit mainu;
		try {
			File main_img = new File("/home/jli/Pictures/image3.jpg");
			InputStream is;
			ImageUnit iu;
			InputStream mains;
			mains = new FileInputStream(main_img);
			mainu = new ImageUnit(mains, main_img.getName());
			ImageUtil.addImgForDailyNew(mainu, dailynew);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int effect = dailyNewDao.insertDailyNew(dailynew);
		System.out.println(effect);
	}
	
	@Test
	public void testQueryDailyNew() {
		DailyNew dailyNewCondition = new DailyNew();
		dailyNewCondition.setEnableStatus(1);
		List<DailyNew> dailyNewList = dailyNewDao.queryDailyNew(dailyNewCondition);
		System.out.println(dailyNewList.size());
	}
	
}
