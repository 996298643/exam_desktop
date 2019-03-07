package com.css.module.model;

public class Ksjg implements java.io.Serializable{
	
	private static final long serialVersionUID = -8994065737221345848L;

	private String id;
	
	private String kslbbm;
	
	private String ksmc;
	
	private String bh;
	
	private String sfwc;
	
	private int xh;

	public Ksjg() {
		super();
	}

	public Ksjg(String id, String kslbbm, String ksmc, String bh, String sfwc) {
		super();
		this.id = id;
		this.kslbbm = kslbbm;
		this.ksmc = ksmc;
		this.bh = bh;
		this.sfwc = sfwc;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKslbbm() {
		return kslbbm;
	}

	public void setKslbbm(String kslbbm) {
		this.kslbbm = kslbbm;
	}

	public String getKsmc() {
		return ksmc;
	}

	public void setKsmc(String ksmc) {
		this.ksmc = ksmc;
	}

	public String getBh() {
		return bh;
	}

	public void setBh(String bh) {
		this.bh = bh;
	}

	public String getSfwc() {
		return sfwc;
	}

	public void setSfwc(String sfwc) {
		this.sfwc = sfwc;
	}

	public int getXh() {
		return xh;
	}

	public void setXh(int xh) {
		this.xh = xh;
	}
}
