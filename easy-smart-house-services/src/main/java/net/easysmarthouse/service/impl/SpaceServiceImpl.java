package net.easysmarthouse.service.impl;

import net.easysmarthouse.service.repository.SpaceRepository;
import net.easysmarthouse.shared.domain.Space;
import net.easysmarthouse.shared.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class SpaceServiceImpl implements SpaceService{

    @Autowired
    private SpaceRepository spaceRepository;

    @Override
    public Collection<Space> getSpaces() {
        return spaceRepository.findAll();
    }
}
