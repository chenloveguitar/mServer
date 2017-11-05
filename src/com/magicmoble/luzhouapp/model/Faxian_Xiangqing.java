package com.magicmoble.luzhouapp.model;

import java.sql.Timestamp;
import java.util.List;

public class Faxian_Xiangqing {
	String uid;
	String title;
	List<Picture> pictures;
	// List<ContentModel> content;
	String content;
	int dianzan_count;
	int pinglun_count;
	int dashang_count;
	String releaser_id;
	String releaser_name;
	String releaser_touxiang;
	boolean isguanzhu;
	boolean shoucang_Tag;
	Hongbao dianzan_hongbao;
	Hongbao fenxiang_hongbao;
	int yuedu;
	Timestamp time;
	List<Tuijian> tuijian;
	String guanggao_url;
	List<Biaoqian> biaoqian;
	boolean isdianzan;
	boolean have_dianzan_hongbao;
	boolean have_fenxiang_hongbao;

	public boolean isHave_dianzan_hongbao() {
		return have_dianzan_hongbao;
	}

	public void setHave_dianzan_hongbao(boolean have_dianzan_hongbao) {
		this.have_dianzan_hongbao = have_dianzan_hongbao;
	}

	public boolean isHave_fenxiang_hongbao() {
		return have_fenxiang_hongbao;
	}

	public void setHave_fenxiang_hongbao(boolean have_fenxiang_hongbao) {
		this.have_fenxiang_hongbao = have_fenxiang_hongbao;
	}

	public boolean isIsdianzan() {
		return isdianzan;
	}

	public void setIsdianzan(boolean isdianzan) {
		this.isdianzan = isdianzan;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getDianzan_count() {
		return dianzan_count;
	}

	public void setDianzan_count(int dianzan_count) {
		this.dianzan_count = dianzan_count;
	}

	public int getPinglun_count() {
		return pinglun_count;
	}

	public void setPinglun_count(int pinglun_count) {
		this.pinglun_count = pinglun_count;
	}

	public int getDashang_count() {
		return dashang_count;
	}

	public void setDashang_count(int dashang_count) {
		this.dashang_count = dashang_count;
	}

	public String getReleaser_id() {
		return releaser_id;
	}

	public void setReleaser_id(String releaser_id) {
		this.releaser_id = releaser_id;
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

	public boolean isIsguanzhu() {
		return isguanzhu;
	}

	public void setIsguanzhu(boolean isguanzhu) {
		this.isguanzhu = isguanzhu;
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

	public int getYuedu() {
		return yuedu;
	}

	public void setYuedu(int yuedu) {
		this.yuedu = yuedu;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public List<Tuijian> getTuijian() {
		return tuijian;
	}

	public void setTuijian(List<Tuijian> tuijian) {
		this.tuijian = tuijian;
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

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

}
