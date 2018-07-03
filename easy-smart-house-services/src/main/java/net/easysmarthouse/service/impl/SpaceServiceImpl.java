package net.easysmarthouse.service.impl;

import net.easysmarthouse.network.NetworkManager;
import net.easysmarthouse.network.exception.NetworkException;
import net.easysmarthouse.network.predicate.NetworkSearchPredicate;
import net.easysmarthouse.provider.device.Device;
import net.easysmarthouse.service.repository.SpaceRepository;
import net.easysmarthouse.shared.domain.Space;
import net.easysmarthouse.shared.domain.device.DeviceEntity;
import net.easysmarthouse.shared.service.SpaceService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SpaceServiceImpl implements SpaceService, ApplicationContextAware {

    @Autowired
    private SpaceRepository spaceRepository;

    private ApplicationContext applicationContext;

    @Override
    @Transactional(readOnly = true)
    public Collection<Space> getSpaces() {
        return spaceRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Space getWithDevices(int spaceId) {
        final Space space = spaceRepository.findById(spaceId);
        if (space != null){
            space.setDevices(
                applicationContext.getBeansOfType(DeviceEntity.class)
                        .values().stream()
                        .filter(deviceEntity -> deviceEntity.getSpaceId().equals(spaceId))
                        .collect(Collectors.toList())
            );
        }
        return space;
    }

    @Override
    @Transactional
    public int save(Space space) {
        return spaceRepository.save(space);
    }

    @Override
    @Transactional
    public int update(Space space) {
        return spaceRepository.update(space);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
