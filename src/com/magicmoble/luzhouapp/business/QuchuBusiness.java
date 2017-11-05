package com.magicmoble.luzhouapp.business;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.taglibs.standard.tag.common.xml.IfTag;

import com.magicmoble.luzhouapp.model.Biaoqian;
import com.magicmoble.luzhouapp.model.ContentModel;
import com.magicmoble.luzhouapp.model.Dianpu;
import com.magicmoble.luzhouapp.model.Dianzan_Number;
import com.magicmoble.luzhouapp.model.Dianzan_message;
import com.magicmoble.luzhouapp.model.Dianzan_touxiang;
import com.magicmoble.luzhouapp.model.Hongbao;
import com.magicmoble.luzhouapp.model.Picture;
import com.magicmoble.luzhouapp.model.Pinglun;
import com.magicmoble.luzhouapp.model.Quchu;
import com.magicmoble.luzhouapp.model.Quchu_Shouye;
import com.magicmoble.luzhouapp.model.Quchu_Xiangqing;
import com.magicmoble.luzhouapp.model.Tuijian;

public class QuchuBusiness {
	public static List<Quchu_Shouye> getAllQuchu(String my_id, int page, int num) {
		List<Quchu_Shouye> list = new ArrayList<Quchu_Shouye>();
		List<Quchu_Shouye> list3 = new ArrayList<Quchu_Shouye>();

		DBHelper db1 = null;
		DBHelper db2 = null;
		String sql = null;
		String sql1 = null;
		List<String> guanzhus = new ArrayList<String>();
		if (my_id != null) {
			guanzhus = GuanzhuBusiness.getFriend_id(my_id, 0);
		}
		if (my_id != null) {
			sql = "select id,title,picture,releaser_id from quchu where ";
			if (guanzhus.size() != 0) {
				for (int i = 0; i < guanzhus.size(); i++) {
					if (i == 0) {
						sql += "(releaser_id='" + guanzhus.get(i);
					}
					if ((i != 0 && i != guanzhus.size())) {
						sql += "releaser_id='" + guanzhus.get(i);
					}
					if (i == guanzhus.size() - 1) {
						sql += "') and";
					}
					if (guanzhus.size() - 1 > i) {
						sql += "' or ";
					}
				}
			}
			sql += " shenhe like '%已发布%' order by rand() LIMIT  6 ";
		}

		sql1 = "select * from (select id,title,picture,releaser_id from quchu where  shenhe like '%已发布%' order by time LIMIT "
				+ (page - 1) * 12 + "," + 12 * page + ") a ";

		if (my_id != null) {
			db1 = new DBHelper(sql);
		}

		ResultSet ret = null;
		ResultSet ret2 = null;
		try {
			if (my_id != null) {

				if (page == 1) {
					ret = db1.pst.executeQuery();
					while (ret.next()) {
						String id = ret.getString(1);
						String title = ret.getString(2);
						String picture = ret.getString(3);
						String[] aa = picture.split(",");
						List<Picture> list2 = new ArrayList<Picture>();
						for (int i = 0; i < aa.length; i++) {
							Picture picture_url = new Picture();
							picture_url.setPicture_url(aa[i]);
							list2.add(picture_url);
						}

						int dianzan_counts = FunctionBusiness.getDianzanNumber(id, 1).getDianzan_count();
						int pinglun_count = FunctionBusiness.getPinglun_size(id);
						String releaser_id = ret.getString(4);
						String releaser_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id).getName();

						Quchu_Shouye quchu = new Quchu_Shouye();
						quchu.setQuchu_id(id);
						quchu.setTitle(title);
						quchu.setPicture(list2);
						quchu.setDianzan_count(dianzan_counts);
						quchu.setReleaser_name(releaser_name);
						quchu.setPinglun_count(pinglun_count);
						list.add(quchu);
					}
				}
			}
			db2 = new DBHelper(sql1);
			ret2 = db2.pst.executeQuery();
			while (ret2.next()) {
				String id = ret2.getString(1);
				String title = ret2.getString(2);
				String picture = ret2.getString(3);
				String[] aa = picture.split(",");
				List<Picture> list2 = new ArrayList<Picture>();
				for (int i = 0; i < aa.length; i++) {
					Picture picture_url = new Picture();
					picture_url.setPicture_url(aa[i]);
					list2.add(picture_url);
				}

				int dianzan_counts = FunctionBusiness.getDianzanNumber(id, 1).getDianzan_count();
				int pinglun_count = FunctionBusiness.getPinglun_size(id);
				String releaser_id = ret2.getString(4);
				String releaser_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id).getName();

				Quchu_Shouye quchu = new Quchu_Shouye();
				quchu.setQuchu_id(id);
				quchu.setTitle(title);
				quchu.setPicture(list2);
				quchu.setDianzan_count(dianzan_counts);
				quchu.setReleaser_name(releaser_name);
				quchu.setPinglun_count(pinglun_count);
				list3.add(quchu);
			}

			list3.removeAll(list);
			Collections.shuffle(list3);
			Collections.shuffle(list);
			for (int i = 0; i < list3.size(); i++) {
				list.add(list3.get(i));
			}

			ret2.close();
			if (my_id != null) {
				if (page == 1) {
					ret.close();
					db1.close();
				}
			}

			db2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static Quchu_Xiangqing getQuchuById(String quchu_id, String my_id) {
		String sql = null;
		DBHelper db1 = null;
		sql = "select id,picture,title,dianpu_touxiang,dianpu_name,dianpu_address,renzheng_Tag,content,shoucang_Tag,guanggao_url,biaoqian,phone,shenhe,time,yuedu,releaser_id from quchu where id='"
				+ quchu_id + "'";
		db1 = new DBHelper(sql);
		List<Biaoqian> biaoqian_list = new ArrayList<Biaoqian>();
		Quchu_Xiangqing quchu = new Quchu_Xiangqing();
		ResultSet ret = null;
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
				if (list2.size() == 0) {
					Picture picture_url = new Picture();
					picture_url.setPicture_url("1111");
					list2.add(picture_url);
				}

				String title = ret.getString(3);
				int dashang_count = FunctionBusiness.getDashangNumber(id);

				int dianzan_counts = FunctionBusiness.getDianzanNumber(id, 1).getDianzan_count();

				String dianpu_touxiang = ret.getString(4);
				String dianpu_name = ret.getString(5);
				String dianpu_address = ret.getString(6);
				int renzheng_Tag = ret.getInt(7);// 1 未认证 2认证中 3已认证

				String content = ret.getString(8);
				String[] contents = content.split("<--分隔符-->");
				// List<ContentModel> list3 = new ArrayList<ContentModel>();
				// ContentModel Model = new ContentModel();
				String str = "<div class='article-content'>";
				for (int i = 0; i < contents.length; i++) {
					if (i % 2 == 0) {
						// Model.setText(contents[i]);
						str += "<p>" + contents[i] + "</p>";
					} else {
						// Model.setPicture_url(contents[i]);
						str += "<img src=" + contents[i] + ">";
						// list3.add(Model);
						// Model = new ContentModel();
					}
				}
				str += "</div>";
				boolean shoucang_Tag = false;
				int isshoucang = FunctionBusiness.getshoucang(id, my_id);
				if (isshoucang == 1) {
					shoucang_Tag = false;
				} else if (isshoucang == 2) {
					shoucang_Tag = true;
				}

				Hongbao dianzan_hongbao = FunctionBusiness.getHongbaoById(id, 1);
				Hongbao fenxiang_hongbao = FunctionBusiness.getHongbaoById(id, 2);
				boolean have_dianzan_hongbao = (dianzan_hongbao.getCount() != 0);
				boolean have_fenxiang_hongbao = (fenxiang_hongbao.getCount() != 0);
				String guanggao_url = ret.getString(10);
				String biaoqian = ret.getString(11);
				if (biaoqian != null) {
					String[] biaoqians = biaoqian.split(",");
					for (int i = 0; i < biaoqians.length; i++) {
						Biaoqian biaoqian2 = new Biaoqian();
						biaoqian2.setBiaoqian(biaoqians[i]);
						biaoqian_list.add(biaoqian2);
					}
				}
				List<Tuijian> tuijian = null;
				if (biaoqian_list.size() != 0) {
					tuijian = getTuijianByBiaoqian(biaoqian_list);
				}

				if (tuijian == null) {
					tuijian = new ArrayList<Tuijian>();
				}
				String phone = ret.getString(12);
				String shenhe = ret.getString(13);
				Timestamp time = ret.getTimestamp(14);
				int yuedu = ret.getInt(15);
				int pinglun_count = FunctionBusiness.getPinglun_size(id);
				boolean isdianzan = false;
				if (my_id == null) {
					isdianzan = false;
				} else {
					isdianzan = FunctionBusiness.getDianzan(my_id, id, 1);
				}

				String releaser_id = ret.getString(16);

				quchu.setUid(id);
				quchu.setPicture(list2);
				quchu.setTitle(title);
				quchu.setDashang_count(dashang_count);
				quchu.setDianzan_count(dianzan_counts);
				quchu.setDianpu_touxiang(dianpu_touxiang);
				quchu.setDianpu_name(dianpu_name);
				quchu.setDianpu_address(dianpu_address);
				quchu.setRenzheng_Tag(renzheng_Tag);
				quchu.setContent(str);
				quchu.setTuijian(tuijian);
				quchu.setShoucang_Tag(shoucang_Tag);
				quchu.setDianzan_hongbao(dianzan_hongbao);
				quchu.setFenxiang_hongbao(fenxiang_hongbao);
				quchu.setGuanggao_url(guanggao_url);
				quchu.setBiaoqian(biaoqian_list);
				quchu.setPhone(phone);
				quchu.setShenhe(shenhe);
				quchu.setTime(time);
				quchu.setYuedu(yuedu);
				quchu.setPinglun_count(pinglun_count);
				quchu.setIsdianzan(isdianzan);
				quchu.setReleaser_id(releaser_id);
				quchu.setHave_dianzan_hongbao(have_dianzan_hongbao);
				quchu.setHave_fenxiang_hongbao(have_fenxiang_hongbao);
				FunctionBusiness.addZujiByQuchu(my_id, id, picture, title);
			}

			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quchu;
	}

	public static List<Tuijian> getTuijianByBiaoqian(List<Biaoqian> biaoqian) {
		String sql = null;
		DBHelper db1 = null;
		sql = "select id,title,dianpu_name,picture,time from quchu WHERE biaoqian like '%"
				+ biaoqian.get(0).getBiaoqian() + "%'";
		for (int i = 1; i < biaoqian.size(); i++) {
			sql += "or  biaoqian like '%" + biaoqian.get(i).getBiaoqian() + "%'";

		}

		sql += "ORDER BY RAND() LIMIT 3";

		db1 = new DBHelper(sql);
		ResultSet ret = null;
		List<Tuijian> list = new ArrayList<Tuijian>();
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
				Timestamp time = ret.getTimestamp(5);

				Tuijian tuijian = new Tuijian();
				tuijian.setTuijian_id(id);
				tuijian.setPicture(list2);
				tuijian.setTitle(title);
				tuijian.setReleaser_name(name);
				tuijian.setTime(time);

				list.add(tuijian);
			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static boolean addQuchu(String my_id, String title, String address, String touxiang, String phone,
			String picture, String content) {

		String sql = null;
		DBHelper db1 = null;
		String uuid = UUID.randomUUID().toString();
		int fenlei_Tag = 2;
		int renzheng_Tag = 1;
		String shenhe = "正在审核中...";
		Timestamp time = new Timestamp(new Date().getTime());
		sql = "INSERT INTO quchu(id,title,dianpu_address,dianpu_touxiang,phone,picture,content,dianpu_name,renzheng_Tag,shenhe,time,releaser_id,fenlei_Tag) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		boolean ret = false;
		db1 = new DBHelper(sql);
		try {
			db1.pst.setString(1, uuid);
			db1.pst.setString(2, title);
			db1.pst.setString(3, address);
			db1.pst.setString(4, touxiang);
			db1.pst.setString(5, phone);
			db1.pst.setString(6, picture);
			db1.pst.setString(7, content);
			db1.pst.setString(8, title);
			db1.pst.setInt(9, renzheng_Tag);
			db1.pst.setString(10, shenhe);
			db1.pst.setTimestamp(11, time);
			db1.pst.setString(12, my_id);
			db1.pst.setInt(13, fenlei_Tag);

			ret = db1.pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db1.close();
		return ret;
	}

	public static void DeleteQuchu(String uuid) {
		{
			String sql = null;
			DBHelper db1 = null;
			sql = "DELETE FROM quchu WHERE id =  " + uuid;
			db1 = new DBHelper(sql);
			try {
				boolean ret = db1.pst.execute();
				db1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public static void quchu_yuedu(String id) {

		DBHelper db2 = null;
		String sql1 = null;
		sql1 = "UPDATE quchu SET yuedu=yuedu+1 WHERE id='" + id + "'";
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

	public static List<Quchu_Shouye> getGuangguang(String quchu_id, String my_id, int page) {
		String sql = null;
		DBHelper db1 = null;

		sql = "SELECT id,title,picture,dianpu_id FROM commodity where buyer_id = 'null' AND shenhe LIKE '已发布%'  and dianpu_id='"
				+ quchu_id
				+ "'  UNION SELECT id ,title,picture,dianpu_id FROM fuwu  where  shenhe LIKE '已发布%'  and dianpu_id='"
				+ quchu_id + "' limit " + (page - 1) * 12 + "," + 12 * page;

		db1 = new DBHelper(sql);
		ResultSet ret = null;
		List<Quchu_Shouye> list = new ArrayList<Quchu_Shouye>();

		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				String id = ret.getString(1);
				String title = ret.getString(2);

				String picture = ret.getString(3);
				String[] aa = picture.split(",");
				List<Picture> list2 = new ArrayList<Picture>();
				for (int i = 0; i < aa.length; i++) {
					Picture picture_url = new Picture();
					picture_url.setPicture_url(aa[i]);
					list2.add(picture_url);
				}
				String dianpu_id = ret.getString(4);
				String dianpu_name = getQuchuById(dianpu_id, my_id).getDianpu_name();

				int dianzan_counts = FunctionBusiness.getDianzanNumber(id, 1).getDianzan_count();
				int pinglun_count = FunctionBusiness.getPinglun_size(id);

				Quchu_Shouye quchu_Shouye = new Quchu_Shouye();
				quchu_Shouye.setQuchu_id(id);
				quchu_Shouye.setTitle(title);
				quchu_Shouye.setReleaser_name(dianpu_name);
				quchu_Shouye.setPicture(list2);
				quchu_Shouye.setDianzan_count(dianzan_counts);
				quchu_Shouye.setPinglun_count(pinglun_count);

				list.add(quchu_Shouye);
			}

			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
