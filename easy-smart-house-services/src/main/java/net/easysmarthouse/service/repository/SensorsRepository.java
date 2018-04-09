package net.easysmarthouse.service.repository;

import net.easysmarthouse.shared.domain.device.SensorEntity;

import java.util.List;

public interface SensorsRepository {

    public List<SensorEntity> getBySpaceId(Integer spaceId);

    public List<SensorEntity> findAll();

    public SensorEntity findByAddress(String address);

    public int insert(SensorEntity sensor);

    public List<SensorEntity> search(String term);
}
