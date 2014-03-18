package com.cardinfolink.vtsh.dao;

import com.cardinfolink.vtsh.dbutil.TableNameUtil;
import com.cardinfolink.vtsh.springdao.DaoException;
import com.mongodb.DBObject;


public interface VtChannelDao {
	
	public static final String DOC_NAME=TableNameUtil.getTableName("channel");
	
	DBObject findByCondition(DBObject con) throws DaoException;
	
}
