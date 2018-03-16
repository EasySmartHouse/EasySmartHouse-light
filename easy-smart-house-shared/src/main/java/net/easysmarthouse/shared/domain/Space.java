package net.easysmarthouse.shared.domain;

import java.io.Serializable;
import java.util.Objects;

public class Space implements Serializable{

    private long id;
    private String name;
    private String image;

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
