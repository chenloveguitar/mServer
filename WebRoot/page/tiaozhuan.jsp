<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("updete_id");
	String del_id = request.getParameter("del_id");
	request.setAttribute("updete_id", id);
	if (id != null) {
		request.getRequestDispatcher("/Update_Shuoshuo").forward(request, response);
	} else if (del_id != null) {
		request.getRequestDispatcher("/Handle_Shuoshuo").forward(request, response);

	}
%>