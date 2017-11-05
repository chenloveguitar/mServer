package com.magicmoble.luzhouapp.model;

import java.sql.Timestamp;
import java.util.List;

public class Faxian_Shouye {
	String Faxian_id;
	List<Picture> picture;
	String title;
	String releaser_name;
	int pinglun_count;
	int dianzan_count;
	int tuijian_Tag;

	public int getPinglun_count() {
		return pinglun_count;
	}

	public void setPinglun_count(int pinglun_count) {
		this.pinglun_count = pinglun_count;
	}

	public int getDianzan_count() {
		return dianzan_count;
	}

	public void setDianzan_count(int dianzan_count) {
		this.dianzan_count = dianzan_count;
	}

	public String getFaxian_id() {
		return Faxian_id;
	}

	public void setFaxian_id(String faxian_id) {
		Faxian_id = faxian_id;
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

	public String getReleaser_name() {
		return releaser_name;
	}

	public void setReleaser_name(String releaser_name) {
		this.releaser_name = releaser_name;
	}

	public int getTuijian_Tag() {
		return tuijian_Tag;
	}

	public void setTuijian_Tag(int tuijian_Tag) {
		this.tuijian_Tag = tuijian_Tag;
	}

}
