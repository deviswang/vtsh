package com.cardinfolink.vtsh.action;

import java.util.List;

import net.sf.json.JSONArray;

import com.cardinfolink.vtsh.dao.PaychannelContentDao;
import com.cardinfolink.vtsh.dao.PaychannelContentImpl;
import com.mongodb.DBObject;

public class PaychannelContentAction {
	private JSONArray data;
	
	public String execute(){
		PaychannelContentDao pcd = new PaychannelContentImpl();
		List<DBObject> list = pcd.findAll();
		data = JSONArray.fromObject(list);
		return "success";
	}

	public JSONArray getData() {
		return data;
	}

	public void setData(JSONArray data) {
		this.data = data;
	}
	
}
