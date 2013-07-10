package mBret.misc;

import java.util.Properties;

public class StaticInfo {

	protected static Properties properties = new Properties();

	public static Properties getProperties() {
		return properties;
	}

	public static void setProperties(Properties properties) {
		StaticInfo.properties = properties;
	}

}
