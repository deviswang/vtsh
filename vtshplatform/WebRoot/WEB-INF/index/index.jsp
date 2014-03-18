<%@ page language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>  
<html>  
<head>  
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
    <title>VTSH SERVICE PLATFORM</title>  
    <link rel="stylesheet" type="text/css" href="themes/default/easyui.css">  
    <link rel="stylesheet" type="text/css" href="themes/icon.css">  
    <script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>  
    <script type="text/javascript" src="js/jquery.easyui.min.js"></script>  
  
   <srcipt type="text/javascript" src="js/main.js"></srcipt>
   
   <srcipt type="text/javascript" src="js/methods.myLoad.js"></srcipt>     
  <script type="text/javascript">
  
  function showInf(){
  		var url="transInf.action";
  		var text = {title:'Transaction Information',
  			 id:'tranInf',
  			 closable:true,
  			 cache:false,
  			 content:'<iframe name="cc" src="'+url+'" frameborder="0" height="100%" width="100%" ccrolling="auto"></iframe>'
  			 };
  		var title='Transaction Information';
  		 if ($("#tabs").tabs('exists', title)) { 
  		 	 $('#tabs').tabs('select', title); 
  		 }else{
  		 	$('#tabs').tabs('add',text);
  		 }
  	}
  
  	//clearing info
  function clearInf(){
		var url="toTxnClearing.action";
		var text = {title:'Clearing Information',
			 id:'clientid',
			 closable:true,
			 cache:false,
			 content:'<iframe name="cc" src="'+url+'" frameborder="0" height="100%" width="100%" ccrolling="auto"></iframe>'
			 };
		var title='Clearing Information';
		 if ($("#tabs").tabs('exists',title)){ 
		 	 $('#tabs').tabs('select',title); 
		 }else{
		 	 $('#tabs').tabs('add',text);
		 }
	}
	
	//Reconciliation Info
  function reconInf(){
		var url="toRecon.action";
		var text = {title:'Reconciliation Information',
			 id:'clientid',
			 closable:true,
			 cache:false,
			 content:'<iframe name="cc" src="'+url+'" frameborder="0" height="100%" width="100%" ccrolling="auto"></iframe>'
			 };
		var title='Reconciliation Information';
		 if ($("#tabs").tabs('exists',title)) { 
		 	 $('#tabs').tabs('select',title); 
		 }else{
		 	 $('#tabs').tabs('add',text);
		 }
	}
  
  function acntInf(){
	  
  		var url = "acntInf.action";
  		var text2={title:'Merchant Information',
  			 	id:'acntInf',
  			 	closable:true,
  			 	cache:false,
  			 	content:'<iframe name="cc" src="'+url+'" frameborder="0" height="100%" width="100%" ccrolling="no"></iframe>'
  			 	}
  		var title='Merchant Information'
  		if( $('#tabs').tabs('exists', title)){
  			 $('#tabs').tabs('select', title); 
  		}else{
  			$('#tabs').tabs('add',text2
  			);
  		}
  	}

  
  	 function addMerchant(){
  		var url = "toAddMerchant.action";
  		var text2={title:'Add Merchant',
  			 	id:'acntInf',
  			 	closable:true,
  			 	cache:false,
  			 	content:'<iframe name="cc" src="'+url+'" frameborder="0" height="100%" width="100%" ccrolling="no"></iframe>'
  			 	}
  		var title='Add Merchant'
  		if( $('#tabs').tabs('exists', title)){
  			 $('#tabs').tabs('select', title); 
  		}else{
  			$('#tabs').tabs('add',text2);
  		}
  	}

  	function registerMerchant(){
  		var url = "registMerchant.action";
  		var text2={title:'Register Information',
  			 	id:'acntInf',
  			 	closable:true,
  			 	cache:false,
  			 	content:'<iframe name="cc" src="'+url+'" frameborder="0" height="100%" width="100%" ccrolling="no"></iframe>'
  			 	}
  		var title='Register Information';
  		if( $('#tabs').tabs('exists', title)){
  			 $('#tabs').tabs('select', title); 
  		}else{
  			$('#tabs').tabs('add',text2);
  		}
  	}

  	function upgradingMerchant(){
  		var url = "upgradingMerchant.action";
  		var text2={title:'Upgrading Information',
  			 	id:'acntInf',
  			 	closable:true,
  			 	cache:false,
  			 	content:'<iframe name="cc" src="'+url+'" frameborder="0" height="100%" width="100%" ccrolling="no"></iframe>'
  			}
  		var title='Upgrading Information';
  		if( $('#tabs').tabs('exists', title)){
  			  $('#tabs').tabs('select', title); 
  		}else{
  			  $('#tabs').tabs('add',text2);
  		}
  	}


  	function declineMerchant(){
  		var url = "declineMerchant.action";
  		var text2={title:'Decline Information',
  			 	id:'acntInf',
  			 	closable:true,
  			 	cache:false,
  			 	content:'<iframe name="cc" src="'+url+'" frameborder="0" height="100%" width="100%" ccrolling="no"></iframe>'
  			 	}
  		var title='Decline Information';
  		if( $('#tabs').tabs('exists', title)){
  			 $('#tabs').tabs('select', title); 
  		}else{
  			$('#tabs').tabs('add',text2
  		);
  	  }
  	}

 	
  /**  
 * 创建新选项卡  
 * @param tabId    选项卡id  
 * @param title    选项卡标题  
 * @param url      选项卡远程调用路径  
 */  
 function addTab(tabId,title,url){  
  //如果当前id的tab不存在则创建一个tab  
   if($("#"+tabId).html()==null){  
        var name = 'iframe_'+tabId;  
        $('#centerTab').tabs('add',{  
            title: title,           
            closable:true,  
           	cache : false,  
            //注：使用iframe即可防止同一个页面出现js和css冲突的问题  
            content : '<iframe name="'+name+'"id="'+tabId+'"src="'+url+'" width="100%" height="100%" frameborder="0" scrolling="no" ></iframe>'  
        });  
    }  
}  
  
 function logout(){
	 var msg =  'Dear <%=session.getAttribute("User") %> , '+'Are you sure to logout?'
	 $.messager.confirm('Confirm Information', msg,function(r){    
		    if (r){    
		    	window.location.href="logout.action";
		    }    
		});  
	 }

 </script>
  
  <style type="text/css">
  	a{
  		margin:2px;
  	}
  	
  	#logout{
  		border:1px solid orange;
  		background:#F5DEB3;
  		/*margin-top:42px;*/
  		margin-left:120px;
  		width:60px;
  		height:18px;
  		font-size:9px;
  	}
  	
  	
  	#log div{
  		/*border:1px solid black;*/
  		margin:0px;
  		padding:0px;
  		height:26px;
  	}
  	
  	.menuButton a{
  		text-align:left;
  	}
  </style>
</head>  
<body class="easyui-layout" style="font-family:Arial;"> 
	<!-- North Content --> 
    <div data-options="region:'north',split:false"  style="height:80px;background:url(images/bk.png);">  
	
	       <table style="width:100%;height:100%;padding:0;margin:0;"><tr>
	       <td width="60"><img alt="VTSH platform" src="images/vtLogo.PNG" ></td>
	       <td>
		   		<span style="color:#E9967A;font-size:32px;margin-left:30px; font-family:Serif,Georgia,Arial">VTSH SERVICE PLATFORM</span>
		   </td>
	       <td id="log" style="width:200px;margin:0px;padding:0px;">
<!--	       	<table style="height:100%;margin:0px;"><tr><td>-->
<!--	       		Welcome xxx-->
<!--	       	</td>-->
<!--	       	</tr>-->
<!--	       	<tr><td>	<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-back'"  id="logout">Logout<a/>-->
<!--	       	</td>-->
<!--	       	</tr>-->
<!--	       	</table>-->
<!--	        <a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-back'"  id="logout">Logout<a/>-->
			<div style="color:pink;font-size:16px;">Welcome : <%=session.getAttribute("User") %></div>
	       	<div ><a class="easyui-linkbutton" plain="true" onclick="logout();" data-options="iconCls:'icon-back'"  id="logout">Logout<a/></div>
	       </td>
	       </tr></table>
    </div>  
    <!-- Main Menu  -->
    <div data-options="region:'west',split:false" title="Main Menu" style="width:200px;padding1:1px;overflow:hidden;background:#F5FFFA">  
    	<%@include file="menu.jsp" %>
    </div> 
    <!-- Main Content --> 
    <div data-options="region:'center'"  style="overflow:hidden;">  
        <div id="tabs" class="easyui-tabs" data-options="fit:true,border:false">  
            <div id="mainTab" title="Home" style="padding:20px;overflow:hidden;" >   
            	<span style="color:blue;font-size:26px;">WELCOME TO </span>
		   		<span style="color:blue;font-size:26px;">VTSH SERVICE PLATFORM</span>
            </div>  
        </div>  
    </div> 
     
    <!-- footer layout -->
     <div data-options="region:'south',split:false" style="height:35px;padding:2px;background:#efefef;">  
     	<div class="panel-header panel-title" style="text-align: center;">Copyright © 2013-2014 VTPayment. All rights reserved.</div>
    </div> 
	<div id="tabsMenu" class="easyui-menu" style="width:120px;">   
 		<div name="close">Close</div>   
		<div name="Other">Close Others</div>   
		<div name="All">Close All</div> 
	</div>   
</body>  
</html>  