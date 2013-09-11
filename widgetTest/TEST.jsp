<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<meta name="viewport"  http-equiv="Content-Type" content="width=device-width initial-scale=1" />
	<title>Insert title here</title>
			<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
			<script src="http://code.jquery.com/jquery-1.8.2.min.js"></script>
			<script type="text/javascript" src="js/jquery.session.js"></script>
			<link rel="stylesheet" href="http://code.jquery.com/mobile/1.2.0/jquery.mobile-1.2.0.min.css" />
			<script src="http://code.jquery.com/mobile/1.2.0/jquery.mobile-1.2.0.min.js"></script>
<body onload = "begin()">
	<% 
	 request.setCharacterEncoding("utf-8");
	out.println("BrickName" + request.getParameter("BrickName") + "<br/>"); %>
</body>
</html>