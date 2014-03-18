package com.cardinfolink.vtsh.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.cardinfolink.vtsh.dao.CurrencyImpl;
import com.cardinfolink.vtsh.dao.MccImpl;
import com.cardinfolink.vtsh.dao.PaychannelImpl;
import com.mongodb.DBObject;

public class JSONObjectUtil {
	
	public static JSONObject getJsonResult(long total,List<DBObject> list){
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("total", total);
		result.put("rows", list);
		JSONObject data = JSONObject.fromObject(result);
		return data;
	}
	
	public static JSONObject getData(String tbName){
		JSONObject obj = null;
		//MCC对应商品范围，CUR 对应交易币种
		if("MCC".equalsIgnoreCase(tbName)){
			DBObject dbj = new MccImpl().findAll().get(0);
			obj = JSONObject.fromObject(dbj);
		}
		if("CUR".equalsIgnoreCase(tbName)){
			DBObject dbj = new CurrencyImpl().findAll().get(0);
			obj = JSONObject.fromObject(dbj);
		}
		if("PAY".equalsIgnoreCase(tbName)){
			DBObject dbj = new PaychannelImpl().findAll().get(0);
			obj = JSONObject.fromObject(dbj);
		}
		return obj;
	}
}
