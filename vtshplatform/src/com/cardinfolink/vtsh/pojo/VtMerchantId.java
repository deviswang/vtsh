package com.cardinfolink.vtsh.pojo;

/**
 * VtMerchantId entity. @author MyEclipse Persistence Tools
 */

public class VtMerchantId implements java.io.Serializable {

	// Fields

	private String prinEmail;
	private String comName;

	// Constructors

	/** default constructor */
	public VtMerchantId() {
	}

	/** full constructor */
	public VtMerchantId(String prinEmail, String comName) {
		this.prinEmail = prinEmail;
		this.comName = comName;
	}

	// Property accessors

	public String getPrinEmail() {
		return this.prinEmail;
	}

	public void setPrinEmail(String prinEmail) {
		this.prinEmail = prinEmail;
	}

	public String getComName() {
		return this.comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VtMerchantId))
			return false;
		VtMerchantId castOther = (VtMerchantId) other;

		return ((this.getPrinEmail() == castOther.getPrinEmail()) || (this
				.getPrinEmail() != null
				&& castOther.getPrinEmail() != null && this.getPrinEmail()
				.equals(castOther.getPrinEmail())))
				&& ((this.getComName() == castOther.getComName()) || (this
						.getComName() != null
						&& castOther.getComName() != null && this.getComName()
						.equals(castOther.getComName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getPrinEmail() == null ? 0 : this.getPrinEmail().hashCode());
		result = 37 * result
				+ (getComName() == null ? 0 : this.getComName().hashCode());
		return result;
	}

}