package com.cruat.methodlogger;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		MethodLogger logger = new MethodLogger();
		logger.trace(logger.getName());
	}
}
