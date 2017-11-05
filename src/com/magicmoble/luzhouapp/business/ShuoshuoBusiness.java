package com.magicmoble.luzhouapp.business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.util.WeakReferenceMonitor.ReleaseListener;

import com.magicmoble.luzhouapp.model.Admin_xinxi;
import com.magicmoble.luzhouapp.model.Dianzan_Number;
import com.magicmoble.luzhouapp.model.Dianzan_message;
import com.magicmoble.luzhouapp.model.Dianzan_touxiang;
import com.magicmoble.luzhouapp.model.Guanzhu;
import com.magicmoble.luzhouapp.model.Picture;
import com.magicmoble.luzhouapp.model.Shuoshuo;

public class ShuoshuoBusiness {
	public static List<Shuoshuo> getAllShuoshuo(int page, String my_id) throws SQLException {
		List<Shuoshuo> list = new ArrayList<Shuoshuo>();
		String sql = null;
		String sql2 = null;
		DBHelper db1 = null;
		DBHelper db2 = null;
		boolean flag = false;
		List<String> guanzhus = GuanzhuBusiness.getFriend_id(my_id, 0);
		if (guanzhus.size() != 0) {
			sql = "SELECT * FROM shuoshuo where (releaser_id='" + guanzhus.get(0) + "'";
			for (int i = 1; i < guanzhus.size(); i++) {
				sql += " or releaser_id='" + guanzhus.get(i) + "'";
			}
			sql += " or releaser_id='"+my_id+"') order by time desc  limit " + (page - 1) * 12 + "," + 12 * page;
			db1 = new DBHelper(sql);
		}

		sql2 = "SELECT * FROM shuoshuo s,admin_xinxi a WHERE a.id=s.releaser_id AND (a.yonghu_Tag=1 or a.yonghu_Tag=3)  ORDER BY a.yonghu_Tag ASC ,s.time desc LIMIT "
				+ (page-1)*12 + "," + 12 * page;

		ResultSet ret = null;
		ResultSet ret2 = null;

		if (guanzhus.size() != 0) {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				boolean isdianzan;
				String id = ret.getString(1);
				String releaser_id = ret.getString(2);
				Admin_xinxi admin_xinxi = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id);
				String releaser_name = admin_xinxi.getName();
				String releaser_touxiang = admin_xinxi.getTouxiang_picture();
				String qianming = admin_xinxi.getQianming();
				String picture = ret.getString(3);
				if (my_id == null) {
					isdianzan = false;
				} else {
					isdianzan = FunctionBusiness.getDianzan(my_id, id, 2);
				}
				String[] aa = picture.split(",");

				List<Picture> list2 = new ArrayList<Picture>();
				for (int i = 0; i < aa.length; i++) {
					Picture picture_url = new Picture();
					picture_url.setPicture_url(aa[i]);
					list2.add(picture_url);
				}
				String content = ret.getString(4);
				int guanzhu_Tag = FunctionBusiness.getGuanzhu_Tag(my_id, releaser_id);
				if (guanzhu_Tag == 0) {
					flag = false;

				} else if (guanzhu_Tag != 0) {
					flag = true;
				}
				int muban_Tag = ret.getInt(6);

				Timestamp time = ret.getTimestamp(8);
				Timestamp now_time = new Timestamp(new Date().getTime());
				Dianzan_Number dianzan_count = FunctionBusiness.getDianzanNumber(id, 2);

				Shuoshuo shuoshuo = new Shuoshuo();
				shuoshuo.setShuoshuo_id(id);
				shuoshuo.setPicture(list2);
				shuoshuo.setContent(content);
				shuoshuo.setDianzan_count(dianzan_count);
				shuoshuo.setTime(time);
				shuoshuo.setReleaser_id(releaser_id);

				shuoshuo.setReleaser_name(releaser_name);
				shuoshuo.setReleaser_touxiang(releaser_touxiang);
				shuoshuo.setIsguanzhu(flag);
				shuoshuo.setQianming(qianming);
				shuoshuo.setMuban_Tag(muban_Tag);
				shuoshuo.setIsdianzan(isdianzan);
				shuoshuo.setNow_time(now_time);
				list.add(shuoshuo);
			}
		}

		if (list.size() < 12) {
			db2 = new DBHelper(sql2);
			ret2 = db2.pst.executeQuery();
			while (ret2.next()) {
				boolean isdianzan;
				String id = ret2.getString(1);
				String releaser_id = ret2.getString(2);
				String releaser_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id).getName();
				String releaser_touxiang = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id)
						.getTouxiang_picture();
				String qianming = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id).getQianming();
				String picture = ret2.getString(3);
				String[] aa = picture.split(",");

				List<Picture> list2 = new ArrayList<Picture>();
				for (int i = 0; i < aa.length; i++) {
					Picture picture_url = new Picture();
					picture_url.setPicture_url(aa[i]);
					list2.add(picture_url);
				}

				String content = ret2.getString(4);
				int guanzhu_Tag = FunctionBusiness.getGuanzhu_Tag(my_id, releaser_id);
				if (guanzhu_Tag == 0) {
					flag = false;

				} else if (guanzhu_Tag != 0) {
					flag = true;
				}
				int muban_Tag = ret2.getInt(6);
				if (my_id == null) {
					isdianzan = false;
				} else {
					isdianzan = FunctionBusiness.getDianzan(my_id, id, 2);
				}

				Timestamp time = ret2.getTimestamp(8);
				Timestamp now_time = new Timestamp(new Date().getTime());
				Dianzan_Number dianzan_count = FunctionBusiness.getDianzanNumber(id, 2);

				Shuoshuo shuoshuo = new Shuoshuo();
				shuoshuo.setShuoshuo_id(id);
				shuoshuo.setPicture(list2);
				shuoshuo.setContent(content);
				shuoshuo.setDianzan_count(dianzan_count);
				shuoshuo.setTime(time);
				shuoshuo.setReleaser_id(releaser_id);
				shuoshuo.setReleaser_name(releaser_name);
				shuoshuo.setReleaser_touxiang(releaser_touxiang);
				shuoshuo.setIsguanzhu(flag);
				shuoshuo.setQianming(qianming);
				shuoshuo.setMuban_Tag(muban_Tag);
				shuoshuo.setNow_time(now_time);
				shuoshuo.setIsdianzan(isdianzan);
				list.add(shuoshuo);
				if (list.size() == 12) {
					break;
				}

			}
		}

		if (guanzhus.size() != 0) {
			ret.close();
			db1.close();
		}
		if (list.size() < 12) {
			ret2.close();
			db2.close();

		}

		return list;
	}

	public static List<Shuoshuo> getAllShuoshuo(int page) {
		List<Shuoshuo> list = new ArrayList<Shuoshuo>();
		String sql = null;
		DBHelper db1 = null;
		boolean flag = false;
		sql = "SELECT * FROM shuoshuo s,admin_xinxi a WHERE a.id=s.releaser_id AND (yonghu_Tag=1 or yonghu_Tag=3) ORDER BY a.yonghu_Tag ASC ,s.time desc LIMIT "
				+ (page - 1) * 10 + "," + 10 * page;
		db1 = new DBHelper(sql);
		ResultSet ret = null;
		try {

			ret = db1.pst.executeQuery();
			while (ret.next()) {

				String id = ret.getString(1);
				String releaser_id = ret.getString(2);
				Admin_xinxi admin_xinxi = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id);
				String releaser_name = admin_xinxi.getName();
				String releaser_touxiang = admin_xinxi.getTouxiang_picture();
				String qianming = admin_xinxi.getQianming();
				String picture = ret.getString(3);
				String[] aa = picture.split(",");

				List<Picture> list2 = new ArrayList<Picture>();
				for (int i = 0; i < aa.length; i++) {
					Picture picture_url = new Picture();
					picture_url.setPicture_url(aa[i]);
					list2.add(picture_url);
				}
				String content = ret.getString(4);

				int guanzhu_Tag = 0;
				if (guanzhu_Tag == 0) {
					flag = false;
				} else if (guanzhu_Tag != 0) {
					flag = true;
				}
				int muban_Tag = ret.getInt(6);

				Timestamp time = ret.getTimestamp(8);
				Timestamp now_time = new Timestamp(new Date().getTime());
				Dianzan_Number dianzan_count = FunctionBusiness.getDianzanNumber(id, 2);

				Shuoshuo shuoshuo = new Shuoshuo();
				shuoshuo.setShuoshuo_id(id);
				shuoshuo.setPicture(list2);
				shuoshuo.setContent(content);
				shuoshuo.setDianzan_count(dianzan_count);
				shuoshuo.setTime(time);
				shuoshuo.setReleaser_id(releaser_id);
				shuoshuo.setReleaser_name(releaser_name);
				shuoshuo.setReleaser_touxiang(releaser_touxiang);
				shuoshuo.setIsguanzhu(flag);
				shuoshuo.setQianming(qianming);
				shuoshuo.setMuban_Tag(muban_Tag);
				shuoshuo.setNow_time(now_time);

				list.add(shuoshuo);
			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void shuoshuo_yuedu(String _id) {
		DBHelper db2 = null;
		String sql1 = null;
		sql1 = "UPDATE shuoshuo SET yuedu_count=yuedu_count+1 WHERE id='" + _id + "'";
		db2 = new DBHelper(sql1);
		int ret2;
		try {
			ret2 = db2.pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		db2.close();

	}

	public static void addShuoshuo(String picture, String content, String releaser_id, int muban_Tag) {
		String sql = null;
		DBHelper db1 = null;
		String uuid = UUID.randomUUID().toString();
		int guanzhu_Tag = 0;
		int dianzan_count = 0;
		Timestamp timestamp = new Timestamp(new Date().getTime());

		sql = "INSERT INTO shuoshuo(id,picture,content,dianzan_count,time,guanzhu_Tag,releaser_id,muban_Tag) VALUES ('"
				+ uuid + "', '" + picture + "', '" + content + "', '" + dianzan_count + "', '" + timestamp + "', '"
				+ guanzhu_Tag + "', '" + releaser_id + "', '" + muban_Tag + "')";

		try {
			db1 = new DBHelper(sql);
			boolean ret = db1.pst.execute();

			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void DeleteShuoshuo(String id) {
		{
			String sql = null;
			DBHelper db1 = null;
			sql = "DELETE FROM shuoshuo WHERE id =  '" + id + "'";
			db1 = new DBHelper(sql);
			try {
				boolean ret = db1.pst.execute();
				db1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public static void UpdateShuoshuo(String key, String value, String id) {
		{
			String sql = null;
			DBHelper db1 = null;
			sql = "UPDATE shuoshuo SET " + key + " = '" + value + "' WHERE id = '" + id + "'";
			db1 = new DBHelper(sql);
			try {
				boolean ret = db1.pst.execute();
				db1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
}
