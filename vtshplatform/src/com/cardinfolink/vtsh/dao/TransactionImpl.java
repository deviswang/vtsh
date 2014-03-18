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

public class TransactionImpl implements TransactionDao {
	@Override
	public List<DBObject> findAll(int pageStart,int pageSize)  {
		List<DBObject> acnt = new ArrayList<DBObject>();
		DBCollection clt=MongodbUtil.getDBCollection(DOC_NAME);
		DBCursor dbc = clt.find().skip(pageStart).limit(pageSize);
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
	public boolean insert(DBObject obj) {
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

	@Override
	public boolean updateTxnStatus(String transId,String status) {
		boolean flag = false;
		DB db = MongodbUtil.getDB();
		db.requestStart();
		DBCollection clt=MongodbUtil.getDBCollection(DOC_NAME);
		WriteResult wrt = clt.update(new BasicDBObject("gw_g_trans_id",transId),
				new BasicDBObject("$set",new BasicDBObject("gw_recon_flag",status)));
		if(wrt.getLastError().ok()){
			flag = true;
		}
		db.requestDone();
		return flag;
	}

}
