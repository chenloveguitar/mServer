package com.magicmoble.luzhouapp.model.server;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.magicmoble.luzhouapp.model.Picture;

public class Guanggao_model {
	String picture;
	String url;
	String id;
	Timestamp time;
	int shangjia_Tag;
	String shangjia_message;

	public String getShangjia_message() {
		return shangjia_message;
	}

	public void setShangjia_message(String shangjia_message) {
		this.shangjia_message = shangjia_message;
	}

	public int getShangjia_Tag() {
		return shangjia_Tag;
	}

	public void setShangjia_Tag(int shangjia_Tag) {
		this.shangjia_Tag = shangjia_Tag;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
