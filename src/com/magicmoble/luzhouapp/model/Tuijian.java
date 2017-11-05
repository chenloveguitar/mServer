package com.magicmoble.luzhouapp.model;

import java.sql.Timestamp;
import java.util.List;

public class Tuijian {

	String Tuijian_id;
	String title;
	String releaser_name;
	List<Picture> picture;
	Timestamp time;

	public String getTitle() {
		return title;
	}

	public String getTuijian_id() {
		return Tuijian_id;
	}

	public void setTuijian_id(String tuijian_id) {
		Tuijian_id = tuijian_id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReleaser_name() {
		return releaser_name;
	}

	public void setReleaser_name(String releaser_name) {
		this.releaser_name = releaser_name;
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

}
