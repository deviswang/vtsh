package com.cardinfolink.vtsh.springmgdbtest;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cardinfolink.vtsh.pojo.VtMerchant;
import com.cardinfolink.vtsh.springdao.DaoException;
import com.cardinfolink.vtsh.springdao.VtMerchantDao;
import com.sun.org.apache.commons.logging.Log;
import com.sun.org.apache.commons.logging.LogFactory;

public class TestMerchant {
//private static Log log = LogFactory.getLog(TestMerchant.class.getName());
	
	private  VtMerchantDao pr=null;
	/**
	 * 
	 *<b>function:</b>
	 * @author cuiran
	 * @createDate 2012-12-12 16:08:02
	 */
	public void init(){
		 ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		 pr = (VtMerchantDao)ctx.getBean("vtMerchantDao");//
	}
	
	/**
	 *<b>function:</b>���
	 * @author cuiran
	 * @createDate 2012-12-12 16:11:01
	 */
//	public void insert(){
//		
//		VtMerchant p=new Person("cuiran",27);
//		 pr.insert(p);
//		 log.debug("��ӳɹ�");
//	}
	/**
	 * 
	 *<b>function:</b>���������ID���Ҷ���
	 * @author cuiran
	 * @createDate 2012-12-12 16:24:10
	 */
	public void findOne(){
		String id="50c83cb552c2ceb0463177d6";
		//VtMerchant p= pr.findOne(id);
		//log.debug(p);
	}
	
	
	/**
	 * 
	 *<b>function:</b>��ѯ����
	 * @author cuiran
	 * @throws DaoException 
	 * @createDate 2012-12-12 16:08:54
	 */
	public void listAll() throws DaoException{
		
		List<VtMerchant> list=pr.findAll();
		//log.debug("��ѯ�������:");
		for (VtMerchant p:list){
			System.out.println(p.getId()+p.getPrinEmail());
			
			//log.debug(p.toString());
		}
		
		
	}
	
	/**
	 * 
	 *<b>function:</b>���Է���
	 * @author cuiran
	 * @createDate 2012-12-12 16:11:37
	 */
	public void start(){
		init();
		//insert();
		//listAll();
		try {
			listAll();
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *<b>function:</b>main����
	 * @author cuiran
	 * @createDate 2012-12-12 11:54:30
	 */
	public static void main(String[] args) {
		TestMerchant t=new TestMerchant();
		t.start();
	}
}
