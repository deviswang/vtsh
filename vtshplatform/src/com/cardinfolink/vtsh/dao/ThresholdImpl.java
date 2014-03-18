package com.cardinfolink.vtsh.dao;

import java.util.ArrayList;
import java.util.List;

import com.cardinfolink.vtsh.dbutil.MongodbUtil;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class ThresholdImpl implements ThresholdDao{
	@Override
	public List<DBObject> findByCondition(DBObject con) {
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
	
}
