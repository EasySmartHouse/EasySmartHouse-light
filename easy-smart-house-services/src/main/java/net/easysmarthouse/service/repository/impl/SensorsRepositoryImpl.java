package net.easysmarthouse.service.repository.impl;

import net.easysmarthouse.provider.device.sensor.PlainSensor;
import net.easysmarthouse.provider.device.sensor.Sensor;
import net.easysmarthouse.provider.device.sensor.SensorType;
import net.easysmarthouse.service.repository.SensorsRepository;
import net.easysmarthouse.shared.domain.device.ActuatorEntity;
import net.easysmarthouse.shared.domain.device.PlainSensorEntity;
import net.easysmarthouse.shared.domain.device.SensorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SensorsRepositoryImpl implements SensorsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private class SensorsRowMapper implements RowMapper<SensorEntity> {
        @Override
        public SensorEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            PlainSensorEntity sensor = new PlainSensorEntity();
            sensor.setId(rs.getInt("ID"));
            sensor.setAddress(rs.getString("ADDRESS"));
            sensor.setLabel(rs.getString("LABEL"));
            sensor.setDescription(rs.getString("DESCRIPTION"));
            sensor.setEnabled(rs.getBoolean("ENABLED"));
            sensor.setSensorType(SensorType.valueOf(rs.getString("SENSOR_TYPE")));
            sensor.setSpaceId(rs.getInt("SPACE_ID"));
            return sensor;
        }
    }

    public List<SensorEntity> findAll() {
        return jdbcTemplate.query("SELECT * FROM SENSORS", new SensorsRowMapper());
    }

    public SensorEntity findByAddress(String address) {
        return jdbcTemplate.queryForObject("SELECT * FROM SENSORS WHERE ADDRESS=?", new Object[]{address},
                new SensorsRowMapper());
    }

    public int insert(SensorEntity sensor) {
        return jdbcTemplate.update("INSERT INTO SENSORS (ID, ADDRESS, LABEL, DESCRIPTION, ENABLED, SENSOR_TYPE, SPACE_ID) VALUES(?, ?, ?, ?, ?, ?)",
                new Object[]{null, sensor.getAddress(), sensor.getLabel(), sensor.getDescription(), sensor.isEnabled(), sensor.getSensorType().toString(), sensor.getSpaceId()});
    }

    @Override
    public List<SensorEntity> getBySpaceId(Integer spaceId) {
        return jdbcTemplate.query("SELECT * FROM SENSORS WHERE SPACE_ID=?", new Object[]{spaceId},
                new SensorsRowMapper());
    }

    @Override
    public List<SensorEntity> search(String term) {
        return jdbcTemplate.query("SELECT S.* FROM FT_SEARCH_DATA(?, 0, 0) FT, SENSORS S WHERE FT.TABLE='SENSORS' AND S.ID = FT.KEYS[0]",
                new Object[]{term},
                new SensorsRowMapper());
    }
}

