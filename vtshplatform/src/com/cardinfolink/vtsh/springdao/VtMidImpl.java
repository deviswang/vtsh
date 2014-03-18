package com.cardinfolink.vtsh.springdao;

import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.data.document.mongodb.query.Criteria;
import org.springframework.data.document.mongodb.query.Query;
import org.springframework.data.document.mongodb.query.Update;

import com.cardinfolink.vtsh.pojo.VtMid;

public class VtMidImpl implements VtMidDao{
	
	private MongoTemplate mongoTemplate;
	@Override
	public VtMid findByCondition(String key,String value) throws DaoException {
		return getMongoTemplate().findOne(new Query(Criteria.where(key).is(value)), VtMid.class);
	}
	
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public void updateVtMid(String mcc,String mid) throws DaoException {
			Update updateAge = new Update();
			 updateAge.set("mid", mid);
				getMongoTemplate().updateMulti("vt_mid",
						new Query(Criteria.where("mcc").is(mcc)),
						updateAge);
	}
		
}
	

