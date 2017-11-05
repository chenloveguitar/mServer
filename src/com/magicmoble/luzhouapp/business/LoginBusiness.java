package com.magicmoble.luzhouapp.business;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.magicmoble.luzhouapp.model.Admin_xinxi;
import com.magicmoble.luzhouapp.model.Login;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class LoginBusiness {
	public static List<Login> getAllLogin() {
		List<Login> list = new ArrayList<Login>();
		String sql = null;
		DBHelper db1 = null;
		sql = "select *from login";
		db1 = new DBHelper(sql);
		ResultSet ret = null;
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				String id = ret.getString(1);
				String phone = ret.getString(2);
				String password = ret.getString(3);

				Login login = new Login();
				login.setLogin_id(id);
				login.setPhone(phone);
				login.setPassword(password);

				list.add(login);
			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static Map<String, String> sendMobileCode(String phone) throws ApiException {// 注册
		String rusult = null;
		Map<String, String> map = new HashMap<String, String>();
		// 官网的URL
		String url = "http://gw.api.taobao.com/router/rest";
		// 成为开发者，创建应用后系统自动生成
		String appkey = "23843110";

		String secret = "665ca1f97324bc5046fcd7f63c39281b";
		String code = "";
		for (int i = 0; i < 6; i++) {
			code += (int) (Math.random() * 10);
		}

		String product = "手机泸州";
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("1234");
		req.setSmsType("normal");
		req.setSmsFreeSignName("手机泸州");

		req.setSmsParamString("{\"code\":\"" + code + "\",\"product\":\"" + product + "\"}");
		req.setRecNum(phone);
		req.setSmsTemplateCode("SMS_63115081");

		AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
		rusult = rsp.getSubMsg();

		map.put("rusult", rusult);
		map.put("code", code);

		return map;
	}

	public static Map<String, String> Repassword(String phone) throws ApiException {// 注册
		String rusult = null;
		Map<String, String> map = new HashMap<String, String>();
		// 官网的URL
		String url = "http://gw.api.taobao.com/router/rest";
		// 成为开发者，创建应用后系统自动生成
		String appkey = "23843110";

		String secret = "665ca1f97324bc5046fcd7f63c39281b";
		String code = "";
		for (int i = 0; i < 6; i++) {
			code += (int) (Math.random() * 10);
		}

		String product = "手机泸州";
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("1234");
		req.setSmsType("normal");
		req.setSmsFreeSignName("手机泸州");

		req.setSmsParamString("{\"code\":\"" + code + "\",\"product\":\"" + product + "\"}");
		req.setRecNum(phone);
		req.setSmsTemplateCode("SMS_63115077");

		AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
		rusult = rsp.getSubMsg();

		map.put("rusult", rusult);
		map.put("code", code);

		return map;
	}

	public static Admin_xinxi getAdmin_xinxiInfoByPhone(String _phone) {
		String sql = null;
		DBHelper db1 = null;
		sql = "select * from admin_xinxi where phone =" + _phone;
		db1 = new DBHelper(sql);
		ResultSet ret = null;
		Admin_xinxi admin_xinxi = new Admin_xinxi();
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				String id = ret.getString(1);
				String touxiang_picture = ret.getString(2);
				String name = ret.getString(3);
				String weichat = ret.getString(4);
				String sex = ret.getString(5);
				String qianming = ret.getString(6);
				String shouhuo_address = ret.getString(7);
				String phone = ret.getString(8);

				admin_xinxi.setAdmin_xinxi_id(id);
				admin_xinxi.setTouxiang_picture(touxiang_picture);
				admin_xinxi.setName(name);
				admin_xinxi.setWeichat(weichat);
				admin_xinxi.setSex(sex);
				admin_xinxi.setQianming(qianming);
				admin_xinxi.setShouhuo_address(shouhuo_address);
				admin_xinxi.setPhone(phone);
			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return admin_xinxi;
	}

	public static Login getLoginInfoById(String _id) {
		String sql = null;
		DBHelper db1 = null;
		sql = "select * from login where id =" + _id;
		db1 = new DBHelper(sql);
		ResultSet ret = null;
		Login login = new Login();
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				String id = ret.getString(1);
				String phone = ret.getString(2);
				String password = ret.getString(3);
				login.setLogin_id(id);
				login.setPhone(phone);
				login.setPassword(password);
			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return login;
	}

	public static void Repass(String Phone, String NewPassword) {
		String sql = null;
		DBHelper db1 = null;
		sql = "UPDATE login set password = " + NewPassword + " where phone = " + Phone;
		db1 = new DBHelper(sql);
		try {
			int ret = db1.pst.executeUpdate();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void Regist(String id, String name, String touxiang, String sex) {
		String sql = null;
		DBHelper db1 = null;
		int yonghu_Tag = 3;
		if (sex == null) {
			sex = "未知";
		}

		Admin_xinxi xinxi = Admin_xinxi_Business.getAdmin_xinxiInfoById(id);

		try {
			if (xinxi.getAdmin_xinxi_id() != null) {

			} else {
				if (id == null) {

				} else {
					sql = "INSERT INTO admin_xinxi(id,touxiang_picture,name,sex,yonghu_Tag) VALUES ('" + id + "', '"
							+ touxiang + "', '" + name + "', '" + sex + "', '" + yonghu_Tag + "')";
					db1 = new DBHelper(sql);

					boolean ret = db1.pst.execute();
					db1.close();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static String Regist(String Phone, String Password) {
		String sql = null;
		String sql2 = null;
		DBHelper db1 = null;
		DBHelper db2 = null;
		int yonghu_Tag = 3;
		String uuid = UUID.randomUUID().toString();
		sql = "INSERT INTO login VALUES ('" + uuid + "', '" + Phone + "', '" + Password + "'" + ")";
		sql2 = "INSERT INTO admin_xinxi(id,name,phone,yonghu_Tag) VALUES ('" + uuid + "', '" + Phone + "', '" + Phone
				+ "', '" + yonghu_Tag + "')";

		db1 = new DBHelper(sql);
		db2 = new DBHelper(sql2);
		try {
			boolean ret = db1.pst.execute();
			boolean ret2 = db2.pst.execute();
			db1.close();
			db2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uuid;
	}
}
