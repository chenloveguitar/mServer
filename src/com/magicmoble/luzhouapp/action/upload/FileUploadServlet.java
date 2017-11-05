package com.magicmoble.luzhouapp.action.upload;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.magicmoble.luzhouapp.business.CommonBusiness;
import com.magicmoble.luzhouapp.json.core.DataObject;
import com.magicmoble.luzhouapp.json.responseUtils.ResponseUtils;
import com.magicmoble.luzhouapp.json.status.StatusHouse;
import com.magicmoble.luzhouapp.json.utils.JackJsonUtils;
import com.magicmoble.luzhouapp.model.FileManagement;
import com.magicmoble.luzhouapp.utils.FileUploadUtil;

@WebServlet("/FileUploadServlet")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parentId = request.getParameter("parentId");
		String deleteds = request.getParameter("deleteds");
		String itemId = request.getParameter("itemId");
		parentId = StringUtils.isNotBlank(parentId) ? parentId : null;
		if(StringUtils.isNotBlank(parentId)){
			FileUploadUtil uploadUtil = new FileUploadUtil(request).upload(deleteds);
			List<String[]> listPath = uploadUtil.getListPath();
			CommonBusiness business = new CommonBusiness();
			List<FileManagement> fileManagements = new ArrayList<FileManagement>();
			//创建时间
			String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			//类型为文件
			Integer isFolder = 0;
			for (int i = 0; i < listPath.size(); i++) {
				String[] fileInfo = listPath.get(i);
				Integer pid = StringUtils.isNotBlank(parentId) && !parentId.equals("null") ? Integer.valueOf(parentId) : -1;
				FileManagement file = new FileManagement(fileInfo[0], createTime,pid , isFolder,fileInfo[1],itemId);
				fileManagements.add(file);
			}
			List<Integer> list = business.createImages(fileManagements);
			DataObject dataObject = new DataObject();
			dataObject.setdata(list);
			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
			String responseText = JackJsonUtils.toJson(dataObject);
			ResponseUtils.renderJson(response, responseText);
		}
		
	}

}
