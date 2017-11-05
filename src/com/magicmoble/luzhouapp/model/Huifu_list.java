package com.magicmoble.luzhouapp.model;

import java.sql.Timestamp;

public class Huifu_list {
	String huifu_id;
	String pinglun_id;
	String huifuer_id;
	String huifuer_name;
	String huifuer_qianming;
	String huifu_content;
	String huifuer_touxiang;
	int dianzan_count;
	boolean isdianzan;
	Timestamp time;

	public String getHuifuer_qianming() {
		return huifuer_qianming;
	}

	public void setHuifuer_qianming(String huifuer_qianming) {
		this.huifuer_qianming = huifuer_qianming;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public boolean isIsdianzan() {
		return isdianzan;
	}

	public void setIsdianzan(boolean isdianzan) {
		this.isdianzan = isdianzan;
	}

	public int getDianzan_count() {
		return dianzan_count;
	}

	public void setDianzan_count(int dianzan_count) {
		this.dianzan_count = dianzan_count;
	}

	public String getHuifuer_touxiang() {
		return huifuer_touxiang;
	}

	public void setHuifuer_touxiang(String huifuer_touxiang) {
		this.huifuer_touxiang = huifuer_touxiang;
	}

	public String getHuifuer_name() {
		return huifuer_name;
	}

	public void setHuifuer_name(String huifuer_name) {
		this.huifuer_name = huifuer_name;
	}

	public String getHuifu_id() {
		return huifu_id;
	}

	public void setHuifu_id(String huifu_id) {
		this.huifu_id = huifu_id;
	}

	public String getPinglun_id() {
		return pinglun_id;
	}

	public void setPinglun_id(String pinglun_id) {
		this.pinglun_id = pinglun_id;
	}

	public String getHuifuer_id() {
		return huifuer_id;
	}

	public void setHuifuer_id(String huifuer_id) {
		this.huifuer_id = huifuer_id;
	}

	public String getHuifu_content() {
		return huifu_content;
	}

	public void setHuifu_content(String huifu_content) {
		this.huifu_content = huifu_content;
	}

}
