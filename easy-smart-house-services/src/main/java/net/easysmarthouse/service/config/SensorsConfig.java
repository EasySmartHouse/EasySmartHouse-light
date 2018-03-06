package net.easysmarthouse.service.config;

import net.easysmarthouse.modules.SensorMonitoringModule;
import net.easysmarthouse.provider.device.sensor.SensorModule;
import net.easysmarthouse.sheduler.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.remoting.rmi.RmiServiceExporter;

@Configuration
@Import(SchedulerConfig.class)
public class SensorsConfig {

    @Bean(name="sensorsModule", initMethod="initModule")
    public SensorModule actuatorsModule(Scheduler scheduler){
        SensorMonitoringModule sensorModule = new SensorMonitoringModule();
        sensorModule.setScheduler(scheduler);
        sensorModule.setTaskDelay(1000l);
        return sensorModule;
    }

    @Bean
    public RmiServiceExporter sensorsService(SensorModule sensorModule){
        RmiServiceExporter serviceExporter = new RmiServiceExporter();
        serviceExporter.setServiceName("sensors-service");
        serviceExporter.setService(sensorModule);
        serviceExporter.setServiceInterface(SensorModule.class);
        serviceExporter.setRegistryPort(9001);
        return serviceExporter;
    }

}
