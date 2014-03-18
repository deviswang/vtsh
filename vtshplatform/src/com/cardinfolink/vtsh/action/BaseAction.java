package com.cardinfolink.vtsh.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

public abstract class BaseAction implements SessionAware{
	
	// �Զ�������������ע���Session
	private Map<String,Object> session;
	
	// ��������ȡ��Session
	public Map<String,Object> getSession(){
		return session;
	}
	
	// Struts2ͨ���˷���ע��Session
	public void setSession(Map<String,Object> m){
		//System.out.println("ע��session:BaseAction.setSession()...");
		this.session = m;
	}
	
}
