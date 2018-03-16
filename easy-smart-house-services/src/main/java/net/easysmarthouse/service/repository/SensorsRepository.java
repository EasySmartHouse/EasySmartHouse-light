package net.easysmarthouse.service.repository;

import net.easysmarthouse.provider.device.sensor.Sensor;

import java.util.List;

public interface SensorsRepository {

    public List<Sensor> findAll();

    public Sensor findByAddress(String address);

    public int insert(Sensor sensor);
}
