<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/styles.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Account System</title>
</head>
<body>
<h3><%= session.getAttribute("BankName") %></h3>
<h4>Hello ${name}</h4>
<h3>Account Administration</h3>
<a href="<%= request.getContextPath() %>/jsp/ViewAccount.jsp" name="view">View</a><br>
<a href="<%= request.getContextPath() %>/jsp/AddAccount.jsp" name="add">Add Account/Deposit</a><br>
<a href="<%= request.getContextPath() %>/jsp/UpdateAccount.jsp" name="update">Update</a><br>
<a href="<%= request.getContextPath() %>/jsp/DeleteAccount.jsp" name="delete">Delete</a><br>
<a href="<%= request.getContextPath() %>/AccountServlet" name="viewAll">View All</a><br>
<br>
<form method="post" action="<%= request.getContextPath() %>/LoginServlet">
<input type="submit" name="submit" value="LOGOUT">
<input type="hidden" name="action" value="logout">
</form>

</body>
</html>