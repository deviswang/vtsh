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
		m= m<10?"0"+m:m;
		d= d<10?"0"+d:d;
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
  //export Csv
  function exportCsv(){
  	window.location.href="reconCSV.action";
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
                <table id="tt2" style="height:100%;width:100%;" align="center">
                </table><div align="center">    
                </div><div id="toolbar" style="padding:5px;height:auto;"><div align="center"> 
<!--                	&lt;div id=&quot;crud&quot;&gt;--> 
<!--            		&lt;a class=&quot;easyui-linkbutton&quot; plain=&quot;true&quot; iconCls=&quot;icon-edit&quot; onclick=&quot;getTransDetails();&quot;&gt;Details&lt;/a&gt;--> 
<!--            		&lt;a class=&quot;easyui-linkbutton&quot; plain=&quot;true&quot; iconCls=&quot;icon-save&quot; onclick=&quot;editUser();&quot;&gt;Save&lt;/a&gt;--> 
<!--            		&lt;a class=&quot;easyui-linkbutton&quot; plain=&quot;true&quot; iconCls=&quot;icon-add&quot;&gt;Add&lt;/a&gt;--> 
<!--					&lt;/div&gt;--> 
					</div><div id="search"><div align="center"> 
					</div><table id="tranSh">
					<form id="tsform" method="post"><div align="center"> 
						</div><tr align="center"><td>Date from</td><td>
						<input  id="startDate" name="startDate" data-options="formatter:myDateFormat"  class="easyui-datebox" style="width:100px;"/></td>
						<td>to </td><td>
						<input id="endDate"  name="endDate" data-options="formatter:myDateFormat" class="easyui-datebox" style="width:100px;"/></td>
						</tr><div align="center"> 
						</div><tr align="center">
<!--						
<td>Amount from</td> <td><input id="startAnt" name="startAnt" class="easyui-number" style="width:100px;"/></td>-->
<!--						<td>to </td><td> <input id="endAnt" name="endAnt" class="easyui-number" style="width:100px;"/></td>-->
						<td>Client ID</td><td><input id="merId" name="merId" class="easyui-number" style="width:100px;"/></td>
<!--						<td>Trade Type</td><td><input id="tdType" name="tdType" class="easyui-number" style="width:100px;"/></td>-->
						</tr><div align="center"> 
						</div><tr>
						<td align="center">  <a class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="searchTranForm();">Search</a> </td>
						<td align="center">  <a class="easyui-linkbutton" plain="true" iconCls="icon-cancel" onclick="resetQueryParams();">Clear</a> </td>
						<td>  <a class="easyui-linkbutton" plain="true" iconCls="icon-ok" onclick="exportCsv();">Export CSV</a>
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
			                title:'Transaction Clearing Information',  
			                iconCls:'icon-save',  
			                toolbar:'#toolbar', 
			                cache:false, 
			                nowrap: false,  
			                striped: true,  
			                fit: true,
			                url:'reconciliation.action',  
			                singleSelect:true,
			                columns:[
			                [  
			                	{field:'txnSetDate',title:'Settlement Date',sortable:true},
			                    {field:'clientid',title:'Merchant ID',sortable:true},
			                    {field:'merName',title:'Merchant Name',sortable:true}, 
			                    {field:'reconCount',title:'Transaction Count'}, 
			                    {field:'txnReconCur',title:'Transaction Currency'}, 
			                    {field:'reconAmount',title:'Transaction Amount'},
			                    {field:'reconFee',title:'MDR'},
			                    {field:'reconSet',title:'Settlement Currency'},
			                    {field:'reconSetAmount',title:'settlement Amount'},
			                    {field:'reconPaychAmount',title:'Channel Credit amount'},
			                    {field:'todayPendingAmount',title:'Today Pending Amount'},
			                    {field:'historyPendingAmount',title:'History Pending Amount'},
			                    {field:'totalPendingAmount',title:'Total Pending Amount'},
			                    {field:'transAmount',title:'Transfer Amount'},
			                    {field:'tranThreshold',title:'Transfer Threshold'},
			                    {field:'reconBenBank',title:'Beneficiary Bank'},
			                    {field:'reconSwiftCode',title:'Bank Swift Code'},
			                    {field:'reconBankAddr',title:'Bank Address'},
			                    {field:'reconBenName',title:'Beneficiary Name'},
			                    {field:'reconAccountNo',title:'Bank Account No.'}
			                ]
			                ], 
			                remoteSort:false,
			                sortName:'txnSetDate',
			                sortOrder:'desc',   
			                pagination:true,  
			                rownumbers:true,
			                pageList:[5,10,20,50],
			                queryParams:{},
			                onDblClickRow:function(index,field,value){getTransDetails();}
			            });
			        });  
			        
   //search transaction by conditions
   function searchTranForm(){
   		var queryParams = $('#tt2').datagrid('options').queryParams;
   		var startD=$('#startDate').datebox('getValue');
   		var endD=$('#endDate').datebox('getValue');
   		//var startA=$('#startAnt').val();
   	//	var endA=$('#endAnt').val();
   		var tdtp = $('#tdType').val();
   		var merid = $('#merId').val();
   		
   		queryParams['startDate']=startD;
   		queryParams['endDate']=endD;
   		//queryParams['startAnt']=startA;
   		//queryParams['endAnt']=endA;
   		queryParams['tdType']=tdtp;
   		queryParams['merId']=merid;
   		
   		$('#tt2').datagrid('reload');
   }
			
	function resetQueryParams(){
		$('#tsform').form('reset');
		$('#startDate').datebox('setValue','');
		$('#endDate').datebox('setValue','');
		//$('#tt2').datagrid('reload');
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
			        /*
			        function getTransDetails(){
            			var row = $('#tt2').datagrid('getSelected');
            			if (row){
							$('#tranDetail').dialog('open').dialog('setTitle','Transaction Information');
							$('#tranDt').form('myLoad',row);
							$('#mfr').tooltip({content:row.m_front_response});
						}else{
							$.messager.alert('Warning','Please select a row!','warning');
						}
            		}*/
            		
            		/**get the length of a json
			        function getJsonLength(jn){
			        	var length=0;
			        	for(var i in jn){
			        		length++;
			        	}
			        	return length;
			        }*/
			        
			        /**set the input  readonly
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
			        */
			    </script>  
			    
		</body>  
<!--            </div>  -->