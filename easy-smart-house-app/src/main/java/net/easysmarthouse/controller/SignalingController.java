package net.easysmarthouse.controller;

import net.easysmarthouse.provider.device.alarm.SignalingElement;
import net.easysmarthouse.provider.device.alarm.SignalingModule;
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
@RequestMapping("/signalingElements")
public class SignalingController {

    private static final Logger logger = LoggerFactory.getLogger(SignalingController.class);

    @Autowired
    private SignalingModule signalingModule;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listSignalingElements() {
        final List<SignalingElement> elements = signalingModule.getElements();
        if (CollectionUtils.isEmpty(elements)) {
            logger.warn("No signaling elements found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(signalingModule.getDevices(), HttpStatus.OK);
    }

}
