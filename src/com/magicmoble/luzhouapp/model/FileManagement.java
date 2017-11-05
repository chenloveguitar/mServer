package com.magicmoble.luzhouapp.model;

public class FileManagement {

	private Integer id;//主键
	private String FileName;//文件名称
	private String createTime;//创建时间
	private Integer parentId;//父级文件夹
	private Integer isFolder;//是否是目录 1是 0 不是
	private String absolutePath;//是否是目录 1是 0 不是
	private String itemId;//业务数据id

	public FileManagement() {
		super();
	}

	public FileManagement(Integer id, String fileName, String createTime, Integer parentId, Integer isFolder,String absolutePath,String itemId) {
		super();
		this.id = id;
		this.FileName = fileName;
		this.createTime = createTime;
		this.parentId = parentId;
		this.isFolder = isFolder;
		this.absolutePath = absolutePath;
		this.itemId = itemId;
	}

	public FileManagement(String fileName, String createTime, Integer parentId, Integer isFolder,String absolutePath,String itemId) {
		super();
		this.FileName = fileName;
		this.createTime = createTime;
		this.parentId = parentId;
		this.isFolder = isFolder;
		this.absolutePath = absolutePath;
		this.itemId = itemId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFileName() {
		return FileName;
	}

	public void setFileName(String fileName) {
		FileName = fileName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getIsFolder() {
		return isFolder;
	}

	public void setIsFolder(Integer isFolder) {
		this.isFolder = isFolder;
	}

	public String getAbsolutePath() {
		return absolutePath;
	}

	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	@Override
	public String toString() {
		return "FileManagement [id=" + id + ", FileName=" + FileName + ", createTime=" + createTime + ", parentId="
				+ parentId + ", isFolder=" + isFolder + "]";
	}
	
	

}
