package org.xi.quick.filemanager.mapper;

import org.xi.quick.filemanager.entity.FileHistoryEntity;

import java.util.List;

public interface FileHistoryMapper {

    int insert(FileHistoryEntity entity);

    List<FileHistoryEntity> select(Integer fileId);
}
