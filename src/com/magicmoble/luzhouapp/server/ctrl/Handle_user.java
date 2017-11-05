package com.magicmoble.luzhouapp.server.ctrl;

import com.magicmoble.luzhouapp.server.server_function.Server_Func;
import com.magicmoble.luzhouapp.server.server_function.Server_Function;

import net.sf.json.JSONArray;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.magicmoble.luzhouapp.json.responseUtils.ResponseUtils;
import com.magicmoble.luzhouapp.json.utils.JackJsonUtils;
import com.magicmoble.luzhouapp.model.server.Shuoshuo;
import com.magicmoble.luzhouapp.model.server.Shuoshuo_xiangqing;
import com.magicmoble.luzhouapp.model.server.User_model;

public class Handle_user extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sex_change = req.getParameter("sex-change");
		String Tag = req.getParameter("Tag");
		if ("" != sex_change && sex_change != null) {
			List<User_model> list = Server_Function.sex_change(sex_change,Tag);
			System.out.println("2222222222222222222");
			String responseText = JackJsonUtils.toJson(list);
			 resp.getWriter().write(responseText);
			//resp.sendRedirect("/mServer/page/Shuoshuo_management.jsp");

		}

		String del_id = req.getParameter("del_id");
		if ("" != del_id && del_id != null) {
			boolean isDelete = Server_Func.deleteShuoshuo(del_id);

			List<Shuoshuo_xiangqing> list = Server_Func.limitShuoshuo_ser(1, 15);
			req.setAttribute("list", list); //
			// response.sendRedirect("/mServer/page/Shuoshuo_management.jsp");
			req.getRequestDispatcher("/page/Shuoshuo_management.jsp").forward(req, resp);

		}
		String blur_rec = req.getParameter("guanjianzi_search");
		if (blur_rec != null) {
			List<User_model> list = Server_Function.search_User(blur_rec,Tag);
			System.out.println("2222222222222222222");
			String responseText = JackJsonUtils.toJson(list);
			
			ResponseUtils.renderJson(resp, responseText); 
			//resp.getWriter().write(responseText);
		}
		String sort_rec = req.getParameter("sort_rec");
		if (sort_rec != null) {
			List<Shuoshuo> list = Server_Function.sort(sort_rec, 1);
			String responseText = JackJsonUtils.toJson(list);
			ResponseUtils.renderJson(resp, responseText);
			// resp.getWriter().write(responseText);
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

}
