package net.easysmarthouse.service.config;

import net.easysmarthouse.modules.SignalingElementModule;
import net.easysmarthouse.provider.device.alarm.SignalingModule;
import net.easysmarthouse.sheduler.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.remoting.rmi.RmiServiceExporter;

@Configuration
@Import(SchedulerConfig.class)
public class SignalingConfig {

    @Bean(name="signalingModule", initMethod="initModule")
    public SignalingModule signalingModule(Scheduler scheduler){
        SignalingElementModule signalingModule = new SignalingElementModule();
        signalingModule.setScheduler(scheduler);
        signalingModule.setTaskDelay(1000l);
        return signalingModule;
    }

    @Bean
    public RmiServiceExporter signalingService(SignalingModule signalingModule){
        RmiServiceExporter serviceExporter = new RmiServiceExporter();
        serviceExporter.setServiceName("signaling-service");
        serviceExporter.setService(signalingModule);
        serviceExporter.setServiceInterface(SignalingModule.class);
        serviceExporter.setRegistryPort(9001);
        return serviceExporter;
    }

}
