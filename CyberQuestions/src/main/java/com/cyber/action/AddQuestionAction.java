package com.cyber.action;

import org.apache.log4j.Logger;

import com.cyber.db.CyberData;
import com.cyber.model.Questions;

public class AddQuestionAction {

	private String q;
	private Logger logger = Logger.getLogger(AddQuestionAction.class);

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}
	
	public String execute() {
		CyberData cd = new CyberData();
		logger.info(q);
		cd.addQuestion(q);
		return "success";
	}

}
