package com.magicmoble.luzhouapp.business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.magicmoble.luzhouapp.model.Admin_xinxi;
import com.magicmoble.luzhouapp.model.Guanzhu;

public class GuanzhuBusiness {
	public static List<String> getFriend_id(String _my_id, int page) {// 关注
		List<String> list = new ArrayList<String>();
		String sql = null;
		DBHelper db1 = null;
		if (page == 0) {
			sql = "select friend_id from guanzhu where my_id='" + _my_id + "'";

		} else {
			sql = "select friend_id from guanzhu where my_id='" + _my_id + "'LIMIT " + ((page - 1) * 12) + ","
					+ 12 * page;
		}

		ResultSet ret = null;
		try {
			db1 = new DBHelper(sql);
			ret = db1.pst.executeQuery();
			while (ret.next()) {

				String friend_id = ret.getString(1);

				list.add(friend_id);
			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<String> getMy_id(String _my_id, int page) {// 粉丝
		List<String> list = new ArrayList<String>();
		String sql = null;
		DBHelper db1 = null;
		if (page == 0) {
			sql = "select my_id from guanzhu where friend_id='" + _my_id + "'";

		} else if (page != 0) {
			sql = "select my_id from guanzhu where friend_id='" + _my_id + "'LIMIT " + ((page - 1) * 12) + ","
					+ 12 * page;
		}
		db1 = new DBHelper(sql);
		ResultSet ret = null;
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {

				String my_id = ret.getString(1);

				list.add(my_id);
			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<Guanzhu> getZhuye_Friend_id(String _my_id, int page) {// 关注
		List<Guanzhu> list = new ArrayList<Guanzhu>();
		String sql = null;
		DBHelper db1 = null;
		if (page == 0) {
			sql = "select friend_id from guanzhu where friend_id='" + _my_id + "'";

		} else {
			sql = "select friend_id from guanzhu where friend_id='" + _my_id + "'LIMIT " + ((page - 1) * 12) + ","
					+ 12 * page;
		}
		db1 = new DBHelper(sql);
		ResultSet ret = null;
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {

				String id = ret.getString(1);
				Admin_xinxi admin_xinxi = Admin_xinxi_Business.getAdmin_xinxiInfoById(id);
				String name = admin_xinxi.getName();
				String touxiang = admin_xinxi.getTouxiang_picture();
				String qianming = admin_xinxi.getQianming();
				int flag = FunctionBusiness.getGuanzhu_Tag(id, _my_id);
				boolean isguanzhu = false;
				if (flag == 0) {
					isguanzhu = false;
				} else if (flag != 0) {
					isguanzhu = true;
				}
				Timestamp timestamp = new Timestamp(0);

				Guanzhu guanzhu = new Guanzhu();
				guanzhu.setMy_id(_my_id);
				guanzhu.setFriend_id(id);
				guanzhu.setName(name);
				guanzhu.setTouxiang(touxiang);
				guanzhu.setIsguanzhu(isguanzhu);
				guanzhu.setQianming(qianming);
				guanzhu.setTime(timestamp);

				list.add(guanzhu);
			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<Guanzhu> getZhuye_My_id(String _my_id, int page) {// 关注
		List<Guanzhu> list = new ArrayList<Guanzhu>();
		String sql = null;
		DBHelper db1 = null;
		if (page == 0) {
			sql = "select friend_id from guanzhu where my_id='" + _my_id + "'";

		} else {
			sql = "select friend_id from guanzhu where my_id='" + _my_id + "'LIMIT " + ((page - 1) * 12) + ","
					+ 12 * page;
		}
		db1 = new DBHelper(sql);
		ResultSet ret = null;
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {

				String id = ret.getString(1);
				Admin_xinxi admin_xinxi = Admin_xinxi_Business.getAdmin_xinxiInfoById(id);
				String name = admin_xinxi.getName();
				String touxiang = admin_xinxi.getTouxiang_picture();
				String qiangming = admin_xinxi.getQianming();
				boolean flag = GuanzhuBusiness.getIsGuanzhu(_my_id, id);
				boolean isguanzhu = false;

				Timestamp timestamp = new Timestamp(0);

				Guanzhu guanzhu = new Guanzhu();
				guanzhu.setMy_id(_my_id);
				guanzhu.setFriend_id(id);
				guanzhu.setName(name);
				guanzhu.setTouxiang(touxiang);
				guanzhu.setIsguanzhu(flag);
				guanzhu.setTime(timestamp);

				list.add(guanzhu);
			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static String addGuanzhu(String my_id, String friend_id, int focus_Tag) {// 1:关注
																					// 2：取消关注
		String sql = null;
		DBHelper db1 = null;
		String str = null;
		String uuid = UUID.randomUUID().toString();
		if (focus_Tag == 1) {
			sql = "INSERT INTO guanzhu(id,my_id,friend_id) VALUES ('" + uuid + "', '" + my_id + "', '" + friend_id
					+ "')";
			str = "关注成功";
		} else if (focus_Tag == 2) {
			sql = "DELETE FROM guanzhu WHERE my_id =  '" + my_id + "' and friend_id='" + friend_id + "'";
			str = "取消关注成功";
		}

		db1 = new DBHelper(sql);
		try {
			boolean ret = db1.pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		db1.close();
		return str;
	}

	public static boolean getIsGuanzhu(String _my_id, String friend_id) {
		String sql = null;
		DBHelper db1 = null;
		boolean flag = false;
		sql = "select id from guanzhu where my_id='" + _my_id + "' and friend_id='" + friend_id + "'";
		db1 = new DBHelper(sql);
		ResultSet ret = null;
		String id = null;
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				id = ret.getString(1);

			}
			if (id == null) {
				flag = false;
			} else if (id != null) {
				flag = true;
			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

}
