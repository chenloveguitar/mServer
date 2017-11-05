package com.magicmoble.luzhouapp.model;

import java.sql.Timestamp;

public class Fangke_list {
	String fangke_id;
	String touxiang;
	String name;
	Timestamp time;

	public String getFangke_id() {
		return fangke_id;
	}

	public void setFangke_id(String fangke_id) {
		this.fangke_id = fangke_id;
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

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

}
