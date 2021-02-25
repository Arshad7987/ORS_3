<%@page import="in.co.sunrays.proj3.dto.RoleDTO"%>
<%-- <%@page import="in.co.sunrays.proj3.ctl.LoginCtl"%> --%>
<%@page import="in.co.sunrays.proj3.dto.UserDTO"%>
<%@page errorPage="ErrorView.jsp"%>
<%@page import="in.co.sunrays.proj3.controller.ORSView"%>
<!DOCTYPE html>
<html>
 <title></title>
   <!-- favicon -->
    <link rel="shortcut icon" href="../theam_wel/image/fav-icon.png" type="image/x-icon">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- for Jquery Calander  -->
<!-- <link rel="stylesheet" href="/ORSProject3/css/jquery-ui.css"> -->
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.min.js"></script>

<link rel="stylesheet" href="js/bootstrap.min.css">
<link rel="stylesheet" href="js/font-awesome.min.css">


<!-- bootstrap -->
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">



<!-- bootstrap end -->
<!-- <link rel="stylesheet" href="css/jquery-ui.css"> -->
<script src="js/jquery.min.js"></script>
<!-- <script src="js/jquery-1.12.4.js"></script>
<script src="js/jquery-ui.js"></script> -->


<!-- datepicker files -->
 <link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<style type="text/css">
#dcolor {
	font-size: 110%;
	color: #ffffff;
}.glyphicon-refresh:hover{
animation: fa-spin 1s infinite linear;
}

.btn-primary {
	height: 35px;
	width: 90px;
	background: lightseagreen;
	box-shadow: 0 0 1px #ccc;
	-webkit-transition: all 0.5s ease-in-out;
	border: 0px;
	color: #fff;
	border-color: none;
}

.btn-primary:hover {
	-webkit-transform: scale(1.1);
	background: #31708f;
}

.btn-success {
	height: 35px;
	width: 90px;
	box-shadow: 0 0 1px #ccc;
	-webkit-transition: all 0.5s ease-in-out;
	border: 0px;
	color: #fff;
	border-color: none;
}

.btn-success:hover {
	-webkit-transform: scale(1.1);
	background: green;
}

.btn-info {
	height: 35px;
	width: 90px;
	box-shadow: 0 0 1px #ccc;
	-webkit-transition: all 0.5s ease-in-out;
	border: 0px;
	color: #fff;
	border-color: none;
}

.btn-info:hover {
	-webkit-transform: scale(1.1);
	background: #33d6ff;
}

.btn-warning {
	height: 35px;
	width: 90px;
	box-shadow: 0 0 1px #ccc;
	-webkit-transition: all 0.5s ease-in-out;
	border: 0px;
	color: #fff;
	border-color: none;
}
.btn-warning:hover {
	-webkit-transform: scale(1.1);
	background: #ff9933;
}

.btn-danger {
	height: 35px;
	width: 90px;
	box-shadow: 0 0 1px #ccc;
	-webkit-transition: all 0.5s ease-in-out;
	border: 0px;
	color: white;
	border-color: none;
}

.btn-danger:hover {
	-webkit-transform: scale(1.1);
	background: #ff3333;
}

.panel {
	box-shadow: 9px 8px 7px #001a33;
	background: rgba(10, 10, 10, 0.44);
}

.text-primary {
	color: blue;
}

.text-danger {
	margin-left: 50px;
	color: #ff0000;
}

.table {
	bordercolor: blue;
	background-color: #9999ff;
}

.navbar-inverse {
	/*  background-color: #7986CB ; */
	border-color: black;
	color: white;
	/*  background-image: url("img/images (2)navar00.jpg");  */
	background: #001a33;
	background-repeat:;
}

element.style {
	color: #000;
}

body {
	padding-top: 110px;
}

#top {
	margin-bottom: 100px;
}

.glyphicon-edit {
	color: #800000;
}

.panel>.panel-heading {
	background-position: center;
	font-size: 30px;
	background: rgba(10, 10, 10, 0.44);
	color: #993333;
	font-family: cursive;
	/* margin-right:30px; */
	/* 	background: rgba(10, 10, 10, 0.44);
 */
	background-size: appworkspace;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
}

.dropdown-menu {
	background-color: #dedfe2;
}

.list-heading {
	background-color: #060613;
}

#error {
	font-size: 20px;
	position: center;
	color: red;
}

#success {
	font-size: 20px;
	position: center;
	color: green;
}
</style>

<script type="text/javascript">
	function selectAll(source) {
		checkboxes = document.getElementsByName('ids');

		for (var i = 0, n = checkboxes.length; i < n; i++) {
			checkboxes[i].checked = source.checked;
		}
	}

	$(document).ready(function() {
		$('[name="ids"]').click(function() {
			if (!($(this)[0].checked)) {
				$('[onclick="selectAll(this)"]')[0].checked = false;
			}
			;

		});

	});
</script>


<script type="text/javascript">
	$(function() {
		$(".dropdown").hover(function() {
			$('.dropdown-menu', this).stop(true, true).fadeIn("fast");
			$(this).toggleClass('open');
		}, function() {
			$('.dropdown-menu', this).stop(true, true).fadeOut("fast");
			$(this).toggleClass('open');
		});
	});
	
	
	
	
</script>

<script>
	
	

	/* $(function(){
		$("#datefac").datepicker({
			 beforeShowDay:
				function(dt){
				return[dt.getDay()==0 ? false:true]     ///// to disable sunday
			}, 
			changeMonth:true,
			changeYear:true,
			yearRange:"-57:+0",	
			defaultDate:"01/01/2018"
			//defaultDate:"01/01/1999"
		});	
	}); 
	 */
    function selectAll(source) {
    	checkboxes = document.getElementsByName('ids');
    	for (var i = 0, n = checkboxes.length; i < n; i++) {
    		checkboxes[i].checked = source.checked;
    	}
    }
    
    function selectone(so) {
    	checkboxes = document.getElementById('mainbox');
    	unbox = document.getElementsByName('ids');
    	var box = false;
    	for (var i = 0, n = unbox.length; i < n; i++) {
    		if (unbox[i].checked == true) {
    			box = true;
    		} else {
    			box = false;
    			break;
    		}
    	}
    	checkboxes.checked = box;
    }
    
</script>

</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top" style="background-color: rgba(28, 35, 49, 0.6);">
		<div class="container-fluid">
			<ul class="nav navbar-nav navbar-left" style="background-color: #3030358f">
			<%UserDTO userdto = (UserDTO) session.getAttribute("user");
			
			%>
				<li><img src="/ORSProject03/img/download.png" width="130" height="52" class="img-rounded"></li>
				
				
			</ul>

			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<%=ORSView.WELCOME_CTL%>"  id="dcolor"
					>&emsp;<span class="glyphicon glyphicon-home" style=" padding-right: 10px"></span>
				</a>

			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">

				<ul class="nav navbar-nav navbar-right" style="background:">

					<%
						
						if (userdto == null) {
					%>
					<li><a href="<%=ORSView.LOGIN_CTL%>" id="dcolor"
						onclick="document.getElementById('id01').style.display='block'"
						style="width: auto; font-family: cursive; color: #e0ebeb;"><span class="glyphicon glyphicon-log-in" style="font-size:20px;margin-right: 8px;"></span> Login</a></li>
					<li><a href="<%=ORSView.USER_REGISTRATION_CTL%>" id="dcolor">
							<font style="font-family: cursive; color: #e0ebeb"> <span class="glyphicon glyphicon-user" style="font-size:20px;margin-right: 10px;"></span>Register
						</font>
					</a></li>

					<%
						} else {
					%>

					<li class="dropdown"><a  id="dcolor"
						class="dropdown-toggle" data-toggle="dropdown 
						aria-haspopup="true" aria-expanded="false"><span
							aria-hidden="true"></span><b> <span class="glyphicon glyphicon-user" aria-hidden="true" 
        		style="font-size:18px;margin-right: 3px;"></span><%
 						if (userdto != null) {
 						%> Hello, <%=userdto.getFirstName()%>(<%=session.getAttribute("role")%>)<span
								class="caret"></span> <%
 						}
 						%>
						</b></a>
						<ul class="dropdown-menu" style="background-color: wheat">
							<li><a href="<%=ORSView.MY_PROFILE_CTL%>"><span
									class="glyphicon glyphicon-edit" aria-hidden="true"></span> <font
									style="font-family: cursive;"><b>Edit Profile</b></font></a></li>
							<li><a href="<%=ORSView.CHANGE_PASSWORD_CTL%>"> <span
									class="glyphicon glyphicon-cog" style="color: #0f0f0a"></span> <font
									style="font-family: cursive;"><b>Change Password</b></font>
							</a></li>
							<li><a href="<%=ORSView.JAVA_DOC_VIEW%>" target="blank"><span
									class="glyphicon glyphicon-file" aria-hidden="true"
									style="color: #734d26"></span> <font
									style="font-family: cursive;"><b>Java Doc</b></font></a></li>
							<li><a href="<%=ORSView.LOGIN_CTL%>?operation=logout"><font
									style="font-family: cursive; color: #800000;"><span
										class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
										<b>Logout</b></font></a></li>
							<%
								}
							%>
						</ul></li>
				</ul>
				<%
					UserDTO user1 = (UserDTO) session.getAttribute("user");
					if (user1 != null) {
						if (userdto.getRoleId() == 1) {
				%>

				<ul class="nav navbar-nav ">
					<li></li>


					<li class="dropdown"><a class="dropdown-toggle" id="dcolor"
						data-toggle="dropdown" href="#">User<span class="caret"></span></a>
						<ul class="dropdown-menu" style="background-color: wheat">
							<li><a href="<%=ORSView.USER_CTL%>"><span
									class="glyphicon glyphicon-user" style="color: #990000"></span> <font
									style="font-family: cursive;"><b>Add User</b></font></a></li>
							<li><a href="<%=ORSView.USER_LIST_CTL%>"><span
									class="glyphicon glyphicon-list-alt" style="color: #734d26"></span><font
									style="font-family: cursive;"> <b>User List</b></font></a></li>
						</ul>
					<li class="dropdown"><a class="dropdown-toggle" id="dcolor"
						data-toggle="dropdown" href="#">Role<span class="caret"></span></a>
						<ul class="dropdown-menu" style="background-color: wheat">
							<li><a href="<%=ORSView.ROLE_CTL%>"><span
									class="glyphicon glyphicon-education" style="color: #993366"></span><font
									style="font-family: cursive;"> <b>Add Role</b></font></a></li>
							<li><a href="<%=ORSView.ROLE_LIST_CTL%>"><span
									class="glyphicon glyphicon-list-alt" style="color: #734d26"></span>
									<font style="font-family: cursive;"><b>Role List</b></font></a></li>
						</ul>
					<li class="dropdown"><a class="dropdown-toggle" id="dcolor"
						data-toggle="dropdown" href="#">Student<span class="caret"></span></a>
						<ul class="dropdown-menu" style="background-color: wheat">
							<li><a href="<%=ORSView.STUDENT_CTL%>"><span
									class="glyphicon glyphicon-education" style="color: #993366"></span><font
									style="font-family: cursive;"><b> Add Student</b></font></a></li>
							<li><a href="<%=ORSView.STUDENT_LIST_CTL%>"><span
									class="glyphicon glyphicon-list-alt" style="color: #734d26"></span>
									<font style="font-family: cursive;"><b>Student List</b></font></a></li>
						</ul>
					<li class="dropdown"><a class="dropdown-toggle" id="dcolor"
						data-toggle="dropdown" href="#">College<span class="caret"></span></a>
						<ul class="dropdown-menu" style="background-color: wheat">
							<li><a href="<%=ORSView.COLLEGE_CTL%>"> <span
									class="glyphicon glyphicon-education" style="color: #993366"></span><font
									style="font-family: cursive;"> <b>Add College</b></font>
							</a></li>
							<li><a href="<%=ORSView.COLLEGE_LIST_CTL%>"><span
									class="glyphicon glyphicon-list-alt" style="color: #734d26"></span><font
									style="font-family: cursive;"> <b>College List</b></font></a></li>
						</ul>
					<li class="dropdown"><a class="dropdown-toggle" id="dcolor"
						data-toggle="dropdown" href="#">Marksheet <span class="caret"></span></a>
						<ul class="dropdown-menu" style="background-color: wheat">
							<li><a href="<%=ORSView.GET_MARKSHEET_CTL%>"><span
									class="glyphicon glyphicon-education" style="color: #993366"></span><font
									style="font-family: cursive;"><b> Get Marksheet</b></font></a></li>
							<li><a href="<%=ORSView.MARKSHEET_CTL%>"><span
									class="glyphicon glyphicon-list-alt" style="color: #734d26"></span><font
									style="font-family: cursive;"> <b>Add Marksheet</b></font></a></li>
							<li><a href="<%=ORSView.MARKSHEET_LIST_CTL%>"><span
									class="glyphicon glyphicon-list-alt" style="color: #734d26"></span><font
									style="font-family: cursive;"><b> Marksheet List</b></font></a></li>
							<li><a href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"><span
									class="glyphicon glyphicon-list-alt" style="color: #734d26"></span><font
									style="font-family: cursive;"> <b>Marksheet Merit
											List</b></font> </a></li>


						</ul></li>
					<li class="dropdown"><a class="dropdown-toggle" id="dcolor"
						data-toggle="dropdown" href="#">Faculty<span class="caret"></span></a>
						<ul class="dropdown-menu" style="background-color: wheat">
							<li><a href=<%=ORSView.FACULTY_CTL%>><span
									class="glyphicon glyphicon-education" style="color: #993366"></span><font
									style="font-family: cursive;"><b> Add Faculty</b></font></a></li>
							<li><a href="<%=ORSView.FACULTY_LIST_CTL%>"><span
									class="glyphicon glyphicon-list-alt" style="color: #734d26"></span>
									<font style="font-family: cursive;"><b>Faculty List</b></font></a></li>
						</ul>
					<li class="dropdown"><a class="dropdown-toggle" id="dcolor"
						data-toggle="dropdown" href="#">Course<span class="caret"></span></a>
						<ul class="dropdown-menu" style="background-color: wheat">
							<li><a href="<%=ORSView.COURSE_CTL%>"><span
									class="glyphicon glyphicon-education" style="color: #993366"></span>
									<font style="font-family: cursive;"><b>Add Course</b></font></a></li>
							<li><a href="<%=ORSView.COURSE_LIST_CTL%>"><span
									class="glyphicon glyphicon-list-alt" style="color: #734d26"></span><font
									style="font-family: cursive;"> <b>Course List</b></font></a></li>
						</ul>
					<li class="dropdown"><a class="dropdown-toggle" id="dcolor"
						data-toggle="dropdown" href="#">Subject<span class="caret"></span></a>
						<ul class="dropdown-menu" style="background-color: wheat">
							<li><a href="<%=ORSView.SUBJECT_CTL%>"><span
									class="glyphicon glyphicon-education" style="color: #993366"></span><font
									style="font-family: cursive;"><b> Add Subject</b></font></a></li>
							<li><a href="<%=ORSView.SUBJECT_LIST_CTL%>"><span
									class="glyphicon glyphicon-list-alt" style="color: #734d26"></span>
									<font style="font-family: cursive;"><b>Subject List</b></font></a></li>
						</ul>
					<li class="dropdown"><a class="dropdown-toggle" id="dcolor"
						data-toggle="dropdown" href="#">Time Table<span class="caret"></span></a>
						<ul class="dropdown-menu" style="background-color: wheat">
							<li><a href="<%=ORSView.TIMETABLE_CTL%>"><span
									class="glyphicon glyphicon-education" style="color: #993366"></span><font
									style="font-family: cursive;"><b> Add Time Table</b></font></a></li>
							<li><a href="<%=ORSView.TIMETABLE_LIST_CTL%>"><span
									class="glyphicon glyphicon-list-alt" style="color: #734d26"></span>
									<font style="font-family: cursive;"><b>Time Table List</b></font></a></li>
						</ul></li></ul>
						</div> <%
 	}
 		if (userdto.getRoleId() == 2) {
 %><ul class="nav navbar-nav ">
							<li class="dropdown">
							<li class="dropdown"><a class="dropdown-toggle" id="dcolor"
								data-toggle="dropdown" href="#">Course<span class="caret"></span></a>
								<ul class="dropdown-menu" style="background-color: wheat">
									<li><a href="<%=ORSView.COURSE_LIST_CTL%>"><font
											style="font-family: cursive;"><b>Course List</b></font></a></li>
								</ul>
							<li class="dropdown"><a class="dropdown-toggle" id="dcolor"
								data-toggle="dropdown" href="#">Subject<span class="caret"></span></a>
								<ul class="dropdown-menu" style="background-color: wheat">
									<li><a href="<%=ORSView.SUBJECT_LIST_CTL%>"><font
											style="font-family: cursive;"><b>Subject List</b></font></a></li>
								</ul>
							<li class="dropdown"><a class="dropdown-toggle" id="dcolor"
								data-toggle="dropdown" href="#">TimeTable<span class="caret"></span></a>
								<ul class="dropdown-menu" style="background-color: wheat">
									<li><a href="<%=ORSView.TIMETABLE_LIST_CTL%>"><font
											style="font-family: cursive;"><b>TimeTable List</b></font></a></li>
								</ul></li></ul> <%
 	}
 		if (userdto.getRoleId() == 5) {
 %>
								<ul class="nav navbar-nav ">

									<li class="dropdown"><a class="dropdown-toggle"
										data-toggle="dropdown" href="#">Marksheet <span
											class="caret"></span></a>
										<ul class="dropdown-menu" style="background-color: wheat">
											<li><a href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>">Marksheet
													Merit List</a></li>
											<li><a href="<%=ORSView.GET_MARKSHEET_CTL%>">Get
													Marksheet</a></li>
											<li><a href="<%=ORSView.MARKSHEET_LIST_CTL%>">Marksheet
													List</a></li>
										</ul></li>

									<li class="dropdown"><a class="dropdown-toggle"
										id="dcolor" data-toggle="dropdown" href="#">Course<span
											class="caret"></span></a>
										<ul class="dropdown-menu" style="background-color: wheat">
											<li><a href="<%=ORSView.COURSE_LIST_CTL%>">Course
													List</a></li>
										</ul>
									<li class="dropdown"><a class="dropdown-toggle"
										id="dcolor" data-toggle="dropdown" href="#">Subject<span
											class="caret"></span></a>
										<ul class="dropdown-menu" style="background-color: wheat">
											<li><a href="<%=ORSView.SUBJECT_LIST_CTL%>">Subject
													List</a></li>
										</ul></li></ul> <%
 	}
 	}
 %>
			</div>

				
	</nav>
</body>
</html>