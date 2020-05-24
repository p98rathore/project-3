<%@page import="in.co.rays.ctl.TimetableCtl"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="in.co.rays.util.DataValidator"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Timetable View</title>
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

	<jsp:useBean id="dto" class="in.co.rays.dto.TimetableDTO"
		scope="request"></jsp:useBean>

	<% 
	List clist=(List) request.getAttribute("courseList");
	List slist=(List) request.getAttribute("subjectList");
	%>
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-6 ml-auto mr-auto">
					<div class="card card-login" style="margin-bottom: 20px" >
						<form class="form" method="post" action="<%=ORSView.TIMETABLE_CTL%>">
							<div class="card-header card-header-primary text-center" style="background-color: transparent;">
								<h4 class="card-title"> <font><%if(dto.getId()>0) {%>
							Update Timetable
								<%}else{ %>
								Add Timetable
								<%} %>
								</font>
								</h4>
							</div>
							<%
								if (ServletUtility.getSuccessMessage(request) != null
										&& ServletUtility.getSuccessMessage(request).length() > 0) {
							%>
							<div class="alert alert-success"
								style="line-height: 10px; ">
								
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

									<%=HTMLUtility.getList("subjectId", String.valueOf(dto.getSubId()), slist)%>
									
								</div>
								
									<span class="text-danger pt-3">
										<%=ServletUtility.getErrorMessage("subjectId", request)%>
										
									</span>
								</div>	
									<div class="form-group">
								<label>Semester<span style="color:red ">*</span></label>	
								
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-th-list" aria-hidden="true"></i>
										</span>
									</div>
									
<% LinkedHashMap map = new LinkedHashMap();
map.put("1st", "1st");
map.put("2nd", "2nd");
map.put("3rd", "3rd");
map.put("4th", "4th");
map.put("5th", "5th");
map.put("6th", "6th");
map.put("7th", "7th");
map.put("8th", "8th");

String html= HTMLUtility.getList("semester", dto.getSemester(), map);
%>
									<%=html %>
									
								</div>
								
									
									<span class="text-danger pt-3" >
										<%=ServletUtility.getErrorMessage("semester", request)%>
										
									</span>
									
								</div>	
																		<div class="form-group">
									<label>Exam Date<span style="color:red ">*</span></label>
									<div class="input-group">
									
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-calendar-plus-o" aria-hidden="true"
											style="font-size: 1.1em;"></i>

										</span>
									</div>
									
									<input type="text"  id="datepickert"  class="form-control"
										name="examDate" readonly placeholder="enter exam date" value="<%=DataUtility.getDateString(dto.getExamDate())%>">
										</div>
											<span class="text-danger pt-3">
										<%=ServletUtility.getErrorMessage("examDate", request)%>
										
									</span>
									</div>
											<div class="form-group">
								<label>Exam Time<span style="color:red ">*</span></label>	
								
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-clock-o" aria-hidden="true"></i>
										</span>
									</div>
									
<% LinkedHashMap<String, String> map1 = new LinkedHashMap<String, String>();
map1.put("08:00 AM to 11:00 AM", "08:00 AM to 11:00 AM");
map1.put("12:00 PM to 03:00 PM", "12:00 PM to 03:00 PM");
map1.put("04:00 PM to 07:00 PM", "04:00 PM to 07:00 PM");

String html1= HTMLUtility.getList("examTime", dto.getExamTime(), map1);
%>
									<%=html1 %>
									
								
								</div>
								
									<span class="text-danger pt-3" >
										<%=ServletUtility.getErrorMessage("examTime", request)%>
										
									</span>
									
								</div>	
								<div class="form-group">
								<label>Description<span style="color:red ">*</span></label>
								<div class="input-group">
									
									 <div class="input-group-prepend">
										<span class="input-group-text"> 
										<i class="fa fa-file"></i>
										</span>
									</div >
									
									<input type="text" class="form-control col-lg-12"
										placeholder="Enter Description" name="description"
										value="<%=DataUtility.getStringData(dto.getDescription())%>">
								
									
									</div>
										
									<span class="text-danger pt-3">
										<%=ServletUtility.getErrorMessage("description", request)%>
									
									</span>
									</div>	
								
							</div>
						
							<div class="row">
								<%
									if (dto != null && dto.getId() > 0) {
								%>
								<div class="col-md-12 ml-auto d-flex justify-content-center">

									<button class="gradient-button btn-success btn-round" name="operation" type="submit"
										value="<%=TimetableCtl.OP_UPDATE%>">Update</button>
									<button class="gradient-button btn-warning btn-round" name="operation" type="submit"
										value="<%=TimetableCtl.OP_CANCEL%>">Cancel</button>
								</div>
								<%
									} else {
								%>
								<div class="col-md-12 ml-auto d-flex justify-content-center">

									<button class="gradient-button btn-success btn-round" name="operation" type="submit"
										value="<%=TimetableCtl.OP_SAVE%>">Save</button>
									<button class="gradient-button btn-warning btn-round" name="operation" type="submit"
										value="<%=TimetableCtl.OP_RESET%>">Reset</button>
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