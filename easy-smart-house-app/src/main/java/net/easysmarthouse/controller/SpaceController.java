package net.easysmarthouse.controller;

import com.fasterxml.jackson.annotation.JsonView;
import net.easysmarthouse.shared.domain.Space;
import net.easysmarthouse.shared.json.view.Views;
import net.easysmarthouse.shared.service.SpaceService;
import net.easysmarthouse.util.CustomError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

import static net.easysmarthouse.util.ResponseHelper.setFullImageUrl;

@RestController
@RequestMapping("/spaces")
public class SpaceController {

    private static final Logger logger = LoggerFactory.getLogger(SpaceController.class);

    @Autowired
    private SpaceService spaceService;

    @CrossOrigin
    @JsonView(Views.WithImageDevicesSpaceId.class)
    @GetMapping
    public ResponseEntity<?> getSpaces(HttpServletRequest request) {
        final Collection<Space> spaces = spaceService.getSpaces();
        if (CollectionUtils.isEmpty(spaces)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        spaces.forEach(space ->
                setFullImageUrl(space, "/spaces", request)
        );
        return new ResponseEntity<>(spaces, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<?> saveSpaces(@RequestBody Space space) {
        int spaceId = spaceService.save(space);
        space.setId(spaceId);
        return new ResponseEntity<>(space, HttpStatus.CREATED);
    }

    @CrossOrigin
    @PutMapping
    public Space updateSpace(@RequestBody Space space) {
        spaceService.update(space);
        return space;
    }

    @CrossOrigin
    @JsonView(Views.WithImageDevicesSpaceId.class)
    @GetMapping(path = {"/{spaceId}"})
    public ResponseEntity<?> getSpaces(@PathVariable Integer spaceId, HttpServletRequest request) {
        final Space space = spaceService.getWithDevices(spaceId);
        if (space == null) {
            final String errorMsg = String.format("Space with id [%d] not found.", spaceId);
            logger.error(errorMsg);
            return new ResponseEntity(new CustomError(errorMsg), HttpStatus.NOT_FOUND);
        }
        setFullImageUrl(space, "/spaces/" + spaceId, request);
        return new ResponseEntity<>(space, HttpStatus.OK);
    }

}
