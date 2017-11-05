package com.magicmoble.luzhouapp.model;

import java.util.List;

public class Dianzan_Number {
	int dianzan_count;
	List<Dianzan_touxiang> touxiang_list;

	public int getDianzan_count() {
		return dianzan_count;
	}

	public void setDianzan_count(int dianzan_count) {
		this.dianzan_count = dianzan_count;
	}

	public List<Dianzan_touxiang> getTouxiang_list() {
		return touxiang_list;
	}

	public void setTouxiang_list(List<Dianzan_touxiang> touxiang_list) {
		this.touxiang_list = touxiang_list;
	}

}
