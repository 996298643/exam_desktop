package com.css.module.model;

public class Kstm implements java.io.Serializable{

	private static final long serialVersionUID = -8110267930207809273L;

	private String id;
	
	private String kslbid;
	
	private String kstmmc;
	
	private String kstmxh;
	
	private String TMXXLBID;
	
	public Kstm() {
		super();
	}

	public Kstm(String id, String kslbid, String kstmmc, String kstmxh, String tMXXLBID) {
		super();
		this.id = id;
		this.kslbid = kslbid;
		this.kstmmc = kstmmc;
		this.kstmxh = kstmxh;
		TMXXLBID = tMXXLBID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKslbid() {
		return kslbid;
	}

	public void setKslbid(String kslbid) {
		this.kslbid = kslbid;
	}

	public String getKstmmc() {
		return kstmmc;
	}

	public void setKstmmc(String kstmmc) {
		this.kstmmc = kstmmc;
	}

	public String getKstmxh() {
		return kstmxh;
	}

	public void setKstmxh(String kstmxh) {
		this.kstmxh = kstmxh;
	}

	public String getTMXXLBID() {
		return TMXXLBID;
	}

	public void setTMXXLBID(String tMXXLBID) {
		TMXXLBID = tMXXLBID;
	}
	
}
