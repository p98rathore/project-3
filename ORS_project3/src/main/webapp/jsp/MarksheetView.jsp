<%@page import="in.co.rays.ctl.MarksheetCtl"%>
<%@page import="in.co.rays.ctl.BaseCtl"%>
<%@page import="in.co.rays.util.DataValidator"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.ctl.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Marksheet View</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
<style type="text/css">
.footer{

position: relative;
bottom: auto;
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

	<jsp:useBean id="dto" class="in.co.rays.dto.MarksheetDTO"
		scope="request"></jsp:useBean>
<% List l=(List) request.getAttribute("studentList"); %>
	
		<div class="container">
			<div class="row">
				<div class="col-lg-5 col-md-6 ml-auto mr-auto">
					<div class="card card-login" style="margin-bottom: 20px">
						<form class="form" method="post" action="<%=ORSView.MARKSHEET_CTL %>">
							<div class="card-header card-header-primary text-center" style="background-color: transparent;">
								<h4 class="card-title"><font>
								<%if(dto!=null&&dto.getId()>0) {%>
								Update
								<%}else{ %>
								Add
								<%} %>
								Marksheet </font></h4>
							</div>
							<%
								if (ServletUtility.getSuccessMessage(request) != null
										&& ServletUtility.getSuccessMessage(request).length() > 0) {
							%>
							<div class="alert alert-success"
								style="line-height: 10px;">
								
								
									<b><%=ServletUtility.getSuccessMessage(request)%></b><button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true"><i class="fa fa-times"></i></span>
									</button>
								
							</div>
							<%
								}
							%>
							<%
								if (ServletUtility.getErrorMessage(request) != null
										&& ServletUtility.getErrorMessage(request).length() > 0) {
							%>
							<div class="alert alert-danger"
								style="line-height: 10px; ">
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
									<label>Roll No <span style="color:red ">*</span></label>
								<div class="input-group">
									 <div class="input-group-prepend">
										<span class="input-group-text"> 
										<i class="fa fa-key"></i>
										</span>
									</div >
										<input type="text" class="form-control col-lg-12"
										placeholder=" Enter RollNO" name="rollNo"
										value="<%= DataUtility.getStringData(dto.getRollNo())%>">
									
									
									
									</div>
									<span class="text-danger pt-3">
										<%=ServletUtility.getErrorMessage("rollNo", request)%>
										
									</span>
								</div>
							<div class="form-group">
									<label>Student Name <span style="color:red ">*</span></label>
								<div class="input-group">
									 <div class="input-group-prepend">
										<span class="input-group-text"> 
										<i class="fa fa-users"></i>
										</span>
									</div >
									<%=HTMLUtility.getList("studentId",String.valueOf(dto.getStudentId()), l) %>
									
									
									
									</div>
									<span class="text-danger pt-3">
										<%=ServletUtility.getErrorMessage("studentId", request)%>
									
									</span>
								</div>
								<div class="form-group">
									<label>Physics <span style="color:red ">*</span></label>
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i class="fa fa-calculator"></i>
										</span>
									</div>

									<input type="text" class="form-control col-lg-12"
										placeholder=" Enter Physics marks" name="physics"
										value="<%=DataUtility.getStringData(dto.getPhysics())%>">
									
								
									</div>
										<span class="text-danger pt-3">
										<%=ServletUtility.getErrorMessage("physics", request)%>
										
									</span>
								
								</div>
								<div class="form-group">
									<label>Chemistry <span style="color:red ">*</span></label>
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i class="fa fa-calculator"></i>
										</span>
									</div>

									<input type="text" class="form-control col-lg-12"
										placeholder=" Enter Chemistry marks" name="chemistry"
										value="<%=DataUtility.getStringData(dto.getChemistry())%>">
									
									
									
									</div>
									<span class="text-danger pt-3">
										<%=ServletUtility.getErrorMessage("chemistry", request)%>
										
									</span>
								</div>
								<div class="form-group">
									<label>Maths <span style="color:red ">*</span></label>
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i class="fa fa-calculator"></i>
										</span>
									</div>

									<input type="text" class="form-control col-lg-12"
										placeholder=" Enter maths marks" name="maths"
										value="<%=DataUtility.getStringData(dto.getMaths())%>">
									
									
									
									</div>
									<span class="text-danger pt-3">
										<%=ServletUtility.getErrorMessage("maths", request)%>
										
									</span>
								</div>
							</div>
							
							
								<div class="row d-flex justify-content-center">
								<%if(dto!=null && dto.getId()> 0) {%>
								<button class="gradient-button btn-success btn-round" name="operation"
										type="submit" value="<%=MarksheetCtl.OP_UPDATE%>">upate</button>
										
									<button class="gradient-button btn-warning btn-round" name="operation"
										type="submit"
										value="<%=MarksheetCtl.OP_CANCEL%>">Cancel</button>
								
								<%}else{ %>
								<button class="gradient-button btn-success btn-round " name="operation"
										type="submit" value="<%=MarksheetCtl.OP_SAVE%>">Save</button>
										
									<button class="gradient-button btn-warning btn-round" name="operation"
										type="submit"
										value="<%=MarksheetCtl.OP_RESET%>">reset</button>
								<%} %>
									
								</div> </form>
							
								
					</div>
				</div>
			</div>
			
			
		</div>
	<br>
	<br>
	<br>
		<div class="footer">
<%@ include file ="Footer.jsp" %>
</div>

</body>
</html>