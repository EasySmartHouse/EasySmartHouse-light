package net.easysmarthouse.service.config;

import net.easysmarthouse.provider.device.alarm.SignalingElement;
import net.easysmarthouse.provider.device.alarm.SignalingModule;
import net.easysmarthouse.service.el.DeviceELBeanFactoryPostProcessor;
import net.easysmarthouse.shared.service.DeviceService;
import net.easysmarthouse.shared.service.ImageService;
import net.easysmarthouse.shared.service.SpaceService;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.remoting.rmi.RmiServiceExporter;


@Configuration
@ComponentScan({"net.easysmarthouse.service.repository", "net.easysmarthouse.service.context",
        "net.easysmarthouse.service.impl"})
@Import({NetworkBaseConfig.class, SchedulerConfig.class, ActuatorsConfig.class,
        SensorsConfig.class, TriggersConfig.class, SignalingConfig.class,
        OneWireConfig.class, MockConfig.class, LoggingConfig.class,
        DataSourceConfig.class})
public class RootConfig {

    @Bean
    public BeanFactoryPostProcessor deviceELBeanFactoryPostProcessor() {
        return new DeviceELBeanFactoryPostProcessor();
    }

    @Bean
    public RmiServiceExporter spaceService(SpaceService spaceService) {
        RmiServiceExporter serviceExporter = new RmiServiceExporter();
        serviceExporter.setServiceName("space-service");
        serviceExporter.setService(spaceService);
        serviceExporter.setServiceInterface(SpaceService.class);
        serviceExporter.setRegistryPort(9001);
        return serviceExporter;
    }

    @Bean
    public RmiServiceExporter imageService(ImageService imageService) {
        RmiServiceExporter serviceExporter = new RmiServiceExporter();
        serviceExporter.setServiceName("image-service");
        serviceExporter.setService(imageService);
        serviceExporter.setServiceInterface(ImageService.class);
        serviceExporter.setRegistryPort(9001);
        return serviceExporter;
    }

    @Bean
    public RmiServiceExporter deviceService(DeviceService deviceService) {
        RmiServiceExporter serviceExporter = new RmiServiceExporter();
        serviceExporter.setServiceName("device-service");
        serviceExporter.setService(deviceService);
        serviceExporter.setServiceInterface(DeviceService.class);
        serviceExporter.setRegistryPort(9001);
        return serviceExporter;
    }

}
