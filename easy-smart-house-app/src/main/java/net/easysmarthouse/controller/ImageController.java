package net.easysmarthouse.controller;

import net.easysmarthouse.shared.domain.Image;
import net.easysmarthouse.shared.service.ImageService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/{fileName:.+}", method = RequestMethod.GET)
    public void getImage(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        final Image image = imageService.getImageByName(fileName);
        if (image != null) {
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
            IOUtils.copy(new ByteArrayInputStream(image.getContent()), response.getOutputStream());
        }
    }
}
