package com.magicmoble.luzhouapp.server.ctrl;

import com.magicmoble.luzhouapp.server.server_function.Server_Func;
import com.magicmoble.luzhouapp.server.server_function.Server_Function;

import net.sf.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.magicmoble.luzhouapp.json.core.DataObject;
import com.magicmoble.luzhouapp.json.responseUtils.ResponseUtils;
import com.magicmoble.luzhouapp.json.status.StatusHouse;
import com.magicmoble.luzhouapp.json.utils.JackJsonUtils;
import com.magicmoble.luzhouapp.model.server.Shuoshuo;
import com.magicmoble.luzhouapp.model.server.Shuoshuo_xiangqing;
import com.magicmoble.luzhouapp.model.server.Toutiao;

public class Handle_NoExamine_neirong extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static void main(String[] args) {
		
		Server_Function.CURRENT_PAGE = 1;
		List<Toutiao> list = Server_Function.NoExamine("已推荐", 1);
		int totalSize = Server_Function.TOTAL_SIZE;
		int totalPage = Server_Function.TOTAL_PAGE;
		Map<String, Object> page = new HashMap<String,Object>();
		page.put("results", list);
		page.put("totalSize", totalSize);
		page.put("totalPage", totalPage);
		DataObject dataObject = new DataObject();
		dataObject.setdata(page);
		dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
		String responseText = JackJsonUtils.toJson(dataObject);
		System.out.println(responseText);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String change_rec = req.getParameter("change_rec");
		String change_hongbao = req.getParameter("change_hongbao");
		String _flag = req.getParameter("Tag");
		String change_dashang = req.getParameter("change_dashang");
		String guangjianzi_search = req.getParameter("guangjianzi_search");
		String change_paixu = req.getParameter("change_paixu");
		String chaxun_id = req.getParameter("chaxun_id");
		String currentPage = req.getParameter("currentPage");
		String pageSize = req.getParameter("pageSize");
		Server_Function.CURRENT_PAGE = Integer.valueOf(currentPage == null ? "1":currentPage);
		Server_Func.CURRENT_PAGE = Integer.valueOf(currentPage == null ? "1":currentPage);
		int flag = 0;
		if (_flag != null) {
			flag = Integer.parseInt(_flag);
		}
	
		if ("" != change_rec && change_rec != null) {

			List<Toutiao> list = Server_Function.NoExamine(change_rec, flag);
			int totalSize = Server_Function.TOTAL_SIZE;
			int totalPage = Server_Function.TOTAL_PAGE;
			Map<String, Object> page = new HashMap<String,Object>();
			page.put("results", list);
			page.put("totalSize", totalSize);
			page.put("totalPage", totalPage);
			DataObject dataObject = new DataObject();
			dataObject.setdata(page);
			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
			String responseText = JackJsonUtils.toJson(dataObject);
			ResponseUtils.renderJson(resp, responseText);
			// resp.getWriter().write(responseText);
		}

		if ("" != change_hongbao && change_hongbao != null) {

			List<Toutiao> list = Server_Function.NoExamine_hongbao(change_hongbao, flag);
			int totalSize = Server_Function.TOTAL_SIZE;
			int totalPage = Server_Function.TOTAL_PAGE;
			Map<String, Object> page = new HashMap<String,Object>();
			page.put("results", list);
			page.put("totalSize", totalSize);
			page.put("totalPage", totalPage);
			DataObject dataObject = new DataObject();
			dataObject.setdata(page);
			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
			String responseText = JackJsonUtils.toJson(dataObject);
			ResponseUtils.renderJson(resp, responseText);
			// resp.getWriter().write(responseText);
		}
		if ("" != change_dashang && change_dashang != null) {

			List<Toutiao> list = Server_Function.NoExamine_dashang(change_dashang, flag);
			int totalSize = Server_Function.TOTAL_SIZE;
			int totalPage = Server_Function.TOTAL_PAGE;
			Map<String, Object> page = new HashMap<String,Object>();
			page.put("results", list);
			page.put("totalSize", totalSize);
			page.put("totalPage", totalPage);
			DataObject dataObject = new DataObject();
			dataObject.setdata(page);
			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
			String responseText = JackJsonUtils.toJson(dataObject);
			ResponseUtils.renderJson(resp, responseText);
			// resp.getWriter().write(responseText);
		}
		if ("" != guangjianzi_search && guangjianzi_search != null) {

			List<Toutiao> list = Server_Function.NoExamine_search(guangjianzi_search, flag);
			int totalSize = Server_Function.TOTAL_SIZE;
			int totalPage = Server_Function.TOTAL_PAGE;
			Map<String, Object> page = new HashMap<String,Object>();
			page.put("results", list);
			page.put("totalSize", totalSize);
			page.put("totalPage", totalPage);
			DataObject dataObject = new DataObject();
			dataObject.setdata(page);
			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
			String responseText = JackJsonUtils.toJson(dataObject);
			ResponseUtils.renderJson(resp, responseText);
			// resp.getWriter().write(responseText);
		}
		if ("" != change_paixu && change_paixu != null) {
			List<Toutiao> list = Server_Function.NoExamine_paixu(change_paixu, flag);
			int totalSize = Server_Function.TOTAL_SIZE;
			int totalPage = Server_Function.TOTAL_PAGE;
			Map<String, Object> page = new HashMap<String,Object>();
			page.put("results", list);
			page.put("totalSize", totalSize);
			page.put("totalPage", totalPage);
			DataObject dataObject = new DataObject();
			dataObject.setdata(page);
			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
			String responseText = JackJsonUtils.toJson(dataObject);
			ResponseUtils.renderJson(resp, responseText);
			// resp.getWriter().write(responseText);
		}

		String del_id = req.getParameter("del_id");
		if ("" != del_id && del_id != null) {
			Server_Function.Delete_wenzhang(del_id, flag);
			List<Toutiao> list = null;
			if (flag == 1) {
				list = Server_Func.toutiao_NoExamine();
			} else if (flag == 2) {
				list = Server_Func.riji_NoExamine();
			} else if (flag == 3) {
				list = Server_Func.tuji_NoExamine();
			} else if (flag == 4) {
				list = Server_Func.faxian_NoExamine();
			} else if (flag == 5) {
				list = Server_Func.quchu_NoExamine();
			} else if (flag == 6) {
				list = Server_Func.guangjie_NoExamine();
			} else if (flag == 7) {
				List<Toutiao> list1 = Server_Func.toutiao_NoExamine();
				List<Toutiao> list2 = Server_Func.riji_NoExamine();
				List<Toutiao> list3 = Server_Func.tuji_NoExamine();
				List<Toutiao> list4 = Server_Func.faxian_NoExamine();
				List<Toutiao> list5 = Server_Func.quchu_NoExamine();
				List<Toutiao> list6 = Server_Func.guangjie_NoExamine();
				list = new ArrayList<Toutiao>();
				list.addAll(list1);
				list.addAll(list2);
				list.addAll(list3);
				list.addAll(list4);
				list.addAll(list5);
				list.addAll(list6);
			}

			req.setAttribute("list", list); //
			// response.sendRedirect("/mServer/page/Shuoshuo_management.jsp");
			req.getRequestDispatcher("page/Content_management_NoExamine.jsp").forward(req, resp);

		}

		String blur_rec = req.getParameter("blur_rec");
		if (blur_rec != null) {
			List<Shuoshuo> list = Server_Function.search(blur_rec);
			String responseText = JackJsonUtils.toJson(list);
			ResponseUtils.renderJson(resp, responseText); //
			resp.getWriter().write(responseText);
		}
		String sort_rec = req.getParameter("sort_rec");
		if (sort_rec != null) {
			List<Shuoshuo> list = Server_Function.sort(sort_rec, 1);
			String responseText = JackJsonUtils.toJson(list);
			ResponseUtils.renderJson(resp, responseText); // resp.getWriter().write(responseText);
		}
		
//		头条1,日记2,图片3,秘密4,去处5,逛街6,全部7
		List<Toutiao> list = null;
		switch(_flag){
			case "1":
				list = Server_Func.toutiao_NoExamine();
				break;
			case "2":
				list = Server_Func.riji_NoExamine();
				break;
			case "3":
				list = Server_Func.tuji_NoExamine();
				break;
			case "4":
				list = Server_Func.faxian_NoExamine();
				break;
			case "5":
				list = Server_Func.quchu_NoExamine();
				break;
			case "6":
				list = Server_Func.guangjie_NoExamine();
				break;
			case "7":
				//待定
				break;
		}
		Map<String, Object> page = new HashMap<String,Object>();
		int totalSize = Server_Func.TOTAL_SIZE;
		int totalPage = Server_Func.TOTAL_PAGE;
		page.put("results", list);
		page.put("totalSize", totalSize);
		page.put("totalPage", totalPage);
		DataObject dataObject = new DataObject();
		dataObject.setdata(page);
		dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
		String responseText = JackJsonUtils.toJson(dataObject);
		ResponseUtils.renderJson(resp, responseText);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

}
