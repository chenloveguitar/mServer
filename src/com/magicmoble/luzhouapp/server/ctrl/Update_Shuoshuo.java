package com.magicmoble.luzhouapp.server.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.magicmoble.luzhouapp.model.server.Shuoshuo_xiangqing;
import com.magicmoble.luzhouapp.server.server_function.Server_Func;

public class Update_Shuoshuo extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String updete_id = request.getParameter("updete_id");
		if ("" != updete_id && updete_id != null) {
			List<Shuoshuo_xiangqing> list = Server_Func.getShuoshuo_Info(updete_id);
			request.setAttribute("list", list);
			request.setAttribute("updete_id", updete_id);
			request.getRequestDispatcher("/page/update_Shuoshuo.jsp").forward(request, response);
		}
	}

}
