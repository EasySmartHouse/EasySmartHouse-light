package net.easysmarthouse.shared.domain.device;

import net.easysmarthouse.provider.device.Device;

import java.util.Collection;

public interface DeviceEntity<V> extends Device {

    public Integer getId();

    public Boolean isEnabled();

    public V getValue();

    public Collection<Integer> getVoters();

    public Integer getSpaceId();

}
