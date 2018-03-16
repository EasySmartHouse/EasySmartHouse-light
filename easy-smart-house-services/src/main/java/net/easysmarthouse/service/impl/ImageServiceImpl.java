package net.easysmarthouse.service.impl;

import net.easysmarthouse.service.repository.ImageRepository;
import net.easysmarthouse.shared.domain.Image;
import net.easysmarthouse.shared.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService{

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Image getImageByName(String name) {
        return imageRepository.findByFileName(name);
    }
}
