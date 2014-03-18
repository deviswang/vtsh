package com.cardinfolink.vtsh.action;

import com.cardinfolink.vtsh.dao.MerchantDao;
import com.cardinfolink.vtsh.dao.MerchantImpl;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class RegStatusAction  extends BaseAction{
	
	private String status;
	
	private String clientId;
	
	private boolean flag;
	
	private MerchantDao md;
	
	public String execute(){
		
		System.out.println("½øÈëÐÞ¸Ä×¢²á×´Ì¬¡£¡£");
		//DBObject con = new BasicDBObject();
		System.out.println(clientId);
		DBObject merchant = new MerchantImpl().findByClientid(clientId);
		System.out.println(merchant);
		if(status!=null && "H".equals(status)){
			merchant.put("max_amount","5000USD");
		}
		if(status!=null && "D".equals(status)){
			merchant.put("max_amount", "10USD");
			merchant.put("tranStatus", "N");
		}
		merchant.put("regStatus",status);
		flag = md.updateMerchant(merchant , clientId);
		
		return "success"; 
		
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public MerchantDao getMd() {
		return md;
	}
	public void setMd(MerchantDao md) {
		this.md = md;
	}
}
