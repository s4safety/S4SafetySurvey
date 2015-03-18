package com.cyber.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.cyber.model.Answers;
import com.cyber.model.Questions;

public class CyberData {
	private static Connection dbConnection = null;

	private static final Logger logger = Logger.getLogger(CyberData.class);

	public ArrayList<Questions> getAllQuestions() {
		ArrayList<Questions> questions = new ArrayList<Questions>();
		Connection conn = getConnection();
		String sql = "select * from cyber_questions where q_status = 'Y' order by q_order";
		logger.info("SQL Started: " + sql);
		PreparedStatement preparedStatement;
		try {
			preparedStatement = conn.prepareStatement(sql);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int questionID = rs.getInt("Q_ID");
				String questionText = rs.getString("Q_TEXT");
				int questionOrder = rs.getInt("Q_ORDER");
				ArrayList<Answers> answers = getAnswers(questionID);
				questions.add(new Questions(questionID, questionText,
						questionOrder, true, answers));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return questions;
	}

	public Questions getQuestionbyID(int qID) {
		Questions q = null;
		try {
			Connection conn = getConnection();
			String sql = "select * from cyber_questions where Q_STATUS='Y' AND Q_ID = ? ORDER BY Q_ORDER";
			logger.info("SQL Started: " + sql);
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, qID);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int questionID = rs.getInt("Q_ID");
				String questionText = rs.getString("Q_TEXT");
				int questionOrder = rs.getInt("Q_ORDER");
				ArrayList<Answers> answers = getAnswers(questionID);
				q = new Questions(questionID, questionText, questionOrder,
						true, answers);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return q;

	}

	public Questions getNextQuestion(int qorder) {
		Questions q = null;
		try {
			Connection conn = getConnection();
			String sql = "select * from cyber_questions where q_status = 'Y' and q_order > ? order by q_order limit 1";
			logger.info("SQL Started: " + sql);
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, qorder);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int questionID = rs.getInt("Q_ID");
				String questionText = rs.getString("Q_TEXT");
				int questionOrder = rs.getInt("Q_ORDER");
				ArrayList<Answers> answers = getAnswers(questionID);
				q = new Questions(questionID, questionText, questionOrder,
						true, answers);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return q;

	}

	public void addQuestion(String q) {
		try {
			int lastOrder = CyberData.getLastOrder();
			String sql = "INSERT INTO cybersurvey.cyber_questions(Q_TEXT,Q_ORDER, Q_STATUS)"
					+ " VALUES(?, ?, ?);";
			Connection conn = getConnection();
	
			logger.info("SQL Started: " + sql);
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, q);
			preparedStatement.setInt(2, lastOrder + 1);
			preparedStatement.setString(3, "Y");
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addAnswer(int qID, String answer, String isCorrect) {
		try {
			int lastOrder = CyberData.getLastAnswerOrder();
			String sql = "INSERT INTO cybersurvey.cyber_answers(Q_ID,A_TEXT,A_ORDER,A_CORRECT,A_STATUS)"
					+ " VALUES(?, ?, ?,?,?);";
			Connection conn = getConnection();
				logger.info("SQL Started: " + sql);
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, qID);
			preparedStatement.setString(2, answer);
			 
			preparedStatement.setInt(3, lastOrder + 1);
			preparedStatement.setString(4, isCorrect);
			preparedStatement.setString(5, "Y");
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public String validAnswer(int questionID, int answerID) {
		try {
			String sql = "select a_correct from cyber_answers where q_id = ? and a_id = ?";
			Connection conn = getConnection();
			logger.info("SQL Started: " + sql);
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, questionID);
			preparedStatement.setInt(2, answerID);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				return rs.getString("A_CORRECT");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "N";
	}

	public Questions getFirstQuestion() {
		Questions q = null;
		try {
			Connection conn = getConnection();
			String sql = "select * from cyber_questions where q_status = 'Y' order by q_order limit 1";
			logger.info("SQL Started: " + sql);
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int questionID = rs.getInt("Q_ID");
				String questionText = rs.getString("Q_TEXT");
				int questionOrder = rs.getInt("Q_ORDER");
				ArrayList<Answers> answers = getAnswers(questionID);
				q = new Questions(questionID, questionText, questionOrder,
						true, answers);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return q;

	}

	public ArrayList<Answers> getAnswers(int qID) {
		ArrayList<Answers> answers = new ArrayList<Answers>();
		try {
			Connection conn = getConnection();
			String sql = "select * from cyber_answers where A_STATUS='Y' AND Q_ID = ? ORDER BY A_ORDER";
			logger.info("SQL Started: " + sql);
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, qID);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int aID = rs.getInt("A_ID");
				String aText = rs.getString("A_TEXT");
				int aOrder = rs.getInt("A_ORDER");
				String aCorrect = rs.getString("A_CORRECT");
				boolean validAnswer = false;
				if (aCorrect.equalsIgnoreCase("Y")) {
					validAnswer = true;
				}
				Answers a = new Answers(aID, qID, aText, aOrder, validAnswer,
						true);
				answers.add(a);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return answers;
	}

	public static Connection getConnection() {
		if (dbConnection != null) {
			return dbConnection;
		} else {
			try {

				String dbDriver = "com.mysql.jdbc.Driver";
				String connectionUrl = "jdbc:mysql://localhost:3306/cybersurvey";
				String userName = "root";
				String password = "hellovarun1";

				logger.info("DB Driver: " + dbDriver);
				logger.info("Connection URL: " + connectionUrl);
				logger.info("User Name: " + userName);
				logger.info("Password: " + password);

				Class.forName(dbDriver).newInstance();
				dbConnection = (Connection) DriverManager.getConnection(
						connectionUrl, userName, password);
				logger.info("connected");

			} catch (Exception e) {
				e.printStackTrace();
			}
			return dbConnection;
		}
	}

	public static int getLastOrder() {
		try {
			Connection conn = getConnection();
			String sql = "select max(q_order) as MORDER from cyber_questions";
			logger.info("SQL Started: " + sql);
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int qOrder = rs.getInt("MORDER");
				return qOrder;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static int getLastAnswerOrder() {
		try {
			Connection conn = getConnection();
			String sql = "select max(a_order) as MORDER from cyber_answers";
			logger.info("SQL Started: " + sql);
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int qOrder = rs.getInt("MORDER");
				return qOrder;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static void main(String args[]) throws IOException {

		try {

			CyberData c = new CyberData();
			ArrayList<Questions> q = c.getAllQuestions();

			for (int i = 0; i < q.size(); i++) {
				System.out.println(q.get(i).getQuestionText());
				ArrayList<Answers> a = q.get(i).getAnswerArray();
				for (int j = 0; j < a.size(); j++) {
					System.out.println(a.get(j).getAnswerText());
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}