package net.easysmarthouse.config;

import net.easysmarthouse.shared.service.DeviceService;
import net.easysmarthouse.shared.service.ImageService;
import net.easysmarthouse.shared.service.SpaceService;
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
public class ServiceConfig {

    @Value("${service.host}")
    private String serviceHost;

    @Value("${service.port}")
    private long servicePort;

    @Bean
    public RmiProxyFactoryBean createSpaceService() {
        RmiProxyFactoryBean proxyBean = new RmiProxyFactoryBean();
        proxyBean.setServiceUrl("rmi://" + serviceHost + ":" + servicePort + "/space-service");
        proxyBean.setServiceInterface(SpaceService.class);
        return proxyBean;
    }

    @Bean
    public RmiProxyFactoryBean createImageService() {
        RmiProxyFactoryBean proxyBean = new RmiProxyFactoryBean();
        proxyBean.setServiceUrl("rmi://" + serviceHost + ":" + servicePort + "/image-service");
        proxyBean.setServiceInterface(ImageService.class);
        return proxyBean;
    }

    @Bean
    public RmiProxyFactoryBean createDeviceService() {
        RmiProxyFactoryBean proxyBean = new RmiProxyFactoryBean();
        proxyBean.setServiceUrl("rmi://" + serviceHost + ":" + servicePort + "/device-service");
        proxyBean.setServiceInterface(DeviceService.class);
        return proxyBean;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
