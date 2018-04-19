package net.easysmarthouse.controller;

import net.easysmarthouse.mail.MailBuilder;
import net.easysmarthouse.service.UserSecurityService;
import net.easysmarthouse.shared.domain.user.PasswordChange;
import net.easysmarthouse.shared.domain.user.User;
import net.easysmarthouse.shared.domain.user.VerificationToken;
import net.easysmarthouse.shared.event.VerifyRegistrationEvent;
import net.easysmarthouse.shared.service.UserService;
import net.easysmarthouse.util.CustomError;
import net.easysmarthouse.util.ErrorType;
import net.easysmarthouse.util.RequestHelper;
import net.easysmarthouse.validation.RestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserSecurityService userSecurityService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private MessageSource messages;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailBuilder mailBuilder;

    @CrossOrigin
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<User> register(final @RequestBody User newUser, WebRequest request) {
        if (newUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (userService.findByUsername(newUser.getUsername()) != null) {
            logger.error(String.format("Username already exist: [%s]", newUser.getUsername()));
            return new ResponseEntity(
                    new CustomError(String.format("User with username [%s] already exist ", newUser.getUsername())),
                    HttpStatus.CONFLICT);
        }

        if (userService.findByEmail(newUser.getEmail()) != null) {
            return new ResponseEntity(
                    new CustomError(String.format("User with such email [%s] already exist ", newUser.getEmail())),
                    HttpStatus.CONFLICT);
        }

        newUser.setRole("USER");
        newUser.setEnabled(Boolean.FALSE);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        userService.save(newUser);
        eventPublisher.publishEvent(
                new VerifyRegistrationEvent(request.getContextPath(), request.getLocale(), newUser)
        );

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity<User> login(Principal principal) {
        if (principal == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        final User user = userService.findByUsername(principal.getName());
        if (user == null) {
            return new ResponseEntity(
                    new CustomError("User not found"),
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

    @RequestMapping(value = "/confirmRegistration", method = RequestMethod.GET)
    public ResponseEntity<?> confirmRegistration(WebRequest request, @RequestParam("token") String token) {
        Locale locale = request.getLocale();

        VerificationToken verificationToken = userService.getVerificationToken(token, true);
        if (verificationToken == null) {
            String message = messages.getMessage("auth.message.invalidToken", null, locale);
            throw new RestException("token", ErrorType.INVALID_TOKEN, message);
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            String message = messages.getMessage("auth.message.expired", null, locale);
            throw new RestException("token", ErrorType.INVALID_TOKEN, message);
        }

        user.setEnabled(true);
        userService.update(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "/resendRegistrationToken", method = RequestMethod.GET)
    public ResponseEntity<?> resendRegistrationToken(WebRequest request, @RequestParam("token") String token) {
        Locale locale = request.getLocale();

        VerificationToken verificationToken = userService.generateNewVerificationToken(token, true);
        if (verificationToken == null) {
            String message = messages.getMessage("auth.message.invalidToken", null, locale);
            throw new RestException("token", ErrorType.INVALID_TOKEN, message);
        }

        eventPublisher.publishEvent(
                new VerifyRegistrationEvent(request.getContextPath(), request.getLocale(), verificationToken.getUser())
        );

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "resetPassword", method = RequestMethod.POST)
    public ResponseEntity<?> resetPassword(HttpServletRequest request, @RequestParam("email") String email) {
        Locale locale = request.getLocale();

        User user = userService.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token);

        final String url = RequestHelper.getAppUrl(request)
                + "/user/changePassword?id="
                + user.getId() + "&token=" + token;
        final String message = messages.getMessage("message.resetPassword", null, locale);

        mailSender.send(
                mailBuilder.createMessage("Reset Password", message + " \r\n" + url, user)
        );

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
    public ResponseEntity<?> changePassword(Locale locale, @RequestParam("id") long id, @RequestParam("token") String token) {
        String result = userSecurityService.validatePasswordResetToken(id, token);
        if (result != null) {
            throw new RestException("token", ErrorType.INVALID_TOKEN,
                    messages.getMessage("auth.message." + result, null, locale));
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/savePassword", method = RequestMethod.POST)
    public ResponseEntity<?> savePassword(Locale locale, @Valid PasswordChange passwordChange) {
        final User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        user.setPassword(passwordEncoder.encode(passwordChange.getNewPassword()));
        userService.changeUserPassword(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
