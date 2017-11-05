package com.magicmoble.luzhouapp.model;

import java.sql.Timestamp;
import java.util.List;

public class Create_order {

	String commodity_id;
	List<Picture> picture;
	String title;
	double price;
	double freight;
	int shuliang;

	public String getCommodity_id() {
		return commodity_id;
	}

	public void setCommodity_id(String commodity_id) {
		this.commodity_id = commodity_id;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getFreight() {
		return freight;
	}

	public void setFreight(double freight) {
		this.freight = freight;
	}

	public int getShuliang() {
		return shuliang;
	}

	public void setShuliang(int shuliang) {
		this.shuliang = shuliang;
	}

}
