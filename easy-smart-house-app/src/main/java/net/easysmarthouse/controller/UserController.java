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

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @CrossOrigin
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody User newUser) {
        if (userService.findByUsername(newUser.getUsername()) != null) {
            logger.error(String.format("Username Already exist: [%s]", newUser.getUsername()));
            return new ResponseEntity(
                    new CustomError(String.format("User with username %s already exist ", newUser.getUsername())),
                    HttpStatus.CONFLICT);
        }
        newUser.setRole("USER");

        return new ResponseEntity<User>(userService.save(newUser), HttpStatus.CREATED);
    }

    @CrossOrigin
    @RequestMapping("/login")
    public Principal user(Principal principal) {
        return principal;
    }


}
