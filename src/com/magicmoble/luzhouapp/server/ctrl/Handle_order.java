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

import com.magicmoble.luzhouapp.business.Dingdan_Business;
import com.magicmoble.luzhouapp.business.FunctionBusiness;
import com.magicmoble.luzhouapp.json.responseUtils.ResponseUtils;
import com.magicmoble.luzhouapp.json.utils.JackJsonUtils;
import com.magicmoble.luzhouapp.model.Order;
import com.magicmoble.luzhouapp.model.server.Shuoshuo;
import com.magicmoble.luzhouapp.model.server.Shuoshuo_xiangqing;

public class Handle_order extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String del_id = req.getParameter("del_id");
		if ("" != del_id && del_id != null) {
			boolean isDelete = Server_Func.deleteShuoshuo(del_id);

			List<Shuoshuo_xiangqing> list = Server_Func.limitShuoshuo_ser(1, 15);
			req.setAttribute("list", list); //
			// response.sendRedirect("/mServer/page/Shuoshuo_management.jsp");
			req.getRequestDispatcher("/page/order-detail.jsp").forward(req, resp);

		}
		String order_id = req.getParameter("order_id");
		String _guangjie_fenlei_Tag = req.getParameter("guangjie_fenlei_Tag");
		int guangjie_fenlei_Tag = Integer.parseInt(_guangjie_fenlei_Tag);
		if ("" != order_id && order_id != null) {
			Order order = Dingdan_Business.order_click(order_id, "1", guangjie_fenlei_Tag);

			req.setAttribute("list", order); //
			// response.sendRedirect("/mServer/page/Shuoshuo_management.jsp");
			req.getRequestDispatcher("/page/order-detail.jsp").forward(req, resp);

		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

}
