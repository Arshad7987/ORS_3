<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@page import="in.co.sunrays.proj3.dto.CollegeDTO"%>
<%@page import="in.co.sunrays.proj3.controller.StudentCtl"%>
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.proj3.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.sunrays.proj3.util.DataUtility"%>
<%@page import="in.co.sunrays.proj3.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj3.dto.StudentDTO"%>
<%@page import="java.util.Iterator"%>
<%@ include file="Header.jsp"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Student View</title>

<!--    favicon-->
<link rel="shortcut icon"
	href="/ORSProject3/theam_wel/image/fav-icon.png" type="image/x-icon">


<style type="text/css">
body {
	background-image: url("../image/bg_theam.jpg");
	background-size: cover;
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-position: center;
}

label {
	position: absolute;
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
		yearRange:"-28:-18",
		defaultDate : "01/01/2000",
	});
});
</script>

</head>

<body>
	<form action="<%=ORSView.STUDENT_CTL%>" method="post">

		<jsp:useBean id="dto" class="in.co.sunrays.proj3.dto.StudentDTO"
			scope="request"></jsp:useBean>

		<%
			List l = (List) request.getAttribute("collegeList");
		%>

		<div class="container-fluid">
		<div class="row">
		<div class="col-xs-12 col-md-6 col-sm-12 col-lg-4 col-md-offset-4">
			<div class="panel panel-primary" style="margin-top:10px; background-color: #DCDCDC;">
				<div class="panel-heading" style="background-color:#e9967a00;" align="center">
		
		<%
 	if (dto.getId() > 0) {
 %> <b><h1>Update Student</h1></b> <%
 	} else {
 %> <b><h1>Add Student</h1></b> <%
 	}
 %>
		
		</div>
		
		<div class="panel-body">
			<div align="center">
			  <%if(!ServletUtility.getSuccessMessage(request).equals("")){ %>
			<div class="alert alert-success alert-dismissible ">
                          <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                          <strong> <%=ServletUtility.getSuccessMessage(request)%></strong>
                          </div>
	      <%} if(!ServletUtility.getErrorMessage(request).equals("")){%>
			<div class="alert alert-danger alert-dismissible ">
                          <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                          <strong> <%=ServletUtility.getErrorMessage(request)%></strong>
                          </div>
				<%} %>
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
		First Name<span style="color: red;">*</span></label>

	 <div class="col-md-12"  style="margin-bottom: 25px;">
	 <div class="input-group">
	 <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
	 <input type="text" class="form-control" name="firstName" placeholder="Enter First Name"
	 value="<%=DataUtility.getStringData(dto.getFirstName())%>">
	 </div>
	 <label class="control-label text-danger"  for="inputError1">
	 <%=ServletUtility.getErrorMessage("firstName", request)%></label>
	 </div>
	 </div> 
    
					
                    
    <div class="form-group" style="margin-left: 10%;">
	<label align="left" class="control-label col-md-6 text-info">
	Last Name<span style="color: red;">*</span></label>
	<div class="col-md-12"  style="margin-bottom: 25px;">
	<div class="input-group">
	<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
	<input type="text" class="form-control" name="lastName" placeholder="Enter Last Name"
	value="<%=DataUtility.getStringData(dto.getLastName())%>">
	</div>
	<label class="control-label text-danger" for="inputError1">
	<%=ServletUtility.getErrorMessage("lastName", request)%></label>
	</div>
	</div>
												

                    
     <div class="form-group" style="margin-left: 10%;">
	 <label class="control-label col-md-6 text-info">
	 Login Id<span style="color: red;">*</span></label>

     <div class="col-md-12"  style="margin-bottom: 25px;">
	 <div class="input-group">
	 <span class="input-group-addon">
	 <i class="glyphicon glyphicon-log-in"></i></span> 
	 <input type="text" class="form-control" name="email" placeholder="Enter Email ID"
	 value="<%=DataUtility.getStringData(dto.getEmail())%>" <%=(dto.getId() > 0) ? "readonly" : ""%>>
	 </div>
	 <label class="control-label text-danger" for="inputError1">
	 <%=ServletUtility.getErrorMessage("email", request)%></label>
	 </div>
	 </div>
								
   
      
        <div class="form-group" style="margin-left: 10%;">
		<label align="left" class="control-label col-md-6 text-info">
		Mobile No.<span style="color: red;">*</span></label>

		<div class="col-md-12"  style="margin-bottom: 25px;">
		<div class="input-group">
		<span class="input-group-addon">
		<i class="glyphicon glyphicon-earphone"></i></span> 
		<input type="text" class="form-control" name="mobileNo" maxlength="10"
		placeholder="Enter Mobile Number" value="<%=DataUtility.getStringData(dto.getMobileNo())%>">
		</div>
		<label class="control-label text-danger" for="inputError1">
		<%=ServletUtility.getErrorMessage("mobileNo", request)%></label>
		</div>
	    </div>        
        
																				
 
                
        <div class="form-group" style="margin-left: 10%;">
		<label align="left" class="control-label col-md-6 text-info">
		College<span style="color: red;">*</span></label>

		<div class="col-md-12"  style="margin-bottom: 25px;">
		<div class="input-group">
		<span class="input-group-addon">
		<i class="fa fa-university"></i></span> 
		<%=HTMLUtility.getList("collegeId", String.valueOf(dto.getCollegeId()), l)%>
		</div>
		<label class="control-label text-danger" for="inputError1">
		<%=ServletUtility.getErrorMessage("collegeId", request)%></label>
		</div>
		</div>
					 
      
                
        <div class="form-group" style="margin-left: 10%;">
		<label class="control-label col-md-6 text-info">
		Date Of Birth<span style="color: red;">*</span></label>

		<div class="col-md-12"  style="margin-bottom: 25px;">
		<div class="input-group">
		<span class="input-group-addon">
		<i class="glyphicon glyphicon-calendar"></i></span> 
		<input type="text" class="form-control" name="dob"  id="datepicker" readonly="readonly"
		placeholder="Enter Date of Birth" value="<%=DataUtility.getDateString(dto.getDob())%>">
		</div>
		<label class="control-label text-danger" for="inputError1">
		<%=ServletUtility.getErrorMessage("dob", request)%></label>
	    </div>
		</div>
           
           </div>
           
      
           
		<div class="form-group" align="center">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<%if (dto.getId() > 0) { %><br>
        <button type="submit" class="btn btn-success" name="operation"
		value="<%=StudentCtl.OP_UPDATE%>"><span class="glyphicon glyphicon-check"></span> Update</button>
		<button type="submit" class="btn btn-primary" name="operation"
		value="<%=StudentCtl.OP_CANCEL%>"><span class="glyphicon glyphicon-remove"></span> Cancel</button>
						<%
						} else {
					%><br>
		<button type="submit" class="btn btn-primary" name="operation"
		value="<%=StudentCtl.OP_SAVE%>"><span class="glyphicon glyphicon-check"></span> Save</button>&emsp;
		<button type="submit" class="btn btn-warning" name="operation"
		value="<%=StudentCtl.OP_RESET%>"><span class="glyphicon glyphicon-refresh"></span> Reset</button>
		<%}%>
					
					
					</div>
					</div>
					</div>
					</div>
				</div>
			</div>
		<br><br>
			</div>
	
    </form>
    
   	<div style="min-height: 200px">
    <%@ include file="Footer.jsp"%>
</body>
</html>