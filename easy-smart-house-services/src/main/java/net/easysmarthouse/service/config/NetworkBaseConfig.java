package net.easysmarthouse.service.config;

import net.easysmarthouse.network.NetworkManager;
import net.easysmarthouse.network.NetworkManagersHub;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class NetworkBaseConfig {

    @Primary
    @Bean(name = "networkManagersHub", destroyMethod = "endSession")
    public NetworkManager networkManagersHub() {
        return new NetworkManagersHub();
    }
}
