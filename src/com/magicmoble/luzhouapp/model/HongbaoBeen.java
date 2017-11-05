package com.magicmoble.luzhouapp.model;

import java.io.Serializable;

public class HongbaoBeen implements Serializable {
	public double remainSize;
	public double remainMoney;

	public HongbaoBeen(double remainSize, double remainMoney) {
		this.remainMoney = remainMoney;
		this.remainSize = remainSize;
	}

}
