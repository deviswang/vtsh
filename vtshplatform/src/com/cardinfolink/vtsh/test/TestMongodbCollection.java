package com.cardinfolink.vtsh.test;

import java.net.UnknownHostException;
import org.junit.Test;

import com.cardinfolink.vtsh.dao.MerchantDao;
import com.cardinfolink.vtsh.dao.MerchantImpl;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;

public class TestMongodbCollection {
	
	@Test
	public void testMongo() throws UnknownHostException{
		Mongo mg = new Mongo();
		DB db = mg.getDB("database");//获取数据库连接
		DBCollection users = db.getCollection("user");//获取文档连接
		DBCursor cur = users.find();//查找所有文档
		
		while(cur.hasNext()){
			System.out.println(cur.next());
		}
		DBObject dbo = users.findOne();
		
		System.out.println("---------------------------------------------");
		System.out.println(dbo);
		
		//查询所有的DataBase
		for(String name : mg.getDatabaseNames()){
			System.out.println("dbName: "+name);
		}
		
		//查询所有聚集集合(相当于ORM中的数据表)
		System.out.println(JSON.serialize(cur));
		
		String json = JSON.serialize(cur);
		
		System.out.println(cur.count());
		System.out.println(json);
		
		
		
	}
	
	public static void main(String[] args) {
//		DBCollection cur = MongodbUtil.getDBCollection("vtsh_txn");
//		DBCursor dbc = cur.find();
//		while(dbc.hasNext()){
//			System.out.println(dbc.next().get("_id"));
//		}
//		System.out.println("----------------------");
//		DBCollection cur2 = MongodbUtil.getDBCollection("vtsh_acnt");
//		DBCursor dbc2 = cur2.find();
//		while(dbc2.hasNext()){
//			System.out.println(dbc2.next());
//		}
		
//		System.out.println("开始了");
//		MerchantDao md = new MerchantImpl();
//		
//		DBObject obj = new BasicDBObject();
//		obj.put("_id","52d4cfdaf10eeac4b2f18a29");
//		obj.put("clientid", "000000000000056");
//		
//		BasicDBObject fee = new BasicDBObject();
//		
//		fee.put("fee1","0.01" );
//		fee.put("fee2","0.001");
//		//fee.put("front_url", "http://211.147.64.194/front");
//		//fee.put( "back_url", "http://211.147.64.194/back");
//		
//		obj.put("fee", fee);
//		obj.put("md5", "359445454534534" );
//		BasicDBObject unp = new BasicDBObject();
//		unp.put("merchantId" , "0000000000000666");
//		unp.put("md5" ,"359445454564asdasdf56456");
//		unp.put("acqcode", "28460250");
//		unp.put("front_url","http://211.147.64.194/vtpaymensdfsdft.comsdf");
//		unp.put("back_url" ,"http://211.147.64.194/cardinfolink.com");
//		//unp.put(key, val);
//		obj.put("unionpay", unp);
//		
//		System.out.println(md.updateMerchant(obj));
//		//System.out.println(md.insert(obj));
//		
//		System.out.println("Overl");
//		
//		List<DBObject> list = md.findAll();
//		for(DBObject dbo : list){
//			System.out.println(dbo);
//		}
		MerchantDao md = new MerchantImpl();
		DBObject obj = new BasicDBObject("clientid","000000000000024");
		System.out.println(md.deleteMerchant(obj));
		System.out.println("Overl");
		
		
	}
}
