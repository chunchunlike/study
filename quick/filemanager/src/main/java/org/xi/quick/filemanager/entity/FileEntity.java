package org.xi.quick.filemanager.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "file")
public class FileEntity {

    public FileEntity() {
        super();
    }

    public FileEntity(Integer parentId, String fileName, String filePath, Integer fileType, String suffix) {
        super();
        this.parentId = parentId;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileType = fileType;
        this.suffix = suffix;
    }

    public FileEntity(Integer parentId, String fileName, String filePath, Integer fileType, String suffix,
            Integer userId) {
        super();
        this.parentId = parentId;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileType = fileType;
        this.suffix = suffix;
        this.userId = userId;
    }

    public FileEntity(Integer id, Integer parentId, String fileName, String filePath, Integer fileType,
            String suffix, Timestamp createTime, Timestamp updateTime, Integer status, Integer userId) {
        super();
        this.id = id;
        this.parentId = parentId;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileType = fileType;
        this.suffix = suffix;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.status = status;
        this.userId = userId;
    }

    @Id
    @Column(name = "id")
    private Integer id;

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

    @Column(name = "update_time")
    private Timestamp updateTime;

    @Column(name = "status")
    private Integer status;

    @Column(name = "user_id")
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
