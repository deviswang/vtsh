package com.cardinfolink.vtsh.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class VtDateUtil {
	/**��ȡ��ǰϵͳʱ����ַ�����*/
	public static String getCurrentDate(String fmt){
		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
		Date date = new Date();
		String strDate = sdf.format(date);
		return strDate;
	}
}
