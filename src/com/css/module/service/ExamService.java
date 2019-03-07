package com.css.module.service;

import java.util.List;

import com.css.module.dao.AdminDao;
import com.css.module.dao.ExamDao;
import com.css.module.model.Ksjg;
import com.css.module.model.Ksjgnr;
import com.css.module.model.Kslb;

public class ExamService {
	
	public static void saveKsjg(Ksjg ksjg) {
		ExamDao.insertKsjg(ksjg.getId(), ksjg.getKslbbm(), ksjg.getKsmc(), ksjg.getBh(), ksjg.getSfwc(), ksjg.getXh());
	}
	
	public static List<Ksjg> selectKsjgAll(){
		return ExamDao.selectKsjgAll();
	}
	
	public static Ksjg selectKsjgId(String id){
		return ExamDao.selectKsjgById(id);
	}
	
	public static Ksjg selectKsjgByBh(String bh){
		return ExamDao.selectKsjgByBh(bh);
	}
	
	public static Ksjg selectKsjgByBhAndKslbbm(String bh, String kslbbm){
		return ExamDao.selectKsjgByBhAndKslbbm(bh, kslbbm);
	}
	
	public static void updateKsjgSfwc(String sfwcpc, String id) {
		ExamDao.updateKsjgBySfwc(sfwcpc, id);
	}
	
	public static List<Ksjgnr> selectKsjgnrByKsjgid(String ksjgid){
		return ExamDao.selectKsjgnrByKsjgid(ksjgid);
	}
	
	public static void saveKsjgnr(Ksjgnr nr) {
		ExamDao.insertKsjgnr(nr.getId(), nr.getKsjgid(), nr.getKtxh(), nr.getKtda(), nr.getKtmc());
	}
	
	public static List<Ksjgnr> selectKsjgnrAll(){
		return ExamDao.selectKsjgnrAll();
	}
	
	public static Ksjgnr selectKsjgnrId(String id){
		return ExamDao.selectKsjgnrById(id);
	}
	
	public static Ksjgnr selectKsjgnrByTxh(String ksjgid, String txh){
		return ExamDao.selectKsjgnrByKtxh(ksjgid, txh);
	}

	public static void updateKsjgnrByTxh(Ksjgnr nr) {
		ExamDao.updateKsjgnrById(nr.getId(), nr.getKsjgid(), nr.getKtxh(), nr.getKtda(), nr.getKtmc());
	}

}
