<html>
	<head>
		<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"> 
		<title>WidgetTest</title>
		<!--jquery mobile lib -->
		<script src="http://code.jquery.com/jquery-1.10.0.min.js"></script>
		<link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.1/jquery.mobile-1.3.1.min.css" />
		<script src="http://code.jquery.com/mobile/1.3.1/jquery.mobile-1.3.1.min.js"></script>
		<!--jquery UI lib -->
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
		<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
		<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

		<!--HTML5 lib -->
		<script src="kinetic-v4.5.1.min.js"></script>

		<!-- (Start) Add jQuery UI Touch Punch -->
		<script src="jquery.ui.touch-punch.min.js"></script>

		<!-- gallery lib-->
		<script src="js/modernizr.custom.17475.js"></script>
		<script type="text/javascript" src="js/jquery.tmpl.min.js"></script>
		<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
		<script type="text/javascript" src="js/jquery.elastislide.js"></script>
		<script type="text/javascript" src="js/gallery.js"></script>
		<link rel="stylesheet" type="text/css" href="css/demo.css" />
		<link rel="stylesheet" type="text/css" href="css/style.css" />
		<link rel="stylesheet" type="text/css" href="css/elastislide.css" />
		<link href='http://fonts.googleapis.com/css?family=PT+Sans+Narrow&v1' rel='stylesheet' type='text/css' />
		<link href='http://fonts.googleapis.com/css?family=Pacifico' rel='stylesheet' type='text/css' />
		<script type="text/javascript">
		/*
			此jspc會因使用者動作而產生兩筆session  
			當使用者選擇application時 產生名為 applicationID 的 session
				1.applicationID 
			當使用者選擇 brick並且經由購物車同整時 產生名為 selectBrick 的 session
				2.selectBrick  為JSON型態-----在使用者選完想要的brick時按下GO TO Designer就會將所選擇的Brick包成JSON裡面還有的物件為 
					(1) id
					(2) pic
		*/
			var getJson;
			var getJsonAppliction;
			
			<!--------用於放置目前Cart上Brick的Id和pic url 用於做JSON-------->
			
			var selectBrickArray = [];
			//var selectBrickPic =[];
			
			<!--------給cart上的Brick ID-------->
			
			var index = 0;
			
			<!--------swap-------->
			
			function swap(x,y){
				var step;
				step = x;
				x = y;
				y = step;
			}
			
			
			
			<!--------sort array-------->
			
			function deletSort(brickSite){
				for(var i ; i < (brickSite-1) ; i++){
					swap(selectBrickArray[brickSite-1],selectBrickArray[brickSite]);
					//swap(selectBrickPic[brickSite-1],selectBrickPic[brickSite]);
					}
			}
			
			
			
			<!--------delete Action-------->
			
			function deleteBrick(deleteBrick_id,brick_id){
				//alert("ffds");
				var deletBriclNumber;
				var step = '#'+brick_id;
				for(var i in selectBrickArray){
					//alert(selectBrickArray[i]);
					if( selectBrickArray[i].id == deleteBrick_id)
					{
						//alert(selectBrickArray[i]);
						deletBriclNumber = i;
						break;
					}
				}
				deletSort(deletBriclNumber);
				selectBrickArray.pop();
				
				//selectBrickPic.pop();
				


				//alert(selectBrickArray);
				//alert(selectBrickPic);
				//alert(step);
				$(step).remove();
			}
			<!--------Just Alert-------->
			function cartHead(){
				alert("You can delete the brick you choose when you click the pic in the cart");
			}
			
			
			
			<!--------Add information to array and add brick into cart-------->
			
			function chooseBrick(brickNumber){
				//alert(brickNumber);
				var selectedItem = new Object();
				selectedItem.id = getJson.brick[brickNumber].brick_id;
				selectedItem.pic = getJson.brick[brickNumber].brick_pic;
				selectedItem.name = getJson.brick[brickNumber].brick_name;
				selectedItem.url = getJson.brick[brickNumber].URL;
				var ssssss  =  JSON.stringify(getJson.brick[brickNumber].brick_data);
				selectedItem.data =  JSON.parse(ssssss);
				selectBrickArray.push(selectedItem);
				
				//alert(JSON.stringify(getJson.brick[brickNumber].brick_data).replace(/\\/g,""));
				//selectBrickArray.push(getJson.brick[brickNumber].brick_id);
				//selectBrickPic.push(getJson.brick[brickNumber].brick_pic);

				//alert(getJson.brick[brickNumber].brick_id);
				var $aa  = $("<li id = "+index+"><a href='#' onclick =deleteBrick('"+getJson.brick[brickNumber].brick_id+"',"+index+")><img style='height:100px;' src='"+  getJson.brick[brickNumber].brick_pic + "' alt='image01' data-description='From off a hill whose concave womb reworded' /></a></li>");
				//alert(index);
				index++;
				Gallery.addItems( $aa );
				//alert(selectBrickArray);
			}
			
			
			
			<!--------When choose search appliction and select one-------->
			
			function chooseAppiction(applicationNumber){
				var application_id = getJsonAppliction.application[applicationNumber].application_id;
				//alert(application_id);
				$.post("SetSession.jsp" ,{applicationID_data:application_id,calledFor:"applicationID",action:"selectAppliction"}, function(success) {
						alert(success);
/*web change to designer*/		
						location.replace("testSession.jsp");
							}
						);
			}
			
			
			
			<!--------Get Brick Json-------->
			
			function getBrickServerJSON(){
				var brickTrail = $("#searchBrickId").val();
				//alert(brickTrail);
				//改成.ajax
				$.post("SearchService" ,{searchKey:brickTrail,calledFor:"brickSearch"}, function(jsonString) 
				{
					//alert(jsonString);
					getJson = JSON.parse(jsonString);
					alert(JSON.stringify(getJson));
					$("#brickSearch").text("");
					for(var i in getJson.brick){
						//alert(getJson.brick[i].brick_name);
						//alert(getJson.brick[i].brick_id);
						$("#brickSearch").append("<tr onclick = 'chooseBrick("+i+")'><td class = 'brickNameAndDescription'><div><img class = 'brickImg' src='"+getJson.brick[i].brick_pic+"'</div><div class = 'bdes'><h2 class = 'btitle'>"+getJson.brick[i].brick_name+"</h2><p class = 'bdesp'>"+getJson.brick[i].brick_description+"</p></div></td></tr>").trigger('create');
					}
				});
			}
			
			
			<!--------Get Application Json-------->
			
			function getApplicationServerJSON(){
				brickTrail = $("#searchApplicationId").val();
				$.post("SearchService" ,{searchKey:brickTrail,calledFor:"applicationSearch"}, function(jsonString) 
					{
						getJsonAppliction = JSON.parse(jsonString);
						$("#applicationSearch").text("");
						for(var i in getJsonAppliction.application){
							$("#applicationSearch").append("<tr onclick = 'chooseAppiction("+i+")'><td class = 'brickNameAndDescription'><div><img class = 'brickImg' src='"+getJsonAppliction.application[i].application_pic+"'</div><div class = 'bdes'><h2 class = 'btitle'>"+getJsonAppliction.application[i].application_name+"</h2><p class = 'bdesp'>"+getJsonAppliction.application[i].application_description+"</p></div></td></tr>").trigger('create');
						}
					});
			}
			
			
			<!--------Set Json and session-------->
			
			function setSeesionAndChangePage(){
				var jsonToDesginerStep = '{"brick":[';
				
				var cartObj = new Object();
				var itemArr = new Array();

				cartObj.num = selectBrickArray.length;
			
				
				for(var i in selectBrickArray){
					var item = new Object();
					item = selectBrickArray[i];
					itemArr.push(item);
				}
				cartObj.brick = itemArr;

				//alert(JSON.stringify(cartObj).replace(/\\/g,""));
				//alert("hellow");
				var cartObjStr  =JSON.stringify(cartObj).replace(/\\/g,"");
				var testJson = JSON.parse(cartObjStr);
				alert(cartObjStr);
				$.post("SetSession.jsp" ,{selectBrick_data:cartObjStr,input:"selectBrick",action:"selectBrick"}, function(success) {
						alert(success);
/*web change to designer*/		location.replace("listTest.jsp");
							}
						);
			}
		</script>
		<noscript>
		  <style>
			.es-carousel ul{
			  display:block;
			}
		  </style>
		</noscript>
		<style type ="text/css">

			.btitle{
				position: relative;
				text-align: center;
				font-size: 1.8em ;
				//font-family: "Pacifico", Georgia, "Times New Roman", serif;
			}
			.ui-btn-left{
				font-family: "Pacifico", Georgia, "Times New Roman", serif;
			}
			.bdesp{
				font-weight: bold;
				text-align: left;
				font : 0.7em "Trebuchet MS", sans-serif;
			}
			.brickImg{
				margin-top: .5em;
				padding : .2em;
				float : left;
				width : 76px;
				height : 61px;
			}
			.top{
				height :20%;
			}
			#test{
				color :white;
			}
			table{
				border-width:4px;
				border-style:dotted;
				border-color :#5e87b0;
			}
			tbody{
				
			}
			tr{
				border-width:4px;
				border-style:dotted;
				border-color :#5e87b0;
			}
		</style>
	</head>
	<body>
		<div data-role="page" id="page1">
			 <div data-theme="b" data-role="header" id='shoppingCart' >
				
				<a data-role="button" href="#" class="ui-btn-left" onclick = 'location.replace("main.jsp");'>
					Go Back
				</a>
				<h1 >Brick Cart</h1>

				<div id="rg-gallery" class="rg-gallery">
					  <div class="rg-thumbs">
						<!-- Elastislide Carousel Thumbnail Viewer -->
						<div class="es-carousel-wrapper" style="height:100px;">
						  <div class="es-nav" >
							<span class="es-nav-prev">Previous</span>
							<span class="es-nav-next">Next</span>
						  </div>
						  <div class="es-carousel">
							<ul style="height: 100px; width: 698px; display: block; margin-left: 0px;" >
							  <li><a href="#" onclick ='cartHead()'><img style="height:100px;" src="images/3.jpg" data-large="images/3.jpg" alt="image02" data-description="A plaintful story from a sistering vale" /></a></li>
							 </ul>
						  </div>
						</div>
						<!-- End Elastislide Carousel Thumbnail Viewer -->
					  </div><!-- rg-thumbs -->
				</div><!-- rg-gallery -->
			</div><!-- header -->
			<div data-role="content">
				<div data-role="collapsible-set" data-theme="b" data-content-theme='c'  data-mini="true">
					<div data-role="collapsible" data-collapsed="false">
						<h2 id = "test">Brick Search</h2>
							<input name="" id="searchBrickId" placeholder="" type="text"  size="30" autofocus/>	
										<input type="button" value="Search" onclick="getBrickServerJSON()">
							<table width='100%'  border = "1">
								<tbody width='100%' id = "brickSearch" >
									
								</tbody>
							</table>
					</div>
					<div data-role="collapsible" >
						<h2>Application Search</h2>
							<input name="" id="searchApplicationId" placeholder="" value="Music" type="text"  size="30" autofocus/>	
										<input type="button" value="Search" onclick="getApplicationServerJSON()">
							<table width='100%'>
								<tbody width='100%' id = "applicationSearch" >
									
								</tbody>
							</table>
					</div>
				</div>
			</div>
			<input id = "designerButton" data-theme="c" type="button" value="Go to Designer" onclick="setSeesionAndChangePage()">
		</div>
	</body>
</html>
