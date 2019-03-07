package com.css.module.service;

import java.util.List;

import com.css.module.dao.AdminDao;
import com.css.module.model.Kslb;
import com.css.module.model.Kstm;
import com.css.module.model.Tmxx;
import com.css.module.model.Tmxxlb;

public class AdminService {


	/**
	 * 考试类别操作
	 * */
	public static void saveKslb(Kslb kslb) {
		AdminDao.insertKslb(kslb.getId(), kslb.getLbmc(), kslb.getBm());
	}
	
	public static void deleteKslb(String id) {
		AdminDao.deleteKslbById(id);
	}
	
	public static List<Kslb> selectKslbAll(){
		return AdminDao.selectKslbAll();
	}
	
	public static Kslb selectKslbId(String id){
		return AdminDao.selectKslbById(id);
	}


	/**
	 * 考试题目操作
	 * */
	public static void saveKstm(Kstm kstm) {
		AdminDao.insertKstm(kstm.getId(), kstm.getKslbid(), kstm.getKstmmc(), kstm.getKstmxh(), kstm.getTMXXLBID());
	}
	
	public static void deleteKstmAll() {
		AdminDao.deleteKstmAll();
	}
	
	public static void deleteKstmByKslbid(String kslbid) {
		AdminDao.deleteKstmByKslbid(kslbid);
	}
	
	public static void deleteKstm(String id) {
		AdminDao.deleteKstmById(id);
	}
	
	public static List<Kstm> selectKstmAll(){
		return AdminDao.selectKstmAll();
	}

	
	public static List<Kstm> selectKstmKslbid(String kslbid){
		return AdminDao.selectKstmByKslbid(kslbid);
	}
	
	

	/**
	 * 考试题目选项类别操作
	 * */
	public static void saveTmxxlb(Tmxxlb lb) {
		AdminDao.insertTmxxlb(lb.getId(), lb.getLbmc(), lb.getLb());
	}
	
	public static void deleteTmxxlb(String id) {
		AdminDao.deleteTmxxlbById(id);
	}
	
	public static List<Tmxxlb> selectTmxxlbAll(){
		return AdminDao.selectTmxxlbAll();
	}
	
	public static Tmxxlb selectTmxxlbId(String id){
		return AdminDao.selectTmxxlbById(id);
	}
	
	
	
	/**
	 * 考试题目选项操作
	 * */
	public static void saveTmxx(Tmxx tmxx) {
		AdminDao.insertTmxx(tmxx.getId(), tmxx.getTMXXLBID(), tmxx.getXXMC(), tmxx.getXXDM());
	}
	
	public static void deleteTmxx(String id) {
		AdminDao.deleteTmxxById(id);
	}
	
	public static List<Tmxx> selectTmxxAll(){
		return AdminDao.selectTmxxAll();
	}
	
	public static Tmxx selectTmxxId(String id){
		return AdminDao.selectTmxxById(id);
	}
	
	public static List<Tmxx> selectByTmxxlbid(String tmxxlbid){
		return AdminDao.selectTmxxByTmxxlbid(tmxxlbid);
	}
}
