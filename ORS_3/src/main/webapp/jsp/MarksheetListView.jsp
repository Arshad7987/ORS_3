<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head>
<%@page import="in.co.sunrays.proj3.controller.MarksheetMeritListCtl"%>
<%@page import="in.co.sunrays.proj3.util.DataUtility"%>
<%@page import="in.co.sunrays.proj3.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj3.dto.MarksheetDTO"%>
<%@page import="in.co.sunrays.proj3.model.ModelFactory"%>
<%@page import="in.co.sunrays.proj3.model.MarksheetModelInt"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<title>Marksheet List</title>
<!--    favicon-->
    <link rel="shortcut icon" href="../theam_wel/image/fav-icon.png" type="image/x-i">

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
<jsp:useBean id="marksheetdto" class="in.co.sunrays.proj3.dto.MarksheetDTO" scope="request"></jsp:useBean>
	<jsp:useBean id="model" class="in.co.sunrays.proj3.model.MarksheetModelHibImpl" scope="request"></jsp:useBean>
		
	<form action="<%=ORSView.MARKSHEET_LIST_CTL%>" method="post">
    
    <br>
        <div class="container">
        <div class="row">
        <div class="panel" style="background-color: rgba(248, 222, 210, 0.85); margin-bottom: 150px;" >
        <div class="panel-body">
        <div align="center">
         
         <H2>  <span class="glyphicon glyphicon-list"></span><b> Marksheet List</b> </H2>
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
	         <%List list=ServletUtility.getList(request);
			if( list.size()>0){%>
           <div class="form-inline" >
				<div class="form-group  text-center">
					<label class="control-label"  for="name">Student Name :</label>
					<input type="text"  class="form-control" name="name" size=15
					placeholder="Enter Student Name" value="<%=ServletUtility.getParameter("name", request)%>">
				</div>				&emsp;&emsp;
				
				<div class="form-group text-center">
					<label class="control-label" for="rollNo">Roll No. :</label>
					<input type="text" name="rollNo" class="form-control" 
					placeholder="Enter City" size=15 value="<%=ServletUtility.getParameter("rollNo", request)%>">
				</div>

				<div class="form-group">
				<button type="submit" name="operation" class=" form-control btn btn-primary" 
				 value="<%=MarksheetMeritListCtl.OP_SEARCH%>">
                <span class="glyphicon glyphicon-search"></span> Search </button>
       
			     <button type="submit" name="operation" class=" form-control btn btn-warning" 
			     value="<%=MarksheetMeritListCtl.OP_RESET%>" >
                 <span class="	glyphicon glyphicon-refresh"></span> Reset </button>
        
        </div>
        </div>
        <hr>
        
        <% if (userdto.getRoleId() == RoleDTO.ADMIN || userdto.getRoleId() == RoleDTO.FACULTY) { %>
        			<div style="float:right">
				 <div class="col-sm-3" style="margin-left: 4%;">
      <button type="submit" name="operation" value="<%=MarksheetMeritListCtl.OP_DELETE%>"
	  class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> 
	  <%=MarksheetMeritListCtl.OP_DELETE%> </button></div>
			</div>
			<div style="float:left">
				<div class="col-sm-3" >
    <button type="submit" name="operation" value="<%=MarksheetMeritListCtl.OP_NEW%>"
	class="btn btn-primary"> <span class="glyphicon glyphicon-plus"></span> 
	<%=MarksheetMeritListCtl.OP_NEW%> </button></div>
			</div>
			
        </div>			
	<%}%>		
				<br>
			<div class="box-body table-responsive">
					
              <table  class="table  table-bordered table-striped table-hover">
              <thead>
                   <tr>
					<th style="text-align:center;">
					 <input type="checkbox" id="mainbox"
						onchange="selectAll(this)"> Select All</th>
                     <th style="text-align: center;">S.No.</th>
                     <th style="text-align: center;">RollNo</th>
					<th style="text-align: center;">Name</th>
					<th style="text-align: center;">Physics</th>
					<th style="text-align: center;">Chemistry</th>
					<th style="text-align: center;">Maths</th>
					<th style="text-align: center;">Total</th>
					<th style="text-align: center;">Percentage (%)</th>
					<th style="text-align: center;">Grade</th>
					
					<th style="text-align: center;">Result</th>
                    <th style="text-align: center;">Edit</th>
                              
                   </tr>
                   </thead>
			        
                

                <%
                MarksheetModelInt s = ModelFactory.getInstance().getMarksheetModel();
				List l = s.list();
				int count = l.size();
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize);

                     list = ServletUtility.getList(request);
                    Iterator<MarksheetDTO> it = list.iterator();
                    while (it.hasNext()) {

						MarksheetDTO dto = it.next();
						String grade = null;
						String result = "<font color='green'>Pass</font>";
						String div="";
						long p = DataUtility.getLong(dto.getPhysics()+"");
						long c = DataUtility.getLong(dto.getChemistry()+"");
						long m = DataUtility.getLong(dto.getMaths()+"");
						long totalMarks = (p + c + m);
						
						float percentage=(float)totalMarks/3;
                        percentage=percentage*100;
                        percentage=Math.round(percentage);
                        percentage=percentage/100;
                       int avg=(int)(p+c+m)/3;
                        if(avg>=80&&!(p<=32||c<=32||m<=32))
                        {
                            grade="A+";
                        }
                        else if(avg>60 && avg<=80&&!(p<=32||c<=32||m<=32))
                        {
                        	grade="A";
                        }
                        else if(avg>40 && avg<=60&&!(p<=32||c<=32||m<=32))
                        {
                        	grade="B";
                        }
                        else if(avg<40&&!(p<=32||c<=32||m<=32))
                        {
                        		
                        	grade="C";
                        }else{
                        	grade="D";
                        	result = "<font color='red'>Fail</font>";
                        }
					
						
					
				%>
                
                <tbody>
                <tr>
					<td align="center"><input type="checkbox" name="ids" onchange="selectone(this)"
					 value="<%=dto.getId()%>"></td>
						<td align="center"><%=++index%></td>
					<td align="center"><%=dto.getRollNo()%></td>
					<td align="center"><%=dto.getName()%></td>
					<td align="center"><%=dto.getPhysics()<33?dto.getPhysics()+"<font color='red'>*</font>":dto.getPhysics()%></td>
					<td align="center"><%=dto.getChemistry()<33?dto.getChemistry()+"<font color='red'>*</font>":dto.getChemistry()%></td>
					<td align="center"><%=dto.getMaths()<33?dto.getMaths()+"<font color='red'>*</font>":dto.getMaths()%></td>
					<td align="center"><%=totalMarks%></td>
					<td align="center"><%=percentage%></td>
					<td align="center"><%=grade%></td>
					
					<td align="center"><%=result %></td>
					 
					
					<td style="size: 20%; text-align: center;"><%
						if (userdto.getId() == 2||userdto.getId() == 4) {
					%>
					---
					<%
						} else {
					%><a href="MarksheetCtl?id=<%=dto.getId()%>">
					<span class="glyphicon glyphicon-edit"></span></a>
					<%
						}
					%></td>
					
					
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
        <button type="submit" name="operation" value="<%=MarksheetMeritListCtl.OP_NEXT%>"<%=(index == count  || list.size() < pageSize) ? "disabled" : ""%>  class="btn btn-primary" 
        ><%=MarksheetMeritListCtl.OP_NEXT%> <span class="glyphicon glyphicon-chevron-right"></span> 
        </button></div>

			</div>
			
			<div style="float:left">
				 
  <div class="col-sm-4"">
  <button type="submit" name="operation"
	value="<%=MarksheetMeritListCtl.OP_PREVIOUS%>"<%=(pageNo == 1) ? "disabled" : ""%>   class="btn btn-primary">
	<span class="glyphicon glyphicon-chevron-left"></span> <%=MarksheetMeritListCtl.OP_PREVIOUS%> </button> 
	 </div>

			</div>
		</div><hr>	
			<%}else{ %>
			<table align="center">
				<tr>
					<td>
					 
					  <button type="submit" name="operation" class=" form-control btn btn-warning" 
			     value="<%=MarksheetMeritListCtl.OP_BACK%>" style=" width: 150px; height: 47px; font-size: 16px; background-color: gray;">
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