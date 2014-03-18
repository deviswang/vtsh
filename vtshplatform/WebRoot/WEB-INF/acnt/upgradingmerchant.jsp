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
    <script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>  
    <script type="text/javascript" src="js/jquery.easyui.min.js"></script>  
   <srcipt type="text/javascript" src="js/main.js"></srcipt>             
   <srcipt type="text/javascript" src="js/myload.js"></srcipt>   
    <script type="text/javascript" src="js/json2.js"></script>  
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

function convertToJson(formValues) {
    var result = {};
    for(var formValue,j=0;j<formValues.length;j++) {
    formValue = formValues[j];
    var name = formValue.name;
    var value = formValue.value;
    if (name.indexOf('.') < 0) {
   	 	result[name] = value;
    	continue;
    } else {
    var simpleNames = name.split('.');
    // 构建命名空间
    var obj = result;
    for ( var i = 0; i < simpleNames.length - 1; i++) {
    var simpleName = simpleNames[i];
    if (simpleName.indexOf('[') < 0) {
    if (obj[simpleName] == null) {
    obj[simpleName] = {};
    }
    obj = obj[simpleName];
    } else { // 数组
    // 分隔
    var arrNames = simpleName.split('[');
    var arrName = arrNames[0];
    var arrIndex = parseInt(arrNames[1]);
    if (obj[arrName] == null) {
    obj[arrName] = []; // new Array();
    }
    obj = obj[arrName];
    multiChooseArray = result[arrName];
    if (obj[arrIndex] == null) {
    obj[arrIndex] = {}; // new Object();
    }
    obj = obj[arrIndex];
    }
    }
 
    if(obj[simpleNames[simpleNames.length - 1]] ) {
    var temp = obj[simpleNames[simpleNames.length - 1]];
    obj[simpleNames[simpleNames.length - 1]] = temp;
    } else {
    obj[simpleNames[simpleNames.length - 1]] = value;
    }
 
     }
     }
    return result;
}
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
   #dlg td{
   		font-size:13px;
   		height:18px;
   		color:#276ea9;
   		font-family:Arial;
   		
   	}
   	#dlg input{
   		border:0px;
   		readonly;
   	}
   	#tbar a{
   		border:1px solid #95B8E7;
   		background:#F0F8FF;
   		height:22px;
   	}
   </style>
   
</head>
<body style="font-family:Arial;">         
            	<script> 
            	function formatManager(val,row){
            		return row.unionpay.merchantId;
            	}
            	
            	function detailUser(){
					var row = $('#tt3').datagrid('getSelected');
					if(row){
						$('#dlg').dialog('open').dialog('setTitle','Merchant Information Details');
						$('#acntDt').form('myLoad',row);
						for(var key in row){
							var subRow = row[key];
							if(typeof(subRow)=="object"){
								for(var k in subRow){
									//alert(k + " sub " + subRow[k]);
								}
							}
						}
					}else{
						$.messager.alert('Warning','Please select a row!','warning');
					}
				}
				
				//AddUser
				function updateUser(){
						var row = $('#tt3').datagrid('getSelected');
						if(row){
						  $('#modifyAcnt').dialog('open').dialog('setTitle','Update Merchant');
						  $('#modifyAcntForm').form('myLoad',row);
						}else{
						  $.messager.alert('Warning','Please select a row!','warning');
						}
				}
				//Reset the upadate form information
				function myReset(){
					$('#modifyAcnt').dialog('close');
				}
			        $(function(){  
			            $('#tt3').datagrid({  
			                title:'Merchant Information',  
			                iconCls:'icon-save', 
			                toolbar:"#tbar", 
			                cache:false, 
			                nowrap: false,  
			                striped: true,  
			                fit: true,  
			                url:'toUpgradingMerchant.action',  
			                sortName: 'name',  
			                sortOrder: 'desc',  
			                idField:'_id', 
			                singleSelect:true,
			                columns:[
			                  [  
			                 	{field:'regTime',title:'Register Time'},
			                    {field:'clientid',title:'Merchant Id'},
			                    {field:'merName',title:'Merchant Name'},
			                    {field:'currency',title:'Merchant Currency'}, 
			                    {field:'payChannel',title:'Paychannel'},
			                    {field:'risk',title:'Risk Rule'},
			                    {field:'threshold',title:'Threshold Rule'},
			                    {field:'md5',title:'MD5'},
			                    {field:'regStatus',title:'Register Status'}
			                 ]
			                ],
			                pagination:true,
			                rownumbers:true,
			                checkbox:true,
			                pageList:[5,10,20,50],
			                onDblClickRow:function(index,field,value){detailUser();}
			            });  
			        }); 
			        
	//	//submit the update merchant form
		function submitUpdateMerchant(){
			$.messager.confirm("Tips","Are you sure to update the merchant ?",function(r){
				if(r){
					$('#modifyAcntForm').form('submit',{    
	    			url:"updateMerchant.action",
	    			success:function(data){    
	       			if(data){
	       				$.messager.alert("Tips","Update Merchant Successfully!","info");	
	       				$('#modifyAcnt').dialog('close');
	       					setTimeout(function(){window.location.reload();},1000);
	       				}
	    	  		}    
				});    
				}
			});
		}
		
	//json转换
	function showValue(){
		var dataVo = $("#acntDt").serializeArray();
		var dataJson = convertToJson(dataVo);
		dataVo = JSON.stringify(dataJson);
		var url="updateMerchant.action";
		$.post(
			url,{'user':dataVo},function(data){
				if(data){
					 $.messager.alert("Tips","Update Merchant Successfully!","info");	
	       			 $('#modifyAcnt').dialog('close');
	       			 setTimeout(function(){window.location.reload();},1000);
			}
		});	
	}		  
		//remove a merchant
		function removeMerchant(){
			var row = $('#tt3').datagrid('getSelected');
			if(row){
				var id = row.clientid;
				var url = "deleteMerchant.action";
				$.messager.confirm("Tips","Are you sure to delete the merchant ?",function(r){
					if(r){
					$.post(url,{clientid:id},function(data){
						if(data){
	       					$.messager.alert("Tips","Update Merchant Successfully!","info");	
	       					setTimeout(function(){window.location.reload();},2000);
	       				}else{
	       					$.messager.alert('Warning','Delete Merchant failed!','warning');
	       				}
						});
					}
				});
			}else{
				$.messager.alert('Warning','Please select a row!','warning');
			}
		}
		
		      /**set the input  readonly*/
			  function setInputReadonly(){
			        	var inputs = $('#acntDt input');
			        	//alert(inputs.length);
			        	$.each(inputs,function(i,n){
			        		$(n).attr('readonly','true');
			        	});
			        }
			        $(function(){
			        	setInputReadonly();
			        });
			        
			        
			 function setRegStatus(){
			    	var clientId=document.getElementById("clid").value;
			    	$.messager.alert('Warning',clientId,'warning');
			    	var radios = document.getElementsByName("regSt");
			    	var statu = "" ;
			    	for( var i=0; i<radios.length; i++){
			    		if(radios[i].checked==true)
			    			statu=radios[i].value;
			    	}
			    	if(statu!=""){
				    	var url = "regStatus.action";
				    	$.messager.confirm("Tips","Are you sure to change the register status ?",function(r){
						if(r){
							$.post(url,{clientid:clientId,status:statu},function(data){
							if(data){
		       					$.messager.alert("Tips","Change Regstatus Successfully!","info");	
		       					setTimeout(function(){window.location.reload();},2000);
		       				}else{
		       					$.messager.alert('Warning','Change Regstatus failed!','warning');
		       				}
							});
							}
							});
			    	}else{
			    		$.messager.alert('Warning','Please select "Approve" or "Decline" !','warning');
			    	}	
			  }  
			        
			        
			        
 </script>
 
	<table id="tt3"  style="width:800px;height:300px"></table>
            	<div id="tbar" >
            		<a class="easyui-linkbutton" plain="true" iconCls="icon-edit" onclick="detailUser();">Merchant Detail</a>
            		<a class="easyui-linkbutton" plain="true" iconCls="icon-reload" onclick="updateUser();">Delete Merchant</a>
<!--            	<a class="easyui-linkbutton" plain="true" iconCls="icon-cancel" onclick="removeMerchant();">Delete Merchant</a>-->
          		</div>		 
	<div id="dlg" class="easyui-dialog" style="width:900px;height:450px;padding:10px"
			closed="true" buttons="#dlg-buttons" data-options="maximizable:true,collapsible:true,resizable:true,resizable:true">
		<form id="acntDt" method="post" novalidate>
			<table style="height:95%;width:100%;margin:0 auto;border:1px solid #ccc;padding:10px;" >
			    	<tr>
			    	<td colspan="4" class="subInf">Main Information</td>
			    	</tr>
			    	<td>Client ID</td><td> <input id="clid" name = "clientid"  /></td>
			    	<td>MD5</td><td><input name="md5" type="text"  readonly/></td>
			    	</tr>
			    	<tr>
			    	<td>Merchant Name</td><td><input name="merName"  type="text"  /></td>
			    	<td>Merchant Abbr</td><td><input name="merAbbr"  type="text"  /></td>
			    	</tr>
			    	
			    	<tr>
			    	<td>Company Address</td><td><input name="comAddr"     type="text" /></td>
			    	<td>Country/Region</td><td><input  name="comCountry"  type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Merchant Type Desc</td><td><input name="merTypeDesc" type="text" /></td>
			    	<td>Merchant Type Code</td><td><input name="merTypeCode" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Legal Registration No.</td><td><input name="comRegno" type="text" /></td>
			    	<td>Merchant URL</td><td><input name="merUrl" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Merchant Attribute</td><td><input name="merAttr" type="text" /></td>
			    	<td>Merchant City</td><td><input name="merCity" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Post Code</td><td><input name="postCode" type="text" /></td>
			    	<td>Locked Status</td><td><input name="lockStat" type="text" /></td>
			    	</tr>
			    	<tr>
<!--			    	<td>Merchant Attribute</td><td><input name="" type="text" /></td>-->
			    	<td>Default Currency</td><td>
			    	  <input name="currency" />
			    	</td>
			    	</tr>
			    	<tr>
			    	<td>Principal Contact Name</td><td><input name="prinName" type="text" /></td>
			    	<td>Operation Contact Name</td><td><input name="operName" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Principal Email</td><td><input name="prinMail" type="text" /></td>
			    	<td>Operation Email</td><td><input name="operMail" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Principal Tel</td><td><input name="prinTel" type="text" /></td>
			    	<td>Operation Tel</td><td><input name="operTel" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Application Date</td><td><input name="appDate" type="text" /></td>
			    	<td>Approval Status</td><td><input name="appStat" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Approval Time</td><td><input name="appTime" type="text" /></td>
			    	<td>Max Limit Per Transaction</td><td><input name="tranLimit" type="text" /></td>
			    	</tr>
			    	
			    	<tr>
			    	<td colspan="4" height="20"></td>
			    	</tr>
			    	<tr>
			    	<td colspan="4" class="subInf">Bank Information</td>
			    	</tr>
			    	<tr>
			    	<td>Settlement Currency 1</td><td><input name="bank.setCur1" /></td>
			    	<td>Settlement Currency 2</td><td><input name="bank.setCur2"  /></td>
			    	</tr>
			    	<tr>
			    	<td>Settle Bank Name1</td><td><input name="bank.bkName1" /></td>
			    	<td>Settle Bank Name2</td><td><input name="bank.bkName2"  /></td>
			    	</tr>
			    	<tr>
			    	<td>Settle Account Name 1</td><td><input name="bank.acntName1" /></td>
			    	<td>Settle Account Name 2</td><td><input name="bank.acntName2"  /></td>
			    	</tr>
			    	<tr>
			    	<td>Settle Account Number 1</td><td><input name="bank.acntNum1" /></td>
			    	<td>Settle Account Number 2</td><td><input name="bank.acntNum2"  /></td>
			    	</tr>
			    	<tr>
			    	<td>Bank SwiftCode 1</td><td><input name="bank.swiftCode1" /></td>
			    	<td>Bank SwiftCode 2</td><td><input name="bank.swiftCode2"  /></td>
			    	</tr>
			    	<tr>
			    	<td>Bank Address 1</td><td><input name="bank.addr1"  /></td>
			    	<td>Bank Address 2</td><td><input name="bank.addr2" /></td>
			    	</tr>
			    	<tr>
			    	<td>Transfer Fee1</td><td><input name="bank.tranFee1"  /></td>
			    	<td>Transfer Fee2</td><td><input name="bank.tranFee2"  /></td>
			    	</tr>
			    	<tr>
			    	<td>Transfer Threashold 1</td><td><input name="bank. tranTh1" /></td>
			    	<td>Transfer Threashold 2</td><td><input name="bank.tranTh2"  /></td>
			    	</tr>
			    	
			    	<tr>
			    	<td colspan="4" height="20"></td>
			    	</tr>
			    	<tr>
			    	<td colspan="4" class="subInf">Union Pay</td>
			    	</tr>
			    	<tr>
			    	<td>Merchant ID</td><td><input name="unionpay.merchantId" type="text" /></td>
			    	<td>Merchant MD5</td><td><input name="unionpay.md5" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<tr>
			    	<td>Merchant Refund URL</td><td><input name="unionpay.refund_url" type="text" /></td>
			    	<td>Merchant Query URL</td><td><input name="unionpay.query_url" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Merchant Pay URL</td><td><input name="unionpay.pay_url" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Merchant Acqcode</td><td><input name="unionpay.acqcode" type="text" /></td>
			    	</tr>
			    	
			    	<tr>
			    	<td>Fee 1</td><td><input name="unionpay.fee1" type="text" /> </td>
			    	<td>Fee 2</td><td><input name="unionpay.fee2" type="text" /> </td>
			    	</tr>
			    	
			    	
			 </table>
		</form>
		<div id="modifyAcnt" class="easyui-dialog" style="width:900px;height:450px;padding:10px"
			buttons="#dlg-buttons" closed="true" buttons="#dlg-buttons" data-options="maximizable:true,collapsible:true,resizable:true,resizable:true">
		<form id="modifyAcntForm" method="post" novalidate>
			<table style="height:95%;width:100%;margin:0 auto;border:1px solid #ccc;padding:10px;" >
			    	<tr>
			    	<td colspan="4" class="subInf">Main Information</td>
			    	</tr>
			    	<tr>
			    	<td>Client ID</td><td><input name="clientid"  class="easyui-validatebox" data-options="required:true"/></td>
			    	<td>MD5</td><td><input name="md5" type="text" value="${md5}"  readonly/></td>
			    	</tr>
			    	<tr>
			    	<td>Merchant Name</td><td><input name="merName" type="text" class="easyui-validatebox" data-options="required:true"/></td>
			    	<td>Merchant Abbr</td><td><input name="merAbbr" type="text"  /></td>
			    	</tr>
			    	<tr>
			    	<td>Company Address</td><td><input name="comAddr" type="text" /></td>
			    	<td>Country/Region</td><td><input  name="comCountry" type="text"  class="easyui-validatebox" data-options="required:true"/></td>
			    	</tr>
			    	<tr>
			    	<td>Merchant Type Desc</td><td><input name="merTypeDesc" type="text" /></td>
			    	<td>Merchant Type Code</td><td><input class="easyui-combobox" name="merTypeCode" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Legal Registration No.</td><td><input name="comRegno" type="text" /></td>
			    	<td>Merchant URL</td><td><input name="merUrl" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Merchant Attribute</td><td><input name="merAttr" type="text" /></td>
			    	<td>Merchant City</td><td><input name="merCity" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Post Code</td> <td><input name="postCode" type="text" /></td>
			    	<td>Locked Status</td><td><input name="lockStat" type="text" /></td>
			    	</tr>
			    	<tr>
<!--			    	<td>Merchant Attribute</td><td><input name="" type="text" /></td>-->
			    	<td>Default Currency</td><td>
			    	  <select name="defCurr" class="easyui-combobox" 
			    	  data-options="multiple:true,panelHeight:'auto'" style="width:155px;">
                        <option value="AED"
                                >AED</option>
                        <option value="AUD"
                                >AUD</option>
                        <option value="CHF"
                                >CHF</option>
                        <option value="CNY"
                                >CNY</option>
                        <option value="EUR"
                                >EUR</option>
                        <option value="GBP"
                                >GBP</option>
                        <option value="HKD"
                                >HKD</option>
                        <option value="JPY"
                                >JPY</option>
                        <option value="KRW"
                                >KRW</option>
                        <option value="MOP"
                                >MOP</option>
                        <option value="NZD"
                                >NZD</option>
                        <option value="SGD"
                                >SGD</option>
                        <option value="THB"
                                >THB</option>
                        <option value="USD"
                                >USD</option>
                        </select>
			    	</td>
			    	</tr>
			    	<tr>
			    	<td>Principal Contact Name</td><td><input name="prinName" type="text" /></td>
			    	<td>Operation Contact Name</td><td><input name="operName" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Principal Email</td><td><input name="prinMail" type="text" /></td>
			    	<td>Operation Email</td><td><input name="operMail" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Principal Tel</td><td><input name="prinTel" type="text" /></td>
			    	<td>Operation Tel</td><td><input name="operTel" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Application Date</td><td><input name="appDate" type="text" /></td>
			    	<td>Approval Status</td><td><input name="appStat" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Approval Time</td><td><input name="appTime" type="text" /></td>
			    	<td>Max Limit Per Transaction</td><td><input name="tranLimit" type="text" /></td>
			    	</tr>
			    	
			    	<tr>
			    	<td colspan="4" height="20"></td>
			    	</tr>
			    	<tr>
			    	<td colspan="4" class="subInf">Bank Information</td>
			    	</tr>
			    	<tr>
			    	<td>Settlement Currency 1</td><td><input name="bank.setCur1" class="easyui-validatebox" data-options="required:true" /></td>
			    	<td>Settlement Currency 2</td><td><input name="bank.setCur2" class="easyui-validatebox" data-options="required:true"  /></td>
			    	</tr>
			    	<tr>
			    	<td>Settle Bank Name1</td><td><input name="bank.bkName1" class="easyui-validatebox" data-options="required:true" /></td>
			    	<td>Settle Bank Name2</td><td><input name="bank.bkName2" class="easyui-validatebox" data-options="required:true"  /></td>
			    	</tr>
			    	<tr>
			    	<td>Settle Account Name 1</td><td><input name="bank.acntName1" class="easyui-validatebox" data-options="required:true" /></td>
			    	<td>Settle Account Name 2</td><td><input name="bank.acntName2" class="easyui-validatebox" data-options="required:true"  /></td>
			    	</tr>
			    	<tr>
			    	<td>Settle Account Number 1</td><td><input name="bank.acntNum1" class="easyui-validatebox" data-options="required:true" /></td>
			    	<td>Settle Account Number 2</td><td><input name="bank.acntNum2" class="easyui-validatebox" data-options="required:true"  /></td>
			    	</tr>
			    	<tr>
			    	<td>Bank SwiftCode 1</td><td><input name="bank.swiftCode1" class="easyui-validatebox" data-options="required:true" /></td>
			    	<td>Bank SwiftCode 2</td><td><input name="bank.swiftCode2" class="easyui-validatebox" data-options="required:true" /></td>
			    	</tr>
			    	<tr>
			    	<td>Bank Address 1</td><td><input name="bank.addr1" class="easyui-validatebox" data-options="required:true" /></td>
			    	<td>Bank Address 2</td><td><input name="bank.addr2" class="easyui-validatebox" data-options="required:true"/></td>
			    	</tr>
			    	<tr>
			    	<td>Transfer Fee1</td><td><input name="bank.tranFee1" class="easyui-validatebox" data-options="required:true" /></td>
			    	<td>Transfer Fee2</td><td><input name="bank.tranFee2" class="easyui-validatebox" data-options="required:true"  /></td>
			    	</tr>
			    	<tr>
			    	<td>Transfer Threashold 1</td><td><input name="bank.tranTh1" class="easyui-validatebox" data-options="required:true"  /></td>
			    	<td>Transfer Threashold 2</td><td><input name="bank.tranTh2" class="easyui-validatebox" data-options="required:true"  /></td>
			    	</tr>
			    	<tr>
			    	<td colspan="4" height="20"></td>
			    	</tr>
			    	<tr>
			    	<td colspan="4" class="subInf">UnionPay</td>
			    	</tr>
			    	<tr>
			    	<td>Merchant ID</td><td><input name="UP.merchantId" type="text" /></td>
			    	<td>Merchant MD5</td><td><input name="UP.md5" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Merchant Refund URL</td><td> <input name="UP.refund_url" type="text" /></td>
			    	<td>Merchant Query URL</td> <td> <input name="UP.query_url" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Merchant Pay URL</td><td><input name="UP.pay_url" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Merchant Acqcode</td><td><input name="UP.acqcode" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Fee 1</td><td><input name="UP.fee.fee1" type="text" /></td>
			    	</tr>
			    	<tr>
			    	<td>Fee 2</td><td><input name="UP.fee.fee2" type="text" /></td>
			    	</tr>
			    	
			    	<tr>
			    	<td> Approve </td><td><input name="regSt" value="H"  type="radio" /></td>
			    	<td> Decline </td><td><input name="regSt" value="D"  type="radio" /></td>
			    	</tr>
			    	
			 </table>
		</form>
	</div>
	
	<div id="dlg-buttons">
		<a class="easyui-linkbutton" iconCls="icon-ok" onclick="setRegStatus();"> Save </a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:myReset();">Cancel</a>
	</div>

		</body>  
<!--            </div>  -->