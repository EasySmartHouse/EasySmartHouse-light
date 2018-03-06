package net.easysmarthouse.config;

import net.easysmarthouse.provider.device.actuator.ActuatorsModule;
import net.easysmarthouse.provider.device.alarm.SignalingModule;
import net.easysmarthouse.provider.device.sensor.SensorModule;
import net.easysmarthouse.provider.device.trigger.TriggerModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

@Configuration
public class DeviceModuleConfig {

    public static final String SERVICE_ADDRESS = "127.0.0.1";
    public static final int SERVICE_PORT = 9001;

    @Bean
    public RmiProxyFactoryBean createSensorModule() {
        RmiProxyFactoryBean proxyBean = new RmiProxyFactoryBean();
        proxyBean.setServiceUrl("rmi://" + SERVICE_ADDRESS + ":" + SERVICE_PORT + "/sensors-service");
        proxyBean.setServiceInterface(SensorModule.class);
        return proxyBean;
    }

    @Bean
    public RmiProxyFactoryBean createActuatorsModule() {
        RmiProxyFactoryBean proxyBean = new RmiProxyFactoryBean();
        proxyBean.setServiceUrl("rmi://" + SERVICE_ADDRESS + ":" + SERVICE_PORT + "/actuators-service");
        proxyBean.setServiceInterface(ActuatorsModule.class);
        return proxyBean;
    }

    @Bean
    public RmiProxyFactoryBean createSignalingModule() {
        RmiProxyFactoryBean proxyBean = new RmiProxyFactoryBean();
        proxyBean.setServiceUrl("rmi://" + SERVICE_ADDRESS + ":" + SERVICE_PORT + "/signaling-service");
        proxyBean.setServiceInterface(SignalingModule.class);
        return proxyBean;
    }

    @Bean
    public RmiProxyFactoryBean createTriggerModule() {
        RmiProxyFactoryBean proxyBean = new RmiProxyFactoryBean();
        proxyBean.setServiceUrl("rmi://" + SERVICE_ADDRESS + ":" + SERVICE_PORT + "/triggers-service");
        proxyBean.setServiceInterface(TriggerModule.class);
        return proxyBean;
    }

}
