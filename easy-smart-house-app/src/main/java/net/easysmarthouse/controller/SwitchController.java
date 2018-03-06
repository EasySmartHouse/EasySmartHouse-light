package net.easysmarthouse.controller;

import net.easysmarthouse.provider.device.actuator.Actuator;
import net.easysmarthouse.provider.device.actuator.ActuatorsModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SwitchController {

    @Autowired
    private ActuatorsModule actuatorsModule;

    @RequestMapping(value = "/actuators", method = RequestMethod.GET)
    public List<Actuator> listActuators() {
        return actuatorsModule.getDevices();
    }

}
