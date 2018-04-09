package net.easysmarthouse.shared.service;

import net.easysmarthouse.shared.domain.Space;

import java.util.Collection;

public interface SpaceService {

    public int update(Space space);

    public int save(Space space);

    public Space getWithDevices(int spaceId);

    public Collection<Space> getSpaces();

}
