<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap.css">
<title>Episode Rater Search Results</title>
</head>
<body>

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
        	<span class="glpyhicon glyphicon-search"></span>
				</button>
      </form></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>

<div class="container">
<h2><b>Search Results</b></h2>

<div class="container">
	<table class="table">
		<thead>
			<tr>
				<th>TV Show</th>
				<th>Rating</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${search}" var="search">
			<tr>
				<td><a href="show?title=${search.title}">${search.title}</a></td>
				<td>${search.rating}/5</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>


<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

</body>
</html>
