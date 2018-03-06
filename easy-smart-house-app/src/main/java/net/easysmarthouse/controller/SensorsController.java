package net.easysmarthouse.controller;

import net.easysmarthouse.provider.device.sensor.Sensor;
import net.easysmarthouse.provider.device.sensor.SensorModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SensorsController {

    @Autowired
    private SensorModule sensorModule;

    @RequestMapping(value = "/sensors", method = RequestMethod.GET)
    public List<Sensor> listSensors() {
        return sensorModule.getDevices();
    }

}
