/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.mocks.device.alarm;

import net.easysmarthouse.mocks.device.MockDevice;
import net.easysmarthouse.provider.device.DeviceType;
import net.easysmarthouse.provider.device.alarm.SignalingElement;
import java.util.Random;

/**
 *
 * @author mirash
 */
public class MockSignalingElement extends MockDevice implements SignalingElement {

    private final Random random;
    private String keyAddress;
    private boolean enabled;

    public MockSignalingElement() {
        this.random = new Random();
    }

    @Override
    public DeviceType getDeviceType() {
        return DeviceType.Key;
    }

    @Override
    public String getKeyAddress() {
        return keyAddress;
    }

    @Override
    public boolean isAlarm() {
        return random.nextBoolean();
    }

    @Override
    public void setAlarm(boolean enabled) {
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setKeyAddress(String keyAddress) {
        this.keyAddress = keyAddress;
    }
}
