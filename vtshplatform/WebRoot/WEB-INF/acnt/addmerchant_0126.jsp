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
	
	$(function(){
		 getTblParaCode();
		 getCur();
		 getPaychannel();
		
	});
	
	//get an Array Json
	function getArrayData(data){
			var dataArr = new Array();
			for(var p in data){
	      		if(p=='_id'){continue;}
	      		var na = new Object();
	      		na.key=p;
	      		na.value=data[p];
	      		dataArr.push(na);
	      	}
		return dataArr;
	}
	
	//获取cur
	function getCur(){
		url="getData.action?tbName=";
		query = "CUR";
		id = "vtCur";
		$.post(url+query,function (data){
			var dataArr = getArrayData(data)
			$('#'+id).combobox({
	      				data:dataArr,
	      				valueField:'value',
	      				textField:'key' 
	      	});
		});
	}
	
	//获取MCC
	function getMcc(){
		url="getData.action?tbName=";
		query = "MCC";
		id = "vtMcc";
		$.post(url+query,function (data){
			var dataArr = getArrayData(data)
			$('#'+id).combobox({
	      				data:dataArr,
	      				valueField:'value',
	      				textField:'key' 
	      	});
		});
	}
	
	//获取渠道信息并加载
	function getPaychannel(){
		var vals = $('#payCh').val();
		var up = new Object();
		up.key = "Unionpay";
		up.value = "UP";
		var ap = new Object();
		ap.key="Alipay";
		ap.value="AP";
		
		var dataArr = new Array();
		dataArr.push(up);
		dataArr.push(ap);
		var id = "payCh";
		$('#'+id).combobox({
	      			data:dataArr,
	      			valueField:'value',
	      			textField:'key' 
	     });
		
		
	}
	
	
	
	
	function getTblParaCode(){
		$.post('getNations.action',function (data){
			var json = data;
			var dataArr = new Array();
			for(var p in json){
	      		if(p=='_id'){continue;}
	      		//document.write(p+" : "+json[p]+" , ");
	      		var nation = new Object();
	      		nation.paraCode=p;
	      		nation.paraValue=json[p];
	      		dataArr.push(nation);
	      		//document.write(nation+" ,,, ");
	      		//var content="<option value='"+json[p]+"'>"+ p +"</option>"; 
	      		//$('#countryId').append(content);
	      }
	      		$('#countryId').combobox({
	      				data:dataArr,
	      				valueField:'paraValue',
	      				textField:'paraCode' 
	      		});
	      
		});
	}

	//submit the addition merchant form
	function submitAddMerchant(){
		$.messager.confirm("Tips","Are you sure to add the merchant ?",function(r){
			if(r){
				$('#addM').form({    
    			url:"addMerchant.action",    
    			success:function(data){    
       			if(data){
       				$.messager.alert("Tips","Add Merchant Successfully!","info");
       				$('#addM').form('reset');	
       				}
    	  		}    
			});    
				$('#addM').submit();
			}
		});
	}
	
	
	//选择渠道自动加载相应商户信息
	$(function (){
		$('#payCh').combobox({
			onSelect:function(rec){
				addTble(rec);
			},
			onUnselect:function(rec){
				remvoeTble(rec);
			}
		
		});
	
	
	});
	
	function removeTable(rec){
		if("UP"==rec.value.match("UP")){$('#btns').before(uniMsg);}
		if("AP"==rec.value.match("AP")){$('#btns').before(aliMsg);}
	
	}
	
	function addTble(rec){
		var addMsg = "<tr>"+
			    	"<td colspan='4' height='20'></td>"+
			    	"</tr><tr>"+
			    	"<td colspan='4' class='subInf'>Merchant Fee</td>"+
			    	"</tr><tr>"+
			    	"<td>Fee 1</td><td><input name='fee1' type='text' /></td>"+
			    	"<td>Fee 2</td><td><input name='fee2' type='text' /></td></tr>";
		var uniMsg="<tr><td colspan='4' height='20'></td></tr>"+
				"<tr><td colspan='4' class='subInf' id='busChanel'>Union Pay</td></tr><tr>"+
			    "<td>Merchant ID</td><td><input name='merchantId' class='easyui-validatebox' data-options='required:true' /></td>"+
			    	"<td>Merchant MD5</td><td><input name='merchant.md5' class='easyui-validatebox' data-options='required:true'/></td>"+
			    "</tr><tr>"+
			    	"<td>Merchant Front URL</td><td><input name='front_url' class='easyui-validatebox' data-options='required:true,validType:'url'' /></td>"+           
			     "<td>Merchant Back URL</td><td><input name='back_url' class='easyui-validatebox' data-options='required:true,validType:'url'' /></td>"+
			     "</tr><tr>"+
			    	"<td>Merchant Acqcode</td><td><input name='acqcode' class='easyui-combobox' /></td>"+
			    	"<td>Merchant Fee</td><td><input name='merFee' class='easyui-validatebox' data-options='required:true'/></td>"+
			    	"</tr><tr>"+
			    	"<td>Merchant Type Code</td><td><input id='vtMcc' class='easyui-combobox' name='merTypeCode' type='text' /></td></tr>";
		var aliMsg="<tr><td colspan='4' height='20'></td></tr>"+
		"<tr><td colspan='4' class='subInf' id='busChanel'>Alipay</td></tr><tr>"+
			    "<td>Merchant ID</td><td><input name='merchantId' class='easyui-validatebox' data-options='required:true' /></td>"+
			    	"<td>Merchant MD5</td><td><input name='merchant.md5' class='easyui-validatebox' data-options='required:true'/></td>"+
			    "</tr><tr>"+
			    	"<td>Merchant Front URL</td><td><input name='front_url' class='easyui-validatebox' data-options='required:true,validType:'url'' /></td>"+           
			     "<td>Merchant Back URL</td><td><input name='back_url' class='easyui-validatebox' data-options='required:true,validType:'url'' /></td>"+
			     "</tr><tr>"+
			    	"<td>Merchant Acqcode</td><td><input name='acqcode' class='easyui-combobox' /></td>"+
			    	"<td>Merchant Fee</td><td><input name='merFee' class='easyui-validatebox' data-options='required:true'/></td>"+
			    	"</tr>";
			    	//<tr id='aliTb'><td colspan='4'><table style='width:100%'> </table></td></tr>
			var id = "payCh";
					//alert(rec.key+rec.value);
					//alert($('#payCh').val());	
					//$('#btns').before(addMsg);
			if("UP"==rec.value.match("UP")){$('#btns').before(uniMsg);}
			if("AP"==rec.value.match("AP")){$('#btns').before(aliMsg);}
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
   </style>
   
   
</head>
<body>         
            
	<div id="dlg"  style="width:100%;height:100%;"
			closed="true"  data-options="maximizable:true,collapsible:true,resizable:true,resizable:true">
		<form id="addM" method="post" validate >
			<table id="tbM" style="height:95%;width:100%;margin:0 auto;border:1px solid #ccc;padding:10px;" >
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
			    	<td>Country/Region</td><td><select id="countryId" name="comCountry" class="easyui-combobox" style="width:155px;" /></select></td>
			    	</tr>
			    	<tr>
			    	<td>Merchant Type Desc</td><td><input name="merTypeDesc" type="text" /></td>
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
			    	<td>Post Code</td><td><input name="postCode" type="text"/></td>
			    	<td>Locked Status</td><td><input name="lockStat" type="text"/></td>
			    	</tr>
			    	<tr>
			    	<td>Payment Channel</td><td>
			    		<select id="payCh" name="payChannel" class="easyui-combobox" 
			    	  data-options="multiple:true,panelHeight:'auto'" style="width:155px;" >
<!--			    	  	 <option value="UP"-->
<!--                          >Unionpay</option>-->
<!--                         <option value="AP"-->
<!--                           >Alipay</option>-->
			    	    </select>
			    	  </td>
			    	<td>Default Currency</td><td>
			    	  <select name="defCurr" class="easyui-combobox" id="vtCur"
			    	  data-options="multiple:true,panelHeight:'auto'" style="width:155px;">
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
<!--			    	<tr>-->
<!--			    	<td>Post Code</td><td><input name="postCode" type="text" /></td>-->
<!--			    	<td>Locked Status</td><td><input name="lockStat" type="text" /></td>-->
<!--			    	</tr>-->
			    	<tr>
			    	<td colspan="4" height="20"></td>
			    	</tr>
			    	<tr>
			    	<td colspan="4" class="subInf">Bank Information</td>
			    	</tr>
			    	<tr>
			    	<td>Settlement Currency 1</td><td><input name="setCur1" class="easyui-validatebox" data-options="required:true" /></td>
			    	<td>Settlement Currency 2</td><td><input name="setCur2"   /></td>
			    	</tr>
			    	<tr>
			    	<td>Settle Bank Name1</td><td><input name="bkName1" class="easyui-validatebox" data-options="required:true" /></td>
			    	<td>Settle Bank Name2</td><td><input name="bkName2"  /></td>
			    	</tr>
			    	<tr>
			    	<td>Settle Account Name 1</td><td><input name="acntName1" class="easyui-validatebox" data-options="required:true" /></td>
			    	<td>Settle Account Name 2</td><td><input name="acntName2"  /></td>
			    	</tr>
			    	<tr>
			    	<td>Settle Account Number 1</td><td><input name="acntNum1" class="easyui-validatebox" data-options="required:true" /></td>
			    	<td>Settle Account Number 2</td><td><input name="acntNum2"  /></td>
			    	</tr>
			    	<tr>
			    	<td>Bank SwiftCode 1</td><td><input name="swiftCode1" class="easyui-validatebox" data-options="required:true" /></td>
			    	<td>Bank SwiftCode 2</td><td><input name="swiftCode2" /></td>
			    	</tr>
			    	<tr>
			    	<td>Bank Address 1</td><td><input name="addr1" class="easyui-validatebox" data-options="required:true" /></td>
			    	<td>Bank Address 2</td><td><input name="addr2" /></td>
			    	</tr>
			    	<tr>
			    	<td>Transfer Fee1</td><td><input name="tranFee1" class="easyui-validatebox" data-options="required:true" /></td>
			    	<td>Transfer Fee2</td><td><input name="tranFee2" /></td>
			    	</tr>
			    	<tr>
			    	<td>Transfer Threashold 1</td><td><input name="tranTh1" class="easyui-validatebox" data-options="required:true"  /></td>
			    	<td>Transfer Threashold 2</td><td><input name="tranTh2" /></td>
			    	</tr>
			    	
			    	
			    	<tr>
			    	<td colspan="4" height="20"></td>
			    	</tr>
			    	<tr>
			    	<td colspan="4" class="subInf">Merchant Fee</td>
			    	</tr>
			    	<tr>
			    	<td>Fee 1</td><td><input name="fee1" type="text" /></td>
			    	<td>Fee 2</td><td><input name="fee2" type="text" /></td>
			    	</tr>
			    	                                                                                                                
			    	<tr id="btns">
			    	<td colspan="4" height="20">&nbsp;</td>
			    	</tr>
			    	<tr>
			    	<td colspan="4"><a  class="easyui-linkbutton" iconCls="icon-ok" onclick="submitAddMerchant();">Save</a>
			    		<a  class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#addM').form('reset')">Reset</a>
			    	</td><td></td>
			    	</tr>
			 </table>
		</form>
	</div>

		</body>  
<!--            </div>  -->