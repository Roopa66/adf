package adf.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigPropertyLoader {
	static Properties properties = null;
	static String resourcePath = null;

	private ConfigPropertyLoader() {}

	static {
		try {
			properties = new Properties();
			resourcePath = System.getProperty("user.dir") + File.separator + "src/main/resources/config.properties";
			FileInputStream ins = new FileInputStream(resourcePath);
			properties.load(ins);
		} catch (Exception e) {}}

	public static String getConfigValue(String key) {
		String value = null;
		value = properties.getProperty(key);
		return value;
	}

}
