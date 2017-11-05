package com.magicmoble.luzhouapp.business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.swing.plaf.basic.BasicTreeUI.TreeCancelEditingAction;

import org.apache.taglibs.standard.tag.common.xml.IfTag;

import com.magicmoble.luzhouapp.action.fabu.GuangjieInq;
import com.magicmoble.luzhouapp.model.Biaoqian;
import com.magicmoble.luzhouapp.model.Commodity;
import com.magicmoble.luzhouapp.model.ContentModel;
import com.magicmoble.luzhouapp.model.Dianzan_Number;
import com.magicmoble.luzhouapp.model.Dianzan_message;
import com.magicmoble.luzhouapp.model.Dianzan_touxiang;
import com.magicmoble.luzhouapp.model.Faxian_tuijian;
import com.magicmoble.luzhouapp.model.Guangjie_Shouye;
import com.magicmoble.luzhouapp.model.Guangjie_Xiangqing;
import com.magicmoble.luzhouapp.model.Guanzhu;
import com.magicmoble.luzhouapp.model.Hongbao;
import com.magicmoble.luzhouapp.model.Picture;
import com.magicmoble.luzhouapp.model.Pinglun;
import com.magicmoble.luzhouapp.model.Quchu_Shouye;
import com.magicmoble.luzhouapp.model.Quchu_Xiangqing;
import com.magicmoble.luzhouapp.model.Tuijian;

public class CommodityBusiness {
	public static List<Tuijian> getTuijianByBiaoqian(List<Biaoqian> biaoqian, int guangjie_fenlei_Tag) {
		String sql = null;
		DBHelper db1 = null;

		if (guangjie_fenlei_Tag == 1) {

			sql = "select id,title,releaser_id,picture,time from commodity WHERE biaoqian like '%"
					+ biaoqian.get(0).getBiaoqian() + "%'";
			for (int i = 1; i < biaoqian.size(); i++) {
				sql += "or  biaoqian like '%" + biaoqian.get(i).getBiaoqian() + "%'";

			}

			sql += "ORDER BY RAND() LIMIT 3";
		} else if (guangjie_fenlei_Tag == 2) {
			sql = "select id,title,releaser_id,picture,time from fuwu WHERE biaoqian like '%"
					+ biaoqian.get(0).getBiaoqian() + "%'";
			for (int i = 1; i < biaoqian.size(); i++) {
				sql += "or  biaoqian like '%" + biaoqian.get(i).getBiaoqian() + "%'";

			}

			sql += "ORDER BY RAND() LIMIT 3";
		}

		db1 = new DBHelper(sql);

		ResultSet ret = null;

		List<Tuijian> list = new ArrayList<Tuijian>();
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				String id = ret.getString(1);
				String title = ret.getString(2);

				String releaser_id = ret.getString(3);
				String name = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id).getName();
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

	public static List<Guangjie_Shouye> getAllGuangjie(String my_id, int page, int num) {
		List<Guangjie_Shouye> guangjie_list = new ArrayList<Guangjie_Shouye>();
		List<Guangjie_Shouye> guangjie_list2 = new ArrayList<Guangjie_Shouye>();

		DBHelper db1 = null;
		DBHelper db2 = null;
		String sql1 = null;
		String sql = null;
		List<String> guanzhus = new ArrayList<String>();
		if (my_id != null) {
			guanzhus = GuanzhuBusiness.getFriend_id(my_id, 0);

			sql = "select id,title,picture,releaser_id,guangjie_fenlei_Tag from (SELECT id ,title,picture,releaser_id,shenhe,time,guangjie_fenlei_Tag FROM commodity where buyer_id = 'null' AND shenhe LIKE '已发布%' and shuliang>0   UNION SELECT id ,title,picture,releaser_id,shenhe,time,guangjie_fenlei_Tag FROM fuwu where shenhe LIKE '已发布%'   ) a where ";
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

			sql += " shenhe like '%已发布%' LIMIT  6 ";
		}

		sql1 = "select id,title,picture,releaser_id,guangjie_fenlei_Tag from (SELECT id ,title,picture,releaser_id,shenhe,time,guangjie_fenlei_Tag FROM commodity where buyer_id = 'null' AND shenhe LIKE '已发布%'   UNION SELECT id ,title,picture,releaser_id,shenhe,time,guangjie_fenlei_Tag FROM fuwu  where   shenhe LIKE '已发布%' ) a order by time desc LIMIT "
				+ (page - 1) * 12 + "," + 12 * page;

		if (my_id != null) {
			db1 = new DBHelper(sql);
		}

		ResultSet ret = null;
		ResultSet ret2 = null;
		int dianzan_counts = 0;
		try {
			if (my_id != null) {
				if (page == 1) {
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
						guangjie_list.add(guangjie);
					}
				}
			}
			db2 = new DBHelper(sql1);
			ret2 = db2.pst.executeQuery();
			while (ret2.next()) {
				String id = ret2.getString(1);

				String title = ret2.getString(2);
				String picture = ret2.getString(3);
				String[] pictures = picture.split(",");
				List<Picture> list2 = new ArrayList<Picture>();
				for (int i = 0; i < pictures.length; i++) {
					Picture picture_url = new Picture();
					picture_url.setPicture_url(pictures[i]);
					list2.add(picture_url);
				}
				String releaser_id = ret2.getString(4);
				int guangjie_fenlei_Tag = ret2.getInt(5);
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

				guangjie_list2.add(guangjie);
			}

			guangjie_list2.removeAll(guangjie_list);
			Collections.shuffle(guangjie_list2);
			Collections.shuffle(guangjie_list);
			for (int i = 0; i < guangjie_list2.size(); i++) {
				guangjie_list.add(guangjie_list2.get(i));
			}

			if (my_id != null) {
				if (page == 1) {
					ret.close();
					db1.close();
				}
			}
			db2.close();
			ret2.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return guangjie_list;
	}

	public static List<Commodity> getTusssijian(int page) {
		List<Commodity> list = new ArrayList<Commodity>();
		String sql = null;
		DBHelper db1 = null;
		sql = "select * from faxian_tuijian WHERE fufei_tuijian=1 ORDER BY RAND() LIMIT 6";
		db1 = new DBHelper(sql);
		ResultSet ret = null;
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				String id = ret.getString(1);
				String title = ret.getString(2);
				String price = ret.getString(3);
				String unit = ret.getString(4);
				String shuliang = ret.getString(5);
				String freight = ret.getString(6);
				String phone = ret.getString(7);
				String picture = ret.getString(8);
				String[] aa = picture.split(",");
				List<Picture> list2 = new ArrayList<Picture>();
				for (int i = 0; i < aa.length; i++) {
					Picture picture_url = new Picture();
					picture_url.setPicture_url(aa[i]);
					list2.add(picture_url);
				}

				String content = ret.getString(9);
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
				String seller_id = ret.getString(10);
				String buyer_id = ret.getString(11);
				String releaser_id = ret.getString(12);
				String seller_name = ret.getString(13);
				String buyer_name = ret.getString(14);
				String releaser_name = ret.getString(15);
				String shenhe = ret.getString(16);

				Commodity commodity = new Commodity();
				commodity.setCommodity_id(id);
				commodity.setTitle(title);
				commodity.setPrice(price);
				commodity.setUnit(unit);
				commodity.setShuliang(shuliang);
				commodity.setFreight(freight);
				commodity.setPhone(phone);
				commodity.setPicture(list2);
				commodity.setContent(list3);
				commodity.setSeller_id(seller_id);
				commodity.setBuyer_id(buyer_id);
				commodity.setReleaser_id(releaser_id);
				commodity.setSeller_name(seller_name);
				commodity.setBuyer_name(buyer_name);
				commodity.setReleaser_name(releaser_name);
				commodity.setShenhe(shenhe);

				list.add(commodity);
			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void commodity_yuedu(String guangjie_id) {
		String sql1 = null;
		sql1 = "UPDATE commodity SET yuedu=(yuedu+1) WHERE id='" + guangjie_id + "'";
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

	public static Guangjie_Xiangqing getGuangjie_CommodityById(String guangjie_id, String my_id,
			int guangjie_fenlei_Tag) {
		String sql = null;
		DBHelper db1 = null;
		System.out.println("byid:" + guangjie_id);
		List<Biaoqian> biaoqian_list = new ArrayList<Biaoqian>();
		List<Tuijian> tuijian = new ArrayList<Tuijian>();
		sql = "select id,picture,title,releaser_id,yuedu,price,unit,dianpu_id,content,biaoqian,shoucang_Tag,guanggao_url,phone,time,freight,seller_id,shuliang from commodity WHERE id='"
				+ guangjie_id + "' and  guangjie_fenlei_Tag=" + guangjie_fenlei_Tag;
		db1 = new DBHelper(sql);
		Guangjie_Xiangqing guangjie = new Guangjie_Xiangqing();
		ResultSet ret = null;
		boolean isguanzhu = false;

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
				int dianzan_counts = FunctionBusiness.getDianzanNumber(id, 6).getDianzan_count();

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
				String biaoqian = ret.getString(10);
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
				boolean isdianzan = false;
				if (my_id == null) {
					isdianzan = false;
				} else {
					isdianzan = FunctionBusiness.getDianzan(my_id, id, 6);
				}
				boolean shoucang_Tag = false;
				int isshoucang = FunctionBusiness.getshoucang(id, my_id);
				if (isshoucang == 1) {
					shoucang_Tag = false;
				} else if (isshoucang == 2) {
					shoucang_Tag = true;
				}

				Hongbao dianzan_hongbao = FunctionBusiness.getHongbaoById(guangjie_id, 1);
				Hongbao fenxiang_hongbao = FunctionBusiness.getHongbaoById(guangjie_id, 2);
				String guanggao_url = ret.getString(12);
				String phone = ret.getString(13);
				Timestamp time = ret.getTimestamp(14);
				int freight = ret.getInt(15);
				String seller_id = ret.getString(16);
				int pinglun_count = FunctionBusiness.getPinglun_size(id);

				int shuliang = ret.getInt(17);
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
				guangjie.setContent(str);
				guangjie.setTuijian(tuijian);
				guangjie.setShoucang_Tag(shoucang_Tag);
				guangjie.setDianzan_hongbao(dianzan_hongbao);
				guangjie.setFenxiang_hongbao(fenxiang_hongbao);
				guangjie.setGuanggao_url(guanggao_url);
				guangjie.setBiaoqian(biaoqian_list);
				guangjie.setPhone(phone);
				guangjie.setTime(time);

				guangjie.setFreight(freight);
				guangjie.setSeller_id(seller_id);
				guangjie.setPinglun_count(pinglun_count);
				guangjie.setGuangjie_fenlei_Tag(guangjie_fenlei_Tag);
				guangjie.setIsdianzan(isdianzan);
				guangjie.setShuliang(shuliang);
				guangjie.setHave_dianzan_hongbao(have_dianzan_hongbao);
				guangjie.setHave_fenxiang_hongbao(have_fenxiang_hongbao);

				FunctionBusiness.addZujiByCommodity(my_id, id, picture, title);
			}
			ret.close();
			db1.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return guangjie;
	}

	public static List<Commodity> getNoBuyCommodity(int page) {
		List<Commodity> list = new ArrayList<Commodity>();
		String sql = null;
		DBHelper db1 = null;
		sql = "select * from commodity  where buyer = 'null' AND shenhe LIKE '已发布%'  LIMIT " + (page - 1) * 12 + ","
				+ 12 * page;
		db1 = new DBHelper(sql);
		ResultSet ret = null;
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				String id = ret.getString(1);
				String title = ret.getString(2);
				String price = ret.getString(3);
				String unit = ret.getString(4);
				String shuliang = ret.getString(5);
				String freight = ret.getString(6);
				String phone = ret.getString(7);
				String picture = ret.getString(8);
				String[] aa = picture.split(",");
				List<Picture> list2 = new ArrayList<Picture>();
				for (int i = 0; i < aa.length; i++) {
					Picture picture_url = new Picture();
					picture_url.setPicture_url(aa[i]);
					list2.add(picture_url);
				}

				String content = ret.getString(9);
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
				String seller_id = ret.getString(10);
				String buyer_id = ret.getString(11);
				String releaser_id = ret.getString(12);
				String seller_name = ret.getString(13);
				String buyer_name = ret.getString(14);
				String releaser_name = ret.getString(15);
				String shenhe = ret.getString(16);

				Commodity commodity = new Commodity();
				commodity.setCommodity_id(id);
				commodity.setTitle(title);
				commodity.setPrice(price);
				commodity.setUnit(unit);
				commodity.setShuliang(shuliang);
				commodity.setFreight(freight);
				commodity.setPhone(phone);
				commodity.setPicture(list2);
				commodity.setContent(list3);
				commodity.setSeller_id(seller_id);
				commodity.setBuyer_id(buyer_id);
				commodity.setReleaser_id(releaser_id);
				commodity.setSeller_name(seller_name);
				commodity.setBuyer_name(buyer_name);
				commodity.setReleaser_name(releaser_name);
				commodity.setShenhe(shenhe);

				list.add(commodity);
			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<Commodity> getNoBuyCommodity() {
		List<Commodity> list = new ArrayList<Commodity>();
		String sql = null;
		DBHelper db1 = null;
		sql = "select * from commodity  where buyer = 'null' AND shenhe LIKE '已发布%' ";
		db1 = new DBHelper(sql);
		ResultSet ret = null;
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				String id = ret.getString(1);
				String title = ret.getString(2);
				String price = ret.getString(3);
				String unit = ret.getString(4);
				String shuliang = ret.getString(5);
				String freight = ret.getString(6);
				String phone = ret.getString(7);
				String picture = ret.getString(8);
				String[] aa = picture.split(",");

				List<Picture> list2 = new ArrayList<Picture>();
				for (int i = 0; i < aa.length; i++) {
					Picture picture_url = new Picture();
					picture_url.setPicture_url(aa[i]);
					list2.add(picture_url);
				}

				String content = ret.getString(9);
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
				String seller_id = ret.getString(10);
				String buyer_id = ret.getString(11);
				String releaser_id = ret.getString(12);
				String seller_name = ret.getString(13);
				String buyer_name = ret.getString(14);
				String releaser_name = ret.getString(15);
				String shenhe = ret.getString(16);

				Commodity commodity = new Commodity();
				commodity.setCommodity_id(id);
				commodity.setTitle(title);
				commodity.setPrice(price);
				commodity.setUnit(unit);
				commodity.setShuliang(shuliang);
				commodity.setFreight(freight);
				commodity.setPhone(phone);
				commodity.setPicture(list2);
				commodity.setContent(list3);
				commodity.setSeller_id(seller_id);
				commodity.setBuyer_id(buyer_id);
				commodity.setReleaser_id(releaser_id);
				commodity.setSeller_name(seller_name);
				commodity.setBuyer_name(buyer_name);
				commodity.setReleaser_name(releaser_name);
				commodity.setShenhe(shenhe);

				list.add(commodity);
			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void addCommodity(String title, String price, String unit, String shuliang, String freight,
			String phone, String picture, String content, String seller_id) {
		{
			String sql = null;
			DBHelper db1 = null;
			String uuid = UUID.randomUUID().toString();
			String releaser_id = seller_id;
			String seller_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(seller_id).getName();
			String buyer_name = null;
			String releaser_name = seller_name;
			Timestamp time = new Timestamp(new Date().getTime());
			String shenhe = "正在审核中...";

			sql = "INSERT INTO commodity(id,title,price,unit,shuliang,freight,phone,picture,content,seller_id,releaser_id,seller_name,releaser_name,shenhe,time) VALUES ('"
					+ uuid + "', '" + title + "', '" + price + "','" + unit + "','" + shuliang + "','" + freight + "','"
					+ phone + "','" + picture + "','" + content + "','" + seller_id + "','" + seller_id + "','"
					+ seller_name + "','" + seller_name + "','" + shenhe + "','" + time + "')";
			db1 = new DBHelper(sql);
			try {
				boolean ret = db1.pst.execute();
				db1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public static void DeleteCommodity(String id) {
		{
			String sql = null;
			DBHelper db1 = null;
			sql = "DELETE FROM commodity WHERE id =  '" + id + "'";
			db1 = new DBHelper(sql);
			try {
				boolean ret = db1.pst.execute();
				db1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public static void UpdateCommodity(String key, String value, String id) {
		{
			String sql = null;
			DBHelper db1 = null;
			sql = "UPDATE commodity SET " + key + " = '" + value + "' WHERE id = '" + id + "'";
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
