package com.cardinfolink.vtsh.dao;

import java.util.ArrayList;
import java.util.List;

import com.cardinfolink.vtsh.dbutil.MongodbUtil;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class TblParaCodeImpl implements TblParaCodeDao{

	@Override
	public List<DBObject> findAll() {
			List<DBObject> tblPara = new ArrayList<DBObject>();
			DBCollection clt=MongodbUtil.getDBCollection(DOC_NAME);
			DBCursor dbc = clt.find();
			try{
				while(dbc.hasNext()){
					DBObject dbo = dbc.next();
					String id = dbo.get("_id").toString();
					dbo.put("_id", id);
					tblPara.add(dbo);
				}
			}catch(Exception e){}
			finally{
				dbc.close();
			}
			return tblPara;
		}
}
