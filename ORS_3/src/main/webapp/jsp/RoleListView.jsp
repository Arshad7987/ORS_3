<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="in.co.sunrays.proj3.model.RoleModelInt"%>
<%@page import="in.co.sunrays.proj3.model.ModelFactory"%>
<%@page import="in.co.sunrays.proj3.controller.RoleListCtl"%>
<%@page import="in.co.sunrays.proj3.controller.BaseCtl"%>
<%@page import="in.co.sunrays.proj3.dto.RoleDTO"%>
<%@page import="in.co.sunrays.proj3.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

	<jsp:useBean id="dto" class="in.co.sunrays.proj3.dto.RoleDTO" scope="request"></jsp:useBean>
	<jsp:useBean id="model" class="in.co.sunrays.proj3.model.RoleModelHibImpl" scope="request"></jsp:useBean>
	

<html>
<head>
<title>Role List</title>
<!--    favicon-->
    <link rel="shortcut icon" href="theam_wel/image/fav-icon.png" type="image/x-i">
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
	.table-hover tbody tr:hover td
    {
        background-color: #0064ff36;
    }  
</style>
</head>

<body>

    <%@include file="Header.jsp"%>
	
	<form action="<%=ORSView.ROLE_LIST_CTL%>" method="post">
   
    <br>
        <div class="container">
        <div class="row">
        <div class="panel" style="background-color: #DCDCDC; margin-bottom: 150px;" >
        <div class="panel-body">
        <div align="center">
         
         <H2>  <span class="glyphicon glyphicon-list"></span><b> Role List</b> </H2>
         <hr style="height:2px; color:#000000;">
       </div>
       
        <div class="text-center" >
            
              
            
			
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
	
	<br><br><br>
	
	<div class="container-fluid text-center">
	 <% List   list = ServletUtility.getList(request);
    
    if(list.size()>0){%>
           <div class="form-inline" >
				<div class="form-group  text-center">
					<label class="control-label"  for="name"> Role Name :</label>
					<input type="text"  class="form-control" name="name" size=15
					placeholder="Enter Role Name" value="<%=ServletUtility.getParameter("name", request)%>">
				
				
				</div>
				
				&emsp;&emsp;
		
										
				<div class="form-group">
				<button type="submit" name="operation" class=" form-control btn btn-primary" 
				 value="<%=RoleListCtl.OP_SEARCH%>">
                <span class="glyphicon glyphicon-search"></span> Search </button>
       
			     <button type="submit" name="operation" class=" form-control btn btn-warning" 
			     value="<%=RoleListCtl.OP_RESET%>" >
                 <span class="	glyphicon glyphicon-refresh"></span> Reset </button>
        
        </div>
        </div><hr>
        
          <% if (userdto.getRoleId() == RoleDTO.ADMIN || userdto.getRoleId() == RoleDTO.FACULTY) { %>
        			<div style="float:right">
				 <div class="col-sm-3" style="margin-left: 4%;">
      <button type="submit" name="operation" value="<%=RoleListCtl.OP_DELETE%>"
	  class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> 
	  <%=RoleListCtl.OP_DELETE%> </button></div>
			</div>
			<div style="float:left">
				<div class="col-sm-3" >
    <button type="submit" name="operation" value="<%=RoleListCtl.OP_NEW%>"
	class="btn btn-primary"> <span class="glyphicon glyphicon-plus"></span> 
	<%=RoleListCtl.OP_NEW%> </button></div>
			</div>
			
        </div>			
		<%}%>		
				<br>
				
				
       
                		
		
			
		  
			
			
			<div class="box-body table-responsive">
					
                <table  class="table  table-bordered table-striped table-hover">
              <thead>
                   <tr>
					 <th align="left">
					 <input type="checkbox" id="mainbox" name="ids"
						onchange="selectAll(this)"> Select All</th>
                     <th style="text-align: center;">S.No.</th>
                     <th style="text-align: center;">Name</th>
                     <th style="text-align: center;">Description of Role</th>
                     <th style="text-align: center;">Edit</th>
                              
                   </tr>
                   </thead>
			        
                

                <%
                RoleModelInt s = ModelFactory.getInstance().getRoleModel();
				List l = s.list();
				int count = l.size();
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize);

                 
                    Iterator<RoleDTO> it = list.iterator();
                    while (it.hasNext()) {
                        dto = it.next();
                       
                %>
                
                <tbody>
                <tr> <%
						if (dto.getId() == 1) {
					%>
					<td align="left"><input type="checkbox" name="ids" 
					disabled="disabled" value="<%=dto.getId()%>"></td>
					<%
						} else {
					%>
					<td align="left"><input type="checkbox" name="ids" onchange="selectone(this)"
					value="<%=dto.getId()%>"></td>
					<%
						}
					%> 					
					<td align="center"><%=++index%></td>
					<td align="center"><%=dto.getName()%></td>
					<td align="center"><%=dto.getDescription()%></td>
					
					<td style="size: 20%; text-align: center;">
					<%-- <%
						if (roledto.getId() == 1) {
					%>
					---
					<%
						} else {
					%> --%>
					<a href="RoleCtl?id=<%=dto.getId()%>">
					<span class="glyphicon glyphicon-edit"></span></a>
					<%-- <%
						}
					%> --%>
					</td>
					
				
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> 
			<input type="hidden" name="pageSize" value="<%=pageSize%>">
				
				</tr>
				</tbody>
				<%
                    }
                %>
            	
			
			</table>
			</div>
		
		<div>
			
		<div style="float:right">
				
        <div class="col-sm-4"">
        <button type="submit" name="operation" value="<%=RoleListCtl.OP_NEXT%>"<%=(index == count || dto.getId()==0 || list.size() < pageSize) ? "disabled" : ""%>  class="btn btn-primary" 
        ><%=RoleListCtl.OP_NEXT%> <span class="glyphicon glyphicon-chevron-right"></span> 
        </button></div>

			</div>
			
			<div style="float:left">
				 
  <div class="col-sm-4"">
  <button type="submit" name="operation"
	value="<%=RoleListCtl.OP_PREVIOUS%>"<%=(pageNo == 1) ? "disabled" : ""%>   class="btn btn-primary">
	<span class="glyphicon glyphicon-chevron-left"></span> <%=RoleListCtl.OP_PREVIOUS%> </button> 
	 </div>

			</div>
		</div><hr>	
				<%}else{ %>
				<table align="center">
				<tr>
					<td>
					 
					  <button type="submit" name="operation" class=" form-control btn btn-warning" 
			     value="<%=RoleListCtl.OP_BACK%>" style=" width: 150px; height: 47px; font-size: 16px; background-color: gray;">
                 <span style="margin-right: 7px;" class="	glyphicon glyphicon-folder-open"></span>  Back </button>
                 
					</td>
			
				</tr>
			</table>
			<%} %>
             </form>
    
    <br>
    <%@include file="Footer.jsp"%>
</body>
</html>
