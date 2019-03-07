package com.css.module.model;

public class Ksjgnr implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5725051508801873154L;
	private String id;
	private String ksjgid;
	private String ktxh;
	private String ktda;
	private String ktmc;

	public Ksjgnr() {
		super();
	}

	public Ksjgnr(String id, String ksjgid, String ktxh, String ktda, String ktmc) {
		super();
		this.id = id;
		this.ksjgid = ksjgid;
		this.ktxh = ktxh;
		this.ktda = ktda;
		this.ktmc = ktmc;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKsjgid() {
		return ksjgid;
	}

	public void setKsjgid(String ksjgid) {
		this.ksjgid = ksjgid;
	}

	public String getKtxh() {
		return ktxh;
	}

	public void setKtxh(String ktxh) {
		this.ktxh = ktxh;
	}

	public String getKtda() {
		return ktda;
	}

	public void setKtda(String ktda) {
		this.ktda = ktda;
	}

	public String getKtmc() {
		return ktmc;
	}

	public void setKtmc(String ktmc) {
		this.ktmc = ktmc;
	}

}
