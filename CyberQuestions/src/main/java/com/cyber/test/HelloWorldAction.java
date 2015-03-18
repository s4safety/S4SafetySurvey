package com.cyber.test;

import org.apache.log4j.Logger;

public class HelloWorldAction {
	private String name;
	
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(HelloWorldAction.class);

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String execute() throws Exception {
		logger.info("Inside execute method");
		String thisName = this.name.toUpperCase();
		logger.info("The value of name is : "+ thisName);
		if (thisName.equals("VINAY")) {
			return "vinay";
		} else {
			return "success";
		}
	}


}
