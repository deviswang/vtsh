package com.cardinfolink.vtsh.action;

import java.util.List;

import net.sf.json.JSONObject;

import com.cardinfolink.vtsh.dao.TblParaCodeDao;
import com.cardinfolink.vtsh.dao.TblParaCodeImpl;
import com.mongodb.DBObject;

public class GetNationsAction {
	
	private JSONObject tbl;
	
	public String execute(){
		
		 TblParaCodeDao tbcd = new TblParaCodeImpl();
		 List<DBObject> list = tbcd.findAll();
		 DBObject nations =list.get(0);
		 tbl = JSONObject.fromObject(nations);
		 
		return "success";
	}
	
	public JSONObject getTbl(){
		return tbl;
	}
	
	public void setTbl(JSONObject tbl){
		this.tbl = tbl;
	}
	
}
