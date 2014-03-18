package com.cardinfolink.vtsh.dbutil;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Properties;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;


public class MongodbUtil {
	private static Mongo mongo;
	private static DB db;
	private static String host;
	private static int port;
	private static String DB_NAME;
	//用户名密码
	private static String username;
	private static String password;
	
//	private static ThreadLocal<DBCollection> tl = 
//	new ThreadLocal<DBCollection>();
	static{
		try {
			Properties ps = new Properties();
			ps.load(MongodbUtil.class.getClassLoader()
						.getResourceAsStream("db.properties"));
			host=ps.getProperty("host");
			port=Integer.parseInt(ps.getProperty("port"));
			DB_NAME=ps.getProperty("dbname");
			username=ps.getProperty("username");
			password=ps.getProperty("password");
			char[] pwd = password.toCharArray();//转换密码
			mongo = new Mongo(host,port);
			db=mongo.getDB(DB_NAME);
			db.authenticate(username, pwd);//验证用户名密码
		}catch (UnknownHostException e) {
			e.printStackTrace();
		}catch (IOException ex) {
			ex.printStackTrace();
			throw new RuntimeException("读取资源文件失败！");
		}
	}
	public static DBCollection getDBCollection(String tableName) {
		DBCollection dbc=null;
			dbc =db.getCollection(tableName);
		return dbc;
	}
	public static DB getDB(){
		return db;
	}
}
