<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Account Information</title>
</head>
<body>
<h3>Update Account Information</h3>
<form name="myForm" method="post" action="<%= request.getContextPath() %>/AccountServlet">
ID: <input type="text" name="accId" <c:if test="${not empty disable}">readonly="readonly" value="${id}"
									</c:if>><br>
	<input type="hidden" name="action" value="update">
<c:if test="${hasResult}">
Name: <input type="text" name="name"><br>
Type: <input type="text" name="type"><br>
	<input type="submit" name="submit" value="Update">
	<input type="hidden" name="action" value="update">
</c:if>
</form>
<br>
${notFound}
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

</body>
</html>