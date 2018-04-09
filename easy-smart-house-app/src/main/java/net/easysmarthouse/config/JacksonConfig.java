package net.easysmarthouse.config;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Autowired(required = true)
    public void configeJackson(ObjectMapper jackson2ObjectMapper) {
        jackson2ObjectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
    }


}
