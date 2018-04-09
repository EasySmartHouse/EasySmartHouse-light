package net.easysmarthouse.service.repository;

import net.easysmarthouse.shared.domain.device.ActuatorEntity;

import java.util.List;

public interface ActuatorsRepository {

    public List<ActuatorEntity> getBySpaceId(Integer spaceId);

    public List<ActuatorEntity> findAll();

    public ActuatorEntity findByAddress(String address);

    public List<ActuatorEntity> search(String term);
}
