package com.cardinfolink.vtsh.springdao;

import java.util.List;

import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.data.document.mongodb.query.Criteria;
import org.springframework.data.document.mongodb.query.Query;

import com.cardinfolink.vtsh.pojo.VtServPrice;

public class VtServPriceImpl implements VtServPriceDao{
	private MongoTemplate mongoTemplate;
	public List<VtServPrice> findAll() {
		return getMongoTemplate().find(new Query(), VtServPrice.class);  
	}

	public VtServPrice findByservNo(String servNo) {
	return	getMongoTemplate().findOne(new Query(Criteria.where("servNo").is(servNo)), VtServPrice.class);
	}
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}	
}
