package com.magicmoble.luzhouapp.model;

import java.sql.Timestamp;
import java.util.List;

public class Zhuye_Model {

	String name;
	String touxiang;
	String qianming;
	String yonghu_id;
	int guanzhu_count;
	int fensi_count;
	boolean isguanzhu;
	String sex;
List<Dianpu>  dianpu;



	public List<Dianpu> getDianpu() {
	return dianpu;
}

public void setDianpu(List<Dianpu> dianpu) {
	this.dianpu = dianpu;
}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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

	public int getGuanzhu_count() {
		return guanzhu_count;
	}

	public void setGuanzhu_count(int guanzhu_count) {
		this.guanzhu_count = guanzhu_count;
	}

	public int getFensi_count() {
		return fensi_count;
	}

	public void setFensi_count(int fensi_count) {
		this.fensi_count = fensi_count;
	}

	public boolean isIsguanzhu() {
		return isguanzhu;
	}

	public void setIsguanzhu(boolean isguanzhu) {
		this.isguanzhu = isguanzhu;
	}

	public String getYonghu_id() {
		return yonghu_id;
	}

	public void setYonghu_id(String yonghu_id) {
		this.yonghu_id = yonghu_id;
	}

}
