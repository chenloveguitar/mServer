package com.magicmoble.luzhouapp.server.server_function;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.magicmoble.luzhouapp.business.Admin_xinxi_Business;
import com.magicmoble.luzhouapp.business.CommodityBusiness;
import com.magicmoble.luzhouapp.business.DBHelper;
import com.magicmoble.luzhouapp.business.FaxianBusiness;
import com.magicmoble.luzhouapp.business.FuwuBusiness;
import com.magicmoble.luzhouapp.business.GuanzhuBusiness;
import com.magicmoble.luzhouapp.business.QuchuBusiness;
import com.magicmoble.luzhouapp.business.ToutiaoBusiness;
import com.magicmoble.luzhouapp.model.Admin_xinxi;
import com.magicmoble.luzhouapp.model.Faxian_Xiangqing;
import com.magicmoble.luzhouapp.model.Guangjie_Xiangqing;
import com.magicmoble.luzhouapp.model.Picture;
import com.magicmoble.luzhouapp.model.Quchu_Xiangqing;
import com.magicmoble.luzhouapp.model.Toutiao_Xiangqing;
import com.magicmoble.luzhouapp.model.server.Admin;
import com.magicmoble.luzhouapp.model.server.Home_model;
import com.magicmoble.luzhouapp.model.server.Login;
import com.magicmoble.luzhouapp.model.server.Pinglun_model;
import com.magicmoble.luzhouapp.model.server.Shuoshuo;
import com.magicmoble.luzhouapp.model.server.Toutiao;
import com.magicmoble.luzhouapp.model.server.User_model;

public class Server_Function {
	public static Integer PAGE_SIZE = 5;//每页显示条数
	public static Integer TOTAL_SIZE = 0;//总条数
	public static Integer CURRENT_PAGE = 0;//当前页
	public static Integer TOTAL_PAGE = 0;//总页数
	public static Home_model get_home1() {
		Timestamp time = new Timestamp(new Date().getTime());
		String sql1 = "SELECT count(id) FROM toutiao WHERE shenhe='正在审核中...'";
		String sql2 = "SELECT count(id) FROM faxian WHERE shenhe='正在审核中...'";
		String sql3 = "SELECT count(id) FROM commodity WHERE shenhe='正在审核中...'";
		String sql4 = "SELECT count(id) FROM fuwu WHERE shenhe='正在审核中...'";
		String sql5 = "SELECT count(id) FROM quchu WHERE shenhe='正在审核中...'";
		String sql6 = "SELECT count(id) FROM dingdan WHERE  see_time < now_time and guangjie_fenlei_Tag=0";
		String sql7 = "SELECT count(id) FROM dingdan WHERE  see_time < now_time and guangjie_fenlei_Tag=1";
		String sql8 = "SELECT count(id) FROM dingdan WHERE  see_time < now_time and guangjie_fenlei_Tag=2";
		String sql9 = "SELECT count(id) FROM pinglun WHERE  see_time < now_time";
		String sql10 = "SELECT count(id) FROM shuoshuo WHERE  see_time < now_time";
		String sql11 = "SELECT count(id) FROM jubao WHERE  see_time < now_time";
		String sql12 = "SELECT count(id) FROM renzheng WHERE  see_time < now_time and leixing_Tag=1";// 1个人认证
		String sql13 = "SELECT count(id) FROM renzheng WHERE  see_time < now_time and leixing_Tag=2";// 2店铺认证
		String sql14 = "SELECT count(id) FROM tixian_list WHERE  see_time < now_time";
		// String sql15 = "";
		Home_model home_model = new Home_model();

		DBHelper db1 = null;
		ResultSet ret1 = null;
		int count;
		try {
			db1 = new DBHelper(sql1);
			ret1 = db1.pst.executeQuery();
			while (ret1.next()) {
				count = ret1.getInt(1);
				home_model.setToutiao_No_Release(count);
			}
			db1 = new DBHelper(sql2);
			ret1 = db1.pst.executeQuery();
			while (ret1.next()) {
				count = ret1.getInt(1);
				home_model.setFaxian_No_Release(count);
				;
			}
			db1 = new DBHelper(sql3);
			ret1 = db1.pst.executeQuery();
			while (ret1.next()) {
				count = ret1.getInt(1);
				home_model.setCommodity_No_Release(count);
			}
			db1 = new DBHelper(sql4);
			ret1 = db1.pst.executeQuery();
			while (ret1.next()) {
				count = ret1.getInt(1);
				home_model.setFuwu_No_Release(count);
			}
			db1 = new DBHelper(sql5);
			ret1 = db1.pst.executeQuery();
			while (ret1.next()) {
				count = ret1.getInt(1);
				home_model.setQuchu_No_Release(count);
			}
			db1 = new DBHelper(sql6);
			ret1 = db1.pst.executeQuery();
			while (ret1.next()) {
				count = ret1.getInt(1);
				home_model.setTuijian_dingdan(count);
			}
			db1 = new DBHelper(sql7);
			ret1 = db1.pst.executeQuery();
			while (ret1.next()) {
				count = ret1.getInt(1);
				home_model.setCommodity_dingdan(count);
			}
			db1 = new DBHelper(sql8);
			ret1 = db1.pst.executeQuery();
			while (ret1.next()) {
				count = ret1.getInt(1);
				home_model.setFuwu_dingdan(count);
			}
			db1 = new DBHelper(sql9);
			ret1 = db1.pst.executeQuery();
			while (ret1.next()) {
				count = ret1.getInt(1);
				home_model.setPinglun_NoSee(count);
			}
			db1 = new DBHelper(sql10);
			ret1 = db1.pst.executeQuery();
			while (ret1.next()) {
				count = ret1.getInt(1);
				home_model.setShuoshuo_NoSee(count);
			}
			db1 = new DBHelper(sql11);
			ret1 = db1.pst.executeQuery();
			while (ret1.next()) {
				count = ret1.getInt(1);
				home_model.setJubao_NoSee(count);
			}
			db1 = new DBHelper(sql12);
			ret1 = db1.pst.executeQuery();
			while (ret1.next()) {
				count = ret1.getInt(1);
				home_model.setPersonal_certification(count);
			}
			db1 = new DBHelper(sql13);
			ret1 = db1.pst.executeQuery();
			while (ret1.next()) {
				count = ret1.getInt(1);
				home_model.setStore_certification(count);
			}
			db1 = new DBHelper(sql14);
			ret1 = db1.pst.executeQuery();
			while (ret1.next()) {
				count = ret1.getInt(1);
				home_model.setTixian_Untreated(count);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return home_model;

	}

	public static List<Shuoshuo> getShuoshuo_ser() {

		String sql = "SELECT picture,yuedu,content,share_count,releaser_id,time,tuijian_Tag FROM shuoshuo ";

		DBHelper db1 = null;
		ResultSet ret = null;

		List<Shuoshuo> list = new ArrayList<Shuoshuo>();
		try {
			db1 = new DBHelper(sql);
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				String picture = ret.getString(1);

				String[] aa = picture.split(",");

				List<Picture> list2 = new ArrayList<Picture>();
				for (int i = 0; i < aa.length; i++) {
					Picture picture_url = new Picture();
					picture_url.setPicture_url(aa[i]);
					list2.add(picture_url);
				}
				int yuedu_count = ret.getInt(2);
				String content = ret.getString(3);
				int share_count = ret.getInt(4);
				String releaser_id = ret.getString(5);
				Admin_xinxi admin_xinxi = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id);
				String touxiang = admin_xinxi.getTouxiang_picture();
				String name = admin_xinxi.getName();
				String qianming = admin_xinxi.getQianming();
				Timestamp time = ret.getTimestamp(6);
				int tuijiang_Tag = ret.getInt(7);
				String tuijian_message = null;
				if (tuijiang_Tag == 0) {
					tuijian_message = "未推荐";
				} else {
					tuijian_message = "已推荐";
				}

				Shuoshuo shuoshuo = new Shuoshuo();
				shuoshuo.setContent(content);
				shuoshuo.setName(name);
				shuoshuo.setPictures(list2);
				shuoshuo.setQianming(qianming);
				shuoshuo.setShare_count(share_count);
				shuoshuo.setTime(time);
				shuoshuo.setTouxiang(touxiang);
				shuoshuo.setTuijian_message(tuijian_message);
				shuoshuo.setYuedu_count(yuedu_count);

				list.add(shuoshuo);
			}
			db1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	public static List<Shuoshuo> changeShuoshuo_ser(String value) {

		String sql = "SELECT picture,yuedu,content,share_count,releaser_id,time,tuijian_Tag,id FROM shuoshuo where tuijian_Tag=?";

		DBHelper db1 = null;
		ResultSet ret = null;

		List<Shuoshuo> list = new ArrayList<Shuoshuo>();
		try {
			db1 = new DBHelper(sql);
			if (value != null) {
				if (value.equals("已推荐")) {
					db1.pst.setInt(1, 1);
					ret = db1.pst.executeQuery();
				} else if (value.equals("未推荐")) {
					db1.pst.setInt(1, 0);
					ret = db1.pst.executeQuery();
				}
			}
			while (ret.next()) {
				String picture = ret.getString(1);

				String[] aa = picture.split(",");

				List<Picture> list2 = new ArrayList<Picture>();
				for (int i = 0; i < aa.length; i++) {
					Picture picture_url = new Picture();
					picture_url.setPicture_url(aa[i]);
					list2.add(picture_url);
				}
				int yuedu_count = ret.getInt(2);
				String content = ret.getString(3);
				int share_count = ret.getInt(4);
				String releaser_id = ret.getString(5);
				Admin_xinxi admin_xinxi = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id);
				String touxiang = admin_xinxi.getTouxiang_picture();
				String name = admin_xinxi.getName();
				String qianming = admin_xinxi.getQianming();
				Timestamp time = ret.getTimestamp(6);
				int tuijiang_Tag = ret.getInt(7);
				String tuijian_message = null;
				if (tuijiang_Tag == 0) {
					tuijian_message = "未推荐";
				} else {
					tuijian_message = "已推荐";
				}
				String id = ret.getString(8);

				Shuoshuo shuoshuo = new Shuoshuo();
				shuoshuo.setContent(content);
				shuoshuo.setName(name);
				shuoshuo.setPictures(list2);
				shuoshuo.setQianming(qianming);
				shuoshuo.setShare_count(share_count);
				shuoshuo.setTime(time);
				shuoshuo.setTouxiang(touxiang);
				shuoshuo.setTuijian_message(tuijian_message);
				shuoshuo.setYuedu_count(yuedu_count);
				shuoshuo.setId(id);
				list.add(shuoshuo);
				System.out.println("list:" + list.size());
			}
			db1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	public static List<Shuoshuo> limitShuoshuo_ser(int pageno, int pagecount) {

		String sql = "SELECT picture,yuedu,content,share_count,releaser_id,time,tuijian_Tag FROM shuoshuo limit ?,?";

		DBHelper db1 = null;
		ResultSet ret = null;

		List<Shuoshuo> list = new ArrayList<Shuoshuo>();
		try {
			// 记录开始位置
			int begin = (pageno - 1) * pagecount;
			db1 = new DBHelper(sql);
			db1.pst.setInt(1, begin);
			db1.pst.setInt(2, pagecount);
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				String picture = ret.getString(1);

				String[] aa = picture.split(",");

				List<Picture> list2 = new ArrayList<Picture>();
				for (int i = 0; i < aa.length; i++) {
					Picture picture_url = new Picture();
					picture_url.setPicture_url(aa[i]);
					list2.add(picture_url);
				}
				int yuedu_count = ret.getInt(2);
				String content = ret.getString(3);
				int share_count = ret.getInt(4);
				String releaser_id = ret.getString(5);
				Admin_xinxi admin_xinxi = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id);
				String touxiang = admin_xinxi.getTouxiang_picture();
				String name = admin_xinxi.getName();
				String qianming = admin_xinxi.getQianming();
				Timestamp time = ret.getTimestamp(6);
				int tuijiang_Tag = ret.getInt(7);
				String tuijian_message = null;
				if (tuijiang_Tag == 0) {
					tuijian_message = "未推荐";
				} else {
					tuijian_message = "已推荐";
				}
				// 包含id的说说实体类
				Shuoshuo shuoshuo = new Shuoshuo();
				shuoshuo.setContent(content);
				shuoshuo.setName(name);
				shuoshuo.setPictures(list2);
				shuoshuo.setQianming(qianming);
				shuoshuo.setShare_count(share_count);
				shuoshuo.setTime(time);
				shuoshuo.setTouxiang(touxiang);
				shuoshuo.setTuijian_message(tuijian_message);
				shuoshuo.setYuedu_count(yuedu_count);
				list.add(shuoshuo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return list;

	}

	public static int getTotalPage(int pagecount) {
		String sql = "SELECT count(id) as totalcount from shuoshuo";
		DBHelper db1 = null;
		ResultSet ret = null;
		int totalcount = 0;
		db1 = new DBHelper(sql);
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				totalcount = ret.getInt("totalcount");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}

		// totalpage
		int totalpage = -1;

		if (totalcount > 0) {
			if (totalcount % pagecount == 0) {
				totalpage = totalcount / pagecount;
			} else {
				totalpage = totalcount / pagecount + 1;
			}
		} else {
			totalpage = 0;
		}

		return totalpage;
	}

	public static List<Shuoshuo> search(String search_str) {

		String sql;
		if (search_str.equals("关键字")) {
			sql = "SELECT picture,yuedu,content,share_count,releaser_id,time,tuijian_Tag,id FROM shuoshuo ";
		} else {
			sql = "SELECT picture,yuedu,content,share_count,releaser_id,time,tuijian_Tag,id FROM shuoshuo where content  like '%"
					+ search_str + "%'";
		}

		DBHelper db1 = null;
		ResultSet ret = null;

		List<Shuoshuo> list = new ArrayList<Shuoshuo>();
		try {
			db1 = new DBHelper(sql);

			ret = db1.pst.executeQuery();

			while (ret.next()) {
				String picture = ret.getString(1);

				String[] aa = picture.split(",");

				List<Picture> list2 = new ArrayList<Picture>();
				for (int i = 0; i < aa.length; i++) {
					Picture picture_url = new Picture();
					picture_url.setPicture_url(aa[i]);
					list2.add(picture_url);
				}
				int yuedu_count = ret.getInt(2);
				String content = ret.getString(3);
				int share_count = ret.getInt(4);
				String releaser_id = ret.getString(5);
				Admin_xinxi admin_xinxi = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id);
				String touxiang = admin_xinxi.getTouxiang_picture();
				String name = admin_xinxi.getName();
				String qianming = admin_xinxi.getQianming();
				Timestamp time = ret.getTimestamp(6);
				int tuijiang_Tag = ret.getInt(7);
				String tuijian_message = null;
				if (tuijiang_Tag == 0) {
					tuijian_message = "未推荐";
				} else {
					tuijian_message = "已推荐";
				}
				String id = ret.getString(8);

				Shuoshuo shuoshuo = new Shuoshuo();
				shuoshuo.setContent(content);
				shuoshuo.setName(name);
				shuoshuo.setPictures(list2);
				shuoshuo.setQianming(qianming);
				shuoshuo.setShare_count(share_count);
				shuoshuo.setTime(time);
				shuoshuo.setTouxiang(touxiang);
				shuoshuo.setTuijian_message(tuijian_message);
				shuoshuo.setYuedu_count(yuedu_count);
				shuoshuo.setId(id);
				list.add(shuoshuo);
				System.out.println("list:" + list.size());
			}
			db1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	public static List<Shuoshuo> sort(String sort_rec, int page) {
		String sql = null;
		if (sort_rec.equals("阅读量从高到低")) {
			sql = "SELECT picture,yuedu,content,share_count,releaser_id,time,tuijian_Tag,id FROM shuoshuo order by yuedu desc LIMIT "
					+ (page - 1) * 15 + "," + 15 * page;
		} else if (sort_rec.equals("阅读量从低到高")) {
			sql = "SELECT picture,yuedu,content,share_count,releaser_id,time,tuijian_Tag,id FROM shuoshuo order by yuedu asc  LIMIT "
					+ (page - 1) * 15 + "," + 15 * page;
		} else if (sort_rec.equals("点赞量从高到低")) {
			sql = "SELECT picture,yuedu,content,share_count,releaser_id,time,tuijian_Tag,id FROM shuoshuo order by dianzan_count desc  LIMIT "
					+ (page - 1) * 15 + "," + 15 * page;
		} else if (sort_rec.equals("点赞量从低到高")) {
			sql = "SELECT picture,yuedu,content,share_count,releaser_id,time,tuijian_Tag,id FROM shuoshuo order by dianzan_count asc  LIMIT "
					+ (page - 1) * 15 + "," + 15 * page;
		} else if (sort_rec.equals("日期从晚到早")) {
			sql = "SELECT picture,yuedu,content,share_count,releaser_id,time,tuijian_Tag,id FROM shuoshuo order by time desc LIMIT "
					+ (page - 1) * 15 + "," + 15 * page;
		} else if (sort_rec.equals("日期从早到晚")) {
			sql = "SELECT picture,yuedu,content,share_count,releaser_id,time,tuijian_Tag,id FROM shuoshuo order by time asc LIMIT "
					+ (page - 1) * 15 + "," + 15 * page;
		}

		DBHelper db1 = null;
		ResultSet ret = null;

		List<Shuoshuo> list = new ArrayList<Shuoshuo>();
		try {
			db1 = new DBHelper(sql);

			ret = db1.pst.executeQuery();

			while (ret.next()) {
				String picture = ret.getString(1);

				String[] aa = picture.split(",");

				List<Picture> list2 = new ArrayList<Picture>();
				for (int i = 0; i < aa.length; i++) {
					Picture picture_url = new Picture();
					picture_url.setPicture_url(aa[i]);
					list2.add(picture_url);
				}
				int yuedu_count = ret.getInt(2);
				String content = ret.getString(3);
				int share_count = ret.getInt(4);
				String releaser_id = ret.getString(5);
				Admin_xinxi admin_xinxi = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id);
				String touxiang = admin_xinxi.getTouxiang_picture();
				String name = admin_xinxi.getName();
				String qianming = admin_xinxi.getQianming();
				Timestamp time = ret.getTimestamp(6);
				int tuijiang_Tag = ret.getInt(7);
				String tuijian_message = null;
				if (tuijiang_Tag == 0) {
					tuijian_message = "未推荐";
				} else {
					tuijian_message = "已推荐";
				}
				String id = ret.getString(8);

				Shuoshuo shuoshuo = new Shuoshuo();
				shuoshuo.setContent(content);
				shuoshuo.setName(name);
				shuoshuo.setPictures(list2);
				shuoshuo.setQianming(qianming);
				shuoshuo.setShare_count(share_count);
				shuoshuo.setTime(time);
				shuoshuo.setTouxiang(touxiang);
				shuoshuo.setTuijian_message(tuijian_message);
				shuoshuo.setYuedu_count(yuedu_count);
				shuoshuo.setId(id);
				list.add(shuoshuo);
				System.out.println("list:" + list.size());
			}
			db1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	public static List<Toutiao> NoExamine(String change_rec, int flag) {
		String sql = null;
		DBHelper db1 = null;
		int days = 0;

		List<Toutiao> list = new ArrayList<Toutiao>();
		try {
			if (flag == 1) {
				if (change_rec != null) {
					if (change_rec.equals("已推荐")) {
						sql = "select DISTINCT * FROM (SELECT id,picture,title,yuedu_count,releaser_id,time FROM toutiao UNION all SELECT id,picture,title,yuedu_count,releaser_id,time FROM faxian UNION all SELECT id,picture,title,yuedu ,releaser_id,time FROM quchu UNION all SELECT id,picture,title,yuedu ,releaser_id,time FROM commodity UNION all SELECT id,picture,title,yuedu ,releaser_id,time FROM fuwu) a LEFT OUTER JOIN  (SELECT tiaomu_id from tuijian_list) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS not null";
					} else if (change_rec.equals("未推荐")) {
						sql = "select DISTINCT * FROM (SELECT id,picture,title,yuedu_count,releaser_id,time FROM toutiao UNION all SELECT id,picture,title,yuedu_count,releaser_id,time FROM faxian UNION all SELECT id,picture,title,yuedu ,releaser_id,time FROM quchu UNION all SELECT id,picture,title,yuedu ,releaser_id,time FROM commodity UNION all SELECT id,picture,title,yuedu ,releaser_id,time FROM fuwu) a LEFT OUTER JOIN  (SELECT tiaomu_id from tuijian_list) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS null";
					}
				}
			} else if (flag == 2) {
				if (change_rec != null) {
					if (change_rec.equals("已推荐")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu_count,releaser_id,time from toutiao  where fenlei_Tag=1) a LEFT OUTER JOIN  (SELECT tiaomu_id from tuijian_list) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS not null";
					} else if (change_rec.equals("未推荐")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu_count,releaser_id,time from toutiao where fenlei_Tag=1) a LEFT OUTER JOIN  (SELECT tiaomu_id from tuijian_list) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS null";
					}
				}
			} else if (flag == 3) {
				if (change_rec != null) {
					if (change_rec.equals("已推荐")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu_count,releaser_id,time from toutiao where fenlei_Tag=2) a LEFT OUTER JOIN  (SELECT tiaomu_id from tuijian_list) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS not null";
					} else if (change_rec.equals("未推荐")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu_count,releaser_id,time from toutiao where fenlei_Tag=2) a LEFT OUTER JOIN  (SELECT tiaomu_id from tuijian_list) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS null";
					}
				}
			} else if (flag == 4) {
				if (change_rec != null) {
					if (change_rec.equals("已推荐")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu_count,releaser_id,time from toutiao where fenlei_Tag=3) a LEFT OUTER JOIN  (SELECT tiaomu_id from tuijian_list) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS not null";
					} else if (change_rec.equals("未推荐")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu_count,releaser_id,time from toutiao where fenlei_Tag=3) a LEFT OUTER JOIN  (SELECT tiaomu_id from tuijian_list) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS null";
					}
				}
			} else if (flag == 5) {
				if (change_rec != null) {
					if (change_rec.equals("已推荐")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu,releaser_id,time from faxian ) a LEFT OUTER JOIN  (SELECT tiaomu_id from tuijian_list) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS not null";
					} else if (change_rec.equals("未推荐")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu,releaser_id,time from faxian ) a LEFT OUTER JOIN  (SELECT tiaomu_id from tuijian_list) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS null";
					}
				}
			} else if (flag == 6) {
				if (change_rec != null) {
					if (change_rec.equals("已推荐")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu,releaser_id,time from quchu ) a LEFT OUTER JOIN  (SELECT tiaomu_id from tuijian_list) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS not null";
					} else if (change_rec.equals("未推荐")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu,releaser_id,time from quchu ) a LEFT OUTER JOIN  (SELECT tiaomu_id from tuijian_list) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS  null";
					}
				}
			} else if (flag == 7) {
				if (change_rec != null) {
					if (change_rec.equals("已推荐")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu,releaser_id,time from commodity union select id,picture,title,yuedu,releaser_id,time from fuwu ) a LEFT OUTER JOIN  (SELECT tiaomu_id from tuijian_list) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS not null";
					} else if (change_rec.equals("未推荐")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu,releaser_id,time from commodity union select id,picture,title,yuedu,releaser_id,time from fuwu ) a LEFT OUTER JOIN  (SELECT tiaomu_id from tuijian_list) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS null";
					}
				}
			}
			TOTAL_SIZE = getTotalSize(sql);
			sql = getLimitSql(sql);
			db1 = new DBHelper(sql);
			TOTAL_PAGE = (TOTAL_SIZE + PAGE_SIZE -1) /  PAGE_SIZE;
			ResultSet ret = db1.pst.executeQuery();
			while (ret.next()) {
				String id = ret.getString(1);
				String picture = ret.getString(2);
				String[] aa = picture.split(",");
				List<Picture> list2 = new ArrayList<Picture>();
				for (int i = 0; i < aa.length; i++) {
					Picture picture_url = new Picture();
					picture_url.setPicture_url(aa[i]);
					list2.add(picture_url);
				}
				String title = ret.getString(3);
				int yuedu = ret.getInt(4);
				String releaser_id = ret.getString(5);
				String releaser_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id).getName();
				String releaser_touxiang = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id)
						.getTouxiang_picture();
				int shoucang_count = Server_Func.getshoucang(id);
				Timestamp time = ret.getTimestamp(6);

				days = Server_Func.tuijian_days(id);
				String tuijian_message;

				if (days == 0) {
					tuijian_message = "未推荐";
				} else {
					tuijian_message = "已推荐";
				}
				String tuijian_Tag;
				if (tuijian_message.equals("已推荐")) {
					tuijian_Tag = "首页推荐";
				} else {
					tuijian_Tag = "未推荐";
				}

				Toutiao toutiao = new Toutiao();
				toutiao.setId(id);
				toutiao.setPictures(list2);
				toutiao.setTitle(title);
				toutiao.setYuedu_count(yuedu);
				toutiao.setReleaser_name(releaser_name);
				toutiao.setReleaser_touxiang(releaser_touxiang);
				toutiao.setDays(days);
				toutiao.setShare_count(0);
				toutiao.setShoucang_count(shoucang_count);
				toutiao.setTime(time);
				toutiao.setTuijian_message(tuijian_message);
				list.add(toutiao);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}
		
		return list;
	}

	public static String getLimitSql(String sql){
		String limitSql = "SELECT results.* FROM (" + sql +") as results limit "+(CURRENT_PAGE - 1) * PAGE_SIZE+" ," +PAGE_SIZE;
		return limitSql;
	}
	
	
	public static Integer getTotalSize(String sql){
		StringBuilder countSql = new StringBuilder(" select count(results.id) from (");
		countSql.append(sql);
		countSql.append(") as results ");
		DBHelper db1 = new DBHelper(countSql.toString());
		try {
			ResultSet resultSet = db1.pst.executeQuery();
			if(resultSet.next()){
				return resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static List<Toutiao> NoExamine_hongbao(String change_rec, int flag) {
		String sql = null;
		DBHelper db1 = null;
		int days = 0;
		
		List<Toutiao> list = new ArrayList<Toutiao>();
		try {
			if (flag == 1) {
				if (change_rec != null) {
					if (change_rec.equals("还有余额")) {
						sql = "select DISTINCT * FROM (SELECT id,picture,title,yuedu_count,releaser_id,time FROM toutiao UNION all SELECT id,picture,title,yuedu_count,releaser_id,time FROM faxian UNION all SELECT id,picture,title,yuedu ,releaser_id,time FROM quchu UNION all SELECT id,picture,title,yuedu ,releaser_id,time FROM commodity UNION all SELECT id,picture,title,yuedu ,releaser_id,time FROM fuwu) a LEFT OUTER JOIN  (SELECT tiaomu_id from hongbao where hongbao_count!=0) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS not null";
					} else if (change_rec.equals("余额用尽")) {
						sql = "select DISTINCT * FROM (SELECT id,picture,title,yuedu_count,releaser_id,time FROM toutiao UNION all SELECT id,picture,title,yuedu_count,releaser_id,time FROM faxian UNION all SELECT id,picture,title,yuedu ,releaser_id,time FROM quchu UNION all SELECT id,picture,title,yuedu ,releaser_id,time FROM commodity UNION all SELECT id,picture,title,yuedu ,releaser_id,time FROM fuwu) a LEFT OUTER JOIN  (SELECT tiaomu_id from hongbao where hongbao_count=0) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS not null";
					} else if (change_rec.equals("没有红包")) {
						sql = "select DISTINCT * FROM (SELECT id,picture,title,yuedu_count,releaser_id,time FROM toutiao UNION all SELECT id,picture,title,yuedu_count,releaser_id,time FROM faxian UNION all SELECT id,picture,title,yuedu ,releaser_id,time FROM quchu UNION all SELECT id,picture,title,yuedu ,releaser_id,time FROM commodity UNION all SELECT id,picture,title,yuedu ,releaser_id,time FROM fuwu) a LEFT OUTER JOIN  (SELECT tiaomu_id from hongbao ) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS null";
					}
				}
			} else if (flag == 2) {
				if (change_rec != null) {
					if (change_rec.equals("还有余额")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu_count,releaser_id,time from toutiao  where fenlei_Tag=1) a LEFT OUTER JOIN  (SELECT tiaomu_id from  hongbao where hongbao_count!=0 ) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS not null";
					} else if (change_rec.equals("余额用尽")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu_count,releaser_id,time from toutiao where fenlei_Tag=1) a LEFT OUTER JOIN  (SELECT tiaomu_id from  hongbao where hongbao_count=0) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS not null";
					} else if (change_rec.equals("没有红包")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu_count,releaser_id,time from toutiao where fenlei_Tag=1) a LEFT OUTER JOIN  (SELECT tiaomu_id from  hongbao) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS null";
					}
				}
			} else if (flag == 3) {
				if (change_rec != null) {
					if (change_rec.equals("还有余额")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu_count,releaser_id,time from toutiao where fenlei_Tag=2) a LEFT OUTER JOIN  (SELECT tiaomu_id from hongbao where hongbao_count!=0) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS not null";
					} else if (change_rec.equals("余额用尽")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu_count,releaser_id,time from toutiao where fenlei_Tag=2) a LEFT OUTER JOIN  (SELECT tiaomu_id from hongbao where hongbao_count=0) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS not null";
					} else if (change_rec.equals("没有红包")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu_count,releaser_id,time from toutiao where fenlei_Tag=2) a LEFT OUTER JOIN  (SELECT tiaomu_id from hongbao ) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS null";
					}
				}
			} else if (flag == 4) {
				if (change_rec != null) {
					if (change_rec.equals("还有余额")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu_count,releaser_id,time from toutiao where fenlei_Tag=3) a LEFT OUTER JOIN  (SELECT tiaomu_id from hongbao where hongbao_count!=0) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS not null";
					} else if (change_rec.equals("余额用尽")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu_count,releaser_id,time from toutiao where fenlei_Tag=3) a LEFT OUTER JOIN  (SELECT tiaomu_id from hongbao where hongbao_count=0) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS  not null";
					} else if (change_rec.equals("没有红包")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu_count,releaser_id,time from toutiao where fenlei_Tag=3) a LEFT OUTER JOIN  (SELECT tiaomu_id from hongbao ) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS null";
					}
				}
			} else if (flag == 5) {
				if (change_rec != null) {
					if (change_rec.equals("还有余额")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu,releaser_id,time from faxian ) a LEFT OUTER JOIN  (SELECT tiaomu_id from hongbao where hongbao_count!=0) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS not null";
					} else if (change_rec.equals("余额用尽")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu,releaser_id,time from faxian ) a LEFT OUTER JOIN  (SELECT tiaomu_id from hongbao where hongbao_count=0) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS  not null";
					} else if (change_rec.equals("没有红包")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu,releaser_id,time from faxian ) a LEFT OUTER JOIN  (SELECT tiaomu_id from hongbao) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS null";
					}
				}
			} else if (flag == 6) {
				if (change_rec != null) {
					if (change_rec.equals("还有余额")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu,releaser_id,time from quchu ) a LEFT OUTER JOIN  (SELECT tiaomu_id from hongbao where hongbao_count!=0) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS not null";
					} else if (change_rec.equals("余额用尽")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu,releaser_id,time from quchu ) a LEFT OUTER JOIN  (SELECT tiaomu_id from hongbao where hongbao_count=0) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS not null";
					} else if (change_rec.equals("没有红包")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu,releaser_id,time from quchu ) a LEFT OUTER JOIN  (SELECT tiaomu_id from hongbao ) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS  null";
					}
				}
			} else if (flag == 7) {
				if (change_rec != null) {
					if (change_rec.equals("还有余额")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu,releaser_id,time from commodity union select id,picture,title,yuedu,releaser_id,time from fuwu ) a LEFT OUTER JOIN  (SELECT tiaomu_id from hongbao where hongbao_count!=0) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS not null";
					} else if (change_rec.equals("余额用尽")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu,releaser_id,time from commodity union select id,picture,title,yuedu,releaser_id,time from fuwu ) a LEFT OUTER JOIN  (SELECT tiaomu_id from hongbao where hongbao_count=0) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS not null";
					} else if (change_rec.equals("没有红包")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu,releaser_id,time from commodity union select id,picture,title,yuedu,releaser_id,time from fuwu ) a LEFT OUTER JOIN  (SELECT tiaomu_id from hongbao ) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS null";
					}
				}
			}
			TOTAL_SIZE = getTotalSize(sql);
			sql = getLimitSql(sql);
			db1 = new DBHelper(sql);
			TOTAL_PAGE = (TOTAL_SIZE + PAGE_SIZE -1) /  PAGE_SIZE;
			ResultSet ret = db1.pst.executeQuery();
			while (ret.next()) {
				String id = ret.getString(1);
				String picture = ret.getString(2);
				String[] aa = picture.split(",");
				List<Picture> list2 = new ArrayList<Picture>();
				for (int i = 0; i < aa.length; i++) {
					Picture picture_url = new Picture();
					picture_url.setPicture_url(aa[i]);
					list2.add(picture_url);
				}
				String title = ret.getString(3);
				int yuedu = ret.getInt(4);
				String releaser_id = ret.getString(5);
				String releaser_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id).getName();
				String releaser_touxiang = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id)
						.getTouxiang_picture();
				int shoucang_count = Server_Func.getshoucang(id);
				Timestamp time = ret.getTimestamp(6);

				days = Server_Func.tuijian_days(id);
				String tuijian_message;

				if (days == 0) {
					tuijian_message = "未推荐";
				} else {
					tuijian_message = "已推荐";
				}
				String tuijian_Tag;
				if (tuijian_message.equals("已推荐")) {
					tuijian_Tag = "首页推荐";
				} else {
					tuijian_Tag = "未推荐";
				}

				Toutiao toutiao = new Toutiao();
				toutiao.setId(id);
				toutiao.setPictures(list2);
				toutiao.setTitle(title);
				toutiao.setYuedu_count(yuedu);
				toutiao.setReleaser_name(releaser_name);
				toutiao.setReleaser_touxiang(releaser_touxiang);
				toutiao.setDays(days);
				toutiao.setShare_count(0);
				toutiao.setShoucang_count(shoucang_count);
				toutiao.setTime(time);
				toutiao.setTuijian_message(tuijian_message);
				list.add(toutiao);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return list;
	}
	
	public static void main(String[] args) {
		
	}

	public static List<Toutiao> NoExamine_dashang(String change_rec, int flag) {
		String sql = null;
		DBHelper db1 = null;
		int days = 0;

		List<Toutiao> list = new ArrayList<Toutiao>();
		try {
			if (flag == 1) {
				if (change_rec != null) {
					if (change_rec.equals("有打赏")) {
						sql = "select DISTINCT * FROM (SELECT id,picture,title,yuedu_count,releaser_id,time FROM toutiao UNION all SELECT id,picture,title,yuedu_count,releaser_id,time FROM faxian UNION all SELECT id,picture,title,yuedu ,releaser_id,time FROM quchu UNION all SELECT id,picture,title,yuedu ,releaser_id,time FROM commodity UNION all SELECT id,picture,title,yuedu ,releaser_id,time FROM fuwu) a LEFT OUTER JOIN  (SELECT tiaomu_id from dashang) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS not null";
					} else if (change_rec.equals("没有打赏")) {
						sql = "select DISTINCT * FROM (SELECT id,picture,title,yuedu_count,releaser_id,time FROM toutiao UNION all SELECT id,picture,title,yuedu_count,releaser_id,time FROM faxian UNION all SELECT id,picture,title,yuedu ,releaser_id,time FROM quchu UNION all SELECT id,picture,title,yuedu ,releaser_id,time FROM commodity UNION all SELECT id,picture,title,yuedu ,releaser_id,time FROM fuwu) a LEFT OUTER JOIN  (SELECT tiaomu_id from dashang) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS  null";
					}
				}
			} else if (flag == 2) {
				if (change_rec != null) {
					if (change_rec.equals("有打赏")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu_count,releaser_id,time from toutiao  where fenlei_Tag=1) a LEFT OUTER JOIN  (SELECT tiaomu_id from  dashang ) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS not null";
					} else if (change_rec.equals("没有打赏")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu_count,releaser_id,time from toutiao where fenlei_Tag=1) a LEFT OUTER JOIN  (SELECT tiaomu_id from  dashang) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS  null";
					}
				}
			} else if (flag == 3) {
				if (change_rec != null) {
					if (change_rec.equals("有打赏")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu_count,releaser_id,time from toutiao where fenlei_Tag=2) a LEFT OUTER JOIN  (SELECT tiaomu_id from hongbao dashang) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS not null";
					} else if (change_rec.equals("没有打赏")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu_count,releaser_id,time from toutiao where fenlei_Tag=2) a LEFT OUTER JOIN  (SELECT tiaomu_id from hongbao dashang) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS  null";
					}
				}
			} else if (flag == 4) {
				if (change_rec != null) {
					if (change_rec.equals("有打赏")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu_count,releaser_id,time from toutiao where fenlei_Tag=3) a LEFT OUTER JOIN  (SELECT tiaomu_id from  dashang) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS not null";
					} else if (change_rec.equals("没有打赏")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu_count,releaser_id,time from toutiao where fenlei_Tag=3) a LEFT OUTER JOIN  (SELECT tiaomu_id from  dashang) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS   null";
					}
				}
			} else if (flag == 5) {
				if (change_rec != null) {
					if (change_rec.equals("有打赏")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu,releaser_id,time from faxian ) a LEFT OUTER JOIN  (SELECT tiaomu_id from dashang) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS not null";
					} else if (change_rec.equals("没有打赏")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu,releaser_id,time from faxian ) a LEFT OUTER JOIN  (SELECT tiaomu_id from dashang) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS   null";
					}
				}
			} else if (flag == 6) {
				if (change_rec != null) {
					if (change_rec.equals("有打赏")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu,releaser_id,time from quchu ) a LEFT OUTER JOIN  (SELECT tiaomu_id from dashang) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS not null";
					} else if (change_rec.equals("没有打赏")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu,releaser_id,time from quchu ) a LEFT OUTER JOIN  (SELECT tiaomu_id from dashang) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS  null";
					}
				}
			} else if (flag == 7) {
				if (change_rec != null) {
					if (change_rec.equals("有打赏")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu,releaser_id,time from commodity union select id,picture,title,yuedu,releaser_id,time from fuwu ) a LEFT OUTER JOIN  (SELECT tiaomu_id from dashang) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS not null";
					} else if (change_rec.equals("没有打赏")) {
						sql = "select DISTINCT * FROM (select id,picture,title,yuedu,releaser_id,time from commodity union select id,picture,title,yuedu,releaser_id,time from fuwu ) a LEFT OUTER JOIN  (SELECT tiaomu_id from dashang) b ON a.id=b.tiaomu_id WHERE b.tiaomu_id IS  null";
					}
				}
			}
			TOTAL_SIZE = getTotalSize(sql);
			sql = getLimitSql(sql);
			db1 = new DBHelper(sql);
			TOTAL_PAGE = (TOTAL_SIZE + PAGE_SIZE -1) /  PAGE_SIZE;
			ResultSet ret = db1.pst.executeQuery();
			while (ret.next()) {
				String id = ret.getString(1);
				String picture = ret.getString(2);
				String[] aa = picture.split(",");
				List<Picture> list2 = new ArrayList<Picture>();
				for (int i = 0; i < aa.length; i++) {
					Picture picture_url = new Picture();
					picture_url.setPicture_url(aa[i]);
					list2.add(picture_url);
				}
				String title = ret.getString(3);
				int yuedu = ret.getInt(4);
				String releaser_id = ret.getString(5);
				String releaser_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id).getName();
				String releaser_touxiang = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id)
						.getTouxiang_picture();
				int shoucang_count = Server_Func.getshoucang(id);
				Timestamp time = ret.getTimestamp(6);

				days = Server_Func.tuijian_days(id);
				String tuijian_message;

				if (days == 0) {
					tuijian_message = "未推荐";
				} else {
					tuijian_message = "已推荐";
				}
				String tuijian_Tag;
				if (tuijian_message.equals("已推荐")) {
					tuijian_Tag = "首页推荐";
				} else {
					tuijian_Tag = "未推荐";
				}

				Toutiao toutiao = new Toutiao();
				toutiao.setId(id);
				toutiao.setPictures(list2);
				toutiao.setTitle(title);
				toutiao.setYuedu_count(yuedu);
				toutiao.setReleaser_name(releaser_name);
				toutiao.setReleaser_touxiang(releaser_touxiang);
				toutiao.setDays(days);
				toutiao.setShare_count(0);
				toutiao.setShoucang_count(shoucang_count);
				toutiao.setTime(time);
				toutiao.setTuijian_message(tuijian_message);
				list.add(toutiao);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return list;
	}

	public static List<Toutiao> NoExamine_search(String change_rec, int flag) {
		String sql = null;
		DBHelper db1 = null;
		int days = 0;

		List<Toutiao> list = new ArrayList<Toutiao>();
		try {
			if (flag == 1) {
				if (change_rec != null) {

					sql = "SELECT id,picture,title,yuedu_count,releaser_id,time FROM toutiao where title like '%"
							+ change_rec
							+ "%' UNION all SELECT id,picture,title,yuedu_count,releaser_id,time FROM faxian where title like '%"
							+ change_rec
							+ "%' UNION all SELECT id,picture,title,yuedu ,releaser_id,time FROM quchu where title like '%"
							+ change_rec
							+ "%' UNION all SELECT id,picture,title,yuedu ,releaser_id,time FROM commodity where title like '%"
							+ change_rec
							+ "%' UNION all SELECT id,picture,title,yuedu ,releaser_id,time FROM fuwu where title like '%"
							+ change_rec + "%' ";

				}
			} else if (flag == 2) {
				if (change_rec != null) {

					sql = "select id,picture,title,yuedu_count,releaser_id,time from toutiao  where fenlei_Tag=1 and title like '%"
							+ change_rec + "%'";

				}
			} else if (flag == 3) {
				if (change_rec != null) {

					sql = "select id,picture,title,yuedu_count,releaser_id,time from toutiao where fenlei_Tag=2 and title like '%"
							+ change_rec + "%'";

				}
			} else if (flag == 4) {
				if (change_rec != null) {

					sql = "select id,picture,title,yuedu_count,releaser_id,time from toutiao where fenlei_Tag=3 and title like '%"
							+ change_rec + "%'";

				}
			} else if (flag == 5) {
				if (change_rec != null) {

					sql = "select id,picture,title,yuedu_count,releaser_id,time from faxian where title like '%"
							+ change_rec + "%'";

				}
			} else if (flag == 6) {
				if (change_rec != null) {

					sql = "select id,picture,title,yuedu,releaser_id,time from quchu where title like '%" + change_rec
							+ "%'";

				}
			} else if (flag == 7) {
				if (change_rec != null) {

					sql = "select id,picture,title,yuedu,releaser_id,time from commodity union select id,picture,title,yuedu,releaser_id,time from fuwu where title like '%"
							+ change_rec + "%'";

				}
			}
			TOTAL_SIZE = getTotalSize(sql);
			sql = getLimitSql(sql);
			db1 = new DBHelper(sql);
			TOTAL_PAGE = (TOTAL_SIZE + PAGE_SIZE -1) /  PAGE_SIZE;
			ResultSet ret = db1.pst.executeQuery();
			while (ret.next()) {
				String id = ret.getString(1);
				String picture = ret.getString(2);
				String[] aa = picture.split(",");
				List<Picture> list2 = new ArrayList<Picture>();
				for (int i = 0; i < aa.length; i++) {
					Picture picture_url = new Picture();
					picture_url.setPicture_url(aa[i]);
					list2.add(picture_url);
				}
				String title = ret.getString(3);
				int yuedu = ret.getInt(4);
				String releaser_id = ret.getString(5);
				String releaser_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id).getName();
				String releaser_touxiang = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id)
						.getTouxiang_picture();
				int shoucang_count = Server_Func.getshoucang(id);
				Timestamp time = ret.getTimestamp(6);

				days = Server_Func.tuijian_days(id);
				String tuijian_message;

				if (days == 0) {
					tuijian_message = "未推荐";
				} else {
					tuijian_message = "已推荐";
				}
				String tuijian_Tag;
				if (tuijian_message.equals("已推荐")) {
					tuijian_Tag = "首页推荐";
				} else {
					tuijian_Tag = "未推荐";
				}

				Toutiao toutiao = new Toutiao();
				toutiao.setId(id);
				toutiao.setPictures(list2);
				toutiao.setTitle(title);
				toutiao.setYuedu_count(yuedu);
				toutiao.setReleaser_name(releaser_name);
				toutiao.setReleaser_touxiang(releaser_touxiang);
				toutiao.setDays(days);
				toutiao.setShare_count(0);
				toutiao.setShoucang_count(shoucang_count);
				toutiao.setTime(time);
				toutiao.setTuijian_message(tuijian_message);
				list.add(toutiao);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return list;
	}

	public static List<Pinglun_model> getpinglun() {
		String sql = null;
		DBHelper db1 = null;

		List<Pinglun_model> list = new ArrayList<Pinglun_model>();
		try {

			sql = "select id,content,pingluner_id,dianzan_count,time from pinglun";

			db1 = new DBHelper(sql);
			ResultSet ret = db1.pst.executeQuery();
			while (ret.next()) {
				String id = ret.getString(1);
				String content = ret.getString(2);
				String pingluner_id = ret.getString(3);
				int dianzan_count = ret.getInt(4);
				Admin_xinxi admin = Admin_xinxi_Business.getAdmin_xinxiInfoById(pingluner_id);
				String pingluner_name = admin.getName();
				String pingluner_touxiang = admin.getTouxiang_picture();
				Timestamp time = ret.getTimestamp(5);

				Pinglun_model pinglun = new Pinglun_model();
				pinglun.setContent(content);
				pinglun.setDianzan_count(dianzan_count);
				pinglun.setTime(time);
				pinglun.setPingluner_name(pingluner_name);
				pinglun.setPingluner_touxiang(pingluner_touxiang);

				list.add(pinglun);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return list;
	}

	public static List<Toutiao> NoExamine_paixu(String change_rec, int flag) {
		String sql = null;
		DBHelper db1 = null;
		int days = 0;

		List<Toutiao> list = new ArrayList<Toutiao>();
		try {
			if (flag == 1) {
				if (change_rec != null) {

					if (change_rec.equals("阅读量从高到低")) {
						sql = "SELECT unionall.* FROM((SELECT id,picture,title,yuedu_count,releaser_id,time FROM toutiao ORDER BY yuedu_count DESC) UNION all (SELECT id,picture,title,yuedu_count,releaser_id,time FROM faxian ORDER BY yuedu_count DESC) UNION all (SELECT id,picture,title,yuedu ,releaser_id,time FROM quchu ORDER BY yuedu DESC) UNION all (SELECT id,picture,title,yuedu ,releaser_id,time FROM commodity ORDER BY yuedu ASC) UNION all (SELECT id,picture,title,yuedu ,releaser_id,time FROM fuwu ORDER BY yuedu DESC)) AS unionall ORDER BY unionall.yuedu_count DESC";
					} else if (change_rec.equals("阅读量从低到高")) {
						sql = "SELECT unionall.* FROM( (SELECT id,picture,title,yuedu_count,releaser_id,time FROM toutiao ORDER BY yuedu_count ASC) UNION all (SELECT id,picture,title,yuedu_count,releaser_id,time FROM faxian ORDER BY yuedu_count ASC) UNION all (SELECT id,picture,title,yuedu ,releaser_id,time FROM quchu ORDER BY yuedu ASC) UNION all (SELECT id,picture,title,yuedu ,releaser_id,time FROM commodity ORDER BY yuedu ASC) UNION all (SELECT id,picture,title,yuedu ,releaser_id,time FROM fuwu ORDER BY yuedu ASC)) AS unionall ORDER BY unionall.yuedu_count ASC";
					} else if (change_rec.equals("时间由早到晚")) {
						sql = "SELECT unionall.* FROM((SELECT id,picture,title,yuedu_count,releaser_id,time FROM toutiao ORDER BY time DESC) UNION all (SELECT id,picture,title,yuedu_count,releaser_id,time FROM faxian ORDER BY time DESC) UNION all (SELECT id,picture,title,yuedu ,releaser_id,time FROM quchu ORDER BY time DESC) UNION all (SELECT id,picture,title,yuedu ,releaser_id,time FROM commodity ORDER BY time DESC) UNION all (SELECT id,picture,title,yuedu ,releaser_id,time FROM fuwu ORDER BY time DESC)) AS unionall ORDER BY unionall.time DESC";
					} else if (change_rec.equals("时间由晚到早")) {
						sql = "SELECT unionall.* FROM((SELECT id,picture,title,yuedu_count,releaser_id,time FROM toutiao ORDER BY time ASC) UNION all (SELECT id,picture,title,yuedu_count,releaser_id,time FROM faxian ORDER BY time ASC) UNION all (SELECT id,picture,title,yuedu ,releaser_id,time FROM quchu ORDER BY time ASC) UNION all (SELECT id,picture,title,yuedu ,releaser_id,time FROM commodity ORDER BY time ASC) UNION all (SELECT id,picture,title,yuedu ,releaser_id,time FROM fuwu ORDER BY time ASC)) AS unionall ORDER BY unionall.time ASC";
					}
				}
			}else if (flag == 2) {
				if (change_rec != null) {
					if (change_rec.equals("阅读量从高到低")) {
						sql = "SELECT id, picture, title, yuedu_count, releaser_id, time FROM toutiao ORDER BY yuedu_count DESC";
					} else if (change_rec.equals("阅读量从低到高")) {
						sql = "SELECT id, picture, title, yuedu_count, releaser_id, time FROM toutiao ORDER BY yuedu_count ASC";
					} else if (change_rec.equals("时间由早到晚")) {
						sql = "SELECT id, picture, title, yuedu_count, releaser_id, time FROM toutiao ORDER BY time DESC";
					} else if (change_rec.equals("时间由晚到早")) {
						sql = "SELECT id, picture, title, yuedu_count, releaser_id, time FROM toutiao ORDER BY time ASC";
					}
				}
			}else if (flag == 3) {
				if (change_rec != null) {

					if (change_rec.equals("阅读量从高到低")) {
						sql = "SELECT unionall.* FROM((SELECT id,picture,title,yuedu_count,releaser_id,time FROM toutiao ORDER BY yuedu_count DESC) UNION all (SELECT id,picture,title,yuedu_count,releaser_id,time FROM faxian ORDER BY yuedu_count DESC) UNION all (SELECT id,picture,title,yuedu ,releaser_id,time FROM quchu ORDER BY yuedu DESC) UNION all (SELECT id,picture,title,yuedu ,releaser_id,time FROM commodity ORDER BY yuedu ASC) UNION all (SELECT id,picture,title,yuedu ,releaser_id,time FROM fuwu ORDER BY yuedu DESC)) AS unionall ORDER BY unionall.yuedu_count DESC";
					} else if (change_rec.equals("阅读量从低到高")) {
						sql = "SELECT unionall.* FROM( (SELECT id,picture,title,yuedu_count,releaser_id,time FROM toutiao ORDER BY yuedu_count ASC) UNION all (SELECT id,picture,title,yuedu_count,releaser_id,time FROM faxian ORDER BY yuedu_count ASC) UNION all (SELECT id,picture,title,yuedu ,releaser_id,time FROM quchu ORDER BY yuedu ASC) UNION all (SELECT id,picture,title,yuedu ,releaser_id,time FROM commodity ORDER BY yuedu ASC) UNION all (SELECT id,picture,title,yuedu ,releaser_id,time FROM fuwu ORDER BY yuedu ASC)) AS unionall ORDER BY unionall.yuedu_count ASC";
					} else if (change_rec.equals("时间由早到晚")) {
						sql = "SELECT unionall.* FROM((SELECT id,picture,title,yuedu_count,releaser_id,time FROM toutiao ORDER BY time DESC) UNION all (SELECT id,picture,title,yuedu_count,releaser_id,time FROM faxian ORDER BY time DESC) UNION all (SELECT id,picture,title,yuedu ,releaser_id,time FROM quchu ORDER BY time DESC) UNION all (SELECT id,picture,title,yuedu ,releaser_id,time FROM commodity ORDER BY time DESC) UNION all (SELECT id,picture,title,yuedu ,releaser_id,time FROM fuwu ORDER BY time DESC)) AS unionall ORDER BY unionall.time DESC";
					} else if (change_rec.equals("时间由晚到早")) {
						sql = "SELECT unionall.* FROM((SELECT id,picture,title,yuedu_count,releaser_id,time FROM toutiao ORDER BY time ASC) UNION all (SELECT id,picture,title,yuedu_count,releaser_id,time FROM faxian ORDER BY time ASC) UNION all (SELECT id,picture,title,yuedu ,releaser_id,time FROM quchu ORDER BY time ASC) UNION all (SELECT id,picture,title,yuedu ,releaser_id,time FROM commodity ORDER BY time ASC) UNION all (SELECT id,picture,title,yuedu ,releaser_id,time FROM fuwu ORDER BY time ASC)) AS unionall ORDER BY unionall.time ASC";
					}
				}
			}else if (flag == 4) {
				if (change_rec != null) {

					if (change_rec.equals("阅读量从高到低")) {
						sql = "SELECT unionall.* FROM((SELECT id,picture,title,yuedu_count,releaser_id,time FROM toutiao ORDER BY yuedu_count DESC) UNION all (SELECT id,picture,title,yuedu_count,releaser_id,time FROM faxian ORDER BY yuedu_count DESC) UNION all (SELECT id,picture,title,yuedu ,releaser_id,time FROM quchu ORDER BY yuedu DESC) UNION all (SELECT id,picture,title,yuedu ,releaser_id,time FROM commodity ORDER BY yuedu ASC) UNION all (SELECT id,picture,title,yuedu ,releaser_id,time FROM fuwu ORDER BY yuedu DESC)) AS unionall ORDER BY unionall.yuedu_count DESC";
					} else if (change_rec.equals("阅读量从低到高")) {
						sql = "SELECT unionall.* FROM( (SELECT id,picture,title,yuedu_count,releaser_id,time FROM toutiao ORDER BY yuedu_count ASC) UNION all (SELECT id,picture,title,yuedu_count,releaser_id,time FROM faxian ORDER BY yuedu_count ASC) UNION all (SELECT id,picture,title,yuedu ,releaser_id,time FROM quchu ORDER BY yuedu ASC) UNION all (SELECT id,picture,title,yuedu ,releaser_id,time FROM commodity ORDER BY yuedu ASC) UNION all (SELECT id,picture,title,yuedu ,releaser_id,time FROM fuwu ORDER BY yuedu ASC)) AS unionall ORDER BY unionall.yuedu_count ASC";
					} else if (change_rec.equals("时间由早到晚")) {
						sql = "SELECT unionall.* FROM((SELECT id,picture,title,yuedu_count,releaser_id,time FROM toutiao ORDER BY time DESC) UNION all (SELECT id,picture,title,yuedu_count,releaser_id,time FROM faxian ORDER BY time DESC) UNION all (SELECT id,picture,title,yuedu ,releaser_id,time FROM quchu ORDER BY time DESC) UNION all (SELECT id,picture,title,yuedu ,releaser_id,time FROM commodity ORDER BY time DESC) UNION all (SELECT id,picture,title,yuedu ,releaser_id,time FROM fuwu ORDER BY time DESC)) AS unionall ORDER BY unionall.time DESC";
					} else if (change_rec.equals("时间由晚到早")) {
						sql = "SELECT unionall.* FROM((SELECT id,picture,title,yuedu_count,releaser_id,time FROM toutiao ORDER BY time ASC) UNION all (SELECT id,picture,title,yuedu_count,releaser_id,time FROM faxian ORDER BY time ASC) UNION all (SELECT id,picture,title,yuedu ,releaser_id,time FROM quchu ORDER BY time ASC) UNION all (SELECT id,picture,title,yuedu ,releaser_id,time FROM commodity ORDER BY time ASC) UNION all (SELECT id,picture,title,yuedu ,releaser_id,time FROM fuwu ORDER BY time ASC)) AS unionall ORDER BY unionall.time ASC";
					}
				}
			}else if (flag == 5) {
				if (change_rec != null) {

					if (change_rec.equals("阅读量从高到低")) {
						sql = "SELECT id, picture, title, yuedu_count, releaser_id, time FROM faxian ORDER BY yuedu_count DESC";
					} else if (change_rec.equals("阅读量从低到高")) {
						sql = "SELECT id, picture, title, yuedu_count, releaser_id, time FROM faxian ORDER BY yuedu_count ASC";
					} else if (change_rec.equals("时间由早到晚")) {
						sql = "SELECT id, picture, title, yuedu_count, releaser_id, time FROM faxian ORDER BY time DESC";
					} else if (change_rec.equals("时间由晚到早")) {
						sql = "SELECT id, picture, title, yuedu_count, releaser_id, time FROM faxian ORDER BY time ASC";
					}
				}
			}else if (flag == 6) {
				if (change_rec != null) {

					if (change_rec.equals("阅读量从高到低")) {
						sql = "SELECT id, picture, title, yuedu, releaser_id, time FROM quchu ORDER BY yuedu DESC";
					} else if (change_rec.equals("阅读量从低到高")) {
						sql = "SELECT id, picture, title, yuedu, releaser_id, time FROM quchu ORDER BY yuedu ASC";
					} else if (change_rec.equals("时间由早到晚")) {
						sql = "SELECT id, picture, title, yuedu, releaser_id, time FROM quchu ORDER BY time DESC";
					} else if (change_rec.equals("时间由晚到早")) {
						sql = "SELECT id, picture, title, yuedu, releaser_id, time FROM quchu ORDER BY time ASC";
					}
				}
			}else if (flag == 7) {
				if (change_rec != null) {

					if (change_rec.equals("阅读量从高到低")) {
						sql = "SELECT id, picture, title, yuedu, releaser_id, time FROM commodity ORDER BY yuedu DESC";
					} else if (change_rec.equals("阅读量从低到高")) {
						sql = "SELECT id, picture, title, yuedu, releaser_id, time FROM commodity ORDER BY yuedu ASC";
					} else if (change_rec.equals("时间由早到晚")) {
						sql = "SELECT id, picture, title, yuedu, releaser_id, time FROM commodity ORDER BY time DESC";
					} else if (change_rec.equals("时间由晚到早")) {
						sql = "SELECT id, picture, title, yuedu, releaser_id, time FROM commodity ORDER BY time ASC";
					}
				}
			}
			TOTAL_SIZE = getTotalSize(sql);
			sql = getLimitSql(sql);
			db1 = new DBHelper(sql);
			TOTAL_PAGE = (TOTAL_SIZE + PAGE_SIZE -1) /  PAGE_SIZE;
			ResultSet ret = db1.pst.executeQuery();
			while (ret.next()) {
				String id = ret.getString(1);
				String picture = ret.getString(2);
				String[] aa = picture.split(",");
				List<Picture> list2 = new ArrayList<Picture>();
				for (int i = 0; i < aa.length; i++) {
					Picture picture_url = new Picture();
					picture_url.setPicture_url(aa[i]);
					list2.add(picture_url);
				}
				String title = ret.getString(3);
				int yuedu = ret.getInt(4);
				String releaser_id = ret.getString(5);
				String releaser_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id).getName();
				String releaser_touxiang = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id)
						.getTouxiang_picture();
				int shoucang_count = Server_Func.getshoucang(id);
				Timestamp time = ret.getTimestamp(6);

				days = Server_Func.tuijian_days(id);
				String tuijian_message;

				if (days == 0) {
					tuijian_message = "未推荐";
				} else {
					tuijian_message = "已推荐";
				}
				String tuijian_Tag;
				if (tuijian_message.equals("已推荐")) {
					tuijian_Tag = "首页推荐";
				} else {
					tuijian_Tag = "未推荐";
				}

				Toutiao toutiao = new Toutiao();
				toutiao.setId(id);
				toutiao.setPictures(list2);
				toutiao.setTitle(title);
				toutiao.setYuedu_count(yuedu);
				toutiao.setReleaser_name(releaser_name);
				toutiao.setReleaser_touxiang(releaser_touxiang);
				toutiao.setDays(days);
				toutiao.setShare_count(0);
				toutiao.setShoucang_count(shoucang_count);
				toutiao.setTime(time);
				toutiao.setTuijian_message(tuijian_message);
				list.add(toutiao);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return list;
	}

	public static List<String> getFolder(String _path) {
		List<String> list = new ArrayList<String>();
		String path = _path; // 路径
		File f = new File(path);

		File fa[] = f.listFiles();
		for (int i = 0; i < fa.length; i++)

		{
			File fs = fa[i];
			if (fs.isDirectory()) {
				list.add(fs.getName());
			} else {
				list.add(fs.getName());
				System.out.println(fs.getName());
			}
		}
		return list;
	}

	public static List<Pinglun_model> getpinglun_toutiao() {
		String sql = null;
		DBHelper db1 = null;

		List<Pinglun_model> list = new ArrayList<Pinglun_model>();
		try {

			sql = "SELECT * FROM(select id,tiaomu_id,content,pingluner_id,dianzan_count,time from pinglun) a LEFT OUTER JOIN (SELECT id FROM toutiao) b ON b.id=a.tiaomu_id where b.id is not NULL";

			db1 = new DBHelper(sql);
			ResultSet ret = db1.pst.executeQuery();
			while (ret.next()) {
				String id = ret.getString(1);
				String tiaomu_id = ret.getString(2);
				System.out.println(tiaomu_id);
				List<Toutiao_Xiangqing> toutiao = ToutiaoBusiness.getToutiaoById("1", tiaomu_id);
				Toutiao_Xiangqing toutiao_Xiangqing = null;
				if (toutiao != null) {
					toutiao_Xiangqing = toutiao.get(0);
				}

				String title = toutiao_Xiangqing.getTitle();
				String content = ret.getString(3);
				String pingluner_id = ret.getString(4);
				int dianzan_count = ret.getInt(5);
				Admin_xinxi admin = Admin_xinxi_Business.getAdmin_xinxiInfoById(pingluner_id);
				String pingluner_name = admin.getName();
				String pingluner_touxiang = admin.getTouxiang_picture();
				Timestamp time = ret.getTimestamp(6);

				Pinglun_model pinglun = new Pinglun_model();
				pinglun.setId(id);
				pinglun.setContent(content);
				pinglun.setDianzan_count(dianzan_count);
				pinglun.setTime(time);
				pinglun.setPingluner_name(pingluner_name);
				pinglun.setPingluner_touxiang(pingluner_touxiang);
				pinglun.setTitle(title);
				list.add(pinglun);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return list;
	}

	public static List<Pinglun_model> getpinglun_faxian() {
		String sql = null;
		DBHelper db1 = null;

		List<Pinglun_model> list = new ArrayList<Pinglun_model>();
		try {

			sql = "SELECT * FROM(select id,tiaomu_id,content,pingluner_id,dianzan_count,time from pinglun) a LEFT OUTER JOIN (SELECT id FROM faxian) b ON b.id=a.tiaomu_id where b.id is not NULL";

			db1 = new DBHelper(sql);
			ResultSet ret = db1.pst.executeQuery();
			while (ret.next()) {
				String id = ret.getString(1);
				String tiaomu_id = ret.getString(2);
				Faxian_Xiangqing faxian = FaxianBusiness.getFaxianById("1", tiaomu_id);
				String title = faxian.getTitle();
				String content = ret.getString(3);
				String pingluner_id = ret.getString(4);
				int dianzan_count = ret.getInt(5);
				Admin_xinxi admin = Admin_xinxi_Business.getAdmin_xinxiInfoById(pingluner_id);
				String pingluner_name = admin.getName();
				String pingluner_touxiang = admin.getTouxiang_picture();
				Timestamp time = ret.getTimestamp(6);

				Pinglun_model pinglun = new Pinglun_model();
				pinglun.setId(id);
				pinglun.setContent(content);
				pinglun.setDianzan_count(dianzan_count);
				pinglun.setTime(time);
				pinglun.setPingluner_name(pingluner_name);
				pinglun.setPingluner_touxiang(pingluner_touxiang);
				pinglun.setTitle(title);
				list.add(pinglun);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return list;
	}

	public static List<Pinglun_model> getpinglun_quchu() {
		String sql = null;
		DBHelper db1 = null;

		List<Pinglun_model> list = new ArrayList<Pinglun_model>();
		try {

			sql = "SELECT * FROM(select id,tiaomu_id,content,pingluner_id,dianzan_count,time from pinglun) a LEFT OUTER JOIN (SELECT id FROM quchu) b ON b.id=a.tiaomu_id where b.id is not NULL";

			db1 = new DBHelper(sql);
			ResultSet ret = db1.pst.executeQuery();
			while (ret.next()) {
				String id = ret.getString(1);
				String tiaomu_id = ret.getString(2);
				Quchu_Xiangqing quchu = QuchuBusiness.getQuchuById(tiaomu_id, "1");
				String title = quchu.getTitle();
				String content = ret.getString(3);
				String pingluner_id = ret.getString(4);
				int dianzan_count = ret.getInt(5);
				Admin_xinxi admin = Admin_xinxi_Business.getAdmin_xinxiInfoById(pingluner_id);
				String pingluner_name = admin.getName();
				String pingluner_touxiang = admin.getTouxiang_picture();
				Timestamp time = ret.getTimestamp(6);

				Pinglun_model pinglun = new Pinglun_model();
				pinglun.setId(id);
				pinglun.setContent(content);
				pinglun.setDianzan_count(dianzan_count);
				pinglun.setTime(time);
				pinglun.setPingluner_name(pingluner_name);
				pinglun.setPingluner_touxiang(pingluner_touxiang);
				pinglun.setTitle(title);
				list.add(pinglun);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return list;
	}

	public static List<Pinglun_model> getpinglun_commodity() {
		String sql = null;
		DBHelper db1 = null;

		List<Pinglun_model> list = new ArrayList<Pinglun_model>();
		try {

			sql = "SELECT * FROM(select id,tiaomu_id,content,pingluner_id,dianzan_count,time from pinglun) a LEFT OUTER JOIN (SELECT id FROM commodity) b ON b.id=a.tiaomu_id where b.id is not NULL";

			db1 = new DBHelper(sql);
			ResultSet ret = db1.pst.executeQuery();
			while (ret.next()) {
				String id = ret.getString(1);
				String tiaomu_id = ret.getString(2);
				Guangjie_Xiangqing guangjie_Xiangqing = CommodityBusiness.getGuangjie_CommodityById(tiaomu_id, "1", 1);
				String title = guangjie_Xiangqing.getTitle();
				String content = ret.getString(3);
				String pingluner_id = ret.getString(4);
				int dianzan_count = ret.getInt(5);
				Admin_xinxi admin = Admin_xinxi_Business.getAdmin_xinxiInfoById(pingluner_id);
				String pingluner_name = admin.getName();
				String pingluner_touxiang = admin.getTouxiang_picture();
				Timestamp time = ret.getTimestamp(6);

				Pinglun_model pinglun = new Pinglun_model();
				pinglun.setId(id);
				pinglun.setContent(content);
				pinglun.setDianzan_count(dianzan_count);
				pinglun.setTime(time);
				pinglun.setPingluner_name(pingluner_name);
				pinglun.setPingluner_touxiang(pingluner_touxiang);
				pinglun.setTitle(title);
				list.add(pinglun);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return list;
	}

	public static List<Pinglun_model> getpinglun_fuwu() {
		String sql = null;
		DBHelper db1 = null;

		List<Pinglun_model> list = new ArrayList<Pinglun_model>();
		try {

			sql = "SELECT * FROM(select id,tiaomu_id,content,pingluner_id,dianzan_count,time from pinglun) a LEFT OUTER JOIN (SELECT id FROM fuwu) b ON b.id=a.tiaomu_id where b.id is not NULL";

			db1 = new DBHelper(sql);
			ResultSet ret = db1.pst.executeQuery();
			while (ret.next()) {
				String id = ret.getString(1);
				String tiaomu_id = ret.getString(2);
				Guangjie_Xiangqing guangjie_Xiangqing = FuwuBusiness.getGuangjie_FuwuById(tiaomu_id, "1", 2);
				String title = guangjie_Xiangqing.getTitle();
				String content = ret.getString(3);
				String pingluner_id = ret.getString(4);
				int dianzan_count = ret.getInt(5);
				Admin_xinxi admin = Admin_xinxi_Business.getAdmin_xinxiInfoById(pingluner_id);
				String pingluner_name = admin.getName();
				String pingluner_touxiang = admin.getTouxiang_picture();
				Timestamp time = ret.getTimestamp(6);

				Pinglun_model pinglun = new Pinglun_model();
				pinglun.setId(id);
				pinglun.setContent(content);
				pinglun.setDianzan_count(dianzan_count);
				pinglun.setTime(time);
				pinglun.setPingluner_name(pingluner_name);
				pinglun.setPingluner_touxiang(pingluner_touxiang);
				pinglun.setTitle(title);
				list.add(pinglun);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return list;
	}

	public static String add_riji(String picture, String title, String name, String content, int muban_Tag,
			String releaser_id) {
		String sql = null;
		DBHelper db1 = null;
		String uuid = UUID.randomUUID().toString();
		int fufei_Tag = 0;
		int fenlei_Tag = 1;
		String releaser_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id).getName();
		fenlei_Tag = 2;
		String shenhe = "已发布";
		Timestamp time = new Timestamp(new Date().getTime());

		sql = "INSERT INTO toutiao(id,picture,title,name,content,fufei_Tag,fenlei_Tag,muban_Tag,releaser_id,releaser_name,shenhe,time,yuedu) VALUES ('"
				+ uuid + "', '" + picture + "', '" + title + "', '" + name + "', '" + content + "', " + fufei_Tag + ", "
				+ fenlei_Tag + "," + muban_Tag + ",'" + releaser_id + "','" + releaser_name + "','" + shenhe + "','"
				+ time + "')";

		try {
			db1 = new DBHelper(sql);
			int ret = db1.pst.executeUpdate();
			if(ret > 0){
				return uuid;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}
		return null;

	}

	public static String add_toutiao(String picture, String title, String name, String content, int muban_Tag,
			String releaser_id, int yuedu_count, int dianzan_count) {
		String sql = null;
		DBHelper db1 = null;
		String uuid = UUID.randomUUID().toString();
		int fufei_Tag = 0;
		int fenlei_Tag = 2;
		String releaser_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id).getName();
		fenlei_Tag = 2;
		String shenhe = "已发布";
		Timestamp time = new Timestamp(new Date().getTime());

		sql = "INSERT INTO toutiao(id,picture,title,name,content,fufei_Tag,fenlei_Tag,muban_Tag,releaser_id,releaser_name,shenhe,time,yuedu_count,dianzan_count) VALUES ('"
				+ uuid + "', '" + picture + "', '" + title + "', '" + name + "', '" + content + "', " + fufei_Tag + ", "
				+ fenlei_Tag + "," + muban_Tag + ",'" + releaser_id + "','" + releaser_name + "','" + shenhe + "','"
				+ time + "'," + yuedu_count + "," + dianzan_count + ")";

		try {
			db1 = new DBHelper(sql);
			int ret = db1.pst.executeUpdate();
			if(ret > 0){
				return uuid;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}
		return null;

	}

	public static String add_faxian(String releaser_id, String picture, String title, String name, String content) {
		String sql = null;
		DBHelper db1 = null;
		String uuid = UUID.randomUUID().toString();
		int fenlei_Tag = 1;
		String shenhe = "已发布";
		Timestamp time = new Timestamp(new Date().getTime());

		sql = "INSERT INTO faxian(id,picture,title,name,content,fenlei_Tag,shenhe,releaser_id,time) VALUES ('" + uuid
				+ "', '" + picture + "', '" + title + "', '" + name + "','" + content + "'," + fenlei_Tag + ",'"
				+ shenhe + "','" + releaser_id + "')";

		try {
			db1 = new DBHelper(sql);
			int ret = db1.pst.executeUpdate();
			if(ret > 0){
				return uuid;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}
		return null;

	}

	public static String add_quchu(String releaser_id, String title, String address, String phone, String picture,
			String content) {
		String sql = null;
		DBHelper db1 = null;
		String uuid = UUID.randomUUID().toString();
		int fenlei_Tag = 2;
		int renzheng_Tag = 1;
		String shenhe = "正在审核中...";
		Timestamp time = new Timestamp(new Date().getTime());

		sql = "INSERT INTO quchu(id,title,dianpu_address,phone,picture,content,dianpu_name,renzheng_Tag,shenhe,time,releaser_id,fenlei_Tag) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

		try {

			db1 = new DBHelper(sql);

			db1.pst.setString(1, uuid);
			db1.pst.setString(2, title);
			db1.pst.setString(3, address);
			db1.pst.setString(4, phone);
			db1.pst.setString(5, picture);
			db1.pst.setString(6, content);
			db1.pst.setString(7, title);
			db1.pst.setInt(8, renzheng_Tag);
			db1.pst.setString(9, shenhe);
			db1.pst.setTimestamp(10, time);
			db1.pst.setString(11, releaser_id);
			db1.pst.setInt(12, fenlei_Tag);

			int ret = db1.pst.executeUpdate();
			if(ret > 0){
				return uuid;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}
		return null;

	}

	public static String add_commodity(String title, String price, String shuliang, String freight, String phone,
			String picture, String content, String seller_id) {
		String sql = null;
		DBHelper db1 = null;
		String uuid = UUID.randomUUID().toString();
		String releaser_id = seller_id;
		String seller_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(seller_id).getName();
		String releaser_name = seller_name;
		Timestamp time = new Timestamp(new Date().getTime());
		String shenhe = "正在审核中...";

		sql = "INSERT INTO commodity(id,title,price,unit,shuliang,freight,phone,picture,content,seller_id,releaser_id,seller_name,releaser_name,shenhe,time) VALUES ('"
				+ uuid + "', '" + title + "', '" + price + "','" + shuliang + "','" + freight + "','" + phone + "','"
				+ picture + "','" + content + "','" + seller_id + "','" + seller_id + "','" + seller_name + "','"
				+ seller_name + "','" + shenhe + "','" + time + "')";

		try {
			db1 = new DBHelper(sql);
			int ret = db1.pst.executeUpdate();
			if(ret > 0){
				return uuid;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}
		return null;

	}

	public static String add_fuwu(String releaser_id, String title, String price, String phone, String picture,
			String content) {
		String sql = null;
		DBHelper db1 = null;
		String uuid = UUID.randomUUID().toString();

		String shenhe = "正在审核中...";
		Timestamp time = new Timestamp(new Date().getTime());

		sql = "INSERT INTO fuwu(id,title,price,unit,phone,picture,content,shenhe,releaser_id,time) VALUES ('" + uuid
				+ "', '" + title + "'," + price + ",'" + phone + "','" + picture + "','" + content + "','" + shenhe
				+ "','" + releaser_id + "','" + time + "')";

		try {
			db1 = new DBHelper(sql);
			int ret = db1.pst.executeUpdate();
			if(ret > 0){
				return uuid;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}
		return null;

	}

	public static List<User_model> search_User(String str, String Tag) {// 已取消
		String sql = null;
		DBHelper db1 = null;
		if (Tag.equals("1")) {
			sql = "SELECT touxiang_picture,name,qianbao,id,time,qianming FROM admin_xinxi WHERE name like '%" + str
					+ "%'";
		} else if (Tag.equals("2")) {
			sql = " select DISTINCT * FROM(SELECT touxiang_picture,name,qianbao,id,time,qianming FROM admin_xinxi WHERE name like '%"
					+ str
					+ "%') a LEFT OUTER JOIN (SELECT tuijian_user_id from tuijian_user) b ON a.id=b.tuijian_user_id  WHERE b.tuijian_user_id IS not null";
		} else if (Tag.equals("3")) {
			sql = "SELECT touxiang_picture,name,qianbao,id,time,qianming FROM admin_xinxi WHERE yonghu_Tag=3 and  name like '%"
					+ str + "%'";
		} else if (Tag.equals("4")) {
			sql = "SELECT touxiang_picture,name,qianbao,id,time,qianming FROM admin_xinxi WHERE yonghu_Tag=1 and  name like '%"
					+ str + "%'";
		} else if (Tag.equals("5")) {
			sql = "SELECT touxiang_picture,name,qianbao,id,time,qianming FROM admin_xinxi WHERE yonghu_Tag=4 and  name like '%"
					+ str + "%'";
		} else if (Tag.equals("6")) {
			sql = "SELECT touxiang_picture,name,qianbao,id,time,qianming FROM admin_xinxi WHERE yonghu_Tag=2 and  name like '%"
					+ str + "%'";
		} else
			db1 = new DBHelper(sql);
		List<User_model> user_models = new ArrayList<User_model>();
		try {
			ResultSet ret = db1.pst.executeQuery();
			while (ret.next()) {
				String touxiang = ret.getString(1);
				String name = ret.getString(2);
				double qianbao = ret.getDouble(3);
				String id = ret.getString(4);
				int fensi = GuanzhuBusiness.getZhuye_Friend_id(id, 0).size();
				int guanzhu = GuanzhuBusiness.getZhuye_My_id(id, 0).size();
				Timestamp time = ret.getTimestamp(5);
				String qianming = ret.getString(6);
				String renzheng_message = Server_Func.getrenzheng(id);

				User_model model = new User_model();
				model.setTouxiang(touxiang);
				model.setName(name);
				model.setFensi(fensi);
				model.setGuanzhu(guanzhu);
				model.setTime(time);
				model.setQianbao(qianbao);

				model.setQianming(qianming);
				model.setRenzheng_Tag(renzheng_message);

				user_models.add(model);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return user_models;
	}

	public static List<User_model> sex_change(String str, String Tag) {// 已取消
		String sql = null;
		DBHelper db1 = null;
		if (Tag.equals("1")) {
			sql = "SELECT touxiang_picture,name,qianbao,id,time,qianming FROM admin_xinxi WHERE sex='" + str + "'";
		} else if (Tag.equals("2")) {
			sql = " select DISTINCT * FROM(SELECT touxiang_picture,name,qianbao,id,time,qianming FROM admin_xinxi WHERE sex='"
					+ str
					+ "') a LEFT OUTER JOIN (SELECT tuijian_user_id from tuijian_user) b ON a.id=b.tuijian_user_id  WHERE b.tuijian_user_id IS not null";
		} else if (Tag.equals("3")) {
			sql = "SELECT touxiang_picture,name,qianbao,id,time,qianming FROM admin_xinxi WHERE yonghu_Tag=3 and  sex='"
					+ str + "'";
		} else if (Tag.equals("4")) {
			sql = "SELECT touxiang_picture,name,qianbao,id,time,qianming FROM admin_xinxi WHERE yonghu_Tag=1 and sex='"
					+ str + "'";
		} else if (Tag.equals("5")) {
			sql = "SELECT touxiang_picture,name,qianbao,id,time,qianming FROM admin_xinxi WHERE yonghu_Tag=4 and  sex='"
					+ str + "'";
		} else if (Tag.equals("6")) {
			sql = "SELECT touxiang_picture,name,qianbao,id,time,qianming FROM admin_xinxi WHERE yonghu_Tag=2 and  sex='"
					+ str + "'";
		} else
			db1 = new DBHelper(sql);
		List<User_model> user_models = new ArrayList<User_model>();
		try {
			ResultSet ret = db1.pst.executeQuery();
			while (ret.next()) {
				String touxiang = ret.getString(1);
				String name = ret.getString(2);
				double qianbao = ret.getDouble(3);
				String id = ret.getString(4);
				int fensi = GuanzhuBusiness.getZhuye_Friend_id(id, 0).size();
				int guanzhu = GuanzhuBusiness.getZhuye_My_id(id, 0).size();
				Timestamp time = ret.getTimestamp(5);
				String qianming = ret.getString(6);
				String renzheng_message = Server_Func.getrenzheng(id);

				User_model model = new User_model();
				model.setTouxiang(touxiang);
				model.setName(name);
				model.setFensi(fensi);
				model.setGuanzhu(guanzhu);
				model.setTime(time);
				model.setQianbao(qianbao);

				model.setQianming(qianming);
				model.setRenzheng_Tag(renzheng_message);

				user_models.add(model);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return user_models;
	}

	public static List<Login> getAllLogin() {
		List<Login> list = new ArrayList<Login>();
		String sql = null;
		DBHelper db1 = null;
		sql = "select * from admin";
		db1 = new DBHelper(sql);
		ResultSet ret = null;
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				String id = ret.getString(1);
				String admin_user = ret.getString(2);
				String password = ret.getString(3);
				String admin_leixing = ret.getString(3);

				Login login = new Login();
				login.setLogin_id(id);
				login.setPhone(admin_user);
				login.setPassword(password);
				login.setAdmin_leixing(admin_leixing);

				list.add(login);
			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void add_admin(String zhanghao, String user_name, String password, String phone, String select) {
		String sql = null;
		DBHelper db1 = null;
		String uuid = UUID.randomUUID().toString();

		sql = "INSERT INTO fuwu(id,admin_user,password,phone,admin_leixing) VALUES ('" + uuid + "','" + zhanghao + "','"
				+ user_name + "','" + password + "','" + phone + "','" + select + "')";

		try {
			db1 = new DBHelper(sql);
			boolean ret = db1.pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}

	}

	public static void add_guanggao(String picture_url, String fenlei_Tag, String shangjia_Tag, String url) {
		String sql = null;
		DBHelper db1 = null;
		String uuid = UUID.randomUUID().toString();

		sql = "INSERT INTO advertisement(id,picture,guanggao_id,fenlei_Tag,shangjia_Tag) VALUES ('" + uuid + "','"
				+ picture_url + "','" + fenlei_Tag + "','" + shangjia_Tag + "','" + url + "')";

		try {
			db1 = new DBHelper(sql);
			boolean ret = db1.pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}

	}

	public static Toutiao chaxun(String change_rec, int flag) {
		String sql = null;
		DBHelper db1 = null;
		Toutiao toutiao = new Toutiao();
		try {
			if (flag == 1) {
				if (change_rec != null) {

					sql = "SELECT id,content FROM toutiao where id='" + change_rec
							+ "' UNION all SELECT id,content FROM faxian where id='" + change_rec
							+ "' UNION all SELECT id,content FROM quchu where id='" + change_rec
							+ "' UNION all SELECT id,content FROM commodity where id='" + change_rec
							+ "' UNION all SELECT id,content FROM fuwu where id='" + change_rec + "' ";

				}
			} else if (flag == 2) {
				if (change_rec != null) {

					sql = "select id,content from toutiao  where fenlei_Tag=1 and id='" + change_rec + "'";

				}
			} else if (flag == 3) {
				if (change_rec != null) {

					sql = "select id,content from toutiao where fenlei_Tag=2 and id='" + change_rec + "'";

				}
			} else if (flag == 4) {
				if (change_rec != null) {

					sql = "select id,content from toutiao where fenlei_Tag=3 and id='" + change_rec + "'";

				}
			} else if (flag == 5) {
				if (change_rec != null) {

					sql = "select id,content from faxian where id='" + change_rec + "'";

				}
			} else if (flag == 6) {
				if (change_rec != null) {

					sql = "select id,content from quchu where id='" + change_rec + "'";

				}
			} else if (flag == 7) {
				if (change_rec != null) {

					sql = "select id,content from commodity union select id,content from fuwu where id='" + change_rec
							+ "'";

				}
			}

			db1 = new DBHelper(sql);
			ResultSet ret = db1.pst.executeQuery();
			while (ret.next()) {
				String id = ret.getString(1);
				String content = ret.getString(2);
				String[] contents = content.split("<--分隔符-->");
				String str = "<div  class='article-content' >";
				for (int i = 0; i < contents.length; i++) {
					if (contents[i].length() > 4) {
						if (contents[i].substring(0, 4).equals("http")) {
							str += "<p><img src=\"" + contents[i] + "\"></p>";
						} else {
							str += "<p>" + contents[i] + "</p>";
						}
					} else {
						str += "<p>" + contents[i] + "</p>";
					}

				}
				str += "</div>";

				toutiao.setId(id);
				toutiao.setContent(str);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return toutiao;
	}

	public static List<Admin> getadmin() {
		String sql = null;
		DBHelper db1 = null;
		List<Admin> toutiao = new ArrayList<Admin>();
		try {

			sql = "SELECT name,phone FROM admin";

			db1 = new DBHelper(sql);
			ResultSet ret = db1.pst.executeQuery();
			while (ret.next()) {
				String name = ret.getString(1);
				String phone = ret.getString(2);

				Admin admin = new Admin();
				admin.setName(name);
				admin.setPhone(phone);
				toutiao.add(admin);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return toutiao;
	}

	public static String setshenhe(String _shenhe, String id, int Tag) {
		String sql = null;
		String sql2 = null;
		String sql3 = null;
		String sql4 = null;
		String sql5 = null;
		DBHelper db1 = null;
		String shenhe = "";
		if (_shenhe.equals("是")) {
			shenhe = "已发布";
		} else {
			shenhe = "审核失败";
		}
		if (Tag == 1) {
			sql = "UPDATE toutiao SET shenhe = '" + shenhe + "' where id='" + id + "'";
			sql2 = "UPDATE toutiao SET shenhe = '" + shenhe + "' where id='" + id + "'";
			sql3 = "UPDATE toutiao SET shenhe = '" + shenhe + "' where id='" + id + "'";
			sql4 = "UPDATE toutiao SET shenhe = '" + shenhe + "' where id='" + id + "'";
			sql5 = "UPDATE toutiao SET shenhe = '" + shenhe + "' where id='" + id + "'";
		} else if (Tag == 2) {
			sql = "UPDATE toutiao SET shenhe = '" + shenhe + "' where id='" + id + "'";
		} else if (Tag == 3) {
			sql = "UPDATE toutiao SET shenhe = '" + shenhe + "' where id='" + id + "'";
		} else if (Tag == 4) {
			sql = "UPDATE toutiao SET shenhe = '" + shenhe + "' where id='" + id + "'";
		} else if (Tag == 5) {
			sql = "UPDATE faxian SET shenhe = '" + shenhe + "' where id='" + id + "'";
		} else if (Tag == 6) {
			sql = "UPDATE quchu SET shenhe = '" + shenhe + "' where id='" + id + "'";
		} else if (Tag == 7) {
			sql = "UPDATE commodity SET shenhe = '" + shenhe + "' where id='" + id + "'";
			sql2 = "UPDATE fuwu SET shenhe = '" + shenhe + "' where id='" + id + "'";
		}

		db1 = new DBHelper(sql);
		try {
			if (Tag == 1) {
				db1 = new DBHelper(sql);
				boolean ret = db1.pst.execute();
				db1 = new DBHelper(sql2);
				boolean ret1 = db1.pst.execute();
				db1 = new DBHelper(sql3);
				boolean ret2 = db1.pst.execute();
				db1 = new DBHelper(sql4);
				boolean ret3 = db1.pst.execute();
				db1 = new DBHelper(sql5);
				boolean ret4 = db1.pst.execute();
			} else if (Tag == 7) {
				db1 = new DBHelper(sql);
				boolean ret = db1.pst.execute();
				db1 = new DBHelper(sql2);
				boolean ret1 = db1.pst.execute();

			} else {
				boolean ret = db1.pst.execute();
			}
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return shenhe;

	}

	public static void Delete_wenzhang(String change_rec, int flag) {
		String sql = null;
		String sql2 = null;
		String sql3 = null;
		String sql4 = null;
		String sql5 = null;
		DBHelper db1 = null;
		Toutiao toutiao = new Toutiao();
		try {
			if (flag == 1) {
				if (change_rec != null) {

					sql = "delete FROM toutiao where id='" + change_rec + "'";
					sql2 = "delete FROM faxian where id='" + change_rec + "'";
					sql3 = "delete FROM quchu where id='" + change_rec + "'";
					sql4 = "delete FROM commodity where id='" + change_rec + "'";
					sql5 = "delete FROM fuwu where id='" + change_rec + "'";

				}
			} else if (flag == 2) {
				if (change_rec != null) {

					sql = "delete from toutiao  where fenlei_Tag=1 and id='" + change_rec + "'";

				}
			} else if (flag == 3) {
				if (change_rec != null) {

					sql = "delete from toutiao where fenlei_Tag=2 and id='" + change_rec + "'";

				}
			} else if (flag == 4) {
				if (change_rec != null) {

					sql = "delete from toutiao where fenlei_Tag=3 and id='" + change_rec + "'";

				}
			} else if (flag == 5) {
				if (change_rec != null) {

					sql = "delete from faxian where id='" + change_rec + "'";

				}
			} else if (flag == 6) {
				if (change_rec != null) {

					sql = "delete from quchu where id='" + change_rec + "'";

				}
			} else if (flag == 7) {
				if (change_rec != null) {

					sql = "delete from commodity union select id,content from fuwu where id='" + change_rec + "'";

				}
			}
			if (flag == 1) {
				db1 = new DBHelper(sql);
				boolean ret = db1.pst.execute();
				db1 = new DBHelper(sql2);
				boolean ret2 = db1.pst.execute();
				db1 = new DBHelper(sql3);
				boolean ret3 = db1.pst.execute();
				db1 = new DBHelper(sql4);
				boolean ret4 = db1.pst.execute();
				db1 = new DBHelper(sql5);
				boolean ret5 = db1.pst.execute();
			} else {
				db1 = new DBHelper(sql);
				boolean ret = db1.pst.execute();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}

	}

}
