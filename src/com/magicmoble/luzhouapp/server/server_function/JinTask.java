package com.magicmoble.luzhouapp.server.server_function;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.TimerTask;

import com.magicmoble.luzhouapp.business.DBHelper;
import com.magicmoble.luzhouapp.business.FunctionBusiness;
import com.magicmoble.luzhouapp.business.JiguangPush;

public class JinTask extends TimerTask {

	public void run() {
		// 可以定义自己要处理的方法

		Timestamp time = new Timestamp(new Date().getTime());
		String sql = "SELECT id,buyer_id,seller_id,pay_end_time FROM dingdan where buyer_zhuangtai=1";

		DBHelper db1 = new DBHelper(sql);
		DBHelper db2 = null;
		ResultSet ret = null;
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				String id = ret.getString(1);
				String buyer_id = ret.getString(2);
				String seller_id = ret.getString(3);
				Timestamp time2 = ret.getTimestamp(4);
				if (time2.getTime() < time.getTime()) {
					FunctionBusiness.addmessage(seller_id, buyer_id, id, "订单已取消", "超出时间未付款 系统自动取消");

					String sql1 = "update dingdan set buyer_zhuangtai=7,seller_zhuangtai=7 where id='" + id + "'";
					db2 = new DBHelper(sql1);
					boolean ret2 = db2.pst.execute();
System.out.println("11111111111111");
					 JiguangPush.push(buyer_id, "你的订单超出时间未付款 系统自动取消");
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (db2 != null) {
				db2.close();
			}

			db1.close();
		}

	}

}