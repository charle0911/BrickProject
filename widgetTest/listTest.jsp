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


<meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"> 
<head>
  <!--jquery mobile lib -->
  <script src="js/jquery-1.10.0.min.js"></script>
    <link rel="stylesheet" href="css/jquery.mobile-1.3.1.min.css" />
  <script src="js/jquery.mobile-1.3.1.min.js"></script>
  <!--jquery UI lib -->
  <link rel="stylesheet" href="css/jquery-ui.css" />
    <script src="js/jquery-1.9.1.js"></script>
    <script src="js/jquery-ui.js"></script>

  <!--HTML5 lib -->
    <script src="kinetic-v4.5.1.min.js"></script>
    
    <!-- (Start) Add jQuery UI Touch Punch -->
    <script src="jquery.ui.touch-punch.min.js"></script>
    
    <!-- gallery lib-->
    <script src="js/modernizr.custom.17475.js"></script>
    <!--Canvas lib-- >
    <script src="kinetic-v4.5.1.min.js"></script>
    
    
  <!--let brcik item can be storable -->
  
  <script type="text/javascript">
/////////////////////////////////////////////////////

         selectBrick =  <%= selectBrick%>;
        var appliaction_id = <%= applicationID%>;
        var id = <%= id%>;
        var action = '<%= action%>';
      
      function loadImg(){

      
      //alert(selectBrick.brick[0].data.outputEvent[0].id);
      alert(JSON.stringify(selectBrick)); 

      //$("#brickCart ul").html();
      for(var i=0;i<selectBrick.num;i++)
      { 

       // alert(selectBrick.brick[i].pic);
        
        $("#brickCart ul").append("<li style = 'width:200px'><a href='#' onclick='_addBrick(this,"+i+")'><img style='height:100px;width=200px;' src='"+selectBrick.brick[i].pic+"'/></a></li>");
        
      }  
      $("#brickCart").elastislide();
        //alert(selectBrick);
        
      }

     
  //////////////////////////////////////////
    
    currentView  = "brick-list1";

    $(function() {
    $( "#"+currentView ).sortable();
    $( "#"+currentView ).disableSelection();
    });

      $(function() {
          // Bind the tapholdHandler callback function to the taphold event on div.box
            $( "#tw" ).on( 'taphold', tapholdHandler );
 
            // Callback function references the event target and adds the 'tap' class to it
            function tapholdHandler( event ) {
            alert("hellow");
            }
      });

   // tabs effect
       $(function() 
    {
      $( "#tabs" ).tabs();
    });

       


       function changePage(num)
       {
            currentView  = "brick-list"+num;
            $( "#"+currentView ).sortable();
            $( "#"+currentView ).disableSelection();
       }
       

    </script>



    <link rel="shortcut icon" href="../favicon.ico"> 
    <link rel="stylesheet" type="text/css" href="css/demo.css" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <link rel="stylesheet" type="text/css" href="css/elastislide.css" />
    <link href='http://fonts.googleapis.com/css?family=PT+Sans+Narrow&v1' rel='stylesheet' type='text/css' />
    <link href='http://fonts.googleapis.com/css?family=Pacifico' rel='stylesheet' type='text/css' />




    <noscript>
      <style>
        .es-carousel ul{
          display:block;
        }
    </style>

    </noscript>
    <style type="text/css">
    
     li img{ align:center;width:200px;}
     #brick-list li{vertical-align: middle;list-style:none; }
     img.center { display: block; margin-left: auto; margin-right: auto; }
    </style>
    
    <link rel="stylesheet" type="text/css" href="css/demo.css" />
    <link rel="stylesheet" type="text/css" href="css/elastislide.css" />
    <link rel="stylesheet" type="text/css" href="css/custom.css" />


</head>
<body onload = "loadImg()">
<div>
  <div class="container">
    
    <div class="header">
      <div class="clr"></div>
    </div><!-- header -->
      
    <div data-theme="b" data-role="header" id='shoppingCart' >
      <h1 >Brick Cart</h1>
      <a data-role="button" href="#page1" class="ui-btn-right">
        Detail
      </a>
      <div id="rg-gallery" class="rg-gallery">
        <div class="rg-thumbs">
        <!-- Elastislide Carousel Thumbnail Viewer -->
          <div class="es-carousel-wrapper">
              <div class="es-nav">
                <span class="es-nav-prev">Previous</span>
                <span class="es-nav-next">Next</span>
              </div>
              <div class="es-carousel" id="brickCart">
                <ul style="height:100px">
                  
                  
                
                </ul>
              </div>
          </div>
          <!-- End Elastislide Carousel Thumbnail Viewer -->
        </div><!-- rg-gallery -->                                                                                                                                                                                     
        
      </div><!-- header -->
    </div><!-- container -->
    
    <script type="text/javascript" src="js/jquery.tmpl.min.js"></script>
    <script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
    <script type="text/javascript" src="js/jquery.elastislide.js"></script>
    <script type="text/javascript" src="js/gallery.js"></script>
</div>
<div data-role="content">
<div id='tabs'>

          <ul><!-- the page of view-->
          <li><a href="#tabs-1" onclick="changePage(1)" >view 1</a></li>
          <li><a href="#tabs-2" onclick="changePage(2)"> + </a></li>
          </ul>

        <!-- 預設的view-->
        <div id="tabs-1"> 
          <ul id="brick-list1" data-role="listview" data-theme="d" data-inset="true" itemscope itemtype="http://schema.org/ItemList">
           
          </ul>

        </div>
        
      <div id="tabs-2">
        <ul id="brick-list2" data-role="listview" data-theme="d" data-inset="true" itemscope itemtype="http://schema.org/ItemList">

           
          </ul>

       </div>

  <button id="finish">finish</button>
</div>
</div>
<script defer="defer">
  
singerJsonStr   = '{ "brick": { "id": "b0001", "name": "Artist", "outputEvent": [ { "trigger": "click", "id": "oe01", "event": "singerName" } ], "inputEvent": [ { } ], "kernalResourceProvider": [ { "type": "search albums", "defaultResourceUrl": "http://", "action": "read" }, { "type": "search songs", "defaultResourceUrl": "http://", "action": "read" } ], "optionalResourceProvider": [ { "type": "write click times", "defaultResourceUrl": "http://", "action": "write" }, { "type": "read click times", "defaultResourceUrl": "http://", "action": "read" } ] } }';
songListJsonStr ='{ "brick": { "id": "b0002", "name": "SongList", "outputEvent": [ { "trigger": "click", "id": "oe02", "event": "songName" } ], "inputEvent": [ { "id": "ie02", "event": "singerName" } ], "kernalResourceProvider": [ { "type": "search albums", "defaultResourceUrl": "http://", "action": "read" }, { "type": "search songs", "defaultResourceUrl": "http://", "action": "read" } ], "optionalResourceProvider": [ { "type": "write click times", "defaultResourceUrl": "http://", "action": "write" }, { "type": "read click times", "defaultResourceUrl": "http://", "action": "read" } ] } }';
videoJsonStr    ='{ "brick": { "id": "b0003", "name": "Video", "outputEvent": [ {} ], "inputEvent": [ { "id": "ie03", "event": "songName" } ], "kernalResourceProvider": [ { "type": "search albums", "defaultResourceUrl": "http://", "action": "read" }, { "type": "search songs", "defaultResourceUrl": "http://", "action": "read" } ], "optionalResourceProvider": [ { "type": "write click times", "defaultResourceUrl": "http://", "action": "write" }, { "type": "read click times", "defaultResourceUrl": "http://", "action": "read" } ] } }';
lyricJsonStr    ='{ "brick": { "id": "b0004", "name": "Lyric", "outputEvent": [ {} ], "inputEvent": [ { "id": "ie03", "event": "songName" } ], "kernalResourceProvider": [ { "type": "search albums", "defaultResourceUrl": "http://", "action": "read" }, { "type": "search songs", "defaultResourceUrl": "http://", "action": "read" } ], "optionalResourceProvider": [ { "type": "write click times", "defaultResourceUrl": "http://", "action": "write" }, { "type": "read click times", "defaultResourceUrl": "http://", "action": "read" } ] } }';
var BrickData = new Array();
BrickData.push(JSON.parse(singerJsonStr));
BrickData.push(JSON.parse(songListJsonStr));
BrickData.push(JSON.parse(videoJsonStr));
BrickData.push(JSON.parse(lyricJsonStr));

function randomColor()
{
  var arrHex = ["0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"];
  var strHex = "#";
  var index;
  for(var i = 0; i < 6; i++) {
    index = Math.round(Math.random() * 15);
    strHex += arrHex[index];
  }
  return strHex;

}

function autoMatch(newObj,stage,layer)
{
    

    var checkOutputEvent =selectBrick.brick[newObj.code].data.outputEvent[0].event;
    var checkInputEvent =selectBrick.brick[newObj.code].data.inputEvent[0].event;

    for(var j=0;j< currentlistArr.length-1;j++) //檢查view中的所有Brick不包含目前新增的
    {
      var currentNum = currentlistArr[j].code;//目前加入的清單中Brick的真正編號

      var inputEvent = selectBrick.brick[currentNum].data.inputEvent[0].event; 
      var outputEvent = selectBrick.brick[currentNum].data.outputEvent[0].event;

     
      //alert("test");
      
      if(inputEvent==checkOutputEvent&&inputEvent!="none") //先檢查每個Brick的input和新增的Brick的output是否相同
      {
         //alert(selectBrick.brick[currentNum].name)
          var checkName = new Object();
          checkName.name = selectBrick.brick[newObj.code].name;
          checkName.checked = "true";
          currentlistArr[j].event.push(checkName);
         // alert(currentlistArr[j].event.length);
          alert("dd"+inputEvent);
          alert(checkOutputEvent);
          var posX =  $(window).width()*0.04;
          var posY =  $(window).width()*0.04 ;
          var color;
          if( newObj.color==null) //檢查output是否有顏色有顏色代表已找過並確定顏色
            { //alert("dfdsf");
             color = randomColor(); //亂數給定顏色

             var outCircle = new Kinetic.Circle({
             x: posX+$(window).width()*0.6+$(window).width()*0.04*2,
             y: posY,
             radius: $(window).width()*0.03,
             fill:color,
             stroke: 'black',
             strokeWidth: 0.5,
             });
             outCircle.on('mouseover', function() {
             document.body.style.cursor = 'pointer';//鼠標變手指
             });
             outCircle.on('mouseout', function() {
             document.body.style.cursor = 'default';
             });
             
             
             //circle click event
             outCircle.on('click', function()
             {
             
             $( "#dialog" ).dialog();
            
             
             });
             
             // add the shape to the layer
             newObj.layer.add(outCircle);
             // add the layer to the stage
             newObj.stage.add(newObj.layer);
             newObj.color = color;

          }
          else //代表已經有檢查過並設定顏色
            color =  newObj.color;
         
          var circle = new Kinetic.Circle({
             x: posX,
             y: posY,
             radius: $(window).width()*0.03,
             fill:color,
             stroke: 'black',
             strokeWidth: 0.5,
             });
             circle.on('mouseover', function() {
             document.body.style.cursor = 'pointer';//鼠標變手指
             });
             circle.on('mouseout', function() {
             document.body.style.cursor = 'default';
             });
             
            // alert("fdfdsf"+currentlistArr[j].event.length);
             var target = j;
             //circle click event
             circle.on('click', function()
             {
             
          // alert("d!!"+currentlistArr[target].event.length);
           $( "#dialog" ).dialog();
           $( "#default" ).html("");
           for(var i=0;i<currentlistArr[target].event.length;i++)
           {
            $( "#default" ).append("<input type='checkbox' id='checkbox-1' class='custom'"+
              "checked="+currentlistArr[target].event[i].checked+"/><label for='checkbox-1'>"+currentlistArr[target].event[i].name+"</label>");
            }
              //$( ".selector" ).collapsibleset( "refresh" );
              //$("input[type='checkbox']").checkboxradio("refresh");
             $( "#default" ).children().page();
            // $( "#menu" ).menu();
           
             });
             
             // add the shape to the layer
             alert("currentNum:"+currentNum);

             var inLayer =currentlistArr[j].layer.add(circle);
             // add the layer to the stage
             currentlistArr[j].stage.add(inLayer);


      }


      if(checkInputEvent==outputEvent&&outputEvent!="none") //先檢查每個Brick的output和新增的Brick的input是否相同
      {
          newObj.event.push(selectBrick.brick[currentNum].name);

          var posX =  $(window).width()*0.04;
          var posY =  $(window).width()*0.04 ;
          var color;
          if(currentlistArr[j].color==null) //檢查output是否有顏色有顏色代表已找過並確定顏色
            { //alert("dfdsf");
             color = randomColor(); //亂數給定顏色

             var outCircle = new Kinetic.Circle({
             x: posX+$(window).width()*0.6+$(window).width()*0.04*2,
             y: posY,
             radius: $(window).width()*0.03,
             fill:color,
             stroke: 'black',
             strokeWidth: 0.5,
             });
             outCircle.on('mouseover', function() {
             document.body.style.cursor = 'pointer';//鼠標變手指
             });
             outCircle.on('mouseout', function() {
             document.body.style.cursor = 'default';
             });
             
             
             //circle click event
             outCircle.on('click', function()
             {
             
             $( "#dialog" ).dialog();
             //$( "#menu" ).menu();
             
             });
             
             // add the shape to the layer
            currentlistArr[j].layer.add(outCircle);
             // add the layer to the stage
             currentlistArr[j].stage.add(currentlistArr[j].layer);
             currentlistArr[j].color = color;

          }
          else //代表已經有檢查過並設定顏色
            color =  currentlistArr[j].color;
         
          var circle = new Kinetic.Circle({
             x: posX,
             y: posY,
             radius: $(window).width()*0.03,
             fill:color,
             stroke: 'black',
             strokeWidth: 0.5,
             });
             circle.on('mouseover', function() {
             document.body.style.cursor = 'pointer';//鼠標變手指
             });
             circle.on('mouseout', function() {
             document.body.style.cursor = 'default';
             });
             
             
             //circle click event
             circle.on('click', function()
             {
             
             $( "#dialog" ).dialog();
             

             //$( "#menu" ).menu();
             
             });
             
             // add the shape to the layer
             alert("currentNum:"+currentNum);

             var inLayer =newObj.layer.add(circle);
             // add the layer to the stage
            newObj.stage.add(inLayer);

      }
      
     
  

 }
   
}

function _drawImage(imageObj,stage,layer) { 
        
      
          //////////////////加入圖片
          // darth vader
          var darthVaderImg = new Kinetic.Image({
          image: imageObj,
          width:  $(window).width()*0.6,
          height: $(window).width()*0.6*0.5,
          x: $(window).width()*0.04*2,
          y: 0,
          id:'first',
          
          });
          
          // add cursor styling
          darthVaderImg.on('mouseover', function() {
          document.body.style.cursor = 'pointer';//鼠標變手指
          });

          darthVaderImg.on('mouseout', function() {
          document.body.style.cursor = 'default';
          });

          brickGroup.add(darthVaderImg);
          layer.add(brickGroup);
          stage.add(layer);
      }

/*function ref()
{
  alert("fdf");
  $("#brick-list1").listview('refresh'); //refresh listview 
}*/
var currentlistArr = new Array();
 function _addBrick(item,num) //add Brikcs from cart to specific view
       {
           

            listObj = new Object();
           listObj.code = num;
            
             $(item).parent().remove()  // remove from brick cart 

             //alert(selectBrick1.brick[num].pic);
             $("#"+currentView).append(
              " <li class='ui-li ui-li-static ui-btn-up-d ui-last-child' id='list"+num+"'>"+
              
              "</li>"
             );
             
//"<img src='"+selectBrick1.brick[num].pic+"'>
             
             var stage = new Kinetic.Stage({
               container: "list"+num,
               width    : $(window).width(),
               height   :$(window).width()*0.6*0.5
             });
             
             var layer = new Kinetic.Layer();

             brickGroup = new Kinetic.Group({
             x: 0,
             y: 0,
             height:250,
             draggable: false,
             });
             
             var imageObj = new Image();
             //alert(selectBrick.brick[num].pic);
             imageObj.src = selectBrick.brick[num].pic;
             //imageObj.src ="widgetTest9/images/1.png";//selectBrick.brick[num].pic;
             imageObj.onload = function() {
             _drawImage(this,stage,layer);
             };
             
             listObj.layer = layer;
             listObj.stage = stage;
             listObj.event = new Array();
             currentlistArr.push(listObj);
             autoMatch(listObj,stage,layer);             

       }



//finish  click event
 $("#finish").click(function(){
  

    var $lists = $("#"+currentView+"li");
    var comObj = new Object();
    var app = new Array();
    comObj.application = app;
    var view = {
                "id": "v0001",
                "sequence" : 1,
                "name": "music",
                "layout": "VerticalLayout",
                "forbiddenConnection": [
                    {
                        "source": {
                            "brick": "b0001",
                            "": "oe01"
                        },
                        "destination": {
                            "brick": "b0002",
                            "event": "ie01"
                        }
                    }
                ],
                "enforcedConnection": [
                    {}
                ]
              };
    var bricks = new Array();
   // alert(view.brick);
   // app.push(view);
   var seq =1;
     $('#brick-list1 li').each(function(index,val){
      var id = $(val).attr("id");
      var num = id.slice(4);

      var brickObj = new Object();
      brickObj.id = BrickData[num-1].brick.id;
      brickObj.sequence =seq;
      

      var binding = new Array();
      var typeObj = new Object();
      typeObj.type="search"+BrickData[num-1].brick.name;
      typeObj.resourceUrl="http://";
      binding.push(typeObj);
      brickObj.resourceBinding =binding;
      bricks[seq-1] = brickObj;
      
 
      //bricBrickData[num]
      //alert(index+'-'+  BrickData[num].brick.id);
      seq++;
        }) ;   
     view.bricks = bricks;
     var viewObj = new Object();
     viewObj.veiw = view;
     app.push(viewObj);

     alert(JSON.stringify(comObj));
   
});
</script>
<div id="dialog" title="Select Action" style="Background-color:white;display:none">
    <div data-role='collapsible' data-content-theme='c' class="selector">
    <h2>Forbidden Default Connections</h2>
    <div data-role="fieldcontain">
    <fieldset data-role="controlgroup" data-theme="c" id="default">
    

    </fieldset>
</div>
    </div>
    <div data-role='collapsible' data-content-theme='c'>
    <h2>Disable Enfroced Connetctions</h2>
    </div>
</div>

</body>
</html>