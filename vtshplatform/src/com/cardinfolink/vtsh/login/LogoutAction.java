package com.cardinfolink.vtsh.login;

import java.util.Map;

import com.cardinfolink.vtsh.action.BaseAction;

public class LogoutAction extends BaseAction{
	public String execute(){
		Map<String,Object> session = getSession();
		session.put("User", null);
		return "success";
	}
}
