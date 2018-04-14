package net.easysmarthouse.controller;

import net.easysmarthouse.shared.domain.Image;
import net.easysmarthouse.shared.service.ImageService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/images")
public class ImageController {

    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private ImageService imageService;

    @CrossOrigin
    @RequestMapping(value = "/{fileName:.+}", method = RequestMethod.GET)
    public void getImage(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        final Image image = imageService.getImageByName(fileName);
        if (image != null) {
            IOUtils.copy(new ByteArrayInputStream(image.getContent()), response.getOutputStream());
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
