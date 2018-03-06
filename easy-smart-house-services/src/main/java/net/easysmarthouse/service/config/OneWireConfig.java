package net.easysmarthouse.service.config;

import net.easysmarthouse.maxim.device.converter.OneWireDeviceConverter;
import net.easysmarthouse.maxim.network.OneWireNetworkManager;
import net.easysmarthouse.maxim.network.adapter.AdapterProvider;
import net.easysmarthouse.maxim.network.adapter.AdapterProviderImpl;
import net.easysmarthouse.maxim.network.extension.OneWireConversionExtensionImpl;
import net.easysmarthouse.maxim.network.extension.OneWireSearchExtension;
import net.easysmarthouse.maxim.network.extension.OneWireSearchExtensionImpl;
import net.easysmarthouse.network.NetworkManager;
import net.easysmarthouse.network.NetworkManagerStorage;
import net.easysmarthouse.network.extension.ConversionExtension;
import net.easysmarthouse.provider.device.converter.DeviceConverter;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@Profile("onewire")
@PropertySources({
        @PropertySource(value = "file:config/application.properties", ignoreResourceNotFound = true),
        @PropertySource(value = "classpath:config/application.properties", ignoreResourceNotFound = true)
})
@Import(NetworkBaseConfig.class)
public class OneWireConfig {

    @Value("${onewire.adapter.name}")
    private String adapterName;

    @Value("${onewire.port.name}")
    private String portName;

    @Bean
    public AdapterProvider adapterProvider() {
        AdapterProviderImpl adapterProvider = new AdapterProviderImpl();
        adapterProvider.setAdapterName(adapterName);
        adapterProvider.setPortName(portName);
        return adapterProvider;
    }

    @Bean(initMethod = "initExtension")
    public OneWireSearchExtension searchExtension(AdapterProvider adapterProvider) {
        try {
            OneWireSearchExtensionImpl oneWireSearchExtension = new OneWireSearchExtensionImpl();
            oneWireSearchExtension.setAdapter(adapterProvider.getAdapter());
            return oneWireSearchExtension;
        } catch (Exception ex) {
            throw new BeanCreationException("oneWireSearchExtension", "Failed to create a OneWireSearchExtension", ex);
        }
    }

    @Bean(name = "oneWireConversionExtension")
    public ConversionExtension conversionExtension(AdapterProvider adapterProvider) {
        try {
            OneWireConversionExtensionImpl conversionExtension = new OneWireConversionExtensionImpl();
            conversionExtension.setAdapter(adapterProvider.getAdapter());
            return conversionExtension;
        } catch (Exception ex) {
            throw new BeanCreationException("conversionExtension", "Failed to create a ConversionExtension", ex);
        }
    }

    @Bean(name = "oneWireDeviceConverter")
    public DeviceConverter deviceConverter() {
        return new OneWireDeviceConverter();
    }

    @Bean(name = "oneWireNetworkManager", initMethod = "init", destroyMethod = "destroy")
    public NetworkManager networkManager(AdapterProvider adapterProvider, OneWireSearchExtension searchExtension,
                                         @Qualifier("oneWireConversionExtension") ConversionExtension conversionExtension,
                                         @Qualifier("oneWireDeviceConverter") DeviceConverter deviceConverter,
                                         NetworkManagerStorage networkManagerStorage) {
        OneWireNetworkManager networkManager = new OneWireNetworkManager();
        networkManager.setAdapterProvider(adapterProvider);
        networkManager.setSearchExtension(searchExtension);
        networkManager.setConversionExtension(conversionExtension);
        networkManager.setDeviceConverter(deviceConverter);
        networkManager.setStorage(networkManagerStorage);
        return networkManager;
    }


    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
