package org.xi.quick.usermanager.repository.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mysql.cj.api.jdbc.Statement;
import org.xi.quick.usermanager.repository.UserRepository;
import org.xi.quick.usermanager.entity.UserEntity;

@Repository("userRepository")
public class JdbcUserRepository extends JdbcBaseRepository implements UserRepository {

    public Integer insert(UserEntity entity) {

        final UserEntity entityTmp = entity;
        final String sql = "insert into file(username,password,email,phone) values(?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(new PreparedStatementCreator() {
            public java.sql.PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                int i = 0;
                java.sql.PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(++i, entityTmp.getUsername());
                ps.setString(++i, entityTmp.getPassword());
                ps.setString(++i, entityTmp.getEmail());
                ps.setString(++i, entityTmp.getPhone());
                return ps;
            }
        }, keyHolder);
        Integer id = keyHolder.getKey().intValue();
        entity.setUserId(id);
        return id;
    }


    public Integer delete(Integer userId) {

        final String sql = "update user set status=-1 where user_id=?";

        return jdbc.update(sql, userId);
    }

    public Integer update(UserEntity entity) {

        final String sql = "update user password=:password,email=:email,phone=:phone,update_time=now() where user_id=:user_id";

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("password", entity.getPassword());
        paramMap.put("email", entity.getEmail());
        paramMap.put("phone", entity.getPhone());
        paramMap.put("user_id", entity.getUserId());

        return jdbc.update(sql, paramMap);
    }

    public UserEntity selectOne(Integer userId){

        String sql = "select user_id,username,password,email,phone,create_time,status from user where user_id=?";
        UserEntity entity = jdbc.queryForObject(sql, new UserRowMapper(), userId);
        return entity;
    }

    public UserEntity selectOne(String username, String password){

        String sql = "select user_id,username,password,email,phone,create_time,status from user where username=? and password=?";
        UserEntity entity = jdbc.queryForObject(sql, new UserRowMapper(), username, password);
        return entity;
    }

    private final class UserRowMapper implements RowMapper<UserEntity> {

        public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserEntity entity = new UserEntity(rs.getInt("user_id"), rs.getString("username"),
                    rs.getString("password"), rs.getString("email"), rs.getString("phone"),
                    rs.getTimestamp("create_time"), rs.getInt("status"));

            return entity;
        }
    }

}
