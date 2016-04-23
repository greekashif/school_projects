<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap.css">
<title>Episode Rater</title>
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
      
      	<li><a class="navbar-brand" href="show?browse=a">Browse TV Shows</a></li>
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
        <li><form class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search for TV show">
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
	<h2>${season.title} - Season&nbsp;${season.season}</h2>
	<p>Rating: ${season.rating}/5</p>
	<p>Synopsis: ${season.synopsis}</p>
</div>

<div class="container">
<c:if test="${role eq 'Mod'}">
	<h3>Add Episode</h3>
</c:if>
	  <p style=color:red>${error}</p>
</div>

<div class="container">

<c:if test="${role eq 'Mod'}">
	<form role="form" method="post" action="show" name="changePass">
		<div class="row">
	 		<div class="form group col-xs-4">
	 			 <input name="title" type="hidden" value="${season.title}">
	 			 <input name="season" type="hidden" value="${season.season}">
	 		</div>
		</div>
		
		<div class="row">
	  	<div class="form-group col-xs-4">
	    	<label for="episodeNum">Episode Number</label>
	    	<input name="episodeNumber" type="text" class="form-control" id="episodeNum" placeholder="Enter Episode Number">
	  	</div>
	  </div>
	  
	  <div class="row">
	  	<div class="form-group col-xs-4">
	    	<label for="epName">Episode Title</label>
	    	<input name="episodeName" type="text" class="form-control" id="epName" placeholder="Enter Episode Title">
	  	</div>
	  </div>
	  
	 	<p><b>Synopsis</b></p>
   		<textarea name="synopsis" class="form-control" rows=3></textarea>
   			<br>

		<div class="row">
			<div class="container">
			  <button name="addEpisode" type="submit" class="btn btn-primary">Add Episode</button>
			</div>
		</div>
			
	</form>
	</c:if>
	
	<table class="table">
		<thead>
			<tr>
				<th>Episode Number</th>
				<th>Episode Title</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${episodes}" var="episodes">
			<tr>
				<td><a href="show?episodeNumber=${episodes.episodeNumber}&season=${episodes.season}&title=${episodes.title}">${episodes.episodeNumber}</a></td>
				<td>${episodes.episodeName}</td>
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
