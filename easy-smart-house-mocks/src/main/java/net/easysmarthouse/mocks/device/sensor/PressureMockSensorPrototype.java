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
class PressureMockSensorPrototype extends MockSensor {

    @Override
    public SensorType getSensorType() {
        return SensorType.PressureSensor;
    }

    @Override
    public Double getValue() throws DeviceException {
        return Double.valueOf("0");
    }
}
