package com.cardinfolink.vtsh.dao;

import java.util.List;

import com.cardinfolink.vtsh.dbutil.TableNameUtil;
import com.mongodb.DBObject;


public interface MerchantDao {
	/**
	 * The name of document
	 */
	public static final String DOC_NAME=TableNameUtil.getTableName("merchant");
	/**
	 * Find all 
	 */
	List<DBObject> findAll();
	/**
	 * Find all with pagination
	 */
	List<DBObject> findAll(int pageStart,int pageSize);
	List<DBObject> findByCondition(DBObject con,int pageStart,int pageSize);
	
	/**
	 * 
	 */
	boolean insert(DBObject obj);
	
	/**
	 * update the merchant information
	 * @param obj
	 * @return
	 */
	boolean updateMerchant(DBObject obj);
	
	/**delete a merchant*/
	boolean deleteMerchant(DBObject obj);
	/**
	 * get total page
	 */
	long getTotalPage();
	
	DBObject findByClientid(String id);
	
	DBObject findByPrinEmail(String name);
	
	boolean updateMerchant(DBObject obj,String clientid);
	
	
}
