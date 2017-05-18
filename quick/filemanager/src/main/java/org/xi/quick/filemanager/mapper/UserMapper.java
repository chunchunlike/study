package org.xi.quick.filemanager.mapper;

import org.apache.ibatis.annotations.Param;
import org.xi.quick.filemanager.entity.UserEntity;

public interface UserMapper {
	
	Integer insert(UserEntity entity);
	
	Integer delete(Integer fileId);
	
	Integer update(UserEntity entity);
	
	UserEntity selectOne(@Param("username") String username, @Param("password") String password);
}
