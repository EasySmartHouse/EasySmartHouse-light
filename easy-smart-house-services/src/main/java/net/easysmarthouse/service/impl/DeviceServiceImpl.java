package net.easysmarthouse.service.impl;

import net.easysmarthouse.network.NetworkManager;
import net.easysmarthouse.network.exception.NetworkException;
import net.easysmarthouse.service.repository.ActuatorsRepository;
import net.easysmarthouse.service.repository.SensorsRepository;
import net.easysmarthouse.shared.domain.device.DeviceEntity;
import net.easysmarthouse.shared.service.DeviceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl implements DeviceService {

    private Logger logger = Logger.getLogger(DeviceServiceImpl.class);

    @Autowired
    private SensorsRepository sensorsRepository;

    @Autowired
    private ActuatorsRepository actuatorsRepository;

    @Autowired
    private NetworkManager networkManager;

    @Override
    @Transactional(readOnly = true)
    public Collection<DeviceEntity> searchDevices(final String term) {
        Collection<DeviceEntity> foundDevices = new LinkedHashSet<>();

        foundDevices.addAll(sensorsRepository.search(term));
        foundDevices.addAll(actuatorsRepository.search(term));

        return foundDevices;
    }

    @Override
    public Collection<String> getAddresses() {
        try {
            networkManager.startSession();
            return networkManager.getDevices()
                    .stream()
                    .map(device -> device.getAddress())
                    .collect(Collectors.toList());
        } catch (NetworkException ex) {
            logger.error("Error while getting devices addresses");
            throw new RuntimeException(ex);
        } finally {
            try {
                networkManager.endSession();
            }catch(Exception ex){
                logger.error("Fail to finalize session");
            }
        }
    }
}
