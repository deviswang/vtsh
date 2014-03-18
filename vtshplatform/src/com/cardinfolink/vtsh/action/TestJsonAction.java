package com.cardinfolink.vtsh.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import com.cardinfolink.vtsh.dao.TransactionDao;
import com.cardinfolink.vtsh.dao.TransactionImpl;
import com.cardinfolink.vtsh.util.DateUtil;
import com.cardinfolink.vtsh.util.JSONObjectUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class TestJsonAction {
	
	private JSONObject transInfs;
	
	private int page;
	
	private int rows;
	
	private long total;
	
	private String queryParams;
	private String startDate;
	private String endDate;
	private String startAnt;
	private String endAnt;
	private String merId;
	private String tdType;
	
	private int i=0;
	
	public String execute(){
		System.out.println("查询条件: "+queryParams+"");
		System.out.println("startDate: "+startDate+" endDate: "
				+endDate+" startAnt: "+startAnt+" endAnt: "+endAnt
				+" MerID: "+merId +" tdType: "+tdType);
		List<DBObject> tranList = new ArrayList<DBObject>();
		TransactionDao td = new TransactionImpl();
		//查询条件
		BasicDBObject condition = new BasicDBObject();
		if((startDate==null || "".equals(startDate))
				&& (endDate==null || "".equals(endDate)) 
				&& (startAnt==null || "".equals(startAnt)) 
				&& (endAnt==null || "".equals(endAnt)) 
				&& (merId==null || "".equals(merId))
				&& (tdType==null || "".equals(tdType))
		){//如果查询条件为空，直接查找前三天的结果:
//			tranList = td.findAll((page-1)*rows,rows);
//			total = td.getTotalPage();
			String date1 = DateUtil.formatCurrentDate();
			Date date_2 = DateUtil.addDay(new Date(), -3);
			String date2 = DateUtil.formatCurrentDate(date_2);
			
			BasicDBObject dateCon = null;
			dateCon = new BasicDBObject("$gte",date2).append("$lte",date1);
			condition.put("gw_date", dateCon);
			
		}else {
			
			BasicDBObject dateCon = null;
			BasicDBObject amountCon = null;
			
			if((startDate!=null && !"".equals(startDate)) && (endDate!=null && !"".equals(endDate))){
				// startDate < gw_date <=endDate;
				System.out.println("hello111");
				dateCon = new BasicDBObject("$gte",startDate).append("$lte", endDate);
				//set search key--value
				condition.put("gw_date", dateCon);
			}
			if((startAnt!=null && !"".equals(startAnt)) 
					&& (endAnt!=null && !"".equals(endAnt))){
				// set amount         
				System.out.println("hello222");
				amountCon = new BasicDBObject("$gt",startAnt).append("$lte", endAnt);
				//condition.put("m_request.clientid", dateCon);
				condition.put("gw_amount", amountCon);
			}if((merId!=null && !"".equals(merId))){
				System.out.println("comming into Mer_id");
				condition.put("merchant.clientid", merId);
			}if(tdType!=null && !"".equals(tdType)){
				System.out.println("comming into Transaction_type");
				condition.put("gw_tran_type", tdType);
			}
		}
		
		
		tranList = td.findByCondition(condition,(page-1)*rows,rows);
		total = td.findByCondition(condition).size();
		
		transInfs = JSONObjectUtil.getJsonResult(total, tranList);
		i+=1;
		//System.out.println("page: "+page +" rows: "+rows+" total: "+total);
		//System.out.println("第"+i+" 次查询 "+transInfs.size());
		return "success";
	}
	public JSONObject getTransInfs() {
		return transInfs;
	}
	public void setTransInfs(JSONObject transInfs) {
		this.transInfs = transInfs;
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
	public String getQueryParams() {
		return queryParams;
	}
	public void setQueryParams(String queryParams) {
		this.queryParams = queryParams;
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
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	public String getTdType() {
		return tdType;
	}
	public void setTdType(String tdType) {
		this.tdType = tdType;
	}
	
}
