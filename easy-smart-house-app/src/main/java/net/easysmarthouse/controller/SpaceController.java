package net.easysmarthouse.controller;

import net.easysmarthouse.provider.device.alarm.SignalingModule;
import net.easysmarthouse.shared.domain.Space;
import net.easysmarthouse.shared.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@RestController
public class SpaceController {

    @Autowired
    private SpaceService spaceService;

    @RequestMapping(value = "/spaces", method = RequestMethod.GET)
    public Collection<Space> getSpaces(HttpServletRequest request) {
        final String imagesUrl = request.getRequestURL().toString()
                .replace("/spaces", "/images");
        final Collection<Space> spaces = spaceService.getSpaces();
        spaces.forEach(space ->
                space.setImage(imagesUrl + "/" + space.getImage())
        );
        return spaces;
    }

}
