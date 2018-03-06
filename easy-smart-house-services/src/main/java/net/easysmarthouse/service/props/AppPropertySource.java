/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.service.props;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.ResourcePropertySource;

/**
 * @author mirash
 */
public class AppPropertySource extends PropertySource {

    private static final String SOURCE_LOCATION_DEFAULT = "file:config/application.properties";
    private static final String SOURCE_NAME = "application.property.source";
    private PropertySource delegate;

    private final Environment env;

    private void initPropertySource() {
        try {
            final String sourceLocation = Arrays.asList(env.getActiveProfiles()).contains("dev")
                    ? "classpath:config/application.properties"
                    : SOURCE_LOCATION_DEFAULT;
            this.delegate = new ResourcePropertySource(sourceLocation);
        } catch (IOException ex) {
            throw new RuntimeException("application.properties read error", ex);
        }
    }

    public AppPropertySource(Environment env) {
        super(SOURCE_NAME);
        this.env = env;
        initPropertySource();
    }

    @Override
    public Object getProperty(String string) {
        return delegate.getProperty(string);
    }

}
