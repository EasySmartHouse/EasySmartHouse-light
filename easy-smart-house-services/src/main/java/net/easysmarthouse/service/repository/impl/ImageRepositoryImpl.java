package net.easysmarthouse.service.repository.impl;

import net.easysmarthouse.service.repository.ImageRepository;
import net.easysmarthouse.shared.domain.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ImageRepositoryImpl implements ImageRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private class ImageRowMapper implements RowMapper<Image> {
        @Override
        public Image mapRow(ResultSet rs, int rowNum) throws SQLException {
            Image image = new Image();
            image.setId(rs.getLong("ID"));
            image.setName(rs.getString("FILE_NAME"));
            image.setContent(rs.getBytes("FILE_CONTENT"));
            return image;
        }
    }

    @Override
    public Image findByFileName(String fileName) {
        return jdbcTemplate.queryForObject("SELECT * FROM IMAGES WHERE FILE_NAME=?", new Object[]{fileName},
                new ImageRowMapper());
    }
}
