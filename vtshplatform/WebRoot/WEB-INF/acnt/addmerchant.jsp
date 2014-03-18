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

//json转换
//保存用户。。。
	function showValue(){
		$.messager.confirm("Tips","Are you sure to add the merchant ?",function(r){
			var dataVo = $("#addM").serializeArray();
			var dataJson = convertToJson(dataVo);
			dataVo = JSON.stringify(dataJson);
			var url="addMerchant.action";
			$.post(
				url,{'user':dataVo},function(data){
				if(data){
					$.messager.alert("Tips","Add Merchant Successfully!","info");
       				$('#addM').form('reset');	
				}
			}
		);	
		});
	}

	var payArr;
	$(function(){
		 getTblParaCode();
		 getCur();
		 getPaychannel();
		var url="payCont.action";
		var id = "payCh";
		$.post(url,function (data){
			 payArr=data;
		});	
	
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
		
		/*var vals = $('#payCh').val();
		var up = new Object();
		up.key = "Unionpay";
		up.value = "UP";
		var ap = new Object();
		ap.key="Alipay";
		ap.value="AP";
		var dataArr = new Array();
		dataArr.push(up);
		dataArr.push(ap);
		var id = "payCh";*/
		
		var url="getData.action?tbName=";
		var query = "PAY";
		var id = "payCh";
		$.post(url+query,function (data){
			var dataArr = getArrayData(data)
			$('#'+id).combobox({
	      		data:dataArr,
	      		valueField:'value',
	      		textField:'key' 
	      	});
		});
	}

	//转换获取国家列表
	function getTblParaCode(){
		$.post('getNations.action',function (data){
			var json = data;
			var dataArr = new Array();
			for(var p in json){
	      		if(p=='_id'){continue;}
	      		var nation = new Object();
	      		nation.paraCode=p;
	      		nation.paraValue=json[p];
	      		dataArr.push(nation);
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
	
	//
	function getFormJson(){
		var data = $("#addM").serializeArray();
		document.write(data[0]);
	}
	
	//选择渠道自动加载相应商户信息
	$(function (){
		$('#payCh').combobox({
			onSelect:function(rec){
				addTble(rec);
			},
			onUnselect:function(rec){
				removeTable(rec);
			}
		});
	});
	
	/*
	function removeTable(rec){
		var data = payArr;
		for(var i=0;i<data.length;i++){
			var name=data[i].name;
			var cont = data[i].content;
			alert(rec.value);
			if(name==rec.value.match(name)){
				
			}
		}
	}
	*/
	//
	function addTble(rec){
		var data = payArr;
		for(var i=0;i<data.length;i++){
			var name=data[i].name;
			var cont = data[i].content;
			if(name==rec.value.match(name)){$('#btns').before(cont);
		  }
		}
		
		
			/*
			$.post(url,function (data){
			var dataArr = getArrayData(data)
			for(var i=0;i<data.length;i++){
				var name=data[i].name;
				var cont = data[i].content;
				if(name==rec.value.match(name)){$('#btns').before(cont);}
			}
			});
			if("UP"==rec.value.match("UP")){$('#btns').before(uniMsg);}
			if("AP"==rec.value.match("AP")){$('#btns').before(aliMsg);}
			if("TL"==rec.value.match("TL")){$('#btns').before(aliMsg);}
			*/
	}		   
	
	$(function(){
	  setJson();
	});
	
	function setJson(){
		var obj = new Object();
		var key = "alipay";
		var value={"a":"b","c":"d"};
		obj.prototype=key;
	  }
	
	function checkUser(){
		var user = $('#merchantName').val();
		var url = 'existUser.action';
		$.post(
			url,{'userInfo':user},function(data){
				if(data){
					$.messager.alert('Warning','Sorrry, the web name has already existed!','warning');
					$('#merchantName').val('');
				}	
			}
		);
	}
	
	//$(function(){$('#merchantName')='checkUser';});
	
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
<!--<div id="tt" class="easyui-tabs" style="width:500px;height:250px;">   -->
		<form id="addM" method="post" validate >
			<table id="tbM" style="border: 1px solid rgb(204, 204, 204); margin: 0pt auto; padding: 10px; height: 95%; width: 100%;"> 
			    	<tr> 
			    	<td colspan="4" class="subInf">Main Information</td> 
			    	</tr> 
			    	<tr> 
			    	<td>Client ID</td><td><input name="clientid"  onblur="checkUser();" value="" class="easyui-validatebox" data-options="required:true"></td> 
			    	<td>MD5</td><td><input type="text" name="md5" value="${md5}" readonly></td> 
			    	</tr> 
			    	<tr>
			    	<td>Merchant Name</td><td><input onblur="checkUser();" id="merchantName" type="text" name="merName" class="easyui-validatebox" data-options="required:true"></td> 
			    	<td>Merchant Abbr</td><td><input type="text" name="merAbbr"></td> 
			    	</tr> 
			    	<tr> 
			    	<td>Company Address</td><td><input type="text" name="comAddr"></td> 
			    	<td>Country/Region</td><td><select id="countryId" name="comCountry" class="easyui-combobox" style="width: 155px;"></select></td> 
			    	</tr> 
			    	<tr> 
			    	<td>Merchant Type Desc</td><td><input type="text" name="merTypeDesc"></td> 
			    	</tr> 
			    	<tr> 
			    	<td>Legal Registration No.</td><td><input type="text" name="comRegno"></td> 
			    	<td>Merchant URL</td><td><input type="text" name="merUrl"></td> 
			    	</tr> 
			    	<tr> 
			    	<td>Merchant Attribute</td><td><input type="text" name="merAttr"></td> 
			    	<td>Merchant City</td><td><input type="text" name="merCity"></td> 
			    	</tr> 
			    	<tr> 
			    	<td>Post Code</td><td><input type="text" name="postCode"></td> 
			    	<td>Locked Status</td><td><input type="text" name="lockStat"></td> 
			    	</tr>
			    	 
			    	<tr>
			    	<td>Payment Channel</td><td>
			    		<select id="payCh" name="payChannel" class="easyui-combobox" data-options="multiple:true,panelHeight:'auto'" style="width: 155px;" > 
			    	    </select>
			    	</td>
			    	<td>Default Currency</td>
			    	<td> 
			    	  <select name="defCurr" class="easyui-combobox" id="vtCur" data-options="multiple:true,panelHeight:'auto'" style="width: 155px;"> 
                      </select> 
			    	</td> 
			    	</tr> 
			    	<tr> 
			    	<td>Principal Contact Name</td><td><input type="text" name="prinName"></td> 
			    	<td>Operation Contact Name</td><td><input type="text" name="operName"></td> 
			    	</tr> 
			    	<tr> 
			    	<td>Principal Email</td><td><input type="text" name="prinMail"></td> 
			    	<td>Operation Email</td><td><input type="text" name="operMail"></td> 
			    	</tr> 
			    	<tr> 
			    	<td>Principal Tel</td><td><input type="text" name="prinTel"></td> 
			    	<td>Operation Tel</td><td><input type="text" name="operTel"></td> 
			    	</tr> 
			    	<tr> 
			    	<td>Application Date</td><td><input type="text" name="appDate"></td> 
			    	<td>Approval Status</td><td><input type="text" name="appStat"></td> 
			    	</tr> 
			    	<tr> 
			    	<td>Approval Time</td><td><input type="text" name="appTime"></td> 
			    	<td>Max Limit Per Transaction</td><td><input type="text" name="tranLimit"></td> 
			    	</tr> 
			    	
			    	<tr> 
			    	<td height="20" colspan="4"><br></td> 
			    	</tr> 
			    	<tr> 
			    	<td colspan="4" class="subInf">Bank Information</td> 
			    	</tr> 
			    	
			    	
			    	
			    	<tr> 
			    	<td>Settlement Currency 1</td><td><input name="bank.setCur1" class="easyui-validatebox" data-options="required:true"></td> 
			    	<td>Settlement Currency 2</td><td><input name="bank.setCur2"></td> 
			    	</tr> 
			    	<tr> 
			    	<td>Settle Bank Name1</td><td><input name="bank.bkName1" class="easyui-validatebox" data-options="required:true"></td> 
			    	<td>Settle Bank Name2</td><td><input name="bank.bkName2"></td> 
			    	</tr> 
			    	<tr> 
			    	<td>Settle Account Name 1</td><td><input name="bank.acntName1" class="easyui-validatebox" data-options="required:true"></td> 
			    	<td>Settle Account Name 2</td><td><input name="bank.acntName2"></td> 
			    	</tr> 
			    	<tr> 
			    	<td>Settle Account Number 1</td><td><input name="bank.acntNum1" class="easyui-validatebox" data-options="required:true"></td> 
			    	<td>Settle Account Number 2</td><td><input name="bank.acntNum2"></td> 
			    	</tr> 
			    	<tr> 
			    	<td>Bank SwiftCode 1</td><td><input name="bank.swiftCode1" class="easyui-validatebox" data-options="required:true"></td> 
			    	<td>Bank SwiftCode 2</td><td><input name="bank.swiftCode2"></td> 
			    	</tr> 
			    	<tr> 
			    	<td>Bank Address 1</td> <td><input name="bank.addr1" class="easyui-validatebox" data-options="required:true"></td> 
			    	<td>Bank Address 2</td> <td><input name="bank.addr2"></td> 
			    	</tr> 
			    	
			    	<tr> 
			    	<td>Transfer Fee1</td><td><input name="bank.tranFee1" class="easyui-validatebox" data-options="required:true"></td> 
			    	<td>Transfer Fee2</td><td><input name="bank.tranFee2"></td> 
			    	</tr> 
			    	
			    	<tr> 
			    	<td>Transfer Threashold 1</td><td><input name="bank.tranTh1" class="easyui-validatebox" data-options="required:true"></td> 
			    	<td>Transfer Threashold 2</td><td><input name="bank.tranTh2"></td> 
			    	</tr>
					
					<tr> 
			    	<td height="20" colspan="4"><br></td> 
			    	</tr> 
			    	<tr> 
			    	<td colspan="4" class="subInf">Settlement Information</td> 
			    	</tr> 
			    	<tr> 
			    		<td>Rolling Reserve Rate</td> <td><input name="roSerRate"  data-options="required:true"></td> 
			    	</tr>
			    	
<!--			    	<tr>   -->
<!--			    	<td height="20" colspan="4"><br></td> -->
<!--			    	</tr>  -->
<!--			    	<tr>   -->
<!--			    	<td colspan="4" class="subInf">Merchant Fee</td> -->
<!--			    	</tr>  -->
<!--			    	<tr>   -->
<!--			    	<td> Fee 1 </td> <td> <input type="text" name="unionpay.fee1" ></td>   -->
<!--			    	<td> Fee 2 </td> <td> <input type="text" name="unionpay.fee2" ></td>   -->
<!--			    	</tr>  -->

			    	<tr id="btns"> 
			    	<td height="20" colspan="4">&nbsp;</td> 
			    	</tr> 
			    	<tr> 
			    	<td colspan="4"><a class="easyui-linkbutton" iconcls="icon-ok" onclick="showValue()">Save</a> 
			    		<a class="easyui-linkbutton" iconcls="icon-cancel" onclick="javascript:$('#addM').form('reset')">Reset</a>
<!--			   &lt;a class=&quot;easyui-linkbutton&quot; iconCls=&quot;icon-cancel&quot; onclick=&quot;getFormJson();&quot;&gt;Reset&lt;/a&gt;               --> 
			    	</td></tr></table>
			 <table id="tbM" style="border: 1px solid rgb(204, 204, 204); margin: 0pt auto; padding: 10px; height: 95%; width: 100%;">
			 <tr><td><br></td> 
			</tr>
			</table>
		</form>
	</div>
		</body>  
<!--            </div>  -->