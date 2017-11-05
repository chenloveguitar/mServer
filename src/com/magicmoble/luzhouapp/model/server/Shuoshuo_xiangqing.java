package com.magicmoble.luzhouapp.model.server;

import java.sql.Timestamp;
import java.util.List;

import com.magicmoble.luzhouapp.model.Dianzan_Number;
import com.magicmoble.luzhouapp.model.Picture;
import com.magicmoble.luzhouapp.model.Pinglun;

public class Shuoshuo_xiangqing {
	String shuoshuo_id;
	String releaser_id;
	String name;
	String releaser_touxiang;
	boolean isguanzhu;
	boolean isdianzan;
	String qianming;
	List<Picture> pictures;
	String content;
	int muban_Tag;
	int share_count;
	int yuedu_count;
	int dianzan_count;
	Timestamp time;
	Timestamp now_time;
	int pinglun_count;
	int shoucang_count;
	List<Pinglun> pinglun;
	String tuijian_message;

	public int getShoucang_count() {
		return shoucang_count;
	}

	public void setShoucang_count(int shoucang_count) {
		this.shoucang_count = shoucang_count;
	}

	public String getShuoshuo_id() {
		return shuoshuo_id;
	}

	public void setShuoshuo_id(String shuoshuo_id) {
		this.shuoshuo_id = shuoshuo_id;
	}

	public String getTuijian_message() {
		return tuijian_message;
	}

	public void setTuijian_message(String tuijian_message) {
		this.tuijian_message = tuijian_message;
	}

	public int getYuedu_count() {
		return yuedu_count;
	}

	public void setYuedu_count(int yuedu_count) {
		this.yuedu_count = yuedu_count;
	}

	public int getShare_count() {
		return share_count;
	}

	public void setShare_count(int share_count) {
		this.share_count = share_count;
	}

	public boolean isIsdianzan() {
		return isdianzan;
	}

	public void setIsdianzan(boolean isdianzan) {
		this.isdianzan = isdianzan;
	}

	public String getId() {
		return shuoshuo_id;
	}

	public void setId(String shuoshuo_id) {
		this.shuoshuo_id = shuoshuo_id;
	}

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getDianzan_count() {
		return dianzan_count;
	}

	public void setDianzan_count(int dianzan_count) {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
