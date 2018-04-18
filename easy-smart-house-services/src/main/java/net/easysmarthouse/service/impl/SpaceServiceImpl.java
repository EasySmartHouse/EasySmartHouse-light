package net.easysmarthouse.service.impl;

import net.easysmarthouse.service.repository.SpaceRepository;
import net.easysmarthouse.shared.domain.Space;
import net.easysmarthouse.shared.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class SpaceServiceImpl implements SpaceService {

    @Autowired
    private SpaceRepository spaceRepository;

    @Override
    @Transactional(readOnly = true)
    public Collection<Space> getSpaces() {
        return spaceRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Space getWithDevices(int spaceId) {
        return spaceRepository.getWithDevices(spaceId);
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
}
