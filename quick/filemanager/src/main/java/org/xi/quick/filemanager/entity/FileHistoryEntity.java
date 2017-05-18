package org.xi.quick.filemanager.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "file_history")
public class FileHistoryEntity {

	public FileHistoryEntity() {
		super();
	}

	public FileHistoryEntity(Integer fileId, Integer parentId, String fileName, String filePath, Integer fileType,
			String suffix) {
		super();
		this.fileId = fileId;
		this.parentId = parentId;
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileType = fileType;
		this.suffix = suffix;
	}

	public FileHistoryEntity(Integer fileId, Integer parentId, String fileName, String filePath, Integer fileType,
			String suffix, Integer userId) {
		super();
		this.fileId = fileId;
		this.parentId = parentId;
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileType = fileType;
		this.suffix = suffix;
		this.userId = userId;
	}

	public FileHistoryEntity(Integer fileHistoryId, Integer fileId, Integer parentId, String fileName, String filePath,
			Integer fileType, String suffix, Timestamp createTime, Timestamp operateType, Integer userId) {
		super();
		this.fileHistoryId = fileHistoryId;
		this.fileId = fileId;
		this.parentId = parentId;
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileType = fileType;
		this.suffix = suffix;
		this.createTime = createTime;
		this.operateType = operateType;
		this.userId = userId;
	}

	@Id
	@Column(name = "file_history_id")
	private Integer fileHistoryId;

	@Column(name = "file_id")
	private Integer fileId;

	@Column(name = "parent_id")
	private Integer parentId;

	@Column(name = "file_name")
	private String fileName;

	@Column(name = "file_path")
	private String filePath;

	@Column(name = "file_type")
	private Integer fileType;

	@Column(name = "suffix")
	private String suffix;

	@Column(name = "create_time")
	private Timestamp createTime;

	@Column(name = "operate_type")
	private Timestamp operateType;

	@Column(name = "user_id")
	private Integer userId;

	public Integer getFileHistoryId() {
		return fileHistoryId;
	}

	public void setFileHistoryId(Integer fileHistoryId) {
		this.fileHistoryId = fileHistoryId;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Integer getFileType() {
		return fileType;
	}

	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getOperateType() {
		return operateType;
	}

	public void setOperateType(Timestamp operateType) {
		this.operateType = operateType;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
