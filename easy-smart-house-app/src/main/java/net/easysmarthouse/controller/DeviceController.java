package net.easysmarthouse.controller;

import net.easysmarthouse.shared.domain.device.DeviceEntity;
import net.easysmarthouse.shared.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    Collection<DeviceEntity> search(@RequestParam("search") String term) {
        return deviceService.searchDevices(term);
    }

}
