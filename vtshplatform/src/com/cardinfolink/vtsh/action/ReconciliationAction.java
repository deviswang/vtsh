package com.cardinfolink.vtsh.action;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import com.cardinfolink.vtsh.dao.ReconciliationDao;
import com.cardinfolink.vtsh.dao.ReconciliationImpl;
import com.cardinfolink.vtsh.util.DateUtil;
import com.cardinfolink.vtsh.util.JSONObjectUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class ReconciliationAction extends BaseAction{
	private JSONObject reconInf;
	private int page;
	private int rows;
	private long total;
	
	private String startDate;
	private String endDate;
	private String merId;
//	private String tdType;	
	
	public String execute(){
		System.out.println("clearing  : startDate: "+startDate+" endDate: "+endDate
				+" MerID: "+merId +" tdType: ");
		
		//Map<String,Object> session = this.getSession();
		//String clientid = (String)session.get("clientid");
		BasicDBObject con = new BasicDBObject();
		
		ReconciliationDao rd = new ReconciliationImpl();
		
		if((startDate==null || "".equals(startDate))
				&& (endDate==null || "".equals(endDate)) 
				&& (merId==null || "".equals(merId))
			//  && (tdType==null || "".equals(tdType))
		){
			//�����ѯ����Ϊ�գ�ֱ�Ӳ���ǰ����ȫ�����:
			//��õ�¼�û���id
			//	con.put("clientid", clientid);
			String date1 = DateUtil.formatCurrentDate();
			Date date_2 = DateUtil.addDay(new Date(), -3);
			String date2 = DateUtil.formatCurrentDate(date_2);
			
			BasicDBObject dateCon = null;
			dateCon = new BasicDBObject("$gte",date2).append("$lte",date1);
			con.put("txnSetDate", dateCon);
			System.out.println(date1);
			System.out.println(con);
			
			//System.out.println(clientid);
		}else {
			con.clear();
			BasicDBObject dateCon = null;
			if((startDate!=null && !"".equals(startDate)) && (endDate!=null && !"".equals(endDate))){
				// startDate < gw_date <=endDate;
				System.out.println("hello111");
				System.out.println(startDate+" : "+endDate);
				dateCon = new BasicDBObject("$gte",startDate).append("$lte", endDate);
				//set search key--value
				con.put("txnSetDate", dateCon);
				System.out.println();
			}
			if((merId!=null && !"".equals(merId))){
				System.out.println("comming into Mer_id");
				con.put("clientid", merId);
			}
//			if(tdType!=null && !"".equals(tdType)){
//				System.out.println("comming into Transaction_type");
//				con.put("txnType", tdType);
//			}
		}
		
		List<DBObject> list;
		try {
			
			list = rd.findByCondition(con);
			total = list.size();
			List<DBObject> list2 = rd.findByCondition(con,(page-1)*rows, rows);
			reconInf = JSONObjectUtil.getJsonResult(total,list2);
			System.out.println(list);
			System.out.println(" +++++ "+list2);
			System.out.println(reconInf);
			System.out.println("....denglude "+" page "+ page+" rows "+rows);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "success";
	}
	
	
	public JSONObject getReconInf() {
		return reconInf;
	}
	public void setReconInf(JSONObject reconInf) {
		this.reconInf = reconInf;
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
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
//	public String getTdType() {
//		return tdType;
//	}
//	public void setTdType(String tdType) {
//		this.tdType = tdType;
//	}
	
}
