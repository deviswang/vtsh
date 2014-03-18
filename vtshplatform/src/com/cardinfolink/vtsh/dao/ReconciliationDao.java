package com.cardinfolink.vtsh.dao;

import java.util.List;

import com.cardinfolink.vtsh.dbutil.TableNameUtil;
import com.mongodb.DBObject;

public interface ReconciliationDao {
	
	public static final String DOC_NAME=TableNameUtil.getTableName("recon");
	/**
	 * 
	 * @param obj
	 * @return 
	 * @throws Exception
	 */
	boolean save(DBObject obj) throws Exception;
	
	List<DBObject> findByCondition(DBObject con) throws Exception;
	
	List<DBObject> findByCondition(DBObject con, int pageStart, int pageSize) throws Exception;
	
	List<DBObject> findByAll() throws Exception;
	List<DBObject> findByAll(int pageStart, int pageSize) throws Exception;
	
}
