package com.cyber.action;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.cyber.db.CyberData;
import com.cyber.model.Questions;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("deprecation")
public class CyberAction extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 1L;

	private String qorder;
	public ArrayList<Questions> questions;
	public Questions singleQuestion;
	private int nextqorder;
	private String selectedquestionid;
	private String selectedanswerid;
	private Logger logger = Logger.getLogger(CyberAction.class);
	private InputStream correctanswer;
	@SuppressWarnings("unused")
	private Map<String, Object> session;

	public String getSelectedquestionid() {
		return selectedquestionid;
	}

	public void setSelectedquestionid(String selectedquestionid) {
		this.selectedquestionid = selectedquestionid;
	}

	public String getSelectedanswerid() {
		return selectedanswerid;
	}

	public void setSelectedanswerid(String selectedanswerid) {
		this.selectedanswerid = selectedanswerid;
	}

	public InputStream getCorrectanswer() {
		return correctanswer;
	}

	public void setCorrectanswer(InputStream correctanswer) {
		this.correctanswer = correctanswer;
	}

	public int getNextqorder() {
		return nextqorder;
	}

	public void setNextqorder(int nextqorder) {
		this.nextqorder = nextqorder;
	}

	public String getQorder() {
		return qorder;
	}

	public void setQorder(String qorder) {
		this.qorder = qorder;
	}

	public String execute() {

		ArrayList<Questions> q;
		q = new CyberData().getAllQuestions();
		logger.info("q size :" + q.size());
		logger.info("qOrder: " + this.qorder);
		for (int i = 0; i < q.size(); i++) {
			if (i == Integer.parseInt(this.qorder)) {
				singleQuestion = q.get(i);
				logger.info("inside question " + i);
				logger.info(singleQuestion.getQuestionText());

			}

		}

		return "success";
	}

	public String startQuiz() {
		Questions q;
		q = new CyberData().getFirstQuestion();
		singleQuestion = q;
		return "success";
	}

	public String nextQuiz() {
		Questions q;
		q = new CyberData().getNextQuestion(Integer.parseInt(qorder));
		singleQuestion = q;
		if(q == null) {
			return "final";
		}
		return "success";
	}

	@SuppressWarnings("deprecation")
	public String validateAnswer() {
		String canswer = new CyberData().validAnswer(
				Integer.parseInt(selectedquestionid),
				Integer.parseInt(selectedanswerid));
		logger.info("Correct Answer :" + correctanswer);
		correctanswer = new StringBufferInputStream(canswer);
		return "success";
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
