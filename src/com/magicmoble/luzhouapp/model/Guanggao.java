package com.magicmoble.luzhouapp.model;

import java.util.List;

public class Guanggao {

	List<Picture> picture;
	String Uid;
	int fenlei_Tag;
	int guangjie_fenlei_Tag;
	int muban_Tag;
	int guanggao_Tag;

	public int getGuanggao_Tag() {
		return guanggao_Tag;
	}

	public void setGuanggao_Tag(int guanggao_Tag) {
		this.guanggao_Tag = guanggao_Tag;
	}

	public int getMuban_Tag() {
		return muban_Tag;
	}

	public void setMuban_Tag(int muban_Tag) {
		this.muban_Tag = muban_Tag;
	}

	public List<Picture> getPicture() {
		return picture;
	}

	public void setPicture(List<Picture> picture) {
		this.picture = picture;
	}

	public String getUid() {
		return Uid;
	}

	public void setUid(String uid) {
		Uid = uid;
	}

	public int getFenlei_Tag() {
		return fenlei_Tag;
	}

	public void setFenlei_Tag(int fenlei_Tag) {
		this.fenlei_Tag = fenlei_Tag;
	}

	public int getGuangjie_fenlei_Tag() {
		return guangjie_fenlei_Tag;
	}

	public void setGuangjie_fenlei_Tag(int guangjie_fenlei_Tag) {
		this.guangjie_fenlei_Tag = guangjie_fenlei_Tag;
	}

}
