package com.cardinfolink.vtsh.csv;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cardinfolink.vtsh.dao.ClearDao;
import com.cardinfolink.vtsh.dao.ClearImpl;
import com.cardinfolink.vtsh.dao.ReconciliationDao;
import com.cardinfolink.vtsh.dao.ReconciliationImpl;
import com.mongodb.DBObject;

/**
 *
 * @author Brian
 */

public class CSVExportUtil {

private String sp=",";
private String cr="\r";
private String tp=" ";
private String cn="\n";
private String getCsvFileName(String FileName){
    SimpleDateFormat format =new SimpleDateFormat("yyyyMMdd");
    return FileName  + format.format(new Date()) + "00.csv";
}
private String getCsvFileName(String FileName,String settleDateLow){
    SimpleDateFormat format =new SimpleDateFormat("yyyyMMdd");
    if(null==settleDateLow || settleDateLow.equals("")){
    return FileName  + format.format(new Date()) + "00.csv";
    }else{
    return FileName  + settleDateLow + "00.csv";
    }
}
private String getCsvFileName1(String FileName,String settleDateLow){
    SimpleDateFormat format =new SimpleDateFormat("yyyyMMdd");
    if(null==settleDateLow || settleDateLow.equals("")){
    return FileName  + format.format(new Date()) + "01.csv";
    }else{
    return FileName  + settleDateLow + "01.csv";
    }
}
/**
 * 导出当前交易流水
 * @param request
 * @param response
 * @param getPutOutTaskResult
 * @return
 */
public int exportCvsFile_Txn(HttpServletRequest request,HttpServletResponse response,List<DBObject> getPutOutTaskResult) {
  String filenamedisplay = getCsvFileName("Trans");
  try {
   filenamedisplay = URLEncoder.encode(filenamedisplay, "UTF-8");
  } catch (UnsupportedEncodingException e1) {
   e1.printStackTrace();
  }
  response.reset();
  response.addHeader("Content-Disposition", "attachment;filename="  + filenamedisplay);
  OutputStream output = null;
  FileInputStream fis = null;
  //
  try {
   output = response.getOutputStream();
    //List<DBObject> resultIterator = cd.findByCondition(new BasicDBObject("clientid","0000000000000015"));
    String title="";
    title += "Settlement Date    ".trim() + sp;
    title += "Merchant ID        ".trim() + sp;
    title += "Transaction Type ".trim() + sp;
    title += "Transaction Count".trim() + sp;
    title += "Transaction Currency ".trim() + sp;
    title += "Currency Amount  ".trim() + sp;
    title += "Transaction Settle".trim() + sp;
    title += "Settle Amount     ".trim() + sp;
    title += "Paychannel Amount ".trim() + sp;
    title += "Transaction Fee   ".trim() + sp;
    title += "Transaction Income".trim() + sp+ cr+cn;
 
    output.write(title.getBytes());
    String s;
    
    for(DBObject tc : getPutOutTaskResult){
    	s="";
    	s+=tc.get("txnSetDate").toString()+ sp;
    	s+=tc.get("clientid").toString()+ sp;
    	s+=tc.get("txnType").toString()+ sp;
    	s+=tc.get("txnCount").toString()+ sp;
    	s+=tc.get("txnCur").toString()+ sp;
    	s+=tc.get("txnCurAmount").toString()+ sp;
    	s+=tc.get("txnSet").toString()+ sp;
    	s+=tc.get("txnSetAmount").toString()+ sp;
    	s+=tc.get("txnPaychAmount").toString()+ sp;
    	s+=tc.get("txnFee").toString()+ sp;
    	s+=tc.get("txnIncome").toString()+ sp + cr+cn;
    	output.write(s.getBytes());
    }
    
   output.flush();
  } catch (Exception e) {
   e.printStackTrace();
   return -1;
  } finally {
   if (fis != null) {
    try {
     fis.close();
    } catch (IOException e) {
     e.printStackTrace();
    }
    fis = null;
   }
   if (output != null) {
    try {
     output.close();
    } catch (IOException e) {
     e.printStackTrace();
    }
    output = null;
   }
  }
  return 1;
 }


public int exportCvsFile_Recon(HttpServletRequest request,HttpServletResponse response,List<DBObject> getPutOutTaskResult) {
	  String filenamedisplay = getCsvFileName("Reconciliation");
	  try {
	   filenamedisplay = URLEncoder.encode(filenamedisplay, "UTF-8");
	  } catch (UnsupportedEncodingException e1) {
	   e1.printStackTrace();
	  }
	  response.reset();
	  response.addHeader("Content-Disposition", "attachment;filename="  + filenamedisplay);
	  OutputStream output = null;
	  FileInputStream fis = null;
	
	 
	  try {
	   output = response.getOutputStream();
	   
	    String title="";
	    title += "Settlement Date    ".trim() + sp;
	    title += "Merchant ID        ".trim() + sp;
	    title += "Merchant Name		 ".trim() + sp;
	    title += "Transaction Count".trim() + sp;
	    title += "Transaction Currency ".trim() + sp;
	    title += "Transaction Amount   ".trim() + sp;
	    title += "MDR  					".trim() + sp;
	    title += "Settlement Currency   ".trim() + sp;
	    title += "Settlement Amount  ".trim() + sp;
	    title += "Channel Credit Amount    ".trim() + sp;
	    title += "History Pending Amount ".trim() + sp;
	    title += "Total Pending Amount  ".trim() + sp;
	    title += "Transfer Amount  ".trim() + sp;
	    title += "Transfer Threshold ".trim() + sp;
	    title += "Beneficiary Bank  ".trim() + sp;
	    title += "Bank Swift Code  ".trim() + sp;
	    title += "Bank Address  ".trim() + sp;
	    title += "Beneficiary Name ".trim() + sp;
	    title += "Bank Account No. ".trim() + sp+ cr+cn;
	 
	    output.write(title.getBytes());
	    String s;
	    System.out.println(getPutOutTaskResult.size());
	    for(DBObject tc : getPutOutTaskResult){
	    	s="";
	    	s+=tc.get("txnSetDate").toString()+ sp;
	    	s+=tc.get("clientid").toString()+ sp;
	    	s+=tc.get("merName").toString()+ sp;
	    	s+=tc.get("reconCount").toString()+ sp;
	    	s+=tc.get("txnReconCur").toString()+ sp;
	    	s+=tc.get("reconAmount").toString()+ sp;
	    	s+=tc.get("reconFee").toString()+ sp;
	    	s+=tc.get("reconSet").toString()+ sp;
	    	s+=tc.get("reconSetAmount").toString()+ sp;
	    	s+=tc.get("reconPaychAmount").toString()+ sp;
	    	s+=tc.get("todayPendingAmount").toString()+ sp;
	    	s+=tc.get("historyPendingAmount").toString()+ sp;
	    	s+=tc.get("totalPendingAmount").toString()+ sp;
	    	s+=tc.get("transAmount").toString()+ sp;
	    	s+=tc.get("tranThreshold").toString()+ sp;
	    	s+=tc.get("reconBenBank").toString()+ sp;
	    	s+=tc.get("reconSwiftCode").toString()+ sp;
	    	s+=tc.get("reconBankAddr").toString()+ sp;
	    	s+=tc.get("reconBenName").toString()+ sp;
	    	s+=tc.get("reconAccountNo").toString()+ sp + cr+cn;
	    	output.write(s.getBytes());
	    }
	   output.flush();
	  } catch (Exception e) {
	   e.printStackTrace();
	   return -1;
	  } finally {
	   if (fis != null) {
	    try {
	     fis.close();
	    } catch (IOException e) {
	     e.printStackTrace();
	    }
	    fis = null;
	   }
	   if (output != null) {
	    try {
	     output.close();
	    } catch (IOException e) {
	     e.printStackTrace();
	    }
	    output = null;
	   }
	  }
	  return 1;
	 }


}
