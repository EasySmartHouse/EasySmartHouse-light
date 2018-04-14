package net.easysmarthouse.controller;

import net.easysmarthouse.shared.domain.device.DeviceEntity;
import net.easysmarthouse.shared.service.DeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);

    @Autowired
    private DeviceService deviceService;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<?> search(@RequestParam("search") String term) {
        final Collection<DeviceEntity> entities = deviceService.searchDevices(term);
        if (CollectionUtils.isEmpty(entities)) {
            logger.warn(String.format("No devices found for term [%s]", term));
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

}
