package com.magicmoble.luzhouapp.model;

import java.sql.Timestamp;

public class Dashang {
	String dashang_id;
	String tiaomu_id;
	String my_id;
	String friend_id;
	int money;
	Timestamp time;

	public String getDashang_id() {
		return dashang_id;
	}

	public void setDashang_id(String dashang_id) {
		this.dashang_id = dashang_id;
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

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getTiaomu_id() {
		return tiaomu_id;
	}

	public void setTiaomu_id(String tiaomu_id) {
		this.tiaomu_id = tiaomu_id;
	}

}
