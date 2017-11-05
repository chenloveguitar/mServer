package com.magicmoble.luzhouapp.model.server;

public class Login {
	private String login_id;
	private String phone;

	private String password;
	private String admin_leixing;

	public String getAdmin_leixing() {
		return admin_leixing;
	}

	public void setAdmin_leixing(String admin_leixing) {
		this.admin_leixing = admin_leixing;
	}

	public String getLogin_id() {
		return login_id;
	}

	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
