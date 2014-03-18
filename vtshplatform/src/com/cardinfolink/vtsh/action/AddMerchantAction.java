package com.cardinfolink.vtsh.action;

import com.cardinfolink.vtsh.dao.MerchantDao;
import com.cardinfolink.vtsh.dao.MerchantImpl;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class AddMerchantAction {
	
	private String user;
	
	private boolean ok;
	
	public String execute(){
		System.out.println("开始了");
		MerchantDao md = new MerchantImpl();
		//将json字符串转换成DBObject对象。
		DBObject user2= (DBObject) JSON.parse(user);

		ok = md.insert(user2); 

		System.out.println("已经将用户存入数据库，存入的用户信息是：  " + user2);
		System.out.println(ok);
		System.out.println("结束。。。。");
		
		return "success";
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}
	
	
}
