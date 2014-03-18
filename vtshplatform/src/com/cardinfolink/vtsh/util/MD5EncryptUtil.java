package com.cardinfolink.vtsh.util;

import java.security.MessageDigest;

public class MD5EncryptUtil {
	 public static String getMd5String(String plainText){
	        String retStr =  null;
	        if(plainText == null || plainText.equals(""))
	            return null;
	        try{
	            MessageDigest md5 = MessageDigest.getInstance("MD5");
	            md5.update(plainText.getBytes());
	            byte result[] = md5.digest();
	            StringBuilder buf = new StringBuilder("");
	            for (int offset = 0; offset < result.length; offset++) {
	                int i = result[offset];
	                if(i<0) i+= 256;
	                if(i<16)
	                    buf.append("0");
	                    buf.append(Integer.toHexString(i));
	            }
	            retStr = buf.toString();
	        } catch (Exception ex){
	        }
	        return retStr;
	    }
}
