package net.easysmarthouse.shared.domain;

import com.fasterxml.jackson.annotation.JsonView;
import net.easysmarthouse.provider.device.Device;
import net.easysmarthouse.shared.domain.device.DeviceEntity;
import net.easysmarthouse.shared.json.view.Views;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class Space implements Serializable {

    private long id;
    private String name;

    @JsonView(Views.WithImage.class)
    private String image;

    @JsonView(Views.WithDevices.class)
    private Collection<DeviceEntity> devices = Collections.EMPTY_LIST;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Collection<DeviceEntity> getDevices() {
        return devices;
    }

    public void setDevices(Collection<DeviceEntity> devices) {
        this.devices = devices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Space space = (Space) o;
        return id == space.id &&
                Objects.equals(name, space.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }
}
