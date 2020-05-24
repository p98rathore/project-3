<%@page import="in.co.rays.ctl.JasperMarksheet"%>
<%@page import="in.co.rays.ctl.MarksheetMeritListCtl"%>
<%@page import="in.co.rays.dto.MarksheetDTO"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Marksheet Merit List</title>
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
	
	
}
.footer{

position: fixed;
bottom: 0px;
width :100%
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

<jsp:useBean id="dto" class="in.co.rays.dto.MarksheetDTO"
		scope="request"></jsp:useBean>
<form action="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>" method="post">

<div align="center">
					<h3>
						<font>Marksheet Merit List</font>
					</h3>
				</div>

<%
								if (ServletUtility.getSuccessMessage(request) != null
										&& ServletUtility.getSuccessMessage(request).length() > 0) {
							%>
							<div class="alert alert-success" 
								style="line-height: 10px; margin-left: 20px; margin-right: 20px; width:100%;">
								
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
							
								<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;
					
					List list = (List) ServletUtility.getList(request);
					Iterator
					it = list.iterator();

					if (list.size() != 0) {
						
						
					
				%>
				<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
					type="hidden" name="pageSize" value="<%=pageSize%>">
					
						<div class="col-12 table-responsive">
					<table class="table table-striped table-hover bg-light">
						<thead class="bg-dark text-white">
							<tr>
								
								<th scope="col" class="text-center">S.No.</th>
								<th scope="col" class="text-center">RollNo</th>
								<th scope="col" class="text-center">Name</th>
								<th scope="col" class="text-center">Physics</th>
								<th scope="col" class="text-center">Chemistry</th>
								<th scope="col" class="text-center">Maths</th>
								<th scope="col" class="text-center">Total</th>
								<th scope="col" class="text-center">Percentage (%)</th>
						

							</tr>
						</thead>

						<%
							while (it.hasNext()) {

									dto =(MarksheetDTO) it.next();

									int physics = dto.getPhysics();
									int chemistry = dto.getChemistry();
									int maths = dto.getMaths();
									int total = physics + chemistry + maths;
									float percentage = (float) total / 3;
									percentage = Float.parseFloat(new DecimalFormat("##.##").format(percentage));
						%>
						<tbody>
							<tr>
								
								<td class="text-center"><%=index++%></td>
								<td class="text-center"><%=dto.getRollNo()%></td>
								<td class="text-center"><%=dto.getName()%></td>
								<td class="text-center"><%=dto.getPhysics()%></td>
								<td class="text-center"><%=dto.getChemistry()%></td>
								<td class="text-center"><%=dto.getMaths()%></td>
								<td  class="text-center"><%=total%></td>
								<td class="text-center"><%=percentage%> %</td>
								
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
				</div>
				<%
					}
					
				%>
				
				<div class="row">
						<div class="col-md-12 d-flex justify-content-center">

							<button class="btn btn-info " type="submit"
								name="operation" value="<%=MarksheetMeritListCtl.OP_BACK%>">
								<i class="fa fa-arrow-circle-left" aria-hidden="true"></i>&emsp;Back
							</button>
							&nbsp;
							&nbsp;
							&nbsp;
							<!-- <button class="btn btn-info col-lg-1 col-sm-2" type="button"
								name="operation" align="right">  -->
							<a href="<%=ORSView.JASPER_MARKSHEET_MERIT_LIST_CTL %>" class="btn btn-info" target="blank"><font color="white"><span class="form-group-text"><i
											class="fa fa-search-plus"></i></span><font>print</font></a>
							</button>
						</div>
					</div>
				
			
				
							
</form>
<br>
<br>
<br>
<%@ include file="Footer.jsp"%>
</body>
</html>