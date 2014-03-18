package com.cardinfolink.vtsh.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.cardinfolink.vtsh.dao.MerchantDao;
import com.cardinfolink.vtsh.dao.MerchantImpl;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class AddMerchantAction0225 {
	
	private String user;
	
	private String fee1;
	private String fee2;
	
	//unionpay information
	private String clientid;
	private String md5;
	private String merchantId;
	private String acqcode;
	private String refund_url;
	private String query_url;
	private String pay_url;
	
	//alipay information
	private String clientid2;
	private String md52;
	private String merchantId2;
	//private String acqcode2;
	private String refund_url2;
	private String query_url2;
	private String pay_url2;
	
	//allinpay information
	private String clientid3;
	private String md53;
	private String merchantId3;
	//private String acqcode2;
	private String refund_url3;
	private String query_url3;
	private String pay_url3;
	
	//bank information
	private String setCur1;
	private String setCur2;
	private String bkName1;
	private String bkName2;
	private String acntName1;
	private String acntName2;
	private String acntNum1;
	private String acntNum2;
	private String swiftCode1;
	private String swiftCode2;
	private String addr1;
	private String addr2;
	private String tranFee1;
	private String tranFee2;
	private String tranTh1;
	private String tranTh2;
	
	// merchant information
	private String merName;
	private String merAbbr;
	private String comAddr;
	private String comCountry;
	private String merTypeDesc;
	private String merTypeCode;
	private String comRegno;
	private String merUrl;
	private String merAttr;
	private String merCity;
	private String postCode;
	private String lockStat;
	private String defCurr;
	private String prinName;
	private String operName;
	private String prinMail;
	private String operMail;
	private String prinTel;
	private String operTel;
	private String appDate;
	private String appStat;
	private String appTime;
	private String tranLimit;
	private String payChannel;
	
	private boolean flag;
	
	public String execute(){
		System.out.println("开始了");
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String merchantMd5 = request.getParameter("merchant.md5");
		
		MerchantDao md = new MerchantImpl();
		
		DBObject obj = new BasicDBObject();
		obj.put("clientid", clientid);
		obj.put("merName", merName);
		obj.put("merAbbr", merAbbr);
		obj.put("comAddr", comAddr);
		obj.put("comCountry",comCountry);
		obj.put("merUrl", merUrl);
		obj.put("merAttr",merAttr );
		obj.put("merCity", merCity);
		obj.put("postCode", postCode);
		obj.put("lockStat",lockStat );
		obj.put("defCurr",defCurr );
		obj.put("prinName", prinName);
		obj.put("operName", operName);
		obj.put("merTypeDesc", merTypeDesc);
		obj.put("merTypeCode", merTypeCode);
		obj.put("comRegno",comRegno );
		obj.put("prinMail", prinMail);
		obj.put("operMail", operMail);
		obj.put("prinTel",prinTel );
		obj.put("operTel",operTel );
		obj.put("appDate", appDate);
		obj.put("appStat", appStat);
		obj.put("appTime", appTime);
		obj.put("tranLimit", tranLimit);
		obj.put("payChannel", payChannel);
		
		//fee information;
		BasicDBObject fee = new BasicDBObject();
		fee.put("fee1",fee1 );
		fee.put("fee2",fee2);
		
		obj.put("fee", fee);
		
		obj.put("md5", md5 );
		
		if(null!=payChannel && payChannel.contains("UP")){
			//business chanel information
			BasicDBObject unp = new BasicDBObject();
			unp.put("merchantId" , merchantId);
			unp.put("md5" ,merchantMd5);
			unp.put("acqcode", acqcode);
			unp.put("refund_url",  refund_url);
			unp.put("query_url" , query_url);
			unp.put("pay_url", pay_url);
			obj.put("unionpay", unp);
		}
		if(null!=payChannel && payChannel.contains("AP")){
			BasicDBObject ap = new BasicDBObject();
			ap.put("merchantId" , merchantId2);
			ap.put("md5" ,md52);
			//ap.put("acqcode", acqcode);
			ap.put("refund_url",  refund_url2);
			ap.put("query_url" , query_url2);
			ap.put("pay_url", pay_url2);
			obj.put("unionpay", ap);
		}
		if(null!=payChannel && payChannel.contains("TL")){
			BasicDBObject tl = new BasicDBObject();
			tl.put("merchantId" , merchantId3);
			tl.put("md5" ,md53);
			//ap.put("acqcode", acqcode);
			tl.put("refund_url",  refund_url3);
			tl.put("query_url" , query_url3);
			tl.put("pay_url", pay_url3);
			obj.put("unionpay", tl);
		}
		
		
		//business bank account information 
		BasicDBObject bank = new BasicDBObject();
		bank.put("setCur1",setCur1 );
		bank.put("setCur2", setCur2);
		bank.put("bkName1", bkName1);
		bank.put("bkName2",bkName2 );
		bank.put("acntName1",acntName1 );
		bank.put("acntName2",acntName2 );
		bank.put("acntNum1",acntNum1 );
		bank.put("acntNum2",acntNum2 );
		bank.put("swiftCode1",swiftCode1 );
		bank.put("swiftCode2",swiftCode2 );
		bank.put("addr1", addr1);
		bank.put("addr2",addr2 );
		bank.put("tranFee1", tranFee1);
		bank.put("tranFee2", tranFee2);
		bank.put("tranTh1", tranTh1 );
		bank.put("tranTh2",tranTh2 );
		
		obj.put("bank", bank);
		
		//flag = md.insert(obj);
		
		//将json字符串转换成DBObject对象。
		DBObject user2= (DBObject) JSON.parse(user);
		flag = md.insert(user2);
		System.out.println(" jiayige: "+user2);
		
		System.out.println(flag);
		System.out.println("结束。。。。");
		
		return "success";
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getClientid() {
		return clientid;
	}
	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public String getFee1() {
		return fee1;
	}
	public void setFee1(String fee1) {
		this.fee1 = fee1;
	}
	public String getFee2() {
		return fee2;
	}
	public void setFee2(String fee2) {
		this.fee2 = fee2;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getAcqcode() {
		return acqcode;
	}
	public void setAcqcode(String acqcode) {
		this.acqcode = acqcode;
	}

	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getMerName() {
		return merName;
	}
	public void setMerName(String merName) {
		this.merName = merName;
	}
	
	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}

	public String getMerAbbr() {
		return merAbbr;
	}

	public void setMerAbbr(String merAbbr) {
		this.merAbbr = merAbbr;
	}

	public String getComAddr() {
		return comAddr;
	}

	public void setComAddr(String comAddr) {
		this.comAddr = comAddr;
	}

	public String getComCountry() {
		return comCountry;
	}

	public void setComCountry(String comCountry) {
		this.comCountry = comCountry;
	}

	public String getMerTypeDesc() {
		return merTypeDesc;
	}

	public void setMerTypeDesc(String merTypeDesc) {
		this.merTypeDesc = merTypeDesc;
	}

	public String getMerTypeCode() {
		return merTypeCode;
	}

	public void setMerTypeCode(String merTypeCode) {
		this.merTypeCode = merTypeCode;
	}

	public String getComRegno() {
		return comRegno;
	}

	public void setComRegno(String comRegno) {
		this.comRegno = comRegno;
	}

	public String getMerUrl() {
		return merUrl;
	}

	public void setMerUrl(String merUrl) {
		this.merUrl = merUrl;
	}

	public String getMerAttr() {
		return merAttr;
	}

	public void setMerAttr(String merAttr) {
		this.merAttr = merAttr;
	}

	public String getMerCity() {
		return merCity;
	}

	public void setMerCity(String merCity) {
		this.merCity = merCity;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getLockStat() {
		return lockStat;
	}

	public void setLockStat(String lockStat) {
		this.lockStat = lockStat;
	}

	public String getDefCurr() {
		return defCurr;
	}

	public void setDefCurr(String defCurr) {
		this.defCurr = defCurr;
	}

	public String getPrinName() {
		return prinName;
	}

	public void setPrinName(String prinName) {
		this.prinName = prinName;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public String getPrinMail() {
		return prinMail;
	}

	public void setPrinMail(String prinMail) {
		this.prinMail = prinMail;
	}

	public String getOperMail() {
		return operMail;
	}

	public void setOperMail(String operMail) {
		this.operMail = operMail;
	}

	public String getPrinTel() {
		return prinTel;
	}

	public void setPrinTel(String prinTel) {
		this.prinTel = prinTel;
	}

	public String getOperTel() {
		return operTel;
	}

	public void setOperTel(String operTel) {
		this.operTel = operTel;
	}

	public String getAppDate() {
		return appDate;
	}

	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}

	public String getAppStat() {
		return appStat;
	}

	public void setAppStat(String appStat) {
		this.appStat = appStat;
	}

	public String getAppTime() {
		return appTime;
	}

	public void setAppTime(String appTime) {
		this.appTime = appTime;
	}

	public String getTranLimit() {
		return tranLimit;
	}

	public void setTranLimit(String tranLimit) {
		this.tranLimit = tranLimit;
	}

	public String getSetCur1() {
		return setCur1;
	}

	public void setSetCur1(String setCur1) {
		this.setCur1 = setCur1;
	}

	public String getSetCur2() {
		return setCur2;
	}

	public void setSetCur2(String setCur2) {
		this.setCur2 = setCur2;
	}

	public String getBkName1() {
		return bkName1;
	}

	public void setBkName1(String bkName1) {
		this.bkName1 = bkName1;
	}

	public String getBkName2() {
		return bkName2;
	}

	public void setBkName2(String bkName2) {
		this.bkName2 = bkName2;
	}

	public String getAcntName1() {
		return acntName1;
	}

	public void setAcntName1(String acntName1) {
		this.acntName1 = acntName1;
	}

	public String getAcntName2() {
		return acntName2;
	}

	public void setAcntName2(String acntName2) {
		this.acntName2 = acntName2;
	}

	public String getAcntNum1() {
		return acntNum1;
	}

	public void setAcntNum1(String acntNum1) {
		this.acntNum1 = acntNum1;
	}

	public String getAcntNum2() {
		return acntNum2;
	}

	public void setAcntNum2(String acntNum2) {
		this.acntNum2 = acntNum2;
	}

	public String getSwiftCode1() {
		return swiftCode1;
	}

	public void setSwiftCode1(String swiftCode1) {
		this.swiftCode1 = swiftCode1;
	}

	public String getSwiftCode2() {
		return swiftCode2;
	}

	public void setSwiftCode2(String swiftCode2) {
		this.swiftCode2 = swiftCode2;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getTranFee1() {
		return tranFee1;
	}

	public void setTranFee1(String tranFee1) {
		this.tranFee1 = tranFee1;
	}

	public String getTranFee2() {
		return tranFee2;
	}

	public void setTranFee2(String tranFee2) {
		this.tranFee2 = tranFee2;
	}

	public String getTranTh1() {
		return tranTh1;
	}

	public void setTranTh1(String tranTh1) {
		this.tranTh1 = tranTh1;
	}

	public String getTranTh2() {
		return tranTh2;
	}

	public void setTranTh2(String tranTh2) {
		this.tranTh2 = tranTh2;
	}

	public String getRefund_url() {
		return refund_url;
	}

	public void setRefund_url(String refundUrl) {
		refund_url = refundUrl;
	}

	public String getQuery_url() {
		return query_url;
	}

	public void setQuery_url(String queryUrl) {
		query_url = queryUrl;
	}

	public String getPay_url() {
		return pay_url;
	}

	public void setPay_url(String payUrl) {
		pay_url = payUrl;
	}

	public String getClientid2() {
		return clientid2;
	}

	public void setClientid2(String clientid2) {
		this.clientid2 = clientid2;
	}

	public String getMd52() {
		return md52;
	}

	public void setMd52(String md52) {
		this.md52 = md52;
	}

	public String getMerchantId2() {
		return merchantId2;
	}

	public void setMerchantId2(String merchantId2) {
		this.merchantId2 = merchantId2;
	}

	public String getRefund_url2() {
		return refund_url2;
	}

	public void setRefund_url2(String refundUrl2) {
		refund_url2 = refundUrl2;
	}

	public String getQuery_url2() {
		return query_url2;
	}

	public void setQuery_url2(String queryUrl2) {
		query_url2 = queryUrl2;
	}

	public String getPay_url2() {
		return pay_url2;
	}

	public void setPay_url2(String payUrl2) {
		pay_url2 = payUrl2;
	}

	public String getClientid3() {
		return clientid3;
	}

	public void setClientid3(String clientid3) {
		this.clientid3 = clientid3;
	}

	public String getMd53() {
		return md53;
	}

	public void setMd53(String md53) {
		this.md53 = md53;
	}

	public String getMerchantId3() {
		return merchantId3;
	}

	public void setMerchantId3(String merchantId3) {
		this.merchantId3 = merchantId3;
	}

	public String getRefund_url3() {
		return refund_url3;
	}

	public void setRefund_url3(String refundUrl3) {
		refund_url3 = refundUrl3;
	}

	public String getQuery_url3() {
		return query_url3;
	}

	public void setQuery_url3(String queryUrl3) {
		query_url3 = queryUrl3;
	}

	public String getPay_url3() {
		return pay_url3;
	}

	public void setPay_url3(String payUrl3) {
		pay_url3 = payUrl3;
	}
}
