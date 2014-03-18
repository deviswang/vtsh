package com.cardinfolink.vtsh.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;



public class HttpSendUtil {
	
	/**用来给管理平台发送用户注册信息并接受返回信息的方法。*/
	public static String[] sendAndGetMerchantInf(String comName,String prinEmail){
		Map<String,String> map = setMerchantMap(comName,prinEmail);
		String[] merchInf = null;
		System.out.println(comName+",,"+prinEmail);

//		HttpResponse res = HttpSend.sendbyHTTPSPost(map,url);
		String url = "http://127.0.0.1:8080/vtshplatform/createNewMerchant";
		HttpResponse res = HttpSend.sendbyHTTPPost(map,url);   //测试
//		HttpResponse res = HttpSend.sendbyHTTPSPost(map,url);  //生产
		String str="";
		try {
			str = EntityUtils.toString(res.getEntity());
			merchInf = str.split(",");
			for(String merch : merchInf){
				System.out.println(":::"+merch);
			}
			System.out.println("the return message is:\n"+str);
		} catch (ParseException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("搞定了!!!");
		return merchInf;
	}
	
	public static Map<String,String> setMerchantMap(String comName ,String prinEmail){
		Map<String,String> map = new HashMap<String,String>();
		map.put("comName", comName);
		map.put("prinEmail",prinEmail);
		return map;
	}
	
//	public static void main(String[] args) {
//		String[] str={"1221456456","sdfsdf"};
//		try {
//			isRightMessage(str);
//		} catch (ReturnMessageException e) {
//			e.printStackTrace();
//		}
//		System.out.println(str);
//	}
	
}
