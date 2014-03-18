package com.cardinfolink.vtsh.springdao;

import java.util.List;

import com.cardinfolink.vtsh.pojo.VtMerchant;


public interface VtMerchantDao {
	/**���ݹ�˾���Ʋ��Ҹ��û��Ƿ����*/
	VtMerchant findByCorpName(String corpName) throws DaoException;
	/**�����û���ע����Ϣ*/
	void saveMerchant(VtMerchant rmt) throws DaoException;
	/**���������û�*/
	List<VtMerchant> findAll() throws DaoException;
	
	
	/**����ID�����û���Ϣ�Ƿ����*/
	//VtMerchant findById(VtMerchantId id) throws DaoException;
	
	/**����IDɾ����Ӧ���û���Ϣ��*/
	//int deleteById(VtMerchantId id) throws DaoException;
	/**����website��*/
	VtMerchant findByEmail(String email) throws DaoException;
	 
	 /**�����û�����Ϣ*/
	void updateMerchant(String priEmail,String pwd) throws DaoException;
	 /**�����̻���*/
	VtMerchant findByCompanyName(String comName)throws DaoException;
	
	VtMerchant findById(String comName,String prinEmail) throws DaoException;
	
//	Merchant findByPrinEmail(String PrinEmail)throws DaoException;
//	
//	boolean updateMerchant(Merchant merchant) throws DaoException;
//	
//	Merchant findByComName(String comName)throws DaoException;
}
