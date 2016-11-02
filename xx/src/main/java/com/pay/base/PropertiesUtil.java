package com.pay.base;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * properties文件读取工具类
 * 
 * @author jql
 *
 */
public class PropertiesUtil {
	private static final Logger LOG = LoggerFactory.getLogger(PropertiesUtil.class);

	private PropertiesUtil() {
	}

	public static Properties getProperties(InputStream is) {
		Properties properties = new Properties();
		try {
			properties.load(is);
			return properties;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		} finally {
			if (is != null) {
				try {
					is.close();
					is = null;
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
			}
		}
		return null;
	}

	public static Properties getProperties(String relativePath) {
		Properties properties = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(relativePath);
			properties.load(inputStream);
			return properties;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
					inputStream = null;
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
			}
		}
		return null;
	}

	public static void save(Properties propertie, String filePath, String description) throws Exception {
		if (propertie == null) {
			throw new NullPointerException("propertie == null");
		}
		OutputStreamWriter outputStream = null;
		try {
			outputStream = new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8");
			propertie.store(outputStream, description);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
					outputStream = null;
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
			}
		}
	}
}