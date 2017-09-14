package org.xi.quick.usermanager.repository;

import org.xi.quick.usermanager.entity.UserEntity;

public interface UserRepository {

    Integer insert(UserEntity entity);

    Integer delete(Integer fileId);

    Integer update(UserEntity entity);

    UserEntity selectOne(String username, String password);
}
