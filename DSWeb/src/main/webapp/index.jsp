<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
ul {
	padding: 0;
	margin: 0;
	list-style: none;
	position: relative;
	margin: 0px;
}
</style>
<meta charset="UTF-8">
<title>Beogle :D</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<ul>
		<li class="menu-item"><a href="http://google.com"
			class="menu-item-link">Google</a> <a href="https://wallpapers.com/"
			class="menu-item-link">Wallpapers</a>
	</ul>
	<br>
	<p id="p1">Find Your Favorite WallPaper</p>
	<br>
	<form action="Page2.jsp" method="post" name="FORM">
		<div style="text-align: center;">
			<input type="text" class="search_area" id="keyword" name="name"
				placeholder="Type here" autofocus> <input type="submit"
				class="search_button" id="myButton" name="myButton" value="Search">
		</div>
	</form>
	<script href="index0.js"></script>

</body>
</html>

