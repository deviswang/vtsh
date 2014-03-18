package com.cardinfolink.vtsh.springdao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.data.document.mongodb.query.Criteria;
import org.springframework.data.document.mongodb.query.Query;
import org.springframework.data.document.mongodb.query.Update;
import com.cardinfolink.vtsh.pojo.VtMerchant;

public class VtMerchantImpl implements VtMerchantDao{
	
	private MongoTemplate mongoTemplate;
	
	@SuppressWarnings("unchecked")
	public VtMerchant findByCorpName(String comName) throws DaoException{
		return getMongoTemplate().findOne(new Query(Criteria.where("comName").is(comName)),VtMerchant.class);
	}
	public void saveMerchant(VtMerchant rmt) throws DaoException{
		  getMongoTemplate().insert(rmt); 
	}
	public List<VtMerchant> findAll() throws DaoException {
		return getMongoTemplate().find(new Query(), VtMerchant.class);
	}

	/**根据联合主键ID来查找用户信息*/
	@SuppressWarnings("unchecked")
	public VtMerchant findByEmail(String email)throws DaoException {
		return getMongoTemplate().findOne(new Query(Criteria.where("prinEmail").is(email)),VtMerchant.class);
	}
	
	@SuppressWarnings("unchecked")
	public VtMerchant findByCompanyName(String comName)throws DaoException {
		return getMongoTemplate().findOne(new Query(Criteria.where("comName").is(comName)),VtMerchant.class);
	}
	
	//新增更改用户信息
	public void updateMerchant(String priEmail,String pwd) throws DaoException {
		Update updateAge = new Update();
		 updateAge.set("logUserpwd", pwd);
			getMongoTemplate().updateMulti("vt_merchant",
					new Query(Criteria.where("prinEmail").is(priEmail)),
					updateAge);
	}
	
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public VtMerchant findById(String comName, String prinEmail)
		throws DaoException {
		return getMongoTemplate().findOne(new Query(Criteria.where("comName").is(comName).and("prinEmail").is(prinEmail)),VtMerchant.class);
	}
}
