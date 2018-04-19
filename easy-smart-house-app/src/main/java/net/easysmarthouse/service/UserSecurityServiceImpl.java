package net.easysmarthouse.service;

import net.easysmarthouse.shared.domain.user.PasswordResetToken;
import net.easysmarthouse.shared.domain.user.User;
import net.easysmarthouse.shared.service.UserService;
import net.easysmarthouse.web.security.Authorities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Calendar;

@Service
public class UserSecurityServiceImpl implements UserSecurityService {

    @Autowired
    private UserService userService;

    @Override
    public String validatePasswordResetToken(long id, String token) {
        final PasswordResetToken passToken = userService.findPasswordResetToken(token, true);
        if ((passToken == null) || (passToken.getUser().getId() != id)) {
            return "invalidToken";
        }

        final Calendar cal = Calendar.getInstance();
        if ((passToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return "expired";
        }

        final User user = passToken.getUser();
        final Authentication auth = new UsernamePasswordAuthenticationToken(
                user, null, Arrays.asList(
                new SimpleGrantedAuthority(Authorities.CHANGE_PASSWORD)
        ));
        SecurityContextHolder.getContext().setAuthentication(auth);
        return null;


    }
}
