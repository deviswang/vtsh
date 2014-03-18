package com.cardinfolink.vtsh.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.cardinfolink.vtsh.dao.MerchantDao;
import com.cardinfolink.vtsh.dao.MerchantImpl;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class DeleteMerchantAction {
	private String clientid;
	private boolean flag;
	
	public String execute(){
		
		HttpServletRequest request = (HttpServletRequest) ServletActionContext.getRequest();
		
		System.out.println(clientid);
		clientid = request.getParameter("clientid");
		MerchantDao md = new MerchantImpl();
		DBObject obj = new BasicDBObject("clientid",clientid);
		
		flag = md.deleteMerchant(obj);
		System.out.println("delete ok");
		
		return "success";
	}
	public String getClientid() {
		return clientid;
	}
	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
}
