package com.magicmoble.luzhouapp.model;

import java.sql.Timestamp;
import java.util.List;

public class Message {
	String Uid;
	String releaser_id;
	String releaser_name;
	String releaser_touxiang;
	String releaser_qianming;
	String tiaomu_id;
	List<Picture> picture;
	String title;
	String message;
	String content;
	Timestamp time;

	public String getReleaser_qianming() {
		return releaser_qianming;
	}

	public void setReleaser_qianming(String releaser_qianming) {
		this.releaser_qianming = releaser_qianming;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUid() {
		return Uid;
	}

	public void setUid(String uid) {
		Uid = uid;
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

	public String getReleaser_touxiang() {
		return releaser_touxiang;
	}

	public void setReleaser_touxiang(String releaser_touxiang) {
		this.releaser_touxiang = releaser_touxiang;
	}

	public String getTiaomu_id() {
		return tiaomu_id;
	}

	public void setTiaomu_id(String tiaomu_id) {
		this.tiaomu_id = tiaomu_id;
	}

	public List<Picture> getPicture() {
		return picture;
	}

	public void setPicture(List<Picture> picture) {
		this.picture = picture;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
