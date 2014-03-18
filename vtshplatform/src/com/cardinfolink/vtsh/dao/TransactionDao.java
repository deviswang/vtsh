package com.cardinfolink.vtsh.dao;

import java.util.List;

import com.cardinfolink.vtsh.dbutil.TableNameUtil;
import com.mongodb.DBObject;

public interface TransactionDao {
	/**
	 * The name of document
	 */
	
	public static final String DOC_NAME=TableNameUtil.getTableName("txn");
	
	/**findAll*/
	List<DBObject> findAll(int pageStart,int pageSize) ;
	
	/**save a merchant*/
	boolean insert(DBObject obj);
	/**
	 * get total page
	 */
	long getTotalPage();
	/**
	 * get result by condition
	 */
	List<DBObject> findByCondition(DBObject condition);
	/**
	 * get result by condition
	 */
	List<DBObject> findByCondition(DBObject condition,int pageStart,int pageSize);
	
	/**
	 * update the transaction clearing status 
	 * @param status
	 * @return
	 */
	boolean updateTxnStatus(String clientid,String status);
	
}
