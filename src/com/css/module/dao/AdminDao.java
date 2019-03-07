package com.css.module.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.css.common.utils.DBManager;
import com.css.module.model.Kslb;
import com.css.module.model.Kstm;
import com.css.module.model.Tmxx;
import com.css.module.model.Tmxxlb;

public class AdminDao {

	/**
	 * @see 插入题目信息
	 * @param bm: 题目编码 1.罪犯自评, 2.90项评测, 3.copa评测
	 */
	public static void insertKslb(String id, String lbmc, String bm) {
		DBManager.execute("insert into KSLB(id, LBMC, BM) values('" + id + "'," + "'" + lbmc + "'," + "'" + bm + "'");
	}

	public static void deleteKslbById(String id) {
		DBManager.execute("delete from KSLB where id='" + id + "'");
	}

	public static List<Kslb> selectKslbAll() {
		List<Kslb> kslbs = new ArrayList<Kslb>();
		Connection conn = DBManager.getConnection();
		Statement state = null;
		ResultSet result = null;
		try {
			state = conn.createStatement();
			result = state.executeQuery("select * from KSLB");
			while (result.next()) {
				Kslb kslb = new Kslb();
				kslb.setId(result.getString("id"));
				kslb.setLbmc(result.getString("LBMC"));
				kslb.setBm(result.getString("BM"));
				kslbs.add(kslb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (result != null) {
					result.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (state != null) {
					state.close();
				}
			} catch (SQLException e1) {
				e.printStackTrace();
			}
			return null;
		} finally {
			try {
				if (result != null) {
					result.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (state != null) {
					state.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return kslbs;
	}

	public static Kslb selectKslbById(String id) {
		Kslb kslb = new Kslb();
		Connection conn = DBManager.getConnection();
		Statement state = null;
		ResultSet result = null;
		try {
			state = conn.createStatement();
			result = state.executeQuery("select * from KSLB where id='" + id + "'");
			while (result.next()) {
				kslb.setId(result.getString("id"));
				kslb.setLbmc(result.getString("LBMC"));
				kslb.setBm(result.getString("BM"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (result != null) {
					result.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (state != null) {
					state.close();
				}
			} catch (SQLException e1) {
				e.printStackTrace();
			}
			return null;
		} finally {
			try {
				if (result != null) {
					result.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (state != null) {
					state.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return kslb;
	}

	public static void updateKslbById(String id, String lbmc, String bm) {
		DBManager.execute("update KSLB set LBMC='" + lbmc + "',BM='" + bm + "' where id='" + id + "'");
	}

	/**
	 * @see 插入考试题目
	 */
	public static void insertKstm(String id, String kslbid, String kstmmc, String kstmxh, String TMXXLBID) {
		DBManager.execute("insert into KSTM(id, KSLBID, KSTMMC, KSTMXH, TMXXLBID) values('" + id + "','" + kslbid
				+ "','" + kstmmc + "','" + kstmxh + "','" + TMXXLBID + "')");
	}
	
	public static void deleteKstmAll() {
		DBManager.execute("delete from KSTM");
	}
	
	public static void deleteKstmByKslbid(String kslbid) {
		DBManager.execute("delete from KSTM where KSLBID='"+kslbid+"'");
	}

	public static void deleteKstmById(String id) {
		DBManager.execute("delete from KSTM where id='" + id + "'");
	}

	public static List<Kstm> selectKstmAll() {
		List<Kstm> ms = new ArrayList<Kstm>();
		Connection conn = DBManager.getConnection();
		Statement state = null;
		ResultSet result = null;
		try {
			state = conn.createStatement();
			result = state.executeQuery("select * from KSTM order by KSTMXH");
			while (result.next()) {
				Kstm m = new Kstm();
				m.setId(result.getString("id"));
				m.setKslbid(result.getString("KSLBID"));
				m.setKstmmc(result.getString("KSTMMC"));
				m.setKstmxh(result.getString("KSTMXH"));
				m.setTMXXLBID(result.getString("TMXXLBID"));
				ms.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (result != null) {
					result.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (state != null) {
					state.close();
				}
			} catch (SQLException e1) {
				e.printStackTrace();
			}
		} finally {
			try {
				if (result != null) {
					result.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (state != null) {
					state.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ms;
	}

	public static List<Kstm> selectKstmByKslbid(String kslbid) {
		List<Kstm> ms = new ArrayList<Kstm>();
		Connection conn = DBManager.getConnection();
		Statement state = null;
		ResultSet result = null;
		try {
			state = conn.createStatement();
			result = state.executeQuery("select * from KSTM where KSLBID='"+kslbid+"' order by KSTMXH");
			while (result.next()) {
				Kstm m = new Kstm();
				m.setId(result.getString("id"));
				m.setKslbid(result.getString("KSLBID"));
				m.setKstmmc(result.getString("KSTMMC"));
				m.setKstmxh(result.getString("KSTMXH"));
				m.setTMXXLBID(result.getString("TMXXLBID"));
				ms.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (result != null) {
					result.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (state != null) {
					state.close();
				}
			} catch (SQLException e1) {
				e.printStackTrace();
			}
		} finally {
			try {
				if (result != null) {
					result.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (state != null) {
					state.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ms;
	}

	/**
	 * @see 插入考试题目选项类别
	 */
	public static void insertTmxxlb(String id, String lbmc, String lb) {
		DBManager.execute("insert into TMXXLB(id, LBMC, LB) values('" + id + "','" + lbmc + "','" + lb + "')");
	}

	public static void deleteTmxxlbById(String id) {
		DBManager.execute("delete from TMXXLB where id='" + id + "'");
	}

	public static List<Tmxxlb> selectTmxxlbAll() {
		List<Tmxxlb> ms = new ArrayList<Tmxxlb>();
		Connection conn = DBManager.getConnection();
		Statement state = null;
		ResultSet result = null;
		try {
			state = conn.createStatement();
			result = state.executeQuery("select * from TMXXLB");
			while (result.next()) {
				Tmxxlb m = new Tmxxlb();
				m.setId(result.getString("id"));
				m.setLbmc(result.getString("LBMC"));
				m.setLb(result.getString("LB"));
				ms.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (result != null) {
					result.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (state != null) {
					state.close();
				}
			} catch (SQLException e1) {
				e.printStackTrace();
			}
		} finally {
			try {
				if (result != null) {
					result.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (state != null) {
					state.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ms;
	}

	public static Tmxxlb selectTmxxlbById(String id) {
		Tmxxlb m = new Tmxxlb();
		Connection conn = DBManager.getConnection();
		Statement state = null;
		ResultSet result = null;
		try {
			state = conn.createStatement();
			result = state.executeQuery("select * from TMXXLB where id='" + id + "'");
			while (result.next()) {
				m.setId(result.getString("id"));
				m.setLbmc(result.getString("LBMC"));
				m.setLb(result.getString("LB"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (result != null) {
					result.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (state != null) {
					state.close();
				}
			} catch (SQLException e1) {
				e.printStackTrace();
			}
		} finally {
			try {
				if (result != null) {
					result.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (state != null) {
					state.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return m;
	}

	/**
	 * @see 插入考试题目选项
	 */
	public static void insertTmxx(String id, String TMXXLBID, String XXMC, String XXDM) {
		DBManager.execute("insert into TMXX(id, TMXXLBID, XXMC, XXDM) values('" + id + "','" + TMXXLBID + "','" + XXMC
				+ "','" + XXDM + "')");
	}

	public static void deleteTmxxById(String id) {
		DBManager.execute("delete from TMXX where id='" + id + "'");
	}

	public static List<Tmxx> selectTmxxAll() {
		List<Tmxx> ms = new ArrayList<Tmxx>();
		Connection conn = DBManager.getConnection();
		Statement state = null;
		ResultSet result = null;
		try {
			state = conn.createStatement();
		   result = state.executeQuery("select * from TMXX");
			while (result.next()) {
				Tmxx m = new Tmxx();
				m.setId(result.getString("id"));
				m.setTMXXLBID(result.getString("TMXXLBID"));
				m.setXXMC(result.getString("XXMC"));
				m.setXXDM(result.getString("XXDM"));
				ms.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (result != null) {
					result.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (state != null) {
					state.close();
				}
			} catch (SQLException e1) {
				e.printStackTrace();
			}
		} finally {
			try {
				if (result != null) {
					result.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (state != null) {
					state.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ms;
	}

	public static Tmxx selectTmxxById(String id) {
		Tmxx m = new Tmxx();
		Connection conn = DBManager.getConnection();
		Statement state = null;
		ResultSet result = null;
		try {
			state = conn.createStatement();
			result = state.executeQuery("select * from TMXX where id='"+id+"'");
			while (result.next()) {
				m.setId(result.getString("id"));
				m.setTMXXLBID(result.getString("TMXXLBID"));
				m.setXXMC(result.getString("XXMC"));
				m.setXXDM(result.getString("XXDM"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (result != null) {
					result.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (state != null) {
					state.close();
				}
			} catch (SQLException e1) {
				e.printStackTrace();
			}
		} finally {
			try {
				if (result != null) {
					result.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (state != null) {
					state.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return m;
	}
	
	public static List<Tmxx> selectTmxxByTmxxlbid(String tmxxlbid) {
		Connection conn = DBManager.getConnection();
		Statement state = null;
		ResultSet result = null;
		List<Tmxx> tmxxs = new ArrayList<Tmxx>();
		try {
			state = conn.createStatement();
			result = state.executeQuery("select * from TMXX where TMXXLBID='"+tmxxlbid+"'");
			while (result.next()) {
				Tmxx m = new Tmxx();
				m.setId(result.getString("id"));
				m.setTMXXLBID(result.getString("TMXXLBID"));
				m.setXXMC(result.getString("XXMC"));
				m.setXXDM(result.getString("XXDM"));
				tmxxs.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (result != null) {
					result.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (state != null) {
					state.close();
				}
			} catch (SQLException e1) {
				e.printStackTrace();
			}
		} finally {
			try {
				if (result != null) {
					result.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (state != null) {
					state.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return tmxxs;
	}
}
