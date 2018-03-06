package net.easysmarthouse.controller;

import net.easysmarthouse.provider.device.alarm.SignalingElement;
import net.easysmarthouse.provider.device.alarm.SignalingModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SignalingController {

    @Autowired
    private SignalingModule signalingModule;

    @RequestMapping(value = "/signalingElements", method = RequestMethod.GET)
    public List<SignalingElement> listSignalingElements() {
        return signalingModule.getDevices();
    }

}
