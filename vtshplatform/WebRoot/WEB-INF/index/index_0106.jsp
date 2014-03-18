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
  <script type="text/javascript">
  	function showInf(){
  		//alert($('#mainTab').html());
  		//$('#mainTab').attr('href','test.action');
  	
  		
  		$('#tabs').tabs('add',
  			{title:'Transaction Information',
  			 closable:true,
  			 cache:false,
  			 content:'<iframe name="cc" src="transInf.action" frameborder="0" height="100%" width="100%" ccrolling="auto"></iframe>'
  			 }
  		);
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
            content : '<iframe name="'+name+'"id="'+tabId+'"src="'+url+'" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>'  
        });  
    }  
}  
  	
  	
  	
  	
  	
  </script>
  
  <style type="text/css">
  	a{
  		margin:2px;
  	}
  </style>
</head>  
<body class="easyui-layout">  
    <div data-options="region:'north',split:false"  style="height:80px;">  
	
	       <table style="width:100%;height:100%;padding:0;margin:0;"><tr>
	       <td width="60"><img alt="VTSH platform" src="images/vtLogo.PNG" ></td>
	       <td>USER INFO</td>
	       <td>LOG OUT</td>
	       </tr></table>
    </div>  
<!--    <div data-options="region:'east',iconCls:'icon-reload',split:true" title="Tree Menu" style="width:180px;">  -->
<!--        <ul class="easyui-tree" data-options="url:'tree_data.json'"></ul>  -->
<!--    </div>  -->
    <div data-options="region:'west',split:false" title="Main Menu" style="width:200px;padding1:1px;overflow:hidden;">  
    	<%@include file="menu.jsp" %>
    </div>  
    <div data-options="region:'center'"  style="overflow:hidden;">  
        <div id="tabs" class="easyui-tabs" data-options="fit:true,border:false">  
            <div id="mainTab" title="Home" style="padding:20px;overflow:hidden;" >   
             Welcome to VTSH service platform!

            </div>  
            <div title="Accounts Information" data-options="closable:true" style="padding:20px;">
            	<table id="tt3" class="easyui-datagrid" style="width:800px;height:300px"
            	 title="account information">
            	</table>
            	<script>  
			        $(function(){  
			            $('#tt3').datagrid({  
			                title:'Account Information',  
			                iconCls:'icon-save',  
			                //width:100%,  
			                //height:100%,  
			                nowrap: false,  
			                striped: true,  
			                fit: true,  
			                url:'account.action',  
			                sortName: 'name',  
			                sortOrder: 'desc',  
			                idField:'_id.inc', 
			                singleSelect:true,
			               // frozenColumns:[[  
			                  //  {field:'ck',checkbox:true},  
			                  ////  {title:'Id',field:'_id.inc',width:80,sortable:true}  
			               // ]],  
			                columns:[[  
			                    {title:'Base Information',colspan:3},  
			                    {field:'opt',title:'Operation',width:100,align:'center', rowspan:2,  
			                        formatter:function(value,rec){  
			                            return '<span style="color:red">Edit Delete</span>';  
			                        }  
			                    }  
			                ],[  
			                	//{field:'ck',checkbox:true},  
			                    {field:'clientid',title:'clientid',width:120},  
			                    {field:'md5',title:'md5',width:120,rowspan:2},  
			                    {field:'unionpay.md5',title:'unionpay',width:150,rowspan:2
			                    }  
			                ]],
			                pagination:true,  
			                rownumbers:true,
			                pageList:[5,10,15,50] 
			            });  
			        });  
			        
			    </script>  
            	
            </div>  
            <div title="Transaction Information" data-options="iconCls:'icon-reload',closable:true" style="overflow:hidden;padding:2px;">  
                <table id="tt2" style="height:100%;width:100%;"></table>   
                 <script>  
			        $(function(){  
			            $('#tt2').datagrid({  
			                title:'Account Information',  
			                iconCls:'icon-save',  
			                //width:100%,  
			                //height:100%,  
			                nowrap: false,  
			                striped: true,  
			                fit: true,  
			                url:'test.action',  
			                sortName: 'name',  
			                sortOrder: 'desc',  
			                idField:'_id.inc', 
			                singleSelect:true,
			               // frozenColumns:[[  
			                  //  {field:'ck',checkbox:true},  
			                  ////  {title:'Id',field:'_id.inc',width:80,sortable:true}  
			               // ]],  
			                columns:[[  
			                    {title:'Base Information',colspan:3},  
			                    {field:'opt',title:'Operation',width:100,align:'center', rowspan:2,  
			                        formatter:function(value,rec){  
			                            return '<span style="color:red">Edit Delete</span>';  
			                        }  
			                    }  
			                ],[  
			                	//{field:'ck',checkbox:true},  
			                    {field:'gw_currency',title:'gw_currency',width:120},  
			                    {field:'gw_date',title:'gw_date',width:120,rowspan:2},  
			                    {field:'cycle_id',title:'Cycle_ID',width:150,rowspan:2}
			                    
			                ]],  
			                pagination:true,  
			                rownumbers:true,
			                pageList:[5,10,15,50] 
			            });  
			        });  
			        
			    </script>  
            </div>  
        </div>  
    </div>  
    <!-- footer layout -->
     <div data-options="region:'south',split:false" style="height:35px;padding:2px;background:#efefef;">  
     	<div class="panel-header panel-title" style="text-align: center;">Copyright © 2013 VTPayment. All rights reserved.</div>
<!--        <div  class="easyui-layout" data-options="fit:true,border:false" style="background:#ccc;" >-->
<!--        	  -->
<!--            <div data-options="region:'center'" ><span style="margin-left:40%; margin-bottom:20px;font-size:14px;">Copyright © 2013 VTPayment. All rights reserved.<span/></div>  -->
<!--        </div>  -->
    </div> 
    
	<div id="tabsMenu" class="easyui-menu" style="width:120px;">   
 		<div name="close">Close</div>   
		<div name="Other">Close Others</div>   
		<div name="All">Close all</div> 
	</div>   
    
   
</body>  
</html>  