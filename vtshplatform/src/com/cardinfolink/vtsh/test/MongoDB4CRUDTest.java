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
	 * <b>function:</b>ʵ��MongoDB��CRUD����
	 * @author hoojo
	 * @createDate 2011-6-2 ����03:21:23
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
	 * <b>function:</b> ��ѯ��������
	 * @author hoojo
	 * @createDate 2011-6-2 ����03:22:40
	 */
	private void queryAll(){
		print("��ѯuser����������");
		//DB�α�
		DBCursor cur = users.find();
		while(cur.hasNext()){
			print(cur.next());
		}
		
	}
	
	@Test
	public void add(){
		//�Ȳ�ѯ��������
		queryAll();
		print("count: "+ users.count());
		DBObject user = new BasicDBObject();
		user.put("name", "hoojo");
		user.put("age", 24);
		users.save(user); //���棬getN()��ȡӰ������
	    print(users.save(user).getN());
		
	    DBObject us =  users.findOne(user);
		System.out.println(us);
		
		//��չ�ֶΣ���������ֶΣ���Ӱ����������
		user.put("sex", "female");
		print(users.save(user).getN());
		DBObject us2 =  users.findOne(user);
		System.out.println(us2);
		
		
		//���List����
		List<DBObject> list = new ArrayList<DBObject>();
		list.add(user);
		DBObject user2 = new BasicDBObject("name","lucy");
		user2.put("age", 23);
		
		//BasicDBObject bdt = new BasicDBObject();
		
		
		list.add(user2);
		print(users.insert(list).getN());
		
		//��ѯ�����ݣ������Ƿ���ӳɹ�
	    print("count: " + users.count());
	    queryAll();
		
	    System.out.println(users.findOne(user2));
		
		
	}
	
	
	
	
}
