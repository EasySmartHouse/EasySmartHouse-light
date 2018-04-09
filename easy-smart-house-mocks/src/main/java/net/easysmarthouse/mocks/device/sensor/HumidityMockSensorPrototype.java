/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.mocks.device.sensor;

import net.easysmarthouse.provider.device.exception.DeviceException;
import net.easysmarthouse.provider.device.sensor.SensorType;

/**
 *
 * @author mirash
 */
class HumidityMockSensorPrototype extends MockSensor {

    @Override
    public SensorType getSensorType() {
        return SensorType.HumiditySensor;
    }

    @Override
    public Double getValue() throws DeviceException {
        return Double.valueOf("0");
    }
}
