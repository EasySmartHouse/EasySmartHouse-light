package net.easysmarthouse.controller;

import net.easysmarthouse.provider.device.sensor.Sensor;
import net.easysmarthouse.provider.device.sensor.SensorModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorsController {

    private static final Logger logger = LoggerFactory.getLogger(SensorsController.class);

    @Autowired
    private SensorModule sensorModule;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listSensors() {
        final List<Sensor> sensors = sensorModule.getDevices();
        if (CollectionUtils.isEmpty(sensors)) {
            logger.warn("No sensors found");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(sensors, HttpStatus.OK);
    }

}
