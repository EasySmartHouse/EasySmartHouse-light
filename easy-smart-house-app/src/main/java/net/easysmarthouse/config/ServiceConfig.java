package net.easysmarthouse.config;

import net.easysmarthouse.shared.service.DeviceService;
import net.easysmarthouse.shared.service.ImageService;
import net.easysmarthouse.shared.service.SpaceService;
import net.easysmarthouse.shared.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

@Configuration
@Import(RemoteConfig.class)
public class ServiceConfig {

    @Autowired
    private ServiceConfigurator serviceConfigurator;

    @Bean
    public RmiProxyFactoryBean createSpaceService() {
        RmiProxyFactoryBean proxyBean = new RmiProxyFactoryBean();
        proxyBean.setServiceUrl(
                serviceConfigurator.getServiceUrl("space-service")
        );
        proxyBean.setServiceInterface(SpaceService.class);
        return proxyBean;
    }

    @Bean
    public RmiProxyFactoryBean createImageService() {
        RmiProxyFactoryBean proxyBean = new RmiProxyFactoryBean();
        proxyBean.setServiceUrl(
                serviceConfigurator.getServiceUrl("image-service")
        );
        proxyBean.setServiceInterface(ImageService.class);
        return proxyBean;
    }

    @Bean
    public RmiProxyFactoryBean createDeviceService() {
        RmiProxyFactoryBean proxyBean = new RmiProxyFactoryBean();
        proxyBean.setServiceUrl(
                serviceConfigurator.getServiceUrl("device-service")
        );
        proxyBean.setServiceInterface(DeviceService.class);
        return proxyBean;
    }

    @Bean
    public RmiProxyFactoryBean createUserService() {
        RmiProxyFactoryBean proxyBean = new RmiProxyFactoryBean();
        proxyBean.setServiceUrl(
                serviceConfigurator.getServiceUrl("user-service")
        );
        proxyBean.setServiceInterface(UserService.class);
        return proxyBean;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
