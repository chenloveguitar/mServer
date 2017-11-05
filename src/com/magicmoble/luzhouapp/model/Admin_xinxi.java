package com.magicmoble.luzhouapp.model;

import java.sql.Timestamp;

public class Admin_xinxi {
	String admin_xinxi_id;
	public Admin_xinxi(String admin_xinxi_id, String touxiang_picture, String name, String weichat, String sex,
			String qianming, String shouhuo_address, String shouhuo_name, String phone, Timestamp time,
			double qianbao) {
		super();
		this.admin_xinxi_id = admin_xinxi_id;
		this.touxiang_picture = touxiang_picture;
		this.name = name;
		this.weichat = weichat;
		this.sex = sex;
		this.qianming = qianming;
		this.shouhuo_address = shouhuo_address;
		this.shouhuo_name = shouhuo_name;
		this.phone = phone;
		this.time = time;
		this.qianbao = qianbao;
	}

	public Admin_xinxi() {
		super();
	}

	String touxiang_picture;
	String name;
	String weichat;
	String sex;
	String qianming;
	String shouhuo_address;
	String shouhuo_name;
	String phone;
	Timestamp time;
	double qianbao;
	
	
	

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public double getQianbao() {
		return qianbao;
	}

	public void setQianbao(double qianbao) {
		this.qianbao = qianbao;
	}

	public String getShouhuo_name() {
		return shouhuo_name;
	}

	public void setShouhuo_name(String shouhuo_name) {
		this.shouhuo_name = shouhuo_name;
	}

	public String getAdmin_xinxi_id() {
		return admin_xinxi_id;
	}

	public void setAdmin_xinxi_id(String admin_xinxi_id) {
		this.admin_xinxi_id = admin_xinxi_id;
	}

	public String getTouxiang_picture() {
		return touxiang_picture;
	}

	public void setTouxiang_picture(String touxiang_picture) {
		this.touxiang_picture = touxiang_picture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWeichat() {
		return weichat;
	}

	public void setWeichat(String weichat) {
		this.weichat = weichat;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getQianming() {
		return qianming;
	}

	public void setQianming(String qianming) {
		this.qianming = qianming;
	}

	public String getShouhuo_address() {
		return shouhuo_address;
	}

	public void setShouhuo_address(String shouhuo_address) {
		this.shouhuo_address = shouhuo_address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Admin_xinxi [admin_xinxi_id=" + admin_xinxi_id + ", touxiang_picture=" + touxiang_picture + ", name="
				+ name + ", weichat=" + weichat + ", sex=" + sex + ", qianming=" + qianming + ", shouhuo_address="
				+ shouhuo_address + ", shouhuo_name=" + shouhuo_name + ", phone=" + phone + ", time=" + time
				+ ", qianbao=" + qianbao + "]";
	}
	
	

}
