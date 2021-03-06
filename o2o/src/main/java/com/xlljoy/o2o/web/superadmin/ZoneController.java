package com.xlljoy.o2o.web.superadmin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xlljoy.o2o.entity.Zone;
import com.xlljoy.o2o.service.ZoneService;

@Controller
@RequestMapping("/superadmin")
public class ZoneController {
	
	@Autowired
	private ZoneService zoneService;
	
	@RequestMapping(value="/listzone", method=RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listZone(){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			List<Zone> zoneList = zoneService.getZoneList();
			modelMap.put("rows", zoneList);
			modelMap.put("total", zoneList.size());
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
		}		

		return modelMap;
	}
}
