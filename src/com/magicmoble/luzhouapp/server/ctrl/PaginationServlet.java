package com.magicmoble.luzhouapp.server.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.magicmoble.luzhouapp.model.server.Shuoshuo;
import com.magicmoble.luzhouapp.server.server_function.Server_Function;

public class PaginationServlet extends HttpServlet {

	private int currentpageno=1;
	
	private int realcurrentpageno=1;
	
    private int pagecount;
	
	public PaginationServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); 
		
	}

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pagecountStr=request.getParameter("pagecount");
		System.out.println("起始位置："+pagecountStr);
		if(pagecountStr!=null){
			pagecount=Integer.parseInt(pagecountStr);
			System.out.println("pagecount:"+pagecount);
		}
		
		request.setAttribute("pagecount", pagecount);
		
		int totalpage=Server_Function.getTotalPage(pagecount);
		request.getSession().setAttribute("totalpage", totalpage);
		
		String pagenoStr=request.getParameter("pageno");
		
		if(pagenoStr==null||"".equals(pagenoStr)){
			pagenoStr="1";
		}
		
		int pageno;
		try {
			//如果pagenoStr不能转成int类型,那么就会抛出NumberFormatException异常
			pageno = Integer.parseInt(pagenoStr);
		} catch (NumberFormatException e) {
			pageno=1;
		}
		
		//如果传递个pageno小于0，默认显示第一页
		if(pageno<1){
			pageno=1;
		}
		
		//如果传递个pageno大于totalpage，默认显示最后一页
		if(pageno>=totalpage){
			pageno=totalpage;
		}
		
		//当前页
		realcurrentpageno=pageno;
		request.setAttribute("realcurrentpageno", realcurrentpageno);
		if(pageno<100){
			//currentpageno用于控制显示分页的链接
			currentpageno=pageno;
		}
		
		request.setAttribute("currentpageno", currentpageno);
		
		List<Shuoshuo> list=Server_Function.limitShuoshuo_ser(pageno, pagecount);
		
		request.getSession().setAttribute("limitlist", list);
		
		response.sendRedirect("http://localhost:8080/mServer/page/Shuoshuo_management.jsp");
	}

	public void init() throws ServletException {
		//每页显示的说说条数
		pagecount=15;
	}

}
