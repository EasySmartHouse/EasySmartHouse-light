package net.easysmarthouse.controller;

import net.easysmarthouse.shared.domain.user.User;
import net.easysmarthouse.shared.service.UserService;
import net.easysmarthouse.util.CustomError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @CrossOrigin
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<User> register(@RequestBody User newUser) {
        if (newUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (userService.findByUsername(newUser.getUsername()) != null) {
            logger.error(String.format("Username already exist: [%s]", newUser.getUsername()));
            return new ResponseEntity(
                    new CustomError(String.format("User with username [%s] already exist ", newUser.getUsername())),
                    HttpStatus.CONFLICT);
        }
        newUser.setRole("USER");
        return new ResponseEntity<>(userService.save(newUser), HttpStatus.CREATED);
    }

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity<User> login(Principal principal) {
        if (principal == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        final User user = userService.findByUsername(principal.getName());
        if (user == null) {
            return new ResponseEntity(new CustomError("User not found"),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody User user) {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.info("Updating User with id {}", id);

        User currentUser = userService.findById(id);

        if (currentUser == null) {
            logger.error("Unable to update. User with id {} not found.", id);
            return new ResponseEntity(new CustomError(
                    String.format("Unable to update. User with id [%d] not found.", id)),
                    HttpStatus.NOT_FOUND);
        }

        currentUser.setFirstname(user.getFirstname());
        currentUser.setLastname(user.getLastname());

        userService.update(currentUser);
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }


}
