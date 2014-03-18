package com.cardinfolink.vtsh.pojo;

import org.springframework.data.document.mongodb.mapping.Document;

/**
 * VtServPrice entity. @author MyEclipse Persistence Tools
 */
@Document(collection="vt_servprice")
public class VtServPrice implements java.io.Serializable {

	// Fields
	private String id;
	private String servNo;
	private Double upopRate;
	private Double alipayRate;
	private String servDesc;
	private String servDesc2;
	private String servDesc3;

	// Constructors

	/** default constructor */
	public VtServPrice() {
	}

	/** minimal constructor */
	public VtServPrice(String servNo) {
		this.servNo = servNo;
	}

	/** full constructor */
	public VtServPrice(String id,String servNo, Double upopRate, Double alipayRate,
			String servDesc, String servDesc2, String servDesc3) {
		this.id = id;
		this.servNo = servNo;
		this.upopRate = upopRate;
		this.alipayRate = alipayRate;
		this.servDesc = servDesc;
		this.servDesc2 = servDesc2;
		this.servDesc3 = servDesc3;
	}

	// Property accessors

	public String getServNo() {
		return this.servNo;
	}

	public void setServNo(String servNo) {
		this.servNo = servNo;
	}

	public Double getUpopRate() {
		return this.upopRate;
	}

	public void setUpopRate(Double upopRate) {
		this.upopRate = upopRate;
	}

	public Double getAlipayRate() {
		return this.alipayRate;
	}

	public void setAlipayRate(Double alipayRate) {
		this.alipayRate = alipayRate;
	}

	public String getServDesc() {
		return this.servDesc;
	}

	public void setServDesc(String servDesc) {
		this.servDesc = servDesc;
	}

	public String getServDesc2() {
		return this.servDesc2;
	}

	public void setServDesc2(String servDesc2) {
		this.servDesc2 = servDesc2;
	}

	public String getServDesc3() {
		return this.servDesc3;
	}

	public void setServDesc3(String servDesc3) {
		this.servDesc3 = servDesc3;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}