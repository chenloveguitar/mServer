package com.magicmoble.luzhouapp.model;

import java.sql.Timestamp;
import java.util.List;

public class Dashang_user {
	String name;
	String touxiang;
	String qianming;
	int fenlei_Tag;
	String releaser_id;

	public int getFenlei_Tag() {
		return fenlei_Tag;
	}

	public void setFenlei_Tag(int fenlei_Tag) {
		this.fenlei_Tag = fenlei_Tag;
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

	public String getQianming() {
		return qianming;
	}

	public void setQianming(String qianming) {
		this.qianming = qianming;
	}

	public String getReleaser_id() {
		return releaser_id;
	}

	public void setReleaser_id(String releaser_id) {
		this.releaser_id = releaser_id;
	}

}
