<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">   

<jsp:useBean class="assn1.StudentSearcher" id="searcher" scope="session" />
<jsp:setProperty property="searchString" name="searcher" value=""/>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Look-Up</title>
</head>

<body>
<h1>Search CSCI 4300 Students</h1>
<form method="get" action="index.jsp">
	Search string: <input type="text" size="20" name="searchString">
	<input type="submit" value="Search Student List">
	<jsp:setProperty property="searchString" name="searcher" />
	</form>

<h2>Search Results:</h2><br>
<table>
<tr><th align="left">Last Name</th><th align="left">First Name</th>
<th align="left">Email</th></tr>
<c:forEach items="${searcher.searchResults}" var="student">
	<tr><td>${student.lname}</td><td>${student.fname}</td>
	<td><a href="mailto:${student.emailAddr}">${student.emailAddr}</a>
	</td><td><img src="${student.imageLocation}"/></td></tr>
	</c:forEach>


</table>
</body>
</html>