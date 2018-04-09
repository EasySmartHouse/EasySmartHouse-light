package net.easysmarthouse.service.repository;

import net.easysmarthouse.shared.domain.Space;

import java.util.List;

public interface SpaceRepository {

    public int save(Space space);

    public int update(Space space);

    public List<Space> findAll();

    public Space getWithDevices(Integer spaceId);

}
