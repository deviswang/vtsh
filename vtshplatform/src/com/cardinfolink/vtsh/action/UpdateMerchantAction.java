package com.cardinfolink.vtsh.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import com.cardinfolink.vtsh.dao.MerchantDao;
import com.cardinfolink.vtsh.dao.MerchantImpl;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class UpdateMerchantAction {
	private String user;
	private String clientid;
	private String md5;
	private String fee1;
	private String fee2;
	private String merchantId;
	private String acqcode;
	private String front_url;
	private String back_url;
	private boolean flag;
	
	public String execute(){
		
		HttpServletRequest request = (HttpServletRequest) ServletActionContext.getRequest();
		back_url = request.getParameter("unionpay.refund_url");
		front_url = request.getParameter("unionpay.query_url");
		fee1 = request.getParameter("fee.fee1");
		fee2 = request.getParameter("fee.fee2");
		merchantId = request.getParameter("unionpay.merchantId");
		acqcode = request.getParameter("unionpay.acqcode");
		String md52 = request.getParameter("unionpay.md5");
		
		System.out.println(fee1);
		
		System.out.println("开始了");
		
		MerchantDao md = new MerchantImpl();
		
		DBObject obj = new BasicDBObject();
		
		obj.put("clientid", clientid);
		
		BasicDBObject fee = new BasicDBObject();
		
		fee.put("fee1",fee1 );
		fee.put("fee2",fee2);
		
		obj.put("fee", fee);
		obj.put("md5", md5 );
		
		BasicDBObject unp = new BasicDBObject();
		
		unp.put("merchantId" , merchantId);
		unp.put("md5" ,md52);
		unp.put("acqcode", acqcode);
		unp.put("front_url",  front_url);
		unp.put("back_url" , back_url);
		
		obj.put("UP", unp);
		
		System.out.println(obj);
		
		DBObject user2 = (DBObject)JSON.parse(user);
		
		flag = md.updateMerchant(user2);
		
		System.out.println("更新的用户信息是:  "+user2);
		System.out.println(flag);
		System.out.println("结束。。。。");
		
		return "success";
	}
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getClientid() {
		return clientid;
	}
	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public String getFee1() {
		return fee1;
	}
	public void setFee1(String fee1) {
		this.fee1 = fee1;
	}
	public String getFee2() {
		return fee2;
	}
	public void setFee2(String fee2) {
		this.fee2 = fee2;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getAcqcode() {
		return acqcode;
	}
	public void setAcqcode(String acqcode) {
		this.acqcode = acqcode;
	}
	public String getFront_url() {
		return front_url;
	}
	public void setFront_url(String frontUrl) {
		front_url = frontUrl;
	}
	public String getBack_url() {
		return back_url;
	}
	public void setBack_url(String backUrl) {
		back_url = backUrl;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
}
