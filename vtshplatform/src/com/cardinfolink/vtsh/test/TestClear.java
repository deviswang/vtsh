package com.cardinfolink.vtsh.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.junit.Test;

import com.cardinfolink.vtsh.action.BaseAction;
import com.cardinfolink.vtsh.clear.TxnClear;
import com.cardinfolink.vtsh.csv.CSVExportUtil;
import com.cardinfolink.vtsh.dao.ClearDao;
import com.cardinfolink.vtsh.dao.ClearImpl;
import com.cardinfolink.vtsh.dao.MerchantDao;
import com.cardinfolink.vtsh.dao.MerchantImpl;
import com.cardinfolink.vtsh.dao.TransactionDao;
import com.cardinfolink.vtsh.dao.TransactionImpl;
import com.cardinfolink.vtsh.util.ClearUtil;
import com.cardinfolink.vtsh.util.DateUtil;
import com.cardinfolink.vtsh.util.JSONObjectUtil;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class TestClear extends BaseAction{
	@Test
	public void test1(){
		TransactionDao td = new TransactionImpl();
		
		Map<String,Object> session = this.getSession();
	
		//BasicDBObject reFlag = new BasicDBObject("gw_recon_flag","Y");
		//BasicDBObject txnType = new BasicDBObject("gw_currency","CNY");
		//(String) session.get("clientid");
		String clientid= "000000000000015";       
		BasicDBObject con = new BasicDBObject();
		con.put("merchant.clientid", clientid);
		//con.put("gw_recon_flag","Y");
		con.put("gw_currency","USD");
		//con.put("gw_settleCurrency", "CNY");
		con.put("gw_tran_type", "purc");
		List<DBObject> list = td.findByCondition(con);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String strDate = sdf.format(date);
		System.out.println();
		double sum=0;
								
		int tranCount = list.size();
		for(DBObject ll : list){
			System.out.println(strDate+" : "+((DBObject)ll.get("merchant")).get("clientid")
					+" : "+((DBObject)ll.get("m_request")).get("txntype")+" : "+" : ");
			sum+=Double.parseDouble(ll.get("gw_settleAmount").toString());
			System.out.println(sum);
		}
		System.out.println(sum);
		System.out.println(tranCount);
	}
	
	@Test
	public void test2(){
		System.out.println(1+Double.parseDouble("-0.9"));
		
	}
	
	@Test
	public void test3(){
		TransactionDao td = new TransactionImpl();
		BasicDBObject con = new BasicDBObject();
		
		//BasicDBObject con = new BasicDBObject();
		//con.put("merchant.clientid", clientid);
		//con.put("gw_recon_flag","Y");
		//con.put("gw_currency","USD");
		//con.put("gw_settleCurrency", "CNY");
		//con.put("gw_tran_type", "purc");
		
		//拿到一条用户信息:
		MerchantDao mt = new MerchantImpl();
		DBObject m=mt.findByClientid("000000000000015");
		String[] currency = m.get("currency").toString().split(",");
		String[] payChannel = m.get("paychannel").toString().split(",");
		String[] tranType = {"purc","refund","void"};
		
		int num=0;
		
		con.put("merchant.clientid", m.get("clientid"));
		for(int i=0;i<currency.length;i++){
			if(i>=1)con.remove("gw_currency");
			String cur = currency[i];
			con.put("gw_currency",cur);
		//	for(int j=0;j<payChannel.length;j++){
			//	String pay = payChannel[j];
			//	con.put("", pay);
				for(int k=0;k<tranType.length;k++){
					if(k>=1)con.remove("gw_tran_type");
					String tran = tranType[k];
					con.put("gw_tran_type", tran);
					List<DBObject> list = td.findByCondition(con);
					System.out.println(++num +" : "+ con);
					
					if(list.size()!=0){
						for(DBObject ll : list){
							System.out.println("这是我的查询交易统计: "+ll);
						}
					}
				//}
			}
		}
		
		
		
//		List<DBObject> list = td.findByCondition(con);
//		System.out.println(list.size());
//		if(list.size()!=0){
//			for(DBObject ll : list){
//				System.out.println("这是我的查询交易统计: "+ll);
//			}
//		}
		System.out.println(""+currency[0]+payChannel[1]+tranType[0]);
		System.out.println(0x208);
	}
	
	@Test
	public void test4(){
		
		TransactionDao td = new TransactionImpl();
		BasicDBObject con = new BasicDBObject();
		
		ClearDao cd = new ClearImpl();
		//拿到一条用户信息:
		MerchantDao mt = new MerchantImpl();
		DBObject m=mt.findByClientid("000000000000015");
		String[] currency = m.get("currency").toString().split(",");
		//String[] payChannel = m.get("paychannel").toString().split(",");
		String[] tranType = {"purc","refund","void"};
		
		int num=0;
		//定义清算日期:
		String txnSetDate = DateUtil.formatCurrentDate();
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
			if(tran.equals("purc")){//交易类型为purc时查询字段为gw_currency
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
				System.out.println(++num+" "+j +" : "+ con);
				
				if(list.size()!=0){
					BasicDBObject clearTxn = new BasicDBObject();
					
					clearTxn.put("txnSetDate", txnSetDate);
					clearTxn.put("clientid","000000000000015");
					clearTxn.put("txnCount",list.size());
					
					clearTxn.put("txnType", tran);
					double txnAmount = 0;//交易金额
					double txnFee = 0;//交易手续费
					String txnSet = "";//清算币种
					double txnSetAmount = 0;//清算金额
					double txnPaychAmount = 0;//渠道送入金额
					//遍历查询到的集合，进行统计
					for(DBObject ll : list){
						//
						DBObject dbo = (DBObject) ll.get("m_request");
						if(tran.equals("refund")){
							txnAmount += Double.parseDouble(dbo.get("returnamt").toString());
						}else{
							txnAmount += Double.parseDouble(dbo.get("txnamount").toString());
						}
						
						txnSet = ll.get("gw_settleCurrency").toString();
						
						if(tran.equals("purc") || tran.equals("refund")){
							
							//String[] fees = (String[]) ll.get("settlefee");
							BasicDBList fes = (BasicDBList) ll.get("settlefee");
		 					txnFee += Double.parseDouble(fes.get(0).toString());
		 					//System.out.println("laiba:::  "+fes.get(0));
		 					BasicDBList feeAmt = (BasicDBList) ll.get("settlefeeAmount");
		 					//String[] feesAmount = (String[]) ll.get("settlefeeAmount");
		 					txnSetAmount += Double.parseDouble(feeAmt.get(0).toString());
		 					txnPaychAmount += Double.parseDouble(ll.get("g_settlefeeAmount").toString());
						}
					
						//修改清算状态Y为S
						//td.updateTxnStatus(ll.get("gw_g_trans_id").toString(), "S");
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
						e.printStackTrace();
				  }
				}
		}
	}
  }
	
	@Test
	public void test5(){
		TransactionDao td = new TransactionImpl();
		td.updateTxnStatus("HXVX999llYYXa3Cl", "S");
	}
	
	@Test
	public void test6(){
		
		TxnClear.SignleMerchantTxnClear("000000000000015");
	}
	
	@Test
	public void test7(){
		List<DBObject> merchants = new MerchantImpl().findAll();
		TxnClear.MerchantTxnClear(merchants);
	}
	@Test
	public void test8(){
		int page=1;
		int total=0;
		int rows = 10;
		JSONObject clearInf=null;
		Map<String,Object> session = this.getSession();
		//获得登录用户的id
		String clientid ="000000000000015";
						
		//创建查询条件类
		BasicDBObject con = new BasicDBObject();
		con.put("clientid", clientid);
		String date = DateUtil.formatCurrentDate();
		con.put("txnSetDate", date);
		System.out.println(date);
		ClearDao cd = new ClearImpl();
		try {
			List<DBObject> list = cd.findByCondition(con);
			total = list.size();
			List<DBObject> list2 = cd.findByCondition(con, (page-1)*rows, rows);
			clearInf = JSONObjectUtil.getJsonResult(total,list2);
			System.out.println(list);
			System.out.println(" +++++ "+list2);
			System.out.println(clearInf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
	@Test
	public void test9(){
		
		MerchantDao md = new MerchantImpl();
		List<DBObject> list = md.findAll();
		TxnClear.MerchantTxnClear(list);
		
	}
	
	@Test
	public void test() throws Exception{
		
		ClearDao cd = new ClearImpl();
		String clientid ="000000000000015";
		BasicDBObject con = new BasicDBObject();
		con.put("clientid", clientid);
		List<DBObject> list = cd.findByCondition(con);
	
		//new CSVExportUtil().exportCvsFile_Txn(request, response,list);
		
		
	}
	
	
	public static void main(String[] args) {
		double a = ((double)Math.round(100.9898*100))/100;
		System.out.println(a);
		double d = 1.03;
		//double f = 
		double c = d-0.42;
		
		//格式化小数到保留两位小数
		System.out.println(String.format("%.2f", c));
		System.out.println(1.03-0.42);
		System.out.println(c);
		//System.out.println(String.format("%.2f"),c));
	}
	
}
