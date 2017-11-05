<%@page
	import="com.magicmoble.luzhouapp.server.server_function.Server_Func"%>
<%@page import="com.magicmoble.luzhouapp.model.server.Toutiao"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String Tag = request.getParameter("Tag");
	int tag = Integer.parseInt(Tag);

	List<Toutiao> list = null;
	if (tag == 1) {
		list = Server_Func.toutiao_NoExamine();
	} else if (tag == 2) {
		list = Server_Func.riji_NoExamine();
	} else if (tag == 3) {
		list = Server_Func.tuji_NoExamine();
	} else if (tag == 4) {
		list = Server_Func.faxian_NoExamine();
	} else if (tag == 5) {
		list = Server_Func.quchu_NoExamine();
	} else if (tag == 6) {
		list = Server_Func.guangjie_NoExamine();
	} else if (tag == 7) {
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

	request.setAttribute("list", list); //
	// response.sendRedirect("/mServer/page/Shuoshuo_management.jsp");
	request.getRequestDispatcher("Content_management_NoExamine.jsp").forward(request, response);
%>