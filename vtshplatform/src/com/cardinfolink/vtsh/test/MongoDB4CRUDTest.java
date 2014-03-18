package com.cardinfolink.vtsh.test;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class MongoDB4CRUDTest {
	private Mongo mg = null;
	private DB db;
	private DBCollection users;
	
	/**
	 * <b>function:</b>实现MongoDB的CRUD操作
	 * @author hoojo
	 * @createDate 2011-6-2 下午03:21:23
	 * @file MongoDB4CRUDTest.java
	 * @package com.hoo.test
	 * @project MongoDB
	 * @blog http://blog.csdn.net/IBM_hoojo
	 * @email hoojo_@126.com
	 * @version 1.0
	 */
	@Before
	public void init(){
		
			try {
				mg = new Mongo();
				//mg = new Mongo("localhost",27017);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}catch (MongoException e){
				e.printStackTrace();
			}
			
			db = mg.getDB("test");
			users = db.getCollection("user");
			
	}
	
	@After
	public void destory(){
		if(mg!=null){
			mg.close();
		}
		mg = null;
		db = null;
		users = null;
		System.gc();
		
	}
	
	public void print(Object o){
		System.out.println(o);
	}
	
	
	/**
	 * <b>function:</b> 查询所有数据
	 * @author hoojo
	 * @createDate 2011-6-2 下午03:22:40
	 */
	private void queryAll(){
		print("查询user的所有数据");
		//DB游标
		DBCursor cur = users.find();
		while(cur.hasNext()){
			print(cur.next());
		}
		
	}
	
	@Test
	public void add(){
		//先查询所有数据
		queryAll();
		print("count: "+ users.count());
		DBObject user = new BasicDBObject();
		user.put("name", "hoojo");
		user.put("age", 24);
		users.save(user); //保存，getN()获取影响行数
	    print(users.save(user).getN());
		
	    DBObject us =  users.findOne(user);
		System.out.println(us);
		
		//扩展字段，随意添加字段，不影响现有数据
		user.put("sex", "female");
		print(users.save(user).getN());
		DBObject us2 =  users.findOne(user);
		System.out.println(us2);
		
		
		//添加List集合
		List<DBObject> list = new ArrayList<DBObject>();
		list.add(user);
		DBObject user2 = new BasicDBObject("name","lucy");
		user2.put("age", 23);
		
		//BasicDBObject bdt = new BasicDBObject();
		
		
		list.add(user2);
		print(users.insert(list).getN());
		
		//查询下数据，看看是否添加成功
	    print("count: " + users.count());
	    queryAll();
		
	    System.out.println(users.findOne(user2));
		
		
	}
	
	
	
	
}
