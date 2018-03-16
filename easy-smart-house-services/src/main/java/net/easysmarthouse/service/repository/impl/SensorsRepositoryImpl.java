package net.easysmarthouse.service.repository.impl;

import net.easysmarthouse.provider.device.sensor.PlainSensor;
import net.easysmarthouse.provider.device.sensor.Sensor;
import net.easysmarthouse.provider.device.sensor.SensorType;
import net.easysmarthouse.service.repository.SensorsRepository;
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

    private class SensorsRowMapper implements RowMapper<Sensor> {
        @Override
        public Sensor mapRow(ResultSet rs, int rowNum) throws SQLException {
            PlainSensor sensor = new PlainSensor();
            sensor.setAddress(rs.getString("ADDRESS"));
            sensor.setLabel(rs.getString("LABEL"));
            sensor.setDescription(rs.getString("DESCRIPTION"));
            sensor.setSensorType(SensorType.valueOf(rs.getString("SENSOR_TYPE")));
            return sensor;
        }
    }

    public List<Sensor> findAll() {
        return jdbcTemplate.query("SELECT * FROM SENSORS", new SensorsRowMapper());
    }

    public Sensor findByAddress(String address) {
        return jdbcTemplate.queryForObject("SELECT * FROM SENSORS WHERE ADDRESS=?", new Object[]{address},
                new SensorsRowMapper());
    }

    public int insert(Sensor sensor) {
        return jdbcTemplate.update("INSERT INTO SENSORS (ID, ADDRESS, LABEL, DESCRIPTION, SENSOR_TYPE) VALUES(?, ?, ?, ?, ?)",
                new Object[]{null, sensor.getAddress(), sensor.getLabel(), sensor.getDescription(), sensor.getSensorType().toString()});
    }


}

