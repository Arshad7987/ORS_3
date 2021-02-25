<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<%@page import="in.co.sunrays.proj3.controller.ORSView"%>
<html>
<%@page isErrorPage="true" %>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Error</title>
 <link rel="shortcut icon" href="theam_wel/image/fav-icon.png" type="image/x-icon">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<style type="text/css">
body {
	background-image: url("/ORSProject03/image/bg_theam.jpg");
	background-size: cover;
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-position: center;
}
</style>
</head>
<body>
<div class="container-fluid">
<div class="row">
<div class="col-sm-3"></div>
<div class="col-sm-6">
<center>
<br><br><br><br><br><br><br>
<h2 style="color: red;">***OOPS something went wrong..!!***</h2>
<br><br><br>
<a href="<%=ORSView.WELCOME_CTL%>" class="btn btn-info">Go Back</a>
</center>
</div>
<div class="col-sm-3"></div>
</div>
</div>
</body>
</html>