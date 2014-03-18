package com.cardinfolink.vtsh.pojo;

import org.springframework.data.document.mongodb.mapping.Document;

/**
 * VtMerchant entity. @author MyEclipse Persistence Tools
 */
@Document(collection="vt_merchant")
public class VtMerchant implements java.io.Serializable {

	// Fields

	private String id;
	private String prinEmail;
	private String comName;
	private String prinGender;
	private String prinFstname;
	private String prinMidname;
	private String prinLstname;
	private String prinPhone;
	private String operGender;
	private String operFstname;
	private String operMidname;
	private String operLstname;
	private String operPhone;
	private String operEmail;
	private String comRegno;
	private String comAddr;
	private String comAddr2;
	private String comPostcode;
	private String comCity;
	private String comCountry;
	private String comWebName;
	private String comWebUrl;
	private String comBusMcc;
	private String busMonVol;
	private String comTranCur1;
	private String comTranCur2;
	private String comSetCur1;
	private String comSetCur2;
	private String cur1BkName;
	private String cur1BkAcntNo;
	private String cur1BkSwiftCode;
	private String cur1BkBnfyName;
	private String cur2BkName;
	private String cur2BkAcntNo;
	private String cur2BkSwiftCode;
	private String cur2BkBnfyName;
	private String payChannel;
	private String upopServPrice;
	private String logUsername;
	private String logUserpwd;
	private String file1Name;
	private String file2Name;
	private String file3Name;
	private String regTime;
	private String desc1;
	private String desc2;
	private String desc3;
	private String desc4;

	// Constructors
	/** default constructor */
	public VtMerchant() {
	}

	/** minimal constructor */


	/** full constructor */
	public VtMerchant(String id, String prinEmail,String comName, String prinGender, String prinFstname,
			String prinMidname, String prinLstname, String prinPhone,
			String operGender, String operFstname, String operMidname,
			String operLstname, String operPhone, String operEmail,
			String comRegno, String comAddr, String comAddr2,
			String comPostcode, String comCity, String comCountry,
			String comWebName, String comWebUrl, String comBusMcc,
			String busMonVol, String comTranCur1, String comTranCur2,
			String comSetCur1, String comSetCur2, String cur1BkName,
			String cur1BkAcntNo, String cur1BkSwiftCode, String cur1BkBnfyName,
			String cur2BkName, String cur2BkAcntNo, String cur2BkSwiftCode,
			String cur2BkBnfyName, String payChannel, String upopServPrice,
			String logUsername, String logUserpwd, String file1Name,
			String file2Name, String file3Name, String regTime, String desc1,
			String desc2, String desc3, String desc4) {
		this.id = id;
		this.prinEmail = prinEmail;
		this.comName = comName;
		this.prinGender = prinGender;
		this.prinFstname = prinFstname;
		this.prinMidname = prinMidname;
		this.prinLstname = prinLstname;
		this.prinPhone = prinPhone;
		this.operGender = operGender;
		this.operFstname = operFstname;
		this.operMidname = operMidname;
		this.operLstname = operLstname;
		this.operPhone = operPhone;
		this.operEmail = operEmail;
		this.comRegno = comRegno;
		this.comAddr = comAddr;
		this.comAddr2 = comAddr2;
		this.comPostcode = comPostcode;
		this.comCity = comCity;
		this.comCountry = comCountry;
		this.comWebName = comWebName;
		this.comWebUrl = comWebUrl;
		this.comBusMcc = comBusMcc;
		this.busMonVol = busMonVol;
		this.comTranCur1 = comTranCur1;
		this.comTranCur2 = comTranCur2;
		this.comSetCur1 = comSetCur1;
		this.comSetCur2 = comSetCur2;
		this.cur1BkName = cur1BkName;
		this.cur1BkAcntNo = cur1BkAcntNo;
		this.cur1BkSwiftCode = cur1BkSwiftCode;
		this.cur1BkBnfyName = cur1BkBnfyName;
		this.cur2BkName = cur2BkName;
		this.cur2BkAcntNo = cur2BkAcntNo;
		this.cur2BkSwiftCode = cur2BkSwiftCode;
		this.cur2BkBnfyName = cur2BkBnfyName;
		this.payChannel = payChannel;
		this.upopServPrice = upopServPrice;
		this.logUsername = logUsername;
		this.logUserpwd = logUserpwd;
		this.file1Name = file1Name;
		this.file2Name = file2Name;
		this.file3Name = file3Name;
		this.regTime = regTime;
		this.desc1 = desc1;
		this.desc2 = desc2;
		this.desc3 = desc3;
		this.desc4 = desc4;
	}

	// Property accessors


	public String getPrinGender() {
		return this.prinGender;
	}

	public void setPrinGender(String prinGender) {
		this.prinGender = prinGender;
	}

	public String getPrinFstname() {
		return this.prinFstname;
	}

	public void setPrinFstname(String prinFstname) {
		this.prinFstname = prinFstname;
	}

	public String getPrinMidname() {
		return this.prinMidname;
	}

	public void setPrinMidname(String prinMidname) {
		this.prinMidname = prinMidname;
	}

	public String getPrinLstname() {
		return this.prinLstname;
	}

	public void setPrinLstname(String prinLstname) {
		this.prinLstname = prinLstname;
	}

	public String getPrinPhone() {
		return this.prinPhone;
	}

	public void setPrinPhone(String prinPhone) {
		this.prinPhone = prinPhone;
	}

	public String getOperGender() {
		return this.operGender;
	}

	public void setOperGender(String operGender) {
		this.operGender = operGender;
	}

	public String getOperFstname() {
		return this.operFstname;
	}

	public void setOperFstname(String operFstname) {
		this.operFstname = operFstname;
	}

	public String getOperMidname() {
		return this.operMidname;
	}

	public void setOperMidname(String operMidname) {
		this.operMidname = operMidname;
	}

	public String getOperLstname() {
		return this.operLstname;
	}

	public void setOperLstname(String operLstname) {
		this.operLstname = operLstname;
	}

	public String getOperPhone() {
		return this.operPhone;
	}

	public void setOperPhone(String operPhone) {
		this.operPhone = operPhone;
	}

	public String getOperEmail() {
		return this.operEmail;
	}

	public void setOperEmail(String operEmail) {
		this.operEmail = operEmail;
	}

	public String getComRegno() {
		return this.comRegno;
	}

	public void setComRegno(String comRegno) {
		this.comRegno = comRegno;
	}

	public String getComAddr() {
		return this.comAddr;
	}

	public void setComAddr(String comAddr) {
		this.comAddr = comAddr;
	}

	public String getComAddr2() {
		return this.comAddr2;
	}

	public void setComAddr2(String comAddr2) {
		this.comAddr2 = comAddr2;
	}

	public String getComPostcode() {
		return this.comPostcode;
	}

	public void setComPostcode(String comPostcode) {
		this.comPostcode = comPostcode;
	}

	public String getComCity() {
		return this.comCity;
	}

	public void setComCity(String comCity) {
		this.comCity = comCity;
	}

	public String getComCountry() {
		return this.comCountry;
	}

	public void setComCountry(String comCountry) {
		this.comCountry = comCountry;
	}

	public String getComWebName() {
		return this.comWebName;
	}

	public void setComWebName(String comWebName) {
		this.comWebName = comWebName;
	}

	public String getComWebUrl() {
		return this.comWebUrl;
	}

	public void setComWebUrl(String comWebUrl) {
		this.comWebUrl = comWebUrl;
	}

	public String getComBusMcc() {
		return this.comBusMcc;
	}

	public void setComBusMcc(String comBusMcc) {
		this.comBusMcc = comBusMcc;
	}

	public String getBusMonVol() {
		return this.busMonVol;
	}

	public void setBusMonVol(String busMonVol) {
		this.busMonVol = busMonVol;
	}

	public String getComTranCur1() {
		return this.comTranCur1;
	}

	public void setComTranCur1(String comTranCur1) {
		this.comTranCur1 = comTranCur1;
	}

	public String getComTranCur2() {
		return this.comTranCur2;
	}

	public void setComTranCur2(String comTranCur2) {
		this.comTranCur2 = comTranCur2;
	}

	public String getComSetCur1() {
		return this.comSetCur1;
	}

	public void setComSetCur1(String comSetCur1) {
		this.comSetCur1 = comSetCur1;
	}

	public String getComSetCur2() {
		return this.comSetCur2;
	}

	public void setComSetCur2(String comSetCur2) {
		this.comSetCur2 = comSetCur2;
	}

	public String getCur1BkName() {
		return this.cur1BkName;
	}

	public void setCur1BkName(String cur1BkName) {
		this.cur1BkName = cur1BkName;
	}

	public String getCur1BkAcntNo() {
		return this.cur1BkAcntNo;
	}

	public void setCur1BkAcntNo(String cur1BkAcntNo) {
		this.cur1BkAcntNo = cur1BkAcntNo;
	}

	public String getCur1BkSwiftCode() {
		return this.cur1BkSwiftCode;
	}

	public void setCur1BkSwiftCode(String cur1BkSwiftCode) {
		this.cur1BkSwiftCode = cur1BkSwiftCode;
	}

	public String getCur1BkBnfyName() {
		return this.cur1BkBnfyName;
	}

	public void setCur1BkBnfyName(String cur1BkBnfyName) {
		this.cur1BkBnfyName = cur1BkBnfyName;
	}

	public String getCur2BkName() {
		return this.cur2BkName;
	}

	public void setCur2BkName(String cur2BkName) {
		this.cur2BkName = cur2BkName;
	}

	public String getCur2BkAcntNo() {
		return this.cur2BkAcntNo;
	}

	public void setCur2BkAcntNo(String cur2BkAcntNo) {
		this.cur2BkAcntNo = cur2BkAcntNo;
	}

	public String getCur2BkSwiftCode() {
		return this.cur2BkSwiftCode;
	}

	public void setCur2BkSwiftCode(String cur2BkSwiftCode) {
		this.cur2BkSwiftCode = cur2BkSwiftCode;
	}

	public String getCur2BkBnfyName() {
		return this.cur2BkBnfyName;
	}

	public void setCur2BkBnfyName(String cur2BkBnfyName) {
		this.cur2BkBnfyName = cur2BkBnfyName;
	}

	public String getPayChannel() {
		return this.payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}

	public String getUpopServPrice() {
		return this.upopServPrice;
	}

	public void setUpopServPrice(String upopServPrice) {
		this.upopServPrice = upopServPrice;
	}

	public String getLogUsername() {
		return this.logUsername;
	}

	public void setLogUsername(String logUsername) {
		this.logUsername = logUsername;
	}

	public String getLogUserpwd() {
		return this.logUserpwd;
	}

	public void setLogUserpwd(String logUserpwd) {
		this.logUserpwd = logUserpwd;
	}

	public String getFile1Name() {
		return this.file1Name;
	}

	public void setFile1Name(String file1Name) {
		this.file1Name = file1Name;
	}

	public String getFile2Name() {
		return this.file2Name;
	}

	public void setFile2Name(String file2Name) {
		this.file2Name = file2Name;
	}

	public String getFile3Name() {
		return this.file3Name;
	}

	public void setFile3Name(String file3Name) {
		this.file3Name = file3Name;
	}

	public String getRegTime() {
		return this.regTime;
	}

	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}

	public String getDesc1() {
		return this.desc1;
	}

	public void setDesc1(String desc1) {
		this.desc1 = desc1;
	}

	public String getDesc2() {
		return this.desc2;
	}

	public void setDesc2(String desc2) {
		this.desc2 = desc2;
	}

	public String getDesc3() {
		return this.desc3;
	}

	public void setDesc3(String desc3) {
		this.desc3 = desc3;
	}

	public String getDesc4() {
		return this.desc4;
	}

	public void setDesc4(String desc4) {
		this.desc4 = desc4;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPrinEmail() {
		return prinEmail;
	}

	public void setPrinEmail(String prinEmail) {
		this.prinEmail = prinEmail;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}
}