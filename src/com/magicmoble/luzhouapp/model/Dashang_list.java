package com.magicmoble.luzhouapp.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

public class Dashang_list {
	String touxiang;
	String name;
	String price;
	Timestamp time;
	boolean isguanzhu;
	String duixiang_id;
	int fenlei_Tag;
	String tiaomu_id;
	String yonghu_id;

	public String getYonghu_id() {
		return yonghu_id;
	}

	public void setYonghu_id(String yonghu_id) {
		this.yonghu_id = yonghu_id;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public boolean isIsguanzhu() {
		return isguanzhu;
	}

	public void setIsguanzhu(boolean isguanzhu) {
		this.isguanzhu = isguanzhu;
	}

	public String getDuixiang_id() {
		return duixiang_id;
	}

	public void setDuixiang_id(String duixiang_id) {
		this.duixiang_id = duixiang_id;
	}

	public int getFenlei_Tag() {
		return fenlei_Tag;
	}

	public void setFenlei_Tag(int fenlei_Tag) {
		this.fenlei_Tag = fenlei_Tag;
	}

	public String getTiaomu_id() {
		return tiaomu_id;
	}

	public void setTiaomu_id(String tiaomu_id) {
		this.tiaomu_id = tiaomu_id;
	}

}
