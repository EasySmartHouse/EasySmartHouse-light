package net.easysmarthouse.controller;

import net.easysmarthouse.provider.device.trigger.Trigger;
import net.easysmarthouse.provider.device.trigger.TriggerModule;
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
@RequestMapping("/triggers")
public class TriggerController {

    private static final Logger logger = LoggerFactory.getLogger(TriggerController.class);

    @Autowired
    private TriggerModule triggerModule;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listTriggers() {
        final List<Trigger> triggers = triggerModule.getDevices();
        if (CollectionUtils.isEmpty(triggers)) {
            logger.warn("No triggers found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(triggers, HttpStatus.OK);
    }
}
