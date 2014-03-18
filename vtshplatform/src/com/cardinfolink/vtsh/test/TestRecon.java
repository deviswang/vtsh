package com.cardinfolink.vtsh.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.cardinfolink.vtsh.dao.MerchantDao;
import com.cardinfolink.vtsh.dao.MerchantImpl;
import com.cardinfolink.vtsh.dao.ReconciliationDao;
import com.cardinfolink.vtsh.dao.ReconciliationImpl;
import com.cardinfolink.vtsh.reconciliation.TxnReconciliation;
import com.cardinfolink.vtsh.util.DateUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class TestRecon {
	
	@Test
	public void test1(){
		String yesterday = DateUtil.formatCurrentDate(DateUtil.addDay(new Date(), -1));
		System.out.println(yesterday);
	}
	
	@Test
	public void test2(){
		MerchantDao md = new MerchantImpl();
		List<DBObject> merchants = md.findAll();
		TxnReconciliation.MerchantTxnClear(merchants);
	}
	
	@Test
	public void test3(){
		ReconciliationDao rd = new ReconciliationImpl();
		BasicDBObject con = new BasicDBObject();
		try {
			List<DBObject> merchants = rd.findByCondition(con);
			for(DBObject merchant : merchants){
				System.out.println(merchant);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
