package com.css.module.model;

public class Kslb implements java.io.Serializable {

	private static final long serialVersionUID = 5876040640109436449L;

	private String id;

	private String lbmc;

	private String bm;
	
	public Kslb() {
		super();
	}

	public Kslb(String id, String lbmc, String bm) {
		super();
		this.id = id;
		this.lbmc = lbmc;
		this.bm = bm;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLbmc() {
		return lbmc;
	}

	public void setLbmc(String lbmc) {
		this.lbmc = lbmc;
	}

	public String getBm() {
		return bm;
	}

	public void setBm(String bm) {
		this.bm = bm;
	}

}
