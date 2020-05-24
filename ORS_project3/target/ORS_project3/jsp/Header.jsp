<%@page import="in.co.rays.ctl.LoginCtl"%>
<%@page import="in.co.rays.dto.RoleDTO"%>
<%@page import="in.co.rays.dto.UserDTO"%>
<%@page import="in.co.rays.ctl.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title></title>
  <meta charset="utf-8">
 
<link
	href="http://netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<!-- CSS Files -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
  
   
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <!-- <link rel="stylesheet" href="/resources/demos/style.css">  -->
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  

<script type="text/javascript">	
$( function() {  
	  $( "#datepicker" ).datepicker({
		  changeMonth :true,
		  changeYear :true,
		  yearRange :'1980:2030',
		  dateFormat:'dd/mm/yy'
	  });
	  
	  
  } );
  
  
  
var d = new Date();
function disableSunday(d){
	  var day = d.getDay();
	  if(day==0)
	  {
	   return [false];
	  }else
	  {
		  return [true];
	  }
}

$( function() {
	  $( "#datepickert" ).datepicker({
		  changeMonth :true,
		  changeYear :true,
		  yearRange :'1980:2030',
		  dateFormat:'dd/mm/yy',
		   minDate : 0 ,
		  
		  beforeShowDay : disableSunday	  ,
	  });
} );
  
</script>

 <script type="text/javascript"
	src="<%=ORSView.APP_CONTEXT%>/js/CheckBox.js">
</script>


 <style type="text/css">
 .navbar {
    position: relative;
    display: -ms-flexbox;
    display: flex;
    -ms-flex-wrap: wrap;
    flex-wrap: wrap;
    -ms-flex-align: center;
    align-items: center;
    -ms-flex-pack: justify;
    justify-content: space-between;
    padding: 0.1rem 1rem;
}
 .dropdown-item {
    display: block;
    width: 100%;
    padding: 0.2rem 1.5rem;
    clear: both;
    font-weight: 400;
    color: #212529;
    text-align: absolute;
    white-space: nowrap;
    background-color: transparent;
    border: 0;
}
 .dropdown-menu {
    position: absolute;
    top: 100%;
    left: 0;
    z-index: 1000;
    display: none;
    float: left;
    min-width: 9rem;
    padding: .2rem 0;
    margin: .1rem 0 0;
    font-size: 1rem;
    color: #212529;
    text-align: left;
    list-style: none;
    background-color: #fff;
    background-clip: padding-box;
    border: 1px solid rgba(0,0,0,.15);
    border-radius: .2rem;
 }
 .nav-item{
 font-color:#9db3a3;
 font-size: 16px;
 }
 
 </style>
</head>


<body>


<%
		UserDTO userDTO = (UserDTO) session.getAttribute("user");

		boolean userLoggedIn = userDTO != null;

		String welcomeMsg = "Hi, ";

		if (userLoggedIn) {
			String role = (String) session.getAttribute("role");
			welcomeMsg += userDTO.getFirstName() + " (" + role + ")";
		} else {
			welcomeMsg += "Guest";
		}
	%>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">


  <!-- Brand -->
  <a class="navbar-brand" href="<%=ORSView.WELCOME_CTL%>">
<img src="<%=ORSView.APP_CONTEXT%>/img/customLogoInvertT.png"
					style="width: 7rem; height: 2.8rem;">
  </a>
  
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
 <ul class="navbar-nav ml-auto">
&nbsp;

<%
						if (userLoggedIn) {
					%>

					<li class="dropdown nav-item"><a href="#"
						class="dropdown-toggle nav-link" data-toggle="dropdown"> <i
							class="fa fa-file"></i> Marksheet
					</a>
						<div class="dropdown-menu dropdown-with-icons">
							<%
								if (userDTO.getRoleId() == RoleDTO.ADMIN || userDTO.getRoleId() == RoleDTO.FACULTY
											|| userDTO.getRoleId() == RoleDTO.COLLEGE) {
							%>
							<a href="<%=ORSView.MARKSHEET_CTL%>" class="dropdown-item"> <i
								class="fa fa-plus"></i> Add Marksheet
							</a>
							<%
								}
							%>
							<a href="<%=ORSView.MARKSHEET_LIST_CTL%>" class="dropdown-item">
								<i class="fa fa-list"></i> Marksheet List
							</a><a href="<%=ORSView.GET_MARKSHEET_CTL%>" class="dropdown-item">
								<i class="fa fa-file"></i> Get Marksheet
							</a><a href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"
								class="dropdown-item"> <i class="fa fa-list-ol"></i>
								Marksheet Merrit List
							</a>
						</div></li>
					<%
						if (userDTO.getRoleId() == RoleDTO.ADMIN || userDTO.getRoleId() == RoleDTO.FACULTY
									|| userDTO.getRoleId() == RoleDTO.COLLEGE) {
								if (userDTO.getRoleId() == RoleDTO.ADMIN) {
					%>
					<li class="dropdown nav-item"><a href="#"
						class="dropdown-toggle nav-link" data-toggle="dropdown"> <i
							class="fa fa-users"></i><b></b> User
					</a>
						<div class="dropdown-menu dropdown-with-icons">
							<a href="<%=ORSView.USER_CTL%>" class="dropdown-item"> <i
								class="fa fa-plus"></i> Add User
							</a> <a href="<%=ORSView.USER_LIST_CTL%>" class="dropdown-item">
								<i class="fa fa-list"></i> User List
							</a>
						</div></li>

					<li class="dropdown nav-item"><a href="#"
						class="dropdown-toggle nav-link" data-toggle="dropdown"> <i
							class="fa fa-tag"></i> Role
					</a>
						<div class="dropdown-menu dropdown-with-icons">
							<a href="<%=ORSView.ROLE_CTL%>" class="dropdown-item"> <i
								class="fa fa-plus"></i> Add Role
							</a> <a href="<%=ORSView.ROLE_LIST_CTL%>" class="dropdown-item">
								<i class="fa fa-list"></i> Role List
							</a>
						</div></li>

					<li class="dropdown nav-item"><a href="#"
						class="dropdown-toggle nav-link" data-toggle="dropdown"> <i
							class="fa fa-graduation-cap"></i> College
					</a>
						<div class="dropdown-menu dropdown-with-icons">
							<a href="<%=ORSView.COLLEGE_CTL%>" class="dropdown-item"> <i
								class="fa fa-plus"></i> Add College
							</a> <a href="<%=ORSView.COLLEGE_LIST_CTL%>" class="dropdown-item">
								<i class="fa fa-list"></i> College List
							</a>
						</div></li>
					<%
						}
					%>

					<%
						if (userDTO.getRoleId() == RoleDTO.ADMIN || userDTO.getRoleId() == RoleDTO.COLLEGE) {
					%>
					<li class="dropdown nav-item"><a href="#"
						class="dropdown-toggle nav-link" data-toggle="dropdown"> <i
							class="fa fa-book"></i> Course
					</a>
						<div class="dropdown-menu dropdown-with-icons">
							<a href="<%=ORSView.COURSE_CTL%>" class="dropdown-item"> <i
								class="fa fa-plus"></i> Add Course
							</a> <a href="<%=ORSView.COURSE_LIST_CTL%>" class="dropdown-item">
								<i class="fa fa-list"></i> Course List
							</a>
						</div></li>

					<li class="dropdown nav-item"><a href="#"
						class="dropdown-toggle nav-link" data-toggle="dropdown"> <i
							class="fa fa-file-text"></i> Subject
					</a>
						<div class="dropdown-menu dropdown-with-icons">
							<a href="<%=ORSView.SUBJECT_CTL%>" class="dropdown-item"> <i
								class="fa fa-plus"></i> Add Subject
							</a> <a href="<%=ORSView.SUBJECT_LIST_CTL%>" class="dropdown-item">
								<i class="fa fa-list"></i> Subject List
							</a>
						</div></li>
					<li class="dropdown nav-item"><a href="#"
						class="dropdown-toggle nav-link" data-toggle="dropdown"> <i
							class="fa fa-users"></i> Faculty
					</a>
						<div class="dropdown-menu dropdown-with-icons">
							<a href="<%=ORSView.FACULTY_CTL%>" class="dropdown-item"> <i
								class="fa fa-plus"></i> Add Faculty
							</a> <a href="<%=ORSView.FACULTY_LIST_CTL%>" class="dropdown-item">
								<i class="fa fa-list"></i> Faculty List
							</a>
						</div></li>
					<%
						}
				
						}
							if (userDTO.getRoleId() == RoleDTO.COLLEGE || userDTO.getRoleId() == RoleDTO.ADMIN
									|| userDTO.getRoleId() == RoleDTO.FACULTY) {
					%>
					<li class="dropdown nav-item"><a href="#"
						class="dropdown-toggle nav-link" data-toggle="dropdown"> 
							<i class="fa fa-th-list"></i> Student
					</a>
						<div class="dropdown-menu dropdown-with-icons">
							<a href="<%=ORSView.STUDENT_CTL%>" class="dropdown-item"> <i
								class="fa fa-plus"></i> Add Student
							</a> <a href="<%=ORSView.STUDENT_LIST_CTL%>" class="dropdown-item">
								<i class="fa fa-list"></i> Student List
							</a>
						</div></li>
						<li class="dropdown nav-item"><a href="#"
						class="dropdown-toggle nav-link" data-toggle="dropdown"> <i
							class="fa fa-calendar"></i> Timetable
					</a>
						<div class="dropdown-menu dropdown-with-icons">
							<a href="<%=ORSView.TIMETABLE_CTL%>" class="dropdown-item"> <i
								class="fa fa-plus"></i> Add Timetable
							</a> <a href="<%=ORSView.TIMETABLE_LIST_CTL%>" class="dropdown-item">
								<i class="fa fa-list"></i> Timetable List
							</a>
						</div></li>

					<%
						}
						}
					%>
					<li class="nav-item dropdown" ><a
						class="dropdown-toggle nav-link" id="navbardrop"  data-toggle="dropdown"> <i
							class="fa fa-user-circle-o" aria-hidden="true"></i> <%=welcomeMsg%>
					</a>
						<div class="dropdown-menu dropdon-with-icons" style="margin-left: -12px">
							<%
								if (userLoggedIn) {
							%>
							<a href="<%=ORSView.MY_PROFILE_CTL%>" class="dropdown-item">
								<i class="fa fa-id-card" aria-hidden="true"></i>&emsp;My Profile
							</a><a href="<%=ORSView.CHANGE_PASSWORD_CTL%>" class="dropdown-item">
								<i class="fa fa-cog" aria-hidden="true"></i>&emsp;Change
								Password
							</a><a
								href="<%=ORSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>"
								class="dropdown-item"> <i class="fa fa-sign-out"
								aria-hidden="true"></i>&emsp;Logout
							</a>
							<%
								if (userDTO.getRoleId() == RoleDTO.ADMIN) {
							%>
								<a href="<%=ORSView.JAVA_DOC_VIEW%>" class="dropdown-item" target="blank">
								<i class="fa fa-book"></i>&emsp;Javadoc
							</a>
							<%
								}
								
							%>
							<%
								} else {
							%>
							<a href="<%=ORSView.LOGIN_CTL%>" class="dropdown-item"> <i
								class="fa fa-sign-in"></i>&emsp;SignIn
							</a> <a href="<%=ORSView.USER_REGISTRATION_CTL%>"
								class="dropdown-item"> <i class="fa fa-user-plus"
								></i>&emsp;SignUp
							</a>
							<%
								}
							%>
						</div>
							</li>
							
					
	</ul>
	</div>			
</nav>
<br>

</body>
</html>