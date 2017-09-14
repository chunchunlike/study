package org.xi.quick.usermanager.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

public class JdbcBaseRepository {

    @Autowired
    protected JdbcOperations jdbc;

    protected final class IdRowMapper implements RowMapper<Integer> {

        private String idName = "id";

        public IdRowMapper() {

        }

        public IdRowMapper(String idName) {
            this.idName = idName;
        }

        public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getInt(idName);
        }
    }
}
