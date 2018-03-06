package net.easysmarthouse.service.repository;

import net.easysmarthouse.provider.device.actuator.Actuator;
import net.easysmarthouse.provider.device.actuator.ActuatorType;
import net.easysmarthouse.provider.device.actuator.PlainAdjustableActuator;
import net.easysmarthouse.provider.device.actuator.SimpleSwitch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ActuatorsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private class ActuatorRowMapper implements RowMapper<Actuator> {
        @Override
        public Actuator mapRow(ResultSet rs, int rowNum) throws SQLException {
            Actuator actuator = null;
            ActuatorType type = ActuatorType.valueOf(rs.getString("ACTUATOR_TYPE"));
            switch (type) {
                case switchActuator: {
                    SimpleSwitch simpleSwitch = new SimpleSwitch();
                    simpleSwitch.setAddress(rs.getString("ADDRESS"));
                    simpleSwitch.setLabel(rs.getString("LABEL"));
                    simpleSwitch.setDescription(rs.getString("DESCRIPTION"));
                    actuator = simpleSwitch;
                    break;
                }
                case adjustableActuator: {
                    PlainAdjustableActuator adjustableActuator = new PlainAdjustableActuator();
                    adjustableActuator.setAddress(rs.getString("ADDRESS"));
                    adjustableActuator.setLabel(rs.getString("LABEL"));
                    adjustableActuator.setDescription(rs.getString("DESCRIPTION"));
                    adjustableActuator.setMaxValue(rs.getDouble("MAX_VALUE"));
                    adjustableActuator.setMinValue(rs.getDouble("MIN_VALUE"));
                    adjustableActuator.setChangeStep(rs.getDouble("CHANGE_STEP"));
                    adjustableActuator.setDefaultValue(rs.getDouble("DEFAULT_VALUE"));
                    actuator = adjustableActuator;
                    break;
                }
            }
            return actuator;
        }
    }

    public List<Actuator> findAll() {
        return jdbcTemplate.query("SELECT act.ADDRESS, act.LABEL, act.DESCRIPTION, act.ACTUATOR_TYPE, " +
                "adj.MIN_VALUE, adj.MAX_VALUE, adj.CHANGE_STEP, adj.DEFAULT_VALUE  " +
                "FROM ACTUATORS act LEFT OUTER JOIN ADJUSTABLE_ACTUATORS adj ON act.ID = adj.ACTUATOR", new ActuatorRowMapper());
    }

    public Actuator findByAddress(String address) {
        return jdbcTemplate.queryForObject("SELECT act.ADDRESS, act.LABEL, act.DESCRIPTION, act.ACTUATOR_TYPE, " +
                        "adj.MIN_VALUE, adj.MAX_VALUE, adj.CHANGE_STEP, adj.DEFAULT_VALUE  " +
                        "FROM ACTUATORS act LEFT OUTER JOIN ADJUSTABLE_ACTUATORS adj ON act.ID = adj.ACTUATOR WHERE act.ADDRESS=?", new Object[]{address},
                new ActuatorRowMapper());
    }

}
