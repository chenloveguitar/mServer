package com.magicmoble.luzhouapp.business;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.swing.text.TabableView;

import com.magicmoble.luzhouapp.model.Admin_xinxi;
import com.magicmoble.luzhouapp.model.Biaoqian;
import com.magicmoble.luzhouapp.model.ContentModel;
import com.magicmoble.luzhouapp.model.Dashang;
import com.magicmoble.luzhouapp.model.Dashang_list;
import com.magicmoble.luzhouapp.model.Dashang_user;
import com.magicmoble.luzhouapp.model.Dianpu;
import com.magicmoble.luzhouapp.model.Dianzan_Number;
import com.magicmoble.luzhouapp.model.Faxian;
import com.magicmoble.luzhouapp.model.Faxian_Shouye;
import com.magicmoble.luzhouapp.model.Fuwu;
import com.magicmoble.luzhouapp.model.Guangjie_Shouye;
import com.magicmoble.luzhouapp.model.Guangjie_Xiangqing;
import com.magicmoble.luzhouapp.model.Guanzhu;
import com.magicmoble.luzhouapp.model.Hongbao;
import com.magicmoble.luzhouapp.model.My_dianpu;
import com.magicmoble.luzhouapp.model.Picture;
import com.magicmoble.luzhouapp.model.Quchu_Xiangqing;
import com.magicmoble.luzhouapp.model.Toutiao_Shouye;
import com.magicmoble.luzhouapp.model.Tuijian;
import com.magicmoble.luzhouapp.model.Zhuye_Model;

public class ZhuyeBusiness {

	public static Zhuye_Model getZhuye(String my_id, String yonghu_id) {
		String sql = "select name,touxiang_picture,qianming,sex from admin_xinxi where id='" + yonghu_id + "'";
		String sql1 = "select id from quchu where releaser_id='" + yonghu_id + "'";
		DBHelper db1 = null;
		DBHelper db2 = null;
		ResultSet ret = null;
		ResultSet ret2 = null;

		Zhuye_Model model = new Zhuye_Model();
		List<Dianpu> dianpus = new ArrayList<Dianpu>();
		try {
			db2 = new DBHelper(sql1);
			ret2 = db2.pst.executeQuery();
			while (ret2.next()) {
				String dianpu_id = ret2.getString(1);
				Dianpu dianpu2 = new Dianpu();
				dianpu2.setDianpu_id(dianpu_id);
				dianpus.add(dianpu2);

			}
			db1 = new DBHelper(sql);
			ret = db1.pst.executeQuery();
			while (ret.next()) {

				String name = ret.getString(1);
				String touxiang = ret.getString(2);
				String qianming = ret.getString(3);
				String _yonghu_id = yonghu_id;
				String sex = ret.getString(4);
				int guanzhu_count = GuanzhuBusiness.getFriend_id(yonghu_id, 0).size();
				int fensi_count = GuanzhuBusiness.getMy_id(yonghu_id, 0).size();
				boolean isguanzhu = GuanzhuBusiness.getIsGuanzhu(my_id, yonghu_id);

				model.setName(name);
				model.setQianming(qianming);
				model.setTouxiang(touxiang);
				model.setYonghu_id(_yonghu_id);
				model.setGuanzhu_count(guanzhu_count);
				model.setFensi_count(fensi_count);
				model.setIsguanzhu(isguanzhu);
				model.setSex(sex);
				if (dianpus == null) {
					model.setDianpu(new ArrayList<Dianpu>());
				} else {
					model.setDianpu(dianpus);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}

	public static List<Toutiao_Shouye> getZhuye_Toutiao(String my_id, String yonghu_id, int page) {
		String sql = "select id,title,name,picture,muban_Tag from toutiao where releaser_id='" + yonghu_id + " 'LIMIT "
				+ (page - 1) * 12 + "," + 12 * page;
		DBHelper db1 = null;
		ResultSet ret = null;
		db1 = new DBHelper(sql);
		List<Toutiao_Shouye> list = new ArrayList<Toutiao_Shouye>();
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {

				String id = ret.getString(1);
				String title = ret.getString(2);
				String name = ret.getString(3);
				String picture = ret.getString(4);
				String[] aa = picture.split(",");

				List<Picture> list2 = new ArrayList<Picture>();
				for (int i = 0; i < aa.length; i++) {
					Picture picture_url = new Picture();
					picture_url.setPicture_url(aa[i]);
					list2.add(picture_url);
				}

				int pinglun_count = FunctionBusiness.getPinglun_size(id);

				int muban_Tag = ret.getInt(5);
				Dianzan_Number dianzan_count = FunctionBusiness.getDianzanNumber(id, 3);

				Toutiao_Shouye toutiao = new Toutiao_Shouye();
				toutiao.setToutiao_id(id);
				toutiao.setPicture(list2);
				toutiao.setTitle(title);
				toutiao.setName(name);
				toutiao.setDianzan_count(dianzan_count);
				toutiao.setPinglun_count(pinglun_count);
				toutiao.setMuban_Tag(muban_Tag);
				toutiao.setGuanggao_Tag(0);
				list.add(toutiao);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public static List<Faxian_Shouye> getZhuye_Faxian(String my_id, String yonghu_id, int page) {
		String sql = "select id,picture,title,name from faxian where releaser_id='" + yonghu_id + " 'LIMIT "
				+ (page - 1) * 12 + "," + 12 * page;
		DBHelper db1 = null;
		ResultSet ret = null;
		db1 = new DBHelper(sql);
		List<Faxian_Shouye> list = new ArrayList<Faxian_Shouye>();
		try {
			ret = db1.pst.executeQuery();
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
				String name = ret.getString(4);
				int pinglun_count = FunctionBusiness.getPinglun_size(id);

				int dianzan_counts = FunctionBusiness.getDianzanNumber(id, 4).getDianzan_count();
				Faxian_Shouye faxian = new Faxian_Shouye();
				faxian.setFaxian_id(id);
				faxian.setPicture(list2);
				faxian.setTitle(title);
				faxian.setReleaser_name(name);
				faxian.setPinglun_count(pinglun_count);
				faxian.setDianzan_count(dianzan_counts);

				list.add(faxian);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public static List<Guangjie_Shouye> getZhuye_Guangjie(String my_id, String yonghu_id, int page) {
		String sql = "select id,title,picture,releaser_id,guangjie_fenlei_Tag from (SELECT id ,title,picture,releaser_id,shenhe,time,guangjie_fenlei_Tag FROM commodity where releaser_id='"
				+ yonghu_id
				+ "' AND shenhe LIKE '已发布%'   UNION SELECT id ,title,picture,releaser_id,shenhe,time,guangjie_fenlei_Tag FROM fuwu  where releaser_id='"
				+ yonghu_id + "' and   shenhe LIKE '已发布%' ) a order by time LIMIT " + (page - 1) * 12 + "," + 12 * page;
		DBHelper db1 = null;
		ResultSet ret = null;
		db1 = new DBHelper(sql);
		List<Guangjie_Shouye> list = new ArrayList<Guangjie_Shouye>();
		try {
			int dianzan_counts = 0;
			ret = db1.pst.executeQuery();
			while (ret.next()) {

				String id = ret.getString(1);

				String title = ret.getString(2);
				String picture = ret.getString(3);
				String[] pictures = picture.split(",");
				List<Picture> list2 = new ArrayList<Picture>();
				for (int i = 0; i < pictures.length; i++) {
					Picture picture_url = new Picture();
					picture_url.setPicture_url(pictures[i]);
					list2.add(picture_url);
				}
				String releaser_id = ret.getString(4);
				int guangjie_fenlei_Tag = ret.getInt(5);
				String releaser_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id).getName();
				if (guangjie_fenlei_Tag == 1) {
					dianzan_counts = FunctionBusiness.getDianzanNumber(id, 6).getDianzan_count();
				} else {
					dianzan_counts = FunctionBusiness.getDianzanNumber(id, 5).getDianzan_count();
				}

				int pinglun_count = FunctionBusiness.getPinglun_size(id);

				Guangjie_Shouye guangjie = new Guangjie_Shouye();
				guangjie.setGuangjie_id(id);
				guangjie.setTitle(title);
				guangjie.setPicture(list2);
				guangjie.setReleaser_name(releaser_name);
				guangjie.setDianzan_count(dianzan_counts);
				guangjie.setPinglun_count(pinglun_count);
				guangjie.setGuangjie_fenlei_Tag(guangjie_fenlei_Tag);

				list.add(guangjie);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public static void add_fangke(String my_id, String fangke_id) {
		String uuid = UUID.randomUUID().toString();
		Timestamp time = new Timestamp(new Date().getTime());
		String sql = "INSERT INTO fangke(id,my_id,fangke_id,time) VALUES ('" + uuid + "','" + my_id + "','" + fangke_id
				+ "','" + time + "')";
		DBHelper db1 = null;
		db1 = new DBHelper(sql);

		try {
			boolean flag = db1.pst.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db1.close();
		}

	}

}
