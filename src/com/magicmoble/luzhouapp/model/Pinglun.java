package com.magicmoble.luzhouapp.model;

import java.sql.Timestamp;
import java.util.List;

public class Pinglun {
	String pinglun_id;
	String pingluner_id;
	String pingluner_name;
	String pingluner_touxiang;
	String pingluner_qianming;
	String content;
	Timestamp time;
	Dianzan_Number dianzan_count;
	List<Huifu> huifu;
	boolean isdianzan;
	String tiaomu_id;
	int huifu_count;

	public String getTiaomu_id() {
		return tiaomu_id;
	}

	public void setTiaomu_id(String tiaomu_id) {
		this.tiaomu_id = tiaomu_id;
	}

	public String getPinglun_id() {
		return pinglun_id;
	}

	public void setPinglun_id(String pinglun_id) {
		this.pinglun_id = pinglun_id;
	}

	public String getPingluner_id() {
		return pingluner_id;
	}

	public void setPingluner_id(String pingluner_id) {
		this.pingluner_id = pingluner_id;
	}

	public String getPingluner_name() {
		return pingluner_name;
	}

	public void setPingluner_name(String pingluner_name) {
		this.pingluner_name = pingluner_name;
	}

	public String getPingluner_touxiang() {
		return pingluner_touxiang;
	}

	public void setPingluner_touxiang(String pingluner_touxiang) {
		this.pingluner_touxiang = pingluner_touxiang;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Dianzan_Number getDianzan_count() {
		return dianzan_count;
	}

	public void setDianzan_count(Dianzan_Number dianzan_count) {
		this.dianzan_count = dianzan_count;
	}

	public List<Huifu> getHuifu() {
		return huifu;
	}

	public void setHuifu(List<Huifu> huifu) {
		this.huifu = huifu;
	}

	public String getPingluner_qianming() {
		return pingluner_qianming;
	}

	public void setPingluner_qianming(String pingluner_qianming) {
		this.pingluner_qianming = pingluner_qianming;
	}

	public boolean getIsdianzan() {
		return isdianzan;
	}

	public void setIsdianzan(boolean isdianzan) {
		this.isdianzan = isdianzan;
	}

	public int getHuifu_count() {
		return huifu_count;
	}

	public void setHuifu_count(int huifu_count) {
		this.huifu_count = huifu_count;
	}

}
