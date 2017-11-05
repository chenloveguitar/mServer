package com.magicmoble.luzhouapp.model;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public class Dianpu {
	String dianpu_id;
	String dianpu_touxiang;
	String dianpu_name;
	String address;
	boolean isrenzheng;

	public String getDianpu_id() {
		return dianpu_id;
	}

	public void setDianpu_id(String dianpu_id) {
		this.dianpu_id = dianpu_id;
	}

	public String getDianpu_touxiang() {
		return dianpu_touxiang;
	}

	public void setDianpu_touxiang(String dianpu_touxiang) {
		this.dianpu_touxiang = dianpu_touxiang;
	}

	public String getDianpu_name() {
		return dianpu_name;
	}

	public void setDianpu_name(String dianpu_name) {
		this.dianpu_name = dianpu_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isIsrenzheng() {
		return isrenzheng;
	}

	public void setIsrenzheng(boolean isrenzheng) {
		this.isrenzheng = isrenzheng;
	}

}
