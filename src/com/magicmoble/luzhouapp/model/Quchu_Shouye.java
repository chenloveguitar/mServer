package com.magicmoble.luzhouapp.model;

import java.sql.Timestamp;
import java.util.List;

public class Quchu_Shouye {
	String quchu_id;
	String title;
	List<Picture> picture;
	int dianzan_count;
	String releaser_name;
	int pinglun_count;
	int tuijian_Tag;

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

	public List<Picture> getPicture() {
		return picture;
	}

	public void setPicture(List<Picture> picture) {
		this.picture = picture;
	}

	public int getDianzan_count() {
		return dianzan_count;
	}

	public void setDianzan_count(int dianzan_count) {
		this.dianzan_count = dianzan_count;
	}

	public String getReleaser_name() {
		return releaser_name;
	}

	public void setReleaser_name(String releaser_name) {
		this.releaser_name = releaser_name;
	}

	public int getPinglun_count() {
		return pinglun_count;
	}

	public void setPinglun_count(int pinglun_count) {
		this.pinglun_count = pinglun_count;
	}

	public int getTuijian_Tag() {
		return tuijian_Tag;
	}

	public void setTuijian_Tag(int tuijian_Tag) {
		this.tuijian_Tag = tuijian_Tag;
	}

}
