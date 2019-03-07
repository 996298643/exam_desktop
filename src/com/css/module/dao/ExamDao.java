package com.css.module.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.css.common.utils.DBManager;
import com.css.module.model.Ksjg;
import com.css.module.model.Ksjgnr;

public class ExamDao {

	/**
	 * @see 插入考試結果表
	 */
	public static void insertKsjg(String id, String kslbbm, String ksmc, String bh, String sfwc, int xh) {
		String sql = "insert into KSJG(id, KSLBBM, KSMC, BH, SFWC, XH) values('" + id + "','" + kslbbm + "','" + ksmc
				+ "','" + bh + "','" + sfwc + "', '" + xh + "')";
		System.out.println(sql);
		DBManager.execute(sql);
	}

	public static List<Ksjg> selectKsjgAll() {
		List<Ksjg> ms = new ArrayList<Ksjg>();
		Connection conn = DBManager.getConnection();
		Statement state = null;
		ResultSet result = null;
		try {
			state = conn.createStatement();
			result = state.executeQuery("select * from KSJG");
			while (result.next()) {
				Ksjg m = new Ksjg();
				m.setId(result.getString("id"));
				m.setKslbbm(result.getString("KSLBBM"));
				m.setKsmc(result.getString("KSMC"));
				m.setBh(result.getString("BH"));
				m.setSfwc(result.getString("SFWC"));
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
		return ms;
	}

	public static Ksjg selectKsjgById(String id) {
		Ksjg m = new Ksjg();
		Connection conn = DBManager.getConnection();
		Statement state = null;
		ResultSet result = null;
		try {
			state = conn.createStatement();
			result = state.executeQuery("select * from KSJG where id='" + id + "'");
			while (result.next()) {
				m.setId(result.getString("id"));
				m.setKslbbm(result.getString("KSLBBM"));
				m.setKsmc(result.getString("KSMC"));
				m.setBh(result.getString("BH"));
				m.setSfwc(result.getString("SFWC"));
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
		return m;
	}

	public static Ksjg selectKsjgByBh(String bh) {
		Ksjg m = new Ksjg();
		Connection conn = DBManager.getConnection();
		Statement state = null;
		ResultSet result = null;
		try {
			state = conn.createStatement();
			result = state.executeQuery("select * from KSJG where BH='" + bh + "' and SFWC='0'");
			while (result.next()) {
				m.setId(result.getString("id"));
				m.setKslbbm(result.getString("KSLBBM"));
				m.setKsmc(result.getString("KSMC"));
				m.setBh(result.getString("BH"));
				m.setSfwc(result.getString("SFWC"));
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
		return m;
	}

	public static Ksjg selectKsjgByBhAndKslbbm(String bh, String kslbbm) {
		Ksjg m = new Ksjg();
		Connection conn = DBManager.getConnection();
		Statement state = null;
		ResultSet result = null;
		try {
			state = conn.createStatement();
			result = state.executeQuery("select * from KSJG where BH='" + bh + "' and KSLBBM='" + kslbbm
					+ "' order by XH desc limit 1");
			
			while (result.next()) {
				m.setId(result.getString("id"));
				m.setKslbbm(result.getString("KSLBBM"));
				m.setKsmc(result.getString("KSMC"));
				m.setBh(result.getString("BH"));
				m.setSfwc(result.getString("SFWC"));
				m.setXh(result.getInt("XH"));
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
		return m;
	}

	public static void updateKsjgBySfwc(String sfwc, String id) {
		DBManager.execute("update KSJG set SFWC='" + sfwc + "' where id='" + id + "'");
	}

	/**
	 * @see 插入考試結果內容表
	 */
	public static void insertKsjgnr(String id, String ksjgid, String ktxh, String ktda, String ktmc) {
		DBManager.execute("insert into KSJGNR(id, KSJGID, KTXH, KTDA, KTMC) values('" + id + "','" + ksjgid + "','"
				+ ktxh + "','" + ktda + "','" + ktmc + "')");
	}

	public static void updateKsjgnrById(String id, String ksjgid, String ktxh, String ktda, String ktmc) {
		DBManager.execute("update KSJGNR set KSJGID='" + ksjgid + "',KTXH='" + ktxh + "',ktda='" + ktda + "',ktmc='"
				+ ktmc + "' where id='" + id + "'");
	}

	public static List<Ksjgnr> selectKsjgnrAll() {
		List<Ksjgnr> ms = new ArrayList<Ksjgnr>();
		Connection conn = DBManager.getConnection();
		Statement state = null;
		ResultSet result = null;

		try {
			state = conn.createStatement();
			result = state.executeQuery("select * from KSJGNR");
			while (result.next()) {
				Ksjgnr m = new Ksjgnr();
				m.setId(result.getString("id"));
				m.setKsjgid(result.getString("KSJGID"));
				m.setKtxh(result.getString("KTXH"));
				m.setKtda(result.getString("KTDA"));
				m.setKtmc(result.getString("KTMC"));
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
		return ms;
	}

	public static Ksjgnr selectKsjgnrById(String id) {
		Ksjgnr m = new Ksjgnr();
		Connection conn = DBManager.getConnection();
		Statement state = null;
		ResultSet result = null;
		try {
			state = conn.createStatement();
			result = state.executeQuery("select * from KSJGNR where id='" + id + "'");
			while (result.next()) {
				m.setId(result.getString("id"));
				m.setKsjgid(result.getString("KSJGID"));
				m.setKtxh(result.getString("KTXH"));
				m.setKtda(result.getString("KTDA"));
				m.setKtmc(result.getString("KTMC"));
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
		return m;
	}

	public static Ksjgnr selectKsjgnrByKtxh(String ksjgid, String ktxh) {
		Ksjgnr m = new Ksjgnr();
		Connection conn = DBManager.getConnection();
		Statement state = null;
		ResultSet result = null;
		try {
			state = conn.createStatement();
			result = state.executeQuery("select * from KSJGNR where KTXH='" + ktxh + "' and KSJGID='" + ksjgid + "'");
			while (result.next()) {
				m.setId(result.getString("id"));
				m.setKsjgid(result.getString("KSJGID"));
				m.setKtxh(result.getString("KTXH"));
				m.setKtda(result.getString("KTDA"));
				m.setKtmc(result.getString("KTMC"));
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
		return m;
	}

	public static List<Ksjgnr> selectKsjgnrByKsjgid(String ksjgid) {
		List<Ksjgnr> ms = new ArrayList<Ksjgnr>();
		Connection conn = DBManager.getConnection();
		Statement state = null;
		ResultSet result = null;

		try {
			state = conn.createStatement();
			result = state.executeQuery("select * from KSJGNR where KSJGID='" + ksjgid + "' order by KTXH");
			while (result.next()) {
				Ksjgnr m = new Ksjgnr();
				m.setId(result.getString("id"));
				m.setKsjgid(result.getString("KSJGID"));
				m.setKtxh(result.getString("KTXH"));
				m.setKtda(result.getString("KTDA"));
				m.setKtmc(result.getString("KTMC"));
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
		return ms;
	}
}
