<%@page import="in.co.rays.ctl.FacultyCtl"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="in.co.rays.util.DataValidator"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Faculty View</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
<style type="text/css">
.footer{

position: fixed;
bottom: 0px;
width :100%
}

.gradient-button {

	margin: 10px;
	font-family: "Arial Black", Gadget, sans-serif;
	font-size: 10px;
	padding: 10px;
	text-align: center;
	text-transform: uppercase;
	transition: 0.5s;
	background-size: 100% auto;
	color: #FFF;
	box-shadow: 0 0 20px #eee;
	border-radius: 4px;
	width: 100px;
	background-position: right center;
	box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
	transition: all 0.3s cubic-bezier(.25, .8, .25, 1);
	cursor: pointer;
	display: inline-block;
}

</style>
</head>

<body>
	<%@ include file="Header.jsp"%>

	<jsp:useBean id="dto" class="in.co.rays.dto.FacultyDTO"
		scope="request"></jsp:useBean>

	<% List list=(List) request.getAttribute("collegeList");
	List clist=(List) request.getAttribute("courseList");
	List slist=(List) request.getAttribute("subjectList");
	%>
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-6 ml-auto mr-auto">
					<div class="card card-login" style="margin-bottom: 20px" >
						<form class="form" method="post" action="<%=ORSView.FACULTY_CTL%>">
							<div class="card-header card-header-primary text-center" style="background-color: transparent;">
								<h4 class="card-title"> <font><%if(dto.getId()>0) {%>
								Update Faculty
								<%}else{ %>
								Add Faculty
								<%} %>
								</font>
								</h4>
							</div>
							<%
								if (ServletUtility.getSuccessMessage(request) != null
										&& ServletUtility.getSuccessMessage(request).length() > 0) {
							%>
							<div class="alert alert-success"
								style="line-height: 10px;">
								
								<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true"><i class="fa fa-times"></i></span>
									</button>
									<b><%=ServletUtility.getSuccessMessage(request)%></b>
								
							</div>
							<%
								}
							%>
							<%
								if (ServletUtility.getErrorMessage(request) != null
										&& ServletUtility.getErrorMessage(request).length() > 0) {
							%>
							<div class="alert alert-danger"
								style="line-height: 10px;">
								<div class="container" style="text-align: center;">
									
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
							
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true"><i class="fa fa-times fg-2px"></i></span>
									</button>
									<b><%=ServletUtility.getErrorMessage(request)%></b>
								</div>
							</div>
							<%
								}
							%>
							<input type="hidden" name="id" value="<%=dto.getId()%>">
							<input type="hidden" name="createdBy"
								value="<%=dto.getCreatedBy()%>"> <input type="hidden"
								name="modifiedBy" value="<%=dto.getModifiedBy()%>"> <input
								type="hidden" name="createdDatetime"
								value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
							<input type="hidden" name="modifiedDatetime"
								value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">
							
							<div class="card-body">
							<div class="form-group">
								<label>First Name <span style="color:red ">*</span></label>	
								<div class="input-group">
								
									 <div class="input-group-prepend">
										<span class="input-group-text"> 
										<i class="fa fa-user-circle"></i>
										</span>
									</div >
									
									<input type="text" class="form-control col-lg-12"
										placeholder="Enter your first name" name="firstName"
										value="<%=DataUtility.getStringData(dto.getFirstName())%>">
								</div>
									
									<span class="text-danger pt-3">
										<%=ServletUtility.getErrorMessage("firstName", request)%>
									
									</span>
								</div>
								<div class="form-group">
								<label>Last Name <span style="color:red ">*</span></label>
								<div class="input-group">
									
									 <div class="input-group-prepend">
										<span class="input-group-text"> 
										<i class="fa fa-user-circle"></i>
										</span>
									</div >
									
									<input type="text" class="form-control col-lg-12"
										placeholder="Enter your last name" name="lastName"
										value="<%=DataUtility.getStringData(dto.getLastName())%>">
								</div>
								
									<span class="text-danger pt-3" >
										<%=ServletUtility.getErrorMessage("lastName", request)%>
									
									</span>
									
									
									</div>
								<div class="form-group">
									<label>Email <span style="color:red ">*</span></label>
								<div class="input-group">
								
									 <div class="input-group-prepend">
										<span class="input-group-text"> 
										<i class="fa fa-envelope"></i>
										</span>
									</div >
									
									<input type="text" class="form-control col-lg-12"
										placeholder="Enter your login id" name="email"
										value="<%=DataUtility.getStringData(dto.getEmail())%>">
									</div>
									
									<span class="text-danger pt-3">
										<%=ServletUtility.getErrorMessage("email", request)%>
									
									</span>
								</div>
																		<div class="form-group">
									<label>DOB<span style="color:red ">*</span></label>
									<div class="input-group">
									
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-calendar-plus-o" aria-hidden="true"
											style="font-size: 1.1em;"></i>

										</span>
									</div>
									
									<input type="text"  id="datepicker"  class="form-control"
										name="dob" readonly placeholder="enter your date of birth" value="<%=DataUtility.getDateString(dto.getDob())%>">
									
								</div>
								
									<span class="text-danger pt-3">
										<%=ServletUtility.getErrorMessage("dob", request)%>
						
									</span>
									
									</div>
								<div class="form-group">
								<label>Gender <span style="color:red ">*</span></label>
								<div class="input-group">
								
									 <div class="input-group-prepend">
										<span class="input-group-text"> 
										<i class="fa fa-venus-double"></i>
										</span>
									</div >
									
									<%LinkedHashMap map= new LinkedHashMap() ;
									map.put("Male", "Male");
									map.put("Female", "Female");
								String list1=	HTMLUtility.getList("gender", DataUtility.getStringData(dto.getGender()), map);
								
									%>
									<%=list1 %>
								</div>
										<span class="text-danger pt-3">
										<%=ServletUtility.getErrorMessage("gender", request)%>
										
									</span>
									
								</div>
								
								<div class="form-group">
								<label>Mobile NO<span style="color:red ">*</span></label>	
								<div class="input-group">
								
									 <div class="input-group-prepend">
										<span class="input-group-text"> 
										<i class="fa fa-phone-square"></i>
										</span>
									</div >
									
									<input type="text" class="form-control col-lg-12"
										placeholder="Enter your mobile no" name="mobileNo"
										value="<%=DataUtility.getStringData(dto.getMobileNo())%>">
								
									
					
									</div>
									<span class="text-danger pt-3">
										<%=ServletUtility.getErrorMessage("mobileNo", request)%>
										
									</span>
									</div>
									<div class="form-group">
								<label>College<span style="color:red ">*</span></label>	
								
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-th-list" aria-hidden="true"></i>
										</span>
									</div>

									<%=HTMLUtility.getList("collegeId", String.valueOf(dto.getCollegeId()), list)%>
								
								</div>
								
									<span class="text-danger pt-3">
										<%=ServletUtility.getErrorMessage("collegeId", request)%>
									
									</span>
									
								</div>
									<div class="form-group">
								<label>Course<span style="color:red ">*</span></label>	
								
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-th-list" aria-hidden="true"></i>
										</span>
									</div>

									<%=HTMLUtility.getList("courseId", String.valueOf(dto.getCourseId()
											), clist)%>
									
									</div>
									
									<span class="text-danger pt-3">
										<%=ServletUtility.getErrorMessage("courseId", request)%>
								
									</span>
								</div>
									<div class="form-group">
								<label>Subject<span style="color:red ">*</span></label>	
								
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-th-list" aria-hidden="true"></i>
										</span>
									</div>

									<%=HTMLUtility.getList("subjectId", String.valueOf(dto.getSubjectId()), slist)%>
									</div>
									<span class="text-danger pt-3">
										<%=ServletUtility.getErrorMessage("subjectId", request)%>
										
									</span>
									
								</div>	
							</div>
							<div class="row">
								<%
									if (dto != null && dto.getId() > 0) {
								%>
								<div class="col-md-12 ml-auto d-flex justify-content-center">

									<button class="gradient-button btn-success btn-round" name="operation" type="submit"
										value="<%=FacultyCtl.OP_UPDATE%>">Update</button>
									<button class="gradient-button btn-warning btn-round" name="operation" type="submit"
										value="<%=FacultyCtl.OP_CANCEL%>">Cancel</button>
								</div>
								<%
									} else {
								%>
								<div class="col-md-12 ml-auto d-flex justify-content-center">

									<button class="gradient-button btn-success btn-round" name="operation" type="submit"
										value="<%=FacultyCtl.OP_SAVE%>">Save</button>
									<button class="gradient-button btn-warning btn-round" name="operation" type="submit"
										value="<%=FacultyCtl.OP_RESET%>">Reset</button>
								</div>
								<%
									}
								%>
							</div>
							
						</form>
					</div>
				</div>
			</div>
		</div>
		<br>
		<br>
		<div class="footer">
<%@ include file ="Footer.jsp" %>
</div>
</body>
</html>