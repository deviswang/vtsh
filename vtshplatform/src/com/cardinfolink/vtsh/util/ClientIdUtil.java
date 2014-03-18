package com.cardinfolink.vtsh.util;

import com.cardinfolink.vtsh.springdao.DaoException;
import com.cardinfolink.vtsh.springdao.VtMidDao;

public class ClientIdUtil {
	
	private static String acqId="8000392";
	
	public static String getClientId(String mcc,String mid,VtMidDao vdao){
		//更新后把mid加1再保存。
		Integer mid2 = Integer.parseInt(mid)+1;
		try {
			vdao.updateVtMid(mcc, mid2.toString());
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return acqId+mcc+mid;
	}
}
