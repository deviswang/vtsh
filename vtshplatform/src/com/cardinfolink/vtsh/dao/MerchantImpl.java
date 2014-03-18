package com.cardinfolink.vtsh.dao;

import java.util.ArrayList;
import java.util.List;

import com.cardinfolink.vtsh.dbutil.MongodbUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class MerchantImpl implements MerchantDao{
	@Override
	public List<DBObject> findAll(int pageStart,int pageSize) {
		List<DBObject> users = new ArrayList<DBObject>();
		DBCollection clt=MongodbUtil.getDBCollection(DOC_NAME);
		DBCursor dbc = clt.find().skip(pageStart).limit(pageSize);
		try{
			while(dbc.hasNext()){
				DBObject dbo = dbc.next();
				String id = dbo.get("_id").toString();
				dbo.put("_id", id);
				users.add(dbo);
			}
		}catch(Exception e){}
		finally{
			dbc.close();
		}
		return users;
	}

	@Override
	public boolean insert(DBObject obj) {
		boolean flag = false;
		DB db = MongodbUtil.getDB();
		db.requestStart();
		DBCollection clt=MongodbUtil.getDBCollection(DOC_NAME);
		WriteResult wrt = clt.insert(obj);
		if(wrt.getLastError().ok()){
			flag = true;
		}
		db.requestDone();
		return flag;
	}

	@Override
	public boolean updateMerchant(DBObject obj) {
		boolean flag = false;
		DB db = MongodbUtil.getDB();
		db.requestStart();
		DBCollection clt=MongodbUtil.getDBCollection(DOC_NAME);
		WriteResult wrt = clt.update(new BasicDBObject("clientid",obj.get("clientid")),
				new BasicDBObject("$set",obj));
		if(wrt.getLastError().ok()){
			flag = true;
		}
		db.requestDone();
		return flag;
	}
	
	@Override
	public boolean deleteMerchant(DBObject obj) {
		boolean flag = false;
		DB db = MongodbUtil.getDB();
		db.requestStart();
		DBCollection clt=MongodbUtil.getDBCollection(DOC_NAME);
		DBObject delObj = clt.findAndRemove(obj);
		if(delObj!=null){
			flag = true;
		}
		db.requestDone();
		return flag;
	}
	
	@Override
	public long getTotalPage() {
		long total =0;
		DB db = MongodbUtil.getDB();
		db.requestStart();
		DBCollection clt=MongodbUtil.getDBCollection(DOC_NAME);
		total = clt.getCount();
		db.requestDone();
		return total;
	}

	@Override
	public DBObject findByClientid(String id) {
		DBObject user = null;
		DB db = MongodbUtil.getDB();
		db.requestStart();
		DBCollection clt=MongodbUtil.getDBCollection(DOC_NAME);
		user = clt.findOne(new BasicDBObject("clientid",id));
		db.requestDone();
		return user;
	}

	@Override
	public DBObject findByPrinEmail(String name){
		DBObject user = null;
		DB db = MongodbUtil.getDB();
		db.requestStart();
		DBCollection clt=MongodbUtil.getDBCollection(DOC_NAME);
		user = clt.findOne(new BasicDBObject("prinEmail",name));
		db.requestDone();
		return user;
	}
	
	@Override
	public List<DBObject> findAll() {
		List<DBObject> users = new ArrayList<DBObject>();
		DBCollection clt=MongodbUtil.getDBCollection(DOC_NAME);
		DBCursor dbc = clt.find();
		try{
			while(dbc.hasNext()){
				DBObject dbo = dbc.next();
				String id = dbo.get("_id").toString();
				dbo.put("_id", id);
				users.add(dbo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			dbc.close();
		}
		return users;
	}
	
	
	@Override
	public boolean updateMerchant(DBObject obj, String clientid) {
		boolean flag = false;
		DB db = MongodbUtil.getDB();
		db.requestStart();
		DBCollection clt=MongodbUtil.getDBCollection(DOC_NAME);
		WriteResult wrt = clt.update(new BasicDBObject("clientid",clientid),
			new BasicDBObject("$set",obj));
		if(wrt.getLastError().ok()){
			flag = true;
		}
		db.requestDone();
		return flag;
	}

	@Override
	public List<DBObject> findByCondition(DBObject con, int pageStart,
			int pageSize) {
		List<DBObject> users = new ArrayList<DBObject>();
		DBCollection clt=MongodbUtil.getDBCollection(DOC_NAME);
		DBCursor dbc = clt.find(con).skip(pageStart).limit(pageSize);
		try{
			while(dbc.hasNext()){
				DBObject dbo = dbc.next();
				String id = dbo.get("_id").toString();
				dbo.put("_id", id);
				users.add(dbo);
			}
		}catch(Exception e){}
		finally{
			dbc.close();
		}
		return users;
	}
	
}
