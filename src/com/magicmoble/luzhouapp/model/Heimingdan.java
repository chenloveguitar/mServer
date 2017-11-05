package com.magicmoble.luzhouapp.model;

import java.sql.Timestamp;
import java.util.List;

public class Heimingdan {
	String yongyu_id;
	String name;
	String touxiang;
	String qianming;

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

	public String getQianming() {
		return qianming;
	}

	public void setQianming(String qianming) {
		this.qianming = qianming;
	}

	public String getYongyu_id() {
		return yongyu_id;
	}

	public void setYongyu_id(String yongyu_id) {
		this.yongyu_id = yongyu_id;
	}

}
