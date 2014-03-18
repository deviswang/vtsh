<%@ page language="java" pageEncoding="utf-8" %>
    <div class="easyui-accordion" data-options="fit:false,border:false" >  
            <div class="menuButton" title="Transaction Management" style="padding:5px;overflow:auto;;text-align:center;background:#F5FFFA;" data-options="selected:true" >  
                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" style="width:80%;border:1px solid orange;background:#F5DEB3"
                		onclick="showInf();"> Transaction Info </a>
				<br/> 
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" style="width:80%;border:1px solid orange;background:#F5DEB3"
                		onclick="clearInf();"> Settlement Info </a>
				<br/>
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-print',plain:true" style="width:80%;border:1px solid orange;background:#F5DEB3"
                		onclick="reconInf();"> Reconciliation Info </a>
				<br/>
            </div>  
<!--            <div title="BUSSINESS Management" style="padding:5px;text-align:center;">  -->
<!--				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"   -->
<!--				        onclick="javascript:$.messager.alert('Warning','Please select a row!','warning');">Business</a>  -->
<!--				<br/> -->
<!--            </div>  -->
            <div class="menuButton" title="Merchant Management" style="padding:5px;text-align:center;background:#F5FFFA;" >  
               <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true"  style="width:80%;border:1px solid orange;background:#F5DEB3" 
				        onclick="acntInf();"> Merchant Info </a>  
				<br/> 
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"   style="width:80%;border:1px solid orange;background:#F5DEB3"
				        onclick="addMerchant();"> Add Merchant </a>  
				<br/> 
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cut',plain:true"   style="width:80%;border:1px solid orange;background:#F5DEB3"
				        onclick="registerMerchant();"> Register Merchant </a>  
				<br/> 
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true"   style="width:80%;border:1px solid orange;background:#F5DEB3"
				        onclick="upgradingMerchant();">  Upgrading Merchant </a>  
				<br/> 
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-print',plain:true"   style="width:80%;border:1px solid orange;background:#F5DEB3"
				        onclick="declineMerchant();"> Declined Merchant </a>  
				<br/> 
            </div>
              
             <div  class="menuButton" title="Help" style="padding:5px;text-align:center;background:#F5FFFA;" >  
              <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true"   style="width:80%;border:1px solid orange;background:#F5DEB3"
				       > Contact Us </a>  
<!--				onclick="$.messager.progress();"    -->
				<br/> 
            </div>  
            
        </div>  
 