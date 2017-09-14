package org.xi.quick.usermanager.mapper;

import org.apache.ibatis.annotations.Param;
import org.xi.quick.usermanager.entity.UserEntity;

import java.util.List;

public interface UserMapper {

    Integer insert(UserEntity entity);

    Integer delete(Integer id);

    Integer update(UserEntity entity);

    UserEntity selectOne(@Param("username") String username, @Param("password") String password);

    List<UserEntity> select(UserEntity entity);
}
