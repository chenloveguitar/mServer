package com.magicmoble.luzhouapp.business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.magicmoble.luzhouapp.action.fabu.Guangjie_clickInq;
import com.magicmoble.luzhouapp.action.weixin_pay.Str;
import com.magicmoble.luzhouapp.model.Admin_xinxi;
import com.magicmoble.luzhouapp.model.Guangjie_Xiangqing;
import com.magicmoble.luzhouapp.model.Order;
import com.magicmoble.luzhouapp.model.Picture;

public class Dingdan_Business {

	public static String addDingdan(String my_id, String tiaomu_id, int shuliang, String buyer_id, String buyer_name,
			String buyer_phone, String address, String beizhu, int guangjie_fenlei_Tag) {

		String uuid = UUID.randomUUID().toString();
		Guangjie_Xiangqing guangjie_Xiangqing = null;
		int buyer_zhuangtai = 1;
		int seller_zhuangtai = 1;
		if (guangjie_fenlei_Tag == 1) {
			guangjie_Xiangqing = CommodityBusiness.getGuangjie_CommodityById(tiaomu_id, my_id, guangjie_fenlei_Tag);
		} else if (guangjie_fenlei_Tag == 2) {
			guangjie_Xiangqing = CommodityBusiness.getGuangjie_CommodityById(tiaomu_id, my_id, guangjie_fenlei_Tag);
		}

		double total_price = 0.0;

		total_price = guangjie_Xiangqing.getPrice() * shuliang + guangjie_Xiangqing.getFreight();

		int r1 = (int) (Math.random() * (10));// 产生2个0-9的随机数
		int r2 = (int) (Math.random() * (10));
		long now = System.currentTimeMillis();// 一个13位的时间戳
		String paymentID = String.valueOf(r1) + String.valueOf(r2) + String.valueOf(now);// 订单ID
		String transaction_id = paymentID;

		Timestamp time = new Timestamp(new Date().getTime());
		Timestamp pay_end_time = new Timestamp(time.getTime() + (1 * 60 * 60 * 1000));

		String sql = "INSERT INTO dingdan(id,transaction_id,buyer_zhuangtai,"
				+ "price,time,tiaomu_id,shuliang,freight,buyer_id,buyer_name,buyer_phone,address,beizhu"
				+ ",total_price,seller_id,seller_zhuangtai,guangjie_fenlei_Tag,pay_time,pay_end_time) VALUES('" + uuid
				+ "','" + transaction_id + "','" + buyer_zhuangtai + "','" + guangjie_Xiangqing.getPrice() + "','"
				+ time + "','" + tiaomu_id + "','" + shuliang + "','" + guangjie_Xiangqing.getFreight() + "','"
				+ buyer_id + "','" + buyer_name + "','" + buyer_phone + "','" + address + "','" + beizhu + "','"
				+ total_price + "','" + guangjie_Xiangqing.getSeller_id() + "','" + seller_zhuangtai + "','"
				+ guangjie_fenlei_Tag + "','" + time + "','" + pay_end_time + "')";
		DBHelper db1 = null;

		boolean ret = false;
		try {
			db1 = new DBHelper(sql);
			ret = db1.pst.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db1.close();
		return transaction_id;
	}

	public static Order order_click(String _transaction_id, String my_id, int guangjie_fenlei_Tag) {

		String sql = "SELECT transaction_id,price,time,tiaomu_id,shuliang,total_price,freight,seller_id,buyer_id,buyer_name,buyer_phone,address,beizhu,tuikuan_message,buyer_zhuangtai,seller_zhuangtai,pay_time,pay_end_time,jiedan_time,jiedan_end_time,express_time,express_end_time,pingjia_time,pingjia_end_time,complain_time,complain_end_time,fahuo_time,fahuo_end_time FROM dingdan WHERE transaction_id='"
				+ _transaction_id + "'";
		DBHelper db1 = null;
		ResultSet ret = null;
		Order order = new Order();

		try {
			db1 = new DBHelper(sql);
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				String transaction_id = ret.getString(1);
				double price = ret.getDouble(2);
				Timestamp time = ret.getTimestamp(3);
				String tiaomu_id = ret.getString(4);
				Guangjie_Xiangqing guangjie_Xiangqing = CommodityBusiness.getGuangjie_CommodityById(tiaomu_id, my_id,
						guangjie_fenlei_Tag);
				List<Picture> commodity_picture = guangjie_Xiangqing.getPicture();
				if (commodity_picture == null) {
					commodity_picture = new ArrayList<Picture>();
					commodity_picture.add(new Picture());
				}
				String title = guangjie_Xiangqing.getTitle();
				int shuliang = ret.getInt(5);
				double total_price = ret.getDouble(6);
				double freight = ret.getDouble(7);
				String seller_id = ret.getString(8);
				Admin_xinxi seller_admin_xinxi = Admin_xinxi_Business.getAdmin_xinxiInfoById(seller_id);
				String seller_name = seller_admin_xinxi.getName();
				String seller_touxiang = seller_admin_xinxi.getTouxiang_picture();
				String seller_qianming = seller_admin_xinxi.getQianming();
				String buyer_id = ret.getString(9);
				Admin_xinxi buyer_admin_xinxi = Admin_xinxi_Business.getAdmin_xinxiInfoById(buyer_id);
				String buyer_name = buyer_admin_xinxi.getName();
				String buyer_touxiang = buyer_admin_xinxi.getTouxiang_picture();
				String buyer_qianming = buyer_admin_xinxi.getQianming();
				String name = ret.getString(10);
				String phone = ret.getString(11);
				String address = ret.getString(12);
				String beizhu = ret.getString(13);
				String tuikuan_message = ret.getString(14);
				int buyer_zhuangtai = ret.getInt(15);
				int seller_zhuangtai = ret.getInt(16);
				Timestamp pay_time = ret.getTimestamp(17);
				Timestamp pay_end_time = ret.getTimestamp(18);
				Timestamp jiedan_time = ret.getTimestamp(19);
				Timestamp jiedan_end_time = ret.getTimestamp(20);
				Timestamp express_time = ret.getTimestamp(21);
				Timestamp express_end_time = ret.getTimestamp(22);
				Timestamp pingjia_time = ret.getTimestamp(23);
				Timestamp pingjia_end_time = ret.getTimestamp(24);
				Timestamp complain_time = ret.getTimestamp(25);
				Timestamp complain_end_time = ret.getTimestamp(26);
				Timestamp fahuo_time = ret.getTimestamp(27);
				Timestamp fahuo_end_time = ret.getTimestamp(28);

				Timestamp Surplus_time = null;

				String seller_zhuangtai_message = null;
				if (seller_zhuangtai == 1) {
					seller_zhuangtai_message = "待付款";
					Surplus_time = new Timestamp(pay_end_time.getTime() - pay_time.getTime());
				} else if (seller_zhuangtai == 2) {
					seller_zhuangtai_message = "待接单";
					Surplus_time = new Timestamp(jiedan_end_time.getTime() - jiedan_time.getTime());
				} else if (seller_zhuangtai == 3) {
					seller_zhuangtai_message = "进行中";
					Surplus_time = new Timestamp(fahuo_end_time.getTime() - fahuo_time.getTime());
				} else if (seller_zhuangtai == 4) {
					seller_zhuangtai_message = "待完成";
					Surplus_time = new Timestamp(express_end_time.getTime() - express_time.getTime());
				} else if (seller_zhuangtai == 6) {
					seller_zhuangtai_message = "有投诉";
					Surplus_time = new Timestamp(complain_end_time.getTime() - complain_time.getTime());
				} else if (seller_zhuangtai == 5) {
					seller_zhuangtai_message = "已完成";
					Surplus_time = new Timestamp(pingjia_end_time.getTime() - pingjia_time.getTime());
				} else if (seller_zhuangtai == 7) {
					seller_zhuangtai_message = "订单关闭";
				}

				String buyer_zhuangtai_message = null;
				if (buyer_zhuangtai == 1) {
					buyer_zhuangtai_message = "未付款" + price + "元";
					Surplus_time = new Timestamp(pay_end_time.getTime() - pay_time.getTime());
				} else if (buyer_zhuangtai == 2) {
					buyer_zhuangtai_message = "待接单";
					Surplus_time = new Timestamp(jiedan_end_time.getTime() - jiedan_time.getTime());
				} else if (buyer_zhuangtai == 3) {
					buyer_zhuangtai_message = "进行中";
					Surplus_time = new Timestamp(fahuo_end_time.getTime() - fahuo_time.getTime());
				} else if (buyer_zhuangtai == 4) {
					buyer_zhuangtai_message = "待完成";
					Surplus_time = new Timestamp(express_end_time.getTime() - express_time.getTime());
				} else if (buyer_zhuangtai == 6) {
					buyer_zhuangtai_message = "投诉中";
					Surplus_time = new Timestamp(complain_end_time.getTime() - complain_time.getTime());
				} else if (buyer_zhuangtai == 5) {
					buyer_zhuangtai_message = "已完成";
					Surplus_time = new Timestamp(pingjia_end_time.getTime() - pingjia_time.getTime());
				} else if (buyer_zhuangtai == 7) {
					buyer_zhuangtai_message = "订单关闭";
				}

				order.setTransaction_id(transaction_id);
				order.setTime(time);
				order.setTitle(title);
				order.setPictures(commodity_picture);
				order.setPrice(price);
				order.setShuliang(shuliang);
				order.setFreight(freight);
				order.setBuyer_name(buyer_name);
				order.setBuyer_touxiang(buyer_touxiang);
				order.setBuyer_qianming(buyer_qianming);
				order.setBuyer_zhuangtai_message(buyer_zhuangtai_message);
				order.setSeller_name(seller_name);
				order.setSeller_qianming(seller_qianming);
				order.setSeller_touxiang(seller_touxiang);
				order.setName(name);
				order.setPhone(phone);
				order.setAddress(address);
				order.setBeizhu(beizhu);
				order.setTotal_price(total_price);
				order.setTuikuan_message(tuikuan_message);
				order.setBuyer_zhuangtai_Tag(buyer_zhuangtai);
				order.setSeller_zhuangtai_Tag(seller_zhuangtai);
				order.setSeller_zhuangtai_message(seller_zhuangtai_message);
				order.setSurplus_time(Surplus_time);
				if (pay_time == null) {
					order.setPay_time(new Timestamp(0));
				} else {
					order.setPay_time(pay_time);
				}
				if (pay_end_time == null) {
					order.setPay_end_time(new Timestamp(0));
				} else {
					order.setPay_end_time(pay_end_time);
				}

				if (fahuo_end_time == null) {
					order.setFahuo_end_time(new Timestamp(0));
				} else {
					order.setFahuo_end_time(fahuo_end_time);
				}
				if (fahuo_time == null) {
					order.setFahuo_time(new Timestamp(0));
				} else {
					order.setFahuo_time(fahuo_time);
				}
				if (jiedan_time == null) {
					order.setJiedan_time(new Timestamp(0));
				} else {
					order.setJiedan_time(jiedan_time);
				}
				if (jiedan_end_time == null) {
					order.setJiedan_end_time(new Timestamp(0));
				} else {
					order.setJiedan_end_time(jiedan_end_time);
				}
				if (express_time == null) {
					order.setExpress_time(new Timestamp(0));
				} else {
					order.setExpress_time(express_time);
				}
				if (express_end_time == null) {
					order.setExpress_end_time(new Timestamp(0));
				} else {
					order.setExpress_end_time(express_end_time);
				}
				if (pingjia_time == null) {
					order.setPingjia_time(new Timestamp(0));
				} else {
					order.setPingjia_time(pingjia_time);
				}
				if (pingjia_end_time == null) {
					order.setPingjia_end_time(new Timestamp(0));
				} else {
					order.setPingjia_end_time(pingjia_end_time);
				}
				if (complain_time == null) {
					order.setComplain_time(new Timestamp(0));
				} else {
					order.setComplain_time(complain_time);
				}
				if (complain_end_time == null) {
					order.setComplain_end_time(new Timestamp(0));
				} else {
					order.setComplain_end_time(complain_end_time);
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;

	}

	public static String cancel(String transaction_id) {

		String sql = " update  dingdan set buyer_zhuangtai=7 , seller_zhuangtai=7 where transaction_id='"
				+ transaction_id + "'";
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
		return "修改成功";

	}

	public static String payment(String transaction_id, double price, String my_id) {

		String uuid = UUID.randomUUID().toString();
		Timestamp time = new Timestamp(new Date().getTime());
		Timestamp end_time = new Timestamp(time.getTime() + 1 * 60 * 60 * 1000);

		String buy_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(my_id).getName();
		Timestamp timestamp = new Timestamp(new Date().getTime());
		String sql = " update dingdan set buyer_zhuangtai=2 , seller_zhuangtai=2 , jiedan_time='" + time
				+ "' , jiedan_end_time='" + end_time + "' where transaction_id='" + transaction_id + "'";
		String sql1 = "update admin_xinxi set qianbao=round(qianbao+" + price + ",2) where id='10086' ";
		String sql2 = "INSERT INTO qianbao_list(id,my_id,price,type,buy_name,time) VALUES('" + uuid + "','" + my_id
				+ "','-" + price + "','购买商品','" + buy_name + "','" + time + "')";

		DBHelper db1 = null;
		DBHelper db2 = null;
		DBHelper db3 = null;
		int ret = 0;
		int ret2 = 0;
		boolean ret3 = false;
		db1 = new DBHelper(sql);
		db2 = new DBHelper(sql1);
		db3 = new DBHelper(sql2);
		try {
			ret = db1.pst.executeUpdate();
			ret2 = db2.pst.executeUpdate();
			ret3 = db3.pst.execute();

			db1.close();
			db2.close();
			db3.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "支付成功";

	}

	public static void addPaylist(String yonghu_id, String transaction_id) {

		String uuid = UUID.randomUUID().toString();

		String sql = "INSERT INTO pay_list(id,yonghu_id,transaction_id) VALUES ('" + uuid + "','" + yonghu_id + "','"
				+ transaction_id + "') ";

		DBHelper db1 = null;
		boolean ret = false;

		try {
			db1 = new DBHelper(sql);
			ret = db1.pst.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db1.close();

		}

	}

	public static void setNotify(String message, String transaction_id) {
		String sql = " update pay_list set message='" + message + "' where transaction_id='" + transaction_id + "'";
		DBHelper db1 = null;
		int ret = 0;
		db1 = new DBHelper(sql);
		try {
			ret = db1.pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db1.close();
		}
	}

	public static String getNotify(String transaction_id) {
		String sql = " select message from pay_list where transaction_id='" + transaction_id + "'";
		DBHelper db1 = null;
		ResultSet ret = null;
		String message = null;
		db1 = new DBHelper(sql);
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				message = ret.getString("1");

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db1.close();
		}
		return message;
	}

	public static void Reprice(double newPrice, String transaction_id) {

		String sql = "update dingdan set total_price='" + newPrice + "' where transaction_id='" + transaction_id + "'";
		DBHelper db1 = null;
		int ret = 0;
		try {
			db1 = new DBHelper(sql);
			ret = db1.pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db1.close();
		}

	}

	public static double gettotal_price(String transaction_id) {

		String sql = "select total_price from dingdan where transaction_id='" + transaction_id + "'";
		DBHelper db1 = null;
		ResultSet ret = null;
		double total_price = 0.0;
		try {
			db1 = new DBHelper(sql);
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				total_price = ret.getDouble(1);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db1.close();
		}

		return total_price;

	}

	public static void jiedan(String transaction_id) {

		Timestamp time = new Timestamp(new Date().getTime());
		Timestamp end_time = new Timestamp(time.getTime() + 7 * 60 * 60 * 1000);

		String sql = "update dingdan set buyer_zhuangtai=3 , seller_zhuangtai=3 , fahuo_time='" + time
				+ "' , fahuo_end_time='" + end_time + "' where transaction_id='" + transaction_id + "'";
		DBHelper db1 = null;
		int ret = 0;
		try {
			db1 = new DBHelper(sql);
			ret = db1.pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db1.close();
		}

	}

	public static void seller_finish(String transaction_id) {
		Timestamp time = new Timestamp(new Date().getTime());
		Timestamp end_time = new Timestamp(time.getTime() + 7 * 60 * 60 * 1000);
		String sql = "update dingdan set buyer_zhuangtai=4 , seller_zhuangtai=4 ,express_time='" + time
				+ "' , express_end_time='" + end_time + "' where transaction_id='" + transaction_id + "'";
		DBHelper db1 = null;
		int ret = 0;
		try {
			db1 = new DBHelper(sql);
			ret = db1.pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db1.close();
		}

	}

	public static void buyer_finish(String transaction_id) {

		Timestamp time = new Timestamp(new Date().getTime());
		Timestamp end_time = new Timestamp(time.getTime() + 7 * 60 * 60 * 1000);
		String sql = "update dingdan set buyer_zhuangtai=5 , seller_zhuangtai=5 , pingjia_time='" + time
				+ "' , pingjia_end_time='" + end_time + "' where transaction_id='" + transaction_id + "'";
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

	public static void Complaint(String transaction_id, String Complaint_message) {
		Timestamp time = new Timestamp(new Date().getTime());
		Timestamp end_time = new Timestamp(time.getTime() + 2 * 60 * 60 * 1000);

		String sql = "update dingdan set buyer_zhuangtai=6 , seller_zhuangtai=6 , tuikuan_message='" + Complaint_message
				+ "' , complain_time='" + time + "' , complain_end_time='" + end_time + "' where transaction_id='"
				+ transaction_id + "'";
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

	public static void delete_dingdan(String transaction_id) {

		String sql = "delete from dingdan where transaction_id='" + transaction_id + "'";
		DBHelper db1 = null;
		boolean ret = false;
		try {
			db1 = new DBHelper(sql);
			ret = db1.pst.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db1.close();
		}

	}

}
