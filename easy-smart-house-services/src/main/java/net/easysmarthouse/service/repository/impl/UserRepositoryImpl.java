package net.easysmarthouse.service.repository.impl;

import net.easysmarthouse.service.repository.UserRepository;
import net.easysmarthouse.shared.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("ID"));
            user.setUsername(rs.getString("USERNAME"));
            user.setPassword(rs.getString("PASSWORD"));
            user.setRole(rs.getString("ROLE"));
            user.setFirstname(rs.getString("FIRST_NAME"));
            user.setLastname(rs.getString("LAST_NAME"));
            return user;
        }
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User save(User user) {
        jdbcTemplate.update("INSERT INTO USERS (ID, USERNAME, PASSWORD, ROLE, FIRST_NAME, LAST_NAME) VALUES(?, ?, ?, ?, ?, ?)",
                new Object[]{user.getId(), user.getUsername(), user.getPassword(), user.getRole(), user.getFirstname(), user.getLastname()}
        );
        return user;
    }

    @Override
    public User findByUsername(String username) {
        return jdbcTemplate.queryForObject("SELECT * FROM USERS WHERE USERNAME=?",
                new Object[]{username},
                new UserRowMapper()
        );
    }
}
