<%
	session.invalidate();
%>
<html>
	<head>
		<meta name="viewport" content="width=device-width initial-scale=1" />
		<title>WidgetTest</title>
		<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
		<script src="http://code.jquery.com/jquery-1.8.2.min.js"></script>
		<link rel="stylesheet" href="http://code.jquery.com/mobile/1.2.0/jquery.mobile-1.2.0.min.css" />
		<script src="http://code.jquery.com/mobile/1.2.0/jquery.mobile-1.2.0.min.js"></script>
		<script type="text/javascript">
			function changPage(){
				location.replace("Register.html");
			}
			function check_data()
			{
				if(document.myForm.account.value.length ==0)
					alert("account can't space");
				else if(document.myForm.password.value.length ==0)
					alert("password can't space");
				else{
					var user_account = $("#logAccount").val();
					var user_password = $("#logPassword").val();
					$.post("AccountService" ,{calledFor:"login",account:user_account,password:user_password}, function(success) 
					{
						if(success == 'true')
							{
								location.replace("main.jsp");
							}
						else
							alert(success);	
						}
					);
				}
			}
		</script>
		<style type = "text/css">
			.content { font-size:.8em;}
		</style>
	</head>
	<body>
		<div data-role="page" id="page1">
			<div data-theme="b" data-role="header">
				<h4>
					BrickBaseService
				</h4>
			</div>
			<div data-role="content">
				<h4>
					Login: <span class = "other">(or <a id = "PageRefresh" href="" onClick = "changPage()">creat an account</a>)</span>
				</h4>
				<form action = "" method = "post" name = "myForm">
					<div data-role="fieldcontain">
						<label for="logAccount">
							Account:
						</label>
						<input name="account" id="logAccount" placeholder="" value="" type="text" data-mini="true">
					</div>
					<div data-role="fieldcontain">
						<label for="logPassword">
							Password:
						</label>
						<input name="password" id="logPassword" placeholder="" value="" type="password">
					</div>
					<input type="button" value="Log In" onClick = "check_data()">
				</form>
			</div>
		</div>
	</body>
</html>