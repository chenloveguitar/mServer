package com.magicmoble.luzhouapp.server.server_function;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.aspectj.weaver.patterns.WildAnnotationTypePattern;
import org.springframework.web.servlet.ModelAndView;

import com.magicmoble.luzhouapp.action.weixin_pay.Str;
import com.magicmoble.luzhouapp.business.Admin_xinxi_Business;
import com.magicmoble.luzhouapp.business.CommodityBusiness;
import com.magicmoble.luzhouapp.business.DBHelper;
import com.magicmoble.luzhouapp.business.Dingdan_Business;
import com.magicmoble.luzhouapp.business.FunctionBusiness;
import com.magicmoble.luzhouapp.business.GuanzhuBusiness;
import com.magicmoble.luzhouapp.business.RenzhengBusiness;
import com.magicmoble.luzhouapp.model.Admin_xinxi;
import com.magicmoble.luzhouapp.model.Commodity;
import com.magicmoble.luzhouapp.model.Dianzan_Number;
import com.magicmoble.luzhouapp.model.Guangjie_Xiangqing;
import com.magicmoble.luzhouapp.model.Guanzhu;
import com.magicmoble.luzhouapp.model.Picture;
import com.magicmoble.luzhouapp.model.server.Dingdan_model;
import com.magicmoble.luzhouapp.model.server.Guanggao_model;
import com.magicmoble.luzhouapp.model.server.Home_model;
import com.magicmoble.luzhouapp.model.server.Shuoshuo_xiangqing;
import com.magicmoble.luzhouapp.model.server.Toutiao;
import com.magicmoble.luzhouapp.model.server.User_model;;

public class Server_Func {
	
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
		} finally {
			db1.close();
		}

		return home_model;

	}

	public static List<Shuoshuo_xiangqing> limitShuoshuo_ser(int pageno, int pagecount) {

		String sql = "SELECT picture,yuedu,content,share_count,releaser_id,time,tuijian_Tag,id FROM shuoshuo order by time desc  limit ?,?";

		DBHelper db1 = null;
		ResultSet ret = null;

		List<Shuoshuo_xiangqing> list = new ArrayList<Shuoshuo_xiangqing>();
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
				String id = ret.getString(8);
				String tuijian_message = null;
				if (tuijiang_Tag == 0) {
					tuijian_message = "未推荐";
				} else {
					tuijian_message = "已推荐";
				}
				// 包含id的说说实体类
				Shuoshuo_xiangqing shuoshuo = new Shuoshuo_xiangqing();
				shuoshuo.setContent(content);
				shuoshuo.setName(name);
				shuoshuo.setPictures(list2);
				shuoshuo.setQianming(qianming);
				shuoshuo.setShare_count(share_count);
				shuoshuo.setTime(time);
				shuoshuo.setReleaser_touxiang(touxiang);
				shuoshuo.setTuijian_message(tuijian_message);
				shuoshuo.setYuedu_count(yuedu_count);
				shuoshuo.setId(id);
				list.add(shuoshuo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return list;

	}

	public static List<Shuoshuo_xiangqing> getShuoshuo_Info(String id) {

		String sql = "SELECT picture,yuedu,content,share_count,releaser_id,time,tuijian_Tag,dianzan_count,id FROM shuoshuo where id=?";

		DBHelper db1 = null;
		ResultSet ret = null;

		List<Shuoshuo_xiangqing> list = new ArrayList<Shuoshuo_xiangqing>();
		try {

			db1 = new DBHelper(sql);
			db1.pst.setString(1, id);
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
				int dianzan_count = FunctionBusiness.getDianzanNumber(id, 2).getDianzan_count();
				String _id = ret.getString(9);
				int shoucang_count = getshoucang_count(_id);

				// 包含id的说说实体类
				Shuoshuo_xiangqing shuoshuo = new Shuoshuo_xiangqing();
				shuoshuo.setShuoshuo_id(_id);
				shuoshuo.setContent(content);
				shuoshuo.setName(name);
				shuoshuo.setPictures(list2);
				shuoshuo.setQianming(qianming);
				shuoshuo.setShare_count(share_count);
				shuoshuo.setTime(time);
				shuoshuo.setReleaser_touxiang(touxiang);
				shuoshuo.setTuijian_message(tuijian_message);
				shuoshuo.setDianzan_count(dianzan_count);
				shuoshuo.setYuedu_count(yuedu_count);
				shuoshuo.setShoucang_count(shoucang_count);
				list.add(shuoshuo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return list;

	}

	public static boolean deleteShuoshuo(String id) {

		String sql = "DELETE FROM shuoshuo where id=?";

		DBHelper db1 = null;
		ResultSet ret = null;

		try {

			db1 = new DBHelper(sql);
			db1.pst.setString(1, id);
			boolean isDelete = db1.pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}
		return false;
	}

	public static boolean deleteNeirong(String id, int flag) {
		String sql = null;
		boolean isDelete = false;
		if (flag == 1) {
			sql = "delete FROM toutiao where id='" + id + "'";

		} else if (flag == 2) {
			sql = "delete from toutiao where id='" + id + "'";
		} else if (flag == 3) {
			sql = "delete from toutiao where id='" + id + "'";
		} else if (flag == 4) {
			sql = "delete from toutiao where id='" + id + "'";
		} else if (flag == 5) {
			sql = "delete from faxian where id='" + id + "'";
		} else if (flag == 6) {
			sql = "delete from quchu where id='" + id + "'";
		} else if (flag == 7) {
			sql = "delete FROM commodity where id='" + id + "'";

		}

		DBHelper db1 = null;
		ResultSet ret = null;

		try {
			if (flag == 1 && flag == 7) {

				if (flag == 1) {
					db1 = new DBHelper(sql);

					isDelete = db1.pst.execute();

				} else if (isDelete) {
					sql = "delete FROM faxian where id='" + id + "'";
					db1 = new DBHelper(sql);

					isDelete = db1.pst.execute();
				} else if (isDelete) {
					sql = "delete FROM quchu where id='" + id + "'";
					db1 = new DBHelper(sql);

					isDelete = db1.pst.execute();
				} else if (isDelete) {
					sql = "delete FROM commodity where id='" + id + "'";
					db1 = new DBHelper(sql);

					isDelete = db1.pst.execute();
				} else if (isDelete) {
					sql = "delete FROM fuwu where id='" + id + "'";
					db1 = new DBHelper(sql);

					isDelete = db1.pst.execute();
				}
				if (flag == 7) {
					sql = "delete FROM fuwu where id='" + id + "'";
					db1 = new DBHelper(sql);

					isDelete = db1.pst.execute();
				} else if (isDelete) {
					sql = "delete FROM fuwu where id='" + id + "'";
					db1 = new DBHelper(sql);

					isDelete = db1.pst.execute();
				}
			} else {
				db1 = new DBHelper(sql);

				isDelete = db1.pst.execute();
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			db1.close();
		}
		return false;
	}

	public static boolean UpdateShuoshuo(String id) {

		String sql = "DELETE FROM shuoshuo where id=?";

		DBHelper db1 = null;
		ResultSet ret = null;

		try {

			db1 = new DBHelper(sql);
			db1.pst.setString(1, id);
			boolean isDelete = db1.pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}
		return false;
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
	public static int getshoucang_count(String id) {

		String sql = "select count(id) from shoucang where quchu_id='" + id + "' or commodity_id='" + id
				+ "' or toutiao_id='" + id + "' or faxian_id='" + id + "'";

		DBHelper db1 = null;
		ResultSet ret = null;
		int count = 0;
		try {

			db1 = new DBHelper(sql);

			ret = db1.pst.executeQuery();
			while (ret.next()) {

				count = ret.getInt(1);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}
		return count;
	}

	public static void shuoshuo_update(String id, String key, String value) {
		String sql = "update shuoshuo set " + key + "='" + value + "' where id='" + id + "'";
		DBHelper db1 = null;
		db1 = new DBHelper(sql);
		int ret = 0;
		try {
			ret = db1.pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db1.close();
		}

	}

	public static void addShuoshuo(String picture, String content, String releaser_id, int muban_Tag, int tuijian_Tag,
			int yuedu_count, int dianzan_count) {
		String sql = null;
		DBHelper db1 = null;
		String uuid = UUID.randomUUID().toString();
		int guanzhu_Tag = 0;
		Timestamp timestamp = new Timestamp(new Date().getTime());

		sql = "INSERT INTO shuoshuo(id,picture,content,dianzan_count,time,guanzhu_Tag,releaser_id,muban_Tag,tuijian_Tag,yuedu) VALUES ('"
				+ uuid + "', '" + picture + "', '" + content + "', '" + dianzan_count + "', '" + timestamp + "', '"
				+ guanzhu_Tag + "', '" + releaser_id + "', '" + muban_Tag + "', '" + tuijian_Tag + "', '" + yuedu_count
				+ "')";

		try {
			db1 = new DBHelper(sql);
			boolean ret = db1.pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}

	}
	public static List<Toutiao> toutiao_NoExamine() {
		String sql = null;
		DBHelper db1 = null;
		int days = 0;

		sql = "SELECT t.id,t.picture,t.title,t.yuedu_count,t.releaser_id,t.shenhe,t.time FROM toutiao t WHERE shenhe='正在审核中...' and fenlei_Tag=1";
		List<Toutiao> list = new ArrayList<Toutiao>();
		try {
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
				String shenhe = ret.getString(6);
				double dianzan_hongbao = getHongbao(id, 1);
				double share_hongbao = getHongbao(id, 2);
				int shoucang_count = getshoucang(id);
				Timestamp time = ret.getTimestamp(7);

				days = tuijian_days(id);
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
				toutiao.setShenhe(shenhe);
				toutiao.setDays(days);
				toutiao.setDianzan_hongbao(dianzan_hongbao);
				toutiao.setShare_hongbao(share_hongbao);
				toutiao.setShare_count(0);
				toutiao.setShoucang_count(shoucang_count);
				toutiao.setTime(time);
				toutiao.setTuijian_message(tuijian_message);
				toutiao.setTuijian_Tag(tuijian_Tag);
				list.add(toutiao);
			}

			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return list;
	}

	public static List<Toutiao> riji_NoExamine() {
		String sql = null;
		DBHelper db1 = null;
		int days = 0;

		sql = "SELECT t.id,t.picture,t.title,t.yuedu_count,t.releaser_id,t.shenhe,t.time FROM toutiao t WHERE shenhe='正在审核中...' and fenlei_Tag=2";
		List<Toutiao> list = new ArrayList<Toutiao>();
		try {
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
				String shenhe = ret.getString(6);
				double dianzan_hongbao = getHongbao(id, 1);
				double share_hongbao = getHongbao(id, 2);
				int shoucang_count = getshoucang(id);
				Timestamp time = ret.getTimestamp(7);

				days = tuijian_days(id);
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
				toutiao.setShenhe(shenhe);
				toutiao.setDays(days);
				toutiao.setDianzan_hongbao(dianzan_hongbao);
				toutiao.setShare_hongbao(share_hongbao);
				toutiao.setShare_count(0);
				toutiao.setShoucang_count(shoucang_count);
				toutiao.setTime(time);
				toutiao.setTuijian_message(tuijian_message);
				toutiao.setTuijian_Tag(tuijian_Tag);
				list.add(toutiao);
			}

			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return list;
	}

	public static List<Toutiao> tuji_NoExamine() {
		String sql = null;
		DBHelper db1 = null;
		int days = 0;

		sql = "SELECT t.id,t.picture,t.title,t.yuedu_count,t.releaser_id,t.shenhe,t.time FROM toutiao t WHERE shenhe='正在审核中...' and fenlei_Tag=3";
		List<Toutiao> list = new ArrayList<Toutiao>();
		try {
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
				String shenhe = ret.getString(6);
				double dianzan_hongbao = getHongbao(id, 1);
				double share_hongbao = getHongbao(id, 2);
				int shoucang_count = getshoucang(id);
				Timestamp time = ret.getTimestamp(7);

				days = tuijian_days(id);
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
				toutiao.setShenhe(shenhe);
				toutiao.setDays(days);
				toutiao.setDianzan_hongbao(dianzan_hongbao);
				toutiao.setShare_hongbao(share_hongbao);
				toutiao.setShare_count(0);
				toutiao.setShoucang_count(shoucang_count);
				toutiao.setTime(time);
				toutiao.setTuijian_message(tuijian_message);
				toutiao.setTuijian_Tag(tuijian_Tag);
				list.add(toutiao);
			}

			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return list;
	}

	public static List<Toutiao> faxian_NoExamine() {
		String sql = null;
		DBHelper db1 = null;
		int days = 0;

		sql = "SELECT t.id,t.picture,t.title,t.yuedu_count,t.releaser_id,t.shenhe,t.time FROM faxian t WHERE shenhe='正在审核中...'";
		List<Toutiao> list = new ArrayList<Toutiao>();
		try {
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
				String shenhe = ret.getString(6);
				double dianzan_hongbao = getHongbao(id, 1);
				double share_hongbao = getHongbao(id, 2);
				int shoucang_count = getshoucang(id);
				Timestamp time = ret.getTimestamp(7);

				days = tuijian_days(id);
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
				toutiao.setShenhe(shenhe);
				toutiao.setDays(days);
				toutiao.setDianzan_hongbao(dianzan_hongbao);
				toutiao.setShare_hongbao(share_hongbao);
				toutiao.setShare_count(0);
				toutiao.setShoucang_count(shoucang_count);
				toutiao.setTime(time);
				toutiao.setTuijian_message(tuijian_message);
				toutiao.setTuijian_Tag(tuijian_Tag);
				list.add(toutiao);
			}

			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return list;
	}

	public static List<Toutiao> quchu_NoExamine() {
		String sql = null;
		DBHelper db1 = null;
		int days = 0;

		sql = "SELECT t.id,t.picture,t.title,t.yuedu,t.releaser_id,t.shenhe,t.time FROM quchu t WHERE shenhe='正在审核中...'";
		List<Toutiao> list = new ArrayList<Toutiao>();
		try {
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
				String shenhe = ret.getString(6);
				double dianzan_hongbao = getHongbao(id, 1);
				double share_hongbao = getHongbao(id, 2);
				int shoucang_count = getshoucang(id);
				Timestamp time = ret.getTimestamp(7);

				days = tuijian_days(id);
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
				toutiao.setShenhe(shenhe);
				toutiao.setDays(days);
				toutiao.setDianzan_hongbao(dianzan_hongbao);
				toutiao.setShare_hongbao(share_hongbao);
				toutiao.setShare_count(0);
				toutiao.setShoucang_count(shoucang_count);
				toutiao.setTime(time);
				toutiao.setTuijian_message(tuijian_message);
				toutiao.setTuijian_Tag(tuijian_Tag);
				list.add(toutiao);
			}

			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return list;
	}

	public static List<Toutiao> guangjie_NoExamine() {
		String sql = null;
		DBHelper db1 = null;
		int days = 0;

		sql = "SELECT t.id,t.picture,t.title,t.yuedu,t.releaser_id,t.shenhe,t.time FROM fuwu t WHERE shenhe='正在审核中...' UNION SELECT t.id,t.picture,t.title,t.yuedu,t.releaser_id,t.shenhe,t.time FROM commodity t WHERE shenhe='正在审核中...'";
		List<Toutiao> list = new ArrayList<Toutiao>();
		try {
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
				String shenhe = ret.getString(6);
				double dianzan_hongbao = getHongbao(id, 1);
				double share_hongbao = getHongbao(id, 2);
				int shoucang_count = getshoucang(id);
				Timestamp time = ret.getTimestamp(7);

				days = tuijian_days(id);
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
				toutiao.setShenhe(shenhe);
				toutiao.setDays(days);
				toutiao.setDianzan_hongbao(dianzan_hongbao);
				toutiao.setShare_hongbao(share_hongbao);
				toutiao.setShare_count(0);
				toutiao.setShoucang_count(shoucang_count);
				toutiao.setTime(time);
				toutiao.setTuijian_message(tuijian_message);
				toutiao.setTuijian_Tag(tuijian_Tag);
				list.add(toutiao);
			}

			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return list;
	}

	public static double getHongbao(String tiaomu_id, int hongbao_Tag) {
		String sql = "SELECT hongbao_price FROM hongbao WHERE tiaomu_id='" + tiaomu_id + "' and hongbao_Tag="
				+ hongbao_Tag;

		DBHelper db1 = null;
		double price = 0;
		db1 = new DBHelper(sql);
		ResultSet ret = null;
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				price = ret.getDouble(1);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return price;

	}

	public static int getshoucang(String tiaomu_id) {
		String sql = null;
		DBHelper db1 = null;
		int flag = 0;
		sql = "SELECT count(id) FROM shoucang WHERE (quchu_id='" + tiaomu_id + "' OR commodity_id='" + tiaomu_id
				+ "' OR toutiao_id='" + tiaomu_id + "' OR faxian_id='" + tiaomu_id + "')";

		db1 = new DBHelper(sql);
		ResultSet ret = null;
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				flag = ret.getInt(1);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return flag;

	}

	public static int tuijian_days(String tiaomu_id) {
		String sql = null;
		DBHelper db1 = null;
		int days = 0;
		sql = "SELECT end_time,start_time FROM tuijian_list where tiaomu_id='" + tiaomu_id + "'";

		db1 = new DBHelper(sql);
		ResultSet ret = null;
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				Timestamp end_time = ret.getTimestamp(1);
				Timestamp start_time = ret.getTimestamp(2);
				days = (int) ((end_time.getTime() - start_time.getTime()) / 1000 / 60 / 60 / 24);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return days;
	}

	public static List<Toutiao> toutiao_Release() {
		String sql = null;
		DBHelper db1 = null;
		int days = 0;

		sql = "SELECT t.id,t.picture,t.title,t.yuedu_count,t.releaser_id,t.shenhe,t.time FROM toutiao t WHERE shenhe='已发布' and fenlei_Tag=1";
		List<Toutiao> list = new ArrayList<Toutiao>();
		try {
			db1 = new DBHelper(sql);
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
				String shenhe = ret.getString(6);
				double dianzan_hongbao = getHongbao(id, 1);
				double share_hongbao = getHongbao(id, 2);
				int shoucang_count = getshoucang(id);
				Timestamp time = ret.getTimestamp(7);

				days = tuijian_days(id);
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
				toutiao.setShenhe(shenhe);
				toutiao.setDays(days);
				toutiao.setDianzan_hongbao(dianzan_hongbao);
				toutiao.setShare_hongbao(share_hongbao);
				toutiao.setShare_count(0);
				toutiao.setShoucang_count(shoucang_count);
				toutiao.setTime(time);
				toutiao.setTuijian_message(tuijian_message);
				toutiao.setTuijian_Tag(tuijian_Tag);
				list.add(toutiao);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return list;
	}

	public static List<Toutiao> riji_Release() {
		String sql = null;
		DBHelper db1 = null;
		int days = 0;

		sql = "SELECT t.id,t.picture,t.title,t.yuedu_count,t.releaser_id,t.shenhe,t.time FROM toutiao t WHERE shenhe='已发布' and fenlei_Tag=2";
		List<Toutiao> list = new ArrayList<Toutiao>();
		try {
			db1 = new DBHelper(sql);
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
				String shenhe = ret.getString(6);
				double dianzan_hongbao = getHongbao(id, 1);
				double share_hongbao = getHongbao(id, 2);
				int shoucang_count = getshoucang(id);
				Timestamp time = ret.getTimestamp(7);

				days = tuijian_days(id);
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
				toutiao.setShenhe(shenhe);
				toutiao.setDays(days);
				toutiao.setDianzan_hongbao(dianzan_hongbao);
				toutiao.setShare_hongbao(share_hongbao);
				toutiao.setShare_count(0);
				toutiao.setShoucang_count(shoucang_count);
				toutiao.setTime(time);
				toutiao.setTuijian_message(tuijian_message);
				toutiao.setTuijian_Tag(tuijian_Tag);
				list.add(toutiao);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return list;
	}

	public static List<Toutiao> tuji_Release() {
		String sql = null;
		DBHelper db1 = null;
		int days = 0;

		sql = "SELECT t.id,t.picture,t.title,t.yuedu_count,t.releaser_id,t.shenhe,t.time FROM toutiao t WHERE shenhe='已发布' and fenlei_Tag=3";
		List<Toutiao> list = new ArrayList<Toutiao>();
		try {
			db1 = new DBHelper(sql);
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
				String shenhe = ret.getString(6);
				double dianzan_hongbao = getHongbao(id, 1);
				double share_hongbao = getHongbao(id, 2);
				int shoucang_count = getshoucang(id);
				Timestamp time = ret.getTimestamp(7);

				days = tuijian_days(id);
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
				toutiao.setShenhe(shenhe);
				toutiao.setDays(days);
				toutiao.setDianzan_hongbao(dianzan_hongbao);
				toutiao.setShare_hongbao(share_hongbao);
				toutiao.setShare_count(0);
				toutiao.setShoucang_count(shoucang_count);
				toutiao.setTime(time);
				toutiao.setTuijian_message(tuijian_message);
				toutiao.setTuijian_Tag(tuijian_Tag);
				list.add(toutiao);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return list;
	}

	public static List<Toutiao> faxian_Release() {
		String sql = null;
		DBHelper db1 = null;
		int days = 0;

		sql = "SELECT t.id,t.picture,t.title,t.yuedu_count,t.releaser_id,t.shenhe,t.time FROM faxian t WHERE shenhe='已发布'";
		List<Toutiao> list = new ArrayList<Toutiao>();
		try {
			db1 = new DBHelper(sql);
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
				String shenhe = ret.getString(6);
				double dianzan_hongbao = getHongbao(id, 1);
				double share_hongbao = getHongbao(id, 2);
				int shoucang_count = getshoucang(id);
				Timestamp time = ret.getTimestamp(7);

				days = tuijian_days(id);
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
				toutiao.setShenhe(shenhe);
				toutiao.setDays(days);
				toutiao.setDianzan_hongbao(dianzan_hongbao);
				toutiao.setShare_hongbao(share_hongbao);
				toutiao.setShare_count(0);
				toutiao.setShoucang_count(shoucang_count);
				toutiao.setTime(time);
				toutiao.setTuijian_message(tuijian_message);
				toutiao.setTuijian_Tag(tuijian_Tag);
				list.add(toutiao);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return list;
	}

	public static List<Toutiao> quchu_Release() {
		String sql = null;
		DBHelper db1 = null;
		int days = 0;

		sql = "SELECT t.id,t.picture,t.title,t.yuedu,t.releaser_id,t.shenhe,t.time FROM quchu t WHERE shenhe='已发布'";
		List<Toutiao> list = new ArrayList<Toutiao>();
		try {
			db1 = new DBHelper(sql);
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
				String shenhe = ret.getString(6);
				double dianzan_hongbao = getHongbao(id, 1);
				double share_hongbao = getHongbao(id, 2);
				int shoucang_count = getshoucang(id);
				Timestamp time = ret.getTimestamp(7);

				days = tuijian_days(id);
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
				toutiao.setShenhe(shenhe);
				toutiao.setDays(days);
				toutiao.setDianzan_hongbao(dianzan_hongbao);
				toutiao.setShare_hongbao(share_hongbao);
				toutiao.setShare_count(0);
				toutiao.setShoucang_count(shoucang_count);
				toutiao.setTime(time);
				toutiao.setTuijian_message(tuijian_message);
				toutiao.setTuijian_Tag(tuijian_Tag);
				list.add(toutiao);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return list;
	}

	public static List<Toutiao> guangjie_Release() {
		String sql = null;
		DBHelper db1 = null;
		int days = 0;

		sql = "SELECT t.id,t.picture,t.title,t.yuedu,t.releaser_id,t.shenhe,t.time FROM fuwu t WHERE shenhe='已发布' UNION SELECT t.id,t.picture,t.title,t.yuedu,t.releaser_id,t.shenhe,t.time FROM commodity t WHERE shenhe='已发布'";
		List<Toutiao> list = new ArrayList<Toutiao>();
		try {
			db1 = new DBHelper(sql);
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
				String shenhe = ret.getString(6);
				double dianzan_hongbao = getHongbao(id, 1);
				double share_hongbao = getHongbao(id, 2);
				int shoucang_count = getshoucang(id);
				Timestamp time = ret.getTimestamp(7);

				days = tuijian_days(id);
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
				toutiao.setShenhe(shenhe);
				toutiao.setDays(days);
				toutiao.setDianzan_hongbao(dianzan_hongbao);
				toutiao.setShare_hongbao(share_hongbao);
				toutiao.setShare_count(0);
				toutiao.setShoucang_count(shoucang_count);
				toutiao.setTime(time);
				toutiao.setTuijian_message(tuijian_message);
				toutiao.setTuijian_Tag(tuijian_Tag);
				list.add(toutiao);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return list;
	}

	public static void addApk(String version, String describem, String download_url) {
		String sql = null;
		DBHelper db1 = null;
		String uuid = UUID.randomUUID().toString();
		Timestamp time = new Timestamp(new Date().getTime());

		sql = "INSERT INTO Apk(id,time,version,description,download_url) VALUES('" + uuid + "','" + time + "','"
				+ version + "','" + describem + "','" + download_url + "')";

		try {
			db1 = new DBHelper(sql);
			boolean ret = db1.pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}
	}

	public static List<Toutiao> end() {
		String sql = null;
		DBHelper db1 = null;
		int days = 0;

		sql = "SELECT id,picture,title,yuedu,releaser_id,time FROM commodity where shuliang=0";
		List<Toutiao> list = new ArrayList<Toutiao>();
		try {
			db1 = new DBHelper(sql);
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
				int shoucang_count = getshoucang(id);
				Timestamp time = ret.getTimestamp(6);

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
				list.add(toutiao);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return list;
	}

	public static List<Dingdan_model> Commodity_NoConfirm() {
		String sql = null;
		DBHelper db1 = null;
		int days = 0;

		sql = "SELECT tiaomu_id,seller_id,buyer_id,shuliang,total_price,id,guangjie_fenlei_Tag,transaction_id FROM dingdan WHERE seller_zhuangtai=2 and buyer_zhuangtai=2 and guangjie_fenlei_Tag=1";
		db1 = new DBHelper(sql);
		List<Dingdan_model> dingdan_models = new ArrayList<Dingdan_model>();
		try {
			ResultSet ret = db1.pst.executeQuery();
			while (ret.next()) {
				String tiaomu_id = ret.getString(1);
				String seller_id = ret.getString(2);
				String seller_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(seller_id).getName();
				String seller_touxiang = Admin_xinxi_Business.getAdmin_xinxiInfoById(seller_id).getTouxiang_picture();

				String buyer_id = ret.getString(3);
				String buyer_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(buyer_id).getName();
				String buyer_touxiang = Admin_xinxi_Business.getAdmin_xinxiInfoById(buyer_id).getTouxiang_picture();
				int shuliang = ret.getInt(4);
				double total_price = ret.getDouble(5);
				String id = ret.getString(6);
				int guangjie_fenlei_Tag = ret.getInt(7);
				String transaction_id = ret.getString(8);
				String title = null;
				if (guangjie_fenlei_Tag == 1) {
					title = CommodityBusiness.getGuangjie_CommodityById(tiaomu_id, null, 1).getTitle();
				} else {
					title = CommodityBusiness.getGuangjie_CommodityById(tiaomu_id, null, 2).getTitle();
				}

				Dingdan_model dingdan_model = new Dingdan_model();
				dingdan_model.setId(id);
				dingdan_model.setTitle(title);
				dingdan_model.setTransaction_id(transaction_id);
				dingdan_model.setBuyer_name(buyer_name);
				dingdan_model.setBuyer_touxiang(buyer_touxiang);

				dingdan_model.setSeller_name(seller_name);
				dingdan_model.setSeller_touxiang(seller_touxiang);
				dingdan_model.setShuliang(shuliang);
				dingdan_model.setTotal_price(total_price);
				dingdan_model.setGuangjie_fenlei_Tag(guangjie_fenlei_Tag);
				dingdan_models.add(dingdan_model);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return dingdan_models;
	}

	public static List<Dingdan_model> Fuwu_NoConfirm() {
		String sql = null;
		DBHelper db1 = null;
		int days = 0;

		sql = "SELECT tiaomu_id,seller_id,buyer_id,shuliang,total_price,id,guangjie_fenlei_Tag,transaction_id FROM dingdan WHERE seller_zhuangtai=2 and buyer_zhuangtai=2 and guangjie_fenlei_Tag=2";
		db1 = new DBHelper(sql);
		List<Dingdan_model> dingdan_models = new ArrayList<Dingdan_model>();
		try {
			ResultSet ret = db1.pst.executeQuery();
			while (ret.next()) {
				String tiaomu_id = ret.getString(1);
				String seller_id = ret.getString(2);
				String seller_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(seller_id).getName();
				String seller_touxiang = Admin_xinxi_Business.getAdmin_xinxiInfoById(seller_id).getTouxiang_picture();

				String buyer_id = ret.getString(3);
				String buyer_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(buyer_id).getName();
				String buyer_touxiang = Admin_xinxi_Business.getAdmin_xinxiInfoById(buyer_id).getTouxiang_picture();
				int shuliang = ret.getInt(4);
				double total_price = ret.getDouble(5);
				String id = ret.getString(6);
				int guangjie_fenlei_Tag = ret.getInt(7);
				String transaction_id = ret.getString(8);
				String title = null;
				if (guangjie_fenlei_Tag == 1) {
					title = CommodityBusiness.getGuangjie_CommodityById(tiaomu_id, null, 1).getTitle();
				} else {
					title = CommodityBusiness.getGuangjie_CommodityById(tiaomu_id, null, 2).getTitle();
				}

				Dingdan_model dingdan_model = new Dingdan_model();
				dingdan_model.setId(id);
				dingdan_model.setTitle(title);
				dingdan_model.setTransaction_id(transaction_id);
				dingdan_model.setBuyer_name(buyer_name);
				dingdan_model.setBuyer_touxiang(buyer_touxiang);

				dingdan_model.setSeller_name(seller_name);
				dingdan_model.setSeller_touxiang(seller_touxiang);
				dingdan_model.setShuliang(shuliang);
				dingdan_model.setTotal_price(total_price);
				dingdan_model.setGuangjie_fenlei_Tag(guangjie_fenlei_Tag);
				dingdan_models.add(dingdan_model);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return dingdan_models;
	}

	public static List<Dingdan_model> Commodity_Ongoing() {// 进行中
		String sql = null;
		DBHelper db1 = null;
		int days = 0;

		sql = "SELECT tiaomu_id,seller_id,buyer_id,shuliang,total_price,id,guangjie_fenlei_Tag,transaction_id FROM dingdan WHERE seller_zhuangtai=3 and buyer_zhuangtai=3 and guangjie_fenlei_Tag=1";
		db1 = new DBHelper(sql);
		List<Dingdan_model> dingdan_models = new ArrayList<Dingdan_model>();
		try {
			ResultSet ret = db1.pst.executeQuery();
			while (ret.next()) {
				String tiaomu_id = ret.getString(1);
				String seller_id = ret.getString(2);
				String seller_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(seller_id).getName();
				String seller_touxiang = Admin_xinxi_Business.getAdmin_xinxiInfoById(seller_id).getTouxiang_picture();

				String buyer_id = ret.getString(3);
				String buyer_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(buyer_id).getName();
				String buyer_touxiang = Admin_xinxi_Business.getAdmin_xinxiInfoById(buyer_id).getTouxiang_picture();
				int shuliang = ret.getInt(4);
				double total_price = ret.getDouble(5);
				String id = ret.getString(6);
				int guangjie_fenlei_Tag = ret.getInt(7);
				String transaction_id = ret.getString(8);
				String title = null;
				if (guangjie_fenlei_Tag == 1) {
					title = CommodityBusiness.getGuangjie_CommodityById(tiaomu_id, null, 1).getTitle();
				} else {
					title = CommodityBusiness.getGuangjie_CommodityById(tiaomu_id, null, 2).getTitle();
				}

				Dingdan_model dingdan_model = new Dingdan_model();
				dingdan_model.setId(id);
				dingdan_model.setTitle(title);
				dingdan_model.setTransaction_id(transaction_id);
				dingdan_model.setBuyer_name(buyer_name);
				dingdan_model.setBuyer_touxiang(buyer_touxiang);

				dingdan_model.setSeller_name(seller_name);
				dingdan_model.setSeller_touxiang(seller_touxiang);
				dingdan_model.setShuliang(shuliang);
				dingdan_model.setTotal_price(total_price);
				dingdan_models.add(dingdan_model);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return dingdan_models;
	}

	public static List<Dingdan_model> Fuwu_Ongoing() {// 进行中
		String sql = null;
		DBHelper db1 = null;
		int days = 0;

		sql = "SELECT tiaomu_id,seller_id,buyer_id,shuliang,total_price,id,guangjie_fenlei_Tag,transaction_id FROM dingdan WHERE seller_zhuangtai=3 and buyer_zhuangtai=3 and guangjie_fenlei_Tag=2";
		db1 = new DBHelper(sql);
		List<Dingdan_model> dingdan_models = new ArrayList<Dingdan_model>();
		try {
			ResultSet ret = db1.pst.executeQuery();
			while (ret.next()) {
				String tiaomu_id = ret.getString(1);
				String seller_id = ret.getString(2);
				String seller_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(seller_id).getName();
				String seller_touxiang = Admin_xinxi_Business.getAdmin_xinxiInfoById(seller_id).getTouxiang_picture();

				String buyer_id = ret.getString(3);
				String buyer_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(buyer_id).getName();
				String buyer_touxiang = Admin_xinxi_Business.getAdmin_xinxiInfoById(buyer_id).getTouxiang_picture();
				int shuliang = ret.getInt(4);
				double total_price = ret.getDouble(5);
				String id = ret.getString(6);
				int guangjie_fenlei_Tag = ret.getInt(7);
				String transaction_id = ret.getString(8);
				String title = null;
				if (guangjie_fenlei_Tag == 1) {
					title = CommodityBusiness.getGuangjie_CommodityById(tiaomu_id, null, 1).getTitle();
				} else {
					title = CommodityBusiness.getGuangjie_CommodityById(tiaomu_id, null, 2).getTitle();
				}

				Dingdan_model dingdan_model = new Dingdan_model();
				dingdan_model.setId(id);
				dingdan_model.setTitle(title);
				dingdan_model.setTransaction_id(transaction_id);
				dingdan_model.setBuyer_name(buyer_name);
				dingdan_model.setBuyer_touxiang(buyer_touxiang);

				dingdan_model.setSeller_name(seller_name);
				dingdan_model.setSeller_touxiang(seller_touxiang);
				dingdan_model.setShuliang(shuliang);
				dingdan_model.setTotal_price(total_price);
				dingdan_models.add(dingdan_model);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return dingdan_models;
	}

	public static List<Dingdan_model> Commodity_Controversial() {// 已完成
		String sql = null;
		DBHelper db1 = null;
		int days = 0;

		sql = "SELECT tiaomu_id,seller_id,buyer_id,shuliang,total_price,id,guangjie_fenlei_Tag,transaction_id FROM dingdan WHERE seller_zhuangtai=6 and buyer_zhuangtai=6 and guangjie_fenlei_Tag=1";
		db1 = new DBHelper(sql);
		List<Dingdan_model> dingdan_models = new ArrayList<Dingdan_model>();
		try {
			ResultSet ret = db1.pst.executeQuery();
			while (ret.next()) {
				String tiaomu_id = ret.getString(1);
				String seller_id = ret.getString(2);
				String seller_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(seller_id).getName();
				String seller_touxiang = Admin_xinxi_Business.getAdmin_xinxiInfoById(seller_id).getTouxiang_picture();

				String buyer_id = ret.getString(3);
				String buyer_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(buyer_id).getName();
				String buyer_touxiang = Admin_xinxi_Business.getAdmin_xinxiInfoById(buyer_id).getTouxiang_picture();
				int shuliang = ret.getInt(4);
				double total_price = ret.getDouble(5);
				String id = ret.getString(6);
				int guangjie_fenlei_Tag = ret.getInt(7);
				String transaction_id = ret.getString(8);
				String title = null;
				if (guangjie_fenlei_Tag == 1) {
					title = CommodityBusiness.getGuangjie_CommodityById(tiaomu_id, null, 1).getTitle();
				} else {
					title = CommodityBusiness.getGuangjie_CommodityById(tiaomu_id, null, 2).getTitle();
				}

				Dingdan_model dingdan_model = new Dingdan_model();
				dingdan_model.setId(id);
				dingdan_model.setTitle(title);
				dingdan_model.setTransaction_id(transaction_id);
				dingdan_model.setBuyer_name(buyer_name);
				dingdan_model.setBuyer_touxiang(buyer_touxiang);

				dingdan_model.setSeller_name(seller_name);
				dingdan_model.setSeller_touxiang(seller_touxiang);
				dingdan_model.setShuliang(shuliang);
				dingdan_model.setTotal_price(total_price);
				dingdan_models.add(dingdan_model);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return dingdan_models;
	}

	public static List<Dingdan_model> Fuwu_Controversial() {// 已完成
		String sql = null;
		DBHelper db1 = null;
		int days = 0;

		sql = "SELECT tiaomu_id,seller_id,buyer_id,shuliang,total_price,id,guangjie_fenlei_Tag,transaction_id FROM dingdan WHERE seller_zhuangtai=6 and buyer_zhuangtai=6 and guangjie_fenlei_Tag=2";
		db1 = new DBHelper(sql);
		List<Dingdan_model> dingdan_models = new ArrayList<Dingdan_model>();
		try {
			ResultSet ret = db1.pst.executeQuery();
			while (ret.next()) {
				String tiaomu_id = ret.getString(1);
				String seller_id = ret.getString(2);
				String seller_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(seller_id).getName();
				String seller_touxiang = Admin_xinxi_Business.getAdmin_xinxiInfoById(seller_id).getTouxiang_picture();

				String buyer_id = ret.getString(3);
				String buyer_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(buyer_id).getName();
				String buyer_touxiang = Admin_xinxi_Business.getAdmin_xinxiInfoById(buyer_id).getTouxiang_picture();
				int shuliang = ret.getInt(4);
				double total_price = ret.getDouble(5);
				String id = ret.getString(6);
				int guangjie_fenlei_Tag = ret.getInt(7);
				String transaction_id = ret.getString(8);
				String title = null;
				if (guangjie_fenlei_Tag == 1) {
					title = CommodityBusiness.getGuangjie_CommodityById(tiaomu_id, null, 1).getTitle();
				} else {
					title = CommodityBusiness.getGuangjie_CommodityById(tiaomu_id, null, 2).getTitle();
				}

				Dingdan_model dingdan_model = new Dingdan_model();
				dingdan_model.setId(id);
				dingdan_model.setTitle(title);
				dingdan_model.setTransaction_id(transaction_id);
				dingdan_model.setBuyer_name(buyer_name);
				dingdan_model.setBuyer_touxiang(buyer_touxiang);

				dingdan_model.setSeller_name(seller_name);
				dingdan_model.setSeller_touxiang(seller_touxiang);
				dingdan_model.setShuliang(shuliang);
				dingdan_model.setTotal_price(total_price);
				dingdan_models.add(dingdan_model);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return dingdan_models;
	}

	public static List<Dingdan_model> Fuwu_Completed() {// 有投诉
		String sql = null;
		DBHelper db1 = null;
		int days = 0;

		sql = "SELECT tiaomu_id,seller_id,buyer_id,shuliang,total_price,id,guangjie_fenlei_Tag,transaction_id FROM dingdan WHERE seller_zhuangtai=5 and buyer_zhuangtai=5 and guangjie_fenlei_Tag=2";
		db1 = new DBHelper(sql);
		List<Dingdan_model> dingdan_models = new ArrayList<Dingdan_model>();
		try {
			ResultSet ret = db1.pst.executeQuery();
			while (ret.next()) {
				String tiaomu_id = ret.getString(1);
				String seller_id = ret.getString(2);
				String seller_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(seller_id).getName();
				String seller_touxiang = Admin_xinxi_Business.getAdmin_xinxiInfoById(seller_id).getTouxiang_picture();

				String buyer_id = ret.getString(3);
				String buyer_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(buyer_id).getName();
				String buyer_touxiang = Admin_xinxi_Business.getAdmin_xinxiInfoById(buyer_id).getTouxiang_picture();
				int shuliang = ret.getInt(4);
				double total_price = ret.getDouble(5);
				String id = ret.getString(6);
				int guangjie_fenlei_Tag = ret.getInt(7);
				String transaction_id = ret.getString(8);
				String title = null;
				if (guangjie_fenlei_Tag == 1) {
					title = CommodityBusiness.getGuangjie_CommodityById(tiaomu_id, null, 1).getTitle();
				} else {
					title = CommodityBusiness.getGuangjie_CommodityById(tiaomu_id, null, 2).getTitle();
				}

				Dingdan_model dingdan_model = new Dingdan_model();
				dingdan_model.setId(id);
				dingdan_model.setTitle(title);
				dingdan_model.setTransaction_id(transaction_id);
				dingdan_model.setBuyer_name(buyer_name);
				dingdan_model.setBuyer_touxiang(buyer_touxiang);

				dingdan_model.setSeller_name(seller_name);
				dingdan_model.setSeller_touxiang(seller_touxiang);
				dingdan_model.setShuliang(shuliang);
				dingdan_model.setTotal_price(total_price);
				dingdan_models.add(dingdan_model);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return dingdan_models;
	}

	public static List<Dingdan_model> Commodity_Completed() {// 有投诉
		String sql = null;
		DBHelper db1 = null;
		int days = 0;

		sql = "SELECT tiaomu_id,seller_id,buyer_id,shuliang,total_price,id,guangjie_fenlei_Tag,transaction_id FROM dingdan WHERE seller_zhuangtai=5 and buyer_zhuangtai=5 and guangjie_fenlei_Tag=1";
		db1 = new DBHelper(sql);
		List<Dingdan_model> dingdan_models = new ArrayList<Dingdan_model>();
		try {
			ResultSet ret = db1.pst.executeQuery();
			while (ret.next()) {
				String tiaomu_id = ret.getString(1);
				String seller_id = ret.getString(2);
				String seller_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(seller_id).getName();
				String seller_touxiang = Admin_xinxi_Business.getAdmin_xinxiInfoById(seller_id).getTouxiang_picture();

				String buyer_id = ret.getString(3);
				String buyer_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(buyer_id).getName();
				String buyer_touxiang = Admin_xinxi_Business.getAdmin_xinxiInfoById(buyer_id).getTouxiang_picture();
				int shuliang = ret.getInt(4);
				double total_price = ret.getDouble(5);
				String id = ret.getString(6);
				int guangjie_fenlei_Tag = ret.getInt(7);
				String transaction_id = ret.getString(8);
				String title = null;
				if (guangjie_fenlei_Tag == 1) {
					title = CommodityBusiness.getGuangjie_CommodityById(tiaomu_id, null, 1).getTitle();
				} else {
					title = CommodityBusiness.getGuangjie_CommodityById(tiaomu_id, null, 2).getTitle();
				}

				Dingdan_model dingdan_model = new Dingdan_model();
				dingdan_model.setId(id);
				dingdan_model.setTitle(title);
				dingdan_model.setTransaction_id(transaction_id);
				dingdan_model.setBuyer_name(buyer_name);
				dingdan_model.setBuyer_touxiang(buyer_touxiang);

				dingdan_model.setSeller_name(seller_name);
				dingdan_model.setSeller_touxiang(seller_touxiang);
				dingdan_model.setShuliang(shuliang);
				dingdan_model.setTotal_price(total_price);
				dingdan_models.add(dingdan_model);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return dingdan_models;
	}

	public static List<Dingdan_model> Commodity_Oncancel() {// 已取消
		String sql = null;
		DBHelper db1 = null;
		int days = 0;

		sql = "SELECT tiaomu_id,seller_id,buyer_id,shuliang,total_price,id,guangjie_fenlei_Tag,transaction_id FROM dingdan WHERE seller_zhuangtai=7 and buyer_zhuangtai=7 and guangjie_fenlei_Tag=1";
		db1 = new DBHelper(sql);
		List<Dingdan_model> dingdan_models = new ArrayList<Dingdan_model>();
		try {
			ResultSet ret = db1.pst.executeQuery();
			while (ret.next()) {
				String tiaomu_id = ret.getString(1);
				String seller_id = ret.getString(2);
				String seller_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(seller_id).getName();
				String seller_touxiang = Admin_xinxi_Business.getAdmin_xinxiInfoById(seller_id).getTouxiang_picture();

				String buyer_id = ret.getString(3);
				String buyer_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(buyer_id).getName();
				String buyer_touxiang = Admin_xinxi_Business.getAdmin_xinxiInfoById(buyer_id).getTouxiang_picture();
				int shuliang = ret.getInt(4);
				double total_price = ret.getDouble(5);
				String id = ret.getString(6);
				int guangjie_fenlei_Tag = ret.getInt(7);
				String transaction_id = ret.getString(8);
				String title = null;
				if (guangjie_fenlei_Tag == 1) {
					title = CommodityBusiness.getGuangjie_CommodityById(tiaomu_id, null, 1).getTitle();
				} else {
					title = CommodityBusiness.getGuangjie_CommodityById(tiaomu_id, null, 2).getTitle();
				}

				Dingdan_model dingdan_model = new Dingdan_model();
				dingdan_model.setId(id);
				dingdan_model.setTitle(title);
				dingdan_model.setTransaction_id(transaction_id);
				dingdan_model.setBuyer_name(buyer_name);
				dingdan_model.setBuyer_touxiang(buyer_touxiang);

				dingdan_model.setSeller_name(seller_name);
				dingdan_model.setSeller_touxiang(seller_touxiang);
				dingdan_model.setShuliang(shuliang);
				dingdan_model.setTotal_price(total_price);
				dingdan_models.add(dingdan_model);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return dingdan_models;
	}

	public static List<Dingdan_model> Fuwu_Oncancel() {// 已取消
		String sql = null;
		DBHelper db1 = null;
		int days = 0;

		sql = "SELECT tiaomu_id,seller_id,buyer_id,shuliang,total_price,id,guangjie_fenlei_Tag,transaction_id FROM dingdan WHERE seller_zhuangtai=7 and buyer_zhuangtai=7 and guangjie_fenlei_Tag=2";
		db1 = new DBHelper(sql);
		List<Dingdan_model> dingdan_models = new ArrayList<Dingdan_model>();
		try {
			ResultSet ret = db1.pst.executeQuery();
			while (ret.next()) {
				String tiaomu_id = ret.getString(1);
				String seller_id = ret.getString(2);
				String seller_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(seller_id).getName();
				String seller_touxiang = Admin_xinxi_Business.getAdmin_xinxiInfoById(seller_id).getTouxiang_picture();

				String buyer_id = ret.getString(3);
				String buyer_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(buyer_id).getName();
				String buyer_touxiang = Admin_xinxi_Business.getAdmin_xinxiInfoById(buyer_id).getTouxiang_picture();
				int shuliang = ret.getInt(4);
				double total_price = ret.getDouble(5);
				String id = ret.getString(6);
				int guangjie_fenlei_Tag = ret.getInt(7);
				String transaction_id = ret.getString(8);
				String title = null;
				if (guangjie_fenlei_Tag == 1) {
					title = CommodityBusiness.getGuangjie_CommodityById(tiaomu_id, null, 1).getTitle();
				} else {
					title = CommodityBusiness.getGuangjie_CommodityById(tiaomu_id, null, 2).getTitle();
				}

				Dingdan_model dingdan_model = new Dingdan_model();
				dingdan_model.setId(id);
				dingdan_model.setTitle(title);
				dingdan_model.setTransaction_id(transaction_id);
				dingdan_model.setBuyer_name(buyer_name);
				dingdan_model.setBuyer_touxiang(buyer_touxiang);

				dingdan_model.setSeller_name(seller_name);
				dingdan_model.setSeller_touxiang(seller_touxiang);
				dingdan_model.setShuliang(shuliang);
				dingdan_model.setTotal_price(total_price);
				dingdan_models.add(dingdan_model);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return dingdan_models;
	}

	public static List<User_model> get_User() {// 已取消
		String sql = null;
		DBHelper db1 = null;

		sql = "SELECT touxiang_picture,name,qianbao,id,time,qianming FROM admin_xinxi WHERE yonghu_Tag=3";
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
				String renzheng_message = getrenzheng(id);

				User_model model = new User_model();
				model.setTouxiang(touxiang);
				model.setName(name);
				model.setFensi(fensi);
				model.setGuanzhu(guanzhu);
				model.setTime(time);
				model.setQianbao(qianbao);
				model.setId(id);
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

	public static List<User_model> get_mingren() {// 已取消
		String sql = null;
		DBHelper db1 = null;

		sql = "SELECT touxiang_picture,name,qianbao,id,time,qianming FROM admin_xinxi WHERE yonghu_Tag=1";
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
				String renzheng_message = getrenzheng(id);

				User_model model = new User_model();
				model.setTouxiang(touxiang);
				model.setName(name);
				model.setFensi(fensi);
				model.setGuanzhu(guanzhu);
				model.setTime(time);
				model.setQianbao(qianbao);
				model.setId(id);
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

	public static List<User_model> get_qiye() {// 已取消
		String sql = null;
		DBHelper db1 = null;

		sql = "SELECT touxiang_picture,name,qianbao,id,time,qianming FROM admin_xinxi WHERE yonghu_Tag=5";
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
				String renzheng_message = getrenzheng(id);

				User_model model = new User_model();
				model.setTouxiang(touxiang);
				model.setName(name);
				model.setFensi(fensi);
				model.setGuanzhu(guanzhu);
				model.setTime(time);
				model.setQianbao(qianbao);
				model.setId(id);
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

	public static List<User_model> get_bianji() {// 已取消
		String sql = null;
		DBHelper db1 = null;

		sql = "SELECT touxiang_picture,name,qianbao,id,time,qianming FROM admin_xinxi WHERE yonghu_Tag=2";
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
				String renzheng_message = getrenzheng(id);

				User_model model = new User_model();
				model.setTouxiang(touxiang);
				model.setName(name);
				model.setFensi(fensi);
				model.setGuanzhu(guanzhu);
				model.setTime(time);
				model.setQianbao(qianbao);
				model.setId(id);
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

	public static List<User_model> get_tuijian_user() {// 已取消
		String sql = null;
		DBHelper db1 = null;

		sql = "select DISTINCT * FROM(SELECT touxiang_picture,name,qianbao,id,time,qianming FROM admin_xinxi) a LEFT OUTER JOIN (SELECT tuijian_user_id from tuijian_user) b ON a.id=b.tuijian_user_id  WHERE b.tuijian_user_id IS not null";
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
				String renzheng_message = getrenzheng(id);

				User_model model = new User_model();
				model.setTouxiang(touxiang);
				model.setName(name);
				model.setFensi(fensi);
				model.setGuanzhu(guanzhu);
				model.setTime(time);
				model.setQianbao(qianbao);
				model.setId(id);
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

	public static String getrenzheng(String id) {// 已取消
		String sql = null;
		DBHelper db1 = null;
		int days = 0;

		sql = "SELECT renzheng_Tag FROM renzheng WHERE my_id='" + id + "'";
		db1 = new DBHelper(sql);
		List<Dingdan_model> dingdan_models = new ArrayList<Dingdan_model>();
		int renzheng_Tag;
		String flag = null;
		try {
			ResultSet ret = db1.pst.executeQuery();
			while (ret.next()) {
				renzheng_Tag = ret.getInt(1);

				if (renzheng_Tag == 1) {
					flag = "未认证";
				} else if (renzheng_Tag == 2) {
					flag = "认证中";
				} else if (renzheng_Tag == 3) {
					flag = "已认证";
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return flag;
	}

	public static List<Guanggao_model> getguanggao() {// 已取消
		String sql = null;
		DBHelper db1 = null;
		int days = 0;
		List<Guanggao_model> guanggaos = new ArrayList<Guanggao_model>();
		sql = "SELECT picture,guanggao_id,fenlei_Tag,guangjie_fenlei_Tag,id,time,shangjia_Tag FROM advertisement";
		db1 = new DBHelper(sql);
		int renzheng_Tag;
		try {
			ResultSet ret = db1.pst.executeQuery();
			while (ret.next()) {
				String picture = ret.getString(1);
				String guanggao_id = ret.getString(2);
				int fenlei_Tag = ret.getInt(3);
				int guangjie_fenlei_Tag = ret.getInt(4);
				String id = ret.getString(5);
				String url = null;
				Timestamp time = ret.getTimestamp(6);
				int shangjia_Tag = ret.getInt(7);
				String shangjia_message;
				if (shangjia_Tag == 1) {
					shangjia_message = "已上架";
				} else {
					shangjia_message = "已下架";
				}

				if (fenlei_Tag == 1) {
					url = "http://120.92.169.86/mServer/Toutiao_clickInq?id=" + guanggao_id;
				} else if (fenlei_Tag == 2) {
					url = "http://120.92.169.86/mServer/Faxian_clickInq?id=" + guanggao_id;
				} else if (fenlei_Tag == 3) {
					url = "http://120.92.169.86/mServer/Quchu_clickInq?id=" + guanggao_id;
				} else if (fenlei_Tag == 4) {
					url = "http://120.92.169.86/mServer/Guangjie_clickInq?id=" + guanggao_id + "&guangjie_fenlei_Tag="
							+ guangjie_fenlei_Tag;
				}

				Guanggao_model guanggao_model = new Guanggao_model();
				guanggao_model.setUrl(url);
				guanggao_model.setPicture(picture);
				guanggao_model.setId(id);
				guanggao_model.setTime(time);
				guanggao_model.setShangjia_Tag(shangjia_Tag);
				guanggao_model.setShangjia_message(shangjia_message);
				guanggaos.add(guanggao_model);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return guanggaos;
	}

	public static boolean deleteHuifu(String id) {

		String sql = "DELETE FROM huifu where id=?";

		DBHelper db1 = null;
		ResultSet ret = null;

		try {

			db1 = new DBHelper(sql);
			db1.pst.setString(1, id);
			boolean isDelete = db1.pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}
		return false;
	}

	public static void add_user(String zhanghao, String user_name, String password, String sex, String qianming,
			String touxiang_picture, String yonghu_Tag) {

		String sql = null;
		String sql1 = null;
		DBHelper db1 = null;
		DBHelper db2 = null;
		String uuid = UUID.randomUUID().toString();
		Timestamp time = new Timestamp(new Date().getTime());

		sql = "INSERT INTO admin_xinxi(id,touxiang_picture,name,sex,qianming,time,yonghu_Tag) values('" + uuid + "','"
				+ touxiang_picture + "','" + user_name + "','" + sex + "','" + qianming + "','" + time + "','"
				+ yonghu_Tag + "')";
		sql1 = "INSERT INTO login(id,phone,password) values('" + uuid + "','" + zhanghao + "','" + password + "')";

		try {
			db1 = new DBHelper(sql);
			boolean ret = db1.pst.execute();
			db2 = new DBHelper(sql1);
			boolean ret1 = db2.pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db2.close();
			db1.close();

		}

	}

}
