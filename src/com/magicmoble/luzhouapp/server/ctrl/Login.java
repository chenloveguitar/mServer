package com.magicmoble.luzhouapp.server.ctrl;

import com.magicmoble.luzhouapp.server.server_function.Server_Func;
import com.magicmoble.luzhouapp.server.server_function.Server_Function;

import net.sf.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.magicmoble.luzhouapp.business.LoginBusiness;
import com.magicmoble.luzhouapp.json.core.DataObject;
import com.magicmoble.luzhouapp.json.core.ListObject;
import com.magicmoble.luzhouapp.json.responseUtils.ResponseUtils;
import com.magicmoble.luzhouapp.json.status.StatusHouse;
import com.magicmoble.luzhouapp.json.utils.JackJsonUtils;
import com.magicmoble.luzhouapp.model.Admin_xinxi;
import com.magicmoble.luzhouapp.model.server.Shuoshuo;
import com.magicmoble.luzhouapp.model.server.Shuoshuo_xiangqing;
import com.magicmoble.luzhouapp.model.server.User_model;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String admin_user = req.getParameter("admin_user");
		String password = req.getParameter("password");
		List<com.magicmoble.luzhouapp.model.server.Login> list = Server_Function.getAllLogin();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getPhone().toString().equals(admin_user)) {
				if (list.get(i).getPassword().toString().equals(password)) {

					if (list.get(i).getAdmin_leixing().equals("1")) {
						req.setAttribute("str", "超级管理员");
					} else if (list.get(i).getAdmin_leixing().equals("2")) {
						req.setAttribute("str", "编辑");
					} else if (list.get(i).getAdmin_leixing().equals("3")) {
						req.setAttribute("str", "财务");
					}

					req.getRequestDispatcher("page/index.jsp").forward(req, resp);

				} else {
					resp.sendRedirect("page/login.jsp");
				}
			} else {
				resp.sendRedirect("page/login.jsp");
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

}
