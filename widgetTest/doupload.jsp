<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

		String zipJudge = (String)session.getAttribute("zipJudge");
		String jsJudge = (String)session.getAttribute("jsJudge");
		String txtJudge = (String)session.getAttribute("txtJudge");
		String jsonJudge = (String)session.getAttribute("jsonJudge");
		String brickNameExist = (String)session.getAttribute("brickNameExist");
		String uploadBrickTestJson = (String)session.getAttribute("uploadApplicationJSON");
%>
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
		<script type="text/javascript">
				var zipJudge =  <%= zipJudge%>;
				var jsJudge = <%= jsJudge%>;
				var txtJudge = <%= txtJudge%>;
				var jsonJudge = <%= jsonJudge%>;
				var brickNameExist = <%= brickNameExist%>;
				var uploadBrickTestJson = <%= uploadBrickTestJson%>;
				function begin(){
					if(brickNameExist == 1){
						$(".text").text("");
						$(".text").append("<p>Sorry upload error</p><br/><p>Your BrickName Have been used</p>");
					}
					else if(zipJudge ==1 ){
						$(".text").text("");
						$(".text").append("<p>Sorry upload error</p><br/><p>Your file is'n the zip format</p>");
					}
					else if(jsJudge ==1 ){
						$(".text").text("");
						$(".text").append("<p>Sorry upload error</p><br/><p>Plz check your file again</p><br/><p>Your lack the js file </p>");
					}
					else if( txtJudge ==1 ){
						$(".text").text("");
						$(".text").append("<p>Sorry upload error</p><br/><p>Your lack the json file </p><br/><p>Plz check your json again</p>");
					}else if(jsonJudge ==0){
						$(".text").text("");
						$(".text").append("<p>Sorry upload error</p><br/><p>Your json is Malformed,It lack the brick object</p><br/><p>Plz check your Json again</p>");
					}else if(jsonJudge ==1){
						$(".text").text("");
						$(".text").append("<p>Sorry upload error</p><br/><p>Your json is Malformed,It lack the outputEvent object</p><br/><p>Plz check your Json again</p>");
					}
					else if(jsonJudge ==2){
						$(".text").text("");
						$(".text").append("<p>Sorry upload error</p><br/><p>Your json is Malformed,It lack the inputEvent object</p><br/><p>Plz check your Json again</p>");
					}
					else if(jsonJudge ==3){
						$(".text").text("");
						$(".text").append("<p>Sorry upload error</p><br/><p>Your json is Malformed,It lack the kernelResourceProvider object</p><br/><p>Plz check your Json again</p>");
					}
					else if(jsonJudge ==4){
						$(".text").text("");
						$(".text").append("<p>Sorry upload error</p><br/><p>Your json is Malformed,It lack the optionalResourceProvider object</p><br/><p>Plz check your Json again</p>");
					}
					else{
					$(".text").text("");
					$(".text").append("<p> Congratulation !! Upload Success!!~~</p><br/><p> You can continue to enjoy your application~~~</p>");	
					alert(JSON.stringify(uploadBrickTestJson));
					}
				}
		
		</script>
		<style type ="text/css">
			p{
				font-family: "Pacifico", Georgia, "Times New Roman", serif;
				font-weight: bold;
				font-size : 0.8em;
				text-align: center;
			}
			.test1{
				background-color : white;
				height : 70%;
			}
		</style>
</head>
<body onload = "begin()">
		<div data-role="page" id="page1">
		<div data-theme="b" data-role="header">
			<h3>
					Upload
			</h3>
		</div>
		<div data-role="content">
		<div class = "test1">
			<div class= "text">	
				
			</div>
			<br/>
			<br/>
			<br/>
			<br/>
		</div>
			<div class="goBack">
			<input  type="button" value="GoBack"  onclick = 'location.replace("upload.html");' >
			</div>
		</div>
	</div>
</body>
</html>