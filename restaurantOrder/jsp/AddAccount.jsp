<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="script.js"></script>
<title>Add Account</title>
</head>
<body>

<h3>Add Account</h3>
<form method="post" action="<%= request.getContextPath() %>/AccountServlet" name="form" 
	onsubmit="return valAccount()">
ID: <input type="text" name="id" id="accId"><br>
Name: <input type="text" name="acctName" id="accName"><br>
Type: <input type="text" name="type" id="accType"><br>
<input type="submit" name="submit" value="Add Account">
<input type="reset" name="reset" value="Clear Form">
<input type="hidden" name="action" value="add">
</form>
<br>${error}
<br>

<h3>Deposit Funds</h3>
<form method="post" action="<%= request.getContextPath() %>/AccountServlet"
	name="depo">
ID: <input type="text" name="depId"><br>
Amount to Deposit: <input type="text" name="depositFunds"><br>
<input type="hidden" name="action" name="deposit">
<input type="submit" name="depSubmit" value="Deposit Funds">
</form>

</body>
</html>