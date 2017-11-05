package com.magicmoble.luzhouapp.business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.magicmoble.luzhouapp.model.Admin_xinxi;
import com.magicmoble.luzhouapp.model.Apk_model;
import com.magicmoble.luzhouapp.model.Buy_sell;
import com.magicmoble.luzhouapp.model.Create_order;
import com.magicmoble.luzhouapp.model.Dianzan_Number;
import com.magicmoble.luzhouapp.model.Dianzan_list;
import com.magicmoble.luzhouapp.model.Dianzan_touxiang;
import com.magicmoble.luzhouapp.model.Faxian_Shouye;
import com.magicmoble.luzhouapp.model.Guangjie_Shouye;
import com.magicmoble.luzhouapp.model.Guangjie_Xiangqing;
import com.magicmoble.luzhouapp.model.Guanzhu;
import com.magicmoble.luzhouapp.model.Heimingdan;
import com.magicmoble.luzhouapp.model.Hongbao;
import com.magicmoble.luzhouapp.model.HongbaoBeen;
import com.magicmoble.luzhouapp.model.Huifu;
import com.magicmoble.luzhouapp.model.Huifu_list;
import com.magicmoble.luzhouapp.model.Message;
import com.magicmoble.luzhouapp.model.Picture;
import com.magicmoble.luzhouapp.model.Pinglun;
import com.magicmoble.luzhouapp.model.Quchu_Shouye;
import com.magicmoble.luzhouapp.model.Remen_Sousuo;
import com.magicmoble.luzhouapp.model.Shoucang_liebiao;
import com.magicmoble.luzhouapp.model.Shuoshuo_Xiangqing;
import com.magicmoble.luzhouapp.model.Toutiao_Shouye;
import com.magicmoble.luzhouapp.model.Tuikuan;

public class FunctionBusiness {

	public static List<Object> getShuoshuoById(String my_id, String _id, int page) {// page是评论的页数
		List<Object> list = new ArrayList<Object>();
		String sql = null;
		DBHelper db1 = null;
		boolean flag = false;
		boolean isdianzan = false;
		sql = "SELECT * FROM shuoshuo WHERE id= '" + _id + "' ";
		db1 = new DBHelper(sql);
		ResultSet ret = null;
		List<Integer> integers = new ArrayList<Integer>();
		try {
			if (page == 1) {
				ret = db1.pst.executeQuery();
				while (ret.next()) {
					String id = ret.getString(1);
					String releaser_id = ret.getString(2);
					String releaser_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id).getName();
					String releaser_touxiang = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id)
							.getTouxiang_picture();
					String qianming = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id).getQianming();
					String picture = ret.getString(3);
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
					if (my_id == null) {
						isdianzan = false;
					} else {
						isdianzan = getDianzan(my_id, id, 2);
					}
					Timestamp time = ret.getTimestamp(8);
					List<Pinglun> pinglun = FunctionBusiness.getPinglun(my_id, id, 0);
					List<Pinglun> remenpinglun = FunctionBusiness.getRemenPinglun_list(my_id, id);

					for (int i = 0; i < pinglun.size(); i++) {
						for (int j = 0; j < remenpinglun.size(); j++) {
							if (pinglun.get(i).getPinglun_id().equals(remenpinglun.get(j).getPinglun_id())) {
								integers.add(i);
								boolean a = true;
							}
						}
					}
					if (integers.size() > 3) {
						pinglun.remove(pinglun.get(integers.get(2)));
						pinglun.remove(pinglun.get(integers.get(1)));
						pinglun.remove(pinglun.get(integers.get(0)));
					} else if (integers.size() == 2) {
						pinglun.remove(pinglun.get(integers.get(1)));
						pinglun.remove(pinglun.get(integers.get(0)));
					} else if (integers.size() == 1) {
						pinglun.remove(pinglun.get(integers.get(0)));
					}

					if (remenpinglun.size() > 0) {
						for (int i = 0; i < remenpinglun.size(); i++) {
							pinglun.add(0, remenpinglun.get(i));
							if (i == 3) {
								break;
							}
						}

					}

					Timestamp now_time = new Timestamp(new Date().getTime());
					int pinglun_count = FunctionBusiness.getPinglun_size(id);
					Dianzan_Number dianzan_count = FunctionBusiness.getDianzanNumber(id, 2);

					Shuoshuo_Xiangqing shuoshuo = new Shuoshuo_Xiangqing();
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
					shuoshuo.setPinglun(pinglun);
					shuoshuo.setPinglun_count(pinglun_count);
					list.add(shuoshuo);
				}
				ret.close();
			} else if (page > 1) {
				List<Pinglun> pingluns = FunctionBusiness.getPinglun_list(_id, my_id, page);
				for (int i = 0; i < pingluns.size(); i++) {
					list.add(pingluns.get(i));
				}
			}
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<Buy_sell> getMySell(int page, String my_id) {
		List<Buy_sell> list = new ArrayList<Buy_sell>();
		String sql = null;
		DBHelper db1 = null;
		sql = "select transaction_id,buyer_id,tiaomu_id,seller_zhuangtai,guangjie_fenlei_Tag from dingdan WHERE seller_id='"
				+ my_id + "' LIMIT " + (page - 1) * 12 + "," + 12 * page;
		db1 = new DBHelper(sql);
		ResultSet ret = null;
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				String transaction_id = ret.getString(1);
				String buyer_id = ret.getString(2);
				String tiaomu_id = ret.getString(3);

				int seller_zhangtai_tag = ret.getInt(4);
				int guangjie_fenlei_Tag = ret.getInt(5);
				Admin_xinxi admin_xinxi = Admin_xinxi_Business.getAdmin_xinxiInfoById(buyer_id);
				String touxiang = admin_xinxi.getTouxiang_picture();
				String name = admin_xinxi.getName();
				String qianming = admin_xinxi.getQianming();
				Guangjie_Xiangqing guangjie_Xiangqing = CommodityBusiness.getGuangjie_CommodityById(tiaomu_id, my_id,
						guangjie_fenlei_Tag);
				List<Picture> list2 = guangjie_Xiangqing.getPicture();
				String title = guangjie_Xiangqing.getTitle();
				String seller_zhuangtai_message = null;
				if (seller_zhangtai_tag == 1) {
					seller_zhuangtai_message = "待付款";
				} else if (seller_zhangtai_tag == 2) {
					seller_zhuangtai_message = "待接单";
				} else if (seller_zhangtai_tag == 3) {
					seller_zhuangtai_message = "进行中";
				} else if (seller_zhangtai_tag == 4) {
					seller_zhuangtai_message = "待完成";
				} else if (seller_zhangtai_tag == 6) {
					seller_zhuangtai_message = "有投诉";
				} else if (seller_zhangtai_tag == 5) {
					seller_zhuangtai_message = "已完成";
				} else if (seller_zhangtai_tag == 7) {
					seller_zhuangtai_message = "订单关闭";
				}

				List<Picture> list3 = new ArrayList<Picture>();
				list3.add(new Picture());

				Buy_sell buy_sell = new Buy_sell();
				buy_sell.setTransaction_id(transaction_id);
				buy_sell.setGuangjie_fenlei_Tag(guangjie_fenlei_Tag);
				buy_sell.setTouxiang(touxiang);
				buy_sell.setName(name);
				buy_sell.setQianming(qianming);
				buy_sell.setPictures(list2);
				buy_sell.setTitle(title);
				buy_sell.setZhuangtai_message(seller_zhuangtai_message);
				buy_sell.setZhuangtai_Tag(seller_zhangtai_tag);
				buy_sell.setPictures(list3);

				list.add(buy_sell);
			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<Buy_sell> getMyBuy(int page, String my_id) {
		List<Buy_sell> list = new ArrayList<Buy_sell>();
		String sql = null;
		DBHelper db1 = null;
		sql = "select transaction_id,seller_id,tiaomu_id,buyer_zhuangtai,guangjie_fenlei_Tag from dingdan WHERE buyer_id='"
				+ my_id + "' LIMIT " + (page - 1) * 12 + "," + 12 * page;
		db1 = new DBHelper(sql);
		ResultSet ret = null;
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				String transaction_id = ret.getString(1);
				String seller_id = ret.getString(2);
				String tiaomu_id = ret.getString(3);

				int buyer_zhangtai_Tag = ret.getInt(4);
				int guangjie_fenlei_Tag = ret.getInt(5);

				String touxiang = Admin_xinxi_Business.getAdmin_xinxiInfoById(seller_id).getTouxiang_picture();
				String name = Admin_xinxi_Business.getAdmin_xinxiInfoById(seller_id).getName();
				String qianming = Admin_xinxi_Business.getAdmin_xinxiInfoById(seller_id).getQianming();
				List<Picture> list2 = CommodityBusiness.getGuangjie_CommodityById(tiaomu_id, my_id, guangjie_fenlei_Tag)
						.getPicture();
				String title = CommodityBusiness.getGuangjie_CommodityById(tiaomu_id, my_id, guangjie_fenlei_Tag)
						.getTitle();
				double price = Dingdan_Business.gettotal_price(transaction_id);

				String buyer_zhuangtai_message = null;
				if (buyer_zhangtai_Tag == 1) {
					buyer_zhuangtai_message = "未付款" + price + "元";
				} else if (buyer_zhangtai_Tag == 2) {
					buyer_zhuangtai_message = "待接单";
				} else if (buyer_zhangtai_Tag == 3) {
					buyer_zhuangtai_message = "进行中";
				} else if (buyer_zhangtai_Tag == 4) {
					buyer_zhuangtai_message = "待完成";
				} else if (buyer_zhangtai_Tag == 6) {
					buyer_zhuangtai_message = "投诉中";
				} else if (buyer_zhangtai_Tag == 5) {
					buyer_zhuangtai_message = "已完成";
				} else if (buyer_zhangtai_Tag == 7) {
					buyer_zhuangtai_message = "订单关闭";
				}
				List<Picture> list3 = new ArrayList<Picture>();
				list3.add(new Picture());

				Buy_sell buy_sell = new Buy_sell();
				buy_sell.setTransaction_id(transaction_id);
				buy_sell.setGuangjie_fenlei_Tag(guangjie_fenlei_Tag);
				buy_sell.setTouxiang(touxiang);
				buy_sell.setName(name);
				buy_sell.setQianming(qianming);
				buy_sell.setPictures(list2);
				buy_sell.setTitle(title);
				buy_sell.setZhuangtai_message(buyer_zhuangtai_message);
				buy_sell.setZhuangtai_Tag(buyer_zhangtai_Tag);
				buy_sell.setPictures(list3);
				buy_sell.setPrice(price);

				list.add(buy_sell);
			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<Object> getMyRelease_Commodity(int page, String my_id) {
		List<Object> list = new ArrayList<Object>();
		String sql = null;
		DBHelper db1 = null;
		sql = "SELECT *FROM (SELECT id ,title,picture,shenhe,time,releaser_id,guangjie_fenlei_Tag FROM commodity UNION SELECT id ,title,picture,shenhe,time,releaser_id,guangjie_fenlei_Tag FROM fuwu) a where releaser_id='"
				+ my_id + "' order BY time DESC LIMIT " + (page - 1) * 12 + "," + 12 * page;
		db1 = new DBHelper(sql);
		ResultSet ret = null;
		try {
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

				String shenhe = ret.getString(4);
				Timestamp time = ret.getTimestamp(5);

				int guangjie_fenlei_Tag = ret.getInt(7);

				Shoucang_liebiao shoucang = new Shoucang_liebiao();
				shoucang.setShoucang_liebiao_id(id);
				shoucang.setTiaomu_id(id);
				shoucang.setPicture(list2);
				shoucang.setTitle(title);
				shoucang.setShenhe(shenhe);
				shoucang.setTime(time);
				shoucang.setMuban_Tag(2);
				shoucang.setGuangjie_fenlei_Tag(guangjie_fenlei_Tag);
				shoucang.setFlag(3);

				list.add(shoucang);
			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<Object> getMyRelease_Faxian(int page, String my_id) {
		List<Object> list = new ArrayList<Object>();
		String sql = null;
		DBHelper db1 = null;
		sql = "select id,title,picture,shenhe,time from faxian  WHERE releaser_id='" + my_id
				+ "' ORDER BY time DESC LIMIT " + (page - 1) * 12 + "," + 12 * page;
		db1 = new DBHelper(sql);
		ResultSet ret = null;
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

				String shenhe = ret.getString(4);
				Timestamp time = ret.getTimestamp(5);

				Shoucang_liebiao shoucang = new Shoucang_liebiao();
				shoucang.setShoucang_liebiao_id(id);
				shoucang.setTiaomu_id(id);
				shoucang.setTiaomu_id(id);
				shoucang.setPicture(list2);
				shoucang.setTitle(title);
				shoucang.setShenhe(shenhe);
				shoucang.setTime(time);
				shoucang.setMuban_Tag(2);
				shoucang.setFlag(2);

				list.add(shoucang);
			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<Object> getMyRelease_Toutiao(int page, String my_id) {
		String sql = null;
		DBHelper db1 = null;

		sql = "select id,title,picture,shenhe,time,muban_Tag from toutiao where releaser_id='" + my_id
				+ "' order by time desc LIMIT " + (page - 1) * 12 + "," + 12 * page;
		db1 = new DBHelper(sql);
		ResultSet ret = null;

		List<Object> list = new ArrayList<Object>();
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
				String shenhe = ret.getString(4);
				Timestamp time = ret.getTimestamp(5);
				int muban_Tag = ret.getInt(6);

				Shoucang_liebiao shoucang = new Shoucang_liebiao();
				shoucang.setShoucang_liebiao_id(id);
				shoucang.setTiaomu_id(id);
				shoucang.setPicture(list2);
				shoucang.setTitle(title);
				shoucang.setShenhe(shenhe);
				shoucang.setTime(time);
				shoucang.setMuban_Tag(muban_Tag);
				shoucang.setFlag(1);
				list.add(shoucang);

			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<Object> getMyRelease_Quchu(int page, String my_id) {
		List<Object> list = new ArrayList<Object>();
		String sql = null;
		DBHelper db1 = null;
		sql = "select id,title,picture,shenhe,time from quchu where releaser_id='" + my_id + "' LIMIT "
				+ (page - 1) * 12 + "," + 12 * page;
		db1 = new DBHelper(sql);
		ResultSet ret = null;
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
				String shenhe = ret.getString(4);
				Timestamp time = ret.getTimestamp(5);

				Shoucang_liebiao shoucang = new Shoucang_liebiao();
				shoucang.setShoucang_liebiao_id(id);
				shoucang.setTiaomu_id(id);
				shoucang.setPicture(list2);
				shoucang.setTitle(title);
				shoucang.setShenhe(shenhe);
				shoucang.setTime(time);
				shoucang.setMuban_Tag(2);
				shoucang.setFlag(4);
				list.add(shoucang);

			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<Shuoshuo_Xiangqing> getMyShuoshuo(int page, String my_id) {
		List<Shuoshuo_Xiangqing> list = new ArrayList<Shuoshuo_Xiangqing>();
		String sql = null;
		DBHelper db1 = null;
		sql = "select * from shuoshuo where releaser_id='" + my_id + "' LIMIT " + (page - 1) * 12 + "," + 12 * page;
		db1 = new DBHelper(sql);
		ResultSet ret = null;
		boolean isdianzan = false;

		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {

				String id = ret.getString(1);
				String releaser_id = ret.getString(2);
				String releaser_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id).getName();
				String releaser_touxiang = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id)
						.getTouxiang_picture();
				String qianming = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id).getQianming();
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
				int muban_Tag = ret.getInt(6);

				isdianzan = FunctionBusiness.getDianzan(my_id, id, 2);
				List<Pinglun> pinglun = FunctionBusiness.getPinglun_list(id, my_id, 1);

				Timestamp time = ret.getTimestamp(8);
				Timestamp now_time = new Timestamp(new Date().getTime());

				Dianzan_Number dianzan_Number = FunctionBusiness.getDianzanNumber(id, 2);

				Shuoshuo_Xiangqing shuoshuo = new Shuoshuo_Xiangqing();
				shuoshuo.setShuoshuo_id(id);
				shuoshuo.setPicture(list2);
				shuoshuo.setContent(content);
				shuoshuo.setDianzan_count(dianzan_Number);
				shuoshuo.setTime(time);
				shuoshuo.setReleaser_id(releaser_id);
				shuoshuo.setReleaser_name(releaser_name);
				shuoshuo.setReleaser_touxiang(releaser_touxiang);
				shuoshuo.setQianming(qianming);
				shuoshuo.setMuban_Tag(muban_Tag);
				shuoshuo.setNow_time(now_time);
				shuoshuo.setPinglun(pinglun);
				shuoshuo.setIsdianzan(isdianzan);
				list.add(shuoshuo);

			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void addShoucangByQuchu(String my_id, String quchu_id) {
		String sql = null;
		String sql1 = null;
		DBHelper db1 = null;
		DBHelper db2 = null;
		String picture = null;

		String title = null;
		String uuid = UUID.randomUUID().toString();
		try {
			sql1 = "select picture,title from quchu where id='" + quchu_id + "'";
			db2 = new DBHelper(sql1);
			ResultSet ret1 = db2.pst.executeQuery();

			while (ret1.next()) {
				picture = ret1.getString(1);
				title = ret1.getString(2);
			}

			Timestamp time = new Timestamp(new Date().getTime());

			sql = "INSERT INTO shoucang(id,my_id,quchu_id,picture,title,time) VALUES ('" + uuid + "', '" + my_id
					+ "', '" + quchu_id + "', '" + picture + "', '" + title + "', '" + time + "')";
			db1 = new DBHelper(sql);

			boolean ret = db1.pst.execute();

			db2.close();

			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void addShoucangByCommodity(String my_id, String commodity_id, int guangjie_fenlei_Tag) {
		String sql = null;
		String sql1 = null;
		DBHelper db1 = null;
		DBHelper db2 = null;
		String uuid = UUID.randomUUID().toString();
		String picture = null;
		String title = null;
		try {
			if (guangjie_fenlei_Tag == 1) {
				sql1 = "select picture,title from commodity where id='" + commodity_id + "'";
			} else if (guangjie_fenlei_Tag == 2) {
				sql1 = "select picture,title from commodity where id='" + commodity_id + "'";
			}

			db2 = new DBHelper(sql1);
			ResultSet ret1 = db2.pst.executeQuery();

			while (ret1.next()) {
				picture = ret1.getString(1);
				title = ret1.getString(2);
			}

			Timestamp time = new Timestamp(new Date().getTime());

			sql = "INSERT INTO shoucang(id,my_id,commodity_id,guangjie_fenlei_Tag,picture,title,time) VALUES ('" + uuid
					+ "', '" + my_id + "', '" + commodity_id + "', '" + guangjie_fenlei_Tag + "', '" + picture + "', '"
					+ title + "', '" + time + "')";
			db1 = new DBHelper(sql);

			boolean ret = db1.pst.execute();

			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void addShoucangByToutiao(String my_id, String toutiao_id) {
		String sql = null;
		String sql1 = null;
		DBHelper db1 = null;
		DBHelper db2 = null;
		String uuid = UUID.randomUUID().toString();
		String picture = null;
		String title = null;
		String muban_Tag = null;

		try {

			sql1 = "select picture,title,muban_Tag from toutiao where id='" + toutiao_id + "'";
			db2 = new DBHelper(sql1);
			ResultSet ret1 = db2.pst.executeQuery();

			while (ret1.next()) {
				picture = ret1.getString(1);
				title = ret1.getString(2);
				muban_Tag = ret1.getString(3);

			}

			Timestamp time = new Timestamp(new Date().getTime());

			sql = "INSERT INTO shoucang(id,my_id,toutiao_id,picture,title,time,muban_Tag) VALUES ('" + uuid + "', '"
					+ my_id + "', '" + toutiao_id + "', '" + picture + "', '" + title + "', '" + time + "', '"
					+ muban_Tag + "')";
			db1 = new DBHelper(sql);

			boolean ret = db1.pst.execute();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void addShoucangByFaxian(String my_id, String faxian_id) {
		String sql = null;
		String sql1 = null;
		DBHelper db1 = null;
		DBHelper db2 = null;
		String uuid = UUID.randomUUID().toString();
		String picture = null;
		String title = null;

		try {

			sql1 = "select picture,title from faxian where id='" + faxian_id + "'";
			db2 = new DBHelper(sql1);
			ResultSet ret1 = db2.pst.executeQuery();

			while (ret1.next()) {
				picture = ret1.getString(1);
				title = ret1.getString(2);
			}

			Timestamp time = new Timestamp(new Date().getTime());

			sql = "INSERT INTO shoucang(id,my_id,faxian_id,picture,title,time) VALUES ('" + uuid + "', '" + my_id
					+ "', '" + faxian_id + "', '" + picture + "', '" + title + "', '" + time + "')";
			db1 = new DBHelper(sql);
			boolean ret = db1.pst.execute();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void DeleteShoucangByQuchu_Id(String my_id, String quchu_id) {
		{
			String sql = null;
			DBHelper db1 = null;
			sql = "DELETE FROM shoucang WHERE my_id =  '" + my_id + "'AND quchu_id='" + quchu_id + "'";
			db1 = new DBHelper(sql);
			try {
				boolean ret = db1.pst.execute();
				db1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public static void DeleteShoucangByCommodity_Id(String my_id, String commodity_id, int guangjie_fenlei_Tag) {
		{
			String sql = null;
			DBHelper db1 = null;
			sql = "DELETE FROM shoucang WHERE my_id =  '" + my_id + "" + "' AND commodity_id='" + commodity_id
					+ "' AND guangjie_fenlei_Tag='" + guangjie_fenlei_Tag + "'";
			db1 = new DBHelper(sql);
			try {
				boolean ret = db1.pst.execute();
				db1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public static void DeleteShoucangByToutiao_Id(String my_id, String toutiao_id) {
		{
			String sql = null;
			DBHelper db1 = null;
			sql = "DELETE FROM shoucang WHERE my_id =  '" + my_id + "'AND toutiao_id='" + toutiao_id + "'";
			db1 = new DBHelper(sql);
			try {
				boolean ret = db1.pst.execute();
				db1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public static List<Pinglun> getPinglun_list(String id, String my_id, int page) {

		List<Pinglun> pinglun = FunctionBusiness.getPinglun(my_id, id, page);

		return pinglun;
	}

	public static List<Pinglun> getRemenPinglun_list(String my_id, String id) {

		List<Pinglun> remen_pinglun = FunctionBusiness.getRemenPinglun(my_id, id);

		return remen_pinglun;
	}

	public static void DeleteShoucangByFaxian_Id(String my_id, String faxian_id) {
		{
			String sql = null;
			DBHelper db1 = null;
			sql = "DELETE FROM shoucang WHERE my_id =  '" + my_id + "'AND faxian_id='" + faxian_id + "'";
			db1 = new DBHelper(sql);
			try {
				boolean ret = db1.pst.execute();
				db1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public static List<Shoucang_liebiao> getMyShoucangByQuchu_id(String _my_id, int page) {
		List<Shoucang_liebiao> list = new ArrayList<Shoucang_liebiao>();
		String sql = null;
		DBHelper db1 = null;

		sql = "SELECT id,my_id,quchu_id,picture,title,time FROM shoucang WHERE my_id='" + _my_id
				+ "' AND quchu_id is not NULL order by time desc LIMIT " + (page - 1) * 10 + "," + 10 * page;
		db1 = new DBHelper(sql);
		ResultSet ret = null;
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				String id = ret.getString(1);
				String my_id = ret.getString(2);
				String quchu_id = ret.getString(3);
				List<Picture> list2 = new ArrayList<Picture>();
				String title = ret.getString(5);
				String picture = ret.getString(4);
				if (picture != null) {
					String[] aa = picture.split(",");

					for (int i = 0; i < aa.length; i++) {
						Picture picture_url = new Picture();
						picture_url.setPicture_url(aa[i]);
						list2.add(picture_url);
					}

				}
				if (list2.size() == 0) {
					Picture picture_url = new Picture();
					picture_url.setPicture_url("");
					list2.add(picture_url);
				}
				Timestamp time = ret.getTimestamp(6);
				Shoucang_liebiao shoucang = new Shoucang_liebiao();
				shoucang.setShoucang_liebiao_id(id);
				shoucang.setTiaomu_id(quchu_id);
				shoucang.setTitle(title);
				shoucang.setPicture(list2);
				shoucang.setTime(time);
				shoucang.setMuban_Tag(2);
				shoucang.setDelect_Tag(2);
				list.add(shoucang);

			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<Shoucang_liebiao> getMyShoucangByToutiao_id(String _my_id, int page) {
		List<Shoucang_liebiao> list = new ArrayList<Shoucang_liebiao>();
		String sql = null;
		DBHelper db1 = null;

		sql = "select id,my_id,toutiao_id,picture,title,time,muban_Tag from shoucang WHERE  my_id='" + _my_id
				+ "' AND toutiao_id is not null order by time desc LIMIT " + (page - 1) * 10 + "," + 10 * page;
		db1 = new DBHelper(sql);
		ResultSet ret = null;
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				String id = ret.getString(1);
				String my_id = ret.getString(2);
				String toutiao_id = ret.getString(3);
				List<Picture> list2 = new ArrayList<Picture>();
				String title = ret.getString(5);
				String picture = ret.getString(4);
				if (picture != null) {
					String[] aa = picture.split(",");

					for (int i = 0; i < aa.length; i++) {
						Picture picture_url = new Picture();
						picture_url.setPicture_url(aa[i]);
						list2.add(picture_url);
					}

				}
				if (list2.size() == 0) {
					Picture picture_url = new Picture();
					picture_url.setPicture_url("");
					list2.add(picture_url);
				}
				Timestamp time = ret.getTimestamp(6);
				int muban_Tag = ret.getInt(7);

				Shoucang_liebiao shoucang = new Shoucang_liebiao();
				shoucang.setShoucang_liebiao_id(id);
				shoucang.setTiaomu_id(toutiao_id);
				shoucang.setTitle(title);
				shoucang.setPicture(list2);
				shoucang.setTime(time);
				shoucang.setMuban_Tag(muban_Tag);
				shoucang.setDelect_Tag(2);

				list.add(shoucang);

			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<Shoucang_liebiao> getMyShoucangByFaxian_id(String _my_id, int page) {
		List<Shoucang_liebiao> list = new ArrayList<Shoucang_liebiao>();
		String sql = null;
		DBHelper db1 = null;
		sql = "select id,my_id,faxian_id,picture,title,time from shoucang WHERE  my_id='" + _my_id
				+ " ' AND faxian_id is not null order by time desc LIMIT " + (page - 1) * 10 + "," + 10 * page;
		db1 = new DBHelper(sql);
		ResultSet ret = null;
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				String id = ret.getString(1);
				String my_id = ret.getString(2);
				String faxian_id = ret.getString(3);
				List<Picture> list2 = new ArrayList<Picture>();
				String title = ret.getString(5);
				String picture = ret.getString(4);
				if (picture != null) {
					String[] aa = picture.split(",");

					for (int i = 0; i < aa.length; i++) {
						Picture picture_url = new Picture();
						picture_url.setPicture_url(aa[i]);
						list2.add(picture_url);
					}

				}
				if (list2.size() == 0) {
					Picture picture_url = new Picture();
					picture_url.setPicture_url("");
					list2.add(picture_url);
				}

				Timestamp time = ret.getTimestamp(6);

				Shoucang_liebiao shoucang = new Shoucang_liebiao();
				shoucang.setShoucang_liebiao_id(id);
				shoucang.setTiaomu_id(faxian_id);
				shoucang.setTitle(title);
				shoucang.setPicture(list2);
				shoucang.setTime(time);
				shoucang.setMuban_Tag(2);
				shoucang.setDelect_Tag(2);
				list.add(shoucang);
			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<Shoucang_liebiao> getMyShoucangByCommodity_id(String _my_id, int page) {
		List<Shoucang_liebiao> list = new ArrayList<Shoucang_liebiao>();
		String sql = null;
		DBHelper db1 = null;

		sql = "select id,my_id,commodity_id,guangjie_fenlei_Tag,picture,title,time from shoucang WHERE my_id='" + _my_id
				+ "' AND commodity_id is not null order by time desc LIMIT " + (page - 1) * 10 + "," + 10 * page;
		db1 = new DBHelper(sql);
		ResultSet ret = null;
		String title = null;
		Timestamp time = null;
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				String id = ret.getString(1);
				String my_id = ret.getString(2);
				String commodity_id = ret.getString(3);
				int guangjie_fenlei_Tag = ret.getInt(4);
				List<Picture> list2 = new ArrayList<Picture>();
				title = ret.getString(6);
				String picture = ret.getString(5);
				if (picture != null) {
					String[] aa = picture.split(",");

					for (int i = 0; i < aa.length; i++) {
						Picture picture_url = new Picture();
						picture_url.setPicture_url(aa[i]);
						list2.add(picture_url);
					}

				}
				if (list2.size() == 0) {
					Picture picture_url = new Picture();
					picture_url.setPicture_url("");
					list2.add(picture_url);
				}

				time = ret.getTimestamp(7);

				Shoucang_liebiao shoucang = new Shoucang_liebiao();
				shoucang.setShoucang_liebiao_id(id);
				shoucang.setTiaomu_id(commodity_id);
				shoucang.setGuangjie_fenlei_Tag(guangjie_fenlei_Tag);
				shoucang.setTitle(title);
				shoucang.setPicture(list2);
				shoucang.setTime(time);
				shoucang.setMuban_Tag(2);
				shoucang.setDelect_Tag(2);
				list.add(shoucang);
			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<Shoucang_liebiao> getZujiByToutiao_id(String _my_id, int page) {
		List<Shoucang_liebiao> list = new ArrayList<Shoucang_liebiao>();
		String sql = null;
		DBHelper db1 = null;

		sql = "select id,my_id,toutiao_id,picture,title,time,muban_Tag from zuji WHERE  my_id='" + _my_id
				+ "' and toutiao_id is not NULL order by time desc limit " + (page - 1) * 12 + "," + 12 * page;
		db1 = new DBHelper(sql);
		ResultSet ret = null;
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				String id = ret.getString(1);
				String my_id = ret.getString(2);
				String toutiao_id = ret.getString(3);
				String title = ret.getString(5);

				List<Picture> list2 = new ArrayList<Picture>();
				String picture = ret.getString(4);
				if (picture != null) {
					String[] aa = picture.split(",");

					for (int i = 0; i < aa.length; i++) {
						Picture picture_url = new Picture();
						picture_url.setPicture_url(aa[i]);
						list2.add(picture_url);
					}

				}
				if (list2.size() == 0) {
					Picture picture_url = new Picture();
					picture_url.setPicture_url("");
					list2.add(picture_url);
				}
				Timestamp time = ret.getTimestamp(6);
				int muban_Tag = ret.getInt(7);

				Shoucang_liebiao shoucang = new Shoucang_liebiao();
				shoucang.setShoucang_liebiao_id(id);
				shoucang.setTitle(title);
				shoucang.setPicture(list2);
				shoucang.setTime(time);
				shoucang.setMuban_Tag(muban_Tag);
				shoucang.setDelect_Tag(1);
				shoucang.setTiaomu_id(toutiao_id);
				list.add(shoucang);

			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<Shoucang_liebiao> getZujiByQuchu_id(String _my_id, int page) {
		List<Shoucang_liebiao> list = new ArrayList<Shoucang_liebiao>();
		String sql = null;
		DBHelper db1 = null;

		sql = "select id,my_id,quchu_id,picture,title,time from zuji WHERE  my_id='" + _my_id
				+ "' and quchu_id is not null order by time desc limit " + (page - 1) * 12 + "," + 12 * page;
		db1 = new DBHelper(sql);
		ResultSet ret = null;
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				String id = ret.getString(1);
				String my_id = ret.getString(2);
				String quchu_id = ret.getString(3);
				String title = ret.getString(5);
				List<Picture> list2 = new ArrayList<Picture>();
				String picture = ret.getString(4);
				if (picture != null) {
					String[] aa = picture.split(",");

					for (int i = 0; i < aa.length; i++) {
						Picture picture_url = new Picture();
						picture_url.setPicture_url(aa[i]);
						list2.add(picture_url);
					}
				}
				if (list2.size() == 0) {
					Picture picture_url = new Picture();
					picture_url.setPicture_url("");
					list2.add(picture_url);
				}

				Timestamp time = ret.getTimestamp(6);

				Shoucang_liebiao shoucang = new Shoucang_liebiao();
				shoucang.setShoucang_liebiao_id(id);
				shoucang.setTitle(title);
				shoucang.setPicture(list2);
				shoucang.setTime(time);
				shoucang.setMuban_Tag(3);
				shoucang.setDelect_Tag(1);
				shoucang.setTiaomu_id(quchu_id);
				list.add(shoucang);
			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<Shoucang_liebiao> getZujiByFaxian_id(String _my_id, int page) {
		List<Shoucang_liebiao> list = new ArrayList<Shoucang_liebiao>();
		String sql = null;
		DBHelper db1 = null;

		sql = "select id,my_id,faxian_id,picture,title,time from zuji WHERE  my_id='" + _my_id
				+ " 'and faxian_id  is not null  order by time desc limit " + (page - 1) * 12 + "," + 12 * page;
		db1 = new DBHelper(sql);
		ResultSet ret = null;
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				String id = ret.getString(1);
				String my_id = ret.getString(2);
				String faxian_id = ret.getString(3);
				String title = ret.getString(5);

				List<Picture> list2 = new ArrayList<Picture>();
				String picture = ret.getString(4);
				if (picture != null) {
					String[] aa = picture.split(",");

					for (int i = 0; i < aa.length; i++) {
						Picture picture_url = new Picture();
						picture_url.setPicture_url(aa[i]);
						list2.add(picture_url);
					}

				}
				if (list2.size() == 0) {
					Picture picture_url = new Picture();
					picture_url.setPicture_url("");
					list2.add(picture_url);
				}
				Timestamp time = ret.getTimestamp(6);

				Shoucang_liebiao shoucang = new Shoucang_liebiao();
				shoucang.setShoucang_liebiao_id(id);
				shoucang.setTitle(title);
				shoucang.setPicture(list2);
				shoucang.setTime(time);
				shoucang.setMuban_Tag(3);
				shoucang.setDelect_Tag(1);
				shoucang.setTiaomu_id(faxian_id);
				list.add(shoucang);
			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<Shoucang_liebiao> getZujiByCommodity_id(String _my_id, int page) {
		List<Shoucang_liebiao> list = new ArrayList<Shoucang_liebiao>();
		String sql = null;
		DBHelper db1 = null;

		sql = "select id,my_id,commodity_id,guangjie_fenlei_Tag,picture,title,time from zuji WHERE my_id='" + _my_id
				+ "' and commodity_id is not null order by time desc limit " + (page - 1) * 12 + "," + 12 * page;
		db1 = new DBHelper(sql);
		ResultSet ret = null;
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				String id = ret.getString(1);
				String my_id = ret.getString(2);
				String commodity_id = ret.getString(3);
				int guangjie_fenlei_Tag = ret.getInt(4);
				String title = ret.getString(6);
				List<Picture> list2 = new ArrayList<Picture>();
				String picture = ret.getString(4);
				if (picture != null) {
					String[] aa = picture.split(",");

					for (int i = 0; i < aa.length; i++) {
						Picture picture_url = new Picture();
						picture_url.setPicture_url(aa[i]);
						list2.add(picture_url);
					}

				}

				if (list2.size() == 0) {
					Picture picture_url = new Picture();
					picture_url.setPicture_url("");
					list2.add(picture_url);
				}
				Timestamp time = ret.getTimestamp(7);

				Shoucang_liebiao shoucang = new Shoucang_liebiao();
				shoucang.setShoucang_liebiao_id(id);
				shoucang.setTitle(title);
				shoucang.setPicture(list2);
				shoucang.setTime(time);
				shoucang.setMuban_Tag(3);
				shoucang.setDelect_Tag(1);
				shoucang.setGuangjie_fenlei_Tag(guangjie_fenlei_Tag);
				shoucang.setTiaomu_id(commodity_id);

				list.add(shoucang);
			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void addDianzan(String yonghu_id, String tiaomu_id, int dianzan_Tag) {
		String sql = null;
		String sql2 = null;
		String sql3 = null;
		String sql4 = null;
		DBHelper db1 = null;
		DBHelper db2 = null;
		DBHelper db3 = null;
		DBHelper db4 = null;
		String uuid = UUID.randomUUID().toString();
		String touxiang = Admin_xinxi_Business.getAdmin_xinxiInfoById(yonghu_id).getTouxiang_picture();
		sql = "INSERT INTO dianzan_list(id,tiaomu_id,yonghu_id,dianzan_Tag,dianzan_touxiang) VALUES ('" + uuid + "', '"
				+ tiaomu_id + "', '" + yonghu_id + "', '" + dianzan_Tag + "','" + touxiang + "')";

		sql2 = " SELECT count(id) FROM dianzan_list WHERE tiaomu_id='" + tiaomu_id + "'";

		if (dianzan_Tag == 1 && dianzan_Tag == 3 && dianzan_Tag == 4 && dianzan_Tag == 5 && dianzan_Tag == 6) {

		}
		db1 = new DBHelper(sql);

		int count = 0;
		try {

			boolean ret = db1.pst.execute();
			db2 = new DBHelper(sql2);
			ResultSet ret2 = db2.pst.executeQuery();
			while (ret2.next()) {
				count = ret2.getInt(1);

			}
			if (dianzan_Tag == 2) {
				sql4 = "update shuoshuo set dianzan_count=" + count + " where id='" + tiaomu_id + "'";
				db4 = new DBHelper(sql4);
				int ret4 = db4.pst.executeUpdate();

			}

			sql3 = "UPDATE pinglun set dianzan_count=" + count + " WHERE id='" + tiaomu_id + "' and pingluner_id='"
					+ yonghu_id + "'";
			db3 = new DBHelper(sql3);
			int ret3 = db3.pst.executeUpdate();

			db1.close();
			db2.close();
			db3.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean getDianzan(String yonghu_id, String tiaomu_id, int dianzan_Tag) throws SQLException {
		String sql = null;
		DBHelper db1 = null;
		boolean flag = false;
		String id = null;
		sql = "select * from dianzan_list where yonghu_id='" + yonghu_id + "' and tiaomu_id='" + tiaomu_id
				+ "' and dianzan_Tag='" + dianzan_Tag + "'";
		db1 = new DBHelper(sql);
		ResultSet ret = null;

		ret = db1.pst.executeQuery();
		while (ret.next()) {
			id = ret.getString(1);

		}
		if (id == null) {
			flag = false;
		} else {
			flag = true;
		}
		db1.close();
		return flag;
	}

	public static void DeleteDianzan(String my_id, String tiaomu_id, int dianzan_Tag) {
		String sql = null;
		DBHelper db1 = null;

		sql = "DELETE FROM dianzan_list WHERE yonghu_id =  '" + my_id + "' AND tiaomu_id='" + tiaomu_id
				+ "' AND dianzan_Tag='" + dianzan_Tag + "'";
		db1 = new DBHelper(sql);
		try {
			boolean ret = db1.pst.execute();

			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Dianzan_Number getDianzanNumber(String tiaomu_id, int dianzan_Tag) {

		String sql = null;
		DBHelper db1 = null;
		sql = "SELECT count(tiaomu_id) FROM dianzan_list WHERE tiaomu_id='" + tiaomu_id + "' and dianzan_Tag='"
				+ dianzan_Tag + "'";
		List<Dianzan_touxiang> touxiang = null;
		Dianzan_Number dianzan_Number = new Dianzan_Number();
		try {
			db1 = new DBHelper(sql);
			ResultSet ret = db1.pst.executeQuery();
			while (ret.next()) {
				int count = ret.getInt(1);
				touxiang = FunctionBusiness.getDianzan_touxiang_List(tiaomu_id, dianzan_Tag);
				dianzan_Number.setDianzan_count(count);
				dianzan_Number.setTouxiang_list(touxiang);
			}

			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dianzan_Number;
	}

	public static List<Pinglun> getPinglun(String my_id, String tiaomu_id, int page) {

		String sql = null;
		DBHelper db1 = null;
		if (page != 0) {
			sql = "SELECT id,pingluner_id,content,time FROM pinglun WHERE tiaomu_id='" + tiaomu_id
					+ "' order by time desc LIMIT " + (page - 1) * 10 + "," + 10 * page;
		} else {
			sql = "SELECT id,pingluner_id,content,time FROM pinglun WHERE tiaomu_id='" + tiaomu_id
					+ "'  order by time desc";
		}

		List<Pinglun> pinglun_list = new ArrayList<Pinglun>();
		db1 = new DBHelper(sql);
		boolean isdianzan = false;
		try {
			ResultSet ret = db1.pst.executeQuery();

			while (ret.next()) {

				String id = ret.getString(1);

				String pingluner_id = ret.getString(2);
				Admin_xinxi admin_xinxi = Admin_xinxi_Business.getAdmin_xinxiInfoById(pingluner_id);
				String pingluner_name = admin_xinxi.getName();
				String pingluner_touxiang = admin_xinxi.getTouxiang_picture();
				String pingluner_qianming = admin_xinxi.getQianming();
				String content = ret.getString(3);
				Timestamp time = ret.getTimestamp(4);
				Dianzan_Number dianzan_count = FunctionBusiness.getDianzanNumber(id, 7);

				List<Huifu> huifus = FunctionBusiness.getHuifu(id);
				int huifu_count = huifus.size();
				if (huifu_count > 3) {
					huifus = huifus.subList(0, 3);
				}

				if (my_id == null) {
					isdianzan = false;
				} else {
					isdianzan = getDianzan(my_id, id, 7);
				}

				Pinglun pinglun = new Pinglun();
				pinglun.setPinglun_id(id);
				pinglun.setPingluner_id(pingluner_id);
				pinglun.setPingluner_name(pingluner_name);
				pinglun.setPingluner_touxiang(pingluner_touxiang);
				pinglun.setContent(content);
				pinglun.setTime(time);
				pinglun.setPingluner_qianming(pingluner_qianming);
				pinglun.setDianzan_count(dianzan_count);
				pinglun.setHuifu(huifus);
				pinglun.setIsdianzan(isdianzan);
				pinglun.setTiaomu_id(tiaomu_id);
				pinglun.setHuifu_count(huifu_count);

				pinglun_list.add(pinglun);

			}

			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pinglun_list;
	}

	public static List<Pinglun> getRemenPinglun(String my_id, String tiaomu_id) {

		String sql = null;
		DBHelper db1 = null;
		int num = 0;
		sql = "SELECT id,pingluner_id,content,time,dianzan_count FROM pinglun where tiaomu_id='" + tiaomu_id
				+ "' order by dianzan_count DESC ";

		db1 = new DBHelper(sql);
		List<Pinglun> pinglun_list = new ArrayList<Pinglun>();
		boolean isdianzan = false;
		try {
			ResultSet ret = db1.pst.executeQuery();
			while (ret.next()) {
				String id = ret.getString(1);
				String pingluner_id = ret.getString(2);
				Admin_xinxi admin_xinxi = Admin_xinxi_Business.getAdmin_xinxiInfoById(pingluner_id);
				String pingluner_name = admin_xinxi.getName();
				String pingluner_touxiang = admin_xinxi.getTouxiang_picture();
				String content = ret.getString(3);
				Timestamp time = ret.getTimestamp(4);
				List<Huifu> huifus = FunctionBusiness.getHuifu(id);
				int huifu_count = huifus.size();
				if (huifu_count > 3) {
					huifus = huifus.subList(0, 3);
				}
				if (my_id == null) {
					isdianzan = false;
				} else {
					isdianzan = getDianzan(my_id, id, 7);
				}

				Dianzan_Number dianzan_count = FunctionBusiness.getDianzanNumber(id, 7);

				Pinglun pinglun = new Pinglun();
				pinglun.setPinglun_id(id);

				pinglun.setPingluner_id(pingluner_id);
				pinglun.setPingluner_name(pingluner_name);
				pinglun.setPingluner_touxiang(pingluner_touxiang);
				pinglun.setContent(content);
				pinglun.setTime(time);
				pinglun.setDianzan_count(dianzan_count);
				pinglun.setHuifu(huifus);
				pinglun.setIsdianzan(isdianzan);
				pinglun.setTiaomu_id(tiaomu_id);
				pinglun.setHuifu_count(huifu_count);
				pinglun_list.add(pinglun);

				num++;
				if (num == 3) {
					break;
				}
				System.out.println(pinglun_list.size());
			}

			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pinglun_list;
	}

	public static List<Huifu> getHuifu(String _pinglun_id) {

		String sql = null;
		DBHelper db1 = null;
		sql = "SELECT huifuer_id,huifu_content,pinglun_id FROM huifu WHERE pinglun_id='" + _pinglun_id
				+ "' order by time desc ";

		List<Huifu> huifu_list = new ArrayList<Huifu>();

		try {
			db1 = new DBHelper(sql);
			ResultSet ret = db1.pst.executeQuery();
			while (ret.next()) {
				String huifuer_id = ret.getString(1);
				String huifuer_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(huifuer_id).getName();
				String huifu_content = ret.getString(2);

				Huifu huifu = new Huifu();
				huifu.setHuifuer_id(huifuer_id);
				huifu.setHuifuer_name(huifuer_name);
				huifu.setHuifu_content(huifu_content);
				huifu.setPinglun_id(_pinglun_id);

				huifu_list.add(huifu);

			}

			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return huifu_list;
	}

	public static int gethuifu_count(String _pinglun_id) {
		DBHelper db1 = null;
		String sql = "SELECT count(pinglun_id) FROM huifu WHERE pinglun_id='" + _pinglun_id + "'";
		db1 = new DBHelper(sql);
		int count = 0;
		ResultSet ret;
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				count = ret.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return count;

	}

	public static Hongbao getHongbaoById(String _my_id, int hongbao_Tag) {
		String sql = null;
		DBHelper db1 = null;
		sql = "select id,hongbao_count,hongbao_price from hongbao WHERE tiaomu_id='" + _my_id + "'AND hongbao_Tag="
				+ hongbao_Tag;
		db1 = new DBHelper(sql);
		ResultSet ret = null;
		Hongbao hongbao = new Hongbao();
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				String id = ret.getString(1);
				int count = ret.getInt(2);
				double price = ret.getDouble(3);

				hongbao.setHongbao_id(id);
				hongbao.setCount(count);
				hongbao.setPrice(price);

			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hongbao;
	}

	public static int getDashangNumber(String tiaomu_id) {
		String sql = null;
		DBHelper db1 = null;
		int dianzan_count = -1;
		sql = "SELECT count('tiaomu_id') FROM dashang WHERE tiaomu_id='" + tiaomu_id + "'";

		db1 = new DBHelper(sql);
		try {
			ResultSet ret = db1.pst.executeQuery();
			while (ret.next()) {
				dianzan_count = ret.getInt(1);

			}

			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dianzan_count;
	}

	public static List<Dianzan_touxiang> getDianzan_touxiang_List(String tiaomu_id, int dianzan_Tag) {
		String sql = null;
		DBHelper db1 = null;

		sql = "SELECT dianzan_touxiang,yonghu_id FROM dianzan_list WHERE tiaomu_id='" + tiaomu_id + "'AND dianzan_Tag='"
				+ dianzan_Tag + "'";
		List<Dianzan_touxiang> list = new ArrayList<Dianzan_touxiang>();
		db1 = new DBHelper(sql);

		try {
			ResultSet ret = db1.pst.executeQuery();
			while (ret.next()) {
				String dianzan_touxiang = ret.getString(1);
				String yonghu_id = ret.getString(2);

				Dianzan_touxiang dianzan_touxiang2 = new Dianzan_touxiang();
				dianzan_touxiang2.setDianzan_touxiang(dianzan_touxiang);
				dianzan_touxiang2.setYonghu_id(yonghu_id);
				list.add(dianzan_touxiang2);

			}

			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static int getGuanzhu_Tag(String my_id, String friend_id) {
		String sql = null;
		DBHelper db1 = null;
		String guanzhu_Tag = null;
		sql = "SELECT id FROM guanzhu WHERE my_id='" + my_id + "' AND friend_id='" + friend_id + "'";

		db1 = new DBHelper(sql);
		List<String> list = new ArrayList<String>();
		try {
			ResultSet ret = db1.pst.executeQuery();
			while (ret.next()) {
				guanzhu_Tag = ret.getString(1);
				list.add(guanzhu_Tag);
			}
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list.size();
	}

	public static void addZujiByToutiao(String my_id, String toutiao_id, String picture, String title, int muban_Tag) {
		String sql = null;
		DBHelper db1 = null;
		String uuid = UUID.randomUUID().toString();
		Timestamp time = new Timestamp(new Date().getTime());
		sql = "INSERT INTO zuji(id,my_id,toutiao_id,time,picture,title,muban_Tag) VALUES ('" + uuid + "', '" + my_id
				+ "', '" + toutiao_id + "','" + time + "','" + picture + "','" + title + "'," + muban_Tag + ")";
		db1 = new DBHelper(sql);
		try {
			boolean ret = db1.pst.execute();

			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void addZujiByCommodity(String my_id, String commodity_id, String picture, String title) {
		String sql = null;
		DBHelper db1 = null;
		String uuid = UUID.randomUUID().toString();
		Timestamp time = new Timestamp(new Date().getTime());
		int guangjie_fenlei_Tag = 1;
		sql = "INSERT INTO zuji(id,my_id,commodity_id,time,picture,title,guangjie_fenlei_Tag) VALUES ('" + uuid + "', '"
				+ my_id + "', '" + commodity_id + "','" + time + "','" + picture + "','" + title + "','"
				+ guangjie_fenlei_Tag + "')";
		db1 = new DBHelper(sql);
		try {
			boolean ret = db1.pst.execute();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void addZujiByFuwu(String my_id, String commodity_id, String picture, String title) {
		String sql = null;
		DBHelper db1 = null;
		int guangjie_fenlei_Tag = 2;
		String uuid = UUID.randomUUID().toString();
		Timestamp time = new Timestamp(new Date().getTime());
		sql = "INSERT INTO zuji(id,my_id,fuwu_id,time,picture,title,guangjie_fenlei_Tag) VALUES ('" + uuid + "', '"
				+ my_id + "', '" + commodity_id + "','" + time + "','" + picture + "','" + title + "','"
				+ guangjie_fenlei_Tag + "')";
		db1 = new DBHelper(sql);
		try {
			boolean ret = db1.pst.execute();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void addZujiByQuchu(String my_id, String quchu_id, String picture, String title) {
		String sql = null;
		DBHelper db1 = null;
		String uuid = UUID.randomUUID().toString();
		Timestamp time = new Timestamp(new Date().getTime());
		sql = "INSERT INTO zuji(id,my_id,quchu_id,time,picture,title) VALUES ('" + uuid + "', '" + my_id + "', '"
				+ quchu_id + "','" + time + "','" + picture + "','" + title + "')";
		db1 = new DBHelper(sql);
		try {
			boolean ret = db1.pst.execute();

			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void addZujiByFaxian(String my_id, String faxian_id, String picture, String title) {
		String sql = null;
		DBHelper db1 = null;
		String uuid = UUID.randomUUID().toString();
		Timestamp time = new Timestamp(new Date().getTime());
		sql = "INSERT INTO zuji(id,my_id,faxian_id,time,picture,title) VALUES ('" + uuid + "', '" + my_id + "', '"
				+ faxian_id + "','" + time + "','" + picture + "','" + title + "')";
		db1 = new DBHelper(sql);
		try {
			boolean ret = db1.pst.execute();

			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static double getHongbaoMoney(HongbaoBeen _leftMoneyPackage) {
		// remainSize 剩余的红包数量
		// remainMoney 剩余的钱
		if (_leftMoneyPackage.remainSize == 1) {
			_leftMoneyPackage.remainSize--;
			return (double) Math.round(_leftMoneyPackage.remainMoney * 10) / 10;
		}
		Random r = new Random();
		double min = 0.1; //
		double max = _leftMoneyPackage.remainMoney / _leftMoneyPackage.remainSize * 2;
		double money = r.nextDouble() * max;
		money = money <= min ? 0.1 : money;
		money = Math.floor(money * 10) / 10;
		_leftMoneyPackage.remainSize--;
		_leftMoneyPackage.remainMoney -= money;
		return money;
	}

	public static String setHongbao(String my_id, String tiaomu_id, int hongbao_count, Double hongbao_price,
			int hongbao_Tag) {

		// hongbao_Tag:1.点赞红包 2.分享红包
		String sql = null;
		DBHelper db1 = null;

		if (hongbao_price / hongbao_count < 0.1) {
			return "发放红包失败，每个红包最小金额为0.1";
		} else {

			String uuid = UUID.randomUUID().toString();
			sql = "INSERT INTO hongbao(id,my_id,tiaomu_id,hongbao_count,hongbao_price,hongbao_Tag) VALUES ('" + uuid
					+ "', '" + my_id + "', '" + tiaomu_id + "'," + hongbao_count + "," + hongbao_price + ","
					+ hongbao_Tag + ")";
			db1 = new DBHelper(sql);
			try {
				boolean ret = db1.pst.execute();

				db1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return "操作成功";
		}
	}

	public static String setTuijian(String my_id, String tiaomu_id, int tianshu) {

		String sql = null;
		DBHelper db1 = null;

		String uuid = UUID.randomUUID().toString();
		long time = new Date().getTime();
		String str = null;
		Timestamp start_time = new Timestamp(time);
		Timestamp now_time = new Timestamp(time);
		Timestamp end_time = new Timestamp(time + tianshu * 24 * 60 * 60 * 1000);
		sql = "INSERT INTO tuijian_list(id,start_time,end_time,now_time,tiaomu_id)  VALUES ('" + uuid + "','"
				+ start_time + "','" + end_time + "','" + now_time + "','" + tiaomu_id + "')";
		boolean ret;
		db1 = new DBHelper(sql);
		try {
			ret = db1.pst.execute();

			db1.close();
			str = "操作成功";

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String getHongbao(String my_id, String tiaomu_id, int fenlei_Tag, int hongbao_Tag) {// fenlei_Tag:
																										// 1:头条
																										// 2：发现
																										// 3：去处
																										// 4：逛街
		// hongbao_Tag:1.点赞红包 2.分享红包
		String sql = null;
		String sql1 = null;
		String sql2 = null;
		String sql3 = null;
		String sql4 = null;
		DBHelper db1 = null;
		DBHelper db2 = null;
		DBHelper db3 = null;
		DBHelper db4 = null;
		DBHelper db5 = null;
		double hongbao_count = 0;
		double hongbao_price = 0;
		double money = 0;
		String id = null;
		String str = null;
		String uuid = UUID.randomUUID().toString();
		sql = "SELECT id,hongbao_count,hongbao_price FROM hongbao WHERE tiaomu_id='" + tiaomu_id + "' and hongbao_Tag="
				+ hongbao_Tag;

		db1 = new DBHelper(sql);
		int n = 0;
		ResultSet ret = null;
		boolean ret1 = false;
		int ret2 = 0;
		int ret3 = 0;
		int num = 0;
		ResultSet ret4 = null;
		List<Hongbao> hongbaos = new ArrayList<Hongbao>();
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				id = ret.getString(1);
				hongbao_count = ret.getDouble(2);
				hongbao_price = ret.getDouble(3);
				Hongbao hongbao = new Hongbao();
				hongbao.setHongbao_id(id);
				hongbao.setCount(hongbao_count);
				hongbao.setPrice(hongbao_price);
				hongbaos.add(hongbao);
			}
			for (int i = 0; i < hongbaos.size(); i++) {
				if (hongbaos.get(i).getCount() > 0) {
					num = i;
					break;
				}
			}
			if (hongbao_count == 0 || hongbao_count < 0) {
				str = "领取失败，没有红包了";

			} else {

				sql4 = "select * from hongbao_list where hongbao_id='" + hongbaos.get(num).getHongbao_id()
						+ "' and lingquer_id='" + my_id + "'";
				db5 = new DBHelper(sql4);
				ret4 = db5.pst.executeQuery();
				ret4.last(); // 结果集指针知道最后一行数据
				n = ret4.getRow();
				if (n != 0) {
					str = "领取失败，您已经领过此红包了";
				} else {

					HongbaoBeen _leftMoneyPackage = new HongbaoBeen(hongbaos.get(num).getCount(),
							hongbaos.get(num).getPrice());
					money = getHongbaoMoney(_leftMoneyPackage);
					sql1 = "INSERT INTO hongbao_list(id,lingquer_id,price,hongbao_id) VALUES ('" + uuid + "','" + my_id
							+ "'," + money + ",'" + hongbaos.get(num).getHongbao_id() + "')";
					db2 = new DBHelper(sql1);
					ret1 = db2.pst.execute();

					hongbaos.get(num).setCount(hongbaos.get(num).getCount() - 1);
					hongbaos.get(num).setPrice(hongbaos.get(num).getPrice() - money);
					sql2 = "UPDATE hongbao SET hongbao_count=" + hongbaos.get(num).getCount() + ",hongbao_price="
							+ hongbaos.get(num).getPrice() + "where id='" + hongbaos.get(num).getHongbao_id() + "'";
					db3 = new DBHelper(sql2);
					ret2 = db3.pst.executeUpdate();

					sql3 = "update admin_xinxi set qianbao=round((qianbao+" + money + "),2)  where id='" + my_id + "'";
					db4 = new DBHelper(sql3);
					ret3 = db4.pst.executeUpdate();

					str = "领取成功，获得" + money + "元";
				}
			}

			ret.close();
			db1.close();
			if (hongbao_count > 0) {
				if (n == 0) {

					db2.close();
					db3.close();
					db4.close();
					db5.close();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return str;
	}

	public static int getshoucang(String tiaomu_id, String my_id) {
		String id = null;
		String sql = null;
		DBHelper db1 = null;
		int flag = 0;
		sql = "SELECT * FROM shoucang WHERE (quchu_id='" + tiaomu_id + "' OR commodity_id='" + tiaomu_id
				+ "' OR toutiao_id='" + tiaomu_id + "' OR faxian_id='" + tiaomu_id + "') and my_id='" + my_id + "'";

		db1 = new DBHelper(sql);
		ResultSet ret = null;
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				id = ret.getString(1);

			}
			if (id != null) {
				flag = 2;
			} else {
				flag = 1;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;

	}

	public static Create_order order(String _commodity_id, int guangjie_fenlei_Tag, String my_id) {

		String sql = null;
		DBHelper db1 = null;
		Create_order create_order = new Create_order();

		if (guangjie_fenlei_Tag == 1) {
			sql = "SELECT id,picture,title,price,freight,shuliang FROM commodity WHERE id='" + _commodity_id + "'";
		} else if (guangjie_fenlei_Tag == 2) {
			sql = "SELECT id,picture,title,price FROM fuwu WHERE id='" + _commodity_id + "'";
		}

		db1 = new DBHelper(sql);
		ResultSet ret = null;

		try {
			if (guangjie_fenlei_Tag == 1) {

				ret = db1.pst.executeQuery();
				while (ret.next()) {
					String id = ret.getString(1);
					List<Picture> pictures = CommodityBusiness
							.getGuangjie_CommodityById(_commodity_id, my_id, guangjie_fenlei_Tag).getPicture();
					String title = ret.getString(3);
					double price = ret.getDouble(4);
					double freight = ret.getDouble(5);
					int shuliang = ret.getInt(6);

					create_order.setCommodity_id(_commodity_id);
					create_order.setPicture(pictures);
					create_order.setTitle(title);
					create_order.setPrice(price);
					create_order.setFreight(freight);
					create_order.setShuliang(shuliang);
				}

			} else if (guangjie_fenlei_Tag == 2) {

				ret = db1.pst.executeQuery();
				while (ret.next()) {
					String id = ret.getString(1);
					List<Picture> pictures = CommodityBusiness
							.getGuangjie_CommodityById(_commodity_id, my_id, guangjie_fenlei_Tag).getPicture();
					String title = ret.getString(3);
					double price = ret.getDouble(4);

					create_order.setCommodity_id(_commodity_id);
					create_order.setPicture(pictures);
					create_order.setTitle(title);
					create_order.setPrice(price);
				}
			}
			db1.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return create_order;
	}

	public static void DeleteHuifu(String id) {
		{
			String sql = null;
			DBHelper db1 = null;
			sql = "DELETE FROM huifu WHERE id =  '" + id + "'";
			db1 = new DBHelper(sql);
			try {
				boolean ret = db1.pst.execute();
				db1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public static void DeletePinglun(String id) {
		{
			String sql = null;
			DBHelper db1 = null;
			sql = "DELETE FROM pinglun WHERE id =  '" + id + "'";
			db1 = new DBHelper(sql);
			try {
				boolean ret = db1.pst.execute();
				db1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public static List<Object> getSousuo(String str, int fenlei_Tag, String my_id, int page) {

		String sql = null;
		String sql1 = null;
		String sql2 = null;
		DBHelper db1 = null;
		DBHelper db2 = null;
		DBHelper db3 = null;

		List<Object> list = new ArrayList<Object>();
		if (fenlei_Tag == 1) {
			sql = "select id,title,name,picture,laiyuan,pinglun_count,dianzan_count,muban_Tag FROM toutiao where title LIKE '%"
					+ str + "%' and shenhe like '%已发布%' LIMIT " + (page - 1) * 10 + "," + 10 * page;
		} else if (fenlei_Tag == 2) {
			sql = "select * FROM faxian where title LIKE '%" + str + "%' and shenhe like '%已发布%' LIMIT "
					+ (page - 1) * 10 + "," + 10 * page;
		} else if (fenlei_Tag == 3) {
			sql = "select * FROM (SELECT id ,title,picture,releaser_id,shenhe,time,guangjie_fenlei_Tag FROM commodity where buyer_id = 'null' AND shenhe LIKE '已发布%'   UNION SELECT id ,title,picture,releaser_id,shenhe,time,guangjie_fenlei_Tag FROM fuwu  where   shenhe LIKE '已发布%' ) where title LIKE '%"
					+ str + "%' LIMIT " + (page - 1) * 10 + "," + 10 * page;
		} else if (fenlei_Tag == 4) {
			sql = "select id,title,picture,releaser_id FROM quchu where title LIKE '%" + str
					+ "%' and shenhe like '%已发布%' LIMIT " + (page - 1) * 10 + "," + 10 * page;
		}

		sql1 = "select id from sousuo where str='" + str + "'";

		ResultSet ret = null;
		ResultSet ret1 = null;
		String _id = null;
		int count = 0;
		String uuid = UUID.randomUUID().toString();
		try {
			db2 = new DBHelper(sql1);
			ret1 = db2.pst.executeQuery();
			System.out.println(ret1);
			while (ret1.next()) {
				_id = ret1.getString(1);
			}
			if (_id == null) {
				sql2 = "INSERT INTO sousuo(id,str,count) VALUES('" + uuid + "','" + str + "'," + count + ")";
				db3 = new DBHelper(sql2);
				boolean ret2 = db3.pst.execute();
			} else {
				sql2 = "UPDATE sousuo set count=count+1 where id='" + _id + "'";
				db3 = new DBHelper(sql2);
				int ret2 = db3.pst.executeUpdate();
			}

			db1 = new DBHelper(sql);

			ret = db1.pst.executeQuery();

			if (fenlei_Tag == 1) {
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
					String laiyuan = ret.getString(5);
					int pinglun_count = FunctionBusiness.getPinglun_size(id);
					int muban_Tag = ret.getInt(8);

					Dianzan_Number dianzan_count = FunctionBusiness.getDianzanNumber(id, 3);
					Toutiao_Shouye toutiao = new Toutiao_Shouye();
					toutiao.setToutiao_id(id);
					toutiao.setPicture(list2);
					toutiao.setTitle(title);
					toutiao.setName(name);
					toutiao.setLaiyuan(laiyuan);
					toutiao.setDianzan_count(dianzan_count);
					toutiao.setPinglun_count(pinglun_count);
					toutiao.setMuban_Tag(muban_Tag);
					toutiao.setGuanggao_Tag(0);
					list.add(toutiao);

				}

			} else if (fenlei_Tag == 2) {
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
			} else if (fenlei_Tag == 3) {

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
					int dianzan_counts = 0;
					if (guangjie_fenlei_Tag == 1) {
						dianzan_counts = FunctionBusiness.getDianzanNumber(id, 6).getDianzan_count();
					} else if (guangjie_fenlei_Tag == 1) {
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

			} else if (fenlei_Tag == 4) {

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

			db1.close();
			db2.close();
			db3.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static void jubao(String reporter_id, String tiaomu_id, int fenlei_Tag, String content) {

		String sql = null;
		String uuid = UUID.randomUUID().toString();
		DBHelper db1 = null;
		sql = "INSERT INTO jubao(id,reporter_id,tiaomu_id,fenlei_Tag,content) VALUES('" + uuid + "','" + reporter_id
				+ "','" + tiaomu_id + "'," + fenlei_Tag + ",'" + content + "')";
		boolean ret;
		db1 = new DBHelper(sql);
		try {
			ret = db1.pst.execute();

			db1.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static List<Dianzan_list> getDianzan_list(String tiaomu_id, String my_id, int page, int dianzan_Tag) {
		List<Dianzan_list> dianzan_lists = new ArrayList<Dianzan_list>();
		String sql = null;
		DBHelper db1 = null;

		sql = "SELECT yonghu_id FROM dianzan_list WHERE tiaomu_id='" + tiaomu_id + "' and dianzan_Tag=" + dianzan_Tag
				+ " limit " + (page - 1) * 12 + "," + 12 * page;
		boolean isguanzhu = false;

		ResultSet ret = null;
		try {
			db1 = new DBHelper(sql);
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				String yonghu_id = ret.getString(1);
				String touxiang = Admin_xinxi_Business.getAdmin_xinxiInfoById(yonghu_id).getTouxiang_picture();
				String qianming = Admin_xinxi_Business.getAdmin_xinxiInfoById(yonghu_id).getQianming();
				String name = Admin_xinxi_Business.getAdmin_xinxiInfoById(yonghu_id).getName();
				int flag = FunctionBusiness.getGuanzhu_Tag(my_id, yonghu_id);
				if (flag == 0) {
					isguanzhu = false;

				} else if (flag != 0) {
					isguanzhu = true;
				}

				Dianzan_list dianzan_list = new Dianzan_list();
				dianzan_list.setYonghu_id(yonghu_id);
				dianzan_list.setIsguanzhu(isguanzhu);
				dianzan_list.setName(name);
				dianzan_list.setQianming(qianming);
				dianzan_list.setTouxiang(touxiang);

				dianzan_lists.add(dianzan_list);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dianzan_lists;

	}

	public static Pinglun addPinglun(String tiaomu_id, String pingluner_id, String content) {

		String sql = null;
		String uuid = UUID.randomUUID().toString();
		DBHelper db1 = null;
		Timestamp time = new Timestamp(new Date().getTime());
		sql = "INSERT INTO pinglun(id,tiaomu_id,pingluner_id,content,time) VALUES('" + uuid + "','" + tiaomu_id + "','"
				+ pingluner_id + "','" + content + "','" + time + "')";
		boolean ret = false;
		db1 = new DBHelper(sql);
		boolean isdianzan = false;
		Pinglun pinglun = new Pinglun();
		try {
			ret = db1.pst.execute();
			String pingluner_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(pingluner_id).getName();
			String pingluner_touxiang = Admin_xinxi_Business.getAdmin_xinxiInfoById(pingluner_id).getTouxiang_picture();
			String pingluner_qianming = Admin_xinxi_Business.getAdmin_xinxiInfoById(pingluner_id).getQianming();
			Dianzan_Number dianzan_count = FunctionBusiness.getDianzanNumber(uuid, 7);

			pinglun.setPinglun_id(uuid);
			pinglun.setPingluner_id(pingluner_id);
			pinglun.setContent(content);
			pinglun.setTime(time);
			pinglun.setDianzan_count(dianzan_count);
			List<Huifu> list=FunctionBusiness.getHuifu(uuid);
			if (list==null) {
				list=new ArrayList<Huifu>();
			
			}
			pinglun.setHuifu(list);

			isdianzan = FunctionBusiness.getDianzan(pingluner_id, uuid, 7);

			pinglun.setIsdianzan(isdianzan);
			pinglun.setPingluner_name(pingluner_name);
			pinglun.setPingluner_qianming(pingluner_qianming);
			pinglun.setPingluner_touxiang(pingluner_touxiang);

			db1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pinglun;

	}

	public static String gettixian(String my_id, double price, String Wechat_name) {

		String sql = null;
		String sql1 = null;
		String sql2 = null;
		String sql3 = null;
		DBHelper db1 = null;
		DBHelper db2 = null;
		DBHelper db3 = null;
		DBHelper db4 = null;
		boolean ret = false;
		int ret1 = 0;
		ResultSet ret2 = null;
		boolean ret3 = false;
		double _price = 0.0;
		String uuid = UUID.randomUUID().toString();
		String str = null;
		sql = "INSERT INTO tixian_list(id,my_id,weichat_name,price) VALUES('" + uuid + "','" + my_id + "','"
				+ Wechat_name + "',round(" + price + "))";

		sql1 = "UPDATE admin_xinxi SET qianbao=ROUND(qianbao-" + price + ") WHERE id='" + my_id + "'";
		sql2 = "SELECT qianbao FROM admin_xinxi WHERE id='" + my_id + "'";

		String uuid2 = UUID.randomUUID().toString();
		Timestamp time = new Timestamp(new Date().getTime());
		sql3 = "INSERT INTO qianbao_list (id,my_id,price,type,time) VALUES('" + uuid2 + "','" + my_id + "','-" + price
				+ "','钱包提现','" + time + "')";

		db3 = new DBHelper(sql2);
		try {
			ret2 = db3.pst.executeQuery();
			while (ret2.next()) {
				_price = ret2.getDouble(1);
			}
			if (_price < price) {
				str = "提现失败，余额不足";
			} else if (_price >= price) {
				db1 = new DBHelper(sql);
				ret = db1.pst.execute();

				db2 = new DBHelper(sql1);
				ret1 = db2.pst.executeUpdate();
				str = "提现成功，请扫描二维码或等待管理员联系";
				db4 = new DBHelper(sql3);
				ret3 = db4.pst.execute();
			}
			db1.close();
			db2.close();
			db3.close();
			db4.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;

	}

	public static int getPinglun_size(String id) {
		String sql = "select COUNT(id) from pinglun WHERE tiaomu_id='" + id + "';";
		DBHelper db1 = null;
		ResultSet ret = null;
		db1 = new DBHelper(sql);
		int count = 0;
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				count = ret.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;

	}

	public static Huifu_list addHuifu(String tiaomu_id, String my_id, String content) {
		String uuid = UUID.randomUUID().toString();
		Timestamp time = new Timestamp(new Date().getTime());
		String sql = "INSERT INTO huifu(id,pinglun_id,huifuer_id,huifu_content,time) VALUES('" + uuid + "','"
				+ tiaomu_id + "','" + my_id + "','" + content + "','" + time + "')";
		DBHelper db1 = null;
		boolean ret = false;
		db1 = new DBHelper(sql);
		int count = 0;
		Huifu_list huifu_list = new Huifu_list();

		try {
			ret = db1.pst.execute();

			Admin_xinxi admin_xinxi = Admin_xinxi_Business.getAdmin_xinxiInfoById(my_id);
			String huifuer_qianming = admin_xinxi.getQianming();
			String huifuer_name = admin_xinxi.getName();
			String huifuer_touxiang = admin_xinxi.getTouxiang_picture();
			int dianzan_count = FunctionBusiness.getDianzanNumber(uuid, 8).getDianzan_count();
			boolean isdianzan = FunctionBusiness.getDianzan(my_id, uuid, 8);

			huifu_list.setHuifu_id(uuid);
			huifu_list.setPinglun_id(tiaomu_id);
			huifu_list.setHuifuer_id(my_id);
			huifu_list.setHuifuer_name(huifuer_name);
			huifu_list.setHuifu_content(content);
			huifu_list.setHuifuer_touxiang(huifuer_touxiang);
			huifu_list.setDianzan_count(dianzan_count);
			huifu_list.setIsdianzan(isdianzan);
			huifu_list.setHuifuer_qianming(huifuer_qianming);
			huifu_list.setTime(time);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return huifu_list;

	}

	public static List<Huifu_list> getHuifu_list(String tiaomu_id, String my_id, int page) {

		String sql = null;
		if (page == 0) {
			sql = "SELECT id,pinglun_id,huifuer_id,huifu_content,time FROM huifu WHERE pinglun_id='" + tiaomu_id
					+ "' order by time desc";
		} else {

			sql = "SELECT id,pinglun_id,huifuer_id,huifu_content,time FROM huifu WHERE pinglun_id='" + tiaomu_id
					+ "' order by time desc limit " + (page - 1) * 12 + "," + 12 * page;
		}
		DBHelper db1 = null;
		ResultSet ret = null;
		db1 = new DBHelper(sql);
		List<Huifu_list> huifus = new ArrayList<Huifu_list>();

		try {

			ret = db1.pst.executeQuery();

			while (ret.next()) {

				String huifu_id = ret.getString(1);
				String pinglun_id = ret.getString(2);
				String huifuer_id = ret.getString(3);
				String huifu_content = ret.getString(4);
				Timestamp time = ret.getTimestamp(5);
				Admin_xinxi admin_xinxi = Admin_xinxi_Business.getAdmin_xinxiInfoById(huifuer_id);
				String huifuer_qianming = admin_xinxi.getQianming();
				String huifuer_name = admin_xinxi.getName();
				String huifuer_touxiang = admin_xinxi.getTouxiang_picture();
				int dianzan_count = FunctionBusiness.getDianzanNumber(huifu_id, 8).getDianzan_count();
				boolean isdianzan = FunctionBusiness.getDianzan(my_id, huifu_id, 8);

				Huifu_list huifu_list = new Huifu_list();
				huifu_list.setHuifu_id(huifu_id);
				huifu_list.setPinglun_id(pinglun_id);
				huifu_list.setHuifuer_id(huifuer_id);
				huifu_list.setHuifuer_name(huifuer_name);
				huifu_list.setHuifu_content(huifu_content);
				huifu_list.setHuifuer_touxiang(huifuer_touxiang);
				huifu_list.setDianzan_count(dianzan_count);
				huifu_list.setIsdianzan(isdianzan);
				huifu_list.setHuifuer_qianming(huifuer_qianming);
				huifu_list.setTime(time);

				huifus.add(huifu_list);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return huifus;

	}

	public static String Delect_Zuji(String tiaomu_id) {
		String sql = null;
		DBHelper db1 = null;
		sql = "DELETE FROM zuji WHERE id =  '" + tiaomu_id + "'";
		db1 = new DBHelper(sql);
		try {
			boolean ret = db1.pst.execute();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "删除成功";
	}

	public static String Delect_shoucang(String tiaomu_id) {
		String sql = null;
		DBHelper db1 = null;
		sql = "DELETE FROM shoucang WHERE id =  '" + tiaomu_id + "'";
		db1 = new DBHelper(sql);
		try {
			boolean ret = db1.pst.execute();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "删除成功";
	}

	public static String setfangke(String my_id, String yonghu_id) {
		String sql = null;
		DBHelper db1 = null;
		String uuid = UUID.randomUUID().toString();
		Timestamp time = new Timestamp(new Date().getTime());
		sql = "INSERT INTO fangke(id,my_id,fangke_id,time) VALUES('" + uuid + "','" + yonghu_id + "','" + my_id + "','"
				+ time + "')";
		db1 = new DBHelper(sql);
		try {
			boolean ret = db1.pst.execute();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "操作成功";
	}

	public static List<Guanzhu> getfangke(String my_id, int page) {
		String sql = null;
		DBHelper db1 = null;
		String uuid = UUID.randomUUID().toString();
		sql = "select fangke_id,time,id from fangke where my_id='" + my_id + "' limit " + (page - 1) * 12 + ","
				+ 12 * page;
		db1 = new DBHelper(sql);

		List<Guanzhu> fangke_lists = new ArrayList<Guanzhu>();

		try {
			ResultSet ret = db1.pst.executeQuery();

			while (ret.next()) {

				String fangke_id = ret.getString(1);
				Admin_xinxi admin_xinxi = Admin_xinxi_Business.getAdmin_xinxiInfoById(fangke_id);
				String name = admin_xinxi.getName();
				String touxiang = admin_xinxi.getTouxiang_picture();
				Timestamp time = ret.getTimestamp(2);
				String id = ret.getString(3);

				Guanzhu guanzhu = new Guanzhu();
				guanzhu.setMy_id(my_id);
				guanzhu.setFriend_id(fangke_id);
				guanzhu.setName(name);
				guanzhu.setTouxiang(touxiang);
				guanzhu.setTime(time);
				guanzhu.setTiaomu_id(id);
				fangke_lists.add(guanzhu);

			}
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return fangke_lists;
	}

	public static boolean Delecte_fangke(String fangke_id) {
		String sql = null;
		DBHelper db1 = null;
		String uuid = UUID.randomUUID().toString();
		sql = "delete from fangke where id='" + fangke_id + "'";
		db1 = new DBHelper(sql);

		List<Guanzhu> fangke_lists = new ArrayList<Guanzhu>();
		boolean ret = false;
		try {
			ret = db1.pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return ret;
	}

	public static String add_heimingdan(String my_id, String yonghu_id) {
		String uuid = UUID.randomUUID().toString();
		String sql = "INSERT INTO heimingdan(id,my_id,yonghu_id) VALUES ('" + uuid + "','" + my_id + "','" + yonghu_id
				+ "') ";

		DBHelper db1 = null;
		boolean ret = false;
		try {
			db1 = new DBHelper(sql);
			ret = db1.pst.execute();

			db1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "加入黑名单成功";

	}

	public static String delect_heimingdan(String my_id, String yonghu_id) {

		String sql = "DELETE FROM heimingdan WHERE yonghu_id='" + yonghu_id + "' AND my_id='" + my_id + "'";

		DBHelper db1 = null;
		boolean ret = false;
		try {
			db1 = new DBHelper(sql);
			ret = db1.pst.execute();

			db1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "加入黑名单成功";

	}

	public static String delect_MyReleaser(String tiaomu_id, int fenlei_Tag, int guangjie_fenlei_Tag) {
		String sql = null;
		if (fenlei_Tag == 1) {
			sql = "DELETE FROM toutiao WHERE id='" + tiaomu_id + "'";
		} else if (fenlei_Tag == 2) {
			sql = "DELETE FROM faxian WHERE id='" + tiaomu_id + "'";
		} else if (fenlei_Tag == 4) {
			sql = "DELETE FROM quchu WHERE id='" + tiaomu_id + "'";
		} else if (fenlei_Tag == 3) {
			if (guangjie_fenlei_Tag == 1) {
				sql = "DELETE FROM commodity WHERE id='" + tiaomu_id + "'";
			} else if (guangjie_fenlei_Tag == 2) {
				sql = "DELETE FROM fuwu WHERE id='" + tiaomu_id + "'";
			}
		}

		DBHelper db1 = null;
		boolean ret = false;
		try {
			db1 = new DBHelper(sql);
			ret = db1.pst.execute();

			db1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "删除成功";

	}

	public static Tuikuan gettuikuan() {

		String sql = "SELECT id,transaction_id,content,releaser_time,end_time FROM tuikuan";

		DBHelper db1 = null;
		ResultSet ret = null;
		db1 = new DBHelper(sql);
		Tuikuan tuikuan = new Tuikuan();
		try {

			ret = db1.pst.executeQuery();
			String id = ret.getString(1);
			String transaction_id = ret.getString(2);
			String content = ret.getString(3);
			Timestamp releaser_time = ret.getTimestamp(4);
			Timestamp end_time = ret.getTimestamp(5);

			tuikuan.setTuikuan_id(id);
			tuikuan.setTransaction_id(transaction_id);
			tuikuan.setContent(content);
			tuikuan.setReleaser_time(releaser_time);
			tuikuan.setEnd_time(end_time);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tuikuan;

	}

	public static Tuikuan addtuikuan() {

		String sql = "SELECT id,transaction_id,content,releaser_time,end_time FROM tuikuan";

		DBHelper db1 = null;
		ResultSet ret = null;
		db1 = new DBHelper(sql);
		Tuikuan tuikuan = new Tuikuan();
		try {

			ret = db1.pst.executeQuery();
			String id = ret.getString(1);
			String transaction_id = ret.getString(2);
			String content = ret.getString(3);
			Timestamp releaser_time = ret.getTimestamp(4);
			Timestamp end_time = ret.getTimestamp(5);

			tuikuan.setTuikuan_id(id);
			tuikuan.setTransaction_id(transaction_id);
			tuikuan.setContent(content);
			tuikuan.setReleaser_time(releaser_time);
			tuikuan.setEnd_time(end_time);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tuikuan;

	}

	public static String delect_zuji_All(String my_id, int delect_Tag, int guangjie_fenlei_Tag) {
		String sql = null;

		if (delect_Tag == 1) {
			sql = "DELETE FROM zuji WHERE my_id='" + my_id + "' and toutiao_id is not null";
		} else if (delect_Tag == 2) {
			sql = "DELETE FROM zuji WHERE my_id='" + my_id + "' and faxian_id is not null";
		} else if (delect_Tag == 4) {
			sql = "DELETE FROM zuji WHERE my_id='" + my_id + "' and quchu_id is not null";
		} else if (delect_Tag == 3) {
			sql = "DELETE FROM zuji WHERE my_id='" + my_id + "' and commodity_id is not null or fuwu_id is not null";

		}
		DBHelper db1 = null;
		try {
			db1 = new DBHelper(sql);
			boolean ret = db1.pst.execute();
			db1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "删除成功";

	}

	public static String delect_shoucang_All(String my_id, int delect_Tag, int guangjie_fenlei_Tag) {
		String sql = null;

		if (delect_Tag == 1) {
			sql = "DELETE FROM shoucang WHERE my_id='" + my_id + "' and toutiao_id is not null";
		} else if (delect_Tag == 2) {
			sql = "DELETE FROM shoucang WHERE my_id='" + my_id + "' and faxian_id is not null";
		} else if (delect_Tag == 4) {
			sql = "DELETE FROM shoucang WHERE my_id='" + my_id + "' and quchu_id is not null";
		} else if (delect_Tag == 3) {

			sql = "DELETE FROM shoucang WHERE my_id='" + my_id + "' and commodity_id is not null ";

		}
		DBHelper db1 = null;
		boolean ret = false;
		try {
			db1 = new DBHelper(sql);
			ret = db1.pst.execute();

			db1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "删除成功";

	}

	public static List<Heimingdan> getHeimingdan(String my_id, int page) {

		String sql = null;
		sql = "select yonghu_id from heimingdan where my_id='" + my_id + "' limit " + (page - 1) * 12 + "," + 12 * page;
		DBHelper db1 = null;
		db1 = new DBHelper(sql);
		List<Heimingdan> heimingdans = new ArrayList<Heimingdan>();
		ResultSet ret = null;
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {

				String yonghu_id = ret.getString(1);
				Admin_xinxi admin_xinxi = Admin_xinxi_Business.getAdmin_xinxiInfoById(yonghu_id);

				String yonghu_name = admin_xinxi.getName();
				String yonghu_touxiang = admin_xinxi.getTouxiang_picture();
				String yonghu_qianming = admin_xinxi.getQianming();

				Heimingdan heimingdan = new Heimingdan();
				heimingdan.setYongyu_id(yonghu_id);
				heimingdan.setName(yonghu_name);
				heimingdan.setQianming(yonghu_qianming);
				heimingdan.setTouxiang(yonghu_touxiang);

				heimingdans.add(heimingdan);

			}
			db1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return heimingdans;

	}

	public static List<Remen_Sousuo> getstr() {

		String sql = "select str from sousuo order by count desc limit 3";
		DBHelper db1 = null;
		ResultSet ret = null;
		db1 = new DBHelper(sql);
		List<Remen_Sousuo> list = new ArrayList<Remen_Sousuo>();
		int num = 0;
		try {
			ret = db1.pst.executeQuery();

			while (ret.next()) {
				num++;
				String str = ret.getString(1);

				Remen_Sousuo remen_Sousuo = new Remen_Sousuo();
				remen_Sousuo.setStr(str);
				remen_Sousuo.setNumber(num);
				list.add(remen_Sousuo);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	public static Admin_xinxi getadmin_xinxi(String my_id) {

		String sql = " select touxiang_picture,name,weichat,sex,qianming from admin_xinxi  where id='" + my_id + "' ";
		DBHelper db1 = null;
		ResultSet ret = null;
		db1 = new DBHelper(sql);
		Admin_xinxi admin_xinxi = new Admin_xinxi();
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				String touxiang = ret.getString(1);

				String name = ret.getString(2);
				String weichat = ret.getString(3);
				String sex = ret.getString(4);
				String qianming = ret.getString(5);
				admin_xinxi.setName(name);
				admin_xinxi.setTouxiang_picture(touxiang);
				admin_xinxi.setWeichat(weichat);
				admin_xinxi.setSex(sex);
				admin_xinxi.setQianming(qianming);
			}

			db1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return admin_xinxi;

	}

	public static boolean setadmin_xinxi(String my_id, String name, String touxiang_picture, String weichat, String sex,
			String qianming) {
		String sql = null;

		if (touxiang_picture != null) {
			sql = " update admin_xinxi set touxiang_picture='" + touxiang_picture + "',name='" + name + "',weichat='"
					+ weichat + "',sex='" + sex + "',qianming='" + qianming + "' where id='" + my_id + "' ";
		} else {
			sql = " update admin_xinxi set name='" + name + "',weichat='" + weichat + "',sex='" + sex + "',qianming='"
					+ qianming + "' where id='" + my_id + "' ";

		}

		DBHelper db1 = null;
		boolean ret = false;
		db1 = new DBHelper(sql);
		try {
			ret = db1.pst.execute();
			db1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;

	}

	public static String getphone(String my_id) {

		String sql = " select phone from admin_xinxi  where id='" + my_id + "' ";
		DBHelper db1 = null;
		ResultSet ret = null;
		db1 = new DBHelper(sql);
		String phone = null;
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				phone = ret.getString(1);

			}
			db1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return phone;

	}

	public static int setLogin_xinxi(String phone, String password) {

		String sql = " update login set password='" + password + "' where phone='" + phone + "' ";
		DBHelper db1 = null;
		int ret = 0;
		db1 = new DBHelper(sql);
		try {
			ret = db1.pst.executeUpdate();
			db1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;

	}

	public static Admin_xinxi getAddress(String my_id) {

		String sql = " select shouhuo_address,shouhuo_name,phone from admin_xinxi  where id='" + my_id + "' ";
		DBHelper db1 = null;
		ResultSet ret = null;
		Admin_xinxi admin_xinxi = new Admin_xinxi();
		db1 = new DBHelper(sql);
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				String address = ret.getString(1);
				String name = ret.getString(2);
				String phone = ret.getString(3);

				admin_xinxi.setShouhuo_name(name);
				admin_xinxi.setShouhuo_address(address);
				admin_xinxi.setPhone(phone);

			}
			db1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return admin_xinxi;

	}

	public static List<Guanzhu> gettuijian_User(String my_id, int page) {

		String sql = " select tuijian_user_id from tuijian_user limit " + (page - 1) * 12 + "," + 12 * page;
		DBHelper db1 = null;
		ResultSet ret = null;
		Admin_xinxi admin_xinxi = null;
		List<Guanzhu> list = new ArrayList<Guanzhu>();

		db1 = new DBHelper(sql);
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {

				String tuijian_user_id = ret.getString(1);
				admin_xinxi = Admin_xinxi_Business.getAdmin_xinxiInfoById(tuijian_user_id);
				String touxiang = admin_xinxi.getTouxiang_picture();
				String name = admin_xinxi.getName();
				String qianming = admin_xinxi.getQianming();
				boolean isguanzhu = GuanzhuBusiness.getIsGuanzhu(my_id, tuijian_user_id);

				Guanzhu guanzhu = new Guanzhu();
				guanzhu.setFriend_id(tuijian_user_id);
				guanzhu.setIsguanzhu(isguanzhu);
				guanzhu.setName(name);
				guanzhu.setQianming(qianming);
				guanzhu.setTouxiang(touxiang);
				guanzhu.setTime(new Timestamp(0));

				list.add(guanzhu);

			}
			db1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db1.close();
		}
		return list;

	}

	public static String setAddress(String my_id, String _phone, String _address, String _name) {

		String sql = " update  admin_xinxi set shouhuo_address='" + _address + "',shouhuo_name='" + _name + "',phone='"
				+ _phone + "' where id='" + my_id + "' ";
		DBHelper db1 = null;
		boolean ret = false;
		Admin_xinxi admin_xinxi = new Admin_xinxi();
		db1 = new DBHelper(sql);
		try {
			ret = db1.pst.execute();

			db1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "修改成功";

	}

	public static Apk_model getApk() {

		String sql = "select id,version,description,download_url from Apk order by time desc limit 1";
		DBHelper db1 = null;
		ResultSet ret = null;
		db1 = new DBHelper(sql);
		Apk_model apk_model = new Apk_model();
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				String id = ret.getString(1);
				String version = ret.getString(2);
				String description = ret.getString(3);
				String download_url = ret.getString(4);

				apk_model.setUid(id);
				apk_model.setVersion(version);
				apk_model.setDescription(description);
				apk_model.setDownload_url(download_url);
			}
			db1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return apk_model;

	}

	public static void addmessage(String my_id, String releaser_id, String tiaomu_id, String message, String content) {

		String uuid = UUID.randomUUID().toString();
		Timestamp time = new Timestamp(new Date().getTime());

		String sql = "insert into message(id,my_id,releaser_id,tiaomu_id,message,time,content) values('" + uuid + "','"
				+ my_id + "','" + releaser_id + "','" + tiaomu_id + "','" + message + "','" + time + "','" + content
				+ "')";
		DBHelper db1 = null;
		db1 = new DBHelper(sql);
		try {
			boolean ret = db1.pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db1.close();
		}

	}

	public static List<Message> getmessage(String my_id) {

		String sql = "select releaser_id ,tiaomu_id,message,time,content from message where my_id='" + my_id + "'";
		DBHelper db1 = null;
		db1 = new DBHelper(sql);
		List<Message> list = new ArrayList<Message>();
		try {
			ResultSet ret = db1.pst.executeQuery();
			while (ret.next()) {
				String releaser_id = ret.getString(1);
				Admin_xinxi admin_xinxi = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id);
				String releaser_name = admin_xinxi.getName();
				String releaser_touxiang = admin_xinxi.getTouxiang_picture();
				String qianming=admin_xinxi.getQianming();
				String tiaomu_id = ret.getString(2);
				String message = ret.getString(3);
				Timestamp time = ret.getTimestamp(4);
				String content = ret.getString(5);

				Message message2 = new Message();
				message2.setReleaser_id(releaser_id);
				message2.setReleaser_name(releaser_name);
				message2.setTime(time);
				message2.setReleaser_touxiang(releaser_touxiang);
				message2.setTiaomu_id(tiaomu_id);
				message2.setMessage(message);
				message2.setContent(content);
				message2.setReleaser_qianming(qianming);
				list.add(message2);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db1.close();
		}
		return list;

	}

	public static String weichat_name(String my_id) {
		String sql = "select weichat from admin_xinxi where id='" + my_id + "'";
		DBHelper db1 = null;
		db1 = new DBHelper(sql);
		String weichat = null;
		try {
			ResultSet ret = db1.pst.executeQuery();
			while (ret.next()) {

				weichat = ret.getString(1);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db1.close();
		}
		return weichat;
	}

}
