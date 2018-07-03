package net.easysmarthouse.service.repository;

import net.easysmarthouse.shared.domain.Space;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface SpaceRepository {

    public Space findById(int spaceId);

    public int save(Space space);

    public int update(Space space);

    public List<Space> findAll();

    public Space getWithDevices(Integer spaceId);

}
