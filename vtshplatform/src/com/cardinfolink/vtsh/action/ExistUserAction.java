package com.cardinfolink.vtsh.action;

import com.cardinfolink.vtsh.dao.MerchantDao;
import com.cardinfolink.vtsh.dao.MerchantImpl;
import com.mongodb.DBObject;

public class ExistUserAction {
	
	private String userInfo;
	private boolean ok;
	
	public String execute(){
		
		MerchantDao md = new MerchantImpl();
		if(userInfo.equals("")){
			
		}
		DBObject merchant = md.findByPrinEmail(userInfo);
		if(null==merchant){
			ok = false;
		}else{
			ok = true;
		}
		return "success";
	}

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}
	
}
