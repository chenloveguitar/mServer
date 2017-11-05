package com.magicmoble.luzhouapp.model;

import java.sql.Timestamp;

public class Tuikuan {

	String tuikuan_id;
	String transaction_id;
	String content;
	Timestamp releaser_time;
	Timestamp end_time;

	public String getTuikuan_id() {
		return tuikuan_id;
	}

	public void setTuikuan_id(String tuikuan_id) {
		this.tuikuan_id = tuikuan_id;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getReleaser_time() {
		return releaser_time;
	}

	public void setReleaser_time(Timestamp releaser_time) {
		this.releaser_time = releaser_time;
	}

	public Timestamp getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Timestamp end_time) {
		this.end_time = end_time;
	}

}
