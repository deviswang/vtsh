package com.cardinfolink.vtsh.dbutil;

import java.io.IOException;
import java.util.Properties;

public class TableNameUtil {
	public static String getTableName(String key){
		String tbName = "";
		try {
			Properties ps = new Properties();
			ps.load(TableNameUtil.class.getClassLoader()
						.getResourceAsStream("db.properties"));
			tbName = ps.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return tbName;
	}
	
}
