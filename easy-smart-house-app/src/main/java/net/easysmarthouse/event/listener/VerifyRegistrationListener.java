package net.easysmarthouse.event.listener;

import net.easysmarthouse.shared.domain.user.User;
import net.easysmarthouse.shared.event.VerifyRegistrationEvent;
import net.easysmarthouse.shared.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class VerifyRegistrationListener implements ApplicationListener<VerifyRegistrationEvent> {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messages;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment env;

    @Override
    public void onApplicationEvent(VerifyRegistrationEvent event) {
        confirmRegistration(event);
    }

    private void confirmRegistration(VerifyRegistrationEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();

        userService.createVerificationToken(user, token);

        final SimpleMailMessage email = createEmailMessage(event, user, token);
        mailSender.send(email);
    }

    private final SimpleMailMessage createEmailMessage(final VerifyRegistrationEvent event, final User user, final String token) {
        final String recipientAddress = user.getEmail();
        final String subject = "Registration Confirmation";
        final String confirmationUrl = event.getAppUrl() + "/registrationConfirm?token=" + token;
        final String message = messages.getMessage("message.mail.registration.success", null, event.getLocale());
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " \r\n" + confirmationUrl);
        email.setFrom(env.getProperty("support.email"));
        return email;
    }

}
