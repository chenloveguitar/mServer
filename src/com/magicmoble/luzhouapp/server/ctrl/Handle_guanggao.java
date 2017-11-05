package com.magicmoble.luzhouapp.server.ctrl;

import com.magicmoble.luzhouapp.server.server_function.Server_Func;
import com.magicmoble.luzhouapp.server.server_function.Server_Function;

import net.sf.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.magicmoble.luzhouapp.json.responseUtils.ResponseUtils;
import com.magicmoble.luzhouapp.json.utils.JackJsonUtils;
import com.magicmoble.luzhouapp.json.utils.UploadPicture;
import com.magicmoble.luzhouapp.model.server.Shuoshuo;
import com.magicmoble.luzhouapp.model.server.Shuoshuo_xiangqing;
import com.magicmoble.luzhouapp.model.server.User_model;

public class Handle_guanggao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] banner = req.getParameterValues("banner");
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		for (int i = 0; i < banner.length; i++) {
			Map<String, String> map = new HashMap<String, String>();
			System.out.println(banner[i]);

			if (i % 4 == 0) {
				map.put("picture", banner[i]);
			} else if (i % 4 == 1) {
				map.put("select1", banner[i]);
			} else if (i % 4 == 2) {
				map.put("select2", banner[i]);
			} else if (i % 4 == 3) {
				map.put("url", banner[i]);
			}

			list.add(map);
		}
		for (

		int j = 0; j < list.size(); j++)

		{
			String[] a = list.get(j).get("picture").split(",");
			String img_base64 = a[1].substring(0, a[1].length() - 1);
			String dataString = new Date().getTime() + "" + (Math.round(Math.random() * 10000)) + ".jpeg";
			String path = req.getRealPath("/upload/textpicture");
			String ServicePath = path + "/" + dataString;
			UploadPicture.GenerateImage(img_base64, ServicePath);
			String url = "http://120.92.169.86/mServer/upload/textpicture/" + dataString;
			Server_Function.add_guanggao(url, list.get(j).get("select1"), list.get(j).get("select2"), url);
		}
		req.getRequestDispatcher("page/Advertisement_management.jsp").forward(req, resp);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

}
