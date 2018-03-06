package net.easysmarthouse.service.context;

import net.easysmarthouse.modules.SensorMonitoringModule;
import net.easysmarthouse.provider.device.sensor.SensorModule;
import net.easysmarthouse.service.repository.SensorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class SensorsPopulator {

    @Autowired
    private SensorModule sensorsModule;

    @Autowired
    private SensorsRepository sensorsRepository;

    @Autowired
    private ConfigurableBeanFactory beanFactory;

    @EventListener(ContextRefreshedEvent.class)
    void populate() throws Exception {
        sensorsRepository.findAll().forEach(sensor -> {
            beanFactory.registerSingleton(sensor.getLabel(), sensor);
            sensorsModule.getSensors().add(sensor);
        });
    }
}
