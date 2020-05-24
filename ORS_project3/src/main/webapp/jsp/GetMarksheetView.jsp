<%@page import="in.co.rays.ctl.GetMarksheetCtl"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="in.co.rays.util.DataValidator"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.ctl.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Get Marksheet</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
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
	background-image: linear-gradient(to right, #d01e5a 0%, #5e256b 51%, #DD5E89 100%)
	
}

.footer{
position: fixed;
left:0;
bottom:0;
width: 100%;
}
</style>
</head>
<body>
<%@ include file="Header.jsp"%>

	<jsp:useBean id="dto" class="in.co.rays.dto.MarksheetDTO"
		scope="request"></jsp:useBean>

	
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-6 ml-auto mr-auto mb-80px" >
					<div class="card card-login" ">
						<form class="form" method="post" action="<%=ORSView.GET_MARKSHEET_CTL %>">
							<div class="card-header card-header-primary text-center" style="background-color: transparent;">
								<h4 class="card-title"><font> Get Marksheet</font>
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
									<span class="text-danger pt-3" >
										<%=ServletUtility.getErrorMessage("rollNo", request)%>
										
									</span>
								</div>
								<div class="row">
								<div class="col-md-12 d-flex justify-content-center">
									<button class="btn btn-success btn-round" name="operation" type="submit"
										value="<%=GetMarksheetCtl.OP_GO%>">Go</button>
								</div>
							</div>
								</div>
								
								
							<%
								int physics = DataUtility.getInt(DataUtility.getStringData(dto.getPhysics()));
								int chemistry = DataUtility.getInt(DataUtility.getStringData(dto.getChemistry()));
								int maths = DataUtility.getInt(DataUtility.getStringData(dto.getMaths()));

								int total = physics + chemistry + maths;
								float percentage = (float) total / 3;
								percentage = Float.parseFloat(new DecimalFormat("##.##").format(percentage));

								if (dto.getRollNo() != null && dto.getRollNo().trim().length() > 0) {
							%>

															
								</form></div></div></div></div>
								<br>
								<br>
								<%if(dto.getName()!=null) {%>
<table align="center" border="1" style="border: groove; width: 35%">
								<tr>
									<td align="center"
										style="background-color: #fdebc5; color: maroon;"><h2>Rays
											Technologies, Indore</h2></td>
								</tr>

							</table>

							<table  align="center" border="1"  bgcolor=" #5c7d65" style="border: groove; width: 35%">
								<tr>
									<td align="center"  style="width: 15%" >Name</td>
									<th align="center"
										style="width: 35%; text-transform: capitalize;"><%=DataUtility.getStringData(dto.getName())%></th>

									<td align="center" style="width: 15%">Roll No</td>
									<th align="center"
										style="width: 25%; text-transform: uppercase;"><%=DataUtility.getStringData(dto.getRollNo())%></th>

								</tr>
								<tr>
									<td align="center" style="width: 15%">Status</td>
									<th align="center" style="width: 35%">Regular</th>

									<td align="center" style="width: 15%">Course</td>
									<th align="center" style="width: 25%">BE</th>

								</tr>
							</table>

							<table  align="center" border="1" bgcolor="#e8e792" style="border: groove; width: 35%">
								<tr style="background-color: #e6e6e485;">
									<th align="center" style="width: 25%">Subject</th>
									<th align="center" style="width: 25%">Earned Credits</th>
									<th align="center" style="width: 25%">Total Credits</th>
									<th align="center" style="width: 25%">Grade</th>
								</tr>
								<tr>
									<td align="center">Physics</td>
									<td align="center"><%=physics%> <%
 	if (physics < 33) {
 %><span style="color: red">*</span> <%
 	}
 %></td>
									<td align="center">100</td>
									<td align="center">
										<%
											if (physics > 90 && physics <= 100) {
										%>A+ <%
											} else if (physics > 80 && physics <= 90) {
										%>A <%
											} else if (physics > 70 && physics <= 80) {
										%>B+ <%
											} else if (physics > 70 && physics <= 80) {
										%>B <%
											} else if (physics > 60 && physics <= 70) {
										%>C+ <%
											} else if (physics > 50 && physics <= 60) {
										%>C <%
											} else if (physics >= 33 && physics <= 50) {
										%>D <%
											} else if (physics >= 0 && physics < 33) {
										%><span style="color: red;">F</span> <%
 	}
 %>
									</td>

								</tr>
								<tr>
									<td align="center">Chemistry</td>
									<td align="center"><%=chemistry%> <%
 	if (chemistry < 33) {
 %><span style="color: red">*</span> <%
 	}
 %></td>
									<td align="center">100</td>
									<td align="center">
										<%
											if (chemistry > 90 && chemistry <= 100) {
										%>A+ <%
											} else if (chemistry > 80 && chemistry <= 90) {
										%>A <%
											} else if (chemistry > 70 && chemistry <= 80) {
										%>B+ <%
											} else if (chemistry > 70 && chemistry <= 80) {
										%>B <%
											} else if (chemistry > 60 && chemistry <= 70) {
										%>C+ <%
											} else if (chemistry > 50 && chemistry <= 60) {
										%>C <%
											} else if (chemistry >= 33 && chemistry <= 50) {
										%>D <%
											} else if (chemistry >= 0 && chemistry < 33) {
										%><span style="color: red;">F</span> <%
 	}
 %>
									</td>

								</tr>
								<tr>
									<td align="center">Maths</td>
									<td align="center"><%=maths%> <%
 	if (maths < 33) {
 %><span style="color: red">*</span> <%
 	}
 %></td>
									<td align="center">100</td>
									<td align="center">
										<%
											if (maths > 90 && maths <= 100) {
										%>A+ <%
											} else if (maths > 80 && maths <= 90) {
										%>A <%
											} else if (maths > 70 && maths <= 80) {
										%>B+ <%
											} else if (maths > 70 && maths <= 80) {
										%>B <%
											} else if (maths > 60 && maths <= 70) {
										%>C+ <%
											} else if (maths > 50 && maths <= 60) {
										%>C <%
											} else if (maths >= 33 && maths <= 50) {
										%>D <%
											} else if (maths >= 0 && maths < 33) {
										%><span style="color: red;">F</span> <%
 	}
 %>
									</td>

								</tr>
							</table>

							<table  align="center" border="1" bgcolor="#fffecc" style="border: groove; width: 35%">
								<tr style="background-color: #e6e6e485;">
									<th align="center" style="width: 25%">Total Marks</th>
									<th align="center" style="width: 25%">Percentage (%)</th>
									<th align="center" style="width: 25%">Division</th>
									<th align="center" style="width: 25%">Result</th>

								</tr>
								<tr>
									<th align="center"><%=total%> <%
 	if (total < 99 || physics < 33 || chemistry < 33 || maths < 33) {
 %><span style="color: red;">*</span> <%
 	}
 %></th>
									<th align="center"><%=percentage%> %</th>
									<th align="center">
										<%
											if (percentage >= 60 && percentage <= 100) {
										%>1<sup>st</sup> <%
 	} else if (percentage >= 40 && percentage < 60) {
 %>2<sup>nd</sup> <%
 	} else if (percentage >= 0 && percentage < 40) {
 %>3<sup>rd</sup> <%
 	}
 %>
									</th>

									<th align="center">
										<%
											if (physics >= 33 && chemistry >= 33 && maths >= 33) {
										%><span style="color: forestgreen;">Pass</span> <%
 	} else {
 %><span style="color: red;">Fail</span> <%
 	}
 %>
									</th>
								</tr>

							</table>
							<%
								}
							%>
		
<%} %>

<br>
<br>
<br>
<div class="footer">
<%@ include file="Footer.jsp"%>
</div>
</body>
</html>