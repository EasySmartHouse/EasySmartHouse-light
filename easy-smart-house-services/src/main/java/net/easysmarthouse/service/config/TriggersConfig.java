package net.easysmarthouse.service.config;

import net.easysmarthouse.modules.TriggerCheckModule;
import net.easysmarthouse.provider.device.trigger.TriggerModule;
import net.easysmarthouse.sheduler.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.remoting.rmi.RmiServiceExporter;

@Configuration
@Import(SchedulerConfig.class)
public class TriggersConfig {

    @Bean(name="triggersModule", initMethod="initModule")
    public TriggerModule triggersModule(Scheduler scheduler){
        TriggerCheckModule triggerModule = new TriggerCheckModule();
        triggerModule.setScheduler(scheduler);
        triggerModule.setTaskDelay(1000l);
        return triggerModule;
    }

    @Bean
    public RmiServiceExporter triggersService(TriggerModule triggerModule){
        RmiServiceExporter serviceExporter = new RmiServiceExporter();
        serviceExporter.setServiceName("triggers-service");
        serviceExporter.setService(triggerModule);
        serviceExporter.setServiceInterface(TriggerModule.class);
        serviceExporter.setRegistryPort(9001);
        return serviceExporter;
    }

}
