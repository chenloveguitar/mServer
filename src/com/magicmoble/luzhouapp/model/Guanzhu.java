package com.magicmoble.luzhouapp.model;

import java.sql.Timestamp;

public class Guanzhu {
	private String my_id;
	private String friend_id;
	String name;
	String touxiang;
	String qianming;
	boolean isguanzhu;
	Timestamp time;
	String tiaomu_id;

	public String getTiaomu_id() {
		return tiaomu_id;
	}

	public void setTiaomu_id(String tiaomu_id) {
		this.tiaomu_id = tiaomu_id;
	}

	public String getQianming() {
		return qianming;
	}

	public void setQianming(String qianming) {
		this.qianming = qianming;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getMy_id() {
		return my_id;
	}

	public void setMy_id(String my_id) {
		this.my_id = my_id;
	}

	public String getFriend_id() {
		return friend_id;
	}

	public void setFriend_id(String friend_id) {
		this.friend_id = friend_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTouxiang() {
		return touxiang;
	}

	public void setTouxiang(String touxiang) {
		this.touxiang = touxiang;
	}

	public boolean isIsguanzhu() {
		return isguanzhu;
	}

	public void setIsguanzhu(boolean isguanzhu) {
		this.isguanzhu = isguanzhu;
	}

}
