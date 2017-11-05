package com.magicmoble.luzhouapp.server.ctrl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.magicmoble.luzhouapp.server.server_function.Server_Func;
import com.magicmoble.luzhouapp.server.server_function.Server_Function;

public class Upload_file extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String str = "";
	int muban_Tag;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String update_id = request.getParameter("update_id");

		String _Tag = request.getParameter("Tag");
		System.out.println(_Tag);
		int Tag = 0;
		int tuijian_Tag;
		Tag = Integer.parseInt(_Tag);
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// String fileName = new Date().getTime() + "" +
		// (Math.round(Math.random() * 10000)) + "" + ".jpeg";
		// Part part = req.getPart("filepath");
		// System.out.println(part);
		// String path = req.getServletContext().getRealPath("/upload/picture");
		//
		// part.write(path + "/" + fileName);

		request.setCharacterEncoding("utf-8"); // 设置编码

		// 获得磁盘文件条目工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 获取文件需要上传到的路径
		String path = request.getRealPath("/upload/picture");
		Map<String, String> map = new HashMap<String, String>();
		// String path = "c:/upload1";
		// 如果没以下两行设置的话，上传大的 文件 会占用 很多内存，
		// 设置暂时存放的 存储室 , 这个存储室，可以和 最终存储文件 的目录不同
		/**
		 * 原理 它是先存到 暂时存储室，然后在真正写到 对应目录的硬盘上， 按理来说 当上传一个文件时，其实是上传了两份，第一个是以 .tem
		 * 格式的 然后再将其真正写到 对应目录的硬盘上
		 */
		factory.setRepository(new File(path));
		// 设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室
		factory.setSizeThreshold(1024 * 1024);
		// 高水平的API文件上传处理
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			// 可以上传多个文件
			if (Tag == 1) {

				List<FileItem> list = (List<FileItem>) upload.parseRequest(request);
				for (FileItem item : list) {
					// 获取表单的属性名字
					String name = item.getFieldName();

					// 如果获取的 表单信息是普通的 文本 信息
					if (item.isFormField()) {
						// 获取用户具体输入的字符串 ，名字起得挺好，因为表单提交过来的是 字符串类型的
						String value = item.getString();

						String _value = new String(value.getBytes("ISO-8859-1"), "utf-8");
						System.out.println(name);
						System.out.println(_value);
						map.put(name, _value);

						request.setAttribute(name, _value);
					} else {// 对传入的非 简单的字符串进行处理 ，比如说二进制的 图片，电影这些
						/**
						 * 以下三步，主要获取 上传文件的名字
						 */
						// 获取路径名
						String value = item.getName();
						// 索引到最后一个反斜杠
						int start = value.lastIndexOf("\\");
						// 截取 上传文件的 字符串名字，加1是 去掉反斜杠，
						String filename = new Date().getTime() + "" + (Math.round(Math.random() * 10000)) + ""
								+ ".jpeg";
						request.setAttribute(name, filename);
						// 真正写到磁盘上
						// 它抛出的异常 用exception 捕捉
						// item.write( new File(path,filename) );//第三方提供的
						// 手动写的
						OutputStream out = new FileOutputStream(new File(path, filename));
						InputStream in = item.getInputStream();
						int length = 0;
						byte[] buf = new byte[1024];
						// in.read(buf) 每次读到的数据存放在 buf 数组中
						while ((length = in.read(buf)) != -1) {
							// 在 buf 数组中 取出数据 写到 （输出流）磁盘上
							out.write(buf, 0, length);
						}
						in.close();
						out.close();
						str += "http://120.92.169.86/mServer/upload/picture/" + filename + ",";

					}

				}

				map.put("picture", str);
				Set<String> set = map.keySet();
				for (String key : set) {

					Server_Func.shuoshuo_update(update_id, key, map.get(key));

				}
			} else if (Tag == 2) {

				List<FileItem> list = (List<FileItem>) upload.parseRequest(request);
				for (FileItem item : list) {
					// 获取表单的属性名字
					String name = item.getFieldName();

					// 如果获取的 表单信息是普通的 文本 信息
					if (item.isFormField()) {
						// 获取用户具体输入的字符串 ，名字起得挺好，因为表单提交过来的是 字符串类型的
						String value = item.getString();

						String _value = new String(value.getBytes("ISO-8859-1"), "utf-8");
						System.out.println(name);
						System.out.println(_value);
						map.put(name, _value);

						request.setAttribute(name, _value);
					} else {// 对传入的非 简单的字符串进行处理 ，比如说二进制的 图片，电影这些
						/**
						 * 以下三步，主要获取 上传文件的名字
						 */
						// 获取路径名
						String value = item.getName();
						// 索引到最后一个反斜杠
						int start = value.lastIndexOf("\\");
						// 截取 上传文件的 字符串名字，加1是 去掉反斜杠，
						String filename = new Date().getTime() + "" + (Math.round(Math.random() * 10000)) + ""
								+ ".jpeg";
						request.setAttribute(name, filename);
						// 真正写到磁盘上
						// 它抛出的异常 用exception 捕捉
						// item.write( new File(path,filename) );//第三方提供的
						// 手动写的
						OutputStream out = new FileOutputStream(new File(path, filename));
						InputStream in = item.getInputStream();
						int length = 0;
						byte[] buf = new byte[1024];
						// in.read(buf) 每次读到的数据存放在 buf 数组中
						while ((length = in.read(buf)) != -1) {
							// 在 buf 数组中 取出数据 写到 （输出流）磁盘上
							out.write(buf, 0, length);
						}
						in.close();
						out.close();
						str += "http://120.92.169.86/mServer/upload/picture/" + filename + ",";
					}

				}
				String[] a = str.split(",");
				if (a.length > 1) {
					muban_Tag = 2;
				} else {
					muban_Tag = 1;
				}
				map.put("picture", str);
				if (map.get("is-tuijian").equals("未推荐")) {
					tuijian_Tag = 0;
				} else if (map.get("is-tuijian").equals("已推荐")) {
					tuijian_Tag = 1;
				} else {
					tuijian_Tag = 0;
				}

				Server_Func.addShuoshuo(map.get("picture"), map.get("shuoshuo-content"), map.get("releaser_id"),
						muban_Tag, tuijian_Tag, Integer.parseInt(map.get("yuedu-count")),
						Integer.parseInt(map.get("dianzan-count")));
				response.sendRedirect("page/Shuoshuo_management.jsp");

			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}
}
