package com.cardinfolink.vtsh.dao;

import java.util.ArrayList;
import java.util.List;

import com.cardinfolink.vtsh.dbutil.MongodbUtil;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class RegistMerchantImpl implements RegistMerchantDao{

	@Override
	public List<DBObject> findAll() {
		List<DBObject> clearList = new ArrayList<DBObject>();
		//DB db = MongodbUtil.getDB();
		DBCollection clt=MongodbUtil.getDBCollection(DOC_NAME);
		DBCursor dbc = clt.find();
		try{
			while(dbc.hasNext()){
				DBObject dbo = dbc.next();
				String id = dbo.get("_id").toString();
				dbo.put("_id", id);
				clearList.add(dbo);
			}
		}catch(Exception e){}
		finally{
			dbc.close();
		}
		return clearList;
	}

	@Override
	public List<DBObject> findByCondition(DBObject condition) {
		List<DBObject> acnt = new ArrayList<DBObject>();
		DBCollection clt=MongodbUtil.getDBCollection(DOC_NAME);
		DBCursor dbc = clt.find(condition);
		try{
			while(dbc.hasNext()){
				DBObject dbo = dbc.next();
				String id = dbo.get("_id").toString();
				dbo.put("_id", id);
				acnt.add(dbo);
			}
		}catch(Exception e){}
		finally{
			dbc.close();
		}
		return acnt;
	}
	
	@Override
	public List<DBObject> findByCondition(DBObject condition, int pageStart,
			int pageSize) {
		List<DBObject> acnt = new ArrayList<DBObject>();
		DBCollection clt=MongodbUtil.getDBCollection(DOC_NAME);
		DBCursor dbc = clt.find(condition).skip(pageStart).limit(pageSize);
		try{
			while(dbc.hasNext()){
				DBObject dbo = dbc.next();
				String id = dbo.get("_id").toString();
				dbo.put("_id", id);
				acnt.add(dbo);
			}
		}catch(Exception e){}
		finally{
			dbc.close();
		}
		return acnt;
	}
}
