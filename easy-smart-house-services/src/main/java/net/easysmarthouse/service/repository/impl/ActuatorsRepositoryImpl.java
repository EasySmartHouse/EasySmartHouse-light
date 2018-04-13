package net.easysmarthouse.service.repository.impl;

import net.easysmarthouse.provider.device.actuator.Actuator;
import net.easysmarthouse.provider.device.actuator.ActuatorType;
import net.easysmarthouse.provider.device.actuator.PlainAdjustableActuator;
import net.easysmarthouse.provider.device.actuator.SimpleSwitch;
import net.easysmarthouse.service.repository.ActuatorsRepository;
import net.easysmarthouse.shared.domain.device.ActuatorEntity;
import net.easysmarthouse.shared.domain.device.PlainAdjustableActuatorEntity;
import net.easysmarthouse.shared.domain.device.SensorEntity;
import net.easysmarthouse.shared.domain.device.SimpleSwitchEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ActuatorsRepositoryImpl implements ActuatorsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private class ActuatorRowMapper implements RowMapper<ActuatorEntity> {
        @Override
        public ActuatorEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            ActuatorEntity actuator = null;
            ActuatorType type = ActuatorType.valueOf(rs.getString("ACTUATOR_TYPE"));
            switch (type) {
                case switchActuator: {
                    SimpleSwitchEntity simpleSwitch = new SimpleSwitchEntity();
                    simpleSwitch.setId(rs.getInt("ID"));
                    simpleSwitch.setAddress(rs.getString("ADDRESS"));
                    simpleSwitch.setLabel(rs.getString("LABEL"));
                    simpleSwitch.setDescription(rs.getString("DESCRIPTION"));
                    simpleSwitch.setEnabled(rs.getBoolean("ENABLED"));
                    simpleSwitch.setSpaceId(rs.getInt("SPACE_ID"));
                    actuator = simpleSwitch;
                    break;
                }
                case adjustableActuator: {
                    PlainAdjustableActuatorEntity adjustableActuator = new PlainAdjustableActuatorEntity();
                    adjustableActuator.setId(rs.getInt("ID"));
                    adjustableActuator.setAddress(rs.getString("ADDRESS"));
                    adjustableActuator.setLabel(rs.getString("LABEL"));
                    adjustableActuator.setDescription(rs.getString("DESCRIPTION"));
                    adjustableActuator.setEnabled(rs.getBoolean("ENABLED"));
                    adjustableActuator.setMaxValue(rs.getDouble("MAX_VALUE"));
                    adjustableActuator.setMinValue(rs.getDouble("MIN_VALUE"));
                    adjustableActuator.setChangeStep(rs.getDouble("CHANGE_STEP"));
                    adjustableActuator.setDefaultValue(rs.getDouble("DEFAULT_VALUE"));
                    adjustableActuator.setSpaceId(rs.getInt("SPACE_ID"));
                    actuator = adjustableActuator;
                    break;
                }
            }
            return actuator;
        }
    }

    public List<ActuatorEntity> findAll() {
        return jdbcTemplate.query("SELECT act.ID, act.ADDRESS, act.LABEL, act.DESCRIPTION, act.ENABLED, act.ACTUATOR_TYPE, act.SPACE_ID, " +
                "adj.MIN_VALUE, adj.MAX_VALUE, adj.CHANGE_STEP, adj.DEFAULT_VALUE  " +
                "FROM ACTUATORS act LEFT OUTER JOIN ADJUSTABLE_ACTUATORS adj ON act.ID = adj.ACTUATOR", new ActuatorRowMapper());
    }

    public ActuatorEntity findByAddress(String address) {
        return jdbcTemplate.queryForObject("SELECT act.ID, act.ADDRESS, act.LABEL, act.DESCRIPTION, act.ENABLED, act.ACTUATOR_TYPE, act.SPACE_ID, " +
                        "adj.MIN_VALUE, adj.MAX_VALUE, adj.CHANGE_STEP, adj.DEFAULT_VALUE  " +
                        "FROM ACTUATORS act LEFT OUTER JOIN ADJUSTABLE_ACTUATORS adj ON act.ID = adj.ACTUATOR " +
                        "WHERE act.ADDRESS=?", new Object[]{address},
                new ActuatorRowMapper());
    }

    @Override
    public List<ActuatorEntity> getBySpaceId(Integer spaceId) {
        return jdbcTemplate.query("SELECT act.ID, act.ADDRESS, act.LABEL, act.DESCRIPTION, act.ENABLED, act.ACTUATOR_TYPE, act.SPACE_ID, " +
                        "adj.MIN_VALUE, adj.MAX_VALUE, adj.CHANGE_STEP, adj.DEFAULT_VALUE  " +
                        "FROM ACTUATORS act LEFT OUTER JOIN ADJUSTABLE_ACTUATORS adj ON act.ID = adj.ACTUATOR " +
                        "WHERE act.SPACE_ID=?",
                new Object[]{spaceId},
                new ActuatorRowMapper()
        );
    }

    @Override
    public List<ActuatorEntity> search(String term) {
        return jdbcTemplate.query("SELECT act.ID, act.ADDRESS, act.LABEL, act.DESCRIPTION, act.ENABLED, act.ACTUATOR_TYPE, act.SPACE_ID, " +
                        "adj.MIN_VALUE, adj.MAX_VALUE, adj.CHANGE_STEP, adj.DEFAULT_VALUE  " +
                        "FROM FT_SEARCH_DATA(?, 0, 0) FT, ACTUATORS act LEFT OUTER JOIN ADJUSTABLE_ACTUATORS adj ON act.ID = adj.ACTUATOR " +
                        "WHERE FT.TABLE='ACTUATORS' AND act.ID = FT.KEYS[0]",
                new Object[]{term},
                new ActuatorRowMapper()
        );
    }
}
