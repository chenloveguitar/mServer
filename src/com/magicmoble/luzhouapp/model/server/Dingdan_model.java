package com.magicmoble.luzhouapp.model.server;

import java.util.List;

import com.magicmoble.luzhouapp.model.Picture;

public class Dingdan_model {
	String id;
	List<Picture> pictures;
	String title;
	String transaction_id;
	String buyer_name;
	String seller_name;
	String buyer_touxiang;
	String seller_touxiang;
	int shuliang;
	double total_price;
	int guangjie_fenlei_Tag;

	public int getGuangjie_fenlei_Tag() {
		return guangjie_fenlei_Tag;
	}

	public void setGuangjie_fenlei_Tag(int guangjie_fenlei_Tag) {
		this.guangjie_fenlei_Tag = guangjie_fenlei_Tag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getBuyer_name() {
		return buyer_name;
	}

	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}

	public String getSeller_name() {
		return seller_name;
	}

	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}

	public String getBuyer_touxiang() {
		return buyer_touxiang;
	}

	public void setBuyer_touxiang(String buyer_touxiang) {
		this.buyer_touxiang = buyer_touxiang;
	}

	public String getSeller_touxiang() {
		return seller_touxiang;
	}

	public void setSeller_touxiang(String seller_touxiang) {
		this.seller_touxiang = seller_touxiang;
	}

	public int getShuliang() {
		return shuliang;
	}

	public void setShuliang(int shuliang) {
		this.shuliang = shuliang;
	}

	public double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}

}
