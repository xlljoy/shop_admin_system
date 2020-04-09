package com.xlljoy.o2o.service;

public interface CacheService {
	// remove all key-value with keyPrefix: eg keyPrefix = shopCategory
	//								   		remove all like shopCategory_allfirstlevel, shopCategory_allsecondlevel, shopCategory_parents_*
	void removeFromCache(String keyPrefix);
}
