package com.cardinfolink.vtsh.dao;

import java.util.ArrayList;
import java.util.List;

import com.cardinfolink.vtsh.dbutil.MongodbUtil;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class ReconciliationImpl implements ReconciliationDao{
	@Override
	public boolean save(DBObject obj) throws Exception {
		boolean flag = false;
		DB db = MongodbUtil.getDB();
		db.requestStart();
		DBCollection clt=MongodbUtil.getDBCollection(DOC_NAME);
		WriteResult wrt =  clt.insert(obj);
		if(wrt.getLastError().ok()){
			flag = true;
		}
		db.requestDone();
		return flag;
	}
	
	@Override
	public List<DBObject> findByCondition(DBObject con) throws Exception {
		List<DBObject> clearList = new ArrayList<DBObject>();
		DBCollection clt=MongodbUtil.getDBCollection(DOC_NAME);
		DBCursor dbc = clt.find(con);
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
	public List<DBObject> findByCondition(DBObject con, int pageStart, int pageSize){
		List<DBObject> clearList = new ArrayList<DBObject>();
		DBCollection clt=MongodbUtil.getDBCollection(DOC_NAME);
		DBCursor dbc = clt.find(con).skip(pageStart).limit(pageSize);
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
	public List<DBObject> findByAll() throws Exception {
		List<DBObject> clearList = new ArrayList<DBObject>();
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
	public List<DBObject> findByAll(int pageStart, int pageSize)
			throws Exception {
		List<DBObject> clearList = new ArrayList<DBObject>();
		//DB db = MongodbUtil.getDB();
		DBCollection clt=MongodbUtil.getDBCollection(DOC_NAME);
		DBCursor dbc = clt.find().skip(pageStart).limit(pageSize);
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
	
	
	
	
}
