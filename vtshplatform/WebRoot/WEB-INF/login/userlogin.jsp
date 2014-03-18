<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--<meta name="viewport" content="initial-scale=2.0,width=device-wiath"/>-->
<title>Merchant Service Platform</title>
<link href="css/VTlogin2.css" media="screen" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#imageCode').click(function(){
			var url = 'image.action?image='+new Date();
        	$(this).attr('src',url);
		});
		
	});
	
	/*异步地验证验证码*/
        	var isPass = false;
        	$(function(){
        		$('#checkCode').blur(function(){
        			var code=$(this).val();
        			if(code==null || code==""){
        				$('#checktips').css("color","orange").html("Empty checkcode!");
        				return false;
        			}
        			$.post(
        				'validateCheckCode.action?date='+new Date(),
        				{"userCode":code},
        				function(data){
        					var pass=data;
        					if(pass==true){
        						isPass=true;
        						$('#checktips').css("color","green").html("validate passed!");
        					}else{
        						$('#checktips').css("color","orange").html("invalid check code!");
        					}	
        				}
        			);		
        		});
        	});
       
        	//校验出错时改变样式:
        	function modCss(bool,id){
        		if(bool){
        			//$('#checktips').css("color","green").html("validate passed!");//css('border','2px solid red');
        		}else{
        			$('#checktips').css("color","orange").html("Please input valid email!");
        		}
        	}
        	
        	//判断的函数
        	function isRight(str,myreg){
        	
        	
                    if(myreg.test(str))
                    {
                    	return true;
                    }
                    else{
                    	return false;
                    }
        	}
        	
        	//验证邮箱
        	var isMail=false ;
        	function checkEmail(id){
        		var str=document.getElementById(id).value;
        		var myreg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/; 	
        		isMail = isRight(str,myreg);
        		modCss(isMail,id)
        		return isRight(str,myreg);
        	}
        	
        	//检查验证码
        	function checkCode(){
        		var code=$('#checkCode').val();
        			if(code==null || code==""){
        				$('#checktips').css("color","orange").html("Empty checkcode!");
        				return false;
        			}
        			$.post(
        				'validateCheckCode.action?date='+new Date(),
        				{"userCode":code},
        				function(data){
        					var pass=data;
        					if(pass==true){
        						isPass=true;
        						$('#checktips').css("color","green").html("validate passed!");
        					}else{
        						$('#checktips').css("color","orange").html("invalid check code!");
        					}	
        				}
        			);	
        	}
        	
        	
        	//登录
        	function userLogin(){
        		checkCode();
        		if(isPass){
        			$('#userLog').submit();
        		}else{
        			$('#checktips').css("color","orange").html("invalid check code!");
        		}
        	}

</script>

<style type="text/css" >
.sub{
width:271px;height:45px;color:whitesmoke;font-size:18px;font-family:Arial, Helvetica, sans-serif;
background:#00a3cf;border:1px double #00a3cf;
}
.sub:hover{
	cursor:pointer;
}
#imageCode:hover{
	cursor:pointer;
}

</style>

</head>

<body onkeypress="if(event.keyCode==13) userLogin();">
<div class="wap">
	<header><a href="#"><img src="images/vtLogo.PNG"></a><a class="logoTxt"  href="#"><img src="images/vtLogotxt.PNG"></a></header>
    <div class="content" >
    	<ul >
        	<li><p>VTSH SERVICE PLATFORM LOGIN</p></li>
            <li class="TBbar" >
            <form id="userLog" name="login" action="login.action" method="post">
            <table width="50%"  style="margin: 0pt auto; text-align:left;" height="199">
            	<tr>
                	<td height="45" width="40" class="tablePic"><img src="images/user.PNG"></td><td colspan="2">
                	<input name="username" id="username" type="text" class="txt" placeholder="Username" /></td>
                    </tr>
                    <tr>
                    <td height="45" width="40" class="tablePic"><img src="images/lock.PNG"></td><td colspan="2">
                    <input  name="pwd" id="pwd" type="password" class="txt" placeholder="Password" /></td>
                    </tr>
                 <tr>
                  <td height="45" width="40" class="tablePic"></td>
                 <td ><input id="checkCode" name="checkcode"  type="text" class="txt" placeholder="Verification Code" style="width:100px;"/>
                 	<img id="imageCode"  src="image.action" alt="checkCode" title="Click to get another one" style="margin-bottom:-12px;margin-top:5px;"/>
                 </td>
                </tr>
                <tr>
                	<td colspan="3" height="20" style="color:orange;" id="checktips" >
                	<s:if test='"T"==loginErr'>
                		<span style="color:orange">Invalid username or password</span>
                	</s:if>
                	<s:elseif test='"E"==loginErr'>
                		<span style="color:orange">Empty username or password</span>
                	</s:elseif>
                	
                	</td><td valign="top"></td>
                </tr>
				   <tr>
                	<td colspan="3" height="60">
                		<input type="button"  class="sub" value="Login" onclick="userLogin();" />
                	</td><td valign="top"><br></td>
                </tr>
            </table>
            </form>
            </li>
        </ul>
    </div> 
    
    <footer>
    <ul>
    <li>
   <p>Copyright © 2013 VTPayment. All rights reserved. | <a href="#">Privacy Policy</a>    <a href="#">Anti-Money Laundering</a></p>
<p>Merchants are advised to read the Terms of Service during registration carefully.</p>
</li>
</ul>
</footer>
    
</div>
</body>
</html>
