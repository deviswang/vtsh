package com.cardinfolink.vtsh.springdao;

import java.util.List;

import com.cardinfolink.vtsh.pojo.VtMerchant;


public interface VtMerchantDao {
	/**根据公司名称查找该用户是否存在*/
	VtMerchant findByCorpName(String corpName) throws DaoException;
	/**保存用户的注册信息*/
	void saveMerchant(VtMerchant rmt) throws DaoException;
	/**查找所有用户*/
	List<VtMerchant> findAll() throws DaoException;
	
	
	/**根据ID查找用户信息是否存在*/
	//VtMerchant findById(VtMerchantId id) throws DaoException;
	
	/**根据ID删除对应的用户信息。*/
	//int deleteById(VtMerchantId id) throws DaoException;
	/**根据website找*/
	VtMerchant findByEmail(String email) throws DaoException;
	 
	 /**更新用户的信息*/
	void updateMerchant(String priEmail,String pwd) throws DaoException;
	 /**查找商户名*/
	VtMerchant findByCompanyName(String comName)throws DaoException;
	
	VtMerchant findById(String comName,String prinEmail) throws DaoException;
	
//	Merchant findByPrinEmail(String PrinEmail)throws DaoException;
//	
//	boolean updateMerchant(Merchant merchant) throws DaoException;
//	
//	Merchant findByComName(String comName)throws DaoException;
}
