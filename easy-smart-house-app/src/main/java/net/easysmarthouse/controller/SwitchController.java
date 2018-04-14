package net.easysmarthouse.controller;

import net.easysmarthouse.provider.device.actuator.Actuator;
import net.easysmarthouse.provider.device.actuator.ActuatorsModule;
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
@RequestMapping("/actuators")
public class SwitchController {

    private static final Logger logger = LoggerFactory.getLogger(SwitchController.class);

    @Autowired
    private ActuatorsModule actuatorsModule;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listActuators() {
        final List<Actuator> actuators = actuatorsModule.getDevices();
        if (CollectionUtils.isEmpty(actuators)) {
            logger.warn("No switches found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(actuatorsModule.getDevices(), HttpStatus.OK);
    }

}
