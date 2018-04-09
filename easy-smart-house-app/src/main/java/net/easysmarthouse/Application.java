package net.easysmarthouse;

import net.easysmarthouse.config.DeviceModuleConfig;
import net.easysmarthouse.config.JacksonConfig;
import net.easysmarthouse.config.ServiceConfig;
import net.easysmarthouse.config.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import javax.xml.ws.Service;

@SpringBootApplication
@Import({ServiceConfig.class, DeviceModuleConfig.class, WebConfig.class, JacksonConfig.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
