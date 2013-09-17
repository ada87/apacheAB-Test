package com.xdnote.ab.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
/**
 * @author xdnote.com
 * <p>
 * 配置存取类
 * </p>
 * */
public class ConfigManager {

    /**
     * <p>配置文件，默认为config.properties ,与程序同级别</p>
     * */
	public static String CONFIGPATH = "config.properties";
    /**
     * <p>配置文件解析后的properties</p>
     * */
	private static Properties prop = null;

    /**
     * @return Properties
     * <p>使用单例模式获取一个配置properties</p>
     * */
	public static Properties getprop(){
		if (prop == null) {
			prop = new Properties();
			try {
	            File configFile = new File(CONFIGPATH);
	            if (!configFile.exists()) {
	                configFile.createNewFile();
	            }
				InputStream in = new FileInputStream(configFile);
				prop.load(in);
				in.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return prop;
	}
    /**
     * @param key 需要取的配置KEY
     * @return value key对应的value String
     * <p>使用单例模式获取一个配置properties</p>
     * */
	public static String getValue(final String key) {
		Properties pro = ConfigManager.getprop();
		return pro.getProperty(key, "");
	}
    /**
     * @param key 需要取的配置KEY
     * @param defaultvalue 当没有这个key时，默认返回值
     * @return value key对应的value String
     * <p>使用单例模式获取一个配置properties</p>
     * */
	public static String getValue(final String key, final String defaultvalue) {
		Properties pro = ConfigManager.getprop();
		return pro.getProperty(key, defaultvalue);
	}
    /**
     * @param key 需要设置配置KEY
     * @param value KEY对应的VALUE
     * <p>使用单例模式获取一个配置properties</p>
     * */
	public static void setValue(final String key, final String value) {
		Properties pro = ConfigManager.getprop();
		pro.setProperty(key, value);
		OutputStream out;
		try {
			out = new FileOutputStream(CONFIGPATH);
			pro.store(out, "");
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
