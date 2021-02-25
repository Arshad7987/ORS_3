<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="in.co.sunrays.proj3.controller.ORSView"%>
<%@page import="in.co.sunrays.proj3.controller.RoleCtl"%>
<%@page import="in.co.sunrays.proj3.controller.BaseCtl"%>
<%@page import="in.co.sunrays.proj3.util.DataUtility"%>
<%@page import="in.co.sunrays.proj3.util.ServletUtility"%>

<html>
<head>

<title>Role View</title>
 <!--    favicon-->
    <link rel="shortcut icon" href="theam_wel/image/fav-icon.png" type="image/x-icon">


<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
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
	
	<%@ include file="Header.jsp"%>
	<form action="<%=ORSView.ROLE_CTL%>" method="post">
	<jsp:useBean id="dto" class="in.co.sunrays.proj3.dto.RoleDTO"
			scope="request"></jsp:useBean>

	<div class="container-fluid">      
		<div class="row">
		<div class="col-xs-12 col-md-6 col-sm-12 col-lg-4 col-md-offset-4">
			<div class="panel panel-primary" style="margin-top:10px; background-color: #DCDCDC;">
				<div class="panel-heading" style="background-color:#e9967a00" align="center">
		
	<%
 	if (dto.getId() > 0) {
 %> <b><h1>Update Role</h1></b> <%
 	} else {
 %> <b><h1>Add Role</h1></b> <%
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
				Name<span style="color: red;">*</span></label>

				<div class="col-md-12" style="margin-bottom: 10px;">
				<div class="input-group">
				<span class="input-group-addon">
				<i class="glyphicon glyphicon-user"></i></span> 
				<input type="text" class="form-control" name="name"
				placeholder="Enter Role Name" value="<%=DataUtility.getStringData(dto.getName())%>"
				>
				</div>
				<label class="control-label text-danger"  for="inputError1">
				<%=ServletUtility.getErrorMessage("name", request)%></label>
				</div>
				</div> 

			
					
				<div class="form-group" style="margin-left: 10%;">
				<label align="left" class="control-label text-info col-md-6">
				Description<span style="color: red;">*</span></label>

				<div class="col-md-12" style="margin-bottom: 10px;">
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
		value="<%=RoleCtl.OP_UPDATE%>"><span class="glyphicon glyphicon-check"></span> Update</button>
		<button type="submit" class="btn btn-primary" name="operation"
		value="<%=RoleCtl.OP_CANCEL%>"><span class="glyphicon glyphicon-remove"></span> Cancel</button>
						<%
						} else {
					%><br>
		<button type="submit" class="btn btn-primary" name="operation"
		value="<%=RoleCtl.OP_SAVE%>"><span class="glyphicon glyphicon-check"></span> Save</button>&emsp;
		<button type="submit" class="btn btn-warning" name="operation"
		value="<%=RoleCtl.OP_RESET%>"><span class="glyphicon glyphicon-refresh"></span> Reset</button>
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
