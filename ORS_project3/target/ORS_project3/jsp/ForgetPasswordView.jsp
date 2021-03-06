<%@page import="in.co.rays.util.DataValidator"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
<style type="text/css">
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

body {
	background-image: url('./img/bg1.jpg');
	background-size: cover;
background-repeat: no-repeat;
height: 100%;
font-family: 'Numans', sans-serif;
}
</style>
<title>Forget Password</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body >
	<%@ include file="Header.jsp"%>

	<jsp:useBean id="dto" class="in.co.rays.dto.UserDTO"
		scope="request"></jsp:useBean>

	
		<div class="container">
			<div class="row">
				<div class="col-lg-5 col-md-7 ml-auto mr-auto">
					<div class="card card-login" style="margin-top: 20px">
						<form class="form" method="post" action="<%=ORSView.FORGET_PASSWORD_CTL%>">
							<div class="card-header card-header-primary text-center" style="background-color: transparent;">
								<h4 class="card-title"> <font>Forget Password</font></h4>
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
							<b> Submit your email address and we'll send you password.</b>
							<div class="form-group">
									<label>Email Id <span style="color:red ">*</span></label>
								<div class="input-group">
									 <div class="input-group-prepend">
										<span class="input-group-text"> 
										<i class="fa fa-envelope"></i>
										</span>
									</div >
									
									<input type="text" class="form-control col-lg-12"
										placeholder="Enter email here" name="login"
										value="<%=DataUtility.getStringData(dto.getLogin())%>">
										
									
									
									</div>
									<span class="text-danger pt-3">
										<%=ServletUtility.getErrorMessage("login", request)%>
									
									</span>
									
								</div>
															</div>
							
							<div class="row d-flex justify-content-center">
								<button class="gradient-button btn-success btn-round"
									name="operation" type="submit" value="<%=LoginCtl.OP_GO%>">
									Go <span><i class="fa fa-sign-in" aria-hidden="true"></i>
									</span>
								</button>
							</div>
							
						</form>
					</div>
				</div>
			</div>
		</div>
	

<%@ include file ="Footer.jsp" %>
</body>

</html>