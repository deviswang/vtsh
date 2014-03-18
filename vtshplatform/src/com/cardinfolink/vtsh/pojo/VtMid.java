package com.cardinfolink.vtsh.pojo;

import org.springframework.data.document.mongodb.mapping.Document;


@Document(collection="vt_mid")
public class VtMid {
	private String id;
	private String mcc;
	private String mid;
	private String desc;
	
	public VtMid() {
		
	}
	public VtMid(String id, String mcc, String mid, String desc) {
		super();
		this.id = id;
		this.mcc = mcc;
		this.mid = mid;
		this.desc = desc;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMcc() {
		return mcc;
	}
	public void setMcc(String mcc) {
		this.mcc = mcc;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
}
