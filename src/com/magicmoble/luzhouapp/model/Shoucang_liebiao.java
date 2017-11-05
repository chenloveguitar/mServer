package com.magicmoble.luzhouapp.model;

import java.sql.Timestamp;
import java.util.List;

public class Shoucang_liebiao {
	String shoucang_liebiao_id;
	String tiaomu_id;
	String title;
	List<Picture> picture;
	Timestamp time;
	int muban_Tag;
	String shenhe;
	int delect_Tag;
	int guangjie_fenlei_Tag;
	int flag;

	public int getDelect_Tag() {
		return delect_Tag;
	}

	public void setDelect_Tag(int delect_Tag) {
		this.delect_Tag = delect_Tag;
	}

	public String getShenhe() {
		return shenhe;
	}

	public void setShenhe(String shenhe) {
		this.shenhe = shenhe;
	}

	public String getShoucang_liebiao_id() {
		return shoucang_liebiao_id;
	}

	public void setShoucang_liebiao_id(String shoucang_liebiao_id) {
		this.shoucang_liebiao_id = shoucang_liebiao_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Picture> getPicture() {
		return picture;
	}

	public void setPicture(List<Picture> picture) {
		this.picture = picture;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public int getMuban_Tag() {
		return muban_Tag;
	}

	public void setMuban_Tag(int muban_Tag) {
		this.muban_Tag = muban_Tag;
	}

	public int getGuangjie_fenlei_Tag() {
		return guangjie_fenlei_Tag;
	}

	public void setGuangjie_fenlei_Tag(int guangjie_fenlei_Tag) {
		this.guangjie_fenlei_Tag = guangjie_fenlei_Tag;
	}

	public String getTiaomu_id() {
		return tiaomu_id;
	}

	public void setTiaomu_id(String tiaomu_id) {
		this.tiaomu_id = tiaomu_id;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
	

}
