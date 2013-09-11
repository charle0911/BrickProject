<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*"%>

<html>
<%
	String selectBrick = (String)session.getAttribute("selectBrick");
	String applicationID = (String)session.getAttribute("applicationID");
	String id = (String)session.getAttribute("user_id");
	String action = (String)session.getAttribute("action");
	// 我們自訂 Session 的屬性名稱以及屬性值
	
%> 								
	<head>
		<meta name="viewport" content="width=device-width initial-scale=1" />
		<title>WidgetTest</title>
		<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
		<script src="http://code.jquery.com/jquery-1.8.2.min.js"></script>
		<script type="text/javascript" src="js/jquery.session.js"></script>
		<link rel="stylesheet" href="http://code.jquery.com/mobile/1.2.0/jquery.mobile-1.2.0.min.css" />
		<script src="http://code.jquery.com/mobile/1.2.0/jquery.mobile-1.2.0.min.js"></script>
		<script src="jquery.cookie.js"></script>
		<script type="text/javascript">
		/*
			用於測試session
		*/
				var selectBrick =  <%= selectBrick%>;
				var appliaction_id = <%= applicationID%>;
				var id = <%= id%>;
				var action = '<%= action%>';
			function clear(){
				location.replace('main.jsp');
			}
			function test(){	
				document.write("<p>selectBrick =" + JSON.stringify( selectBrick )+"</p>");
				document.write("<p>appliaction_id ="+appliaction_id + "</p>");
				document.write("<p>id =" + id + "</p>");
				document.write("<p>" + action + "</p>");
				document.write("<a href = 'Search.jsp'>go back</a>");
			}

			function clearID(){

			}
			function clearApplication(){

			}
			function clearSelectBrick(){

			}
		</script>
	</head>
	
	<body onload = "test()">
		
	</body>
</html>