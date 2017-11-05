package com.magicmoble.luzhouapp.business;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.swing.text.TabableView;

import com.magicmoble.luzhouapp.model.Biaoqian;
import com.magicmoble.luzhouapp.model.ContentModel;
import com.magicmoble.luzhouapp.model.Dashang;
import com.magicmoble.luzhouapp.model.Dashang_list;
import com.magicmoble.luzhouapp.model.Dashang_user;
import com.magicmoble.luzhouapp.model.Dianzan_Number;
import com.magicmoble.luzhouapp.model.Faxian;
import com.magicmoble.luzhouapp.model.Fuwu;
import com.magicmoble.luzhouapp.model.Guangjie_Xiangqing;
import com.magicmoble.luzhouapp.model.Hongbao;
import com.magicmoble.luzhouapp.model.Picture;
import com.magicmoble.luzhouapp.model.Tuijian;

public class DashangBusiness {

	public static void getDashang(double dashang_price, String friend_id) {

		String sql = null;
		DBHelper db1 = null;
		sql = "update admin_xinxi set qianbao=round((qianbao+" + dashang_price + "),2)  where id='" + friend_id + "'";
		db1 = new DBHelper(sql);
		try {
			int ret = db1.pst.executeUpdate();

			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static String Dashang_click(String tiaomu_id, String my_id, String duixiang_id, double money,
			int fenlei_Tag) {

		String sql = null;
		DBHelper db1 = null;
		String id = UUID.randomUUID().toString();
		Timestamp time = new Timestamp(new Date().getTime());
		sql = "INSERT INTO dashang(id,tiaomu_id,my_id,friend_id,money,time,fenlei_Tag) VALUES('" + id + "','"
				+ tiaomu_id + "','" + my_id + "','" + duixiang_id + "'," + money + ",'" + time + "'," + fenlei_Tag
				+ ")";
		db1 = new DBHelper(sql);
		try {
			int ret = db1.pst.executeUpdate();
			getDashang(money, duixiang_id);
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "谢谢打赏";
	}

	public static String Dashang_Iosclick(String tiaomu_id, String my_id, String duixiang_id, double money,
			int fenlei_Tag) {

		String sql = null;
		DBHelper db1 = null;
		String id = UUID.randomUUID().toString();
		Timestamp time = new Timestamp(new Date().getTime());
		double _money = Math.round(money * 0.98 * 0.7 * 10) / 10;
		sql = "INSERT INTO dashang(id,tiaomu_id,my_id,friend_id,money,time,fenlei_Tag) VALUES('" + id + "','"
				+ tiaomu_id + "','" + my_id + "','" + duixiang_id + "'," + _money + ",'" + time + "'," + fenlei_Tag
				+ ")";
		db1 = new DBHelper(sql);
		try {
			int ret = db1.pst.executeUpdate();
			getDashang(money, duixiang_id);
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "谢谢打赏";
	}

	public static com.magicmoble.luzhouapp.model.Dashang_click Dashang(String _tiaomu_id, String _my_id,
			String releaser_id) {
		List<Dashang_list> dashang_lists = new ArrayList<Dashang_list>();
		String sql = null;
		int fenlei_Tag = 0;
		com.magicmoble.luzhouapp.model.Dashang_click click = null;
		DBHelper db1 = null;
		sql = "select * from dashang where tiaomu_id='" + _tiaomu_id + "'";
		db1 = new DBHelper(sql);
		ResultSet ret = null;
		try {
			ret = db1.pst.executeQuery();
			String touxiang = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id).getTouxiang_picture();
			String name = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id).getName();
			String qianming = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id).getQianming();
			while (ret.next()) {

				String id = ret.getString(1);
				String tiaomu_id = ret.getString(2);
				String my_id = ret.getString(3);
				String duixiang_id = ret.getString(4);
				double money = ret.getDouble(5);
				Timestamp time = ret.getTimestamp(6);
				fenlei_Tag = ret.getInt(7);
				String price = money + "元";
				boolean isguanzhu = GuanzhuBusiness.getIsGuanzhu(_my_id, my_id);
				String friend_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(my_id).getName();
				String friend_touxiang = Admin_xinxi_Business.getAdmin_xinxiInfoById(my_id).getTouxiang_picture();

				Dashang_list dashang_list = new Dashang_list();
				dashang_list.setIsguanzhu(isguanzhu);
				dashang_list.setDuixiang_id(duixiang_id);
				dashang_list.setName(friend_name);
				dashang_list.setTouxiang(friend_touxiang);
				dashang_list.setTime(time);
				dashang_list.setPrice(price);
				dashang_list.setTiaomu_id(tiaomu_id);
				dashang_list.setYonghu_id(my_id);

				dashang_lists.add(dashang_list);
			}
			click = new com.magicmoble.luzhouapp.model.Dashang_click();
			Dashang_user dashang_user = new Dashang_user();
			dashang_user.setName(name);
			dashang_user.setTouxiang(touxiang);
			dashang_user.setQianming(qianming);
			dashang_user.setFenlei_Tag(fenlei_Tag);
			dashang_user.setReleaser_id(releaser_id);
			click.setDashang_user(dashang_user);
			click.setDashang_list(dashang_lists);

			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return click;
	}

}
