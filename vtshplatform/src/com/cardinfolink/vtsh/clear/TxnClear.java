package com.cardinfolink.vtsh.clear;

import java.util.List;

import com.cardinfolink.vtsh.dao.ClearDao;
import com.cardinfolink.vtsh.dao.ClearImpl;
import com.cardinfolink.vtsh.dao.MerchantDao;
import com.cardinfolink.vtsh.dao.MerchantImpl;
import com.cardinfolink.vtsh.dao.TransactionDao;
import com.cardinfolink.vtsh.dao.TransactionImpl;
import com.cardinfolink.vtsh.util.ClearUtil;
import com.cardinfolink.vtsh.util.DateUtil;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class TxnClear {
	/**
	 * 交易类型
	 */
	public static final String TYPE1 = "PURC";
	public static final String TYPE2 = "REFUND";
	public static final String TYPE3 = "VOID";
	public static final String TYPE4 = "REFD";//refund的另外一种写法
	
	public static final String[] tranType = {TYPE1,TYPE2,TYPE3,TYPE4};
	
	/**
	 * 做一个商户号当日的清算。
	 * @param clientid
	 */
	public static void SignleMerchantTxnClear(String clientid){
		TransactionDao td = new TransactionImpl();
		//查询条件类
		BasicDBObject con = new BasicDBObject();
		//拿到一条用户信息:
		MerchantDao mt = new MerchantImpl();
		DBObject m=mt.findByClientid(clientid);
		//获得商户的交易币种信息
		String[] currency = m.get("currency").toString().split(",");
		//String[] payChannel = m.get("paychannel").toString().split(",");
		//交易的三种类型
		
		
		int num=0;
		
		// c 遍历添加交易类型条件
		for(int i=0;i<tranType.length;i++){
			if(i>=1){
				con.clear();//清除已有的查询条件。
				//con.removeField("gw_tran_type");
				}
			// a 商户ID查询条件
			con.put("merchant.clientid", m.get("clientid"));
			// b 清算条件(清算日期)条件;
			con.put("gw_recon_flag", "Y");
			String tran = tranType[i];
			con.put("gw_tran_type", tran);
			String key2 = "";
			
			if(tran.equals(TYPE1)){//交易类型为purc时查询字段为gw_currency
				key2="gw_currency";
			}else{//其他交易类型查询字段为gw_settleCurrency
				key2="gw_settleCurrency";
			}
			// d 遍历添加交易币种条件
			for(int j=0;j<currency.length;j++){
				String cur = currency[j];
				
				con.put(key2, cur);
				
				List<DBObject> list = td.findByCondition(con);
				
				//进行清算统计并保存信息。
				saveClearInfo(td,list,tran,cur,clientid);
				
				System.out.println(++num+" "+j +" : "+ con);
		}
	}
	}
	/**
	 * 做一个商户号当日的清算。
	 * @param merchant
	 */
	public static void SignleMerchantTxnClear(DBObject merchant){
		TransactionDao td = new TransactionImpl();
		//查询条件类
		BasicDBObject con = new BasicDBObject();
		//拿到一条用户信息:
		//MerchantDao mt = new MerchantImpl();
		//DBObject m=mt.findByClientid(clientid);
		//获得用户ID
		String clientid = (String) merchant.get("clientid");
		
		//获得商户的交易币种信息
		String[] currency = merchant.get("currency").toString().split(",");
		//String[] payChannel = m.get("paychannel").toString().split(",");
		int num=0;
		// c 遍历添加交易类型条件
		for(int i=0;i<tranType.length;i++){
			if(i>=1){
				con.clear();//清除已有的查询条件。
				//con.removeField("gw_tran_type");
				}
			// a 商户ID查询条件
			con.put("merchant.clientid",clientid);
			// b 清算条件(清算日期)条件;
			con.put("gw_recon_flag", "Y");
			String tran = tranType[i];
			con.put("gw_tran_type", tran);
			String key2 = "";
			
			if(tran.equals(TYPE1)){//交易类型为purc时查询字段为gw_currency
				key2="gw_currency";
			}else{//其他交易类型查询字段为gw_settleCurrency
				key2="gw_settleCurrency";
			}
			// d 遍历添加交易币种条件
			for(int j=0;j<currency.length;j++){
//				if(j>=1){
//					//con.remove(key2);
//					con.removeField(key2);
//				}
				String cur = currency[j];
				
				con.put(key2, cur);
				
				List<DBObject> list = td.findByCondition(con);
				//进行清算统计并保存信息。
				saveClearInfo(td,list,tran,cur,clientid);
				
				System.out.println(++num+" "+j +" : "+ con);
				
		}
	  }
	}
	/**
	 * 保存统计后的信息到数据库中
	 * @param td
	 * @param list
	 * @param tran
	 * @param cur
	 * @param clientid
	 */
	private static void saveClearInfo(TransactionDao td,List<DBObject> list,String tran,String cur,String clientid){
		
		ClearDao cd = new ClearImpl();
		if(list.size()!=0){
			BasicDBObject clearTxn = new BasicDBObject();
			//定义清算日期:
			String txnSetDate = DateUtil.formatCurrentDate();
			clearTxn.put("txnSetDate", txnSetDate);//清算日期
			clearTxn.put("clientid", clientid);//商户id
			clearTxn.put("txnCount", list.size());//交易笔数
			clearTxn.put("txnType", tran);//交易类型
			
			double txnAmount = 0;//交易金额
			double txnFee = 0;//交易手续费
			String txnSet = "";//清算币种
			double txnSetAmount = 0;//清算金额
			double txnPaychAmount = 0;//渠道送入金额
			//double txnGwSetAmount = 0;//Amount before MDR
			//遍历查询到的集合，进行统计
			for(DBObject ll : list){
				//
				DBObject dbo = (DBObject) ll.get("m_request");
				System.out.println(dbo);
				if(tran.equals(TYPE2) || tran.equals(TYPE4) ){//把退款金额变成负数，以便对账信息时
					txnAmount += -Double.parseDouble(dbo.get("returnamt").toString());
				}else if(tran.equals(TYPE1) ){//purchase类型
					txnAmount += Double.parseDouble(ll.get("gw_amount").toString());
				}
				
				txnSet = ll.get("gw_settleCurrency").toString();
				
				if(tran.equals(TYPE1) || tran.equals(TYPE2) ){
					BasicDBList fes = (BasicDBList) ll.get("settlefee");
 					txnFee += Double.parseDouble(fes.get(0).toString());
 					
 					BasicDBList feeAmt = (BasicDBList) ll.get("settlefeeAmount");
 					txnSetAmount += Double.parseDouble(feeAmt.get(0).toString());
 					txnPaychAmount += Double.parseDouble(ll.get("g_settlefeeAmount").toString());
				}
				//修改清算状态Y为S
				td.updateTxnStatus(ll.get("gw_g_trans_id").toString(), "S");
				System.out.println("这是我的查询交易统计: "+ll);
			}
				//收益
				double income = txnPaychAmount - txnSetAmount;
				clearTxn.put("txnCur", cur);//交易币种
				clearTxn.put("txnCurAmount", ClearUtil.getFormatAmount(txnAmount));//交易金额
				clearTxn.put("txnSet", txnSet);//清算币种
				clearTxn.put("txnSetAmount", ClearUtil.getFormatAmount(txnSetAmount));//清算金额
				clearTxn.put("txnPaychAmount", ClearUtil.getFormatAmount(txnPaychAmount));//渠道送入金额
				clearTxn.put("txnFee", ClearUtil.getFormatAmount(txnFee));//手续费
				clearTxn.put("txnIncome", ClearUtil.getFormatAmount(income));//收益1
				
			try {
				cd.save(clearTxn);
			} catch (Exception e) {
				System.out.println("保存清算信息错误...");
				e.printStackTrace();
		  }
		}
	}
	
	/**
	 * 处理批量的商户交易信息统计
	 * @param mernchants
	 */
	public static void MerchantTxnClear(List<DBObject> merchants){
		for(DBObject merchant : merchants){
			String clientid = merchant.get("clientid").toString();
			System.out.println(clientid);
			SignleMerchantTxnClear(merchant);
		}
	}
}
