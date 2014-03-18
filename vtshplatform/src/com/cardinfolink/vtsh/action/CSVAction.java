package com.cardinfolink.vtsh.action;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.cardinfolink.vtsh.csv.CSVExportUtil;
import com.cardinfolink.vtsh.dao.ClearDao;
import com.cardinfolink.vtsh.dao.ClearImpl;
import com.cardinfolink.vtsh.util.DateUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class CSVAction extends BaseAction{
	
	public String execute(){
		ClearDao cd = new ClearImpl();
		Map<String,Object> session = this.getSession();
		String clientid = (String) session.get("clientid");
		BasicDBObject con = new BasicDBObject();
		//con.put("clientid", clientid);
		String date = DateUtil.formatCurrentDate();
		//con.put("txnSetDate", date);
		List<DBObject> list = null;
		
		try {
			list = cd.findByCondition(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request =  ServletActionContext.getRequest();
		new CSVExportUtil().exportCvsFile_Txn(request,response,list);
		
		return null;
	}
	
	
}
