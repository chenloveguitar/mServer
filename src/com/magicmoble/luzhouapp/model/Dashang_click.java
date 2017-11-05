package com.magicmoble.luzhouapp.model;

import java.sql.Timestamp;
import java.util.List;

public class Dashang_click {
	Dashang_user dashang_user;
	List<Dashang_list> Dashang_list;

	public List<Dashang_list> getDashang_list() {
		return Dashang_list;
	}

	public void setDashang_list(List<Dashang_list> dashang_list) {
		Dashang_list = dashang_list;
	}

	public Dashang_user getDashang_user() {
		return dashang_user;
	}

	public void setDashang_user(Dashang_user dashang_user) {
		this.dashang_user = dashang_user;
	}

}
