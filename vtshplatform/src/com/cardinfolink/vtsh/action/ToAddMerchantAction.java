package com.cardinfolink.vtsh.action;

import com.cardinfolink.vtsh.util.RandomMd5Util;

public class ToAddMerchantAction {
	
	private String md5;
	
	public String execute(){
		
		md5 = RandomMd5Util.getRandomMd5Code();
		
		return "success";
		
	}
	
	public String getMd5() {
		
		return md5;
		
	}
	
	public void setMd5(String md5) {
		
		this.md5 = md5;
		
	}
	
}
