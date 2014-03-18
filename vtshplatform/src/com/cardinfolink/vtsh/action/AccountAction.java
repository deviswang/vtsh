package com.cardinfolink.vtsh.action;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.cardinfolink.vtsh.dao.MerchantDao;
import com.cardinfolink.vtsh.dao.MerchantImpl;
import com.cardinfolink.vtsh.util.JSONObjectUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class AccountAction {
	
	private JSONObject acnt;
	
	private int page;
	private int rows;
	private long total;

	private int i = 0;
	public String execute(){
		List<DBObject> users = new ArrayList<DBObject>();
		MerchantDao md = new MerchantImpl();
		
		DBObject con = new BasicDBObject();
		
		con.put("regStatus", "H");//用户已经审核过
		
		total = md.getTotalPage();
		users = md.findByCondition(con,(page-1)*rows,rows);
		
		acnt = JSONObjectUtil.getJsonResult(total, users);
		
		i+=1;
		System.out.println("page: "+page +" rows: "+rows+" total: "+total);
		System.out.println("第"+i+" 次查询 "+users.size());
		return "success";
	}

	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public JSONObject getAcnt() {
		return acnt;
	}
	public void setAcnt(JSONObject acnt) {
		this.acnt = acnt;
	}
}
