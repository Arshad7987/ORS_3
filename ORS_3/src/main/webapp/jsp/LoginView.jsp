<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@page import="in.co.sunrays.proj3.controller.LoginCtl"%>
<%@page import="in.co.sunrays.proj3.controller.ORSView"%>
<%@page import="in.co.sunrays.proj3.util.DataUtility"%>
<%@page import="in.co.sunrays.proj3.util.ServletUtility"%>

<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">


 <!--    favicon-->
    <link rel="shortcut icon" href="theam_wel/image/fav-icon.png" type="image/x-icon">
<title>Login</title>

<style type="text/css">
body {
	background-image:url("./image/bg_login.jpg");
	background-size: cover;
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-position: center;
}
</style>

</head>
<%@ include file="Header.jsp"%>
<body>
	
	
	<form action="<%=ORSView.LOGIN_CTL%>" method="post">
	

	<jsp:useBean id="dto" class="in.co.sunrays.proj3.dto.UserDTO"
		scope="request"></jsp:useBean>
<%String uri=(String)request.getAttribute("uri");
if(uri!=null){%>
	<input type="hidden" name="uri"
		value="<%=uri%>"><%} %>
	<input type="hidden" name="id" value="<%=dto.getId()%>">
	<input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
	<input type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>">
	<input type="hidden" name="createdDatetime"
		value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
	<input type="hidden" name="modifiedDatetime"
		value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">

	<div class="container">

	
		<br>
		<div class="row ">
			<div class="col-md-4"></div>
			<div class="col-md-4" >
				
				<div class="well login-box shadow text-center ">
					<legend >
						<font size="5"  ><b>Login</b></font>
					</legend>
		<div align="center">
				<%if(!ServletUtility.getSuccessMessage(request).equals("")){%>
								<div class="alert alert-success alert-dismissible">
                               <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                            <strong> <%=ServletUtility.getSuccessMessage(request)%></strong>
                         </div>
								<%} if(!ServletUtility.getErrorMessage(request).equals("")){%>
				       	<div class="alert alert-danger alert-dismissible">
                          <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                          <strong> <%=ServletUtility.getErrorMessage(request)%></strong>
                          </div>
								<%} %>
			
			

		</div>
		
					<div class="form-group" align="left">
						<label for="username-email">Login Id</label><font color="red">
							*</font>
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input
								placeholder="Enter LoginId" type="text" class="form-control"
								name="login"
								value="<%=DataUtility.getStringData(dto.getLogin())%>">
						</div>
						<label class="control-label text-danger  error_msg" for="inputError1">
											<%=ServletUtility.getErrorMessage("login", request)%></label>
						
					</div>

					<div class="form-group " align="left">
						<label for="password">Password</label><font color="red"> *</font>
						<div class="input-group ">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span> <input name="password" 
								placeholder="Enter Password" type="password"
								class="form-control"
								value="<%=DataUtility.getStringData(dto.getPassword())%>">
						</div>
							<label class="control-label text-danger  error_msg" for="inputError1">
											<%=ServletUtility.getErrorMessage("password", request)%></label>
			
					</div>

					<div class="form-group" align="left">
						<a href="<%=ORSView.FORGET_PASSWORD_CTL%>"> <b><font
								size="3"><u>Forget password</u></font></b></a>
					</div>

					<div class="form-group text-center">
						<input type="submit" class="btn btn-success" name="operation"
							value="<%=LoginCtl.OP_SIGN_IN%>">
							

						<button class="btn btn-danger btn-cancel-action" name="operation"
							value="<%=LoginCtl.OP_RESET%>">Reset</button>

					</div>
				</div>
			</div>
		</div>

	</div>

	</form>

</body>
<%@ include file="Footer.jsp"%>
</html>
