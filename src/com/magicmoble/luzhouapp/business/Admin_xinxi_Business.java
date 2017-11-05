package com.magicmoble.luzhouapp.business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.magicmoble.luzhouapp.model.Admin_xinxi;

public class Admin_xinxi_Business {

	public static Admin_xinxi getAdmin_xinxiInfoById(String _id) {
		String sql = null;
		DBHelper db1 = null;
		sql = "select * from admin_xinxi where id ='" + _id + "'";

		ResultSet ret = null;
		Admin_xinxi admin_xinxi = new Admin_xinxi();
		try {
			db1 = new DBHelper(sql);
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				String id = ret.getString(1);
				String touxiang_picture = ret.getString(2);
				String name = ret.getString(3);
				String weichat = ret.getString(4);
				String sex = ret.getString(5);
				String qianming = ret.getString(6);
				String shouhuo_address = ret.getString(7);
				String phone = ret.getString(8);
				Timestamp time = ret.getTimestamp(10);
				double qianbao = ret.getDouble(11);
				
				admin_xinxi.setAdmin_xinxi_id(id);
				admin_xinxi.setTouxiang_picture(touxiang_picture);
				admin_xinxi.setName(name);
				admin_xinxi.setWeichat(weichat);
				admin_xinxi.setSex(sex);
				admin_xinxi.setQianming(qianming);
				admin_xinxi.setShouhuo_address(shouhuo_address);
				admin_xinxi.setPhone(phone);
				admin_xinxi.setQianbao(qianbao);
				admin_xinxi.setTime(time);
			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return admin_xinxi;
	}

}
