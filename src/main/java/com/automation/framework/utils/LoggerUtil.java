package com.automation.framework.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtil {
	
	public static Logger getLogger(Class<?> clas) {
		return LogManager.getLogger(clas);
	}

}
