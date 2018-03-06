package net.easysmarthouse.service.context;

import net.easysmarthouse.provider.device.actuator.ActuatorsModule;
import net.easysmarthouse.service.repository.ActuatorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class ActuatorsPopulator {
    @Autowired
    private ActuatorsModule actuatorsModule;

    @Autowired
    private ActuatorsRepository actuatorsRepository;

    @Autowired
    private ConfigurableBeanFactory beanFactory;

    @EventListener(ContextRefreshedEvent.class)
    void populate() throws Exception {
        actuatorsRepository.findAll().forEach(sensor -> {
            beanFactory.registerSingleton(sensor.getLabel(), sensor);
            actuatorsModule.getActuators().add(sensor);
        });
    }

}
