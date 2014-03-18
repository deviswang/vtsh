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
	//�û�������
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
			char[] pwd = password.toCharArray();//ת������
			mongo = new Mongo(host,port);
			db=mongo.getDB(DB_NAME);
			db.authenticate(username, pwd);//��֤�û�������
		}catch (UnknownHostException e) {
			e.printStackTrace();
		}catch (IOException ex) {
			ex.printStackTrace();
			throw new RuntimeException("��ȡ��Դ�ļ�ʧ�ܣ�");
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
