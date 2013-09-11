<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*"%>

<html>
	<%
		  String id = (String)session.getAttribute("user_id");
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
		<script type="text/javascript">
		/*
			此網頁會在進入時讀取LogIn時servlet端存好的user_id session
			再將user的以存application呈現在最後端給以選擇
			
		*/
			var userId = <%= id%>;
			function goBackLogIn(){
				location.replace('Log_In.jsp');
				
			}
			
			function changPageAndSetSession(application_id){
				//alert(application_id);
				$.post("SetSession.jsp" ,{applicationID_data:application_id,input:"applicationID",action:"selectAppliction"}, function(success) {
						alert(success);
/*web change to designer*/	
						location.replace("testSession.jsp");
							}
						);
			}
			
			
			function init(){
				$.post("SearchService" ,{input:"mainApplication",searchKey:userId}, function(success) 
					{
						//alert(success);
						var applicationJson =  JSON.parse(success);
						for(var i in applicationJson.application){
							//alert(applicationJson.application[i].application_name);
						$("#mainUl").append("<li data-theme='c'><a href='' data-transition='slide' onclick = 'changPageAndSetSession("+ applicationJson.application[i].application_id+")' id = '"+ applicationJson.application[i].application_id +"'>"+applicationJson.application[i].application_name+"</a></li>");					
						$("#mainUl").listview( "refresh" );
						}
					});
			}
			
		</script>
	</head>
	<body onload = 'init()'>
			<!-- Home -->
		<div data-role="page" id="mainPage">
			<div data-theme="b" data-role="header">
			<a data-role="button" data-transition="none" data-icon="arrow-l"
			data-iconpos="left" class="ui-btn-left" onclick = "goBackLogIn()">
				back
			</a>
			<h4>
				BrickMain
			</h4>
			</div>
				<div data-role="content">
					<ul data-role="listview" data-divider-theme="b" data-inset="true" id = "mainUl">
						<li data-role="list-divider" role="heading">
							Hello~ !!!! ===> <%= id%>
						</li>
						<li data-theme="c">
							<a href="#" onclick = "location.replace('upload.html');" data-transition="slide">
								Upload your brick
							</a>
						</li>
						<li data-role="list-divider" role="heading">
							Smartphone Interface
						</li>
						<li data-theme="c">
							<a href="#" onclick="location.replace('Search.jsp');" data-transition="slide">
								Make an application
							</a>
						</li>
						<li data-role="list-divider" role="heading">
								Choose the appliaction you own
						</li>
					</ul>
				</div>
		</div>
	</body>
</html>