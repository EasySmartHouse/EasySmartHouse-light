package net.easysmarthouse.shared.domain;

import java.io.Serializable;
import java.util.Objects;

public class Image implements Serializable{

    private long id;
    private String name;
    private byte[] content;

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

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return id == image.id &&
                Objects.equals(name, image.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
