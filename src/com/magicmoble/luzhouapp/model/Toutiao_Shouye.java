package com.magicmoble.luzhouapp.model;

import java.sql.Timestamp;
import java.util.List;

public class Toutiao_Shouye {
	String toutiao_id;
	List<Picture> picture;
	String title;
	String name;
	List<ContentModel> content;
	int muban_Tag;
	String laiyuan;
	Dianzan_Number dianzan_count;
	int pinglun_count;
	Timestamp timestamp;
	int guanggao_Tag;

	public String getToutiao_id() {
		return toutiao_id;
	}

	public void setToutiao_id(String toutiao_id) {
		this.toutiao_id = toutiao_id;
	}

	public List<Picture> getPicture() {
		return picture;
	}

	public void setPicture(List<Picture> picture) {
		this.picture = picture;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ContentModel> getContent() {
		return content;
	}

	public void setContent(List<ContentModel> content) {
		this.content = content;
	}

	public int getMuban_Tag() {
		return muban_Tag;
	}

	public void setMuban_Tag(int muban_Tag) {
		this.muban_Tag = muban_Tag;
	}

	public String getLaiyuan() {
		return laiyuan;
	}

	public void setLaiyuan(String laiyuan) {
		this.laiyuan = laiyuan;
	}

	public Dianzan_Number getDianzan_count() {
		return dianzan_count;
	}

	public void setDianzan_count(Dianzan_Number dianzan_count) {
		this.dianzan_count = dianzan_count;
	}

	public int getPinglun_count() {
		return pinglun_count;
	}

	public void setPinglun_count(int pinglun_count) {
		this.pinglun_count = pinglun_count;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public int getGuanggao_Tag() {
		return guanggao_Tag;
	}

	public void setGuanggao_Tag(int guanggao_Tag) {
		this.guanggao_Tag = guanggao_Tag;
	}

}
