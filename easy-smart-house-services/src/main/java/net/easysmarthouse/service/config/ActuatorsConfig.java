package net.easysmarthouse.service.config;

import net.easysmarthouse.modules.ActuatorControlModule;
import net.easysmarthouse.provider.device.actuator.ActuatorsModule;
import net.easysmarthouse.sheduler.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.remoting.rmi.RmiServiceExporter;

@Configuration
@Import(SchedulerConfig.class)
public class ActuatorsConfig {

    @Bean(name="actuatorsModule", initMethod="initModule")
    public ActuatorsModule actuatorsModule(Scheduler scheduler){
        ActuatorControlModule controlModule = new ActuatorControlModule();
        controlModule.setScheduler(scheduler);
        controlModule.setTaskDelay(1000l);
        return controlModule;
    }

    @Bean
    public RmiServiceExporter actuatorsService(ActuatorsModule actuatorsModule){
        RmiServiceExporter serviceExporter = new RmiServiceExporter();
        serviceExporter.setServiceName("actuators-service");
        serviceExporter.setService(actuatorsModule);
        serviceExporter.setServiceInterface(ActuatorsModule.class);
        serviceExporter.setRegistryPort(9001);
        return serviceExporter;
    }

}
