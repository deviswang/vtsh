package com.cardinfolink.vtsh.util;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;

import com.cardinfolink.vtsh.clear.TxnClear;
import com.cardinfolink.vtsh.dao.MerchantImpl;
import com.mongodb.DBObject;


public class AutoTask extends HttpServlet implements ServletContextListener{
	
   
	private static final long serialVersionUID = -8899284570468799073L;
	private java.util.Timer timer = null;
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		timer.cancel();
	}

	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				//获取所有的商户信息
				List<DBObject> merchants = new MerchantImpl().findAll();
				//执行商户信息统计。
				TxnClear.MerchantTxnClear(merchants);
			}
			 
		 };		 
		 	timer = new java.util.Timer(true);
	        Calendar calendar = Calendar.getInstance();
	        int year = calendar.get(Calendar.YEAR);
	        int month = calendar.get(Calendar.MONTH);
	        int day = calendar.get(Calendar.DAY_OF_MONTH);
	       
	        calendar.set(year, month, day, 8, 59, 00);
	        Date date = calendar.getTime();
	        if(date.before(new Date())){
		    	date=this.addDay(date, 1);
		    }
	        int period = 24*60*60* 1000;	       
	        timer.schedule(task, date,period);
	        
	}
	public Date addDay(Date date, int num) {
		  Calendar startDT = Calendar.getInstance();
		  startDT.setTime(date);
		  startDT.add(Calendar.DAY_OF_MONTH, num);
		  return startDT.getTime();
    }

}
