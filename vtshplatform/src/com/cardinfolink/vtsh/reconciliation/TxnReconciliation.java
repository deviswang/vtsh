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
		//��ѯ�����̻�id
		String clientid = merchant.get("clientid").toString();
		//��������
		String date = DateUtil.formatCurrentDate();
		//����̻��Ľ��ױ�����Ϣ
		String[] currs = merchant.get("currency").toString().split(",");
		//����̻���Ϣ�Ĳ�ѯ����
		//con.put("clientid",clientid);
		for(int i=0;i<currs.length;i++){
			if(i>=1){
				con.clear();
			}
			con.put("txnSetDate", date);//����
			con.put("clientid",clientid);//�̻�id
			String cur = currs[i];
			con.put("txnCur", cur);//���ױ���
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
			//������������:
			String txnSetDate = DateUtil.formatCurrentDate();
			reconTxn.put("txnSetDate", txnSetDate);//��������
			
			reconTxn.put("clientid",clientid);//�̻�id
			reconTxn.put("merName", merName);//�̻�����
			//double txnRate = 
			String txnReconCur = "";
			double txnReconCount = 0;//���ױ���
			double txnReconAmount = 0;//���׽��
			double txnReconFee = 0;//����������
			String txnReconSet = "";//�������
			double txnReconSetAmount = 0;//������
			double txnReconPaychAmount = 0;//����������
			
			double todayPendingAmount = 0;//���չ���
			double historyPendingAmount = 0;//��ʷ����
			double totalPendingAmount = 0;//�ܹ��ˡ�
			
			//������ѯ���ļ��ϣ�����ͳ��.....
			for(DBObject dbo : list){
				System.out.println("��ʼͳ�Ƹ��ֽ��....."+dbo);
				
				txnReconCur = dbo.get("txnCur").toString();
				
				txnReconCount += Double.parseDouble(dbo.get("txnCount").toString());
				
				txnReconAmount += Double.parseDouble(dbo.get("txnCurAmount").toString());
				
				txnReconFee += Double.parseDouble(dbo.get("txnFee").toString());
				
				txnReconSet = dbo.get("txnSet").toString();
				
				txnReconSetAmount += Double.parseDouble(dbo.get("txnSetAmount").toString());
				
				txnReconPaychAmount  += Double.parseDouble(dbo.get("txnPaychAmount").toString());
				
			}
				todayPendingAmount = txnReconSetAmount;//���չ���
				
				reconTxn.put("reconCount", ClearUtil.getFormatAmount(txnReconCount));//���ױ���
				reconTxn.put("txnReconCur", txnReconCur);
				reconTxn.put("reconAmount", ClearUtil.getFormatAmount(txnReconAmount));//���׽��
				reconTxn.put("reconFee", ClearUtil.getFormatAmount(txnReconFee));//����������
				reconTxn.put("reconSet", txnReconSet);//�����������
				reconTxn.put("reconSetAmount", ClearUtil.getFormatAmount(txnReconSetAmount));//������
				reconTxn.put("reconPaychAmount", ClearUtil.getFormatAmount(txnReconPaychAmount));//����������
				
				//��ѯ��ʷ���� : 
				//��ѯ���� �� 
				String yesterday = DateUtil.formatCurrentDate(DateUtil.addDay(new Date(),-1));
				BasicDBObject con2 = new BasicDBObject();
				con2.put("clientid", clientid);
				con2.put("txnSetDate", yesterday);
				con2.put("reconSet", txnReconSet);
				try {
					//��ѯ���յ���ʷ����
					List<DBObject> credits = rd.findByCondition(con2);
					if(credits!=null && credits.size()!=0 ){
						System.out.println("�鵽���׼�¼���ǣ� "+credits.size());
						DBObject reconSingle = credits.get(0);
						System.out.println("\n\r�鵽����ʷ����...."+reconSingle);
						historyPendingAmount = Double.parseDouble(reconSingle.get("totalPendingAmount").toString());
					}
				} catch (Exception e1) {
					e1.printStackTrace();         
				}
				
				String threRule = merchant.get("threshold").toString();
				String holdMon = getThreshold(threRule,txnReconSet);
				
				double tranThreshold = Double.parseDouble(holdMon);//�����޶�
				double transAmount = 0;//Ӧ�������
				
				//�����ܹ���:
				totalPendingAmount = (todayPendingAmount + historyPendingAmount) >= tranThreshold ? 
										0 : (todayPendingAmount + historyPendingAmount);
				//Ӧ������
				transAmount = (todayPendingAmount + historyPendingAmount) >= tranThreshold ? 
										(todayPendingAmount + historyPendingAmount) :	0 ;
										
				reconTxn.put("todayPendingAmount", ClearUtil.getFormatAmount(todayPendingAmount ));	//���չ���
				reconTxn.put("historyPendingAmount",ClearUtil.getFormatAmount( historyPendingAmount ));//��ʷ����
				reconTxn.put("totalPendingAmount", ClearUtil.getFormatAmount(totalPendingAmount));//�ܹ���
				reconTxn.put("transAmount", ClearUtil.getFormatAmount(transAmount));//Ӧ������
				reconTxn.put("tranThreshold", ClearUtil.getFormatAmount(tranThreshold));//�����޶�
				
				//������Ϣ
				DBObject bank = (DBObject) merchant.get("bank");
				
				String reconBenBank = bank.get("bkName1").toString();
				String reconSwiftCode = bank.get("swiftCode1").toString();
				String reconBankAddr = bank.get("addr1").toString();
				String reconBenName = bank.get("acntName1").toString();
				String reconAccountNo = bank.get("acntNum1").toString();
				
				//�����տ���������Ϣ:
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
				System.out.println("ok,,,���˼�¼����ˡ�������������");
				System.out.println(reconTxn);
		}
	}
	
	/**
	 * �����������̻�������Ϣͳ��
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
	 * ��ȡ�̻��������޶�
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

