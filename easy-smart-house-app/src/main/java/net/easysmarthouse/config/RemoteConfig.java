package net.easysmarthouse.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
        @PropertySource(value = "file:services.properties", ignoreResourceNotFound = true),
        @PropertySource(value = "classpath:services.properties", ignoreResourceNotFound = true)
})
public class RemoteConfig {

    @Value("${service.host}")
    private String serviceHost;

    @Value("${service.port}")
    private long servicePort;

    @Bean
    public ServiceConfigurator serviceConfigurator() {
        return new ServiceConfigurator(serviceHost, servicePort);
    }

}
