<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap.css">
<title>Episode Rater Login</title>
</head>
<body>

	<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">

				<li><a class="navbar-brand" href="show?browse=a">Browse TV
						Shows</a></li>

				<li class="active">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">Genre<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="show?genre=drama">Drama</a></li>
						<li><a href="show?genre=comedy">Comedy</a></li>
						<li><a href="show?genre=sports">Sports</a></li>
						<li><a href="show?genre=documentary">Documentary</a></li>
						<!--  <li class="divider"></li>
            <li><a href="#d">Separated link</a></li>
            <li class="divider"></li>
            <li><a href="#e">One more separated link</a></li> -->
					</ul></li>
				<li><form class="navbar-form navbar-left" role="search"
						method="post" action="show">
						<div class="form-group">
							<input name="search" type="text" class="form-control"
								placeholder="Search for TV show">
						</div>
						<button type="submit" class="btn btn-success">
							<span class="glpyhicon glyphicon-search"></span>
						</button>
					</form></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>

	<div class="container">
		<h2>${episode.title} - Season&nbsp;${episode.season} -
			Episode&nbsp;${episode.episodeNumber}: &nbsp;${episode.episodeName}</h2>
		<p>
			Synopsis:
			<c:out value="${episode.synopsis}" />
		</p>
	</div>

	<div class="container">
		<h2>
			<b>Add Review To Episode</b>
		</h2>


		<div class="container">

			<form role="form" method="post" action="review" name="changePass">
				<div class="row">
					<div class="form group col-xs-4">
						<input name="tvShowID" type="hidden" value="${episode.id}">
						<input type="hidden" name="title" value="${episode.title}">
						<input type="hidden" name="season" value="${episode.season}">
						<input type="hidden" name="episodeNumber" value="${episode.episodeNumber}">
					</div>
				</div>

				<div class="row">
					<div class="form group col-xs-4">
						<b>Rating</b><br>
						<table class="table">
							<thead>
								<tr>
									<th><label class="radio-inline"> <input
											type="radio" name="rating" id="rating1" value="1"> 1
									</label></th>
									<th><label class="radio-inline"> <input
											type="radio" name="rating" id="rating2" value="2"> 2
									</label></th>
									<th><label class="radio-inline"> <input
											type="radio" name="rating" id="rating3" value="3"> 3
									</label></th>
									<th><label class="radio-inline"> <input
											type="radio" name="rating" id="rating4" value="4"> 4
									</label></th>
									<th><label class="radio-inline"> <input
											type="radio" name="rating" id="rating5" value="5"> 5
									</label></th>
							</thead>
							<tbody></tbody>
						</table>
					</div>
				</div>

				<p>
					<b>Review</b>
				</p>
				<p style="color: red">${error}</p>
				<textarea name="review" class="form-control" rows=3></textarea>
				<br>

				<div class="row">
					<div class="container">
						<button name="addReview" type="submit" class="btn btn-primary">Add
							Review</button>
					</div>
				</div>

			</form>
		</div>
<p>${username}</p>
		<div class="container">
			<table class="table">
				<thead>
					<tr>
						<th>Review</th>
						<th>Rating</th>
						<th>Username</th>
						<th>Time</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${review}" var="review">
						<tr>
							<td>${review.review}</td>
							<td>${review.rating}/5</td>
							<td>${review.username}</td>
							<td>${review.time}</td>
							<c:if test="${((username eq review.username) and (role eq 'Reg')) or (role eq 'Mod')}">
								<td>
									<form method="post" action="review">
										<input type="hidden" name="reviewID" value="${review.reviewID}">
										<input type="hidden" name="title" value="${episode.title}">
										<input type="hidden" name="season" value="${episode.season}">
										<input type="hidden" name="episodeNumber" value="${episode.episodeNumber}">
										<button name="delete" type="submit" class="btn btn-success">
											<span class="glpyhicon glyphicon-trash"></span>
										</button>
									</form>
								</td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>


		</div>


		<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
		<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
		<script
			src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
</body>
</html>
