package com.foodpanda;

import org.apache.log4j.Logger;

public class Test {

	public static Logger logger = Logger.getLogger(Test.class);
	public static void main(String[] args) {
		logger.error("test");
		logger.info("test");
		logger.debug("test");
	}
}
