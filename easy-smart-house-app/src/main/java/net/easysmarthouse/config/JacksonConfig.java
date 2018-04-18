package net.easysmarthouse.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.core.Version;

@Configuration
public class JacksonConfig {

    @Autowired(required = true)
    public void configeJackson(ObjectMapper jackson2ObjectMapper) {
        jackson2ObjectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
    }

    private SimpleModule getCustomJsonModule() {
        SimpleModule module = new SimpleModule("CustomJsonModule",
                new Version(1, 0, 0, null, null, null));
        return module;
    }

    @Bean(name = "customJsonMapper")
    public ObjectMapper customJsonMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
        mapper.registerModule(getCustomJsonModule());
        return mapper;
    }

}
