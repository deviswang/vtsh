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
   
   </script>
  
  
</head>
<body>         
                <table id="tt2" style="height:100%;width:100%;">
                </table>   
                 <script>  
			        $(function(){  
			            $('#tt2').datagrid({  
			                title:'Account Information',  
			                iconCls:'icon-save',  
			                //width:100%,  
			                //height:100%, 
			                cache:false, 
			                nowrap: false,  
			                striped: true,  
			                fit: true,
			                url:'test.action',  
			                sortOrder: 'desc',  
			               // idField:'', 
			                singleSelect:true,
			               // frozenColumns:[[  
			                  //  {field:'ck',checkbox:true},  
			                  ////  {title:'Id',field:'_id.inc',width:80,sortable:true}  
			               // ]],  
			                columns:[
			                [  
			                   {title:'Base Information',colspan:5},  
			                    {field:'opt',title:'Operation',width:100,align:'center', rowspan:2,  
			                        formatter:function(value,rec){  
			                            return '<span style="color:red">Edit Delete</span>';  
			                        }  
			                  }  
			                ],
			                [  
			                	//{field:'ck',checkbox:true}, 
			                	{field:'_id.inc',title:'_id.inc',width:120},  
			                    {field:'gw_currency',title:'gw_currency',width:120},  
			                    {field:'gw_date',title:'gw_date',width:120,rowspan:2},  
			                    {field:'cycle_id',title:'Cycle_ID',width:150,rowspan:2},
			                    {field:'gw_trans_id',title:'gw_trans_id',width:150,rowspan:2},
			                    {field:'gw_trans_id',title:'gw_trans_id',width:150,rowspan:2},
			                    {field:'gw_trans_id',title:'gw_trans_id',width:150,rowspan:2},
			                    {field:'gw_trans_id',title:'gw_trans_id',width:150,rowspan:2},
			                    {field:'gw_trans_id',title:'gw_trans_id',width:150,rowspan:2}
			                ]
			                ],  
			                pagination:true,  
			                rownumbers:true,
			                pageList:[5,10,15,50] 
			            });  
			        });  
			        
			    </script>  
		</body>  
<!--            </div>  -->