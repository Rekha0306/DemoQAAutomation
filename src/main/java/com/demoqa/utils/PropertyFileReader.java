package com.demoqa.utils;


import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


/**
 * This is the static class to read the config properties
 * and caches the keys.
 *
 */
public class PropertyFileReader {
	
	private static Properties prop = null;
	
	static {
		intializeProperties();
	}
	
	private static void intializeProperties() {
		
		prop = new Properties();
		File proFile = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\config\\config.properties");
		
		try {
			FileInputStream fis = new FileInputStream(proFile);
			prop.load(fis);
		}catch(Throwable e) {
			e.printStackTrace();
		}

	}
	
	public static String getProperty(String key) {
		return prop.getProperty(key);
	}

}
