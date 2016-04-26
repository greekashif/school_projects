<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Account Details</title>
</head>
<body>
<h3>View Account</h3>
<form name="myForm" method="post" action="<%= request.getContextPath() %>/AccountServlet">
Account ID: <input type="text" name="accId">
	<input type="submit" name="submit" value="View Account">
	<input type="hidden" name="action" value="view">
</form>
<br>
${notFound}

<c:if test="${not empty account}">
<table cellspacing="10">
	<tr>
		<th>Id</th>
		<th>Name</th>
		<th>Type</th>
	</tr>
	<tr>
		<td>${account.id}</td>
		<td>${account.name}</td>
		<td>${account.type}</td>
	</tr>
</table>
</c:if>
<br>
<a href="<%= request.getContextPath() %>/jsp/home.jsp">Home</a>

</body>
</html>