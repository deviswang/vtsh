package com.cardinfolink.vtsh.springdao;

import com.cardinfolink.vtsh.pojo.VtMid;

public interface VtMidDao {
	
   VtMid findByCondition(String key,String value) throws DaoException;
   
   void updateVtMid(String mcc,String mid) throws DaoException;
}
