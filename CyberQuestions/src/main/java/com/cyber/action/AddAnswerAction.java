package com.cyber.action;

import org.apache.log4j.Logger;

import com.cyber.db.CyberData;


public class AddAnswerAction {

	private int qid;
	private String atext;
	private String acorrect;
	
	private Logger logger = Logger.getLogger(AddAnswerAction.class);

	 
	
	public int getQid() {
		return qid;
	}



	public void setQid(int qid) {
		this.qid = qid;
	}



	public String getAtext() {
		return atext;
	}



	public void setAtext(String atext) {
		this.atext = atext;
	}



	public String getAcorrect() {
		return acorrect;
	}



	public void setAcorrect(String acorrect) {
		this.acorrect = acorrect;
	}



	public String execute() {
		CyberData cd = new CyberData();
		logger.info(atext);
		cd.addAnswer(qid, atext, acorrect);
		return "success";
	}

}
