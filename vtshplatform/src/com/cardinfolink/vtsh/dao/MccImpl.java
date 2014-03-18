package com.cardinfolink.vtsh.dao;

import java.util.ArrayList;
import java.util.List;

import com.cardinfolink.vtsh.dbutil.MongodbUtil;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class MccImpl implements MccDao{

	@Override
	public List<DBObject> findAll() {
		List<DBObject> mcc = new ArrayList<DBObject>();
		DBCollection clt=MongodbUtil.getDBCollection(DOC_NAME);
		DBCursor dbc = clt.find();
		try{
			while(dbc.hasNext()){
				DBObject dbo = dbc.next();
				String id = dbo.get("_id").toString();
				dbo.put("_id", id);
				mcc.add(dbo);
			}
		}catch(Exception e){}
		finally{
			dbc.close();
		}
		return mcc;
	}

}
