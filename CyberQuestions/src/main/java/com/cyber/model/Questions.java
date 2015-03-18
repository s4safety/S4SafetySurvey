package com.cyber.model;

import java.util.ArrayList;

public class Questions {
	public Questions(int questionID, String questionText, int questionOrder,
			boolean activeQuestion, ArrayList<Answers> answerArray) {
		super();
		this.questionID = questionID;
		this.questionText = questionText;
		this.questionOrder = questionOrder;
		this.activeQuestion = activeQuestion;
		this.answerArray = answerArray;
	}

	private int questionID;
	private String questionText;
	private int questionOrder;
	private boolean activeQuestion;
	
	private ArrayList<Answers> answerArray;

	public int getQuestionID() {
		return questionID;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public int getQuestionOrder() {
		return questionOrder;
	}

	public void setQuestionOrder(int questionOrder) {
		this.questionOrder = questionOrder;
	}

	public boolean isActiveQuestion() {
		return activeQuestion;
	}

	public void setActiveQuestion(boolean activeQuestion) {
		this.activeQuestion = activeQuestion;
	}

	public ArrayList<Answers> getAnswerArray() {
		return answerArray;
	}

	public void setAnswerArray(ArrayList<Answers> answerArray) {
		this.answerArray = answerArray;
	}
}
