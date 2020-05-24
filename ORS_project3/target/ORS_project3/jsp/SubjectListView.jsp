<%@page import="in.co.rays.ctl.SubjectListCtl"%>
<%@page import="in.co.rays.dto.SubjectDTO"%>
<%@page import="in.co.rays.ctl.CourseListCtl"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
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
<style type="text/css">

.gradient-button {

	margin: 5px;
	font-family: "Arial Black", Gadget, sans-serif;
	font-size: 8px;
	padding: 5px;
	text-align: center;
	text-transform: uppercase;
	transition: 0.5s;
	background-size: 50%;
	color: #FFF;
	box-shadow: 0 0 20px #eee;
	border-radius: 2px;
	width: 80px;
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


</style>
<title>Subject List</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>
<%@ include file="Header.jsp"%>

<jsp:useBean id="dto" class="in.co.rays.dto.SubjectDTO"
		scope="request"></jsp:useBean>
<form action="<%=ORSView.SUBJECT_LIST_CTL%>" method="post">

<div align="center">
					<h3>
						<font>Subject List</font>
					</h3>
				</div>

<%
								if (ServletUtility.getSuccessMessage(request) != null
										&& ServletUtility.getSuccessMessage(request).length() > 0) {
							%>
							<div class="alert alert-success" 
								style="line-height:">
								<div class="container" style="text-align: center;">
								<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true"><i class="fa fa-times"></i></span>
									</button>
									<b><%=ServletUtility.getSuccessMessage(request)%></b>
								</div>
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
							
								<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;
					int nextPageSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());

					
					List list = (List) ServletUtility.getList(request);
					Iterator it = list.iterator();
                    List clist =(List) request.getAttribute("courseList");
                    List slist=(List) request.getAttribute("subjectList");
					if (list.size() != 0) {
						
						
					
				%>
				<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
					type="hidden" name="pageSize" value="<%=pageSize%>">
					
					
					
					
					
					
					<div class="col-12">
					<div class="row d-flex justify-content-center">
					<div class="form-group">
					<label><strong>Subject Name<span style="color:red ">*</span></strong></label>
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-search-plus"></i>
										</span>
									</div>
									<%=HTMLUtility.getList("subjectId", String.valueOf(dto.getId()), slist) %>
								</div>
							</div>
							&nbsp;
							
						<div class="form-group">
					<label><strong >Course Name<span style="color:red ">*</span></strong></label>
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-search-plus"></i>
										</span>
									</div>
        <%=HTMLUtility.getList("courseId", String.valueOf(dto.getCourseId()), clist) %>
								</div>
							</div>
							
							
					<div class="col-lg-3 col-sm-3 mt-4"> 
							<div class="row d-inline-flex align-item-center">
							
								<div class="col-3">
									<button class="gradient-button  btn-info " style="border-radius: 10px;"
										name="operation" type="submit"
										value="<%=SubjectListCtl.OP_SEARCH%>">
										<i class="fa fa-search-plus" aria-hidden="true"></i>        <b
											style="font-size: 12px;">Search</b>
									</button>
								</div> 
								
							   <div class="col-3">
									<button class="gradient-button btn-warning " style="border-radius: 10px;"
										name="operation" type="submit"
										value="<%=SubjectListCtl.OP_RESET%>">
										<i class="fa fa-refresh" aria-hidden="true"></i>           <b
											style="font-size: 12px;">Reset</b>
									</button>
								</div>
								
								<div class="col-3">
									<button class="gradient-button btn-success " style="border-radius: 10px;"
										name="operation" type="submit"
										value="<%=SubjectListCtl.OP_NEW%>">
										<i class="fa fa-plus" aria-hidden="true"></i>            <b
											style="font-size: 12px;">New</b>
									</button>
								</div>
								
							
							</div>
						</div>
					</div>
					</div>
				<div class="col-12 table-responsive">
					<table class="table table-striped table-hover bg-light">
						<thead class="bg-dark text-white">
							<tr>
								<th scope="col"><input type="checkbox" id="select_all" /></th>
								<th scope="col" class="text-center">S.No.</th>
								<th scope="col" class="text-center">Subject Name</th>
								<th scope="col" class="text-center">Description</th>
								<th scope="col" class="text-center">Course Name</th>
								
								<th scope="col" class="text-center">Delete</th>
								<th scope="col" class="text-center">Edit</th>

							</tr>
						</thead>

						<%
							while (it.hasNext()) {

									dto =(SubjectDTO) it.next();

									
						%>
						<tbody>
							<tr>
								<td><input type="checkbox" class="checkbox" name="ids"
									value="<%=dto.getId()%>"></td>
								<td class="text-center"><%=index++%></td>
								<td class="text-center"><%=dto.getName()%></td>
								<td class="text-center"><%=dto.getDescription()%> </td>
								<td class="text-center"><%=dto.getCourseName()%></td>
								
								<td class="text-center"> <button class="btn btn-link text-primary " type="submit" name="operation" value="<%=SubjectListCtl.OP_DELETE%>">
										<i class="fa fa-trash" aria-hidden="true"></i></button></td>
								<td class="text-center"><a href="SubjectCtl?id=<%=dto.getId()%>"><i
										class="fa fa-pencil-square-o" aria-hidden="true"></i></a></td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
				</div>
				
				
				<div class="row">
						<div class="col-md-12 d-flex justify-content-center">

							<button class="btn btn-info col-lg-1 col-sm-2" type="submit"
								name="operation" value="<%=SubjectListCtl.OP_PREVIOUS%>"
								<%=pageNo > 1 ? "" : "disabled"%>>
								<i class="fa fa-arrow-circle-left" aria-hidden="true"></i>&emsp;Prev
							</button>
							&emsp;
							&emsp;
							
							<button class="btn btn-warning col-lg-1 col-sm-2" type="submit"
								name="operation" value="<%=SubjectListCtl.OP_NEXT%>"
								<%=(nextPageSize != 0) ? "" : "disabled"%>>
								Next&emsp;<i class="fa fa-arrow-circle-right" aria-hidden="true"></i>
							</button>
						</div>
					</div>
				
			<%
					}
					if (list.size() == 0) {
				%>
				<div class="row">
					<div class="col-md-12 d-flex justify-content-center">

						<button class="btn btn-primary" type="submit" name="operation"
							value="<%=SubjectListCtl.OP_BACK%>">Back</button>
					</div>
				</div>
				<%
					}
				%>
							
</form>
<br>
<br>
<br>
<%@ include file="Footer.jsp"%>
</body>
</html>