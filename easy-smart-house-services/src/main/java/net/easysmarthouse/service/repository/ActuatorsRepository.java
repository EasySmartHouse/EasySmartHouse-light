package net.easysmarthouse.service.repository;

import net.easysmarthouse.provider.device.actuator.Actuator;
import java.util.List;

public interface ActuatorsRepository {

    public List<Actuator> findAll();

    public Actuator findByAddress(String address);
}
