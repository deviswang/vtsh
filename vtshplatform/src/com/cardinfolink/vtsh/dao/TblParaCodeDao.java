package com.cardinfolink.vtsh.dao;

import java.util.List;

import com.cardinfolink.vtsh.dbutil.TableNameUtil;
import com.mongodb.DBObject;

public interface TblParaCodeDao {
	/**
	 * The name of document
	 */
	public static final String DOC_NAME=TableNameUtil.getTableName("nation");
	/**
	 * Find all
	 */
	List<DBObject> findAll();
}
