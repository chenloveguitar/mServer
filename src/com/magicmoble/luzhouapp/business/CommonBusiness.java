package com.magicmoble.luzhouapp.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.magicmoble.luzhouapp.model.Admin_xinxi;
import com.magicmoble.luzhouapp.model.FileManagement;
import com.mysql.jdbc.Statement;

public class CommonBusiness {

	public List<Admin_xinxi> getUsers(){
		List<Admin_xinxi> lists = new ArrayList<Admin_xinxi>();
		String sql = "select * from Admin_xinxi";
		DBHelper db = new DBHelper(sql);
		try {
			ResultSet resultSet = db.pst.executeQuery();
			while (resultSet.next()) {
				String admin_xinxi_id = resultSet.getString(1);
				String touxiang_picture = resultSet.getString(2);
				String name = resultSet.getString(3);
				String weichat = resultSet.getString(4);
				String sex = resultSet.getString(5);
				String qianming = resultSet.getString(6); 
				String shouhuo_address = resultSet.getString(7); 
				String shouhuo_name = resultSet.getString(8); 
				String phone = resultSet.getString(9); 
				Timestamp time = resultSet.getTimestamp(10); 
				double qianbao = resultSet.getDouble(11);
				Admin_xinxi admin_xinxi = new Admin_xinxi(admin_xinxi_id, touxiang_picture, name, weichat, sex, qianming, shouhuo_address, shouhuo_name, phone, time, qianbao);
				lists.add(admin_xinxi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lists;
	}

	public int createNewFolder(Map<String,String> paramMap) {
		String folderName = paramMap.get("folderName");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(new Date());
		String sql = "INSERT INTO file_management (file_name,create_time,parent_id,is_folder) VALUES('"+folderName+"','"+time+"',NULL,1)";
		DBHelper db = new DBHelper(sql);
		try {
			Connection connection = db.conn;
			PreparedStatement prepareStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			int update = prepareStatement.executeUpdate();
			 ResultSet rs = prepareStatement.getGeneratedKeys();
			 if(update > 0 && rs.next()){
				return rs.getInt(1);
			 }
			return -1;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public List<Integer> createImages(List<FileManagement> fileManagements){
		List<Integer> ids = new ArrayList<Integer>();
		StringBuilder sql = new StringBuilder("INSERT INTO file_management (file_name,create_time,parent_id,is_folder,absolute_path,item_id) VALUES");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(new Date());
		for (int i = 0; i < fileManagements.size(); i++) {
			sql.append("('"+
						fileManagements.get(i).getFileName()+
						"','"+
						time+
						"','"+
						fileManagements.get(i).getParentId()+
						"','"+
						fileManagements.get(i).getIsFolder()+
						"','"+
						fileManagements.get(i).getAbsolutePath()+
						"','"+
						fileManagements.get(i).getItemId()+
					"')");
			if(i < fileManagements.size() -1){
				sql.append(",");
			}
		}
		DBHelper db = new DBHelper(sql.toString());
		try {
			Connection connection = db.conn;
			PreparedStatement prepareStatement = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			int update = prepareStatement.executeUpdate();
			 ResultSet rs = prepareStatement.getGeneratedKeys();
			 if(update > 0){
				 while(rs.next()){
					 ids.add(rs.getInt(1)); 
				 }
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ids;
	}
	
	public List<FileManagement> getFolders() {
		List<FileManagement> lists = new ArrayList<FileManagement>();
		String sql = "SELECT * FROM file_management where is_folder = 1";
		DBHelper db = new DBHelper(sql);
		try {
			ResultSet resultSet = db.pst.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String fileName = resultSet.getString(2);
				String createTime = resultSet.getString(3);
				int parentId = resultSet.getInt(4);
				int isFolder = resultSet.getInt(5);
				String absolutePath = resultSet.getString(6);
				String itemId = resultSet.getString(7);
				FileManagement fileManagement = new FileManagement(id, fileName, createTime, parentId, isFolder,absolutePath,itemId);
				lists.add(fileManagement);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lists;
	}
	
	public boolean deleteFolder(String[] ids){
		String sql = " DELETE FROM file_management where 1=1 and id in(";
		for (int i = 0; i < ids.length; i++) {
			if(i < ids.length - 1){
				sql += ids[i] + ",";
			}else{
				sql += ids[i];
			}
		}
		sql += ")";
		DBHelper db = new DBHelper(sql);
		try {
			int update = db.pst.executeUpdate();
			return update > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false ;
	}
	
	public boolean renameFolder(Integer id, String folderName) {
		String sql = " UPDATE file_management SET file_name = '"+folderName+"' WHERE id = " + id;
		DBHelper db = new DBHelper(sql);
		try {
			int update = db.pst.executeUpdate();
			return update > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false ;
	}
	
	public static void main(String[] args) {
		CommonBusiness business = new CommonBusiness();
		List<FileManagement> fileManagements = new ArrayList<FileManagement>();
		String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		Integer isFolder = 0;
		Integer parentId = 31;
		for (int i = 0; i < 50; i++) {
			String fileName = "filename"+i;
			String uuid = UUID.randomUUID().toString() + ".jpeg";
			String absolutePath = "G://upload//"+uuid;
			String itemId = "";
			FileManagement file = new FileManagement(fileName, createTime, parentId, isFolder,absolutePath,itemId);
			fileManagements.add(file);
		}
		List<Integer> list = business.createImages(fileManagements);
		System.out.println(list);
	}

	public List<FileManagement> getImages(Integer pid) {
		List<FileManagement> lists = new ArrayList<FileManagement>();
		String sql = "";
		if(pid == null){
			sql = "SELECT * FROM file_management where is_folder != 1";
			
		}else{
			sql = "SELECT * FROM file_management where is_folder != 1 and parent_id = " + pid;
		}
		DBHelper db = new DBHelper(sql);
		try {
			ResultSet resultSet = db.pst.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String fileName = resultSet.getString(2);
				String createTime = resultSet.getString(3);
				int parentId = resultSet.getInt(4);
				int isFolder = resultSet.getInt(5);
				String absolutePath = resultSet.getString(6);
				String itemId = resultSet.getString(7);
				FileManagement fileManagement = new FileManagement(id, fileName, createTime, parentId, isFolder,absolutePath,itemId);
				lists.add(fileManagement);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lists;
	}

	public List<FileManagement> searchFiles(Map<String, String> searchMap) {
		List<FileManagement> lists = new ArrayList<FileManagement>();
		
		String sql = "SELECT * FROM file_management where 1=1";
		
		if(StringUtils.isNotBlank(searchMap.get("searchPath"))){
			String searchPath = searchMap.get("searchPath");
			if(searchPath.equals("folder")){//文件夹
				sql += " and is_folder = 1";
			}else if(searchPath.equals("images")){//文件
				sql += " and is_folder = 0";
			}
		}
		
		if(StringUtils.isNotBlank(searchMap.get("createDate"))){
			sql += " and DATE_FORMAT(create_time,'%Y-%m-%d') = '"+searchMap.get("createDate")+"'";
		}
		
		if(StringUtils.isNotBlank(searchMap.get("fileName"))){
			sql += " and file_name like '%"+searchMap.get("fileName")+"%'";
		}
		
		if(StringUtils.isNotBlank(searchMap.get("sort"))){
			sql += " order by create_time " + searchMap.get("sort");
		}
		DBHelper db = new DBHelper(sql);
		try {
			ResultSet resultSet = db.pst.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String fileName = resultSet.getString(2);
				String createTime = resultSet.getString(3);
				int parentId = resultSet.getInt(4);
				int isFolder = resultSet.getInt(5);
				String absolutePath = resultSet.getString(6);
				String itemId = resultSet.getString(7);
				FileManagement fileManagement = new FileManagement(id, fileName, createTime, parentId, isFolder,absolutePath,itemId);
				lists.add(fileManagement);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lists;
	}

	public List<FileManagement> getListByIds(String[] ids) {
		List<FileManagement> lists = new ArrayList<FileManagement>();
		StringBuilder sql = new StringBuilder("SELECT * FROM file_management where 1=1 ");
		if(ids != null && ids.length > 0){
			sql.append(" and id in(");
			for (int i = 0; i < ids.length; i++) {
				if(i < ids.length - 1){
					sql.append(ids[i]+",");
				}else{
					sql.append(ids[i]);
				}
			}
			sql.append(")");
		}
		DBHelper db = new DBHelper(sql.toString());
		try {
			ResultSet resultSet = db.pst.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String fileName = resultSet.getString(2);
				String createTime = resultSet.getString(3);
				int parentId = resultSet.getInt(4);
				int isFolder = resultSet.getInt(5);
				String absolutePath = resultSet.getString(6);
				String itemId = resultSet.getString(7);
				FileManagement fileManagement = new FileManagement(id, fileName, createTime, parentId, isFolder,absolutePath,itemId);
				lists.add(fileManagement);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lists;
	}
	
	public List<String> getAbsolutePathsByItemId(String itemId){
		List<String> lists = new ArrayList<String>();
		String sql = "";
		if(StringUtils.isNotBlank(itemId)){
			sql = "SELECT absolute_path FROM file_management where item_id = '" + itemId+"'";
		}
		DBHelper db = new DBHelper(sql);
		try {
			ResultSet resultSet = db.pst.executeQuery();
			while (resultSet.next()) {
				String absolutePath = resultSet.getString(1);
				lists.add(absolutePath);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lists;
	}
}
