<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%@page import="in.co.sunrays.proj3.controller.MyProfileCtl"%>
<%@page import="in.co.sunrays.proj3.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.sunrays.proj3.util.DataUtility"%>
<%@page import="in.co.sunrays.proj3.util.ServletUtility"%>

<head>

 <!--    favicon-->
    <link rel="shortcut icon" href="/ORSProject3/theam_wel/image/fav-icon.png" type="image/x-icon">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to ORS</title>
<style type="text/css">

body {
	background-image: url("../image/bg_theam.jpg");
	background-size: cover;
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-position: center;
}

</style>
</head>
  
<body>

<form action="<%=ORSView.MY_PROFILE_CTL%>" method="post">

        <%@ include file="Header.jsp"%>
 <!-- <script type="text/javascript" src="../js/calendar.js"></script>-->        
 <jsp:useBean id="dto" class="in.co.sunrays.proj3.dto.UserDTO" scope="request"></jsp:useBean>   
            
            <div class="container-fluid">
<div class="row">
		<div class="col-xs-12 col-md-6 col-sm-12 col-lg-4 col-md-offset-4">
			<div class="panel panel-primary" style="margin-top:10px; background-color: #DCDCDC;">
				<div class="panel-heading" style="background-color:gainsboro;" align="center">
		
	 <b><h1>My Profile</h1></b>
		
		</div>
		
		<div class="panel-body">
			<div align="center">
				<%if(!ServletUtility.getSuccessMessage(request).equals("")){%>
								<div class="alert alert-success alert-dismissible">
                               <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                            <strong> <%=ServletUtility.getSuccessMessage(request)%></strong>
                         </div><%} %>
			<div class="alert alert-danger" role="alert"
				style="width: 90%; margin-left: 0%; font-size: 150%;"
				<%=ServletUtility.getErrorMessage(request).equals("") ? "hidden" : ""%>>
				<b><%=ServletUtility.getErrorMessage(request)%></b>
			</div>
		</div>
            
            <br>
            <input type="hidden" name="id" value="<%=dto.getId()%>">
            <input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>"> 
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">
            
            
            <div class="col-xs-12 col-sm-12 col-md-10 col-lg-12">
			
			
	 <div class="form-group" style="margin-left: 10%;">
	 <label align="left" class="control-label text-info col-md-6">
	 Login Id<span style="color: red;">*</span></label>

	  <div class="col-md-12" style="margin-bottom: 10px;">
	 <div class="input-group">
	 <span class="input-group-addon"><i class="glyphicon glyphicon-log-in"></i></span> 
	 <input type="text" class="form-control" name="login" 
	 value="<%=DataUtility.getStringData(dto.getLogin())%>" readonly="readonly">
	 </div>
	 <label class="control-label text-danger"  for="inputError1">
	 <%=ServletUtility.getErrorMessage("login", request)%></label>
	 </div>
	 </div>
            
					
	 <div class="form-group" style="margin-left: 10%;">
	 <label align="left" class="control-label text-info col-md-6">
	 First Name<span style="color: red;">*</span></label>

	  <div class="col-md-12" style="margin-bottom: 10px;">
	 <div class="input-group">
	 <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
	 <input type="text" class="form-control" name="firstName" 
	 value="<%=DataUtility.getStringData(dto.getFirstName())%>">
	 </div>
	 <label class="control-label text-danger"  for="inputError1">
	 <%=ServletUtility.getErrorMessage("firstName", request)%></label>
	 </div>
	 </div>
            
                 
               
    <div class="form-group" style="margin-left: 10%;">
	<label align="left" class="control-label col-md-6 text-info">
	Last Name<span style="color: red;">*</span></label>
	 <div class="col-md-12" style="margin-bottom: 10px;">
	<div class="input-group">
	<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
	<input type="text" class="form-control" name="lastName" 
	value="<%=DataUtility.getStringData(dto.getLastName())%>">
	</div>
	<label class="control-label text-danger" for="inputError1">
	<%=ServletUtility.getErrorMessage("lastName", request)%></label>
	</div>
	</div>
												
   
        <div class="form-group" style="margin-left: 10%;">
		<label align="left" class="control-label col-md-6 text-info">
		Gender<span style="color: red;">*</span></label>

		 <div class="col-md-12" style="margin-bottom: 10px;">
		<div class="input-group">
		<span class="input-group-addon">
		<i class="glyphicon glyphicon-user" aria-hidden="true"></i></span>
		  <%
			  HashMap map = new HashMap();
			  map.put("Male", "Male");
			  map.put("Female", "Female");
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
		Mobile No.<span style="color: red;">*</span></label>

		 <div class="col-md-12" style="margin-bottom: 10px;">
		<div class="input-group">
		<span class="input-group-addon">
		<i class="glyphicon glyphicon-earphone"></i></span> 
		<input type="text" class="form-control" name="mobileNo" maxlength="10"
		value="<%=DataUtility.getStringData(dto.getMobileNo())%>">
		</div>
		<label class="control-label text-danger" for="inputError1">
		<%=ServletUtility.getErrorMessage("mobileNo", request)%></label>
		</div>
	    </div>
                
           
        <div class="form-group" style="margin-left: 10%;">
		<label class="control-label col-md-6 text-info">
		Date Of Birth<span style="color: red;">*</span></label>

		 <div class="col-md-12" style="margin-bottom: 10px;">
		<div class="input-group">
		<span class="input-group-addon">
		<i class="glyphicon glyphicon-calendar"></i></span> 
		<input type="text" class="form-control" name="dob"  id="datepicker" readonly="readonly"
		value="<%=DataUtility.getDateString(dto.getDob())%>">
		</div>
		<label class="control-label text-danger" for="inputError1">
		<%=ServletUtility.getErrorMessage("dob", request)%></label>
	    </div>
		</div>

		</div>
                
        
						
		<div class="form-group" align="center">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"  style="padding-left: 54px;">
		<br>
		<button type="submit" class="btn btn-primary" name="operation"
		value="<%=MyProfileCtl.OP_SAVE%>"><span class="glyphicon glyphicon-check"></span> Save</button>&emsp;
		<button type="submit" class="btn btn-warning" name="operation" style="width: 178px;"
		value="<%=MyProfileCtl.OP_CHANGE_MY_PASSWORD%>"><span class="glyphicon glyphicon-erase"></span> Change My Password</button>
		
		
					</div>
					</div>
					</div>
					</div>
				</div>
			</div>
			<br>
			</div>
          
            
    </form><br>
    
    <%@ include file="Footer.jsp"%>
</body>

</html>