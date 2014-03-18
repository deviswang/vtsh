package com.cardinfolink.vtsh.dao;

import java.util.List;

import com.cardinfolink.vtsh.dbutil.TableNameUtil;
import com.mongodb.DBObject;

public interface PaychannelContentDao {
	/**
	 * The name of document
	 */
	public static final String DOC_NAME=TableNameUtil.getTableName("paycont");
	/**
	 * Find all
	 */
	List<DBObject> findAll();	
}
