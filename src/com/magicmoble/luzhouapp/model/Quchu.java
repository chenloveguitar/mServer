package com.magicmoble.luzhouapp.model;

import java.sql.Timestamp;
import java.util.List;

public class Quchu {
	String quchu_id;
	String title;
	String address;
	String touxiang;
	String phone;
	List<Picture> picture;
	List<ContentModel> content;
	Dianzan_Number dianzan_count;
	int dashang_count;
	int fenlei_Tag;
	String shenhe;
	String releaser_id;
	Timestamp time;
	

	

	public String getQuchu_id() {
		return quchu_id;
	}

	public void setQuchu_id(String quchu_id) {
		this.quchu_id = quchu_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Picture> getPicture() {
		return picture;
	}

	public void setPicture(List<Picture> picture) {
		this.picture = picture;
	}

	public List<ContentModel> getContent() {
		return content;
	}

	public void setContent(List<ContentModel> content) {
		this.content = content;
	}

	public String getTouxiang() {
		return touxiang;
	}

	public void setTouxiang(String touxiang) {
		this.touxiang = touxiang;
	}

	public Dianzan_Number getDianzan_count() {
		return dianzan_count;
	}

	public void setDianzan_count(Dianzan_Number dianzan_count) {
		this.dianzan_count = dianzan_count;
	}

	public int getDashang_count() {
		return dashang_count;
	}

	public void setDashang_count(int dashang_count) {
		this.dashang_count = dashang_count;
	}

	public int getFenlei_Tag() {
		return fenlei_Tag;
	}

	public void setFenlei_Tag(int fenlei_Tag) {
		this.fenlei_Tag = fenlei_Tag;
	}

	public String getReleaser_id() {
		return releaser_id;
	}

	public void setReleaser_id(String releaser_id) {
		this.releaser_id = releaser_id;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getShenhe() {
		return shenhe;
	}

	public void setShenhe(String shenhe) {
		this.shenhe = shenhe;
	}
	
	
}
