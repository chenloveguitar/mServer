package com.magicmoble.luzhouapp.model;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public class Order {

	String transaction_id;
	Timestamp time;
	List<Picture> pictures;
	String title;
	double total_price;
	int shuliang;
	double freight;
	String buyer_id;
	String buyer_name;
	String buyer_touxiang;
	String buyer_qianming;
	String seller_id;
	String seller_name;
	String seller_touxiang;
	String seller_qianming;
	String name;
	String phone;
	String address;
	String beizhu;
	double price;
	String tuikuan_message;
	int buyer_zhuangtai_Tag;
	int seller_zhuangtai_Tag;
	String buyer_zhuangtai_message;
	String seller_zhuangtai_message;
	Timestamp pay_time;
	Timestamp pay_end_time;
	Timestamp jiedan_time;
	Timestamp jiedan_end_time;
	Timestamp express_time;
	Timestamp express_end_time;
	Timestamp pingjia_time;
	Timestamp pingjia_end_time;
	Timestamp complain_time;
	Timestamp complain_end_time;
	Timestamp fahuo_time;
	Timestamp fahuo_end_time;
	Timestamp Surplus_time;

	public Timestamp getSurplus_time() {
		return Surplus_time;
	}

	public void setSurplus_time(Timestamp surplus_time) {
		Surplus_time = surplus_time;
	}

	public Timestamp getFahuo_time() {
		return fahuo_time;
	}

	public void setFahuo_time(Timestamp fahuo_time) {
		this.fahuo_time = fahuo_time;
	}

	public Timestamp getFahuo_end_time() {
		return fahuo_end_time;
	}

	public void setFahuo_end_time(Timestamp fahuo_end_time) {
		this.fahuo_end_time = fahuo_end_time;
	}

	public Timestamp getPay_time() {
		return pay_time;
	}

	public void setPay_time(Timestamp pay_time) {
		this.pay_time = pay_time;
	}

	public Timestamp getPay_end_time() {
		return pay_end_time;
	}

	public void setPay_end_time(Timestamp pay_end_time) {
		this.pay_end_time = pay_end_time;
	}

	public Timestamp getJiedan_time() {
		return jiedan_time;
	}

	public void setJiedan_time(Timestamp jiedan_time) {
		this.jiedan_time = jiedan_time;
	}

	public Timestamp getJiedan_end_time() {
		return jiedan_end_time;
	}

	public void setJiedan_end_time(Timestamp jiedan_end_time) {
		this.jiedan_end_time = jiedan_end_time;
	}

	public Timestamp getExpress_time() {
		return express_time;
	}

	public void setExpress_time(Timestamp express_time) {
		this.express_time = express_time;
	}

	public Timestamp getExpress_end_time() {
		return express_end_time;
	}

	public void setExpress_end_time(Timestamp express_end_time) {
		this.express_end_time = express_end_time;
	}

	public Timestamp getPingjia_time() {
		return pingjia_time;
	}

	public void setPingjia_time(Timestamp pingjia_time) {
		this.pingjia_time = pingjia_time;
	}

	public Timestamp getPingjia_end_time() {
		return pingjia_end_time;
	}

	public void setPingjia_end_time(Timestamp pingjia_end_time) {
		this.pingjia_end_time = pingjia_end_time;
	}

	public Timestamp getComplain_time() {
		return complain_time;
	}

	public void setComplain_time(Timestamp complain_time) {
		this.complain_time = complain_time;
	}

	public Timestamp getComplain_end_time() {
		return complain_end_time;
	}

	public void setComplain_end_time(Timestamp complain_end_time) {
		this.complain_end_time = complain_end_time;
	}

	public String getBuyer_zhuangtai_message() {
		return buyer_zhuangtai_message;
	}

	public void setBuyer_zhuangtai_message(String buyer_zhuangtai_message) {
		this.buyer_zhuangtai_message = buyer_zhuangtai_message;
	}

	public String getSeller_zhuangtai_message() {
		return seller_zhuangtai_message;
	}

	public void setSeller_zhuangtai_message(String seller_zhuangtai_message) {
		this.seller_zhuangtai_message = seller_zhuangtai_message;
	}

	public double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}

	public int getBuyer_zhuangtai_Tag() {
		return buyer_zhuangtai_Tag;
	}

	public void setBuyer_zhuangtai_Tag(int buyer_zhuangtai_Tag) {
		this.buyer_zhuangtai_Tag = buyer_zhuangtai_Tag;
	}

	public int getSeller_zhuangtai_Tag() {
		return seller_zhuangtai_Tag;
	}

	public void setSeller_zhuangtai_Tag(int seller_zhuangtai_Tag) {
		this.seller_zhuangtai_Tag = seller_zhuangtai_Tag;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getShuliang() {
		return shuliang;
	}

	public void setShuliang(int shuliang) {
		this.shuliang = shuliang;
	}

	public double getFreight() {
		return freight;
	}

	public void setFreight(double freight) {
		this.freight = freight;
	}

	public String getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	public String getBuyer_name() {
		return buyer_name;
	}

	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}

	public String getBuyer_touxiang() {
		return buyer_touxiang;
	}

	public void setBuyer_touxiang(String buyer_touxiang) {
		this.buyer_touxiang = buyer_touxiang;
	}

	public String getSeller_name() {
		return seller_name;
	}

	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}

	public String getSeller_touxiang() {
		return seller_touxiang;
	}

	public String getBuyer_qianming() {
		return buyer_qianming;
	}

	public void setBuyer_qianming(String buyer_qianming) {
		this.buyer_qianming = buyer_qianming;
	}

	public String getSeller_qianming() {
		return seller_qianming;
	}

	public void setSeller_qianming(String seller_qianming) {
		this.seller_qianming = seller_qianming;
	}

	public void setSeller_touxiang(String seller_touxiang) {
		this.seller_touxiang = seller_touxiang;
	}

	public String getTuikuan_message() {
		return tuikuan_message;
	}

	public void setTuikuan_message(String tuikuan_message) {
		this.tuikuan_message = tuikuan_message;
	}

}
