package com.magicmoble.luzhouapp.model;

public class Faxian_tuijian {
	String faxian_id;
	String title;
	String picture;
	String name;
	int pinglun_count;
	int dianzan_count;

	public String getTitle() {
		return title;
	}

	public String getFaxian_id() {
		return faxian_id;
	}

	public void setFaxian_id(String faxian_id) {
		this.faxian_id = faxian_id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

}
