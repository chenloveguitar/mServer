package com.magicmoble.luzhouapp.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.magicmoble.luzhouapp.constant.Constants;


public class FileUploadUtil {
	
	private HttpServletRequest request;
	private List<String[]> listPath;
	private Map<String, String> formData;
	
	
	public FileUploadUtil(HttpServletRequest request){
		this.request = request;
		this.listPath = new ArrayList<String[]>();
		this.formData = new HashMap<String, String>();
	}
	
	public Map<String, String> getFormData(){
		return formData;
	}
	
	public List<String[]> getListPath(){
		return listPath;
	}
	
	public FileUploadUtil upload(String deletes){
		DiskFileItemFactory diskFactory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(diskFactory);
		upload.setSizeMax(Long.MAX_VALUE);
		File parent = new File(Constants.uploadPath);
		if(!parent.exists()){
			parent.mkdirs();
		}
		try {
			List<FileItem> fileItems = upload.parseRequest(request);
			Iterator<FileItem> iterator = fileItems.iterator();
			String[] deletedFileNames = {};
			if(deletes != null){
				deletedFileNames = deletes.split(",");
			}
			q:while (iterator.hasNext()) {
				FileItem item = iterator.next();
				if (item.isFormField()) {
					String name = item.getFieldName();  
			        String value = item.getString("UTF-8"); 
			        formData.put(name, value);
				} else {
					//移除其他的文件
					String filename = item.getName(); 
					for (int i = 0; i < deletedFileNames.length; i++) {
						if(filename.equals(deletedFileNames[i])){
							continue q;
						}
					}
					//上传是的名称
					//上传后放入数据库的名称
					String absolutePath = UUID.randomUUID().toString();
					String[] split = filename.split("\\.");
					if(split.length >= 2){
						absolutePath += "." + split[1];
					}
			        File uploadFile = new File(parent,absolutePath);
			        if(!uploadFile.exists()){
			        	uploadFile.createNewFile();
			        }
			        String[] fileInfo = {filename,absolutePath};
					item.write(uploadFile);
					listPath.add(fileInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
}
