package com.cardinfolink.vtsh.action;

import com.cardinfolink.vtsh.dao.MerchantDao;
import com.cardinfolink.vtsh.dao.MerchantImpl;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class AddMerchantAction {
	
	private String user;
	
	private boolean ok;
	
	public String execute(){
		System.out.println("��ʼ��");
		MerchantDao md = new MerchantImpl();
		//��json�ַ���ת����DBObject����
		DBObject user2= (DBObject) JSON.parse(user);

		ok = md.insert(user2); 

		System.out.println("�Ѿ����û��������ݿ⣬������û���Ϣ�ǣ�  " + user2);
		System.out.println(ok);
		System.out.println("������������");
		
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
