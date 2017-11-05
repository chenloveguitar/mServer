package com.magicmoble.luzhouapp.business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.xml.crypto.Data;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.magicmoble.luzhouapp.model.Biaoqian;
import com.magicmoble.luzhouapp.model.Commodity;
import com.magicmoble.luzhouapp.model.ContentModel;
import com.magicmoble.luzhouapp.model.Dianzan_Number;
import com.magicmoble.luzhouapp.model.Dianzan_message;
import com.magicmoble.luzhouapp.model.Dianzan_touxiang;
import com.magicmoble.luzhouapp.model.Faxian;
import com.magicmoble.luzhouapp.model.Faxian_Shouye;
import com.magicmoble.luzhouapp.model.Faxian_Xiangqing;
import com.magicmoble.luzhouapp.model.Guangjie_Shouye;
import com.magicmoble.luzhouapp.model.Guanzhu;
import com.magicmoble.luzhouapp.model.Hongbao;
import com.magicmoble.luzhouapp.model.Picture;
import com.magicmoble.luzhouapp.model.Pinglun;
import com.magicmoble.luzhouapp.model.Quchu;
import com.magicmoble.luzhouapp.model.Quchu_Shouye;
import com.magicmoble.luzhouapp.model.Tuijian;

public class FaxianBusiness {

	public static List<Tuijian> getTuijianByBiaoqian(List<Biaoqian> biaoqian) {
		String sql = null;
		DBHelper db1 = null;
		sql = "select id,title,name,picture,time from faxian WHERE biaoqian like '%" + biaoqian.get(0).getBiaoqian()
				+ "%'";
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

	public static List<Faxian_Shouye> getAllFaxian(int page, String my_id, int num) {
		List<Faxian_Shouye> list = new ArrayList<Faxian_Shouye>();
		List<Faxian_Shouye> list3 = new ArrayList<Faxian_Shouye>();

		DBHelper db1 = null;
		DBHelper db2 = null;
		String sql1 = null;
		String sql = null;
		ResultSet ret = null;
		List<String> guanzhus = new ArrayList<String>();
		if (my_id != null) {
			guanzhus = GuanzhuBusiness.getFriend_id(my_id, 0);
		}

		if (my_id != null) {
			sql = "select *from faxian where ";
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

		sql1 = "select * from faxian where  shenhe like '%已发布%' order by time LIMIT " + (page - 1) * 12 + ","
				+ 12 * page;

		if (my_id != null) {
			db1 = new DBHelper(sql);
		}

		ResultSet ret2 = null;
		try {
			if (my_id != null) {
				if (page == 1) {
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
				}
			}
			db2 = new DBHelper(sql1);
			ret2 = db2.pst.executeQuery();
			while (ret2.next()) {
				String id = ret2.getString(1);

				String picture = ret2.getString(2);
				String[] aa = picture.split(",");

				List<Picture> list2 = new ArrayList<Picture>();
				for (int i = 0; i < aa.length; i++) {
					Picture picture_url = new Picture();
					picture_url.setPicture_url(aa[i]);
					list2.add(picture_url);
				}

				String title = ret2.getString(3);
				String name = ret2.getString(4);
				int pinglun_count = FunctionBusiness.getPinglun_size(id);

				int dianzan_counts = FunctionBusiness.getDianzanNumber(id, 4).getDianzan_count();
				Faxian_Shouye faxian = new Faxian_Shouye();
				faxian.setFaxian_id(id);
				faxian.setPicture(list2);
				faxian.setTitle(title);
				faxian.setReleaser_name(name);
				faxian.setPinglun_count(pinglun_count);
				faxian.setDianzan_count(dianzan_counts);

				list3.add(faxian);
			}

			list3.removeAll(list);
			Collections.shuffle(list3);
			Collections.shuffle(list);
			for (int i = 0; i < list3.size(); i++) {
				list.add(list3.get(i));
			}

			if (my_id != null) {
				db1.close();
			}

			db2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<Object> getAllTuijian(String my_id, int page) {
		List<Object> list = new ArrayList<Object>();
		List<Object> list3 = new ArrayList<Object>();

		DBHelper db1 = null;
		DBHelper db2 = null;
		String sql1 = null;
		int num = 1;
		if (page % 4 == 0) {
			num++;
		}
		String sql = "select *from faxian_tuijian where fufei_Tag=1  order by rand() LIMIT  6 ";

		Timestamp a = new Timestamp(new Date().getTime() - 259200000 * (num - 1));
		Timestamp b = new Timestamp(new Date().getTime() - 259200000 * num);

		sql1 = "select * from faxian_tuijian where fufei_Tag=2 LIMIT " + (page - 1) * 12 + "," + 12 * page;

		db2 = new DBHelper(sql1);
		ResultSet ret = null;
		ResultSet ret2 = null;
		try {
			if (page == 1) {
				db1 = new DBHelper(sql);
				ret = db1.pst.executeQuery();
				while (ret.next()) {
					String id = ret.getString(1);

					String tiaomu_id = ret.getString(2);
					int tuijian_Tag = ret.getInt(3);
					int guangjie_fenlei_Tag = ret.getInt(5);
					if (tuijian_Tag == 1) {
						List<Picture> list2 = FaxianBusiness.getFaxianById(my_id, tiaomu_id).getPictures();
						if (list2 == null) {
							list2 = new ArrayList<Picture>();
						}

						String title = FaxianBusiness.getFaxianById(my_id, tiaomu_id).getTitle();
						String name = FaxianBusiness.getFaxianById(my_id, tiaomu_id).getReleaser_name();
						int pinglun_count = FunctionBusiness.getPinglun_size(id);
						int dianzan_counts = FunctionBusiness.getDianzanNumber(id, 4).getDianzan_count();
						Faxian_Shouye faxian = new Faxian_Shouye();
						faxian.setFaxian_id(tiaomu_id);
						faxian.setPicture(list2);
						faxian.setTitle(title);
						faxian.setReleaser_name(name);
						faxian.setPinglun_count(pinglun_count);
						faxian.setDianzan_count(dianzan_counts);
						faxian.setTuijian_Tag(tuijian_Tag);

						list.add(faxian);

					} else if (tuijian_Tag == 2) {
						String title = QuchuBusiness.getQuchuById(tiaomu_id, my_id).getTitle();
						List<Picture> list2 = QuchuBusiness.getQuchuById(tiaomu_id, my_id).getPicture();
						if (list2 == null) {
							list2 = new ArrayList<Picture>();
						}
						int dianzan_counts = FunctionBusiness.getDianzanNumber(id, 4).getDianzan_count();
						int pinglun_count = FunctionBusiness.getPinglun_size(id);
						String releaser_name = QuchuBusiness.getQuchuById(tiaomu_id, my_id).getDianpu_name();

						Quchu_Shouye quchu = new Quchu_Shouye();
						quchu.setQuchu_id(tiaomu_id);
						quchu.setTitle(title);
						quchu.setPicture(list2);
						quchu.setDianzan_count(dianzan_counts);
						quchu.setReleaser_name(releaser_name);
						quchu.setPinglun_count(pinglun_count);
						quchu.setTuijian_Tag(tuijian_Tag);

						list.add(quchu);

					} else if (tuijian_Tag == 3) {
						if (guangjie_fenlei_Tag == 1) {
							String title = CommodityBusiness.getGuangjie_CommodityById(id, my_id, guangjie_fenlei_Tag)
									.getTitle();
							List<Picture> pictures = CommodityBusiness
									.getGuangjie_CommodityById(id, my_id, guangjie_fenlei_Tag).getPicture();
							if (pictures == null) {
								pictures = new ArrayList<Picture>();
							}
							int dianzan_counts = FunctionBusiness.getDianzanNumber(id, 4).getDianzan_count();
							int pinglun_count = FunctionBusiness.getPinglun_size(id);
							String releaser_name = CommodityBusiness
									.getGuangjie_CommodityById(id, my_id, guangjie_fenlei_Tag).getReleaser_name();

							Guangjie_Shouye guangjie = new Guangjie_Shouye();
							guangjie.setGuangjie_id(tiaomu_id);
							guangjie.setTitle(title);
							guangjie.setPicture(pictures);
							guangjie.setReleaser_name(releaser_name);
							guangjie.setDianzan_count(dianzan_counts);
							guangjie.setPinglun_count(pinglun_count);
							guangjie.setGuangjie_fenlei_Tag(guangjie_fenlei_Tag);
							guangjie.setTuijian_Tag(tuijian_Tag);

							list.add(guangjie);

						} else if (guangjie_fenlei_Tag == 2) {
							String title = FuwuBusiness.getGuangjie_FuwuById(id, my_id, guangjie_fenlei_Tag).getTitle();
							List<Picture> pictures = FuwuBusiness.getGuangjie_FuwuById(id, my_id, guangjie_fenlei_Tag)
									.getPicture();
							if (pictures == null) {
								pictures = new ArrayList<Picture>();
							}
							int dianzan_counts = FunctionBusiness.getDianzanNumber(id, 4).getDianzan_count();
							int pinglun_count = FunctionBusiness.getPinglun_size(id);
							String releaser_name = FuwuBusiness.getGuangjie_FuwuById(id, my_id, guangjie_fenlei_Tag)
									.getReleaser_name();

							Guangjie_Shouye guangjie = new Guangjie_Shouye();
							guangjie.setGuangjie_id(tiaomu_id);
							guangjie.setTitle(title);
							guangjie.setPicture(pictures);
							guangjie.setReleaser_name(releaser_name);
							guangjie.setDianzan_count(dianzan_counts);
							guangjie.setPinglun_count(pinglun_count);
							guangjie.setGuangjie_fenlei_Tag(guangjie_fenlei_Tag);
							guangjie.setTuijian_Tag(tuijian_Tag);

							list.add(guangjie);

						}

					}

				}
			}
			ret2 = db2.pst.executeQuery();
			while (ret2.next()) {
				String id = ret2.getString(1);
				String tiaomu_id = ret2.getString(2);
				int tuijian_Tag = ret2.getInt(3);
				int guangjie_fenlei_Tag = ret2.getInt(5);
				if (tuijian_Tag == 1) {
					List<Picture> list2 = FaxianBusiness.getFaxianById(my_id, tiaomu_id).getPictures();
					if (list2 == null) {
						list2 = new ArrayList<Picture>();
					}

					String title = FaxianBusiness.getFaxianById(my_id, tiaomu_id).getTitle();
					String name = FaxianBusiness.getFaxianById(my_id, tiaomu_id).getReleaser_name();
					int pinglun_count = FunctionBusiness.getPinglun_size(id);
					int dianzan_counts = FunctionBusiness.getDianzanNumber(id, 4).getDianzan_count();
					Faxian_Shouye faxian = new Faxian_Shouye();
					faxian.setFaxian_id(tiaomu_id);
					faxian.setPicture(list2);
					faxian.setTitle(title);
					faxian.setReleaser_name(name);
					faxian.setPinglun_count(pinglun_count);
					faxian.setDianzan_count(dianzan_counts);
					faxian.setTuijian_Tag(tuijian_Tag);

					list3.add(faxian);

				} else if (tuijian_Tag == 2) {
					String title = QuchuBusiness.getQuchuById(tiaomu_id, my_id).getTitle();
					List<Picture> list2 = QuchuBusiness.getQuchuById(tiaomu_id, my_id).getPicture();
					if (list2 == null) {
						list2 = new ArrayList<Picture>();
					}
					int dianzan_counts = FunctionBusiness.getDianzanNumber(id, 4).getDianzan_count();
					int pinglun_count = FunctionBusiness.getPinglun_size(id);
					String releaser_name = QuchuBusiness.getQuchuById(tiaomu_id, my_id).getDianpu_name();

					Quchu_Shouye quchu = new Quchu_Shouye();
					quchu.setQuchu_id(tiaomu_id);
					quchu.setTitle(title);
					quchu.setPicture(list2);
					quchu.setDianzan_count(dianzan_counts);
					quchu.setReleaser_name(releaser_name);
					quchu.setPinglun_count(pinglun_count);
					quchu.setTuijian_Tag(tuijian_Tag);

					list3.add(quchu);

				} else if (tuijian_Tag == 3) {
					if (guangjie_fenlei_Tag == 1) {
						String title = CommodityBusiness.getGuangjie_CommodityById(id, my_id, guangjie_fenlei_Tag)
								.getTitle();
						List<Picture> pictures = CommodityBusiness
								.getGuangjie_CommodityById(id, my_id, guangjie_fenlei_Tag).getPicture();
						if (pictures == null) {
							pictures = new ArrayList<Picture>();
						}
						int dianzan_counts = FunctionBusiness.getDianzanNumber(id, 4).getDianzan_count();
						int pinglun_count = FunctionBusiness.getPinglun_size(id);
						String releaser_name = CommodityBusiness
								.getGuangjie_CommodityById(id, my_id, guangjie_fenlei_Tag).getReleaser_name();

						Guangjie_Shouye guangjie = new Guangjie_Shouye();
						guangjie.setGuangjie_id(tiaomu_id);
						guangjie.setTitle(title);
						guangjie.setPicture(pictures);
						guangjie.setReleaser_name(releaser_name);
						guangjie.setDianzan_count(dianzan_counts);
						guangjie.setPinglun_count(pinglun_count);
						guangjie.setGuangjie_fenlei_Tag(guangjie_fenlei_Tag);
						guangjie.setTuijian_Tag(tuijian_Tag);

						list3.add(guangjie);

					} else if (guangjie_fenlei_Tag == 2) {
						String title = FuwuBusiness.getGuangjie_FuwuById(id, my_id, guangjie_fenlei_Tag).getTitle();
						List<Picture> pictures = FuwuBusiness.getGuangjie_FuwuById(id, my_id, guangjie_fenlei_Tag)
								.getPicture();
						if (pictures == null) {
							pictures = new ArrayList<Picture>();
						}
						int dianzan_counts = FunctionBusiness.getDianzanNumber(id, 4).getDianzan_count();
						int pinglun_count = FunctionBusiness.getPinglun_size(id);
						String releaser_name = FuwuBusiness.getGuangjie_FuwuById(id, my_id, guangjie_fenlei_Tag)
								.getReleaser_name();

						Guangjie_Shouye guangjie = new Guangjie_Shouye();
						guangjie.setGuangjie_id(tiaomu_id);
						guangjie.setTitle(title);
						guangjie.setPicture(pictures);
						guangjie.setReleaser_name(releaser_name);
						guangjie.setDianzan_count(dianzan_counts);
						guangjie.setPinglun_count(pinglun_count);
						guangjie.setGuangjie_fenlei_Tag(guangjie_fenlei_Tag);
						guangjie.setTuijian_Tag(tuijian_Tag);

						list3.add(guangjie);

					}
				}

			}
			list3.removeAll(list);
			for (int i = 0; i < list3.size(); i++) {
				list.add(list3.get(i));
			}
			if (page == 1) {
				db1.close();
			}

			db2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void addFaxian(String my_id, String picture, String title, String name, String content) {
		String sql = null;
		DBHelper db1 = null;
		String uuid = UUID.randomUUID().toString();
		int fenlei_Tag = 1;
		Timestamp time = new Timestamp(new Date().getTime());
		String shenhe = "正在审核中...";
		sql = "INSERT INTO faxian(id,picture,title,name,content,fenlei_Tag,shenhe,releaser_id,time) VALUES ('" + uuid
				+ "', '" + picture + "', '" + title + "', '" + name + "','" + content + "'," + fenlei_Tag + ",'"
				+ shenhe + "','" + my_id + "','" + time + "')";
		db1 = new DBHelper(sql);
		try {
			boolean ret = db1.pst.execute();

			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void DeleteFaxian(String id) {

		String sql = null;
		DBHelper db1 = null;
		sql = "DELETE FROM faxian WHERE id =  '" + id + "'";
		db1 = new DBHelper(sql);
		try {
			boolean ret = db1.pst.execute();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void UpdateFaxian(String key, String value, String id) {
		{
			String sql = null;
			DBHelper db1 = null;
			sql = "UPDATE Faxian SET " + key + " = '" + value + "' WHERE id = '" + id + "'";
			db1 = new DBHelper(sql);
			try {
				boolean ret = db1.pst.execute();
				db1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public static void faxian_yuedu(String _id) {
		DBHelper db2 = null;
		String sql1 = null;
		sql1 = "UPDATE faxian SET yuedu_count=yuedu_count+1 WHERE id='" + _id + "'";
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

	public static Faxian_Xiangqing getFaxianById(String my_id, String _id) {
		Faxian_Xiangqing faxian = new Faxian_Xiangqing();
		String sql = null;

		int yuedu_count;
		DBHelper db1 = null;

		List<Tuijian> tuijian = new ArrayList<Tuijian>();
		List<Biaoqian> list2 = new ArrayList<Biaoqian>();

		sql = "select id,title,content,dianzan_count,pinglun_count,dashang_count,releaser_id,shoucang_Tag,yuedu_count,time,biaoqian,picture from faxian WHERE  id='"
				+ _id + "'";
		db1 = new DBHelper(sql);

		ResultSet ret = null;

		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				String id = ret.getString(1);
				String title = ret.getString(2);

				String content = ret.getString(3);
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
						str += "<img src=\"" + contents[i] + "\">";
						// list3.add(Model);
						// Model = new ContentModel();
					}

				}
				str += "</div>";
				int dianzan_counts = FunctionBusiness.getDianzanNumber(id, 4).getDianzan_count();
				int dashang_count = FunctionBusiness.getDashangNumber(id);
				;
				String releaser_id = ret.getString(7);
				String releaser_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id).getName();
				String releaser_touxiang = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id)
						.getTouxiang_picture();
				boolean isguanzhu = GuanzhuBusiness.getIsGuanzhu(my_id, releaser_id);

				boolean shoucang_Tag = false;
				int isshoucang = FunctionBusiness.getshoucang(id, my_id);
				if (isshoucang == 1) {
					shoucang_Tag = false;
				} else if (isshoucang == 2) {
					shoucang_Tag = true;
				}

				Hongbao dianzan_hongbao = FunctionBusiness.getHongbaoById(id, 1);
				Hongbao fenxiang_hongbao = FunctionBusiness.getHongbaoById(id, 2);
				yuedu_count = ret.getInt(9);
				Timestamp time = ret.getTimestamp(10);
				int pinglun_count = FunctionBusiness.getPinglun_size(id);
				String biaoqian = ret.getString(11);
				if (biaoqian != null) {
					String[] biaoqians = biaoqian.split(",");
					list2 = new ArrayList<Biaoqian>();
					for (int i = 0; i < biaoqians.length; i++) {
						Biaoqian biaoqian2 = new Biaoqian();
						biaoqian2.setBiaoqian(biaoqians[i]);
						list2.add(biaoqian2);
					}
					tuijian = FaxianBusiness.getTuijianByBiaoqian(list2);
				} else {
					tuijian = new ArrayList<Tuijian>();
				}

				String picture = ret.getString(12);
				String[] aa = picture.split(",");

				List<Picture> list3 = new ArrayList<Picture>();
				for (int i = 0; i < aa.length; i++) {
					Picture picture_url = new Picture();
					picture_url.setPicture_url(aa[i]);
					list3.add(picture_url);
				}
				boolean isdianzan = false;
				if (my_id == null) {
					isdianzan = false;
				} else {
					isdianzan = FunctionBusiness.getDianzan(my_id, id, 4);
				}
				boolean have_dianzan_hongbao = (dianzan_hongbao.getCount() != 0);
				boolean have_fenxiang_hongbao = (fenxiang_hongbao.getCount() != 0);

				faxian.setUid(id);
				faxian.setTitle(title);
				faxian.setContent(str);
				faxian.setDianzan_count(dianzan_counts);
				faxian.setPinglun_count(pinglun_count);
				faxian.setReleaser_id(releaser_id);
				faxian.setReleaser_name(releaser_name);
				faxian.setReleaser_touxiang(releaser_touxiang);
				faxian.setIsguanzhu(isguanzhu);
				faxian.setDashang_count(dashang_count);
				faxian.setShoucang_Tag(shoucang_Tag);
				faxian.setDianzan_hongbao(dianzan_hongbao);
				faxian.setFenxiang_hongbao(fenxiang_hongbao);
				faxian.setYuedu(yuedu_count);
				faxian.setTime(time);
				faxian.setBiaoqian(list2);
				faxian.setTuijian(tuijian);
				faxian.setPictures(list3);
				faxian.setIsdianzan(isdianzan);
				faxian.setHave_dianzan_hongbao(have_dianzan_hongbao);
				faxian.setHave_fenxiang_hongbao(have_fenxiang_hongbao);
				FunctionBusiness.addZujiByFaxian(my_id, id, picture, title);
			}

			ret.close();
			db1.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return faxian;
	}
}
