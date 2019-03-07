package com.css.module.model;

public class Tmxx implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6502149744506410666L;
	private String id;
	private String TMXXLBID;
	private String XXMC;
	private String XXDM;

	public Tmxx() {
		super();
	}

	public Tmxx(String id, String tMXXLBID, String xXMC, String xXDM) {
		super();
		this.id = id;
		TMXXLBID = tMXXLBID;
		XXMC = xXMC;
		XXDM = xXDM;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTMXXLBID() {
		return TMXXLBID;
	}

	public void setTMXXLBID(String tMXXLBID) {
		TMXXLBID = tMXXLBID;
	}

	public String getXXMC() {
		return XXMC;
	}

	public void setXXMC(String xXMC) {
		XXMC = xXMC;
	}

	public String getXXDM() {
		return XXDM;
	}

	public void setXXDM(String xXDM) {
		XXDM = xXDM;
	}

}
