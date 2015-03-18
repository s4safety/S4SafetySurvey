<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cyber Questions</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script>
	function disableMsg() {
		document.getElementById('correctresult').style.display = "none";
		document.getElementById('wrongresult').style.display = "none";
	}
</script>
<script>
	function doVerifyAnswer() {
		var radios = document.getElementsByName('selectedAnswer');
		var selectedid;
		var selquestion = document.questions.squestion.value;

		for (var i = 0, length = radios.length; i < length; i++) {
			if (radios[i].checked) {
				selectedid = radios[i].value;
				break;
			}
		}

		$
				.ajax(
						{
							type : 'POST',
							url : 'validateanswer.action?selectedquestionid='
									+ selquestion + '&selectedanswerid='
									+ selectedid
						})
				.done(
						function(msg) {
							console.log(msg);
							if (msg == "Y") {
								document.getElementById('correctresult').style.display = "block";
								document.getElementById('wrongresult').style.display = "none";
							} else {
								document.getElementById('correctresult').style.display = "none";
								document.getElementById('wrongresult').style.display = "block";
							}
						});

	}
</script>
<style type="text/css">
h1 {
	margin: 1em 0px;
	font-family: "Canaro-Light", "Helvetica Neue", Helvetica, Arial,
		sans-serif;
	line-height: 1.4em;
	letter-spacing: 1px;
	color: #FFFFF;
	margin-bottom: 10px;
	margin-top: 10px;
}

jumboton {
	padding: 3px 0;
}

body {
	font-size: 25px
}
</style>

</head>
<body>
	<div class="jumbotron" align="center">


		<s:form action="questions">
			<h1 align="center">
				<s:property value="singleQuestion.questionText" />
			</h1>
			<input type="hidden" name="squestion"
				value="<s:property value="singleQuestion.questionID" />">

			<div class="form-inline">
				<s:iterator value="singleQuestion.answerArray" var="ans">

					<div class="radio" align="center">
						<label><input type="radio" name="selectedAnswer"
							value=<s:property value="answerID"/>> <s:property
								value="answerText" /></label>
					</div>
					<br />

				</s:iterator>


			</div>
		</s:form>
		<div id="correctresult" style="display: none;" align="center">
			<h3>
				<span class="label label-success">You are correct</span>
			</h3>
		</div>
		<div id="wrongresult" style="display: none;" align="center">
			<h3>
				<span class="label label-danger">You are incorrect</span>
			</h3>
		</div>
		<br> <br>


		<s:form action="nextquiz" align="center">
			<input type="submit" class="btn btn-primary btn-lg" value="Submit"
				onclick="doVerifyAnswer(); return false;" />
			&nbsp;
			<input type="submit" class="btn btn-primary btn-lg"
				value="Next Question" />
			<input type="hidden" name="qorder"
				value="<s:property value="singleQuestion.questionOrder" />">
		</s:form>
	</div>
</body>
</html>