package com.magicmoble.luzhouapp.business;

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
import com.magicmoble.luzhouapp.model.Tuijian;

public class RenzhengBusiness {

	public static void renzheng(String my_id, String picture, String name, String message, int leixing_Tag) {
		String uuid = UUID.randomUUID().toString();
		DBHelper db1 = null;

		String renzheng_Tag = "2";
		if (leixing_Tag == 1) {
			String sql = "INSERT INTO renzheng(id,my_id,picture,name,message,renzheng_Tag,leixing_Tag) VALUES('" + uuid
					+ "','" + my_id + "','" + picture + "','" + name + "','" + message + "','" + renzheng_Tag + "','"
					+ leixing_Tag + "')";
			System.out.println(sql);
			db1 = new DBHelper(sql);
			try {
				boolean ret = db1.pst.execute();
				db1.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (leixing_Tag == 2) {
			String sql = "INSERT INTO renzheng(id,my_id,picture,name,message,renzheng_Tag,leixing_Tag) VALUES('" + uuid
					+ "','" + my_id + "','" + picture + "','" + name + "','" + message + "'," + renzheng_Tag + ","
					+ leixing_Tag + ")";
			System.out.println(sql);
			db1 = new DBHelper(sql);
			try {
				boolean ret = db1.pst.execute();
				db1.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
