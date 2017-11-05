package com.magicmoble.luzhouapp.model;

import java.util.List;

public class Fuwu {
	String fuwu_id;
	String title;
	String price;
	String unit;
	String phone;
	List<Picture> picture;
	List<ContentModel> content;
	int tuijian_Tag;

	public String getFuwu_id() {
		return fuwu_id;
	}

	public void setFuwu_id(String fuwu_id) {
		this.fuwu_id = fuwu_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public List<Picture> getPicture() {
		return picture;
	}

	public void setPicture(List<Picture> picture) {
		this.picture = picture;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<ContentModel> getContent() {
		return content;
	}

	public void setContent(List<ContentModel> content) {
		this.content = content;
	}

	public int getTuijian_Tag() {
		return tuijian_Tag;
	}

	public void setTuijian_Tag(int tuijian_Tag) {
		this.tuijian_Tag = tuijian_Tag;
	}

}
