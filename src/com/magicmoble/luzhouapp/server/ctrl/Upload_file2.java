package com.magicmoble.luzhouapp.server.ctrl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;

import com.magicmoble.luzhouapp.json.core.DataObject;
import com.magicmoble.luzhouapp.json.responseUtils.ResponseUtils;
import com.magicmoble.luzhouapp.json.status.StatusHouse;
import com.magicmoble.luzhouapp.json.utils.JackJsonUtils;
import com.magicmoble.luzhouapp.json.utils.UploadPicture;
import com.magicmoble.luzhouapp.server.server_function.Server_Func;
import com.magicmoble.luzhouapp.server.server_function.Server_Function;
import com.mysql.jdbc.Blob;

import ognl.ArrayPropertyAccessor;
/**
 * 信息新增
 * @author jumili
 *
 */
public class Upload_file2 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String str = "";
	int muban_Tag;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String select_val = request.getParameter("select-val");
		String title = request.getParameter("title");
		String user_name = request.getParameter("releaser_name");
		String _yuedu_count = request.getParameter("yuedu_count");
		String _dianzan_count = request.getParameter("dianzan_count");
		String _muban_Tag = request.getParameter("muban_Tag");
		int yuedu_count = 0;
		int dianzan_count = 0;
		int muban_Tag = 0;
		//阅读量
		if(StringUtils.isNotBlank(_yuedu_count)){
			yuedu_count = Integer.parseInt(_yuedu_count);
		}
		//点赞量
		if(StringUtils.isNotBlank(_dianzan_count)){
			muban_Tag = dianzan_count = Integer.parseInt(_dianzan_count);
		}
		//模板
		if(StringUtils.isNotBlank(_muban_Tag)){
			Integer.parseInt(_muban_Tag);
		}
		String releaser_id = request.getParameter("releaser_id");
		String price_fuwu = request.getParameter("price_fuwu");
		String price_commodity = request.getParameter("price_commodity");
		String dianpu_id = request.getParameter("dianpu_id");
		String shuliang = request.getParameter("shuliang");
		String freight = request.getParameter("freight");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");

//		String[] picture = request.getParameterValues("picture");
//		String[] content1 = request.getParameterValues("content1");
//		String content2 = request.getParameter("content2");

		
		// 内容1
//		List<String> list = new ArrayList<String>();
//		for (int i = 0; i < content1.length; i++) {
//			if (i % 2 == 0) {
//				String[] a = content1[i].split(",");
//				String img_base64 = a[1];
//				String dataString = new Date().getTime() + "" + (Math.round(Math.random() * 10000)) + ".jpeg";
//				String path = request.getRealPath("/upload/textpicture");
//				String ServicePath = path + "/" + dataString;
//				UploadPicture.GenerateImage(img_base64, ServicePath);
//				String url = "http://120.92.169.86/mServer/upload/textpicture/" + dataString;
//				list.add(url);
//			} else {
//				list.add(content1[i]);
//			}
//		}
		String content1_str = "";
//		for (int i = 0; i < list.size(); i++) {
//			content1_str += list.get(i) + "<--分隔符-->";
//		}
		// 内容2
//		List<String> list2 = new ArrayList<String>();
//		StringTokenizer token = new StringTokenizer(content2, "<>");// 按照( ) +
//		String[] array = new String[100];// 定义一个字符串数组
//		int i = 0;
//		while (token.hasMoreTokens()) {
//			array[i] = token.nextToken();// 将分割开的子字符串放入数组中
//			i++;
//		}
//		for (int j = 0; j < i; j++) {
//			if (array[j].substring(0, 3).equals("img")) {
//				String[] a = array[j].split(",");
//				String img_base64 = a[1].substring(0, a[1].length() - 1);
//				String dataString = new Date().getTime() + "" + (Math.round(Math.random() * 10000)) + ".jpeg";
//				String path = request.getRealPath("/upload/textpicture");
//				String ServicePath = path + "/" + dataString;
//				UploadPicture.GenerateImage(img_base64, ServicePath);
//				String url = "http://120.92.169.86/mServer/upload/textpicture/" + dataString;
//				list2.add(url);
//
//			} else {
//				list2.add(array[j]);
//			}
//		}
		String content2_str = "";
//		for (int j = 0; j < list2.size(); j++) {
//			content2_str += list2.get(j) + "<--分隔符-->";
//		}
		String content_all = content1_str + content2_str;

		// 图片
		String picture_str = "";
//		for (int m = 0; m < picture.length; m++) {
//			String[] picture2 = picture[m].split(",");
//			if (picture != null) {
//				String img_base64 = picture2[1];
//				String dataString = new Date().getTime() + "" + (Math.round(Math.random() * 10000)) + ".jpeg";
//				String path = request.getRealPath("/upload/picture");
//
//				String ServicePath = path + "/" + dataString;
//				UploadPicture.GenerateImage(img_base64, ServicePath);
//
//				picture_str += "http://120.92.169.86/mServer/upload/picture/" + dataString + ",";
//
//			}
//		}
		String id = null;
		if (select_val.equals("今日头条")) {
			 id = Server_Function.add_toutiao(picture_str, title, user_name, content_all, muban_Tag, releaser_id, yuedu_count,dianzan_count);
		} else if (select_val.equals("酒城日记")) {
			 id = Server_Function.add_riji(picture_str, title, user_name, content_all, muban_Tag, releaser_id);
		} else if (select_val.equals("发现秘密")) {
			 id = Server_Function.add_faxian(releaser_id, picture_str, title, user_name, content_all);
		} else if (select_val.equals("有去处")) {
			 id = Server_Function.add_quchu(releaser_id, title, address, phone, picture_str, content_all);
		} else if (select_val.equals("商品")) {
			 id =  Server_Function.add_commodity(title, price_commodity, shuliang, freight, phone, picture_str, content_all,releaser_id);
		} else if (select_val.equals("服务")) {
			 id =  Server_Function.add_fuwu(releaser_id, title, price_fuwu, phone, picture_str, content_all);
		}
		
		if(StringUtils.isNotBlank(id)){
			DataObject dataObject = new DataObject();
			dataObject.setdata(id);
			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
			String responseText = JackJsonUtils.toJson(dataObject);
			ResponseUtils.renderJson(response, responseText);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}
}
