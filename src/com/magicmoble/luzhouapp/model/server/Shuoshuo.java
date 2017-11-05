package com.magicmoble.luzhouapp.model.server;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.magicmoble.luzhouapp.model.Picture;

public class Shuoshuo {

	List<Picture> pictures;
	int yuedu_count;
	String content;
	int share_count;
	String touxiang;
	String name;
	String qianming;
	Timestamp time;
	String tuijian_message;
	String id;

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

	public int getYuedu_count() {
		return yuedu_count;
	}

	public void setYuedu_count(int yuedu_count) {
		this.yuedu_count = yuedu_count;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getShare_count() {
		return share_count;
	}

	public void setShare_count(int share_count) {
		this.share_count = share_count;
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

	public String getQianming() {
		return qianming;
	}

	public void setQianming(String qianming) {
		this.qianming = qianming;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getTuijian_message() {
		return tuijian_message;
	}

	public void setTuijian_message(String tuijian_message) {
		this.tuijian_message = tuijian_message;
	}

}
