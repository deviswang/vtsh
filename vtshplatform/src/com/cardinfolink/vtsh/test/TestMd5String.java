package com.cardinfolink.vtsh.test;

import java.util.List;

import com.cardinfolink.vtsh.dao.TblParaCodeImpl;
import com.cardinfolink.vtsh.util.RandomMd5Util;
import com.mongodb.DBObject;

public class TestMd5String {
	public static void main(String[] args) {
	System.out.println(	RandomMd5Util.getRandomMd5Code());
	TblParaCodeImpl tbcl = new TblParaCodeImpl();
	List<DBObject> list = tbcl.findAll();
	System.out.println(list);
	}
}
