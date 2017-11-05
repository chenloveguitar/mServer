package com.magicmoble.luzhouapp.model;

import java.sql.Timestamp;
import java.util.List;

public class Toutiao {
	String Toutiao_id;
	List<Picture> picture;
	String title;
	String name;
	List<ContentModel> content;
	int fufei_Tag;
	int fenlei_Tag;
	int muban_Tag;
	String laiyuan;
	Dianzan_Number dianzan_count;
	int pinglun_count;
	String shenhe;
	String releaser_id;
	String releaser_name;
	List<Hongbao> dianzan_hongbao;
	List<Hongbao> fenxiang_hongbao;
	Timestamp time;

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getToutiao_id() {
		return Toutiao_id;
	}

	public void setToutiao_id(String toutiao_id) {
		Toutiao_id = toutiao_id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ContentModel> getContent() {
		return content;
	}

	public void setContent(List<ContentModel> content) {
		this.content = content;
	}

	public int getFufei_Tag() {
		return fufei_Tag;
	}

	public void setFufei_Tag(int fufei_Tag) {
		this.fufei_Tag = fufei_Tag;
	}

	public int getFenlei_Tag() {
		return fenlei_Tag;
	}

	public void setFenlei_Tag(int fenlei_Tag) {
		this.fenlei_Tag = fenlei_Tag;
	}

	public int getMuban_Tag() {
		return muban_Tag;
	}

	public void setMuban_Tag(int muban_Tag) {
		this.muban_Tag = muban_Tag;
	}

	public String getLaiyuan() {
		return laiyuan;
	}

	public void setLaiyuan(String laiyuan) {
		this.laiyuan = laiyuan;
	}

	public Dianzan_Number getDianzan_count() {
		return dianzan_count;
	}

	public void setDianzan_count(Dianzan_Number dianzan_count) {
		this.dianzan_count = dianzan_count;
	}

	public int getPinglun_count() {
		return pinglun_count;
	}

	public void setPinglun_count(int pinglun_count) {
		this.pinglun_count = pinglun_count;
	}

	public String getShenhe() {
		return shenhe;
	}

	public void setShenhe(String shenhe) {
		this.shenhe = shenhe;
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

	public List<Hongbao> getDianzan_hongbao() {
		return dianzan_hongbao;
	}

	public void setDianzan_hongbao(List<Hongbao> dianzan_hongbao) {
		this.dianzan_hongbao = dianzan_hongbao;
	}

	public List<Hongbao> getFenxiang_hongbao() {
		return fenxiang_hongbao;
	}

	public void setFenxiang_hongbao(List<Hongbao> fenxiang_hongbao) {
		this.fenxiang_hongbao = fenxiang_hongbao;
	}

}
