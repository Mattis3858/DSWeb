
<%@page import="com.sample.jsp.bean.Call"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Search</title>
<link rel="stylesheet" type="text/css" href="style1.css">
</head>
<body>

	<form action="Page2.jsp" method="post" name="FORM">
		<p id="Line1">
			Beogle <input type="text" class="search_area" id="keyword"
				name="name" placeholder="Type here" autofocus> <input
				type="submit" class="search_button" id="myButton" name="myButton"
				value="Search">
		</p>
	</form>
	<jsp:useBean id="keyword" class="com.sample.jsp.bean.Keyword"
		scope="page"></jsp:useBean>
	<jsp:setProperty property="*" name="keyword" />
	<jsp:useBean id="call" class="com.sample.jsp.bean.Call" scope="page"></jsp:useBean>
	<jsp:setProperty name="call" property="keyword" />

	<%
    Call cal = new Call();
    cal.setKeyword(keyword.getName());
    cal.run1();
    
    String[] res = cal.getResult();
    cal.run2();
    String[] res2 = cal.getResult2();
    //out.println(res[0]);
    %>
   
    <h1>Wallpaper Result</h1>
    <a href=""></a>
    <a href="<%=res[1]%>"></a>
    <img alt="<%=res[0]%>" src="<%=res[2]%> " width=70% height=70%>
    	<br>
        <img alt="<%=res[3]%>" src="<%=res[4]%>" width=40% height=40%>
        <br>
        <img alt="<%=res[5]%>" src="<%=res[6]%>" width=40% height=40%>
    <h1>Google Result</h1>
    <img alt="<%=res2[0] %>" src="<%=res2[1] %>">
    Score:<%=res2[2] %>
    <br>
    <img alt="<%=res2[3] %>" src="<%=res2[4] %>">
    Score:<%=res2[5] %>
    <br>
    <img alt="<%=res2[6] %>" src="<%=res2[7] %>">
    Score:<%=res2[8] %>






</body>
</html>