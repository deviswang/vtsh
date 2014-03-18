package com.cardinfolink.vtsh.test;

import java.util.List;

import org.junit.Test;

import com.cardinfolink.vtsh.dao.ThresholdDao;
import com.cardinfolink.vtsh.dao.ThresholdImpl;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class TestThreshold {
	@Test
	public void test1(){
		ThresholdDao tld = new ThresholdImpl();
		BasicDBObject con = new BasicDBObject();
		con.put("threshold_rule", "1");
		List<DBObject> holds = tld.findByCondition(con);
		for(DBObject hold : holds){
			System.out.println(hold);
			DBObject dds = (DBObject) hold.get("threshold_param");
			System.out.println(dds);
			String thresMoney = dds.get("USD")+"";
			System.out.println(thresMoney);
		}
		
		
	}	
}
