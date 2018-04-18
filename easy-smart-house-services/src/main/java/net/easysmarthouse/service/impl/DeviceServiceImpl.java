package net.easysmarthouse.service.impl;

import net.easysmarthouse.service.repository.ActuatorsRepository;
import net.easysmarthouse.service.repository.SensorsRepository;
import net.easysmarthouse.shared.domain.device.DeviceEntity;
import net.easysmarthouse.shared.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.LinkedHashSet;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private SensorsRepository sensorsRepository;

    @Autowired
    private ActuatorsRepository actuatorsRepository;

    @Override
    @Transactional(readOnly = true)
    public Collection<DeviceEntity> searchDevices(final String term) {
        Collection<DeviceEntity> foundDevices = new LinkedHashSet<>();

        foundDevices.addAll(sensorsRepository.search(term));
        foundDevices.addAll(actuatorsRepository.search(term));

        return foundDevices;
    }
}
