package com.xlljoy.o2o.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	private String[] encryptPropNames = {"jdbc.username", "jdbc.password"};
	
	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		if (isEncryptProp(propertyName)) {
			String decryptValue = DESUtil.getDecryptString(propertyValue);
			return decryptValue;
		} else {
			return propertyValue;
		}
	}

	private boolean isEncryptProp(String propertyName) {
		if (propertyName != null) {
			for (String name : encryptPropNames) {
				if (propertyName.equals(name)) {
					return true;
				}
			}
		}
		return false;
	}
	
	
}
