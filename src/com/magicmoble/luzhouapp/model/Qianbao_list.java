package com.magicmoble.luzhouapp.model;

import java.sql.Timestamp;

public class Qianbao_list {

	String type;
	String buy_name;
	Timestamp time;
	String price;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBuy_name() {
		return buy_name;
	}

	public void setBuy_name(String buy_name) {
		this.buy_name = buy_name;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
