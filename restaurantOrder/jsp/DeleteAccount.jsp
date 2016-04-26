<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Account</title>
</head>
<body>
<h3>Delete Account</h3>
<form name="myForm" method="post" action="<%= request.getContextPath() %>/AccountServlet" 
	onsubmit="return confirm('Are you sure you want to delete this account?')">
ID: <input type="text" name="accId"><br>
	<input type="hidden" name="action" value="delete">
	<input type="submit" name="submit" value="Delete Account">
</form>
<br>

<c:if test="${hasResult}">
Account ${accId} deleted<br>
</c:if>
${notFound}
</body>
</html>