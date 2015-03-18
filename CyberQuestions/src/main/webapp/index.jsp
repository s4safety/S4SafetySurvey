<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to Cyber Security Questionnaire</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<style type="text/css">
h1 {
	margin: 1em 0px;
	font-family: "Canaro-Light", "Helvetica Neue", Helvetica, Arial,
		sans-serif;
	text-transform: uppercase;
	line-height: 1.4em;
	letter-spacing: 2px;
	color: #FFFFF;
}
</style>

<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-60760208-1', 'auto');
  ga('send', 'pageview');

</script>


</head>
<body bgcolor="#25394D">
	<div class="jumbotron" align="center">
		<h1 align="center">Welcome to CyberSecurity Questionnaire</h1>
		<br>
		<br>
		<form action="questions">
			<input type="submit" class="btn btn-primary btn-lg"
				value="Start the Quiz"></input>
		</form>
	</div>

</body>

</html>
