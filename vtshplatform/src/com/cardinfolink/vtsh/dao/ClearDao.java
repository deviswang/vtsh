package com.cardinfolink.vtsh.dao;

import java.util.List;

import com.cardinfolink.vtsh.dbutil.TableNameUtil;
import com.mongodb.DBObject;

public interface ClearDao {
	public static final String DOC_NAME=TableNameUtil.getTableName("clear");
	/**
	 *save the clearing info 
	 */
	boolean save(DBObject obj) throws Exception;
	
	List<DBObject> findByCondition(DBObject con) throws Exception;
	
	List<DBObject> findByCondition(DBObject con, int pageStart, int pageSize) throws Exception;
	
}
