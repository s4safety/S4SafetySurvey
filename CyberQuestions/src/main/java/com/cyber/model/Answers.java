package com.cyber.model;

public class Answers {
	public Answers(int answerID, int questionID, String answerText,
			int answerOrder, boolean validAnswer, boolean activeAnswer) {
		super();
		this.answerID = answerID;
		this.questionID = questionID;
		this.answerText = answerText;
		this.answerOrder = answerOrder;
		this.validAnswer = validAnswer;
		this.activeAnswer = activeAnswer;
	}
	private int answerID;
	private int questionID;
	private String answerText;
	private int answerOrder;
	public int getAnswerID() {
		return answerID;
	}
	public void setAnswerID(int answerID) {
		this.answerID = answerID;
	}
	public int getQuestionID() {
		return questionID;
	}
	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}
	public String getAnswerText() {
		return answerText;
	}
	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}
	public int getAnswerOrder() {
		return answerOrder;
	}
	public void setAnswerOrder(int answerOrder) {
		this.answerOrder = answerOrder;
	}
	public boolean isValidAnswer() {
		return validAnswer;
	}
	public void setValidAnswer(boolean validAnswer) {
		this.validAnswer = validAnswer;
	}
	public boolean isActiveAnswer() {
		return activeAnswer;
	}
	public void setActiveAnswer(boolean activeAnswer) {
		this.activeAnswer = activeAnswer;
	}
	private boolean validAnswer;
	private boolean activeAnswer;

}
