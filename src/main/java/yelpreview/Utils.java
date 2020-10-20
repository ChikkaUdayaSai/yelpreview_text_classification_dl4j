package yelpreview;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Utils {
	
	final static Logger logger = LoggerFactory.getLogger(Utils.class);

	public static String loadConf(String path, String key) throws Exception {
		Properties props = new Properties();
		InputStream in = new FileInputStream(path);
		props.load(in);
		String value = props.getProperty(key);
		
		if (null==value) {
			String errmsg = key + " is null.";
			logger.error(errmsg);
			throw new Exception(errmsg);
		}
		
		return value;
	}

	public static void main(String[] args) throws Exception{
		
		String dataDir = loadConf("config.properties", "dataDir");
		logger.info(dataDir);

	}

}
