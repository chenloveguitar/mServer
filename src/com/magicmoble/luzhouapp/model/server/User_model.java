package com.magicmoble.luzhouapp.model.server;

import java.sql.Timestamp;

public class User_model {
	String touxiang;
	String name;
	int fensi;
	int guanzhu;
	Timestamp time;
	String qianming;
	String renzheng_Tag;
	double qianbao;
	String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public int getFensi() {
		return fensi;
	}

	public void setFensi(int fensi) {
		this.fensi = fensi;
	}

	public int getGuanzhu() {
		return guanzhu;
	}

	public void setGuanzhu(int guanzhu) {
		this.guanzhu = guanzhu;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getQianming() {
		return qianming;
	}

	public void setQianming(String qianming) {
		this.qianming = qianming;
	}

	public String getRenzheng_Tag() {
		return renzheng_Tag;
	}

	public void setRenzheng_Tag(String renzheng_Tag) {
		this.renzheng_Tag = renzheng_Tag;
	}

	public double getQianbao() {
		return qianbao;
	}

	public void setQianbao(double qianbao) {
		this.qianbao = qianbao;
	}

}
