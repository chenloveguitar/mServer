package com.magicmoble.luzhouapp.model;

import java.util.List;

public class Buy_sell {
	String transaction_id;

	String touxiang;
	String name;
	String qianming;
	List<Picture> pictures;
	String title;
	String zhuangtai_message;
	int guangjie_fenlei_Tag;
	int zhuangtai_Tag;
	double price;
	
	
	

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public int getGuangjie_fenlei_Tag() {
		return guangjie_fenlei_Tag;
	}

	public void setGuangjie_fenlei_Tag(int guangjie_fenlei_Tag) {
		this.guangjie_fenlei_Tag = guangjie_fenlei_Tag;
	}

	public String getTouxiang() {
		return touxiang;
	}

	public void setTouxiang(String touxiang) {
		this.touxiang = touxiang;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQianming() {
		return qianming;
	}

	public void setQianming(String qianming) {
		this.qianming = qianming;
	}

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getZhuangtai_message() {
		return zhuangtai_message;
	}

	public void setZhuangtai_message(String zhuangtai_message) {
		this.zhuangtai_message = zhuangtai_message;
	}

	public int getZhuangtai_Tag() {
		return zhuangtai_Tag;
	}

	public void setZhuangtai_Tag(int zhuangtai_Tag) {
		this.zhuangtai_Tag = zhuangtai_Tag;
	}

}
