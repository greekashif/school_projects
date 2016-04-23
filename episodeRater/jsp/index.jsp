<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap.css">
<title>Episode Rater Login</title>
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
      
      	<li><a class="navbar-brand" href="show?genre=a">Browse TV Shows</a></li>
      
        <li class="active">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Genre<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="show?genre=drama">Drama</a></li>
            <li><a href="show?genre=comedy">Comedy</a></li>
            <li><a href="show?genre=sports">Sports</a></li>
            <li><a href="show?genre=documentary">Documentary</a></li>
           <!--  <li class="divider"></li>
            <li><a href="#d">Separated link</a></li>
            <li class="divider"></li>
            <li><a href="#e">One more separated link</a></li> -->
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
      
      <!-- 
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#">Link</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
          <ul class="dropdown-menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li class="divider"></li>
            <li><a href="#">Separated link</a></li>
          </ul>
        </li>
      </ul> -->
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>

<div class="container">
<h1><b>The Episode Rater</b></h1>
<p>This site allows users to review individual episodes
from popular television shows.

<h3>Member Login</h3><p style=color:red>${error}</p><br></div>

<div class="container">
	<form role="form" method="post" action="login">
 	 <div class="row">
  		<div class="form-group col-xs-4">
   		<label for="userField">Username</label>
  		 <input name="username" type="text" class="form-control" id="userField" placeholder="Enter Username">
  		</div>
 	 </div>
 	 
  <div class="row">
  <div class="form-group col-xs-4">
    <label for="passField">Password</label>
    
    <input name="password" type="password" class="form-control" id="passField" placeholder="Password">
  </div>
  </div>
  
  <div class="row">
  	<div class="container">
  <button type="submit" class="btn btn-primary">Submit</button>
  	</div>
  	</div>
</form>
</div>

<div class="container">
<br>Not a Member?&nbsp;<a href="register.jsp">Register for Free!</a>
</div>


<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

</body>
</html>
