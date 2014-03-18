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
	 * ��������
	 */
	public static final String TYPE1 = "PURC";
	public static final String TYPE2 = "REFUND";
	public static final String TYPE3 = "VOID";
	public static final String TYPE4 = "REFD";//refund������һ��д��
	
	public static final String[] tranType = {TYPE1,TYPE2,TYPE3,TYPE4};
	
	/**
	 * ��һ���̻��ŵ��յ����㡣
	 * @param clientid
	 */
	public static void SignleMerchantTxnClear(String clientid){
		TransactionDao td = new TransactionImpl();
		//��ѯ������
		BasicDBObject con = new BasicDBObject();
		//�õ�һ���û���Ϣ:
		MerchantDao mt = new MerchantImpl();
		DBObject m=mt.findByClientid(clientid);
		//����̻��Ľ��ױ�����Ϣ
		String[] currency = m.get("currency").toString().split(",");
		//String[] payChannel = m.get("paychannel").toString().split(",");
		//���׵���������
		
		
		int num=0;
		
		// c ������ӽ�����������
		for(int i=0;i<tranType.length;i++){
			if(i>=1){
				con.clear();//������еĲ�ѯ������
				//con.removeField("gw_tran_type");
				}
			// a �̻�ID��ѯ����
			con.put("merchant.clientid", m.get("clientid"));
			// b ��������(��������)����;
			con.put("gw_recon_flag", "Y");
			String tran = tranType[i];
			con.put("gw_tran_type", tran);
			String key2 = "";
			
			if(tran.equals(TYPE1)){//��������Ϊpurcʱ��ѯ�ֶ�Ϊgw_currency
				key2="gw_currency";
			}else{//�����������Ͳ�ѯ�ֶ�Ϊgw_settleCurrency
				key2="gw_settleCurrency";
			}
			// d ������ӽ��ױ�������
			for(int j=0;j<currency.length;j++){
				String cur = currency[j];
				
				con.put(key2, cur);
				
				List<DBObject> list = td.findByCondition(con);
				
				//��������ͳ�Ʋ�������Ϣ��
				saveClearInfo(td,list,tran,cur,clientid);
				
				System.out.println(++num+" "+j +" : "+ con);
		}
	}
	}
	/**
	 * ��һ���̻��ŵ��յ����㡣
	 * @param merchant
	 */
	public static void SignleMerchantTxnClear(DBObject merchant){
		TransactionDao td = new TransactionImpl();
		//��ѯ������
		BasicDBObject con = new BasicDBObject();
		//�õ�һ���û���Ϣ:
		//MerchantDao mt = new MerchantImpl();
		//DBObject m=mt.findByClientid(clientid);
		//����û�ID
		String clientid = (String) merchant.get("clientid");
		
		//����̻��Ľ��ױ�����Ϣ
		String[] currency = merchant.get("currency").toString().split(",");
		//String[] payChannel = m.get("paychannel").toString().split(",");
		int num=0;
		// c ������ӽ�����������
		for(int i=0;i<tranType.length;i++){
			if(i>=1){
				con.clear();//������еĲ�ѯ������
				//con.removeField("gw_tran_type");
				}
			// a �̻�ID��ѯ����
			con.put("merchant.clientid",clientid);
			// b ��������(��������)����;
			con.put("gw_recon_flag", "Y");
			String tran = tranType[i];
			con.put("gw_tran_type", tran);
			String key2 = "";
			
			if(tran.equals(TYPE1)){//��������Ϊpurcʱ��ѯ�ֶ�Ϊgw_currency
				key2="gw_currency";
			}else{//�����������Ͳ�ѯ�ֶ�Ϊgw_settleCurrency
				key2="gw_settleCurrency";
			}
			// d ������ӽ��ױ�������
			for(int j=0;j<currency.length;j++){
//				if(j>=1){
//					//con.remove(key2);
//					con.removeField(key2);
//				}
				String cur = currency[j];
				
				con.put(key2, cur);
				
				List<DBObject> list = td.findByCondition(con);
				//��������ͳ�Ʋ�������Ϣ��
				saveClearInfo(td,list,tran,cur,clientid);
				
				System.out.println(++num+" "+j +" : "+ con);
				
		}
	  }
	}
	/**
	 * ����ͳ�ƺ����Ϣ�����ݿ���
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
			//������������:
			String txnSetDate = DateUtil.formatCurrentDate();
			clearTxn.put("txnSetDate", txnSetDate);//��������
			clearTxn.put("clientid", clientid);//�̻�id
			clearTxn.put("txnCount", list.size());//���ױ���
			clearTxn.put("txnType", tran);//��������
			
			double txnAmount = 0;//���׽��
			double txnFee = 0;//����������
			String txnSet = "";//�������
			double txnSetAmount = 0;//������
			double txnPaychAmount = 0;//����������
			//double txnGwSetAmount = 0;//Amount before MDR
			//������ѯ���ļ��ϣ�����ͳ��
			for(DBObject ll : list){
				//
				DBObject dbo = (DBObject) ll.get("m_request");
				System.out.println(dbo);
				if(tran.equals(TYPE2) || tran.equals(TYPE4) ){//���˿����ɸ������Ա������Ϣʱ
					txnAmount += -Double.parseDouble(dbo.get("returnamt").toString());
				}else if(tran.equals(TYPE1) ){//purchase����
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
				//�޸�����״̬YΪS
				td.updateTxnStatus(ll.get("gw_g_trans_id").toString(), "S");
				System.out.println("�����ҵĲ�ѯ����ͳ��: "+ll);
			}
				//����
				double income = txnPaychAmount - txnSetAmount;
				clearTxn.put("txnCur", cur);//���ױ���
				clearTxn.put("txnCurAmount", ClearUtil.getFormatAmount(txnAmount));//���׽��
				clearTxn.put("txnSet", txnSet);//�������
				clearTxn.put("txnSetAmount", ClearUtil.getFormatAmount(txnSetAmount));//������
				clearTxn.put("txnPaychAmount", ClearUtil.getFormatAmount(txnPaychAmount));//����������
				clearTxn.put("txnFee", ClearUtil.getFormatAmount(txnFee));//������
				clearTxn.put("txnIncome", ClearUtil.getFormatAmount(income));//����1
				
			try {
				cd.save(clearTxn);
			} catch (Exception e) {
				System.out.println("����������Ϣ����...");
				e.printStackTrace();
		  }
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
			SignleMerchantTxnClear(merchant);
		}
	}
}
