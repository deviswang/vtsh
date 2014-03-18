package com.cardinfolink.vtsh.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

public abstract class BaseAction implements SessionAware{
	
	// 自定义属性来接收注入的Session
	private Map<String,Object> session;
	
	// 用于子类取得Session
	public Map<String,Object> getSession(){
		return session;
	}
	
	// Struts2通过此方法注入Session
	public void setSession(Map<String,Object> m){
		//System.out.println("注入session:BaseAction.setSession()...");
		this.session = m;
	}
	
}
