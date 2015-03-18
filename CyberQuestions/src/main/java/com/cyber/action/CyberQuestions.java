package com.cyber.action;

import java.util.ArrayList;


import org.apache.log4j.Logger;

import com.cyber.db.CyberData;
import com.cyber.model.Questions;


public class CyberQuestions {


	public ArrayList<Questions> questions;
	public Questions singleQuestion;
	private String qOrder;
	public String getqOrder() {
		return qOrder;
	}

	public void setqOrder(String qOrder) {
		this.qOrder = qOrder;
	}

	private Logger logger = Logger.getLogger(CyberQuestions.class);



	public Questions getSingleQuestion() {
		return singleQuestion;
	}

	public void setSingleQuestion(Questions singleQuestion) {
		this.singleQuestion = singleQuestion;
	}



	public String execute() {
		ArrayList<Questions> q;
		q = new CyberData().getAllQuestions();
		logger.info("q size :" + q.size());
		logger.info("qOrder: " + this.qOrder);
//		for (int i = 0; i < q.size(); i++) {
//			if (i == Integer.parseInt(this.qOrder)) {
//				singleQuestion = q.get(i);
//				logger.info("inside question " + i);
//				logger.info(singleQuestion.getQuestionText());
//
//			}
//
//		}
		return "success";
	}

	public ArrayList<Questions> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Questions> questions) {
		this.questions = questions;
	}
}
