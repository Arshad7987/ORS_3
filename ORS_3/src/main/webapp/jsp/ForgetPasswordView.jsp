<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@page import="in.co.sunrays.proj3.controller.ForgetPasswordCtl"%>
<%@page import="in.co.sunrays.proj3.util.DataUtility"%>
<%@page import="in.co.sunrays.proj3.util.ServletUtility"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <!--    favicon-->
    <link rel="shortcut icon" href="theam_wel/image/fav-icon.png" type="image/x-icon">
<title>Login</title>
</head>
<body class="img-responsive" background="image/bg_forgetpass.jpg"
	style="background-repeat: no-repeat; background-size: 100% 100%;">
	<%@ include file="Header.jsp"%>
	
		<form action="<%=ORSView.FORGET_PASSWORD_CTL%>" method="post">
		
		<jsp:useBean id="dto" class="in.co.sunrays.proj3.dto.UserDTO"
			scope="request">
		</jsp:useBean>
	

	<div class="container">

		<div align="center">
			<div class="alert alert-success" role="alert"
				style="width: 35%; margin-left: 0%; font-size: 133% ; margin-bottom: 5px;"
				<%=ServletUtility.getSuccessMessage(request).equals("") ? "hidden" : ""%>>
				<b> <%=ServletUtility.getSuccessMessage(request)%></b>
			</div>
			<div class="alert alert-danger" role="alert"
				style="width: 35%; margin-left: 0%; font-size: 135%; margin-bottom: 5px;"
				<%=ServletUtility.getErrorMessage(request).equals("") ? "hidden" : ""%>>
				<b><%=ServletUtility.getErrorMessage(request)%></b>
			</div>

			<%
				String message = (String) request.getAttribute("message");

				if (message != null) {
			%>

			<div class="alert alert-danger" role="alert"
				style="width: 35%; margin-left: 0%; font-size: 150%;"
				<%=message.equals("") ? "hidden" : ""%>>
				<b><%=message%></b>
			</div>


		</div>
		<%
			}
		%>
		<br>
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<div class="well login-box" style="background-color: rgba(245, 245, 245, 0.7);">
					<legend>
						<font size="5"><b>Forgot your password?</b></font>
					</legend>
					
					<div class="form-group">
						<h4 align="center"><i>Submit your email address and we'll send you password.</i></h4>
					</div>

					<div class="form-group" align="left">
						<label for="username-email">Login Id</label><font color="red">*</font>
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input
								placeholder="Enter LoginId" type="text" class="form-control"
								name="emailId"
								value="<%=DataUtility.getStringData(dto.getLogin())%>">
						</div>
						<font color="red"><%=ServletUtility.getErrorMessage("emailId", request)%></font>
					</div>

					<div class="form-group text-center">
						<input type="submit" class="btn btn-success" name="operation"
							value="<%=ForgetPasswordCtl.OP_SEND%>">
							

					<button class="btn btn-danger btn-cancel-action" name="operation"
							value="<%=ForgetPasswordCtl.OP_RESET%>">Reset</button>

					</div>
				</div>
			</div>
		</div>

	</div>

	</form>

</body>
<%@ include file="Footer.jsp"%>

</html>