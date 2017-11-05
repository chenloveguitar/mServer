package com.magicmoble.luzhouapp.business;

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
import com.magicmoble.luzhouapp.model.Guanggao;
import com.magicmoble.luzhouapp.model.Hongbao;
import com.magicmoble.luzhouapp.model.Picture;
import com.magicmoble.luzhouapp.model.Toutiao;
import com.magicmoble.luzhouapp.model.Toutiao_Shouye;
import com.magicmoble.luzhouapp.model.Toutiao_Xiangqing;
import com.magicmoble.luzhouapp.model.Tuijian;

public class ToutiaoBusiness {

	public static List<Guanggao> getGuanggao() {
		String sql;
		DBHelper db1 = null;
		List<Guanggao> list = new ArrayList<Guanggao>();
		sql = "select * from advertisement";
		db1 = new DBHelper(sql);
		ResultSet ret = null;
		int num = 0;
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
				String guanggao_id = ret.getString(3);
				int fenlei_Tag = ret.getInt(4);
				int guangjie_fenlei_Tag = ret.getInt(5);
				int muban_Tag = ret.getInt(6);

				Guanggao guanggao = new Guanggao();
				guanggao.setPicture(list2);
				guanggao.setUid(guanggao_id);
				guanggao.setFenlei_Tag(fenlei_Tag);
				guanggao.setGuangjie_fenlei_Tag(guangjie_fenlei_Tag);
				guanggao.setMuban_Tag(muban_Tag);
				if (num >= 5) {
					list.set(num % 5, guanggao);
				} else {
					list.add(guanggao);
				}
				num++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

	public static Toutiao getShoucangToutiaoById(String toutiao_id) {
		Toutiao toutiao = new Toutiao();

		String sql = null;
		DBHelper db1 = null;

		sql = "select id,title,picture,time,muban_Tag from toutiao where id='" + toutiao_id + "'";
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
				Timestamp time = ret.getTimestamp(4);
				int muban_Tag = ret.getInt(5);

				toutiao.setToutiao_id(id);
				toutiao.setTitle(title);
				toutiao.setPicture(list2);
				toutiao.setTime(time);
				toutiao.setMuban_Tag(muban_Tag);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return toutiao;
	}

	public static void toutiao_yuedu(String id) {

		String sql1 = null;
		DBHelper db2 = null;
		int ret2;
		sql1 = "UPDATE toutiao SET yuedu_count=(yuedu_count+1) WHERE id='" + id + "'";
		db2 = new DBHelper(sql1);
		try {
			ret2 = db2.pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db2.close();
	}

	public static List<Toutiao_Xiangqing> getToutiaoById(String my_id, String _id) {
		List<Toutiao_Xiangqing> list = new ArrayList<Toutiao_Xiangqing>();
		String sql = null;

		int yuedu_count;
		DBHelper db1 = null;

		List<Biaoqian> list2 = new ArrayList<Biaoqian>();

		sql = "select id,title,content,dianzan_count,pinglun_count,dashang_count,releaser_id,releaser_name,releaser_touxiang,shoucang_Tag,yuedu_count,time,biaoqian,picture from toutiao WHERE   id='"
				+ _id + "'";
		db1 = new DBHelper(sql);

		ResultSet ret = null;
		boolean isguanzhu;

		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				String id = ret.getString(1);

				String title = ret.getString(2);
				String content = ret.getString(3);
				String[] contents = content.split("<--分隔符-->");
				String str = "<div  class='article-content' >";
				for (int i = 0; i < contents.length; i++) {
					if (contents[i].substring(0, 4).equals("http")) {
						str += "<img src=\"" + contents[i] + "\">";
					} else {
						str += "<p>" + contents[i] + "</p>";
					}
				}
				str += "</div>";
				int dianzan_counts = FunctionBusiness.getDianzanNumber(id, 3).getDianzan_count();
				int dashang_count = FunctionBusiness.getDashangNumber(id);

				String releaser_id = ret.getString(7);
				String releaser_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id).getName();
				String releaser_touxiang = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id)
						.getTouxiang_picture();
				if (my_id == null) {
					isguanzhu = false;
				} else {
					isguanzhu = GuanzhuBusiness.getIsGuanzhu(my_id, releaser_id);
				}
				boolean shoucang_Tag = false;
				int isshoucang = FunctionBusiness.getshoucang(id, my_id);
				if (isshoucang == 1) {
					shoucang_Tag = false;
				} else if (isshoucang == 2) {
					shoucang_Tag = true;
				}

				Hongbao dianzan_hongbao = FunctionBusiness.getHongbaoById(id, 1);
				Hongbao fenxiang_hongbao = FunctionBusiness.getHongbaoById(id, 2);
				yuedu_count = ret.getInt(11);
				Timestamp time = ret.getTimestamp(12);
				int pinglun_count = FunctionBusiness.getPinglun_size(id);

				String biaoqian = ret.getString(13);
				List<Tuijian> tuijian = new ArrayList<Tuijian>();
				if (biaoqian != null) {
					String[] biaoqians = biaoqian.split(",");

					for (int i = 0; i < biaoqians.length; i++) {
						Biaoqian biaoqian2 = new Biaoqian();
						biaoqian2.setBiaoqian(biaoqians[i]);
						list2.add(biaoqian2);
					}
					tuijian = getTuijianByBiaoqian(list2);
				}

				String picture = ret.getString(14);
				String[] aa = picture.split(",");

				List<Picture> pictures = new ArrayList<Picture>();
				for (int i = 0; i < aa.length; i++) {
					Picture picture_url = new Picture();
					picture_url.setPicture_url(aa[i]);
					pictures.add(picture_url);
				}
				int muban_Tag = 0;
				if (pictures.size() >= 3) {
					muban_Tag = 1;
				} else {
					muban_Tag = 2;
				}

				boolean isdianzan = FunctionBusiness.getDianzan(my_id, _id, 3);

				boolean have_dianzan_hongbao = (dianzan_hongbao.getCount() != 0);
				boolean have_fenxiang_hongbao = (fenxiang_hongbao.getCount() != 0);

				Toutiao_Xiangqing toutiao = new Toutiao_Xiangqing();
				toutiao.setUid(id);
				toutiao.setTitle(title);
				toutiao.setContent(str);
				toutiao.setDianzan_count(dianzan_counts);
				toutiao.setPinglun_count(pinglun_count);
				toutiao.setReleaser_id(releaser_id);
				toutiao.setReleaser_name(releaser_name);
				toutiao.setReleaser_touxiang(releaser_touxiang);
				toutiao.setIsguanzhu(isguanzhu);
				toutiao.setDashang_count(dashang_count);
				toutiao.setShoucang_Tag(shoucang_Tag);
				toutiao.setDianzan_hongbao(dianzan_hongbao);
				toutiao.setFenxiang_hongbao(fenxiang_hongbao);
				toutiao.setYuedu(yuedu_count);
				toutiao.setTime(time);
				toutiao.setBiaoqian(list2);
				toutiao.setTuijian(tuijian);

				toutiao.setPicture(pictures);
				toutiao.setIsdianzan(isdianzan);
				toutiao.setHave_dianzan_hongbao(have_dianzan_hongbao);
				toutiao.setHave_fenxiang_hongbao(have_fenxiang_hongbao);
				list.add(toutiao);
				FunctionBusiness.addZujiByToutiao(my_id, id, picture, title, muban_Tag);
			}

			ret.close();

			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<Toutiao> getFenquToutiao(int _fenlei_Tag, int page) {
		List<Toutiao> list = new ArrayList<Toutiao>();
		String sql = null;
		DBHelper db1 = null;
		sql = "select *from toutiao WHERE shenhe LIKE '已发布%' and fenlei_Tag='" + _fenlei_Tag + "'LIMIT  "
				+ (page - 1) * 12 + "," + 12 * page;
		db1 = new DBHelper(sql);
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
				String title = ret.getString(3);
				String name = ret.getString(4);
				String content = ret.getString(5);
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
				int fufei_Tag = ret.getInt(6);
				int fenlei_Tag = ret.getInt(7);
				int muban_Tag = ret.getInt(8);
				String laiyuan = ret.getString(9);

				int pinglun_count = ret.getInt(11);
				String shenhe = ret.getString(12);
				String releaser_id = ret.getString(13);
				String releaser_name = ret.getString(14);
				Dianzan_Number dianzan_count = FunctionBusiness.getDianzanNumber(id, 3);

				Toutiao toutiao = new Toutiao();
				toutiao.setToutiao_id(id);
				toutiao.setPicture(list2);
				toutiao.setTitle(title);
				toutiao.setName(name);
				toutiao.setContent(list3);
				toutiao.setFufei_Tag(fufei_Tag);
				toutiao.setFenlei_Tag(fenlei_Tag);
				toutiao.setMuban_Tag(muban_Tag);
				toutiao.setLaiyuan(laiyuan);
				toutiao.setDianzan_count(dianzan_count);
				toutiao.setPinglun_count(pinglun_count);
				toutiao.setShenhe(shenhe);
				toutiao.setReleaser_id(releaser_id);
				toutiao.setReleaser_name(releaser_name);

				list.add(toutiao);
			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<Tuijian> getTuijianByBiaoqian(List<Biaoqian> biaoqian) {
		String sql = null;
		DBHelper db1 = null;
		sql = "select id,title,name,picture,time from toutiao WHERE biaoqian like '%" + biaoqian.get(0).getBiaoqian()
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

	public static List<Object> getToutiao_ShouyeInfoPage(String my_id, int page, int fenlei_Tag) {
		String sql = null;
		String sql1 = null;
		String sql2 = null;
		DBHelper db1 = null;
		DBHelper db2 = null;
		DBHelper db3 = null;
		List<String> guanzhus = new ArrayList<String>();
		if (my_id != null) {
			guanzhus = GuanzhuBusiness.getFriend_id(my_id, 0);
		}

		if (fenlei_Tag == 1) {

			if (page == 1) {
				sql = "select id,title,name,picture,laiyuan,pinglun_count,dianzan_count,muban_Tag from toutiao WHERE shenhe LIKE '已发布%' and fenlei_Tag=1 and fufei_Tag=1 order by rand() LIMIT 0,12";
			}

			sql1 = "select id,title,name,picture,laiyuan,pinglun_count,dianzan_count,muban_Tag from toutiao WHERE shenhe LIKE '已发布%' and fenlei_Tag=1 and fufei_Tag=0 order by  rand() LIMIT "
					+ (page - 1) * 12 + "," + 12 * page;

		} else if (fenlei_Tag == 2) {
			if (my_id != null) {

				if (page == 1) {
					sql = "select id,title,name,picture,laiyuan,pinglun_count,dianzan_count,muban_Tag from toutiao WHERE shenhe LIKE '已发布%' and fenlei_Tag=2  ";
					if (guanzhus.size() != 0) {
						for (int i = 0; i < guanzhus.size(); i++) {
							if (i == 0) {
								sql += "and (releaser_id=" + guanzhus.get(i);
							}
							if ((i != 0 && i != guanzhus.size())) {
								sql += "releaser_id=" + guanzhus.get(i);
							}
							if (i == guanzhus.size() - 1) {
								sql += ")";
							}
							if (guanzhus.size() - 1 > i) {
								sql += " or ";
							}

						}

					}
					sql += " and fufei_Tag=0 order by rand() LIMIT 0,12";
				}
			}

			sql1 = "select id,title,name,picture,laiyuan,pinglun_count,dianzan_count,muban_Tag from toutiao WHERE shenhe LIKE '已发布%' and fenlei_Tag=2 and fufei_Tag=0 order by rand() LIMIT "
					+ (page - 1) * 12 + "," + 12 * page;

		} else if (fenlei_Tag == 3) {
			if (my_id != null) {

				if (page == 1) {
					sql = "select id,title,name,picture,laiyuan,pinglun_count,dianzan_count,muban_Tag from toutiao WHERE shenhe LIKE '已发布%' and fenlei_Tag=2 ";
					if (guanzhus.size() != 0) {
						for (int i = 0; i < guanzhus.size(); i++) {
							if (i == 0) {
								sql += "and (releaser_id=" + guanzhus.get(i);
							}
							if ((i != 0 && i != guanzhus.size())) {
								sql += "releaser_id=" + guanzhus.get(i);
							}
							if (i == guanzhus.size() - 1) {
								sql += ")";
							}
							if (guanzhus.size() - 1 > i) {
								sql += " or ";
							}
						}
					}
					sql += " and fufei_Tag=0 order by rand() LIMIT 0,12";
				}
			}
			sql1 = "select id,title,name,picture,laiyuan,pinglun_count,dianzan_count,muban_Tag from toutiao WHERE shenhe LIKE '已发布%' and fenlei_Tag=2 and fufei_Tag=0 order by time desc LIMIT "
					+ (page - 1) * 12 + "," + 12 * page;

		} else if (fenlei_Tag == 4) {
			
		}
		sql2 = "select * from advertisement order by  rand() LIMIT 1";
		if (my_id != null && fenlei_Tag == 1 && page == 1) {
			db1 = new DBHelper(sql);

		}

		ResultSet ret = null;
		ResultSet ret1 = null;
		ResultSet ret2 = null;

		List<Object> list = new ArrayList<Object>();
		try {
			if (my_id != null && fenlei_Tag == 1 && page == 1) {

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
			}
			db2 = new DBHelper(sql1);
			ret1 = db2.pst.executeQuery();
			while (ret1.next()) {
				String id = ret1.getString(1);
				String title = ret1.getString(2);
				String name = ret1.getString(3);
				String picture = ret1.getString(4);
				String[] aa = picture.split(",");

				List<Picture> list2 = new ArrayList<Picture>();
				for (int i = 0; i < aa.length; i++) {
					Picture picture_url = new Picture();
					picture_url.setPicture_url(aa[i]);
					list2.add(picture_url);
				}
				String laiyuan = ret1.getString(5);
				int pinglun_count = FunctionBusiness.getPinglun_size(id);

				int muban_Tag = ret1.getInt(8);
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

			// URL_TAG 1：内链 2：外链

			Guanggao guanggao = new Guanggao();
			db3 = new DBHelper(sql2);
			ret2 = db3.pst.executeQuery();
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
				String guanggao_id = ret2.getString(3);
				int guanggao_fenlei_Tag = ret2.getInt(4);
				int guangjie_fenlei_Tag = ret2.getInt(5);
				int muban_Tag = ret2.getInt(6);

				guanggao.setPicture(list2);
				guanggao.setUid(guanggao_id);
				guanggao.setFenlei_Tag(guanggao_fenlei_Tag);
				guanggao.setGuangjie_fenlei_Tag(guangjie_fenlei_Tag);
				guanggao.setMuban_Tag(muban_Tag);
				guanggao.setGuanggao_Tag(1);
				if (list.size() > 3 && page == 1) {
					list.add(3, guanggao);
				}
			}

			if (my_id != null && fenlei_Tag == 1 && page == 1) {
				ret.close();
				db1.close();
			}

			ret1.close();
			ret2.close();

			db2.close();
			db3.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static String addToutiao(String picture, String title, String name, String content, int muban_Tag,
			String releaser_id) {
		String sql = null;
		DBHelper db1 = null;
		String uuid = UUID.randomUUID().toString();
		int fufei_Tag = 0;
		int fenlei_Tag = 0;
		String releaser_name = Admin_xinxi_Business.getAdmin_xinxiInfoById(releaser_id).getName();
		fenlei_Tag = 2;
		String shenhe = "正在审核中...";
		Timestamp time = new Timestamp(new Date().getTime());
		sql = "INSERT INTO toutiao(id,picture,title,name,content,fufei_Tag,fenlei_Tag,muban_Tag,releaser_id,releaser_name,shenhe,time) VALUES ('"
				+ uuid + "', '" + picture + "', '" + title + "', '" + name + "', '" + content + "', " + fufei_Tag + ", "
				+ fenlei_Tag + "," + muban_Tag + ",'" + releaser_id + "','" + releaser_name + "','" + shenhe + "','"
				+ time + "')";
		db1 = new DBHelper(sql);
		try {
			boolean ret = db1.pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		db1.close();
		return "发布成功";

	}

	public static void DeleteToutiao(String id) {
		{
			String sql = null;
			DBHelper db1 = null;
			sql = "DELETE FROM toutiao WHERE id =  '" + id + "'";
			db1 = new DBHelper(sql);
			try {
				boolean ret = db1.pst.execute();
				db1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public static void UpdateToutiao(String key, String value, String id) {
		{
			String sql = null;
			DBHelper db1 = null;
			sql = "UPDATE toutiao SET " + key + " = '" + value + "' WHERE id = '" + id + "'";
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
