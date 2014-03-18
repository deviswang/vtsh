package com.cardinfolink.vtsh.test;

import java.util.List;

import org.junit.Test;

import com.cardinfolink.vtsh.dao.MccDao;
import com.cardinfolink.vtsh.dao.MccImpl;
import com.cardinfolink.vtsh.dao.MerchantDao;
import com.cardinfolink.vtsh.dao.MerchantImpl;
import com.cardinfolink.vtsh.dao.PaychannelContentDao;
import com.cardinfolink.vtsh.dao.PaychannelContentImpl;
import com.cardinfolink.vtsh.dbutil.MongodbUtil;
import com.cardinfolink.vtsh.util.HttpSendUtil;
import com.cardinfolink.vtsh.util.JSONObjectUtil;
import com.cardinfolink.vtsh.util.MD5EncryptUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class TestDB {
	
	@Test
	public void testdb(){
		DBCollection clt = MongodbUtil.getDBCollection("vtsh_txn");
		
		DBCursor dbc = clt.find();
		
		String users = JSON.serialize(dbc);
		
		System.out.println(users);
	}
	public static void main(String[] args) {
		DBCollection clt = MongodbUtil.getDBCollection("vtsh_txn");
		
		
		DBCursor dbc = clt.find();
		
		String users = JSON.serialize(dbc);
		
		System.out.println(users);
	}
	
	@Test
	public void testMcc(){
		MccDao md = new MccImpl();
		List<DBObject> list = md.findAll();
		System.out.println(list.get(0));
		System.out.println(list);
	}
	@Test
	public void testData(){
		System.out.println(JSONObjectUtil.getData("MCC"));
		System.out.println(JSONObjectUtil.getData("CUR"));
		System.out.println(JSONObjectUtil.getData("PAY"));
		PaychannelContentDao pcd = new PaychannelContentImpl();
		List<DBObject> list = pcd.findAll();
		System.out.println(list);
	}
	@Test
	public void testMerchant(){
		MerchantDao md = new MerchantImpl();
		DBObject user = md.findByClientid("000000000000015");
		System.out.println(user);
		System.out.println(MD5EncryptUtil.getMd5String("1111111a").toLowerCase());
		System.out.println(user.get("clientid")+" :: "+user.get("password")
				+" "+user.get("merName"));
		user = md.findByPrinEmail("vtsh-test");
	}
	
	@Test
	public  void testModify(){
		MerchantDao md = new MerchantImpl();
		//DBObject user = md.findByClientid("000000000000015");
		boolean flag = md.updateMerchant(new BasicDBObject("regStatus","D"),"800039245112053");
		System.out.println(flag);
	}
	@Test
	public void test(){
		HttpSendUtil.sendAndGetMerchantInf("test", "test@126.com");
	}
	
	
	
}
