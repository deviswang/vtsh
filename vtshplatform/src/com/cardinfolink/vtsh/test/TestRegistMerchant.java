package com.cardinfolink.vtsh.test;

import java.util.List;

import org.junit.Test;

import com.cardinfolink.vtsh.dao.RegistMerchantDao;
import com.cardinfolink.vtsh.dao.RegistMerchantImpl;
import com.mongodb.DBObject;

public class TestRegistMerchant {

		@Test
		public void test(){

			RegistMerchantDao rmd = new RegistMerchantImpl();
			List<DBObject> list = rmd.findAll();
			for(DBObject merchant : list){
				System.out.println(merchant);
			}
			
		}
}
