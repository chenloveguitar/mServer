package com.magicmoble.luzhouapp.model.server;

import java.sql.Timestamp;
import java.util.List;

import com.magicmoble.luzhouapp.model.Picture;

public class Pinglun_model {
	String id;
	String content;
	String pingluner_touxiang;
	String pingluner_name;
	int dianzan_count;
	Timestamp time;
	String title;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPingluner_touxiang() {
		return pingluner_touxiang;
	}

	public void setPingluner_touxiang(String pingluner_touxiang) {
		this.pingluner_touxiang = pingluner_touxiang;
	}

	public String getPingluner_name() {
		return pingluner_name;
	}

	public void setPingluner_name(String pingluner_name) {
		this.pingluner_name = pingluner_name;
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

}
