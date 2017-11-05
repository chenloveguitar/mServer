package com.magicmoble.luzhouapp.business;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.swing.text.TabableView;

import org.apache.struts2.components.Debug;
import org.springframework.jdbc.core.metadata.Db2CallMetaDataProvider;

import com.magicmoble.luzhouapp.model.Biaoqian;
import com.magicmoble.luzhouapp.model.ContentModel;
import com.magicmoble.luzhouapp.model.Dashang;
import com.magicmoble.luzhouapp.model.Dashang_list;
import com.magicmoble.luzhouapp.model.Dashang_user;
import com.magicmoble.luzhouapp.model.Dianzan_Number;
import com.magicmoble.luzhouapp.model.Faxian;
import com.magicmoble.luzhouapp.model.Fuwu;
import com.magicmoble.luzhouapp.model.Guangjie_Xiangqing;
import com.magicmoble.luzhouapp.model.Hongbao;
import com.magicmoble.luzhouapp.model.Picture;
import com.magicmoble.luzhouapp.model.Qianbao;
import com.magicmoble.luzhouapp.model.Qianbao_list;
import com.magicmoble.luzhouapp.model.Tuijian;
import com.mysql.jdbc.UpdatableResultSet;

public class QianbaoBusiness {
	public static String pay(double _price, String my_id, String friend_id, String type, String buy_name) {

		String sql = null;
		String sql1 = null;
		String sql2 = null;
		String sql3 = null;

		DBHelper db1 = null;
		DBHelper db2 = null;
		DBHelper db3 = null;
		DBHelper db4 = null;
		String str = null;
		String uuid = UUID.randomUUID().toString();
		Timestamp time = new Timestamp(new Date().getTime());

		sql = "SELECT qianbao FROM admin_xinxi WHERE id='" + my_id + "'";
		sql1 = "UPDATE admin_xinxi SET qianbao=round((qianbao-" + _price + "),2) WHERE id='" + my_id + "'";
		sql2 = "UPDATE admin_xinxi SET qianbao=(qianbao+" + _price + ") WHERE id='" + friend_id + "'";
		sql3 = "INSERT INTO qianbao_list(id,my_id,price,type,buy_name,time) VALUES('" + uuid + "','" + my_id + "','-"
				+ _price + "','" + type + "','" + buy_name + "','" + time + "')";

		double price = 0;

		ResultSet ret = null;
		try {
			db1 = new DBHelper(sql);
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				price = ret.getDouble(1);
			}
			if (_price > price) {
				str = "支付失败，余额不足";
			} else {
				db2 = new DBHelper(sql1);
				int ret1 = db2.pst.executeUpdate();
				str = "支付成功";
				db3 = new DBHelper(sql2);
				int ret2 = db3.pst.executeUpdate();
				db4 = new DBHelper(sql3);
				boolean ret3 = db4.pst.execute();

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		db1.close();
		db2.close();
		db3.close();
		db4.close();
		return str;

	}

	public static Qianbao getQianbao(String my_id) {
		String sql = null;
		DBHelper db1 = null;
		sql = "select qianbao from admin_xinxi where id ='" + my_id + "'";
		db1 = new DBHelper(sql);

		ResultSet ret = null;
		Qianbao qianbaoBeen = new Qianbao();

		double qianbao = 0.0;
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {

				qianbao = ret.getDouble(1);
				qianbaoBeen.setQianbao(qianbao);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qianbaoBeen;
	}

	public static Qianbao getqianbao_list(String my_id, int page) {

		String sql = null;
		String sql1 = null;
		Qianbao _qianbao = new Qianbao();
		double qianbao = 0.0;
		List<Qianbao_list> list = new ArrayList<Qianbao_list>();
		DBHelper db1 = null;
		DBHelper db2 = null;
		sql = "SELECT price,type,buy_name,time from qianbao_list WHERE my_id='" + my_id + "' order by time desc limit " + (page - 1) * 10
				+ "," + 10 * page;
		sql1 = "select qianbao from admin_xinxi where id='" + my_id + "'";

		ResultSet ret = null;
		ResultSet ret2 = null;

		try {
			db1 = new DBHelper(sql);
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				String price = ret.getString(1);
				String type = ret.getString(2);
				String buy_name = ret.getString(3);
				Timestamp time = ret.getTimestamp(4);

				Qianbao_list qianbao_list = new Qianbao_list();
				qianbao_list.setBuy_name(buy_name);
				qianbao_list.setPrice(price);
				qianbao_list.setTime(time);
				qianbao_list.setType(type);
				list.add(qianbao_list);
			}
			db2 = new DBHelper(sql1);
			ret2 = db2.pst.executeQuery();

			while (ret2.next()) {
				qianbao = ret2.getDouble(1);

			}

			_qianbao.setQianbao(qianbao);
			_qianbao.setList(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return _qianbao;

	}

	public static String Recharge(String id, double price) {

		String uuid = UUID.randomUUID().toString();
		String buy_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(id).getName();
		Timestamp time = new Timestamp(new Date().getTime());
		String sql = "UPDATE admin_xinxi SET qianbao=ROUND(qianbao+" + price + ",2) WHERE id = '" + id + "'";
		String sql1 = "INSERT INTO qianbao_list(id,my_id,price,type,buy_name,time) VALUES('" + uuid + "','" + id
				+ "','+" + price + "','充值','" + buy_name + "','" + time + "') ";
		DBHelper db1 = null;
		DBHelper db2 = null;
		db1 = new DBHelper(sql);
		db2 = new DBHelper(sql1);
		try {
			boolean ret = db1.pst.execute();
			boolean ret1 = db2.pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			db1.close();
			db2.close();
		}
		return "充值成功";
	}

}
