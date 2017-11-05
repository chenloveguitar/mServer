package com.magicmoble.luzhouapp.action.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.magicmoble.luzhouapp.business.CommonBusiness;
import com.magicmoble.luzhouapp.business.FunctionBusiness;
import com.magicmoble.luzhouapp.json.core.DataObject;
import com.magicmoble.luzhouapp.json.core.ListObject;
import com.magicmoble.luzhouapp.json.responseUtils.ResponseUtils;
import com.magicmoble.luzhouapp.json.status.StatusHouse;
import com.magicmoble.luzhouapp.json.utils.JackJsonUtils;
import com.magicmoble.luzhouapp.model.Admin_xinxi;
import com.magicmoble.luzhouapp.model.FileManagement;
import com.magicmoble.luzhouapp.model.Pinglun;
import com.magicmoble.luzhouapp.model.server.Toutiao;
import com.magicmoble.luzhouapp.server.server_function.Server_Function;

@WebServlet("/CommonServlet")
public class CommonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommonBusiness commonBusiness = new CommonBusiness();
		String type = request.getParameter("type");
		Map<String,String> paramMap = new HashMap<String,String>();
		Enumeration<String> names = request.getParameterNames();
		while(names.hasMoreElements()){
			String name = names.nextElement();
			String value = request.getParameter(name);
			paramMap.put(name, value);
		}
		
		if(type != null){
			//获取所有用户
			if(type.equals("getUsers")){
				List<Admin_xinxi> list = commonBusiness.getUsers();
				ListObject listObject = new ListObject();
				listObject.setResult(list);
				DataObject dataObject = new DataObject();
				dataObject.setdata(listObject);
				dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
				String responseText = JackJsonUtils.toJson(dataObject);
				ResponseUtils.renderJson(response, responseText);
			}else if(type.equals("createFolder")){//创建文件夹
				int id = commonBusiness.createNewFolder(paramMap);
				DataObject dataObject = new DataObject();
				dataObject.setdata(id);
				if(id != -1){
					dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
				}else{
					dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
				}
				String responseText = JackJsonUtils.toJson(dataObject);
				ResponseUtils.renderJson(response, responseText);
			}else if(type.equals("getFolders")){//获取文件夹
				List<FileManagement> list = commonBusiness.getFolders();
				ListObject listObject = new ListObject();
				listObject.setResult(list);
				DataObject dataObject = new DataObject();
				dataObject.setdata(listObject);
				dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
				String responseText = JackJsonUtils.toJson(dataObject);
				ResponseUtils.renderJson(response, responseText);
			}else if(type.equals("deleteFolder")){//删除指定文件夹
				String id = paramMap.get("id");
				String[] ids = id.split(",");
				boolean deleted = commonBusiness.deleteFolder(ids);
				DataObject dataObject = new DataObject();
				dataObject.setdata(deleted);
				dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
				String responseText = JackJsonUtils.toJson(dataObject);
				ResponseUtils.renderJson(response, responseText);
			}else if(type.equals("renameFolder")){//重命名文件夹
				String id = paramMap.get("id");
				String folderName = paramMap.get("folderName");
				boolean renamed = commonBusiness.renameFolder(Integer.valueOf(id),folderName);
				DataObject dataObject = new DataObject();
				dataObject.setdata(renamed);
				dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
				String responseText = JackJsonUtils.toJson(dataObject);
				ResponseUtils.renderJson(response, responseText);
			}else if(type.equals("getImages")){
				String parentId = paramMap.get("parentId");
				List<FileManagement> images = commonBusiness.getImages(StringUtils.isNotBlank(parentId) ? Integer.valueOf(parentId) : null);
				DataObject dataObject = new DataObject();
				dataObject.setdata(images);
				dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
				String responseText = JackJsonUtils.toJson(dataObject);
				ResponseUtils.renderJson(response, responseText);
			}else if(type.equals("deleteImage")){
				String id = paramMap.get("id");
				String[] ids = {id};
				boolean deleted = commonBusiness.deleteFolder(ids);
				DataObject dataObject = new DataObject();
				dataObject.setdata(deleted);
				dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
				String responseText = JackJsonUtils.toJson(dataObject);
				ResponseUtils.renderJson(response, responseText);
			}else if(type.equals("renameImage")){
				String id = paramMap.get("id");
				String imageName = paramMap.get("imageName");
				boolean renamed = commonBusiness.renameFolder(Integer.valueOf(id),imageName);
				DataObject dataObject = new DataObject();
				dataObject.setdata(renamed);
				dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
				String responseText = JackJsonUtils.toJson(dataObject);
				ResponseUtils.renderJson(response, responseText);
			}else if(type.equals("searchFiles")){
				String searchType = paramMap.get("searchType");
				String searchValue = paramMap.get("searchValue");
				String searchPath = paramMap.get("searchPath");
				Map<String, String> searchMap = new HashMap<String,String>();
				searchMap.put("createDate", null);
				searchMap.put("fileName", null);
				searchMap.put("sort", null);
				searchMap.put("searchPath", searchPath);
				switch(searchType){
					case "sort":
						searchMap.put("sort", searchValue);
						break;
					case "searchDate":
						searchMap.put("createDate", searchValue);
						break;
					case "searchWords":
						searchMap.put("fileName", searchValue);
						break;
				}
				List<FileManagement> fileManagements = commonBusiness.searchFiles(searchMap);
				DataObject dataObject = new DataObject();
				dataObject.setdata(fileManagements);
				dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
				String responseText = JackJsonUtils.toJson(dataObject);
				ResponseUtils.renderJson(response, responseText);
			}else if(type.equals("imageDataRel")){
				String id = paramMap.get("id");
				String[] ids = {};
				if(StringUtils.isNotBlank(id)){
					ids = id.split(",");
				}
				String itemId = paramMap.get("itemId");
				List<FileManagement> managements = commonBusiness.getListByIds(ids);
				List<FileManagement> fileManagements = new ArrayList<FileManagement>();
				for (FileManagement fileManagement : managements) {
					fileManagement.setId(null);
					fileManagement.setItemId(itemId);
					fileManagement.setParentId(-1);
					fileManagements.add(fileManagement);
				}
				List<Integer> createdIds = commonBusiness.createImages(fileManagements);
				DataObject dataObject = new DataObject();
				dataObject.setdata(createdIds);
				dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
				String responseText = JackJsonUtils.toJson(dataObject);
				ResponseUtils.renderJson(response, responseText);
			}else if(type.equals("editMessage")){
				String chaxun_id =  paramMap.get("chaxun_id");
				String _Tag = paramMap.get("Tag");
				_Tag = StringUtils.isNotBlank(_Tag)?_Tag:"0";
				int Tag = Integer.parseInt(_Tag);
				Toutiao detail = Server_Function.chaxun(chaxun_id, Tag);
				List<Pinglun> comments = FunctionBusiness.getPinglun("1", detail.getId(), 0);
				Map<String, Object> result = new HashMap<String,Object>();
				result.put("detail", detail);//信息内容
				result.put("comment", comments);//信息回复
				DataObject dataObject = new DataObject();
				dataObject.setdata(result);
				dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
				String responseText = JackJsonUtils.toJson(dataObject);
				ResponseUtils.renderJson(response, responseText);
			}else{
				
			}
		}else{
			
		}
	}

}
