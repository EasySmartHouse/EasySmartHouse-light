package net.easysmarthouse.shared.service;

import net.easysmarthouse.shared.domain.device.DeviceEntity;

import java.util.Collection;

public interface DeviceService {

    public Collection<DeviceEntity> searchDevices(String term);

}
