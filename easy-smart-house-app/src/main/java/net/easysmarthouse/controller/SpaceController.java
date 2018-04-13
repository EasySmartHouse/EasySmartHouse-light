package net.easysmarthouse.controller;

import com.fasterxml.jackson.annotation.JsonView;
import net.easysmarthouse.shared.domain.Space;
import net.easysmarthouse.shared.json.view.Views;
import net.easysmarthouse.shared.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

import static net.easysmarthouse.util.ResponseHelper.setFullImageUrl;

@RestController
@RequestMapping({"/spaces"})
public class SpaceController {

    @Autowired
    private SpaceService spaceService;

    @JsonView(Views.WithImageDevicesSpaceId.class)
    @GetMapping
    public Collection<Space> getSpaces(HttpServletRequest request) {
        final Collection<Space> spaces = spaceService.getSpaces();
        spaces.forEach(space ->
                setFullImageUrl(space, "/spaces", request)
        );
        return spaces;
    }

    @PostMapping
    public Space saveSpaces(@RequestBody Space space) {
        int spaceId = spaceService.save(space);
        space.setId(spaceId);
        return space;
    }

    @PutMapping
    public Space updateSpace(@RequestBody Space space) {
        spaceService.update(space);
        return space;
    }

    @JsonView(Views.WithImageDevicesSpaceId.class)
    @GetMapping(path = {"/{spaceId}"})
    public Space getSpaces(@PathVariable Integer spaceId, HttpServletRequest request) {
        final Space space = spaceService.getWithDevices(spaceId);
        setFullImageUrl(space, "/spaces/" + spaceId, request);
        return space;
    }

}
