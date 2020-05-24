<%@page import="in.co.rays.ctl.ChangePasswordCtl"%>
<%@page import="javax.swing.text.ChangedCharSetException"%>
<%@page import="in.co.rays.ctl.BaseCtl"%>
<%@page import="in.co.rays.util.DataValidator"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password </title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
<style type="text/css">

.gradient-button{
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
body {
	background-image: url('<%=ORSView.APP_CONTEXT%>/img/bg1.jpg');
	background-size: cover;
background-repeat: no-repeat;
height: 100%;
font-family: 'Numans', sans-serif;
}
</style>
</head>
<body>
<%@ include file="Header.jsp"%>

	<jsp:useBean id="dto" class="in.co.rays.dto.UserDTO"
		scope="request"></jsp:useBean>

	
		<div class="container">
			<div class="row">
				<div class="col-lg-5 col-md-6 ml-auto mr-auto">
					<div class="card card-login" style="margin-top: 20px">
						<form class="form" method="post" action="<%=ORSView.CHANGE_PASSWORD_CTL %>">
							<div class="card-header card-header-primary text-center" style="background-color: transparent;">
								<h4 class="card-title"><font>Change Password</font></h4>
							</div>
							<%
								if (ServletUtility.getSuccessMessage(request) != null
										&& ServletUtility.getSuccessMessage(request).length() > 0) {
							%>
							<div class="alert alert-success"
								style="line-height: 10px; margin-left: 20px; margin-right: 20px;">
								
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
								style="line-height: 10px; margin-left: 20px; margin-right: 20px;">
								<div class="container" style="text-align: center;">
									<div class="alert-icon">
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
									</div>
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
									<label>Old Password <span style="color:red ">*</span></label>
								<div class="input-group">
									 <div class="input-group-prepend">
										<span class="input-group-text"> 
										<i class="fa fa-key"></i>
										</span>
									</div >
									
									<input type="password" class="form-control col-lg-12"
										placeholder="Enter your oldPassword" name="oldPassword"
										value="<%=DataUtility.getString(request.getParameter("oldPassword") == null ? ""
					: DataUtility.getString(request.getParameter("oldPassword")))%>">
										
									
									</div>
									
									<span class="text-danger pt-3">
										<%=ServletUtility.getErrorMessage("oldPassword", request)%>
										
									</span>
									
								</div>
								<div class="form-group">
									<label>New Password <span style="color:red ">*</span></label>
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i class="fa fa-key"></i>
										</span>
									</div>

									<input type="password" class="form-control col-lg-12"
										placeholder=" Enter new password here" name="newPassword"
										value="<%=DataUtility.getString(request.getParameter("newPassword") == null ? ""
					: DataUtility.getString(request.getParameter("newPassword")))%>">
									
								</div>
								<span class="text-danger pt-3">
										<%=ServletUtility.getErrorMessage("newPassword", request)%>
										
									</span>
								</div>
								<div class="form-group">
									<label>Confirm Password <span style="color:red ">*</span></label>
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i class="fa fa-key"></i>
										</span>
									</div>

									<input type="password" class="form-control col-lg-12"
										placeholder=" Confirm new password here" name="confirmPassword"
										value="<%=DataUtility.getString(request.getParameter("confirmPassword") == null ? ""
					: DataUtility.getString(request.getParameter("confirmPassword")))%>">
									
									
									</div>
									
									<span class="text-danger pt-3">
										<%=ServletUtility.getErrorMessage("confirmPassword", request)%>
									
									</span>
								</div>
							</div>
							
							
								<div class="row d-flex justify-content-center">
									<button class="gradient-button btn-success" name="operation"
										type="submit" value="<%=BaseCtl.OP_SAVE%>">Save</button>
										
									<button class="gradient-button btn-warning" name="operation"
										type="submit"
										value="<%=ChangePasswordCtl.OP_CHANGE_MY_PROFILE%>">My
										Profile</button>
								</div> </form>
							
								
					</div>
				</div>
			</div>
			
			
		</div>
	


</body>
</html>