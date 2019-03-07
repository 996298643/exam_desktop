package com.css.module.model;

public class Tmxxlb implements java.io.Serializable {

	private static final long serialVersionUID = 4084851967284405822L;

	private String id;

	private String lbmc;

	private String lb;

	public Tmxxlb() {
		super();
	}

	public Tmxxlb(String id, String lbmc, String lb) {
		super();
		this.id = id;
		this.lbmc = lbmc;
		this.lb = lb;
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

	public String getLb() {
		return lb;
	}

	public void setLb(String lb) {
		this.lb = lb;
	}

}
