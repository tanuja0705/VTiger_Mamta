package com.crm.generic.fileUtility;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyUtility {
	
	public String getDataFromPropertyFile(String key) throws Throwable {
		FileInputStream fis = new FileInputStream("./ConfigData/common.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String value = prop.getProperty(key);
		return value;
		
	}

}
