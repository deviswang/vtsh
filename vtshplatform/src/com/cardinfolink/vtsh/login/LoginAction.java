package com.cardinfolink.vtsh.login;

import java.util.Map;

import com.cardinfolink.vtsh.action.BaseAction;
import com.cardinfolink.vtsh.dao.MerchantDao;
import com.cardinfolink.vtsh.dao.MerchantImpl;
import com.cardinfolink.vtsh.util.MD5EncryptUtil;
import com.mongodb.DBObject;

public class LoginAction extends BaseAction{
	
	private String username;
	private String pwd;
	private String loginErr;
	
	public String execute(){
		if(null==username || "".equals(username) || null==pwd || "".equals(pwd)){
			loginErr="E";
			return "error";
		}
		System.out.println(username+" : "+pwd);
		String password = MD5EncryptUtil.getMd5String(pwd).toLowerCase();
		MerchantDao md = new MerchantImpl();
		DBObject user = md.findByClientid(username);
		if(user==null){
			user = md.findByPrinEmail(username);
		}
		Map<String,Object> session = getSession();
		System.out.println(user);
		if(null!=user && user.get("password").equals(password)){
			session.put("User", username);
			session.put("clientid", user.get("clientid"));
		}else{
			loginErr="T";
			return "error";
		}
		return "success";
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getLoginErr() {
		return loginErr;
	}
	public void setLoginErr(String loginErr) {
		this.loginErr = loginErr;
	}
}
