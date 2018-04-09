package net.easysmarthouse.shared.domain.device;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.easysmarthouse.provider.device.DeviceType;
import net.easysmarthouse.provider.device.actuator.PlainAdjustableActuator;
import net.easysmarthouse.shared.json.serializer.DeviceTypeSerializer;
import net.easysmarthouse.shared.json.view.Views;

import java.util.Collection;
import java.util.Collections;

public class PlainAdjustableActuatorEntity extends PlainAdjustableActuator implements ActuatorEntity<Double> {

    private Integer id;
    private Boolean enabled;
    private Collection<Integer> voters = Collections.EMPTY_LIST;

    @JsonView(Views.WithSpaceId.class)
    private Integer spaceId;

    @Override
    @JsonSerialize(using = DeviceTypeSerializer.class)
    public DeviceType getDeviceType() {
        return super.getDeviceType();
    }

    public Boolean getEnabled() {
        return enabled;
    }

    @Override
    public Integer getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Integer spaceId) {
        this.spaceId = spaceId;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public Boolean isEnabled() {
        return enabled;
    }

    @Override
    public Double getValue() {
        return getState();
    }

    @Override
    public Collection<Integer> getVoters() {
        return voters;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setVoters(Collection<Integer> voters) {
        this.voters = voters;
    }
}
