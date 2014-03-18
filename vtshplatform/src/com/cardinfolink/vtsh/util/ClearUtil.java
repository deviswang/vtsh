package com.cardinfolink.vtsh.util;

public class ClearUtil {
	public static String getFormatAmount(Double amount){
		//四舍五入，省略保留两位小数。
		return String.format("%.2f", amount);
	}
}
