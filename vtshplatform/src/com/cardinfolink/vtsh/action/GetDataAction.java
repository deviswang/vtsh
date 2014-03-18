package com.cardinfolink.vtsh.action;

import com.cardinfolink.vtsh.util.JSONObjectUtil;

import net.sf.json.JSONObject;

public class GetDataAction {
	private String tbName;
	
	private JSONObject data;
	
	public String execute(){
		
		data = JSONObjectUtil.getData(tbName);
		
		return "success";
	}

	public String getTbName() {
		return tbName;
	}

	public void setTbName(String tbName) {
		this.tbName = tbName;
	}

	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}
	
}
