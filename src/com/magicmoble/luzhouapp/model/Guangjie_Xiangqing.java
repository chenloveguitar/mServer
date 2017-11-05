package com.magicmoble.luzhouapp.model;

import java.sql.Timestamp;
import java.util.List;

public class Guangjie_Xiangqing {
	String uid;
	List<Picture> picture;
	String title;
	int dashang_count;
	int dianzan_count;
	String releaser_id;
	String releaser_name;
	String releaser_touxiang;
	int yuedu;
	boolean isguanzhu;
	double price;
	String unit;
	String dianpu_id;
	String dianpu_name;
	String dianpu_address;
	String dianpu_touxiang;
	int renzheng_Tag;

	int dianpu_pinglun_count;
	String content;
	List<Tuijian> tuijian;
	boolean shoucang_Tag;
	Hongbao dianzan_hongbao;
	Hongbao fenxiang_hongbao;
	String guanggao_url;
	List<Biaoqian> biaoqian;
	String phone;;
	String shenhe;
	Timestamp time;
	int freight;
	String seller_id;
	int pinglun_count;
	int guangjie_fenlei_Tag;
	boolean isdianzan;
	int shuliang;

	boolean have_fenxiang_hongbao;
	boolean have_dianzan_hongbao;

	public boolean isHave_fenxiang_hongbao() {
		return have_fenxiang_hongbao;
	}

	public void setHave_fenxiang_hongbao(boolean have_fenxiang_hongbao) {
		this.have_fenxiang_hongbao = have_fenxiang_hongbao;
	}

	public boolean isHave_dianzan_hongbao() {
		return have_dianzan_hongbao;
	}

	public void setHave_dianzan_hongbao(boolean have_dianzan_hongbao) {
		this.have_dianzan_hongbao = have_dianzan_hongbao;
	}

	public boolean isIsdianzan() {
		return isdianzan;
	}

	public void setIsdianzan(boolean isdianzan) {
		this.isdianzan = isdianzan;
	}

	public int getPinglun_count() {
		return pinglun_count;
	}

	public void setPinglun_count(int pinglun_count) {
		this.pinglun_count = pinglun_count;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	public String getReleaser_name() {
		return releaser_name;
	}

	public void setReleaser_name(String releaser_name) {
		this.releaser_name = releaser_name;
	}

	public String getReleaser_touxiang() {
		return releaser_touxiang;
	}

	public void setReleaser_touxiang(String releaser_touxiang) {
		this.releaser_touxiang = releaser_touxiang;
	}

	public int getDianpu_pinglun_count() {
		return dianpu_pinglun_count;
	}

	public List<Picture> getPicture() {
		return picture;
	}

	public void setPicture(List<Picture> picture) {
		this.picture = picture;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getDashang_count() {
		return dashang_count;
	}

	public void setDashang_count(int dashang_count) {
		this.dashang_count = dashang_count;
	}

	public int getDianzan_count() {
		return dianzan_count;
	}

	public void setDianzan_count(int dianzan_count) {
		this.dianzan_count = dianzan_count;
	}

	public String getReleaser_id() {
		return releaser_id;
	}

	public void setReleaser_id(String releaser_id) {
		this.releaser_id = releaser_id;
	}

	public int getYuedu() {
		return yuedu;
	}

	public void setYuedu(int yuedu) {
		this.yuedu = yuedu;
	}

	public boolean isIsguanzhu() {
		return isguanzhu;
	}

	public void setIsguanzhu(boolean isguanzhu) {
		this.isguanzhu = isguanzhu;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getDianpu_id() {
		return dianpu_id;
	}

	public void setDianpu_id(String dianpu_id) {
		this.dianpu_id = dianpu_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Tuijian> getTuijian() {
		return tuijian;
	}

	public void setTuijian(List<Tuijian> tuijian) {
		this.tuijian = tuijian;
	}

	public boolean isShoucang_Tag() {
		return shoucang_Tag;
	}

	public void setShoucang_Tag(boolean shoucang_Tag) {
		this.shoucang_Tag = shoucang_Tag;
	}

	public Hongbao getDianzan_hongbao() {
		return dianzan_hongbao;
	}

	public void setDianzan_hongbao(Hongbao dianzan_hongbao) {
		this.dianzan_hongbao = dianzan_hongbao;
	}

	public Hongbao getFenxiang_hongbao() {
		return fenxiang_hongbao;
	}

	public void setFenxiang_hongbao(Hongbao fenxiang_hongbao) {
		this.fenxiang_hongbao = fenxiang_hongbao;
	}

	public String getGuanggao_url() {
		return guanggao_url;
	}

	public void setGuanggao_url(String guanggao_url) {
		this.guanggao_url = guanggao_url;
	}

	public List<Biaoqian> getBiaoqian() {
		return biaoqian;
	}

	public void setBiaoqian(List<Biaoqian> biaoqian) {
		this.biaoqian = biaoqian;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getShenhe() {
		return shenhe;
	}

	public void setShenhe(String shenhe) {

		this.shenhe = shenhe;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getDianpu_name() {
		return dianpu_name;
	}

	public void setDianpu_name(String dianpu_name) {
		this.dianpu_name = dianpu_name;
	}

	public void setDianpu_pinglun_count(int dianpu_pinglun_count) {
		this.dianpu_pinglun_count = dianpu_pinglun_count;
	}

	public int getFreight() {
		return freight;
	}

	public void setFreight(int freight) {
		this.freight = freight;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int getGuangjie_fenlei_Tag() {
		return guangjie_fenlei_Tag;
	}

	public void setGuangjie_fenlei_Tag(int guangjie_fenlei_Tag) {
		this.guangjie_fenlei_Tag = guangjie_fenlei_Tag;
	}

	public String getDianpu_address() {
		return dianpu_address;
	}

	public void setDianpu_address(String dianpu_address) {
		this.dianpu_address = dianpu_address;
	}

	public String getDianpu_touxiang() {
		return dianpu_touxiang;
	}

	public void setDianpu_touxiang(String dianpu_touxiang) {
		this.dianpu_touxiang = dianpu_touxiang;
	}

	public int getRenzheng_Tag() {
		return renzheng_Tag;
	}

	public void setRenzheng_Tag(int renzheng_Tag) {
		this.renzheng_Tag = renzheng_Tag;
	}

	public int getShuliang() {
		return shuliang;
	}

	public void setShuliang(int shuliang) {
		this.shuliang = shuliang;
	}

}
