package com.magicmoble.luzhouapp.business;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.magicmoble.luzhouapp.model.Biaoqian;
import com.magicmoble.luzhouapp.model.ContentModel;
import com.magicmoble.luzhouapp.model.Dianzan_Number;
import com.magicmoble.luzhouapp.model.Dianzan_message;
import com.magicmoble.luzhouapp.model.Dianzan_touxiang;
import com.magicmoble.luzhouapp.model.Faxian;
import com.magicmoble.luzhouapp.model.Fuwu;
import com.magicmoble.luzhouapp.model.Guangjie_Xiangqing;
import com.magicmoble.luzhouapp.model.Hongbao;
import com.magicmoble.luzhouapp.model.Picture;
import com.magicmoble.luzhouapp.model.Quchu_Xiangqing;
import com.magicmoble.luzhouapp.model.Tuijian;

public class FuwuBusiness {

	public static void Fuwu_yuedu(String guangjie_id) {
		String sql1 = null;
		sql1 = "UPDATE fuwu SET yuedu=yuedu+1 WHERE id='" + guangjie_id + "'";
		DBHelper db2 = null;
		db2 = new DBHelper(sql1);
		int ret1 = 0;
		try {
			ret1 = db2.pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db2.close();
	}

	public static Guangjie_Xiangqing getGuangjie_FuwuById(String guangjie_id, String my_id, int guangjie_fenlei_Tag) {
		String sql = null;
		DBHelper db1 = null;
		List<Biaoqian> biaoqian_list = new ArrayList<Biaoqian>();

		sql = "select id,picture,title,releaser_id,yuedu,price,unit,dianpu_id,content,biaoqian,shoucang_Tag,guanggao_url,phone,time from fuwu WHERE id='"
				+ guangjie_id + "' and shenhe LIKE '%已发布%' and guangjie_fenlei_Tag=2";
		db1 = new DBHelper(sql);
		Guangjie_Xiangqing guangjie = new Guangjie_Xiangqing();
		ResultSet ret = null;
		boolean isguanzhu = false;
		int ret1 = 0;

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
				int dashang_count = FunctionBusiness.getDashangNumber(id);

				int dianzan_counts = FunctionBusiness.getDianzanNumber(id, 5).getDianzan_count();
				String releaser_id = ret.getString(4);
				String releaser_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id).getName();
				String releaser_touxiang = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id)
						.getTouxiang_picture();

				int flag = FunctionBusiness.getGuanzhu_Tag(my_id, releaser_id);
				int yuedu = ret.getInt(5);
				if (flag != 0) {
					isguanzhu = true;
				} else {
					isguanzhu = false;
				}

				double price = ret.getDouble(6);
				String unit = ret.getString(7);
				String dianpu_id = ret.getString(8);
				Quchu_Xiangqing quchu_Xiangqing = QuchuBusiness.getQuchuById(dianpu_id, my_id);
				String dianpu_name = quchu_Xiangqing.getDianpu_name();
				String dianpu_touxiang = quchu_Xiangqing.getDianpu_touxiang();
				String dianpu_address = quchu_Xiangqing.getDianpu_address();
				int renzheng_Tag = quchu_Xiangqing.getRenzheng_Tag();

				int dianpu_pinglun_count = FunctionBusiness.getPinglun_size(dianpu_id);

				String content = ret.getString(9);
				String[] contents = content.split("<--分隔符-->");

				// List<ContentModel> list3 = new ArrayList<ContentModel>();
				// ContentModel Model = new ContentModel();
				String str = "<div>";
				for (int i = 0; i < contents.length; i++) {

					if (i % 2 == 0) {

						// Model.setText(contents[i]);
						str += "<span>" + contents[i] + "</span>";
					} else {
						// Model.setPicture_url(contents[i]);
						str += "<img src=" + contents[i] + ">";
						// list3.add(Model);
						// Model = new ContentModel();
					}

				}
				str += "</div>";
				String biaoqian = ret.getString(10);
				List<Tuijian> tuijian;
				if (biaoqian != null) {
					String[] biaoqians = biaoqian.split(",");

					for (int i = 0; i < biaoqians.length; i++) {
						Biaoqian biaoqian2 = new Biaoqian();
						biaoqian2.setBiaoqian(biaoqians[i]);
						biaoqian_list.add(biaoqian2);
					}
					tuijian = CommodityBusiness.getTuijianByBiaoqian(biaoqian_list, guangjie_fenlei_Tag);
				} else {
					tuijian = new ArrayList<Tuijian>();

				}

				Hongbao dianzan_hongbao = FunctionBusiness.getHongbaoById(guangjie_id, 1);
				Hongbao fenxiang_hongbao = FunctionBusiness.getHongbaoById(guangjie_id, 2);
				String guanggao_url = ret.getString(12);

				String phone = ret.getString(13);
				Timestamp time = ret.getTimestamp(14);
				int pinglun_count = FunctionBusiness.getPinglun_size(id);
				boolean isdianzan = false;
				if (my_id == null) {
					isdianzan = false;

				} else {
					isdianzan = FunctionBusiness.getDianzan(my_id, id, 5);
				}
				boolean shoucang_Tag = false;
				int isshoucang = FunctionBusiness.getshoucang(id, my_id);
				if (isshoucang == 1) {
					shoucang_Tag = false;
				} else if (isshoucang == 2) {
					shoucang_Tag = true;
				}
				boolean have_dianzan_hongbao = (dianzan_hongbao.getCount() != 0);
				boolean have_fenxiang_hongbao = (fenxiang_hongbao.getCount() != 0);

				guangjie.setUid(id);
				guangjie.setPicture(list2);
				guangjie.setTitle(title);
				guangjie.setDashang_count(dashang_count);
				guangjie.setDianzan_count(dianzan_counts);
				guangjie.setReleaser_id(releaser_id);
				guangjie.setReleaser_name(releaser_name);
				guangjie.setReleaser_touxiang(releaser_touxiang);
				guangjie.setYuedu(yuedu);
				guangjie.setIsguanzhu(isguanzhu);
				guangjie.setPrice(price);
				guangjie.setUnit(unit);
				guangjie.setDianpu_id(dianpu_id);
				guangjie.setDianpu_name(dianpu_name);
				guangjie.setDianpu_address(dianpu_address);
				guangjie.setDianpu_touxiang(dianpu_touxiang);
				guangjie.setRenzheng_Tag(renzheng_Tag);
				guangjie.setDianpu_pinglun_count(dianpu_pinglun_count);
				guangjie.setContent(content);
				guangjie.setTuijian(tuijian);
				guangjie.setShoucang_Tag(shoucang_Tag);
				guangjie.setDianzan_hongbao(dianzan_hongbao);
				guangjie.setFenxiang_hongbao(fenxiang_hongbao);
				guangjie.setGuanggao_url(guanggao_url);
				guangjie.setBiaoqian(biaoqian_list);
				guangjie.setPhone(phone);
				guangjie.setTime(time);
				guangjie.setPinglun_count(pinglun_count);
				guangjie.setGuangjie_fenlei_Tag(guangjie_fenlei_Tag);
				guangjie.setIsdianzan(isdianzan);
				guangjie.setHave_dianzan_hongbao(have_dianzan_hongbao);
				guangjie.setHave_fenxiang_hongbao(have_fenxiang_hongbao);

				FunctionBusiness.addZujiByFuwu(my_id, id, picture, title);
			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return guangjie;
	}

	public static List<Fuwu> getAllFuwu() {
		List<Fuwu> list = new ArrayList<Fuwu>();
		String sql = null;
		DBHelper db1 = null;
		sql = "select *from Fuwu";
		db1 = new DBHelper(sql);
		ResultSet ret = null;
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				String id = ret.getString(1);
				String title = ret.getString(2);
				String price = ret.getString(3);
				String unit = ret.getString(4);
				String phone = ret.getString(5);
				String picture = ret.getString(6);
				String[] aa = picture.split(",");

				List<Picture> list2 = new ArrayList<Picture>();
				for (int i = 0; i < aa.length; i++) {
					Picture picture_url = new Picture();
					picture_url.setPicture_url(aa[i]);
					list2.add(picture_url);
				}
				String content = ret.getString(7);
				String[] contents = content.split("<--分隔符-->");
				List<ContentModel> list3 = new ArrayList<ContentModel>();
				ContentModel Model = new ContentModel();
				for (int i = 0; i < contents.length; i++) {

					if (i % 2 == 0) {
						Model.setText(contents[i]);
					} else {
						Model.setPicture_url(contents[i]);
						list3.add(Model);
						Model = new ContentModel();
					}

				}
				Fuwu fuwu = new Fuwu();
				fuwu.setFuwu_id(id);
				fuwu.setTitle(title);
				fuwu.setPrice(price);
				fuwu.setUnit(unit);
				fuwu.setPhone(phone);
				fuwu.setPicture(list2);
				fuwu.setContent(list3);

				list.add(fuwu);
			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<Fuwu> getAllFuwu(int page) {
		List<Fuwu> list = new ArrayList<Fuwu>();
		String sql = null;
		DBHelper db1 = null;
		sql = "select *from Fuwu LIMIT " + (page - 1) * 12 + "," + 12 * page;
		db1 = new DBHelper(sql);
		ResultSet ret = null;
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				String id = ret.getString(1);
				String title = ret.getString(2);
				String price = ret.getString(3);
				String unit = ret.getString(4);
				String phone = ret.getString(5);
				String picture = ret.getString(6);
				String[] aa = picture.split(",");

				List<Picture> list2 = new ArrayList<Picture>();
				for (int i = 0; i < aa.length; i++) {
					Picture picture_url = new Picture();
					picture_url.setPicture_url(aa[i]);
					list2.add(picture_url);
				}
				String content = ret.getString(7);
				String[] contents = content.split("<--分隔符-->");
				List<ContentModel> list3 = new ArrayList<ContentModel>();
				ContentModel Model = new ContentModel();
				for (int i = 0; i < contents.length; i++) {

					if (i % 2 == 0) {
						Model.setText(contents[i]);
					} else {
						Model.setPicture_url(contents[i]);
						list3.add(Model);
						Model = new ContentModel();
					}

				}
				Fuwu fuwu = new Fuwu();
				fuwu.setFuwu_id(id);
				fuwu.setTitle(title);
				fuwu.setPrice(price);
				fuwu.setUnit(unit);
				fuwu.setPhone(phone);
				fuwu.setPicture(list2);
				fuwu.setContent(list3);

				list.add(fuwu);
			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void addFuwu(String my_id, String title, String price, String unit, String phone, String picture,
			String content) {
		String sql = null;
		DBHelper db1 = null;
		String uuid = UUID.randomUUID().toString();

		String shenhe = "正在审核中...";
		Timestamp time = new Timestamp(new Date().getTime());
		sql = "INSERT INTO fuwu(id,title,price,unit,phone,picture,content,shenhe,releaser_id,time) VALUES ('" + uuid
				+ "', '" + title + "'," + price + ",'" + unit + "','" + phone + "','" + picture + "','" + content
				+ "','" + shenhe + "','" + my_id + "','" + time + "')";
		db1 = new DBHelper(sql);
		try {
			boolean ret = db1.pst.execute();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void DeleteFuwu(String id) {
		{
			String sql = null;
			DBHelper db1 = null;
			sql = "DELETE FROM fuwu WHERE id =  '" + id + "'";
			db1 = new DBHelper(sql);
			try {
				boolean ret = db1.pst.execute();
				db1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public static void UpdateFuwu(String key, String value, String id) {
		{
			String sql = null;
			DBHelper db1 = null;
			sql = "UPDATE fuwu SET " + key + " = '" + value + "' WHERE id = '" + id + "'";
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
