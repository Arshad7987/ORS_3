<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<%@page import="in.co.sunrays.proj3.controller.MarksheetMeritListCtl"%>
<%@page import="in.co.sunrays.proj3.controller.MarksheetMeritListCtl"%>
<%@page import="in.co.sunrays.proj3.util.DataUtility"%>
<%@page import="in.co.sunrays.proj3.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj3.dto.MarksheetDTO"%>
<%@page import="in.co.sunrays.proj3.model.ModelFactory"%>
<%@page import="in.co.sunrays.proj3.model.MarksheetModelInt"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

	<jsp:useBean id="marksheetdto" class="in.co.sunrays.proj3.dto.MarksheetDTO" scope="request"></jsp:useBean>
	<jsp:useBean id="model" class="in.co.sunrays.proj3.model.MarksheetModelHibImpl" scope="request"></jsp:useBean>
	

<title>Marksheet Merit List</title>
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
	
	<form action="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>" method="post">
    
    <br>
        <div class="container">
        <div class="row">
        <div class="panel" style="background-color:  rgba(248, 222, 210, 0.85); margin-bottom: 150px;" >
        <div class="panel-body">
        <div align="center">
         
         <H2>  <span class="glyphicon glyphicon-list"></span><b> Marksheet Merit List (Top Ten Student)</b> </H2>
         <hr style="height:2px; color:#000000;">
       </div>
       
		<br>
				
				            		
		<%List list=ServletUtility.getList(request);
			if(list==null || list.size()==0){%>
				<table align="center">
				<tr>
					<td>
					 
					  <button type="submit" name="operation" class=" form-control btn btn-warning" 
			     value="<%=MarksheetMeritListCtl.OP_BACK%>" style=" width: 150px; height: 47px; font-size: 16px; background-color: gray;">
                 <span style="margin-right: 7px;" class="	glyphicon glyphicon-folder-open"></span>  Back </button>
                 
					</td>
			
				</tr>
			</table>
			
			<%}else{ %>  
			
			
			<div class="box-body table-responsive">
					
                <table  class="table  table-bordered table-striped table-hover">
              <thead>
                   <tr>
                     <th style="text-align: center;">S.No.</th>
                     <th style="text-align: center;">RollNo</th>
					<th style="text-align: center;">Name</th>
					<th style="text-align: center;">Physics</th>
					<th style="text-align: center;">Chemistry</th>
					<th style="text-align: center;">Maths</th>
					<th style="text-align: center;">Total</th>
					<th style="text-align: center;">Percentage (%)</th>
					<th style="text-align: center;">Grade</th>
					<th style="text-align: center;">Division</th>
					<th style="text-align: center;">Result</th>
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
						String result = null;
						long phyMarks = DataUtility.getLong(dto.getPhysics()+"");
						long chemMarks = DataUtility.getLong(dto.getChemistry()+"");
						long MathMarks = DataUtility.getLong(dto.getMaths()+"");
						long totalMarks = (phyMarks + chemMarks + MathMarks);
						
						float percentage=(float)totalMarks/3;
                        percentage=percentage*100;
                        percentage=Math.round(percentage);
                        percentage=percentage/100;
						
						if (phyMarks >= 35 && chemMarks >= 35 && MathMarks >= 35) {
							if (totalMarks >= 195) {
								grade = "A";
								result = "Pass";
							} else if (totalMarks < 195 && totalMarks >= 150) {
								grade = "B";
								result = "Pass";
							} else {
								grade = "C";
								result = "Pass";
							}

						} else {
							grade = "D";
							result = "Fail";
						}
						
						String div = null;
						if (percentage >= 60) {
							div = "First";
						} else if (percentage >= 45 && percentage < 60) {
							div = "Second";
						} else if (percentage >= 33 && percentage < 45) {
							div = "Third";
						}else if (percentage <33){
							div="Fail";
						}
				%>
                
                <tbody>
                <tr>
					<td align="center"><%=++index%></td>
					<td align="center"><%=dto.getRollNo()%></td>
					<td align="center"><%=dto.getName()%></td>
					<td align="center"><%=dto.getPhysics()%></td>
					<td align="center"><%=dto.getChemistry()%></td>
					<td align="center"><%=dto.getMaths()%></td>
					<td align="center"><%=totalMarks%></td>
					<td align="center"><%=percentage%></td>
					<td align="center"><%=grade%></td>
					<td align="center"><%=div%></td>
					<%
						if (div.equalsIgnoreCase("Fail")) {
					%>
						<td align="center" style="color: red;">FAIL</td>
					<%
						} else {
					%>
						<td align="center"style="color: green;">PASS</td>
					<%
						}
					%>
					
					
					
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
			
		<div class="row">
			
			<div class="col-sm-3" style="margin-left: 2%;"></div>
			
			<div class="col-sm-3" align="right">
			<a style="font-size: 16px;" href="/ORSProject03/ctl/JasperCtl" class="btn btn-primary" role="button" target = "blank">
			<span style="padding-right: 6px;" class="glyphicon glyphicon-print"></span>Print</a>
   			</div>
		
		<div class="col-sm-3" align="left" >
   		<button  style="font-size: 16px;" type="submit" name="operation" value="<%=MarksheetMeritListCtl.OP_BACK%>"
		class="btn btn-warning"> <span style="padding-right: 6px;" class="glyphicon glyphicon-arrow-left"></span> 
		<%=MarksheetMeritListCtl.OP_BACK%> </button></div>
		
	<div style=" margin-top: -1px;"></div>
		
		</div>
		
			
			<hr>	
			
			<%
                    }
                %>
             </form>
    
    <br>
    <%@include file="Footer.jsp"%>
</body>

</html>