package net.easysmarthouse.service.config;

import net.easysmarthouse.mocks.device.actuator.MockSwitchActuator;
import net.easysmarthouse.mocks.device.sensor.MockSensor;
import net.easysmarthouse.mocks.device.sensor.MockSensorAbstractFactory;
import net.easysmarthouse.mocks.network.MockNetworkManager;
import net.easysmarthouse.network.NetworkManager;
import net.easysmarthouse.network.NetworkManagerStorage;
import net.easysmarthouse.provider.device.Device;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

import java.util.LinkedList;
import java.util.List;

@Configuration
@Profile("dev")
@Import(NetworkBaseConfig.class)
public class MockConfig {

    @Bean(name = "mockDevices")
    public List<Device> mockDevices() {
        List<Device> mocks = new LinkedList<>();

        MockSwitchActuator mockSwitchActuator1 = new MockSwitchActuator();
        mockSwitchActuator1.setAddress("6900000002402B05");
        mocks.add(mockSwitchActuator1);

        MockSensor dynamicTemperatureSensor1 = MockSensorAbstractFactory.createMock("temperature", 25.0, 32.0);
        dynamicTemperatureSensor1.setLabel("TemperatureSensor1");
        dynamicTemperatureSensor1.setAddress("EC000801AC673410");
        mocks.add(dynamicTemperatureSensor1);

        return mocks;
    }

    @Bean(name = "mockNetworkManager")
    public NetworkManager mockNetworkManager(@Qualifier("mockDevices") List<Device> mockDevices,
                                             NetworkManagerStorage networkManagerStorage) {
        MockNetworkManager mockNetworkManager = new MockNetworkManager();
        mockNetworkManager.setDevices(mockDevices);
        mockNetworkManager.setStorage(networkManagerStorage);
        return mockNetworkManager;
    }

}
