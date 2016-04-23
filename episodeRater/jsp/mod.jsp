<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap.css">
<title>Mod Page</title>
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
	<h2><b>Moderator Administration</b></h2>
</div>

<!--  Promote user to mod form -->
<div class="container">
	<form role="form" method="post" action="user">
	 	 <div class="row">
	  		<div class="form-group col-xs-4">
	   			<label for="exampleInputEmail1">Promote User</label>
	   			<br>
	   			<p style=color:red>${error}</p>
	  			 <input name="user" type="text" width="50" class="form-control" id="exampleInputEmail1" placeholder="Enter Username">
	  		</div>
	 	 </div>
	 	 
	  <div class="row">
	  	<div class="container">
	  		<button name="promote" type="submit" class="btn btn-primary">Promote User</button>
	  	</div>
	  </div>
	</form>
</div>

<br>

<!-- Change mod password form -->
<div class="container">
	<h4>Change Your Password</h4>
<p style=color:red>${error2}</p>
</div>
	
<div class="container">
	<form role="form" method="post" action="user" name="changePass">
		<div class="row">
	  	<div class="form group col-xs-4">
	   	 <label for="exampleInputPassword1">Original Password</label>
	   	 <input name="pass" type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
	  	</div>
	  </div>
	  
	  <div class="row">
	  	<div class="form group col-xs-4">
	  	  <label for="exampleInputPassword2">New Password</label>
	  	  <input name="newPass" type="password" class="form-control" id="exampleInputPassword2" placeholder="Password">
	 		</div>
	  </div>
	  
	  <div class="row">
	 	 <div class="form group col-xs-4">
	  	  <label for="exampleInputPassword2">Re-enter Password</label>
	 				<input name="newPass2" type="password" class="form-control" id="exampleInputPassword2" placeholder="Password">
	 	 </div>
	  </div>
	  
	  <br>
	  <div class="row">
	  	<div class="container">
	  		<button name="change" type="submit" class="btn btn-primary">Change Password</button>
	  	</div>
	 	</div>
	 	
 	</form>
</div>

<br>

<!-- Add TV Show form -->
<div class="container">
	<h4>Add TV Show</h4>
	<p style=color:red>${error3}</p>
</div>

<div class="container">

	<form role="form" method="post" action="show" name="addTVShow">
		<div class="row">
  		<div class="form group col-xs-4">
   			 <label for="exampleTitle">TV Show</label>
   			 <input name="title" type="text" class="form-control" id="exampleTitle" placeholder="Enter Title">
  		</div>
 		</div>
 		
 		<div class="row">
 			<div class="form group col-xs-4"><br>
 				<b>Genre</b><br>
 					<table class="table">
 						<thead>
 							<tr>
 								<th>
 									<label class="radio-inline">
   									<input type="radio" name="genre" id="genre1" value="Drama">
   									Drama
   								</label>
 								</th>
 								<th>
 									<label class="radio-inline">
   									<input type="radio" name="genre" id="genre2" value="Comedy">
   									Comedy
   								</label>
 								</th>
 								<th>
 									<label class="radio-inline">
   									<input type="radio" name="genre" id="genre3" value="Sports">
   									Sports
   								</label>
 								</th>
 								<th>
 									<label class="radio-inline">
   									<input type="radio" name="genre" id="genre4" value="Documentary">
   									Documentary
   								</label>
 								</th>
 						</thead>
 						<tbody></tbody>		  				
   				</table>
   				
   				
    		</div>
   	</div>
   	
   	<div class="row">
 			<div class="form group col-xs-4">
 				<b>Rating</b><br>
 					<table class="table">
 						<thead>
 							<tr>
 								<th>
 									<label class="radio-inline">
   									<input type="radio" name="rating" id="rating1" value="1">
   									1
   								</label>
 								</th>
 								<th>
 									<label class="radio-inline">
   									<input type="radio" name="rating" id="rating2" value="2">
   									2
   								</label>
 								</th>
 								<th>
 									<label class="radio-inline">
   									<input type="radio" name="rating" id="rating3" value="3">
   									3
   								</label>
 								</th>
 								<th>
 									<label class="radio-inline">
   									<input type="radio" name="rating" id="rating4" value="4">
   									4
   								</label>
 								</th>
 								<th>
 									<label class="radio-inline">
   									<input type="radio" name="rating" id="rating5" value="5">
   									5
   								</label>
 								</th>
 						</thead>
 						<tbody></tbody>		  				
   				</table>
   			</div>
   	</div>
   	
   	
  				
  <p><b>Synopsis</b></p>
   				<textarea name="synopsis" class="form-control" rows=3></textarea>
   				<br>
  
	  <div class="row">
	  	<div class="container">
	  <button name="addShow" type="submit" class="btn btn-primary">Add Show</button>
	  	</div>
	  	</div>
  	</form>
</div>


<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

</body>
</html>
