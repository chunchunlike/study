package org.xi.quick.filemanager.mapper;

import org.xi.quick.filemanager.entity.FileEntity;

import java.util.List;

public interface FileMapper {

    Integer insert(FileEntity entity);

    Integer delete(Integer id);

    Integer update(FileEntity entity);

    List<FileEntity> selectByParentId(Integer parentId);
}
