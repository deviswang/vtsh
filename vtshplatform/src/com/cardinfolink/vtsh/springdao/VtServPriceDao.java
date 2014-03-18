package com.cardinfolink.vtsh.springdao;

import java.util.List;

import com.cardinfolink.vtsh.pojo.VtServPrice;


public interface VtServPriceDao {
	VtServPrice findByservNo(String servNo);
	List<VtServPrice> findAll();
}
