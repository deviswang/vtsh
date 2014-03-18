 <%@ page language="java" pageEncoding="utf-8" %>         
          
<!--            <div title="Tab2" data-options="closable:true" style="padding:20px;">This is Tab2 width close button.</div>  -->
<!--            <div title="Tab3" data-options="iconCls:'icon-reload',closable:true" style="overflow:hidden;padding:2px;">  -->
<!DOCTYPE html>  
<html>  
<head>  
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
    <title>VTSH SERVICE PLATFORM</title>  
    <link rel="stylesheet" type="text/css" href="themes/default/easyui.css">  
    <link rel="stylesheet" type="text/css" href="themes/icon.css">
    <srcipt type="text/javascript" src="js/myload.js" ></srcipt>  
    <srcipt type="text/javascript" src="js/datagrid-filter.js"  ></srcipt>     
    <script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>  
    <script type="text/javascript" src="js/jquery.easyui.min.js"></script>  
   <srcipt type="text/javascript" src="js/main.js"></srcipt>             
   <script type="text/javascript">
	   	$.extend($.fn.form.methods, {
		myLoad : function (jq, param) {
		return jq.each(function () {
			load(this, param);
		});
		
		function load(target, param) {
			if (!$.data(target, "form")) {
				$.data(target, "form", {
					options : $.extend({}, $.fn.form.defaults)
				});
			}
			var options = $.data(target, "form").options;
			if (typeof param == "string") {
				var params = {};
				if (options.onBeforeLoad.call(target, params) == false) {
					return;
				}
				$.ajax({
					url : param,
					data : params,
					dataType : "json",
					success : function (rsp) {
						loadData(rsp);
					},
					error : function () {
						options.onLoadError.apply(target, arguments);
					}
				});
			} else {
				loadData(param);
			}
			function loadData(dd) {
				var form = $(target);
				var formFields = form.find("input[name],select[name],textarea[name]");
				formFields.each(function(){
					var name = this.name;
					var value = jQuery.proxy(function(){try{return eval('this.'+name);}catch(e){return "";}},dd)();
					var rr = setNormalVal(name,value);
					if (!rr.length) {
						var f = form.find("input[numberboxName=\"" + name + "\"]");
						if (f.length) {
							f.numberbox("setValue", value);
						} else {
							$("input[name=\"" + name + "\"]", form).val(value);
							$("textarea[name=\"" + name + "\"]", form).val(value);
							$("select[name=\"" + name + "\"]", form).val(value);
						}
					}
					setPlugsVal(name,value);
				});
				options.onLoadSuccess.call(target, dd);
				$(target).form("validate");
			};
			function setNormalVal(key, val) {
				var rr = $(target).find("input[name=\"" + key + "\"][type=radio], input[name=\"" + key + "\"][type=checkbox]");
				rr._propAttr("checked", false);
				rr.each(function () {
					var f = $(this);
					if (f.val() == String(val) || $.inArray(f.val(), val) >= 0) {
						f._propAttr("checked", true);
					}
				});
				return rr;
			};
			function setPlugsVal(key, val) {
				var form = $(target);
				var cc = ["combobox", "combotree", "combogrid", "datetimebox", "datebox", "combo"];
				var c = form.find("[comboName=\"" + key + "\"]");
				if (c.length) {
					for (var i = 0; i < cc.length; i++) {
						var combo = cc[i];
						if (c.hasClass(combo + "-f")) {
							if (c[combo]("options").multiple) {
								c[combo]("setValues", val);
							} else {
								c[combo]("setValue", val);
							}
							return;
						}
					}
				}
			};
		};
	}
});
   	
   //myDate format
   function myDateFormat(date){
		var y = date.getFullYear();
		var m = date.getMonth()+1;
		var d = date.getDate();
   		return y+"-"+m+"-"+d;
   }
   /*
   $(function(){
   		$('#startDate').datebox({
   			onSelect:function(date){ $('#startDate').val($('#startDate').datebox('getValue'));}
   		});
   		$('#endDate').datebox({
			onSelect:function(date){$('#endDate').val($('#endDate').datebox('getValue'));}
		});
   });
   */
  
   
   </script>
   <style type="text/css">
   	.subInf{
   		border-bottom:1px solid #ccc;
   		font-size:16px;
   		height:30px;
   		font-family:Arial;
   		text-align:center;
   		color:#276ea9;
   		background:#E0ECFF;
   	}
   #tranDetail td{
   		font-size:13px;
   		height:18px;
   		color:#276ea9;
   		font-family:Arial;
   	}
   	
    #tranDetail input{
    	border:0px;
    }
   #toolbar a{
   		border:1px solid #95B8E7;
   		background:#F0F8FF;
   		height:22px;
   	}
   </style>
   
   
    
</head>
<body style="font-family:Arial;">         
                <table id="tt2" style="height:100%;width:100%;">
                </table>   
                <div id="toolbar" style="padding:5px;height:auto;">
                	<div id="crud">
            		<a class="easyui-linkbutton" plain="true" iconCls="icon-edit" onclick="getTransDetails();">Details</a>
<!--            		<a class="easyui-linkbutton" plain="true" iconCls="icon-save" onclick="editUser();">Save</a>-->
<!--            		<a class="easyui-linkbutton" plain="true" iconCls="icon-add">Add</a>-->
					</div>
					<div id="search">
					<table id="tranSh">
					<form id="tsform" method="post">
						<tr><td>Date from</td><td>
						<input  id="startDate" name="startDate" data-options="formatter:myDateFormat"  class="easyui-datebox" style="width:100px;"/></td>
						<td>to </td><td>
						<input id="endDate"  name="endDate" data-options="formatter:myDateFormat" class="easyui-datebox" style="width:100px;"/></td>
						</tr>
						<tr><td>Amount from</td> <td><input id="startAnt" name="startAnt" class="easyui-number" style="width:100px;"/></td>
						<td>to </td><td> <input id="endAnt" name="endAnt" class="easyui-number" style="width:100px;"/></td>
						</tr>
						<tr>
						<td>  <a class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="searchTranForm();">Search</a>
								<a class="easyui-linkbutton" plain="true" iconCls="icon-cancel" onclick="">Clear</a>
						</td>
						</tr>
					</form>
					</table>
					
					</div>
            	</div>
                
                
                 <script>  
                 	/**
                 	load the transaction informations
                 	*/
			        $(function(){  
			            $('#tt2').datagrid({  
			                title:'Account Information',  
			                iconCls:'icon-save',  
			                toolbar:'#toolbar', 
			                cache:false, 
			                nowrap: false,  
			                striped: true,  
			                fit: true,
			                url:'test.action',  
			                sortOrder: 'desc',  
			                singleSelect:true,
			                columns:[
			                [  
			                    {field:'clientid',title:'Client ID',formatter:function(val,row){return row.m_request.clientid}},
			                    {field:'orderno',title:'Order Number',formatter:function(val,row){return row.m_request.orderno}}, 
			                    {field:'txntype',title:'Transaction Type',formatter:function(val,row){return row.m_request.txntype}}, 
			                    {field:'txnamount',title:'Transaction Amount',formatter:function(val,row){return row.m_request.txnamount}},  
			                    {field:'txncurrency',title:'Transaction Currency',formatter:function(val,row){return row.m_request.txncurrency}},
			                    {field:'txnschema',title:'Transaction Schema',formatter:function(val,row){return row.m_request.txnschema}},
			                    {field:'timestamp',title:'Transaction Time',formatter:function(val,row){
			                    		var date = row.m_request.timestamp;
			                    		 year = date.substring(0,4);
			                    		 month = date.substring(4,6);
			                    		 day = date.substring(6,8);
			                    		 hour = date.substring(8,10);
			                    		 min = date.substring(10,12);
			                    		 scnd = date.substring(12,date.length);
			                    		return year+'-'+month+'-'+day+' '+hour+':'+min+':'+scnd;
			                    		}}
			                ]
			                ],  
			                pagination:true,  
			                rownumbers:true,
			                pageList:[5,10,15,50] 
			            });  
			        });  
			        
   //search transaction by conditions
   function searchTranForm(){
   		var queryParams = $('#tt2').datagrid('options').queryParams;
   		var startD=$('#startDate').datebox('getValue');
   		var endD=$('#endDate').datebox('getValue');
   		var startA=$('#startAnt').val();
   		var endA=$('#endAnt').val();
   		
   		queryParams['startDate']=startD;
   		queryParams['endDate']=endD;
   		queryParams['startAnt']=startA;
   		queryParams['endAnt']=endA;
   		//={'startDate':startD,'endDate':endD,'startAnt':startA,'endAnt':endA};
   		
   	//	alert(queryParams.startDate+queryParams.endDate);
   		$('#tt2').datagrid('reload');
   		//$('#tsform').form();
   			/*$('#tsform').form('submit',{    
	    			url:"tranSearch.action",    
	    			success:function(data){   
	    			alert(data); 
	       			if(data){
	       				//$.messager.alert("Tips","Update Merchant Successfully!","info");	
	       				//$('#modifyAcnt').dialog('close');
	       					//setTimeout(function(){window.location.reload();},1000);
	       				//}
	       				 $('#tt2').datagrid('reload',data);  
	    	  			}
	    	  		}    
				});
   		*/
   }
			        
	//set filter 		        
	/*$(function(){
            var dg = $('#tt2').datagrid();
            dg.datagrid('enableFilter', [{
                field:'clientid',
                type:'numberbox',
                options:{precision:1},
                op:['equal','notequal','less','greater']
            },{
                field:'clientid',
                type:'numberbox',
                options:{precision:1},
                op:['equal','notequal','less','greater']
            },{
                field:'txnamount',
                type:'combobox',
                options:{
                    panelHeight:'auto',
                    data:[{value:'',text:'All'},{value:'P',text:'P'},{value:'N',text:'N'}],
                    onChange:function(value){
                        if (value == ''){
                            dg.datagrid('removeFilterRule', 'status');
                        } else {
                            dg.datagrid('addFilterRule', {
                                field: 'status',
                                op: 'equal',
                                value: value
                            });
                        }
                        dg.datagrid('doFilter');
                    }
                }
            }]);
        });
	*/	        
			      
			        /**
			        	show the transaction details informations
			        */
			        function getTransDetails(){
            			var row = $('#tt2').datagrid('getSelected');
            			if (row){
							$('#tranDetail').dialog('open').dialog('setTitle','Transaction Information');
							$('#tranDt').form('myLoad',row);
							//var length = getJsonLength(row);
							//$.messager.alert("Warning","The json length is "+ length,'info');
						}else{
							$.messager.alert('Warning','Please select a row!','warning');
						}
            		}
            		
            		/**get the length of a json*/
			        function getJsonLength(jn){
			        	var length=0;
			        	for(var i in jn){
			        		length++;
			        	}
			        	return length;
			        }
			        
			        /**set the input  readonly*/
			        function setInputReadonly(){
			        	var inputs = $('#tranDt input');
			        	//alert(inputs.length);
			        	$.each(inputs,function(i,n){
			        		$(n).attr('readonly','true');
			        	});
			        }
			        
			        $(function(){
			        	setInputReadonly();
			        });
			        
			    </script>  
			    
			    <div id="tranDetail" class="easyui-dialog"  style="width:900px;height:450px;padding:10px"
					closed="true" buttons="#dlg-buttons" data-options="maximizable:true,collapsible:true">
				<form id="tranDt">
			    <table style="height:95%;width:100%;margin:0 auto;border:1px solid #ccc;padding:10px;" >
			    	<tr>
			    	<td colspan="4" class="subInf">Main Information</td>
			    	</tr>
			    	<tr>
			    	<td>Schema </td><td><input onfocus="this.blur()" name="g_front_response.schema" type="text" /></td>
			    	<td>Cycle Id</td><td><input name="cycle_id" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Gateway Amount</td><td><input name="gw_amount" type="text" /></td>
			    	<td>Gateway Currency</td><td><input name="gw_currency" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Gateway GTrans_ID</td><td><input name="gw_g_trans_id" type="text" /></td>
			    	<td>Gateway MTrans_ID</td><td><input name="gw_m_trans_id" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Gateway Time</td><td><input name="gw_time" type="text" /></td>
			    	<td>Gateway Date</td><td><input name="gw_date" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Gateway Trans Type</td><td><input name="gw_tran_type" type="text" /></td>
			    	<td>Gateway Trans Id</td><td><input name="gw_trans_id" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Gateway Request</td><td><input name="g_request" type="text" /></td>
			    	<td>Merchant Response</td><td><input name="m_front_response" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td colspan="4" height="20"></td>
			    	</tr>
			    	
			    	<tr>
			    	<td colspan="4" class="subInf">Merchant Request</td>
			    	</tr>
			    	<tr>
			    	<td>Client Id</td><td><input name="m_request.clientid" type="text" /></td>
			    	<td>Order Number</td><td><input name="m_request.orderno" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Time Stamp</td><td><input name="m_request.timestamp" type="text" /></td>
			    	<td>PayVia</td><td><input name="m_request.payvia" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Transaction Amount</td><td><input name="m_request.txnamount" type="text" /></td>
			    	<td>Transaction Currency</td><td><input name="m_request.txncurrency" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Transaction Schema</td><td><input name="m_request.txnschema" type="text" /></td>
			    	<td>Transaction Type</td><td><input name="m_request.txntype" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Back End URL</td><td><input name="m_request.backendurl" type="text" /></td>
			    	<td>Front EndURL</td><td><input name="m_request.frontendurl" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Version</td><td><input name="m_request.version" type="text" /></td>
			    	</tr>
			    	
			    	<tr>
			    	<td colspan="4" height="20"></td>
			    	</tr>
			    
			    	<tr>
			    	<td colspan="4" class="subInf">Gateway Back Response</td>
			    	</tr>
			    	<tr>
			    	<td>Merchant Abbr</td><td><input name="g_back_response.merAbbr" type="text" /></td>
			    	<td>Merchant Id</td><td><input name="g_back_response.merId" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Order Amount</td><td><input name="g_back_response.orderAmount" type="text" /></td>
			    	<td>Order Currency</td><td><input name="g_back_response.orderCurrency" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Order Number</td><td><input name="g_back_response.orderNumber" type="text" /></td>
			    	<td>Qid</td><td><input name="g_back_response.qid" type="text" /></td>
			    	</tr>
			    	
			    	<tr>
			    	<td>Response Code</td><td><input name="g_back_response.respCode" type="text" /></td>
			    	<td>Response Message</td><td><input name="g_back_response.respMsg" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Response Time</td><td><input name="g_back_response.respTime" type="text" /></td>
			    	<td>Version</td><td><input name="g_back_response.version" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Settlement Amount</td><td><input name="g_back_response.settleAmount" type="text" /></td>
			    	<td>Settlement Currency</td><td><input name="g_back_response.settleCurrency" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Settlement Date</td><td><input name="g_back_response.settleDate" type="text" /></td>
			    	<td>Sign Method</td><td><input name="g_back_response.signMethod" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Sign Method</td><td><input name="g_back_response.signature" type="text" /></td>
			    	<td>Trace Number</td><td><input name="g_back_response.traceNumber" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Trace Time</td><td><input name="g_back_response.traceTime" type="text" /></td>
			    	<td>Trace Type</td><td><input name="g_back_response.transType" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Exchange Date</td><td><input name="g_back_response.exchangeDate" type="text" /></td>
			    	<td>Exchange Rate</td><td><input name="g_back_response.exchangeRate" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Cup Reserved</td><td><input name="g_back_response.cupReserved" type="text" /></td>
			    	<td>Charset</td><td><input name="g_back_response.charset" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Schema </td><td><input name="g_back_response.schema" type="text" /></td>
			    	</tr>
			    	
			    	<tr>
			    		<td colspan="4" height="20"></td>
			    	</tr>
			    	
			    	<tr >
			    	<td colspan="4" class="subInf">Gateway Front Response</td>
			    	</tr>
			    		<tr>
			    	<td>Merchant Abbr</td><td><input name="g_front_response.merAbbr" type="text" /></td>
			    	<td>Merchant Id</td><td><input name="g_front_response.merId" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Order Amount</td><td><input name="g_front_response.orderAmount" type="text" /></td>
			    	<td>Order Currency</td><td><input name="g_front_response.orderCurrency" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Order Number</td><td><input name="g_front_response.orderNumber" type="text" /></td>
			    	<td>Qid</td><td><input name="g_front_response.qid" type="text" /></td>
			    	</tr>
			    	
			    	<tr>
			    	<td>Response Code</td><td><input name="g_front_response.respCode" type="text" /></td>
			    	<td>Response Message</td><td><input name="g_front_response.respMsg" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Response Time</td><td><input name="g_front_response.respTime" type="text" /></td>
			    	<td>Version</td><td><input name="g_front_response.version" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Settlement Amount</td><td><input name="g_front_response.settleAmount" type="text" /></td>
			    	<td>Settlement Currency</td><td><input name="g_front_response.settleCurrency" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Settlement Date</td><td><input name="g_front_response.settleDate" type="text" /></td>
			    	<td>Sign Method</td><td><input name="g_front_response.signMethod" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Sign Method</td><td><input name="g_front_response.signature" type="text" /></td>
			    	<td>Trace Number</td><td><input name="g_front_response.traceNumber" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Trace Time</td><td><input name="g_front_response.traceTime" type="text" /></td>
			    	<td>Trace Type</td><td><input name="g_front_response.transType" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Exchange Date</td><td><input name="g_front_response.exchangeDate" type="text" /></td>
			    	<td>Exchange Rate</td><td><input name="g_front_response.exchangeRate" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Cup Reserved</td><td><input name="g_front_response.cupReserved" type="text" /></td>
			    	<td>Charset</td><td><input name="g_front_response.charset" type="text" /></td>
			    	</tr>
			    	<tr>
			    		<td>Transaction Id</td><td><input name="_id" type="text" /></td>
			    	</tr>
			    
			    </table>
			    </form>
			    
			    
			    </div>
			    
			    
			    
		</body>  
<!--            </div>  -->