package com.cardinfolink.vtsh.dao;

import java.util.List;

import com.cardinfolink.vtsh.dbutil.TableNameUtil;
import com.mongodb.DBObject;

public interface ThresholdDao {
	public static final String DOC_NAME=TableNameUtil.getTableName("threshold");
	/**
	 *save the clearing info 
	 */
	List<DBObject> findByCondition(DBObject con);
	
}
