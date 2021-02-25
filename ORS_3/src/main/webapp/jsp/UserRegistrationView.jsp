<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@page import="in.co.sunrays.proj3.controller.UserRegistrationCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.sunrays.proj3.util.HTMLUtility"%>
<%@page import="in.co.sunrays.proj3.util.DataUtility"%>
<%@page import="in.co.sunrays.proj3.util.ServletUtility"%>

<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

 <!--    favicon-->
    <link rel="shortcut icon" href="theam_wel/image/fav-icon.png" type="image/x-icon">
<title>User Registration</title>

<style type="text/css">

body {
	background-image: url("image/bg_reg.jpg");
	background-size: cover;
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-position: center;
}

</style>
<!-- datepicker files -->
 <link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
$(function() {
	$("#datepicker").datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat : 'mm/dd/yy',
		yearRange:"-25:-18",
		defaultDate : "01/01/2000",
	});
});
</script>
</head>
<body class="img-responsive" background="/ORSProject3/image/bg_wel.jpg" style="background-repeat:no-repeat ; background-size:100%100%;">
	<form action="<%=ORSView.USER_REGISTRATION_CTL%>" method="post">

		<%@ include file="Header.jsp"%>
		<script type="text/javascript" src="./js/calendar.js"></script>

		<jsp:useBean id="dto" class="in.co.sunrays.proj3.dto.UserDTO"
			scope="request"></jsp:useBean>

		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12 col-md-6 col-sm-12 col-lg-4 col-md-offset-4">
					<div class="panel panel-primary"
						style="margin-top: 10px; background-color: #DCDCDC;">
						<div class="panel-heading" style="background-color: darksalmon"
							align="center">

							<b><h1 style="color: saddlebrown">User Registration</h1></b>

						</div>

						<div class="panel-body">
							<div align="center">
								<div class="alert alert-success" role="alert"
									style="width: 90%; margin-left: 0%; font-size: 150%"
									<%=ServletUtility.getSuccessMessage(request).equals("") ? "hidden" : ""%>>
									<b> <%=ServletUtility.getSuccessMessage(request)%></b>
								</div>
								<div class="alert alert-danger" role="alert"
									style="width: 90%; margin-left: 0%; font-size: 150%;"
									<%=ServletUtility.getErrorMessage(request).equals("") ? "hidden" : ""%>>
									<b><%=ServletUtility.getErrorMessage(request)%></b>
								</div>
							</div>


							<br> <input type="hidden" name="id" value="<%=dto.getId()%>">
							<input type="hidden" name="createdBy"
								value="<%=dto.getCreatedBy()%>"> <input type="hidden"
								name="modifiedBy" value="<%=dto.getModifiedBy()%>"> <input
								type="hidden" name="createdDatetime"
								value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
							<input type="hidden" name="modifiedDatetime"
								value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">


							<div class="col-xs-12 col-sm-12 col-md-10 col-lg-12">


								<div class="form-group" style="margin-left: 10%;">
									<label align="left" class="control-label text-info col-md-6">
										First Name<span style="color: red;">*</span>
									</label>

									<div class="col-md-12">
										<div class="input-group">
											<span class="input-group-addon"><i
												class="glyphicon glyphicon-user"></i></span> <input type="text"
												class="form-control" name="firstName"
												placeholder="Enter First Name"
												value="<%=DataUtility.getStringData(dto.getFirstName())%>">
										</div>
										<label class="control-label text-danger" for="inputError1">
											<%=ServletUtility.getErrorMessage("firstName", request)%></label>
									</div>
								</div>


								<div class="form-group" style="margin-left: 10%;">
									<label align="left" class="control-label col-md-6 text-info">
										Last Name<span style="color: red;">*</span>
									</label>

									<div class="col-md-12">
										<div class="input-group">
											<span class="input-group-addon"><i
												class="glyphicon glyphicon-user"></i></span> <input type="text"
												class="form-control" name="lastName"
												placeholder="Enter Last Name"
												value="<%=DataUtility.getStringData(dto.getLastName())%>">
										</div>
										<label class="control-label text-danger" for="inputError1">
											<%=ServletUtility.getErrorMessage("lastName", request)%></label>
									</div>
								</div>

						
				
								<div class="form-group" style="margin-left: 10%;">
									<label class="control-label col-md-6 text-info"> Login
										Id<span style="color: red;">*</span>
									</label>

									<div class="col-md-12">
										<div class="input-group">
											<span class="input-group-addon"> <i
												class="glyphicon glyphicon-log-in"></i></span> <input type="text"
												class="form-control" name="login"
												placeholder="Enter Login ID"
												value="<%=DataUtility.getStringData(dto.getLogin())%>"
												<%=(dto.getId() > 0) ? "readonly" : ""%>>
										</div>
										<label class="control-label text-danger" for="inputError1">
											<%=ServletUtility.getErrorMessage("login", request)%></label>
									</div>
								</div>

								<div class="form-group" style="margin-left: 10%;">
									<label align="left" class="control-label col-md-6 text-info">
										Password<span style="color: red;">*</span>
									</label>

									<div class="col-md-12">
										<div class="input-group">
											<span class="input-group-addon"> <i
												class="glyphicon glyphicon-lock" aria-hidden="true"></i></span> <input
												type="password" class="form-control" name="password"
												placeholder="Enter Password"
												value="<%=DataUtility.getStringData(dto.getPassword())%>">
										</div>
										<label class="control-label text-danger" for="inputError1">
											<%=ServletUtility.getErrorMessage("password", request)%></label>
									</div>
								</div>

					

								<div class="form-group" style="margin-left: 10%;">
									<label align="left" class="control-label col-md-6 text-info">
										Confirm Password<span style="color: red;">*</span>
									</label>

									<div class="col-md-12">
										<div class="input-group">
											<span class="input-group-addon"> <i
												class="glyphicon glyphicon-lock" aria-hidden="true"></i></span> <input
												type="password" class="form-control" name="confirmPassword"
												placeholder="Enter Confirm Password"
												value="<%=DataUtility.getStringData(dto.getConfirmPassword())%>">
										</div>
										<label class="control-label text-danger" for="inputError1">
											<%=ServletUtility.getErrorMessage("confirmPassword", request)%></label>
									</div>
								</div>

						

								<div class="form-group" style="margin-left: 10%;">
									<label align="left" class="control-label col-md-6 text-info">
										Gender<span style="color: red;">*</span>
									</label>

									<div class="col-md-12">
										<div class="input-group">
											<span class="input-group-addon"> <i class="fa fa-venus-double" aria-hidden="true"></i></span>
											<%
			  HashMap map = new HashMap();
			  map.put("M", "Male");
			  map.put("F", "Female");
			  String htmlList = HTMLUtility.getList("gender", dto.getGender(), map);
		  %>
											<%=htmlList%>
										</div>
										<label class="control-label text-danger" for="inputError1">
											<%=ServletUtility.getErrorMessage("gender", request)%></label>
									</div>
								</div>

					
								<div class="form-group" style="margin-left: 10%;">
									<label align="left" class="control-label col-md-6 text-info">
										Mobile No.<span style="color: red;">*</span>
									</label>

									<div class="col-md-12">
										<div class="input-group">
											<span class="input-group-addon"> <i
												class="glyphicon glyphicon-earphone"></i></span> <input type="text"
												class="form-control" name="mobileNo" maxlength="10"
												placeholder="Enter Mobile Number"
												value="<%=DataUtility.getStringData(dto.getMobileNo())%>">
										</div>
										<label class="control-label text-danger" for="inputError1">
											<%=ServletUtility.getErrorMessage("mobileNo", request)%></label>
									</div>
								</div>

						

								<div class="form-group" style="margin-left: 10%;">
									<label class="control-label col-md-6 text-info"> Date
										Of Birth<span style="color: red;">*</span>
									</label>

									<div class="col-md-12">
										<div class="input-group">
											<span class="input-group-addon"> <i
												class="glyphicon glyphicon-calendar"></i></span> <input type="text"
												class="form-control" name="dob" id="datepicker"
												readonly="readonly" placeholder="Enter Date of Birth"
												value="<%=DataUtility.getDateString(dto.getDob())%>">
										</div>
										<label class="control-label text-danger" for="inputError1">
											<%=ServletUtility.getErrorMessage("dob", request)%></label>
									</div>
								</div>

							</div>
							
				
							<div class="form-group" align="center">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<br>
									<button type="submit" class="btn btn-primary" name="operation"
										value="<%=UserRegistrationCtl.OP_SIGN_UP%>">
										<span class="glyphicon glyphicon-check"></span> Save
									</button>
									&emsp;
									<button type="submit" class="btn btn-warning" name="operation"
										value="<%=UserRegistrationCtl.OP_RESET%>">
										<span class="glyphicon glyphicon-refresh"></span> Reset
									</button>


								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<br>
		</div>
	</form>
	<div style="min-height: 200px">
	<%@ include file="Footer.jsp"%>
	</div>
</body>
</html>