package com.cardinfolink.vtsh.test;

import java.util.List;

import com.cardinfolink.vtsh.dao.TransactionDao;
import com.cardinfolink.vtsh.dao.TransactionImpl;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class TestTransSearch {
	
	public static void main(String[] args) {
		String startDate = "2014-1-1";
		String endDate = "2014-1-22";
		String startAnt = "0";
		String endAnt = "2";
		
		System.out.println("startDate: "+startDate+" endDate: "
				+endDate+" startAnt: "+startAnt+" endAnt: "+endAnt);
		BasicDBObject condition = new BasicDBObject();
		// startDate < gw_date <=endDate;
//		BasicDBObject dateCon = new BasicDBObject("$gt",startDate).append("$lte", endDate);
		// set amount
		BasicDBObject amountCon = new BasicDBObject("$gt",startAnt).append("$lte", endAnt);
		//set search key--value
//		condition.put("gw_date", dateCon);
//		condition.put("m_request.clientid", dateCon);
		condition.put("gw_amount", amountCon);
		
		TransactionDao td = new TransactionImpl();
		List<DBObject> list = td.findByCondition(condition);
		for(DBObject dbo : list){
			System.out.println(dbo);
		}
	}
}
