<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap.css">
<title>Register New User</title>
</head>
<body>

<c:set var="username" value="Anonymous" scope="session"/>
<c:set var="role" value="Guest" scope="session"/>

<nav class="navbar navbar-default" role="navigation">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li><a class="navbar-brand" href="show?browse=a">
            Browse TV Shows</a></li>
        <li class="active">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Genre<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="show?genre=drama">Drama</a></li>
            <li><a href="show?genre=comedy">Comedy</a></li>
            <li><a href="show?genre=sports">Sports</a></li>
            <li><a href="show?genre=documentary">Documentary</a></li>
          </ul>
        </li>
      
     <li><form class="navbar-form navbar-left" role="search" method="post" action="show">
        <div class="form-group">
          <input name="search" type="text" class="form-control" placeholder="Search for TV show">
        </div>
        <button type="submit" class="btn btn-success">
        	<span class="glyphicon glyphicon-search"></span>
					</button>
      </form></li>
      </ul>
     
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>

<div class="container">
<h2><b>Join Our Community!</b></h2>
<p style=color:red>${error}</p>

<h4>Please fill out the following form.</h4><br></div>

<div class="container">
	<form role="form" method="post" action="user">
 	 <div class="row">
  		<div class="form-group col-xs-4">
   		<label for="userfield">Username</label>
  		 <input type="text" width="50" class="form-control" id="userfield" placeholder="Enter Username"
  		 				name="user">
  		</div>
 	 </div>
 	 
  <div class="row">
  <div class="form-group col-xs-4">
    <label for="exampleInputPassword1">Password</label>
    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password"
    			 name="pass">
  </div>
  </div>
  
  <div class="row">
  <div class="form-group col-xs-4">
    <label for="exampleInputPassword2">Re-enter Password</label>
    <input type="password" class="form-control" id="exampleInputPassword2" placeholder="Password"
    			 name="pass2">
  </div>
  </div>
  
  <div class="row">
  	<div class="container">
  <button name="create" type="submit" class="btn btn-primary">Join</button>
  	</div>
  	</div>
</form>
</div>

<div class="container">
<br>Already a Member?&nbsp;<a href="login.jsp">Login here</a>
</div>


<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

</body>
</html>
