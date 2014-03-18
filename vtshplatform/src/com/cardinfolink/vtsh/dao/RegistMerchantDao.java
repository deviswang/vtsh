package com.cardinfolink.vtsh.dao;

import java.util.List;

import com.cardinfolink.vtsh.dbutil.TableNameUtil;
import com.mongodb.DBObject;

public interface RegistMerchantDao {
	
	public final static String DOC_NAME=TableNameUtil.getTableName("registMerchants");
	
	public List<DBObject> findAll();
	
	/**
	 * get result by condition
	 */
	List<DBObject> findByCondition(DBObject condition);
	
	/**
	 * get result by condition
	 */
	List<DBObject> findByCondition(DBObject condition,int pageStart,int pageSize);
	
	
}
