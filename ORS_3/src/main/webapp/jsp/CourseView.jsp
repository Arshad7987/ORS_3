<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="in.co.sunrays.proj3.controller.CourseCtl"%>
<%@page import="in.co.sunrays.proj3.util.DataUtility"%>
<%@page import="in.co.sunrays.proj3.util.ServletUtility"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.proj3.controller.ORSView"%>
<%@page import="in.co.sunrays.proj3.dto.CourseDTO"%>
<%@page import="in.co.sunrays.proj3.util.HTMLUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@include file="Header.jsp"%>

<html>
<head>
<title>Course View</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
</head>

<body>
	<form action="<%=ORSView.COURSE_CTL%>" method="post">

		<jsp:useBean id="dto" class="in.co.sunrays.proj3.dto.CourseDTO"
			scope="request"></jsp:useBean>
			
			<%
				List l = (List) request.getAttribute("courseList");
			%>

<div class="container-fluid">      
		<div class="row">
		<div class="col-xs-12 col-md-6 col-sm-12 col-lg-4 col-md-offset-4">
			<div class="panel panel-primary" style="margin-top:10px; background-color: #DCDCDC;">
				<div class="panel-heading" style="background-color:#e9967a00;" align="center">
		
	<%
 	if (dto.getId() > 0) {
 %> <b><h1>Update Course</h1></b> <%
 	} else {
 %> <b><h1>Add Course</h1></b> <%
 	}
 %>
		</div>
		
		<div class="panel-body">


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
		<br>
		
		<input type="hidden" name="id" value="<%=dto.getId()%>"> 
		<input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
		<input type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>"> 
		<input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
		<input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">


		<div class="col-xs-12 col-sm-12 col-md-10 col-lg-12">
						
						
				<div class="form-group" style="margin-left: 10%;">
				<label align="left" class="control-label text-info col-md-6">
				Course Name<span style="color: red;">*</span></label>

				 <div class="col-md-12" style="margin-bottom: 25px;">
				<div class="input-group">
				<span class="input-group-addon">
				<i class="	glyphicon glyphicon-book"></i></span> 
				<input type="text" class="form-control" name="name"
				placeholder="Enter Course Name" value="<%=DataUtility.getStringData(dto.getName())%>"
				>
				</div>
				<label class="control-label text-danger"  for="inputError1">
				<%=ServletUtility.getErrorMessage("name", request)%></label>
				</div>
				</div> 

					<div class="form-group" style="margin-left: 10%;">
										<label align="left" class="control-label col-md-6 text-info">
											Duration<span style="color: red;">*</span>
										</label>

										<div class="col-md-12" style="margin-bottom: 20px;">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-hourglass" aria-hidden="true"></i></span>
												<%
													HashMap map = new HashMap();
													map.put("2 Years", "2 Years");
													map.put("3 Years", "3 Years");
													map.put("4 Years", "4 Years");
													
													String htmlList = HTMLUtility.getList("duration", dto.getDuration(), map);
												%>
												<%=htmlList%>
											</div>
											<label class="control-label text-danger error_msg" for="inputError1">
												<%=ServletUtility.getErrorMessage("duration", request)%></label>
										</div>
									</div>

				<div class="form-group" style="margin-left: 10%;">
				<label align="left" class="control-label text-info col-md-6">
				Description<span style="color: red;">*</span></label>

				 <div class="col-md-12" style="margin-bottom: 20px;">
				<div class="input-group">
				<span class="input-group-addon">
				<i class="glyphicon glyphicon-align-justify"></i></span> 
				<input type="text" class="form-control" name="description"
				placeholder="Description Of Role" value="<%=DataUtility.getStringData(dto.getDescription())%>">
				</div>
				<label class="control-label text-danger"  for="inputError1">
				<%=ServletUtility.getErrorMessage("description", request)%></label>
				</div>
				</div> 

				</div>
				
					
				
		<div class="form-group" align="center">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<%if (dto.getId() > 0) { %><br>
        <button type="submit" class="btn btn-success" name="operation"
		value="<%=CourseCtl.OP_UPDATE%>"><span class="glyphicon glyphicon-check"></span> Update</button>
		<button type="submit" class="btn btn-primary" name="operation"
		value="<%=CourseCtl.OP_CANCEL%>"><span class="glyphicon glyphicon-remove"></span> Cancel</button>
						<%
						} else {
					%><br>
		<button type="submit" class="btn btn-primary" name="operation"
		value="<%=CourseCtl.OP_SAVE%>"><span class="glyphicon glyphicon-check"></span> Save</button>&emsp;
		<button type="submit" class="btn btn-warning" name="operation"
		value="<%=CourseCtl.OP_RESET%>"><span class="glyphicon glyphicon-refresh"></span> Reset</button>
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
	<br><br>

	<%@ include file="Footer.jsp"%>
</body>
</html>