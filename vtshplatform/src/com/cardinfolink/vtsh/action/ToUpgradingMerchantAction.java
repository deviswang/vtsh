package com.cardinfolink.vtsh.action;

import java.util.List;

import net.sf.json.JSONObject;

import com.cardinfolink.vtsh.dao.RegistMerchantDao;
import com.cardinfolink.vtsh.dao.RegistMerchantImpl;
import com.cardinfolink.vtsh.util.JSONObjectUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class ToUpgradingMerchantAction extends BaseAction{
	
	private JSONObject regt;
	
	private int page;
	
	private int rows;
	
	private long total;
	
	private String merchantId;
	
	private String webName;
	
	public String execute(){
		RegistMerchantDao rmd = new RegistMerchantImpl();
		BasicDBObject condition = new BasicDBObject();
		
		condition.clear();
		
//		if(null!=merchantId && !"".equals(merchantId)){
//			condition.put("", merchantId);
//		}
//		if(null!=webName && !"".equals(webName)){
//			condition.put("",webName);
//		}
		
		condition.put("regStatus", "U");
		
		total = rmd.findByCondition(condition).size();
		List<DBObject> merchantList = rmd.findByCondition(condition,(page-1)*rows,rows);
		regt = JSONObjectUtil.getJsonResult(total, merchantList);
		
		return "success";
	}
	public JSONObject getRegt() {
		return regt;
	}
	public void setRegt(JSONObject regt) {
		this.regt = regt;
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
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getWebName() {
		return webName;
	}
	public void setWebName(String webName) {
		this.webName = webName;
	}
	
	
}
