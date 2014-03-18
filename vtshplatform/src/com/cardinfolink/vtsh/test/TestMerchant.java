package com.cardinfolink.vtsh.test;

import org.junit.Test;

import com.cardinfolink.vtsh.dao.MerchantDao;
import com.cardinfolink.vtsh.dao.MerchantImpl;

public class TestMerchant {
	@Test
	public void test(){
		MerchantDao md = new MerchantImpl();
		System.out.println(md.findByPrinEmail("vtsh-test"));
	}
}
