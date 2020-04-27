package com.sfdc.automation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileReader {
	
	public static Properties prop = null;
    
	
	public static Properties getProperties() {
		Properties prop = null;
		try {
			prop = readPropertiesFile("SFDCProp.properties");
		} catch (IOException e) {
		System.out.println("File not found"+e);
		}
		return prop;
	
	}
	
	public static Properties readPropertiesFile(String filename) throws IOException {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filename);
			prop = new Properties();
			prop.load(fis);
		} catch (Exception e) {
			System.out.println(" properties file cannot be found"+e);
		}
		return prop;
	}
	
	
	
}
