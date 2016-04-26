<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Account</title>
</head>
<body>
<h1>Adding Account Result</h1>

<c:choose>
	<c:when test="${empty error}">
		<h3>Your Account has been added!</h3>
		ID: ${account.id}<br>
		Name: ${account.name}<br>
		Type: ${account.type}<br>
	</c:when>
	<c:otherwise>
		<h3>Error Adding Account</h3>
		Account ID must be unique
	</c:otherwise>
	</c:choose>
</body>
</html>