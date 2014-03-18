package com.cardinfolink.vtsh.dao;

import java.util.ArrayList;
import java.util.List;

import com.cardinfolink.vtsh.dbutil.MongodbUtil;
import com.cardinfolink.vtsh.springdao.DaoException;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class VtChannelImpl implements VtChannelDao{
	
	@Override
	public DBObject findByCondition(DBObject con) throws DaoException {
			List<DBObject> clearList = new ArrayList<DBObject>();
			DBCollection clt=MongodbUtil.getDBCollection(DOC_NAME);
			DBCursor dbc = clt.find(con);
			try{
				while(dbc.hasNext()){
					DBObject dbo = dbc.next();
					String id = dbo.get("_id").toString();
					dbo.put("_id" , id);
					clearList.add(dbo);
				}
			}catch(Exception e){}
			finally{
				dbc.close();
			}
			if(clearList.size()!=0){
				return clearList.get(0);
			}else{
				return null;
			}
			
	}

}
