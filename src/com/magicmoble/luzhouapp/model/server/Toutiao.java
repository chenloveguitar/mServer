package com.magicmoble.luzhouapp.model.server;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.magicmoble.luzhouapp.model.Picture;

public class Toutiao {
	String id;

	List<Picture> pictures;
	String title;

	int yuedu_count;
	int share_count;
	int shoucang_count;
	String releaser_touxiang;
	String releaser_name;
	Timestamp time;
	String shenhe;
	double dianzan_hongbao;
	double share_hongbao;
	int days;
	String tuijian_message;
	String tuijian_Tag;
	String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTuijian_Tag() {
		return tuijian_Tag;
	}

	public void setTuijian_Tag(String tuijian_Tag) {
		this.tuijian_Tag = tuijian_Tag;
	}

	public String getTuijian_message() {
		return tuijian_message;
	}

	public void setTuijian_message(String tuijian_message) {
		this.tuijian_message = tuijian_message;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public int getShoucang_count() {
		return shoucang_count;
	}

	public void setShoucang_count(int shoucang_count) {
		this.shoucang_count = shoucang_count;
	}

	public String getReleaser_touxiang() {
		return releaser_touxiang;
	}

	public void setReleaser_touxiang(String releaser_touxiang) {
		this.releaser_touxiang = releaser_touxiang;
	}

	public String getReleaser_name() {
		return releaser_name;
	}

	public void setReleaser_name(String releaser_name) {
		this.releaser_name = releaser_name;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getShenhe() {
		return shenhe;
	}

	public void setShenhe(String shenhe) {
		this.shenhe = shenhe;
	}

	public double getDianzan_hongbao() {
		return dianzan_hongbao;
	}

	public void setDianzan_hongbao(double dianzan_hongbao) {
		this.dianzan_hongbao = dianzan_hongbao;
	}

	public double getShare_hongbao() {
		return share_hongbao;
	}

	public void setShare_hongbao(double share_hongbao) {
		this.share_hongbao = share_hongbao;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	@Override
	public String toString() {
		return "Toutiao [id=" + id + ", pictures=" + pictures + ", title=" + title + ", yuedu_count=" + yuedu_count
				+ ", share_count=" + share_count + ", shoucang_count=" + shoucang_count + ", releaser_touxiang="
				+ releaser_touxiang + ", releaser_name=" + releaser_name + ", time=" + time + ", shenhe=" + shenhe
				+ ", dianzan_hongbao=" + dianzan_hongbao + ", share_hongbao=" + share_hongbao + ", days=" + days
				+ ", tuijian_message=" + tuijian_message + ", tuijian_Tag=" + tuijian_Tag + ", content=" + content
				+ "]";
	}

}
