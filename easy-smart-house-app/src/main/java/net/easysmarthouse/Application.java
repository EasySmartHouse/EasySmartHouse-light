package net.easysmarthouse;

import net.easysmarthouse.config.DeviceModuleConfig;
import net.easysmarthouse.config.ServiceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import javax.xml.ws.Service;

@SpringBootApplication
@Import({ServiceConfig.class, DeviceModuleConfig.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
