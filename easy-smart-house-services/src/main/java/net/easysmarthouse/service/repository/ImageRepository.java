package net.easysmarthouse.service.repository;

import net.easysmarthouse.shared.domain.Image;

public interface ImageRepository {

    public Image findByFileName(String fileName);

}
