package com.cardinfolink.vtsh.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONObject;

import com.cardinfolink.vtsh.dao.TransactionDao;
import com.cardinfolink.vtsh.dao.TransactionImpl;
import com.cardinfolink.vtsh.util.JSONObjectUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class TransactionSearchAction {
	
	private String startDate;
	private String endDate;
	private String startAnt;
	private String endAnt;
	
	private JSONObject result;
	
	public String execute(){
		String stDate = ServletActionContext.getRequest().getParameter("startDate");
		
		System.out.println("startDate: "+startDate+" endDate: "
				+endDate+" startAnt: "+startAnt+" endAnt: "+endAnt+" : asdf"+stDate);
		BasicDBObject condition = new BasicDBObject();
		// startDate < gw_date <=endDate;
		BasicDBObject dateCon = new BasicDBObject("$gt",startDate).append("$lte", endDate);
		// set amount
		BasicDBObject amountCon = new BasicDBObject("$gt",startAnt).append("$lte", endAnt);
		//set search key--value
		condition.put("gw_date", dateCon);
		//condition.put("m_request.clientid", dateCon);
		condition.put("gw_amount", amountCon);
		
		TransactionDao td = new TransactionImpl();
		List<DBObject> list = td.findByCondition(condition);
//		for(DBObject dbo : list){
//			System.out.println(dbo);
//		}
		result = JSONObjectUtil.getJsonResult((long)list.size(),list);
		for(DBObject dbo : list){
			System.out.println(dbo);
		}
		return "success";
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStartAnt() {
		return startAnt;
	}

	public void setStartAnt(String startAnt) {
		this.startAnt = startAnt;
	}

	public String getEndAnt() {
		return endAnt;
	}

	public void setEndAnt(String endAnt) {
		this.endAnt = endAnt;
	}

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

	
}
