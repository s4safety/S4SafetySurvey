<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CyberQuestions Database Updater</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="jumbotron" align="center">
		<h1 align="center">Add Questions</h1>
		<br> <br>
		<s:form action="addquestion">
			<div class="form-group">
				<s:label value="Enter Question:" />
					<s:textfield name="q" width="150px"/>
			</div>
		</s:form>
		<br> <input type="submit" class="btn btn-primary btn-lg"
			value="Add Questions" />
	</div>
</body>
</html>