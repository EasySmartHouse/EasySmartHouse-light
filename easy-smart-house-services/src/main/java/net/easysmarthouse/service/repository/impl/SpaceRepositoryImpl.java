package net.easysmarthouse.service.repository.impl;

import net.easysmarthouse.service.repository.SpaceRepository;
import net.easysmarthouse.shared.domain.Space;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SpaceRepositoryImpl implements SpaceRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private class SpaceRowMapper implements RowMapper<Space>{
        @Override
        public Space mapRow(ResultSet rs, int rowNum) throws SQLException {
            Space space = new Space();
            space.setId(rs.getLong("ID"));
            space.setName(rs.getString("NAME"));
            space.setImage(rs.getString("FILE_NAME"));
            return space;
        }
    }

    @Override
    public List<Space> findAll() {
        return jdbcTemplate.query("SELECT sp.ID, sp.NAME, img.FILE_NAME FROM SPACES sp LEFT OUTER JOIN IMAGES img ON sp.IMAGE = img.ID",
                new SpaceRowMapper());
    }
}
