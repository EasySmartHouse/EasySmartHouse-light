package net.easysmarthouse.config;

import net.easysmarthouse.provider.device.actuator.ActuatorsModule;
import net.easysmarthouse.provider.device.alarm.SignalingModule;
import net.easysmarthouse.provider.device.sensor.SensorModule;
import net.easysmarthouse.provider.device.trigger.TriggerModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

@Configuration
@PropertySources({
        @PropertySource(value = "file:services.properties", ignoreResourceNotFound = true),
        @PropertySource(value = "classpath:services.properties", ignoreResourceNotFound = true)
})
public class DeviceModuleConfig {

    @Value("${service.host}")
    private String serviceHost;

    @Value("${service.port}")
    private long servicePort;

    @Bean
    public RmiProxyFactoryBean createSensorModule() {
        RmiProxyFactoryBean proxyBean = new RmiProxyFactoryBean();
        proxyBean.setServiceUrl("rmi://" + serviceHost + ":" + servicePort + "/sensors-service");
        proxyBean.setServiceInterface(SensorModule.class);
        return proxyBean;
    }

    @Bean
    public RmiProxyFactoryBean createActuatorsModule() {
        RmiProxyFactoryBean proxyBean = new RmiProxyFactoryBean();
        proxyBean.setServiceUrl("rmi://" + serviceHost + ":" + servicePort + "/actuators-service");
        proxyBean.setServiceInterface(ActuatorsModule.class);
        return proxyBean;
    }

    @Bean
    public RmiProxyFactoryBean createSignalingModule() {
        RmiProxyFactoryBean proxyBean = new RmiProxyFactoryBean();
        proxyBean.setServiceUrl("rmi://" + serviceHost + ":" + servicePort + "/signaling-service");
        proxyBean.setServiceInterface(SignalingModule.class);
        return proxyBean;
    }

    @Bean
    public RmiProxyFactoryBean createTriggerModule() {
        RmiProxyFactoryBean proxyBean = new RmiProxyFactoryBean();
        proxyBean.setServiceUrl("rmi://" + serviceHost + ":" + servicePort + "/triggers-service");
        proxyBean.setServiceInterface(TriggerModule.class);
        return proxyBean;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
