package com.cardinfolink.vtsh.reconciliation;

import java.util.Date;
import java.util.List;

import com.cardinfolink.vtsh.dao.ClearDao;
import com.cardinfolink.vtsh.dao.ClearImpl;
import com.cardinfolink.vtsh.dao.ReconciliationDao;
import com.cardinfolink.vtsh.dao.ReconciliationImpl;
import com.cardinfolink.vtsh.dao.ThresholdDao;
import com.cardinfolink.vtsh.dao.ThresholdImpl;
import com.cardinfolink.vtsh.util.ClearUtil;
import com.cardinfolink.vtsh.util.DateUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class TxnReconciliation {
	
	public static void  SignleTxnReconciliation(DBObject merchant) {
		ClearDao cd = new ClearImpl();
		BasicDBObject con = new BasicDBObject();
		//查询条件商户id
		String clientid = merchant.get("clientid").toString();
		//清算日期
		String date = DateUtil.formatCurrentDate();
		//获得商户的交易币种信息
		String[] currs = merchant.get("currency").toString().split(",");
		//添加商户信息的查询条件
		//con.put("clientid",clientid);
		for(int i=0;i<currs.length;i++){
			if(i>=1){
				con.clear();
			}
			con.put("txnSetDate", date);//日期
			con.put("clientid",clientid);//商户id
			String cur = currs[i];
			con.put("txnCur", cur);//交易币种
			try {
				List<DBObject> list = cd.findByCondition(con);
				saveReconciliationInfo(list,merchant);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void saveReconciliationInfo(List<DBObject> list,DBObject merchant){
		ReconciliationDao rd = new ReconciliationImpl();
		String clientid = merchant.get("clientid").toString();
		String merName = merchant.get("merName").toString();
		
		if(list.size()!=0){
			BasicDBObject reconTxn = new BasicDBObject();
			//定义清算日期:
			String txnSetDate = DateUtil.formatCurrentDate();
			reconTxn.put("txnSetDate", txnSetDate);//清算日期
			
			reconTxn.put("clientid",clientid);//商户id
			reconTxn.put("merName", merName);//商户名称
			//double txnRate = 
			String txnReconCur = "";
			double txnReconCount = 0;//交易笔数
			double txnReconAmount = 0;//交易金额
			double txnReconFee = 0;//交易手续费
			String txnReconSet = "";//清算币种
			double txnReconSetAmount = 0;//清算金额
			double txnReconPaychAmount = 0;//渠道送入金额
			
			double todayPendingAmount = 0;//今日挂账
			double historyPendingAmount = 0;//历史挂账
			double totalPendingAmount = 0;//总挂账。
			
			//遍历查询到的集合，进行统计.....
			for(DBObject dbo : list){
				System.out.println("开始统计各种金额....."+dbo);
				
				txnReconCur = dbo.get("txnCur").toString();
				
				txnReconCount += Double.parseDouble(dbo.get("txnCount").toString());
				
				txnReconAmount += Double.parseDouble(dbo.get("txnCurAmount").toString());
				
				txnReconFee += Double.parseDouble(dbo.get("txnFee").toString());
				
				txnReconSet = dbo.get("txnSet").toString();
				
				txnReconSetAmount += Double.parseDouble(dbo.get("txnSetAmount").toString());
				
				txnReconPaychAmount  += Double.parseDouble(dbo.get("txnPaychAmount").toString());
				
			}
				todayPendingAmount = txnReconSetAmount;//今日挂账
				
				reconTxn.put("reconCount", ClearUtil.getFormatAmount(txnReconCount));//交易笔数
				reconTxn.put("txnReconCur", txnReconCur);
				reconTxn.put("reconAmount", ClearUtil.getFormatAmount(txnReconAmount));//交易金额
				reconTxn.put("reconFee", ClearUtil.getFormatAmount(txnReconFee));//交易手续费
				reconTxn.put("reconSet", txnReconSet);//交易清算币种
				reconTxn.put("reconSetAmount", ClearUtil.getFormatAmount(txnReconSetAmount));//清算金额
				reconTxn.put("reconPaychAmount", ClearUtil.getFormatAmount(txnReconPaychAmount));//渠道送入金额
				
				//查询历史挂账 : 
				//查询条件 ： 
				String yesterday = DateUtil.formatCurrentDate(DateUtil.addDay(new Date(),-1));
				BasicDBObject con2 = new BasicDBObject();
				con2.put("clientid", clientid);
				con2.put("txnSetDate", yesterday);
				con2.put("reconSet", txnReconSet);
				try {
					//查询昨日的历史挂账
					List<DBObject> credits = rd.findByCondition(con2);
					if(credits!=null && credits.size()!=0 ){
						System.out.println("查到交易记录数是： "+credits.size());
						DBObject reconSingle = credits.get(0);
						System.out.println("\n\r查到了历史挂账...."+reconSingle);
						historyPendingAmount = Double.parseDouble(reconSingle.get("totalPendingAmount").toString());
					}
				} catch (Exception e1) {
					e1.printStackTrace();         
				}
				
				String threRule = merchant.get("threshold").toString();
				String holdMon = getThreshold(threRule,txnReconSet);
				
				double tranThreshold = Double.parseDouble(holdMon);//交易限额
				double transAmount = 0;//应划付金额
				
				//计算总挂账:
				totalPendingAmount = (todayPendingAmount + historyPendingAmount) >= tranThreshold ? 
										0 : (todayPendingAmount + historyPendingAmount);
				//应划款金额
				transAmount = (todayPendingAmount + historyPendingAmount) >= tranThreshold ? 
										(todayPendingAmount + historyPendingAmount) :	0 ;
										
				reconTxn.put("todayPendingAmount", ClearUtil.getFormatAmount(todayPendingAmount ));	//今日挂账
				reconTxn.put("historyPendingAmount",ClearUtil.getFormatAmount( historyPendingAmount ));//历史挂账
				reconTxn.put("totalPendingAmount", ClearUtil.getFormatAmount(totalPendingAmount));//总挂账
				reconTxn.put("transAmount", ClearUtil.getFormatAmount(transAmount));//应划款金额
				reconTxn.put("tranThreshold", ClearUtil.getFormatAmount(tranThreshold));//清算限额
				
				//银行信息
				DBObject bank = (DBObject) merchant.get("bank");
				
				String reconBenBank = bank.get("bkName1").toString();
				String reconSwiftCode = bank.get("swiftCode1").toString();
				String reconBankAddr = bank.get("addr1").toString();
				String reconBenName = bank.get("acntName1").toString();
				String reconAccountNo = bank.get("acntNum1").toString();
				
				//存入收款人银行信息:
				reconTxn.put("reconBenBank", reconBenBank);
				reconTxn.put("reconSwiftCode", reconSwiftCode);
				reconTxn.put("reconBankAddr", reconBankAddr);
				reconTxn.put("reconBenName", reconBenName);
				reconTxn.put("reconAccountNo", reconAccountNo);
				
				try {
					rd.save(reconTxn);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("ok,,,对账记录存好了。。。。。。。");
				System.out.println(reconTxn);
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
			SignleTxnReconciliation(merchant); 
		}
	}
	
	/***
	 * 获取商户的清算限额
	 */
	public static String getThreshold(String rule,String setCur){
		ThresholdDao tld = new ThresholdImpl();
		BasicDBObject con = new BasicDBObject();
		con.put("threshold_rule", rule);
		List<DBObject> holds = tld.findByCondition(con);
		String thresMoney ="";
		for(DBObject hold : holds){//holds.size = 1;
			System.out.println(hold);
			DBObject dds = (DBObject)hold.get("threshold_param");
			thresMoney = dds.get(setCur)+"";
		}
		return thresMoney;
	}
}

