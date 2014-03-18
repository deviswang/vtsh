package com.cardinfolink.vtsh.dao;

import java.util.List;

import com.cardinfolink.vtsh.dbutil.TableNameUtil;
import com.mongodb.DBObject;

public interface MccDao {
	/**
	 *The name of document
	 */
	public static final String DOC_NAME=TableNameUtil.getTableName("mcc");
	/**
	 *Find all
	 */
	List<DBObject> findAll();
}
