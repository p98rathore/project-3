<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome View</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
<style type="text/css">


.footer{
position: fixed;

bottom:0px;
width: 100%;
}
</style>
</head>
<body>
<%@ include file ="Header.jsp" %>

<div class="container" >
<div class="row ">
<div class="col-12" class="text-center">
<h1 style="font-size: 200%"  align="center" ><font> Welcome To ORS</font></h1>
</div>
</div>
</div>
<div class="footer">
<%@ include file ="Footer.jsp" %>
</div>
</body>

</html>