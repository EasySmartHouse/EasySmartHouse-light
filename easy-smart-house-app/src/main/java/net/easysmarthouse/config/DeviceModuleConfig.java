package net.easysmarthouse.config;

import net.easysmarthouse.provider.device.actuator.ActuatorsModule;
import net.easysmarthouse.provider.device.alarm.SignalingModule;
import net.easysmarthouse.provider.device.sensor.SensorModule;
import net.easysmarthouse.provider.device.trigger.TriggerModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

@Configuration
@Import(RemoteConfig.class)
public class DeviceModuleConfig {

    @Autowired
    private ServiceConfigurator serviceConfigurator;

    @Bean
    public RmiProxyFactoryBean createSensorModule() {
        RmiProxyFactoryBean proxyBean = new RmiProxyFactoryBean();
        proxyBean.setServiceUrl(
                serviceConfigurator.getServiceUrl("sensors-service")
        );
        proxyBean.setServiceInterface(SensorModule.class);
        return proxyBean;
    }

    @Bean
    public RmiProxyFactoryBean createActuatorsModule() {
        RmiProxyFactoryBean proxyBean = new RmiProxyFactoryBean();
        proxyBean.setServiceUrl(
                serviceConfigurator.getServiceUrl("actuators-service")
        );
        proxyBean.setServiceInterface(ActuatorsModule.class);
        return proxyBean;
    }

    @Bean
    public RmiProxyFactoryBean createSignalingModule() {
        RmiProxyFactoryBean proxyBean = new RmiProxyFactoryBean();
        proxyBean.setServiceUrl(
                serviceConfigurator.getServiceUrl("signaling-service")
        );
        proxyBean.setServiceInterface(SignalingModule.class);
        return proxyBean;
    }

    @Bean
    public RmiProxyFactoryBean createTriggerModule() {
        RmiProxyFactoryBean proxyBean = new RmiProxyFactoryBean();
        proxyBean.setServiceUrl(
                serviceConfigurator.getServiceUrl("triggers-service")
        );
        proxyBean.setServiceInterface(TriggerModule.class);
        return proxyBean;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
