package net.easysmarthouse.shared.domain.device;

import net.easysmarthouse.provider.device.actuator.Actuator;

public interface ActuatorEntity<V> extends Actuator<V>, DeviceEntity<V> {
}
