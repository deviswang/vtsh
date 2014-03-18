package com.cardinfolink.vtsh.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.cardinfolink.vtsh.dao.MerchantDao;
import com.cardinfolink.vtsh.dao.MerchantImpl;
import com.cardinfolink.vtsh.dao.VtChannelImpl;
import com.cardinfolink.vtsh.pojo.VtMerchant;
import com.cardinfolink.vtsh.pojo.VtMid;
import com.cardinfolink.vtsh.pojo.VtServPrice;
import com.cardinfolink.vtsh.springdao.DaoException;
import com.cardinfolink.vtsh.springdao.VtMerchantDao;
import com.cardinfolink.vtsh.springdao.VtMidDao;
import com.cardinfolink.vtsh.springdao.VtServPriceDao;
import com.cardinfolink.vtsh.util.ClientIdUtil;
import com.cardinfolink.vtsh.util.RandomMd5Util;
import com.cardinfolink.vtsh.util.VtDateUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class CreateNewMerchantAction {
	
	private String comName;
	private String prinEmail;
	
	private VtMidDao vmdao;
	private VtMerchantDao vmd;
	private VtServPriceDao vsp;
	
	public VtServPriceDao getVsp() {
		return vsp;
	}
	public void setVsp(VtServPriceDao vsp){
		this.vsp = vsp;
	}
	public VtMidDao getVmdao() {
		return vmdao;
	}
	public void setVmdao(VtMidDao vmdao) {
		this.vmdao = vmdao;
	}
	public VtMerchantDao getVmd() {
		return vmd;
	}
	public void setVmd(VtMerchantDao vmd) {
		this.vmd = vmd;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public String getPrinEmail() {
		return prinEmail;
	}
	public void setPrinEmail(String prinEmail) {
		this.prinEmail = prinEmail;
	}
	
	public String execute(){
		System.out.println("接收注册发过来的信息");
		
		try {
			VtMerchant regMer = vmd.findById(comName, prinEmail);
			if(regMer!=null){
				System.out.println(regMer.getBusMonVol()+"---"+regMer.getComBusMcc());
				
				DBObject regMct = new BasicDBObject();    //创建用户。
				
				String mccDesc = regMer.getComBusMcc();
				VtMid vtmid = vmdao.findByCondition("desc", mccDesc);
				
				String mcc = vtmid.getMcc();
				String mid = vtmid.getMid();
				String clientid = ClientIdUtil.getClientId(mcc,mid,vmdao);
				String md5 = RandomMd5Util.getRandomMd5Code();
				
				String currency = regMer.getComTranCur2()==null ?
						regMer.getComTranCur1() : regMer.getComTranCur1()+","+regMer.getComTranCur2();
				
				String maxAmount = "10USD";//注册未审核用户给10美元限额
				
				regMct.put("clientid",clientid);
				regMct.put("md5", md5);
				regMct.put("currency", currency);
				regMct.put("max_amount", maxAmount);
				
				String webStoreName = regMer.getComName().replace(","," ");
				regMct.put("merName", webStoreName);
				regMct.put("merAbbr", webStoreName);
				
				String pwd = regMer.getLogUserpwd();
				regMct.put("password", pwd);
				String payChan = regMer.getPayChannel();
				
				System.out.println(payChan);
				//添加渠道信息
				String[] chans = payChan.split(",");
				
				for(String chan : chans){
					BasicDBObject con = new BasicDBObject();
					con.put("chanName", chan);
					DBObject channel = new VtChannelImpl().findByCondition(con);
					//修改渠道费率信息
					VtServPrice vp = vsp.findByservNo(regMer.getUpopServPrice());
					
					BasicDBObject merFee =(BasicDBObject) channel.get("merFee");
					
					if(chan.equals("UP")){
						channel.put("merType", mcc);
						merFee.put("fee1", vp.getUpopRate().toString());
						channel.put("merFee", merFee);
					}
					if(chan.equals("AP")){
						merFee.put("fee1", vp.getAlipayRate().toString());
						channel.put("merFee", merFee);
					}
					regMct.put(chan, channel);//渠道信息
					
					System.out.println(vp.getUpopRate()+" ,,,, "+ chan +" ,,, "+ channel );
				}
				
				regMct.put("prinEmail", regMer.getPrinEmail());
				String midName = regMer.getPrinMidname()==null ? "" : regMer.getPrinMidname();
				regMct.put("prinName" , regMer.getPrinLstname()+midName + regMer.getPrinLstname());
				regMct.put("prinPhone" , regMer.getPrinPhone() );
				regMct.put("prinGender" ,regMer.getPrinGender() );
			
				//regMct.put("regTime", regMer.getRegTime());
				String postCode =  regMer.getComPostcode()==null ? "" : regMer.getComPostcode();
				regMct.put("postCode",postCode);
				regMct.put("comAddr",regMer.getComAddr()+" ,,, "+regMer.getComAddr2());
				regMct.put("comRegno", regMer.getComRegno());
				regMct.put("merCity", regMer.getComCity());
				regMct.put("comCountry",regMer.getComCountry());
				regMct.put("merTypeDesc", vtmid.getDesc() );
				regMct.put("upopServPrice",regMer.getUpopServPrice());
				
				//regMct.put("","");
				//银行信息:
				BasicDBObject bk = new BasicDBObject();
				bk.put("setCur1" , regMer.getComTranCur1());
				bk.put("setCur2" , regMer.getComTranCur2()==null?"":regMer.getComTranCur2());
				bk.put("bkName1", regMer.getCur1BkName());
				bk.put("bkName2", regMer.getCur2BkName());
				bk.put("acntName1", regMer.getCur1BkBnfyName());
				bk.put("acntName2", regMer.getCur2BkBnfyName());
				bk.put("acntNum1", regMer.getCur1BkAcntNo());
				bk.put("acntNum2", regMer.getCur2BkAcntNo());
				bk.put("addr1", regMer.getDesc1());
				bk.put("addr2", regMer.getDesc1());
				bk.put("swiftCode1", regMer.getCur1BkSwiftCode());
				bk.put("swiftCode2", regMer.getCur2BkSwiftCode());
				
				regMct.put("bank", bk);
			    regMct.put("comName",regMer.getComName());
			    String dateTime = VtDateUtil.getCurrentDate("yyyyMMdd");
			    System.out.println(dateTime);
				regMct.put("appDate" , dateTime );
				regMct.put("appStat" , dateTime );
				regMct.put("appTime" , dateTime );
				
				regMct.put("operGender", regMer.getOperGender());
				regMct.put("operEmail" , regMer.getOperEmail());
				String operMidName = regMer.getOperMidname() == null ? "" : regMer.getOperMidname();
				regMct.put("operName" , regMer.getOperFstname()+" "+operMidName + regMer.getOperLstname());
				regMct.put("operTel" , regMer.getOperPhone());
				regMct.put("payChannel" , regMer.getPayChannel());
				
				regMct.put("file1Name", regMer.getFile1Name());
				regMct.put("file2Name", regMer.getFile2Name());
				regMct.put("file3Name", regMer.getFile3Name());
				
				regMct.put("tranFee1"  , "");
				regMct.put("tranFee2"  , " ");
				regMct.put("tranLimit" , " ");
				//regMct.put( " " , " " );
				regMct.put("roleStatus", "M");//商户角色...
				regMct.put("delStatus", "N");//设置商户删除状态
				regMct.put("regStatus", "R");//设置用户审核状态
				//regMct.put("", );
				regMct.put("regTime",regMer.getRegTime());//
				String defRisk = "1";//风控规则;
				regMct.put("risk", defRisk);
				regMct.put("roSerRate", "0");//扣率规则
				System.out.println(regMct);
				
				MerchantDao reMd = new MerchantImpl();
				if(reMd.insert(regMct));{ //保存注册的用户
					 try{
		                	HttpServletResponse response = ServletActionContext.getResponse();
							response.getWriter().write( clientid + "," + md5 );
						}   catch (IOException e) {
							System.out.println("向网站返回信息出错...");
							e.printStackTrace();
						}
				}
			}	
		} catch (DaoException e) {
			System.out.println("查询错误,统计用户信息错误");
			e.printStackTrace();
		}
		return null;
	}
	
	
}
