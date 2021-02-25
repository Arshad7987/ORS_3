<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<%@page import="in.co.sunrays.proj3.controller.ORSView"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<title>Page Not Found</title>
 <link rel="shortcut icon" href="theam_wel/image/fav-icon.png" type="image/x-icon">
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
<br><br><br><br><br><br><br><br><br><br><br>

<div class="row">
<div class="col-sm-5"></div>
<div class="col-sm-2">
<center>
<img alt="404" src="./img/img404.jpg" class="img-circle" height="150px" width="150px">
</center>
<br><br>
</div>
<div class="col-sm-5"></div>
</div>
<center>
<h3 style="color: red;">Page Not Found !!</h3>

<a class="btn btn-info" href="<%=ORSView.WELCOME_CTL%>">Go BACK</a>
</center>
</div>
</body>
</html>