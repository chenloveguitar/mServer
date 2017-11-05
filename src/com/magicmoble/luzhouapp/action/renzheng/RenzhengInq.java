package com.magicmoble.luzhouapp.action.renzheng;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.magicmoble.luzhouapp.business.LoginBusiness;
import com.magicmoble.luzhouapp.business.RenzhengBusiness;
import com.magicmoble.luzhouapp.json.core.DataObject;
import com.magicmoble.luzhouapp.json.core.ListObject;
import com.magicmoble.luzhouapp.json.responseUtils.ResponseUtils;
import com.magicmoble.luzhouapp.json.status.StatusHouse;
import com.magicmoble.luzhouapp.json.utils.JackJsonUtils;
import com.magicmoble.luzhouapp.json.utils.UploadPicture;
import com.magicmoble.luzhouapp.model.Admin_xinxi;
import com.magicmoble.luzhouapp.model.Login;
import com.magicmoble.luzhouapp.model.Tou_Picture;
import com.magicmoble.luzhouapp.singleLogin.SingleUtil;

public class RenzhengInq extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String my_id = request.getParameter("my_id");
		String picture = request.getParameter("picture");
		String name = request.getParameter("name");
		String message = request.getParameter("message");
		String renzheng_Tag = request.getParameter("renzheng_Tag");// 1
																	// ；个人认证2。店铺认证
		int _renzheng_Tag = 0;
		if (renzheng_Tag != null) {
			_renzheng_Tag = Integer.parseInt(renzheng_Tag);
		}

		try {
			if (picture == null || name == null || message == null || renzheng_Tag == null) {
				DataObject dataObject = new DataObject();
				dataObject.setdata("数据有误，请查看数据完整");
				dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR_PASSWORD_OR_PHONE);
				String responseText = JackJsonUtils.toJson(dataObject);
				ResponseUtils.renderJson(response, responseText);
			} else {
				Gson gson = new Gson();
				Tou_Picture picture2 = gson.fromJson(picture, Tou_Picture.class);

				if (picture != null) {
					picture = "";
					String img_base64 = picture2.getBase64Picture();
					String dataString = new Date().getTime() + "" + (Math.round(Math.random() * 10000)) + ".jpeg";
					String path = request.getRealPath("/upload/touxiang");
					String ServicePath = path + "/" + dataString;
					UploadPicture.GenerateImage(img_base64, ServicePath);

					picture += "http://120.92.169.86/mServer/upload/touxiang/" + dataString + ",";
				}

				RenzhengBusiness.renzheng(my_id, picture, name, message, _renzheng_Tag);
				DataObject dataObject = new DataObject();
				dataObject.setdata("认证信息提交成功，请等待审核");
				dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
				String responseText = JackJsonUtils.toJson(dataObject);
				ResponseUtils.renderJson(response, responseText);
			}
		}
		// catch (NullPointerException e) {
		//
		// DataObject dataObject = new DataObject();
		// dataObject.setdata(new String("请输入帐号或密码"));
		// dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR_PASSWORD_OR_PHONE);
		// String responseText = JackJsonUtils.toJson(dataObject);
		// ResponseUtils.renderJson(response, responseText);
		// }
		catch (ArrayIndexOutOfBoundsException e) {
			DataObject dataObject = new DataObject();
			dataObject.setdata("json参数错误");
			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
			String responseText = JackJsonUtils.toJson(dataObject);

			ResponseUtils.renderJson(response, responseText);
		} catch (Exception e) {

			DataObject dataObject = new DataObject();
			dataObject.setdata(e);
			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
			String responseText = JackJsonUtils.toJson(dataObject);

			ResponseUtils.renderJson(response, responseText);
		}
	}
}
