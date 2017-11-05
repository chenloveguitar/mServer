package com.magicmoble.luzhouapp.model;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public class Shuoshuo_Xiangqing {
	String shuoshuo_id;
	String releaser_id;
	String releaser_name;
	String releaser_touxiang;
	boolean isguanzhu;
	boolean isdianzan;
	String qianming;
	List<Picture> picture;
	String content;
	int muban_Tag;
	Dianzan_Number dianzan_count;
	Timestamp time;
	Timestamp now_time;
	int pinglun_count;
	List<Pinglun> pinglun;

	public boolean isIsdianzan() {
		return isdianzan;
	}

	public void setIsdianzan(boolean isdianzan) {
		this.isdianzan = isdianzan;
	}

	public String getShuoshuo_id() {
		return shuoshuo_id;
	}

	public void setShuoshuo_id(String shuoshuo_id) {
		this.shuoshuo_id = shuoshuo_id;
	}

	public List<Picture> getPicture() {
		return picture;
	}

	public void setPicture(List<Picture> picture) {
		this.picture = picture;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Dianzan_Number getDianzan_count() {
		return dianzan_count;
	}

	public void setDianzan_count(Dianzan_Number dianzan_count) {
		this.dianzan_count = dianzan_count;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getReleaser_id() {
		return releaser_id;
	}

	public void setReleaser_id(String releaser_id) {
		this.releaser_id = releaser_id;
	}

	public String getReleaser_name() {
		return releaser_name;
	}

	public void setReleaser_name(String releaser_name) {
		this.releaser_name = releaser_name;
	}

	public String getQianming() {
		return qianming;
	}

	public void setQianming(String qianming) {
		this.qianming = qianming;
	}

	public String getReleaser_touxiang() {
		return releaser_touxiang;
	}

	public void setReleaser_touxiang(String releaser_touxiang) {
		this.releaser_touxiang = releaser_touxiang;
	}

	public int getMuban_Tag() {
		return muban_Tag;
	}

	public void setMuban_Tag(int muban_Tag) {
		this.muban_Tag = muban_Tag;
	}

	public Timestamp getNow_time() {
		return now_time;
	}

	public void setNow_time(Timestamp now_time) {
		this.now_time = now_time;
	}

	public int getPinglun_count() {
		return pinglun_count;
	}

	public void setPinglun_count(int pinglun_count) {
		this.pinglun_count = pinglun_count;
	}

	public List<Pinglun> getPinglun() {
		return pinglun;
	}

	public void setPinglun(List<Pinglun> pinglun) {
		this.pinglun = pinglun;
	}

	public boolean isIsguanzhu() {
		return isguanzhu;
	}

	public void setIsguanzhu(boolean isguanzhu) {
		this.isguanzhu = isguanzhu;
	}

}
