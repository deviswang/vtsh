package com.cardinfolink.vtsh.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cardinfolink.vtsh.pojo.VtMid;
import com.cardinfolink.vtsh.springdao.DaoException;
import com.cardinfolink.vtsh.springdao.VtMidDao;

public class TestVtMid {
	
	@Test
	public void test(){
		 ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		 VtMidDao vmd = (VtMidDao) ctx.getBean("vtMidDao");		 
		 VtMid vm;
		try {
			vm = vmd.findByCondition("desc", "Airlines");
			 System.out.println(vm.getDesc());
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		 
		
	}
}
