package net.easysmarthouse.service.config;

import net.easysmarthouse.provider.device.alarm.SignalingElement;
import net.easysmarthouse.provider.device.alarm.SignalingModule;
import net.easysmarthouse.service.el.DeviceELBeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@ComponentScan({"net.easysmarthouse.service.repository", "net.easysmarthouse.service.context"})
@Import({NetworkBaseConfig.class, SchedulerConfig.class, ActuatorsConfig.class,
        SensorsConfig.class,  TriggersConfig.class, SignalingConfig.class,
        OneWireConfig.class, MockConfig.class, LoggingConfig.class,
        DataSourceConfig.class})
public class RootConfig {

    @Bean
    public BeanFactoryPostProcessor deviceELBeanFactoryPostProcessor(){
        return new DeviceELBeanFactoryPostProcessor();
    }

}
