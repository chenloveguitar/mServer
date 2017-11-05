package com.magicmoble.luzhouapp.action.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;

import com.magicmoble.luzhouapp.business.CommonBusiness;
import com.magicmoble.luzhouapp.constant.Constants;
import com.magicmoble.luzhouapp.json.core.DataObject;
import com.magicmoble.luzhouapp.json.responseUtils.ResponseUtils;
import com.magicmoble.luzhouapp.json.status.StatusHouse;
import com.magicmoble.luzhouapp.json.utils.JackJsonUtils;

@WebServlet("/FileDownLoadServlet")
public class FileDownLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) {
		try {
			String absolutePath = request.getParameter("absolutePath");
			String itemId = request.getParameter("itemId");
			if (StringUtils.isNotBlank(absolutePath)) {
				writeFile(response, absolutePath);
			}
			if(StringUtils.isNotBlank(itemId)){
				CommonBusiness commonBusiness = new CommonBusiness();
				List<String> paths = commonBusiness.getAbsolutePathsByItemId(itemId);
				if(paths.size() == 0){
					paths = new ArrayList<String>();
					paths.add("187728108884091077.png");
				}
				writeFile(response, paths.get(0));
			}
		} catch (Exception e) {
			DataObject dataObject = new DataObject();
			dataObject.setMsg("文件不存在!");
			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
			String responseText = JackJsonUtils.toJson(dataObject);
			ResponseUtils.renderJson(response, responseText);
		}
	}
	public void writeFile(HttpServletResponse response,String path){
		try {
			ServletOutputStream outputStream = response.getOutputStream();
			File file = new File(Constants.uploadPath + path);
			if (!file.exists()) {
				DataObject dataObject = new DataObject();
				dataObject.setMsg("文件不存在!");
				dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
				String responseText = JackJsonUtils.toJson(dataObject);
				ResponseUtils.renderJson(response, responseText);
			}
			FileInputStream input = new FileInputStream(file);
			int length = -1;
			byte[] b = new byte[1024];
			while ((length = input.read(b)) != -1) {
				outputStream.write(b, 0, length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
