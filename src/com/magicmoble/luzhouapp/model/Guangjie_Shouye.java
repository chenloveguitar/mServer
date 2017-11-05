package com.magicmoble.luzhouapp.model;

import java.sql.Timestamp;
import java.util.List;

public class Guangjie_Shouye {
	String guangjie_id;
	String title;
	List<Picture> picture;
	int dianzan_count;
	String releaser_name;
	int pinglun_count;
	int guangjie_fenlei_Tag;
	int tuijian_Tag;

	public int getGuangjie_fenlei_Tag() {
		return guangjie_fenlei_Tag;
	}

	public void setGuangjie_fenlei_Tag(int guangjie_fenlei_Tag) {
		this.guangjie_fenlei_Tag = guangjie_fenlei_Tag;
	}

	public String getGuangjie_id() {
		return guangjie_id;
	}

	public void setGuangjie_id(String guangjie_id) {
		this.guangjie_id = guangjie_id;
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
