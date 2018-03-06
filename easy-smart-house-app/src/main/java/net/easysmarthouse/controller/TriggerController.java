package net.easysmarthouse.controller;

import net.easysmarthouse.provider.device.trigger.Trigger;
import net.easysmarthouse.provider.device.trigger.TriggerModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TriggerController {

    @Autowired
    private TriggerModule triggerModule;

    @RequestMapping(value = "/triggers", method = RequestMethod.GET)
    public List<Trigger> listTriggers() {
        return triggerModule.getDevices();
    }
}
