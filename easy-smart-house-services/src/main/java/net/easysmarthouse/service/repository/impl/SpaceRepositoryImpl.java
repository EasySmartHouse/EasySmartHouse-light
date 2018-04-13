package net.easysmarthouse.service.repository.impl;

import net.easysmarthouse.service.repository.ActuatorsRepository;
import net.easysmarthouse.service.repository.SensorsRepository;
import net.easysmarthouse.service.repository.SpaceRepository;
import net.easysmarthouse.shared.domain.Space;
import net.easysmarthouse.shared.domain.device.DeviceEntity;
import net.easysmarthouse.shared.domain.device.SensorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Repository
public class SpaceRepositoryImpl implements SpaceRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ActuatorsRepository actuatorsRepository;

    @Autowired
    private SensorsRepository sensorsRepository;

    private class SpaceRowMapper implements RowMapper<Space> {
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
        return jdbcTemplate.query("SELECT sp.ID, sp.NAME, img.FILE_NAME FROM SPACES sp " +
                        "LEFT OUTER JOIN IMAGES img ON sp.IMAGE = img.ID",
                new SpaceRowMapper());
    }

    @Override
    public Space getWithDevices(Integer spaceId) {
        final Space space = jdbcTemplate.queryForObject("SELECT sp.ID, sp.NAME, img.FILE_NAME FROM SPACES sp " +
                        "LEFT OUTER JOIN IMAGES img ON sp.IMAGE = img.ID " +
                        "WHERE sp.ID=?",
                new Object[]{spaceId},
                new SpaceRowMapper());

        final List<DeviceEntity> spaceDevices = new LinkedList<>();
        space.setDevices(spaceDevices);

        spaceDevices.addAll(sensorsRepository.getBySpaceId(spaceId));
        spaceDevices.addAll(actuatorsRepository.getBySpaceId(spaceId));

        return space;
    }

    @Override
    public int save(Space space) {
        return jdbcTemplate.update("INSERT INTO SPACES (ID, NAME, IMAGE) VALUES(?, ?, ?)",
                new Object[]{null, space.getName(), Integer.valueOf(space.getImage())});
    }

    @Override
    public int update(Space space) {
        return jdbcTemplate.update("UPDATE SPACES SET NAME = ?, IMAGE = ? WHERE ID = ?",
                new Object[]{
                        space.getName(), space.getImage()
                });
    }
}
